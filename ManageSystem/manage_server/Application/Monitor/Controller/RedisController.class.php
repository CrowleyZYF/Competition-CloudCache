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
        $this->ajaxReturn($array);
    }

    public function listOne()
    {
        $condition = $_REQUEST['id'];
        $array = $this->redisModel->getOne($condition);
        $this->ajaxReturn($array);
    }

    public function listOneHistory()
    {
        $id = $_REQUEST['id'];
        $limit = $_REQUEST['limit'];
        $condition = $_REQUEST['condition'];
        $this->redisInfoModel->init($id);
        $array = $this->redisInfoModel->listByCondition($condition,$limit);
        $this->ajaxReturn($array);
    }
}