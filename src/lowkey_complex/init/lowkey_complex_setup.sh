#!/bin/bash
##############################
## 名称: lowkey_complex_setup.sh
## 描述: lowkey_complex模块
## 作者: lowkey
## 日期: 2020-07-01
## 版本：V1.1
## 备注: root用户执行
##############################

# 配置当前路径
#当前路径 current_path = 选择的安装路径
current_path=$(cd `dirname $0`; pwd)

# 安装目录
install_path=/opt/lowkey
mkdir -p $install_path

scriptInit(){
  # 安装日志文件
  mkdir -p $current_path/../logs
  logfile=$current_path/../logs/installLowkeyComplex.log
  touch $logfile

	echo "当前路径:${current_path} , 安装日志$logfile , 安装目录$install_path" >> ${logfile}  2>&1

	echo "---------------------------INSTALL LOWKEY_COMPLEX START `date "+%Y-%m-%d %H:%M:%S"`-----------------------------------------" >> ${logfile}  2>&1
}

# 安装JDK
installJdk(){
  cd $
}

installComplexService(){
  # 将安装包复制到安装目录
  \cp -rf $current_path/../../lowkey_complex $install_path

  lowkey_complex_sh=$install_path/lowkey_complex/init

  # 安装JDK
  cd $install_path/lowkey_complex/lowkey_jdk
  mkdir -p $install_path/lowkey_jdk
  tar -zxvf jdk-8u251-linux-x64.tar.gz -C $install_path/lowkey_jdk/ --strip-components=1

	# lowkey_complex注册服务
	echo y | \cp -rf $lowkey_complex_sh/runshell/lowkey_complex.service /etc/systemd/system
	echo "lowkey_complex.service copy to /etc/systemd/system 目录下成功 " >> ${logfile}  2>&1

	chmod -Rf 755 $install_path/lowkey_complex
	chmod -Rf 755 /etc/systemd/system/lowkey_complex.service

	systemctl enable lowkey_complex.service
	systemctl daemon-reload

}

# Step0 initScript
scriptInit


# Step2 installComplexService
installComplexService

echo "---------------------------INSTALL ComplexService OVER `date "+%Y-%m-%d %H:%M:%S"`-----------------------------------------" >> ${logfile}  2>&1
