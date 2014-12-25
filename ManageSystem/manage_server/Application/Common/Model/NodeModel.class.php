<?php

/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/13
 * Time: 18:14
 */

namespace Common\Model;

use Common\Model\Constant;
use MongoClient;

class NodeModel extends Model
{
    private $mongo;
    private $collection;

    function __construct()
    {
        parent::__construct();
        $this->mongo = new MongoClient(Constant::$constant['db_host']);
        $this->collection = $this->mongo->selectCollection(Constant::$constant['db_node'], Constant::$constant['collection_node']);
    }

    public function getCollection()
    {
        return $this->collection;
    }

    public function getMongo()
    {
        return $this->mongo;
    }

    public function setMongo($ip, $port)
    {
        $this->mongo = new MongoClient('mongodb://' . $ip . ':' . $port);
    }

    public function setCollection($db, $collection)
    {
        $this->mongo = $this->mongo->selectCollection($db, $collection);
    }

    public function getAll()
    {
        $cursor = $this->collection->find();
//      print_r($collection);$collection
        $array = [];
        foreach ($cursor as $document) {
            $array[] = $document;
        }
        return $array;
    }

    public function getByCondition($condition)
    {
        $cursor = $this->collection->find($condition);
        $array = [];
        foreach ($cursor as $document) {
            $array[] = $document;
        }
        return $array;
    }

    public function getOneByCondition($condition)
    {
        return $this->collection->findOne($condition);
    }

    public function update($query, $data)
    {
        $this->collection->update($query, ['$set' => $data]);
    }
}