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
        $this->mongo = new MongoClient(Constant::$constant['db_host']);
        $this->collection = $this->mongo->selectCollection('emem_system', 'redis');
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

    public function getByCondition($condition = [], $sort = [], $limit = 10)
    {
        $cursor = $this->collection->find($condition)->sort($sort)->limit($limit);
        $array = [];
        foreach ($cursor as $document) {
            $array[] = $document;
        }
        return $array;
    }

    public function insert($data)
    {
        $this->collection->insert($data);
    }

    public function stop($data)
    {
        $this->collection->update($data, ['$set' => ['status' => 0]]);
    }

    public function start($data)
    {
        $this->collection->update($data, ['$set' => ['status' => 1]]);
    }

    public function delete($condition)
    {
        $this->collection->remove($condition);
    }
}