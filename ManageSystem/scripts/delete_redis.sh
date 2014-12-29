#!/bin/bash

if [ $# -ne 2 ]
then
    echo "FATAL: Wrong parameter number"
    exit 1
fi

host_ip=$1
port=$2
res=$(ssh $host_ip "cd redis ; if [ ! -f redis$port.conf ] ; then echo 'err' ; fi")
if [ x"$res" = x"err" ] ; then
	echo "FATAL: Redis on this port has never been created"
	exit 0
fi
#打开redis，清除所有数据
res=$(ssh $host_ip "cd redis ; src/redis-cli -p $port ping" 2>&1)
if [ x"$res" = x"PONG" ] ; then
	echo $(ssh $host_ip "cd redis ; echo 'flush data:' ; src/redis-cli -p $port flushall")
else
	ssh $host_ip "cd redis ; src/redis-server redis$port.conf"
	res=$(ssh $host_ip "cd redis ; src/redis-cli -p $port ping" 2>&1)
	if [ x"$res" != x"PONG" ] ; then
		echo "FATAL: Redis start failed,could not flush data"
		exit 1
	else
		echo $(ssh $host_ip "cd redis ; echo 'flush data:' ; src/redis-cli -p $port flushall")
	fi
fi
#关闭redis
ssh $host_ip "cd redis ; src/redis-cli -p $port shutdown" 2>&1
#删除配置文件
ssh $host_ip "cd redis ; rm redis$port.conf"
res=$(ssh $host_ip "cd redis ; if [ ! -f redis$port.conf ] ; then echo 'err' ; fi")
if [ x"$res" = x"err" ] ; then
	echo "OK"
	exit 0
else
	echo "FATAL: Could not delete Redis"
	exit 1
fi
