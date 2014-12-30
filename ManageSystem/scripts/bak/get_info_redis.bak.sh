#!/bin/bash

if [ $# -ne 2 ]
then
    echo "FATAL: Wrong parameter number"
    exit 1
fi

host_ip=$1
port=$2
res=$(ssh $host_ip "cd redis-2.8.17 ; if [ ! -f redis$port.conf ] ; then echo 'err' ; fi")
if [ x"$res" = x"err" ] ; then
	echo "FATAL: Redis on this port has not been created"
	exit 1
fi
exec 8<>/dev/tcp/192.168.1.158/8189;
res=$(ssh $host_ip "cd redis-2.8.17 ; src/redis-cli -p $port ping" 2>&1)
if [ x"$res" = x"PONG" ] ; then
	ssh $host_ip "cd redis-2.8.17 ; src/redis-cli -p $port info" >&8
else
	ssh $host_ip "cd redis-2.8.17 ; src/redis-server redis$port.conf"
	res=$(ssh $host_ip "cd redis-2.8.17 ; src/redis-cli -p $port ping" 2>&1)
	if [ x"$res" != x"PONG" ] ; then
		echo "FATAL: Redis start failed"
		exit 1
	else
		ssh $host_ip "cd redis-2.8.17 ; src/redis-cli -p $port info" >&8
		ssh $host_ip "cd redis-2.8.17 ; src/redis-cli -p $port shutdown" 2>&1
	fi
fi
exec 8<&-;
exec 8>&-;
