<?php

require('stat.php');

$stat = new Stat((new MongoClient())->stat);

print_r($stat->hitRate('a')); //命中率

print_r($stat->top1('a')); //最热门数据

print_r($stat->top10('a')); //最热门数据top10

