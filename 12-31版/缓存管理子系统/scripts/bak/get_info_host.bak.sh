#!/bin/bash

if [ $# -ne 1 ]
then
    echo "FATAL: Wrong parameter number"
    exit 1
fi

host_ip=$1
#exec 8<>/dev/tcp/192.168.1.158/8189;

ssh $host_ip "TERM=linux ; export TERM ; top -bn 1 | head -n 5" #>&8
ssh $host_ip "vmstat" #>&8
ssh $host_ip "ifconfig eth0 | awk -F ":" 'NR==6{print $2,$3}' | awk -F " " '{print $1,$6}' "

#exec 8<&-;
#exec 8>&-;
