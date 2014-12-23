<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/10
 * Time: 19:53
 */

namespace Monitor\Controller;

use Common\Model\Constant;
use Common\Model\RedisModel;
use Think\Controller;
use Common\Model\NodeModel;

class NodeController extends Controller
{
    private $nodeModel;
    private $redisModel;
    private $map;

    public function __construct()
    {
        parent::__construct();
        $this->nodeModel = new NodeModel();
        $this->redisModel = new RedisModel();
        $this->map = Constant::$constant['redis_map'];
    }

    //获取所有node
    public function getNodes()
    {
        $this->ajaxReturn($this->nodeModel->listAll());
    }

    //根据Host获取一个node
    public function getNodeById()
    {
        $condition = ['id' => $_REQUEST['id']];
        $this->ajaxReturn($this->nodeModel->listOneByCondition($condition));
    }

    public function getInstancesOnNode()
    {
        $ip = $_REQUEST['id'];
        $array = $this->redisModel->getByCondition(['ip' => $ip]);
        $result = [];
        foreach ($this->map as $mongoKey => $frontKey) {
            $result[$frontKey] = $array[$mongoKey];
        }
        $this->ajaxReturn($result);
    }

    public function getHistory()
    {
        $ip = $_REQUEST['id'];
        $time = $_REQUEST['time'];
        $this->ajaxReturn($this->redisModel->getByCondition(['ip' => $ip, 'date' => ['$gte' => $time]]));
    }

    public function getHistoryAll()
    {
        $ip = $_REQUEST['id'];
        $this->ajaxReturn($this->redisModel->getByCondition($condition = ['ip' => $ip], $sort = ['date' => -1]));
    }

    public function getHistoryBetween()
    {
        $ip = $_REQUEST['id'];
        $start = $_REQUEST['start'];
        $end = $_REQUEST['end'];
        $this->ajaxReturn($this->redisModel->getByCondition($condition = ['ip' => $ip, 'date' => ['$gte' => $start, '$lt' => $end]]));
    }
}