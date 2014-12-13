<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/10
 * Time: 23:02
 */
namespace Common\Model;

use \MongoClient;

class RedisModel
{
    private $mongo;
    private $collection;

    public function __construct()
    {
        $this->mongo = new MongoClient();
        $this->collection = $this->mongo->selectCollection('emem_system', 'redis');
    }

    public function __destruct()
    {
        $this->mongo->close(false);
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
//        print_r($collection);$collection
        $array = [];
        foreach ($cursor as $document) {
            $array[] = $document;
        }
        return $array;
    }

    public function getOne($condition)
    {
        $cursor = $this->collection->findOne($condition);
        return $cursor;
    }

    public function insert($data)
    {
        $this->collection->insert($data);
    }

    public function stop($data)
    {
        $this->collection->update($data, ['state' => 0]);
    }

    public function start($data)
    {
        $this->collection->update($data, ['state' => 1]);
    }

    public function delete($condition)
    {
        $this->collection->remove($condition);
    }
}