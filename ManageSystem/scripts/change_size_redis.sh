#!/bin/bash

if [ $# -ne 3 ]
then
    echo "FATAL: Wrong parameter number"
    exit 1
fi

host_ip=$1
port=$2
size=$3
res=$(ssh $host_ip "cd redis ; if [ ! -f redis$port.conf ] ; then echo 'err' ; fi")
if [ x"$res" = x"err" ] ; then
	echo "FATAL: Redis on this port have not been created"
	exit 1
fi
res=$(ssh $host_ip "cd redis ; src/redis-cli -p $port ping" 2>&1)
if [ x"$res" = x"PONG" ] ; then
	ssh $host_ip "cd redis ; src/redis-cli -p $port shutdown"	
fi
echo $(ssh $host_ip "cd redis ; grep '^maxmemory .*' redis$port.conf ; echo '-->' ; sed -i 's/^maxmemory .*/maxmemory $size/g' redis$port.conf ; grep '^maxmemory .*' redis$port.conf")
if [ x"$res" = x"PONG" ] ; then
	ssh $host_ip "cd redis ; src/redis-server redis$port.conf"
	res=$(ssh $host_ip "cd redis ; src/redis-cli -p $port ping" 2>&1)
	if [ x"$res" != x"PONG" ] ; then
		echo "FATAL: Redis restart failed"
		exit 1
	else
		echo "OK"
		exit 0
	fi
else
	echo "OK"
	exit 0
fi
