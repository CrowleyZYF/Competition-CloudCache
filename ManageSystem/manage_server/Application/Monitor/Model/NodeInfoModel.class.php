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

class NodeInfoModel extends Model
{
    private $mongo;
    private $collection;

    public function __construct($collection)
    {
        parent::__construct();
        $this->mongo = parent::getMongo();
        $this->collection = $this->mongo->selectCollection(Constant::$constant['db_node_info'], $collection);
    }

    public function getByCondition($condition = [], $sort = ['date' => 1], $limit = 10)
    {
        $cursor = $this->collection->find($condition)->sort($sort)->limit($limit);
        $array = [];
        foreach ($cursor as $document) {
            $array[] = $document;
        }
        return $array;
    }
}