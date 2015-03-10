<?php

require('stat.php');

$stat = new Stat((new MongoClient())->stat);

print_r($stat->hitRate('a')); //命中率

print_r($stat->top1('a')); //最热门数据

$now = time() * 1000;

print_r($stat->top10('a', Stat::today())); //当天的最热门数据top10

print_r($stat->top10('a', $now - Stat::hours(1))); //一个小时内的最热门数据top10

