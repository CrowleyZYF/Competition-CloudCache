<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/13
 * Time: 22:48
 */

namespace Monitor\Model;

use Common\Model\Constant;
use Common\Model\Model;

class RedisInfoModel extends Model
{
    private $mongo;
    private $db;
    private $collection;

    public function __construct($collection)
    {
        parent::__construct();
        $this->mongo = parent::getMongo();
        $this->db = $this->mongo->selectDb(Constant::$constant['db_redis_info']);
        $this->collection = $this->mongo->selectCollection(Constant::$constant['db_redis_info'], $collection);
    }

    public function getByCondition($condition, $sort = ['date' => 1], $limit = 10)
    {
        $cursor = $this->collection->find($condition)->sort($sort)->limit($limit);
        $result = [];
        foreach ($cursor as $document) {
            $result[] = $document;
        }
        return $result;
    }

    public function getByConditionAll($condition = [], $sort = ['date' => 1])
    {
        $cursor = $this->collection->find($condition)->sort($sort);
        $result = [];
        foreach ($cursor as $document) {
            $result[] = $document;
        }
        return $result;
    }

    public function deleteCollection($name)
    {
        $this->db->dropCollection($name);
    }
}