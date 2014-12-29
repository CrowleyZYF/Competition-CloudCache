#!/bin/bash

cd ~/redis ;
a=`ls |grep "redis..*.conf"|sed 's/redis//g'|sed 's/.conf//g'`
for port in $a
do
	exec 8<>/dev/tcp/192.168.1.158/8189;
	res=`src/redis-cli -p $port ping 2>&1`
	if [ x"$res" = x"PONG" ] ; then
		src/redis-cli -p $port info  >&8
		maxinfo=`src/redis-cli -p $port config get maxmemory`
		echo $maxinfo |sed 's\ \:\g'  >&8
	else
		src/redis-server redis$port.conf
		res=`src/redis-cli -p $port ping 2>&1`
		if [ x"$res" != x"PONG" ] ; then
			echo "FATAL: Redis $port  start failed"
			exit 1
		else
			src/redis-cli -p $port info  >&8
			maxinfo=`src/redis-cli -p $port config get maxmemory`
			echo $maxinfo |sed 's\ \:\g'  >&8
			src/redis-cli -p $port shutdown 2>&1
		fi
	fi
	exec 8<&-;
	exec 8>&-;	
done

