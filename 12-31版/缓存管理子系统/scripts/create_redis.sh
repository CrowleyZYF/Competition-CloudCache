#!/bin/bash

if [ $# -ne 3 ]
then
    echo "FATAL: Wrong parameter number"
    exit 1
fi

host_ip=$1
size=$3
port=$2
cmd="if [ ! -d redis ] ; then wget http://download.redis.io/releases/redis-2.8.17.tar.gz; tar xzf redis-2.8.17.tar.gz ; mv redis-2.8.17 redis ; cd redis ; make ; fi"
ssh $host_ip $cmd
res=$(ssh $host_ip "cd redis ; src/redis-cli -p $port ping" 2>&1)
if [ x"$res" = x"PONG" ] ; then
	echo "FATAL: Redis on this port has existed"
	exit 1
fi
ssh $host_ip "cd redis ; cp redis.conf redis$port.conf ; sed -i 's/^# maxmemory .*/maxmemory $size/g' redis$port.conf ; sed -i 's/^daemonize.*/daemonize yes/g' redis$port.conf ; sed -i 's/^port.*/port $port/g' redis$port.conf"
ssh $host_ip "cd redis ; src/redis-server redis$port.conf"
res=$(ssh $host_ip "cd redis ; src/redis-cli -p $port ping" 2>&1)
if [ x"$res" != x"PONG" ] ; then
	echo "FATAL: Redis start failed"
	exit 1
else
	echo "OK"
	exit 0
fi
