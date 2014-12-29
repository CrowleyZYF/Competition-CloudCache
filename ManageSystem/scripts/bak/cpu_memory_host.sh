#!/bin/bash

if [ $# -ne 1 ]
then
    echo "FATAL: Wrong parameter number"
    exit 1
fi

host_ip=$1
ssh $host_ip "TERM=linux ; export TERM ; top -bn 1 | head -n 5"
