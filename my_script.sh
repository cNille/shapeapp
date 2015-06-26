#!/bin/bash

if [ $# -eq 0 ] ; then
    server_version="1.2.11"
else
    server_version=$1
fi

echo Server-version: $server_version
log_file="./$server_version/logs/application.log"

last_event=$(grep -n 'INFO' $log_file | head -n 1 | cut -c -21)
echo Server was setup - $last_event

last_event=$(grep 'INFO' $log_file | tail -1 | cut -c -19)
echo Last event happened - $last_event

orders=$(grep -c 'Order incoming' $log_file)
echo Number of orders: $orders

exceptions=$(grep -c 'Exception' $log_file)
echo Number of exceptions: $exceptions

ios=$(grep -c 'IOS' $log_file)
echo Number of IOS orders: $ios

android=$(grep -c 'android' $log_file)
echo Number of android orders: $android

unknown=$(grep -c 'unknown' $log_file)
echo Number of unknown orders: $unknown
