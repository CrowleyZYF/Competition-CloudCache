<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/13
 * Time: 18:20
 */

namespace Manage\Controller;

use Think\Controller;
use Common\Model\RedisModel;

class RedisInstanceController extends Controller
{
    private $model;

    public function __construct()
    {
        parent::__construct();
        $this->model = new RedisModel();
    }

    public function listAll()
    {
        $array = $this->model->getAll();
        return $this->ajaxReturn($array);
    }

    public function listOne()
    {
        $condition = $_GET['condition'];
        $array = $this->model->getOne($condition);
        return $this->ajaxReturn($array);
    }
}