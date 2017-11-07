#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

SERVER_NAME=`sed '/^app.process.name/!d;s/.*=//' conf/dubbo.properties | tr -d '\r'`

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

PIDS=`ps -ef -ww | grep "java" | grep " -DappName=$SERVER_NAME " | awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME does not started!"
    exit 1
fi

if [ "$1" != "skip" ]; then
    $BIN_DIR/dump.sh
fi

echo -e "Stopping the $SERVER_NAME ...\c"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done

sleep 3
APP_PID=`ps -ef -ww | grep "java" | grep " -DappName=$SERVER_NAME " | awk '{print $2}'`

if [ -z "$APP_PID" ]; then
    echo "Stop APP SUCCESS!"
fi

echo "STOPED PID: $PIDS"
echo "OVER..."
