#!/bin/bash

if [ $# -ne 1 ]
then
    echo "FATAL: Wrong parameter number"
    exit 1
fi

host_ip=$1
res=$(ssh -o ConnectTimeout=3 $host_ip "echo 'OK' ; exit")
if [ x"$res" != x"OK" ] ; then
	echo "no"
	exit 0
else
	echo "yes"
	exit 0
fi
