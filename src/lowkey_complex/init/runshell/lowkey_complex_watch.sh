#!/bin/bash
# 日志目录
lowkey_complex_home_log=/opt/lowkey_complex/logs/lowkey_complex_watch.log
touch $lowkey_complex_home_log

# lowkey_complex进程是否存在
is_exist() {
    pid=$(ps -ef | grep java | grep -i lowkey_complex | grep -v grep | awk '{print $2}')
    #如果不存在返回1，存在返回0
    if [ -z "${pid}" ]; then
        return 1
    else
        return 0
    fi
}

while [ 1 -eq 1 ]; do
    is_exist
    if [ $? -eq 1 ]; then
        /opt/lowkey_complex/init/runshell/lowkey_complex.sh start
        sleep 10
        
        restartpid=$(ps -ef | grep java | grep -i lowkey_complex | grep -v grep | awk '{print $2}')
        echo "--------------------------- lowkey_complex IsExit Started By Watch Dog For Pid [$restartpid]  $(date "+%Y-%m-%d %H:%M:%S")
        ----------------------------------------" >> ${lowkey_complex_home_log}  2>&1
    fi

    sleep 60
done
