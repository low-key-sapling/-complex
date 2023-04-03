#!/bin/bash
##############################
## 名称: 前置检测
## 描述: 产品安装检测、释放JDK、备份产品
## 参数: 暂无
## 作者: lowkey
## 日期: 2022-03-17
## 版本：V1.0
## 备注：
##############################

#-------------------------public param (must)-------------------------

#解压目录
unZipPath=$1
#备份目录
backupPath=$2
#日志目录
logLocation=$3
#是否是tomcat 1-tomcat 0-国产中间件
tomcatFlag=$4

#-------------------------public param (must)-------------------------

#-------------------------variable area-------------------------

#定义退出标识符, 脚本执行后，通过echo $? 查看退出标识符，即上个命令或者脚本的返回结果
exit_failure=1 #Failing exit status
exit_success=0 #Successful exit status
#当前时间
begin_time=$(date +%s)
sys_info=$(uname -a)

#lowkey jdk 释放目录
jdkPath=/appstore/lowkey

# 国产中间件下产品统一安装目录
productMidPath=/appstore/lowkey/deployment
# Tomcat下产品统一安装目录
productTomcatPath=/appstore/lowkey/tomcat/webapps

#Tomcat下complex路径(取complex的路径)
complexTomcatPath=$productTomcatPath/complex

#国产中间件下complex路径(取complex的路径)
complexNonTomcatPath=$productMidPath/complex

#产品Web首次备份目录 用于还原
originWebBackPath=$backupPath/originWebBackPath

#-------------------------variable area-------------------------

#-------------------------function area-------------------------

preInstall() {
   if [ ! -d $logLocation ]; then
      mkdir -p $logLocation
   fi
   if [ ! -d $backupPath ]; then
      mkdir -p $backupPath
   fi
   logfile=$logLocation/deploy_lowkey_1precheck_$(date "+%Y-%m-%d-%H-%M").log

   echo "==========================begin  $(date "+%Y-%m-%d %H:%M:%S")=================================================" >>$logfile 2>&1

   echo -e "\033[47;30m+----------------------------------------------------------------------+\033[0m" >>$logfile 2>&1
   echo -e "\033[47;30m|                     前置检测BEGIN                                      |\033[0m" >>$logfile 2>&1
   echo -e "\033[47;30m+----------------------------------------------------------------------+\033[0m" >>$logfile 2>&1
}

#释放JDK
releaseJdk() {
  #监测jdk是否存在
  if [ ! -f $jdkPath/lowkey_jdk/bin/java ]; then
      echo "该环境安装下[lowkey_jdk]不存在,执行释放jdk操作 $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
      if [ ! -d /appstore/lowkey ]; then
         mkdir -p /appstore/lowkey
      fi

      if [[ $sys_info =~ "x86" ]]; then
         \cp -rf $unZipPath/depends/x86/lowkey_jdk $jdkPath
         echo "当前cpu架构为x86 : $sys_info" >>$logfile 2>&1
         echo "释放 JDK 到路径：$jdkPath" >>$logfile 2>&1
      fi

      if [[ $sys_info =~ "aarch" ]]; then
         \cp -rf $unZipPath/depends/arm/lowkey_jdk $jdkPath
         echo "当前cpu架构为arm: $sys_info" >>$logfile 2>&1
         echo "释放 JDK 到路径：$jdkPath" >>$logfile 2>&1
      fi

      if [[ $sys_info =~ "mips" ]]; then
         \cp -rf $unZipPath/depends/mips/lowkey_jdk $jdkPath
         echo "当前cpu架构为mips : $sys_info" >>$logfile 2>&1
         echo "释放 JDK 到路径：$jdkPath" >>$logfile 2>&1
      fi

      if [ ! -d /opt/lowkey ]; then
         mkdir -p /opt/lowkey
      fi

   else
     echo "该环境安装下[lowkey_jdk]存在,不执行操作 $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
  fi
  \cp -f $unZipPath/depends/zflowkeyjdk.option  $jdkPath
  \cp -f $unZipPath/depends/zflowkeyjvm.option  $jdkPath
  chmod -R 755 $jdkPath/lowkey_jdk
}

save2Log(){
   # command的返回结果
   result=$1
   # 事件信息
   message=$2
   # cp 源路径
   source=$3
   # cp 目标路径
   target=$4
   if [ $result = 0 ]; then
      echo "SUCCESS: 【$message】, mv $source 到 $target 成功 $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
   else
      echo "WARN: 【$message】, mv  $source 到 $target 失败, 请注意检查！！！ $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
   fi
}

