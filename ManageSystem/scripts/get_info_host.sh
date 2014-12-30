#!/bin/bash

exec 8<>/dev/tcp/192.168.1.158/8190;
echo "#top" >&8
TERM=linux ; export TERM ; top -bn 1 | head -n 5 >&8
echo "#io"  >&8
vmstat >&8
bytes=`/sbin/ifconfig eth0|grep "RX bytes"|awk -F ":" '{print $2,$3}'|awk -F " " '{print $1,$6}'`
RX_bytes=`echo $bytes |cut -d ' ' -f 1`
TX_bytes=`echo $bytes |cut -d ' ' -f 2`
if [ ! -f ~/netlog ] ; then
	touch ~/netlog
	last_bytes="0 0"
else
	last_bytes=`cat netlog`
fi
last_RX_bytes=`echo $last_bytes |cut -d ' ' -f 1`
last_TX_bytes=`echo $last_bytes |cut -d ' ' -f 2`
echo "#net" >&8
echo $[$RX_bytes-$last_RX_bytes] >&8
echo $[$TX_bytes-$last_TX_bytes] >&8
echo $RX_bytes " " $TX_bytes >~/netlog

exec 8<&-;
exec 8>&-;
