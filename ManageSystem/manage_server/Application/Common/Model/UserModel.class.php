<?php

/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/15
 * Time: 9:48
 */

namespace Common\Model;

use \MongoClient;

class UserModel
{
    private $mongo;
    private $collection;

    public function __construct()
    {
        $this->mongo = new MongoClient(Constant::$constant['db_host']);
        $this->collection = $this->mongo->selectCollection('emem_system', 'user');
    }

    public function insert($data)
    {
        $this->collection->insert($data);
    }

    public function delete($condition)
    {
        $cursor = $this->collection->findOne($condition);
        $this->collection->remove($condition);
        return $cursor['token'];
    }
}