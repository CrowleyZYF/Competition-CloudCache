<?php

/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/15
 * Time: 9:48
 */

namespace Common\Model;

use \MongoClient;

class UserModel extends Model
{
    private $mongo;
    private $collection;

    public function __construct()
    {
        parent::__construct();
        $this->mongo = parent::getMongo();
        $this->collection = $this->mongo->selectCollection(Constant::$constant['db_user'], Constant::$constant['collection_user']);
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