# 首次备份的目录(避免重复备份,导致原应用丢失)
makeupOriginBackup() {
   sourcePath=$1
   backupTargetPath=$2
   
   if [ -d $backupTargetPath ]; then
      echo "脚本非首次执行!!!  产品安装目录: $sourcePath  上次部署已经备份在$backupTargetPath 目录下 , 本次将对上次备份目录$backupTargetPath 进行重命名操作，然后重新备份本次升级前的应用 $(date "+%Y-%m-%d %H:%M:%S")" >>$logfile 2>&1
	  if [ ! -d $backupPath/lastOpearteBackUp_$(date "+%Y-%m-%d-%H-%M") ]; then
	     mkdir -p $backupPath/lastOpearteBackUp_$(date "+%Y-%m-%d-%H-%M")
	  fi
      mv -f $backupTargetPath  $backupPath/lastOpearteBackUp_$(date "+%Y-%m-%d-%H-%M")
      save2Log $?  '上次部署备份(mv操作)重命名'  $backupTargetPath/*  backupPath/lastOpearteBackUp_$(date "+%Y-%m-%d-%H-%M")
   fi

   mkdir -p $backupTargetPath
   # 将$sourcePath下的所有文件(包括目录,这里主要是应用目录),转移到备份目录
   if [ "`ls -A $sourcePath`" != "" ]; then
      mv -f $sourcePath/* $backupTargetPath
      save2Log $?  '初次:部署备份(mv操作)'  $sourcePath/* $backupTargetPath
   fi
}

#停止服务
stopService() {
   serviceName=$1
   echo "停止 $serviceName 服务" >>$logfile 2>&1
   systemctl stop $serviceName 2>/dev/null
}

#记录产品信息
recordProduct() {

   if [ $tomcatFlag != 1 ]; then
      ####################################国产中间件################################################

      if [ -d $complexNonTomcatPath ]; then
         echo "该环境安装的中间件类型为[国产中间件], 已部署[COMPLEX]模块, 将执行升级操作" >>$logfile 2>&1
      else
         echo "该环境安装的中间件类型为[国产中间件], 未部署[COMPLEX]模块, 将执行新装操作" >>$logfile 2>&1
      fi

   else
      ####################################Tomcat################################################

      if [ -d $complexTomcatPath ]; then
         echo "该环境安装的中间件类型为[Tomcat], 已部署[COMPLEX]模块, 将执行升级操作" >>$logfile 2>&1
         stopService complexTomcat.service
      else
         echo "该环境安装的中间件类型为[Tomcat], 未部署[COMPLEX]模块, 将执行新装操作" >>$logfile 2>&1
      fi
   fi

}

# 产品备份
backupProduct() {

   ####################################国产中间件################################################
   if [ $tomcatFlag != 1 ]; then
      #首次部署需要创建 /appstore/deployment
      if [ ! -d $productMidPath ]; then
         mkdir -p $productMidPath
      fi
      #web应用的备份 （目前可仅根据是否安装了产品特定路径下的complex来判断）
      if [ -d $complexNonTomcatPath ]; then
        echo "国产中间件 $complexNonTomcatPath 存在,将执行升级操作 $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
        #COMPLEX
        makeupOriginBackup $complexNonTomcatPath $originWebBackPath/deployment/complex
        mkdir -p $complexNonTomcatPath
      else
         echo "国产中间件 $complexNonTomcatPath 不存在,首次安装 " >>$logfile 2>&1
      fi
   else
      ####################################Tomcat################################################
      if [ -d $complexTomcatPath ]; then
         echo "Tomcat - $complexTomcatPath 存在,将执行升级操作 $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
         stopService complexTomcat.service
         sleep 3
         #COMPLEX
         makeupOriginBackup $complexTomcatPath $originWebBackPath/webapps/api
         mkdir -p $complexTomcatPath
      else
         echo "Tomcat - $complexTomcatPath 不存在,首次安装 " >>$logfile 2>&1
      fi
   fi

}

install() {

   # 记录产品信息
   recordProduct

   # 释放JDK
   releaseJdk

   # 产品备份
   backupProduct

}

postInstall() {
   echo 'postInstall do nothing ' >>$logfile 2>&1
}

#记录
recordLog() {
   #结束时间
   END_TIME=$(date +%s)
   echo "==========================end    $(date "+%Y-%m-%d %H:%M:%S")=================================================" >>$logfile 2>&1

   #计算脚本执行时间
   echo "==========================time   consuming $(($END_TIME - $begin_time)) seconds=================================================" >>$logfile 2>&1

   echo -e "\033[47;30m+----------------------------------------------------------------------+\033[0m" >>$logfile 2>&1
   echo -e "\033[47;30m|                    前置检测END                                        |\033[0m" >>$logfile 2>&1
   echo -e "\033[47;30m+----------------------------------------------------------------------+\033[0m" >>$logfile 2>&1

   #输出一行空行到日志中，方便区分每次执行的日志
   echo "" >>$logfile 2>&1
}
#-------------------------function area-------------------------

#-------------------------main area-------------------------

# Step  1
preInstall

# Step  2
install

# Step  3
postInstall

# Step  4
recordLog

exit $exit_success

#-------------------------main area-------------------------
