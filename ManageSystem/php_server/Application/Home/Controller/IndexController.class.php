<?php
/**
 * Created by PhpStorm.
 * User: deng
 * Date: 2014/12/14
 * Time: 14:12
 */

namespace Home\Controller;

use Common\Model\NodeModel;
use Think\Controller;

class IndexController extends Controller
{
    public function index()
    {
        echo 'eMem Manage System Back-side API';
    }

    public function init()
    {
        $node1 = [
            'ip' => '192.168.1.73',
            'available_port' => [
                6379, 6380, 6381, 6382, 6383
            ]
        ];
        $node2 = [
            'ip' => '192.168.1.206',
            'available_port' => [
                6379, 6380, 6381, 6382, 6383
            ]
        ];
        $node3 = [
            'ip' => '192.168.1.113',
            'available_port' => [
                6379, 6380, 6381, 6382, 6383
            ]
        ];
        $node4 = [
            'ip' => '192.168.1.166',
            'available_port' => [
                6379, 6380, 6381, 6382, 6383
            ]
        ];
        $nodes = [
            0 => $node1,
            1 => $node2,
            2 => $node3,
            3 => $node4
        ];

        $nodeModel = new NodeModel();
        $collection = $nodeModel->getCollection();
        foreach ($nodes as $node) {
            $collection->update(['ip' => $node['ip']], ['$set' => $node], ['upsert' => true]);
        }
    }
}