<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/13
 * Time: 19:42
 */

namespace Manage\Controller;

use Common\Model\Constant;
use Common\Model\NodeModel;
use Common\Model\RedisModel;
use Common\Model\UserModel;
use Monitor\Model\RedisInfoModel;
use Think\Controller;
use MongoDate;

class RedisController extends Controller
{
    private $map;
    private $nodeModel;
    private $redisModel;
    private $userModel;

    public function __construct()
    {
        $this->nodeModel = new NodeModel();
        $this->redisModel = new RedisModel();
        $this->userModel = new UserModel();
        $this->map = Constant::$constant['redis_map'];
    }

    public function getInstances()
    {
        $array = $this->redisModel->getAll();
        $result = [];
        foreach ($array as $item) {
            $result[] = $this->backToFront($item);
        }
        $this->ajaxReturn($result);
    }

    public function getInstance()
    {
        $id = $_REQUEST['id'];
        $array = $this->redisModel->getByCondition(['ip:port' => $id]);
        $result = [];
        foreach ($array as $item) {
            $result[] = $this->backToFront($item);
        }
        $this->ajaxReturn($result);
    }

    public function createInstance()
    {
        //parse ajax;
        $param = [];
        foreach ($this->map as $mongoName => $frontName) {
            $param[$mongoName] = $_REQUEST[$frontName];
        }
        $max = isset($param['maxmemory']) ? $param['max'] : 256 * 1024 * 1024;
        $min = 128 * 1024 * 1024;
        //get information from db emem_system collection node redis
        //and choose one;
        $node = $this->chooseNode(); //        根据节点的负载情况找到合适的位置.
        $ip = $node['ip'];
        $port = $node['port'];
        $ports = $node['available_ports'];
        //调用shell脚本执行redis启动
        $shell = $this->execNew($ip, $port, $max);
        if ($shell == 'ok') {
            $param['threashold'] = 10;
            $param['number'] = 0;
            $param['maxmemory'] = $max;
            $param['min_memory'] = $min;
            $param['ip:port'] = $ip . ':' . $port;
            $param['ip'] = $ip;
            $param['status'] = 1;
            $param['create_time'] = new MongoDate();//(new \DateTime())->format('Y-m-d H:i:sP');
            //如果成功
            $this->redisModel->insert($param);
            $this->nodeModel->update(['ip' => $ip], ['available_port' => $ports]);

            $userParam = ['ip:port' => $ip . ':' . $port, 'host' => $ip, 'port' => $port, 'token' => $ip . ':' . $port];
            $this->userModel->insert($userParam);
            $this->post(Constant::$constant['cache_system'] . '/token/set', $userParam);
            $this->ajaxReturn($param);
        } else {
            $this->error('New Instance failed' . print_r($shell));
        }
    }

    public function stopInstances()
    {
        $ids = $_REQUEST['ids'];
        foreach ($ids as $id) {
            $result = $this->stopOneInstance($id);
            if (!empty($result)) {
                $this->ajaxReturn($result);
            }
        }
    }

    public function startInstances()
    {
        $ids = $_REQUEST['ids'];
        foreach ($ids as $id) {
            $result = $this->startOneInstance($id);
            if (!empty($result)) {
                $this->ajaxReturn($result);
            }
        }
    }

    public function deleteInstances()
    {
        $ids = $_REQUEST['ids'];
        foreach ($ids as $id) {
            $result = $this->deleteOneInstance($id);
            if (!empty($result)) {
                $this->ajaxReturn($result);
            }
        }
    }

    public function deleteInstance()
    {
        $id = $_REQUEST['id'];
        $result = $this->deleteOneInstance($id);
        if (!empty($result)) {
            $this->ajaxReturn($result);
        }
    }

    private function stopOneInstance($id)
    {
        //根据请求的参数
        $data = explode(':', $id);
        $ip = $data[0];
        $port = $data[1];
        //执行stop脚本.
        $shell = $this->execStop($ip, $port);
        if ($shell == 'ok') {
            $this->redisModel->stop(['ip:port' => $id]);
        } else {
            return $this->error('Stop failed' . $shell);
        }
    }

    private function startOneInstance($id)
    {
        $data = explode(':', $id);
        $ip = $data[0];
        $port = $data[1];
        //执行resume脚本
        $shell = $this->execStart($ip, $port);
        if ($shell == 'ok') {
            $this->redisModel->start(['ip:port' => $id]);
        } else {
            return $this->error('Start failed' . $shell);
        }
    }

    private function deleteOneInstance($id)
    {
        $data = explode(':', $id);
        $ip = $data[0];
        $port = $data[1];
        //执行resume脚本

        //数据库state更新.
        $shell = $this->execDelete($ip, $port);
        if ($shell == 'ok') {
            $this->redisModel->delete(['ip:port' => $id]);
            $redisInfoModel = new RedisInfoModel($data);
            $redisInfoModel->deleteCollection($id);
            $ports = $this->nodeModel->getOneByCondition(['ip' => $ip])['available_port'];
            $ports[] = (int)$port;
            array_unique($ports);
            $this->nodeModel->update(['ip' => $ip], ['available_port' => $ports]);
            $token = $this->userModel->delete(['ip:port' => $id]);
            $this->post(Constant::$constant['cache_system'] . '/token/remove', ['token' => $token]);
        } else {
            return $this->error('Delete failed' . $shell);
        }
    }

    private function chooseNode()
    {
        $nodeModel = $this->nodeModel;
        $nodes = $nodeModel->getAll();
        //print_r($nodes);
        $mems = [];
        foreach ($nodes as $node) {
            $mems[] = $node['mem_free'];
        }
        arsort($mems);
        foreach ($mems as $key => $mem) {
            if (!empty($nodes[$key]['available_port'])) {
                $result = ['ip' => $nodes[$key]['ip'], 'port' => array_pop($nodes[$key]['available_port']), 'available_ports' => $nodes[$key]['available_port']];
                return $result;
            }
        }
        return $this->error('no available port');
    }

    private function execNew($ip, $port, $max)
    {
        return $this->execCommand("/home/trollyxia/manage/create_redis.sh $ip $port $max");
    }

    private function execStart($ip, $port)
    {
        return $this->execCommand("/home/trollyxia/manage/start_redis.sh $ip $port");
    }

    private function execStop($ip, $port)
    {
        return $this->execCommand("/home/trollyxia/manage/stop_redis.sh $ip $port");
    }

    private function execDelete($ip, $port)
    {
        return $this->execCommand("/home/trollyxia/manage/delete_redis.sh $ip $port");
    }

    private function execCommand($command)
    {
        exec($command, $result, $code);
        if ($code != 0) {
            return $result;
        } else {
            return 'ok';
        }
    }

    private function backToFront($array)
    {
        $result = [];
        foreach ($this->map as $mongoName => $frontName) {
            $result[$frontName] = $array[$mongoName];
        }
        $result['time'] = date('Y-m-d H:i:s', $result['time']->sec);
        if (!isset($result['used'])) $result['used'] = 0;
        return $result;
    }

    private function translateMemory($size)
    {
        return $size . 'm';
    }

    protected function error($msg)
    {
        return $msg;
    }

    function post($url, $post_data)
    {
        $postdata = http_build_query($post_data);
        $options = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-type:application/x-www-form-urlencoded',
                'content' => $postdata,
                'timeout' => 15 * 60 // 超时时间（单位:s）
            )
        );
        $context = stream_context_create($options);
        $result = file_get_contents($url, false, $context);
        return $result;
    }

}