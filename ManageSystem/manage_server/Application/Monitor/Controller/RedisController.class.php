<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/10
 * Time: 19:53
 */

namespace Monitor\Controller;

use Common\Model\RedisModel;
use Monitor\Model\RedisInfoModel;
use Think\Controller;
use MongoDate;

class RedisController extends Controller
{

    private $redisModel;
    private $redisInfoModel;

    public function __construct()
    {
        parent::__construct();
        $this->redisModel = new RedisModel();
        if (isset($_REQUEST['id'])) {
            $this->id = $_REQUEST['id'];
            $this->redisInfoModel = new RedisInfoModel($this->id);
        }
    }

    public function getInstances()
    {
        $array = $this->redisModel->getAll();
        $this->ajaxReturn($array);
    }

    public function getInstance()
    {
        $array = $this->redisModel->getByCondition(['ip:port' => $this->id]);
        $this->ajaxReturn($array);
    }

    public function getHistory()
    {
        $time = isset($_REQUEST['time']) ? $_REQUEST['time'] : new MongoDate(0);
        if (isset($_REQUEST['limit']))
            $array = $this->redisInfoModel->getByCondition(['date' => ['$gte' => $time]], $sort = ['date' => -1], $_REQUEST['limit']);
        else
            $array = $this->redisInfoModel->getByConditionAll(['date' => ['$gte' => $time]], $sort = ['date' => -1]);
        $this->ajaxReturn($array);
    }

    public function getHistoryBetween()
    {
        $start = $_REQUEST['start'];
        $end = $_REQUEST['end'];
        if (isset($_REQUEST['limit']))
            $array = $this->redisInfoModel->getByCondition(['date' => ['$gte' => $start, '$lt' => $end]], ['date' => -1], $_REQUEST['limit']);
        else
            $array = $this->redisInfoModel->getByConditionAll(['date' => ['$gte' => $start, '$lt' => $end]], ['date' => -1], $_REQUEST['limit']);
        $this->ajaxReturn($array);
    }

    public function getHistoryAll()
    {
        $array = $this->redisInfoModel->getByConditionAll([], ['date' => -1]);
        $this->ajaxReturn($array);
    }
}