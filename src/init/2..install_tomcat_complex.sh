#!/bin/bash
##############################
## 名称: install_server_java_complex.sh
## 描述: 安装服务端COMPLEX Related
## 参数: 暂无
## 作者: lowkey
## 日期: 2022-01-15
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

#是否是tomcat
tomcatFlag=$4

#redis根目录
redis_path=/appstore/lowkey

#国产中间件下产品统一安装目录
productMidPath=/appstore/deployment

#Tomcat下产品统一安装目录
productTomcatPath=/appstore/lowkey/tomcat/webapps

#complex(取complex的路径-Tomcat)
lowkeyTomcatComplexPath=$productTomcatPath/complex

#complex(取complex的路径-国产中间件)
lowkeyNonTomcatComplexPath=$productMidPath/complex

#产品首次备份目录 用于还原
originWebBackPath=$backupPath/originWebBackPath
originNoneTomcatWebBackPath=$backupPath/originWebBackPath/deployment

#原redis目录
originRedisPath=$redis_path/redis

#在压缩包中的目录位置
WebSource=$unZipPath/web/appstore

#complex在压缩包中的目录位置
ApiSource=$unZipPath/web/appstore/lowkeytomcat/webapps/complex

#-------------------------public param (must)-------------------------

#-------------------------variable area-------------------------

#定义退出标识符, 脚本执行后，通过echo $? 查看退出标识符，即上个命令或者脚本的返回结果
exit_failure=1 #Failing exit status
exit_success=0 #Successful exit status
#当前时间
begin_time=$(date +%s)

#-------------------------variable area-------------------------

#-------------------------function area-------------------------

preInstall() {

   if [ ! -d $logLocation ]; then
      mkdir -p $logLocation
   fi
   logfile=$logLocation/deploy_lowkey_2_$(date "+%Y-%m-%d-%H-%M").log

   echo "==========================begin  $(date "+%Y-%m-%d %H:%M:%S")=================================================" >>$logfile 2>&1

   echo -e "\033[47;30m+----------------------------------------------------------------------+\033[0m" >>$logfile 2>&1
   echo -e "\033[47;30m|                     部署COMPLEX服务START                               |\033[0m" >>$logfile 2>&1
   echo -e "\033[47;30m+----------------------------------------------------------------------+\033[0m" >>$logfile 2>&1
}

recordLog() {

   #结束时间
   END_TIME=$(date +%s)
   echo "==========================end    $(date "+%Y-%m-%d %H:%M:%S")=================================================" >>$logfile 2>&1

   #计算脚本执行时间
   echo "==========================time   consuming $(($END_TIME - $begin_time)) seconds=================================================" >>$logfile 2>&1

   echo -e "\033[47;30m+----------------------------------------------------------------------+\033[0m" >>$logfile 2>&1
   echo -e "\033[47;30m|                     部署COMPLEX服务END                                |\033[0m" >>$logfile 2>&1
   echo -e "\033[47;30m+----------------------------------------------------------------------+\033[0m" >>$logfile 2>&1

   #输出一行空行到日志中，方便区分每次执行的日志
   echo "" >>$logfile 2>&1
}

preIngLog(){
   # 事件信息
   message=$1
   echo "开始执行【$message】  $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
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
      echo "SUCCESS: 【$message】, 复制 $source 到 $target 成功 $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
   else
      echo "WARN: 【$message】, 复制  $source 到 $target 失败, 请注意检查！！！ $(date "+%Y-%m-%d %H:%M:%S") " >>$logfile 2>&1
   fi
}

# 各业务扩展函数, 用于各业务自行扩展配置文件的变更
customBusExt(){
   echo " For  Extend ...." >>$logfile 2>&1
}

