<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/13
 * Time: 19:42
 */

namespace Manage\Controller;

use Think\Controller;
use Common\Model\NodeModel;
use Common\Model\RedisModel;

class ManageInstanceController extends Controller
{
    private $nodeModel;
    private $redisModel;

    public function __construct()
    {
        $this->nodeModel = new NodeModel();
        $this->redisModel = new RedisModel();
    }

    public function newInstance()
    {
        //parse ajax;
        $max = isset($_REQUEST['max']) ? $_REQUEST['max'] : 2147483648;
        $min = isset($_REQUEST['min']) ? $_REQUEST['min'] : 134217728;
        //get information from db emem_system collection node redis
        //and choose one;
        $ip = choosNode();

        //调用shell脚本执行redis启动,并返回port
        $port = startRedis();

        $param = ['threashold' => 20, 'number' => 0];
        $param['max'] = $max;
        $param['min'] = $min;
        $param['ip:port'] = $ip.':'.$port;
        $param['state'] = 1;
        //如果成功
        $this->redisModel->insert($param);
    }

    public function stopInstance()
    {
        //根据请求的参数
        $data = $_REQUEST['data'];

        //执行stop脚本.

        //数据库state更新.
        $this->redisModel->stop([$data]);
    }

    public function startInstance()
    {
        $data = $_REQUEST['data'];

        //执行resume脚本

        //数据库state更新.
        $this->redisModel->start($data);
    }

    public function deleteInstance()
    {
        $data = $_REQUEST['data'];

        //先关闭redis，执行stop脚本
        //执行delete脚本

        //数据库刪除
        $this->redisModel->delete($data);
    }

    private function chooseNode()
    {
        $nodes = $this->nodeModel->listAll();
//        根据节点的负载情况找到合适的位置.
    }
}