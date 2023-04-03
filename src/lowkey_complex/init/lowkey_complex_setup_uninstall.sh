#!/bin/bash
##############################
## 名称: uninstall.sh
## 描述: 卸载服务
## 参数: 暂无
## 作者: zfsoft
## 日期: 2019-07-23
## 版本：V1.0
## 备注: root用户执行
##############################



removeReport(){
	echo "---------------------------UNINSTALL REPORT START `date "+%Y-%m-%d %H:%M:%S"`-----------------------------------------" >> ${logfile}  2>&1 
	#判断进程是否存在，如果不存在就启动它
	PIDS=`ps -ef |grep ChkProof.Report.jar |grep -v grep | awk '{print $2}'`
	if [ "$PIDS" != "" ]; then
		kill -9 $PIDS
		echo "终止Report进程:$PIDS" >> ${logfile}  2>&1 
	else
		echo "Report进程不存在,无需停止" >> ${logfile}  2>&1 
	fi
	
	if [ -d ${reportPath} ]; then 
		rm  ${reportPath} -rf
		echo "${reportPath} 移除结束" >> ${logfile}  2>&1 
	fi
	chkconfig --del zfnetchkreport
	echo "zfnetchkreport 服务删除成功" >> ${logfile}  2>&1 
	rm -rf /etc/init.d/zfnetchkreport
	echo "zfnetchkreport 开机启动文件删除成功" >> ${logfile}  2>&1 

	echo "---------------------------UNINSTALL REPORT OVER `date "+%Y-%m-%d %H:%M:%S"`-----------------------------------------" >> ${logfile}  2>&1 
}

#当前路径 current_path = 选择的安装路径/plugins/report/runshell
current_path=$(cd `dirname $0`; pwd)
echo "当前路径:${current_path}"

cd ${current_path}

#日志文件
logfile=/home/uninstallReport.log
echo "start uninstall "
#report安装目录
reportPath=${current_path}/../../../plugins 

removeReport

exit 0