#还原配置文件
file_restore(){
  \cp -rf $1/*.* $2
  save2Log $?  '还原配置文件'  $1 $2
}

#根据产品安装环境判断是否还原配置文件
config_restore(){
  #根据lowkey的redis-路径判断是否为老产品升级lowkey
  if [ -d $redis_path/redis/redis-/bin ]; then
    #根据mfs的安装目录判断当前系统上的lowkey是否为微服务版
    if [ -d $mfsInstallPath ]; then
      #根据解压的安装包中是否包含mfs判断目标版本是否为微服务版
      if [ -d $mfsSourcePath ]; then
        echo "当前产品升级为： lowkey微服务版 升级 lowkey微服务版 执行配置文件的还原操作" >>$logfile 2>&1
        #还原配置文件
        file_restore $1 $2
      else
        #todo 预留
        echo "当前产品升级为: lowkey微服务版 升级 待扩展" >>$logfile 2>&1
      fi
    else
      #根据解压的安装包中是否包含mfs判断目标版本是否为微服务版
      if [ -d $mfsSourcePath ]; then
        echo "当前产品升级为: lowkey单体版 升级 lowkey微服务版 不执行配置文件的还原操作" >>$logfile 2>&1
      else
        echo "当前产品升级为: lowkey单体版 升级 lowkey标准版 执行配置文件的还原操作" >>$logfile 2>&1
        #还原配置文件
        file_restore $1 $2
      fi
    fi
  else
    #老产品的升级
    echo "当前产品升级为: 老产品 升级 lowkey 不执行配置文件的还原操作" >>$logfile 2>&1
  fi
}

#兼容老产品升级lowkey-注册服务
service_registry(){
  if [ ! -f /etc/systemd/system/tomcat.service ]; then
    \cp -rf $unZipPath//web/etc/systemd/system/tomcat.service /etc/systemd/system
    chmod +x /etc/systemd/system/tomcat.service
    echo "为兼容之前老版本升级 注册服务tomcat.service到/etc/systemd/system" >>$logfile 2>&1
  fi
}

operate_web() {
   if [ $tomcatFlag != 1 ]; then
      ####################################国产中间件################################################
      if [ -d $lowkeyNonTomcatComplexPath ]; then
         #升级新程序API
         preIngLog  '国产中间件-升级complex'
         \cp -rf $ApiSource $productMidPath
         save2Log $?  '国产中间件-升级complex'  $ApiSource  $productMidPath

         #还原配置文件 从第一个脚本 1.pre_check.sh 里备份的源文件 cp配置文件 （如果新版本的配置文件有修改, 需要各个业务维护变更 ）
         preIngLog  '国产中间件-升级:还原complex配置文件'
         config_restore $originNoneTomcatWebBackPath/api/WEB-INF  $lowkeyNonTomcatComplexPath/WEB-INF

         #扩展预留
         customBusExt

      else
         #新装 api
         preIngLog  '国产中间件-新装complex'
         \cp -rf $ApiSource $productMidPath
         save2Log $?  '国产中间件-新装complex'  $ApiSource  $productMidPath

      fi

   else
      ####################################Tomcat################################################

      ###根据是否存在complex文件目录判定是否已经安装过###
      if [ -d $lowkeyTomcatComplexPath ]; then

         #升级新程序
         preIngLog  'Tomcat-升级'
         \cp  -rf  $WebSource   /
         save2Log $?  'Tomcat-升级'  $WebSource  /


         #还原配置文件 从第一个脚本 1.pre_check.sh 里备份的源文件 cp配置文件 （如果新版本的配置文件有修改, 需要各个业务维护变更 ）
         preIngLog  'Tomcat-升级:还原complex配置文件'
         config_restore $originWebBackPath/webapps/api/WEB-INF  $lowkeyTomcatComplexPath/WEB-INF

         preIngLog  'Tomcat-升级:还原complex配置文件'
         config_restore $originWebBackPath/webapps/core/WEB-INF $TomcatCorePath/WEB-INF

         #扩展预留
         customBusExt

	       #兼容老产品升级lowkey将服务注册到/etc/systemd/system/交给监控中心托管
	       service_registry

      else
         #新装
         preIngLog  'Tomcat-新装'
         \cp -rf $WebSource  /
         save2Log $?  'Tomcat-新装'  $WebSource   /

         preIngLog  'Tomcat-新装:开机启动服务'
         \cp -rf $unZipPath//web/etc/systemd/system/tomcat.service /etc/systemd/system
         save2Log  $?  'Tomcat-新装:开机启动服务'  $unZipPath//web/etc/systemd/system/tomcat.service  /etc/systemd/system

      fi
   fi
}

getSysInfo() {

   sysInfo=$(uname -a)

   #架构
   if [[ $sysInfo =~ "x86" ]]; then
      echo "当前cpu架构为x86 : $sysInfo" >>$logfile 2>&1
      arch="x86"
   fi
   if [[ $sysInfo =~ "aarch" ]]; then
      echo "当前cpu架构为aarch : $sysInfo" >>$logfile 2>&1
      arch="arm"
   fi
   if [[ $sysInfo =~ "mips" ]]; then
      echo "当前cpu架构为mips : $sysInfo" >>$logfile 2>&1
      arch="mips"
   fi
}

makeUpRedis() {

   echo "--------------make up redis  start--------------" >>$logfile 2>&1
   echo "开始组装redis:"$arch >>$logfile 2>&1

   #复制对应架构的redis_bin到redis/bin
   \cp -rf $unZipPath/redis/lib/$arch/redis_bin/* $unZipPath/redis/redis-/bin/

   echo "--------------make up redis end---------------" >>$logfile 2>&1
}

operate_redis() {
   # 原redis安装路径不存在,则说明未安装过，继续判断lowkey的网检redis是否安装，如果都没安装，则新装。 其中任何之一安装，采取兼容方案，不做任何操作
   if [ ! -d $originRedisPath ]; then
      if [ ! -d $redis_path/redis/redis- ]; then
         mkdir -p $redis_path/redis/redis-
         # 根据当前os的架构 组装一个可用的redis
         getSysInfo
         makeUpRedis

         \cp -rf $unZipPath/redis/redis-  $redis_path/redis

         chmod -Rf 755 $redis_path/redis/redis-

         #注册服务 & 开机启动
         \cp -rf $unZipPath/redis/redis-/runshell/redis.service /etc/systemd/system/
         save2Log  $? '开机启动服务'  $unZipPath/redis/redis-/runshell/redis.service /etc/systemd/system/

         chmod a+x /etc/systemd/system/redis.service
         sleep 1
         systemctl enable redis.service 2>/dev/null
         echo "-------------redis开机启动设置成功------------" >>$logfile 2>&1

         systemctl daemon-reload
         systemctl restart redis.service 2>/dev/null
         echo "-------------redis restart ok-------------" >>$logfile 2>&1
         echo "-------------redis install end------------" >>$logfile 2>&1
      else
         \cp -rf $unZipPath/redis/redis-/confs/redis-.conf $redis_path/redis/redis-/confs
         echo "------------LOWKEY的 -redis [$redis_path/redis/redis-] 存在 , 采用兼容方案,本次升级部署只覆盖redis.conf文件------------" >>$logfile 2>&1
      fi
   else
      #todo 此次适配没有老的redis （如果老版本包含redis处理方式同保密检查一样）
      \cp -rf $unZipPath/redis/redis-/confs/redis-.conf $originRedisPath/conf/redis.conf
      echo "------------存在非LOWKEY版本的-redis,安装目录: [$originRedisPath] , 采用兼容方案,本次升级部署只覆盖redis.conf文件------------" >>$logfile 2>&1
   fi

}


install() {
   echo "------------ install start------------" >>$logfile 2>&1
   #执行顺序确保redis最后安装 有根据redis路径执行判断的逻辑
   operate_web
   operate_redis
   echo "------------ install end------------" >>$logfile 2>&1
}

postInstall() {

   if [ $tomcatFlag != 1 ]; then
      ####################################国产中间件################################################
       chmod -Rf 755 /appstore
      echo "------------ 国产中间件 postInstall do nothing------------" >>$logfile 2>&1
   else
      ####################################Tomcat################################################
      echo "------------ tomcat postInstall start------------" >>$logfile 2>&1
      # 赋权
      chmod a+x /etc/systemd/system/tomcat.service
      chmod -Rf 755 /appstore
      # 设置开机自启
      systemctl enable tomcat.service 2>/dev/null

      # 重载系统服务
      systemctl daemon-reload
      echo "------------ tomcat postInstall end------------" >>$logfile 2>&1
   fi

}

#-------------------------function area-------------------------

#-------------------------main area-------------------------

# Step 1
preInstall

# Step 2
install

# Step 3
postInstall

# Step 4
recordLog

exit $exit_success

#-------------------------main area-------------------------
