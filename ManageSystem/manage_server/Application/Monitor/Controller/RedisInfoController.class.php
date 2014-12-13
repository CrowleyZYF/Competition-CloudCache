<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/10
 * Time: 19:53
 */

namespace Monitor\Controller;

use Think\Controller;
use Common\Model\RedisModel;
use Monitor\Model\RedisInfoModel;

class RedisInfoController extends Controller
{

    private $redisModel;
    private $redisInfoModel;

    public function __construct()
    {
        parent::__construct();
        $this->redisModel = new RedisModel();
        $this->redisInfoModel = new RedisInfoModel();
    }

    public function listAll()
    {
        $array = $this->redisModel->getAll();
        return $this->ajaxReturn($array);
    }

    /**
     * 还未完工！
     */
    public function listOne()
    {
        $condition = ['ip:host' => '192.168.1.206:6379'];
        $array = $this->redisModel->getOne($condition);
        return $this->ajaxReturn($array);
    }

    public function listOneHistory()
    {
        $condition = [];#$_GET['data'];
        $limit = $_GET['limit'];
        $this->redisInfoModel->init('192.168.1.206:6379');
        $array = $this->redisInfoModel->listByCondition($condition);
        return $this->ajaxReturn($array);
    }
}