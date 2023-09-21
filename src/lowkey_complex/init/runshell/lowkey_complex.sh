#!/bin/bash
##############################
## 名称: lowkey_complex.sh
## 描述: lowkey_complex启停
## 参数: 暂无
## 作者: lowkey
## 日期: 2022-09-14
## 版本：V1.0
## 备注: root用户执行
##############################

#-------------------------public param (must)-------------------------
RETVAL=0

# 安装目录
lowkey_complex_home=/opt/lowkey/lowkey_complex

# 日志目录
logfile=$lowkey_complex_home/logs/lowkey_complex_run.log
touch $logfile

# 脚本目录
lowkey_runshell=$lowkey_complex_home/init/runshell

# JVM设置
source $lowkey_runshell/lowkeyjvm.option
jvm_option=$(cat $lowkey_runshell/lowkeyjvm.option)

# JDK路径
JAVA=/opt/lowkey/lowkey_jdk

# APP路径
APP=$lowkey_complex_home/server/lowkey_complex.jar

echo "lowkey_complex_home_path:$lowkey_complex_home" >> ${logfile} 2>&1
#-------------------------public param (must)-------------------------

#-------------------------variable area-------------------------
# JVM环境变量
source $lowkey_runshell/lowkeyjvm.option
if [ -f $JAVA/bin/java ];then
  export JAVA_HOME=$JAVA
  export PATH=$JAVA_HOME/bin:$PATH
fi

#-------------------------variable area-------------------------

#-------------------------function area-------------------------

#使用说明，用来提示输入参数

usage() {
    echo "Usage: sh startup.sh [start|stop|restart]"
    exit 1
}

#检查程序是否在运行

is_exist() {
    pid=$(ps -ef | grep java | grep -i lowkey_complex | grep -v grep | awk '{print $2}')
    #如果不存在返回1，存在返回0
    if [ -z "${pid}" ]; then
        return 1
    else
        return 0
    fi
}

#检查监控程序是否在运行

is_watch_exist() {
    pidWatch=$(ps -ef | grep -i lowkey_complex_watch | grep -v grep | awk '{print $2}')
    #如果不存在返回1，存在返回0
    if [ -z "${pidWatch}" ]; then
        return 1
    else
        return 0
    fi
}

#启动方法,启动watchDog

startApp=nohup $JAVA/bin/java  ${jvm_option} -Dcustom.srvname=lowkey_complex -jar ${APP} --logging.config=$lowkey_complex_home/server/config/log/logback-spring.xml >/dev/null 2>&1 &

start() {
    is_exist
    if [ $? -eq 0 ]; then
        return $RETVAL
    else
        is_watch_exist
        if [ $? -eq 1 ]; then
            nohup $lowkey_complex_home/init/runshell/lowkey_complex_watch.sh >/dev/null 2>&1 &
        else
            $startApp
        fi
    fi
}

#停止方法

stop() {
    is_watch_exist
    if [ $? -eq 0 ]; then
        kill -9 $pidWatch
    fi
    is_exist
    if [ $? -eq 0 ]; then
        kill -9 $pid
    else
        return $RETVAL
    fi
}

#重启

restart() {
    stop
    sleep 1
    start
}

#-------------------------function area-------------------------

#根据输入参数，选择执行对应方法，不输入则执行使用说明

case "$1" in
"start")
    start
    ;;
"stop")
    stop
    ;;
"restart")
    restart
    ;;
*)
    usage
    ;;
esac
