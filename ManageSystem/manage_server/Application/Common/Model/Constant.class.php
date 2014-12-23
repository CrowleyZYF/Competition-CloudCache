<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/14
 * Time: 19:59
 */
namespace Common\Model;

class Constant
{
    public static $constant = [
        'db_host' => '192.168.1.73',
        'db_port' => '27017',
        'db_node' => 'emem_system',
        'db_redis' => 'emem_system',
        'db_user' => 'emem_system',
        'db_node_info' => 'node_info',
        'db_redis_info' => 'redis_info',
        'collection_node' => 'node',
        'collection_redis' => 'redis',
        'collection_user' => 'user',
        'cache_system' => 'http://192.168.1.73:8080',
        'redis_map' => [
            'ip:port' => 'id',
            'run_id' => 'identify',
            'name' => 'name',
            'status' => 'status',
            'type' => 'type',
            'area' => 'area',
            'create_time' => 'time',
            'used_memory' => 'used',
            'maxmemory' => 'all'
        ],
    ];
}