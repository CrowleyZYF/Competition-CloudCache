<?php

/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/23
 * Time: 22:21
 */
namespace Common\Model;

use MongoClient;

class Model
{
    private static $mongo;

    function __construct()
    {
        //if (!isset(self::$mongo))
        self::$mongo = new MongoClient(Constant::$constant['db_host']);
        // exit;
    }

    public function getMongo()
    {
        return self::$mongo;
    }

    public function setMongo($ip, $port)
    {
        self::$mongo = new MongoClient($ip . ':' . $port);
    }
}