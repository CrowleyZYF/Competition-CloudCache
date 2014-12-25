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
use Monitor\Model\NodeInfoModel;
use Think\Controller;
use Common\Model\NodeModel;
use MongoDate;

class NodeController extends Controller
{
    private $nodeModel;
    private $infoModel;
    private $redisModel;
    private $map;
    private $nodeId;

    public function __construct()
    {
        parent::__construct();
        $this->nodeModel = new NodeModel();
        if (isset($_REQUEST['id'])) {
            $this->nodeId = $_REQUEST['id'];
            $this->redisModel = new RedisModel();
            $this->infoModel = new NodeInfoModel($this->nodeId);
        }
        $this->map = Constant::$constant['redis_map'];
    }

    //获取所有node
    public function getNodes()
    {
        $this->ajaxReturn($this->nodeModel->getAll());
    }

    //根据Host获取一个node
    public function getNodeById()
    {
        $condition = ['id' => $this->nodeId];
        $this->ajaxReturn($this->nodeModel->getOneByCondition($condition));
    }

    public function getInstancesOnNode()
    {
        $instances = $this->redisModel->getByCondition(['ip' => $this->nodeId]);
        $result = [];
        foreach ($instances as $instance) {
            $temp = [];
            foreach ($this->map as $mongoKey => $frontKey) {
                $temp[$frontKey] = $instance[$mongoKey];
            }
            $result[] = $temp;
        }
        $this->ajaxReturn($result);
    }

    public function getHistory()
    {
        $time = isset($_REQUEST['time']) ? $_REQUEST['time'] : new MongoDate(0);
        if (isset($_REQUEST['limit']))
            $this->ajaxReturn($this->infoModel->getByCondition(['date' => ['$gte' => $time]], $sort = ['date' => -1], $_REQUEST['limit']));
        else
            $this->ajaxReturn($this->infoModel->getByConditionAll(['date' => ['$gte' => $time]], $sort = ['date' => -1]));
    }

    public function getHistoryAll()
    {
        $this->ajaxReturn($this->infoModel->getByConditionAll($condition = [], $sort = ['date' => -1]));
    }

    public function getHistoryBetween()
    {
        $start = $_REQUEST['start'];
        $end = $_REQUEST['end'];
        if (isset($_REQUEST['limit'])) {
            $this->ajaxReturn($this->infoModel->getByCondition($condition = ['date' => ['$gte' => $start, '$lt' => $end]], $_REQUEST['limit']));
        } else {
            $this->ajaxReturn($this->infoModel->getByConditionAll($condition = ['date' => ['$gte' => $start, '$lt' => $end]]));
        }
    }
}