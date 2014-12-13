<?php

/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/13
 * Time: 18:14
 */
class NodeModel
{
    private $mongo;
    private $collection;

    public function __construct()
    {
        $this->mongo = new MongoClient();
        $this->collection = $this->mongo->selectCollection('emem_system', 'node');
    }

    public function setMongo($ip, $port)
    {
        $this->mongo = new MongoClient('mongodb://' . $ip . ':' . $port);
    }

    public function setCollection($db, $collection)
    {
        $this->mongo = $this->mongo->selectCollection($db, $collection);
    }

    public function listAll()
    {
        $cursor = $this->collection->find();
//      print_r($collection);$collection
        $array = [];
        foreach ($cursor as $document) {
            $array[] = $document;
        }
        return $array;
    }

    public function listOne($condition)
    {
        $cursor = $this->collection->findOne($condition);
        return $cursor;
    }
}