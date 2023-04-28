## 安装配置NFS共享

### 前提

```
无论是服务端还是客户端都必须保证安装rpcbind和nfs-utils服务
```

### 安装

- 验证是否安装

    - 通过rpm安装：

      rpm -qa|grep rpcbind

      rpm -qa|grep nfs-utils

    - 通过deb安装：

      dpkg -l|grep rpcbind

      dpkg -l|grep nfs-utils

- 安装

  若未安装，则进行安装

  ```
    yum -y install rpcbind
    
    yum -y install nfs-utils
  ```

### 启动

- 启动命令

    ```
    # 启动(会自动拉起rpcbind)
    systemctl start nfs
    # 停止
    systemctl stop nfs
    # 查看状态
    # rpcbind 若没有启动手动启动 systemctl start rpcbind
    systemctl status rpcbind
    systemctl status nfs
    # 设置开机自启：
    systemctl enable rpcbind
    systemctl enable nfs-utils
    ```

- 问题处理

    - 错误描述
      ```
      /sbin/rpcbind: error while loading shared libraries: libtirpc.so.1: cannot open shared object file: No such file or directory
       ```
    - 问题原因
      ```
      缺少依赖：libtirpc.so.1
      ```
    - 解决方案
      ```
      1.安装依赖
          dnf install libnsl
      2.启动
          systemctl start nfs
          若还报错继续向下执行，若不报错，则进行下面配置步骤
      3.换种方式安装依赖(或者自己去下载离线依赖进行安装)
        # 先查询
        sudo apt search libtirpc
            正在排序... 完成
            全文搜索... 完成
            libtirpc-common/未知,未知,now 1.1.4-0.4 all [已安装，自动]
            transport-independent RPC library - common files
            
            libtirpc-dev/未知 1.1.4-0.4 amd64
            transport-independent RPC library - development files
            
            libtirpc3/未知,now 1.1.4-0.4 amd64 [已安装]
            transport-independent RPC library
            
            libtirpc3-dbgsym/未知 1.1.4-0.4 amd64
            debug symbols for libtirpc3
        # 查询出依赖进行安装
        sudo apt install -y libtirpc-common libtirpc-dev libtirpc3
      4.安装完成进行依赖加载
        # 查找依赖
        find / -name libtirpc.so.1
        
        /usr/lib64/libtirpc.so.1
        # 加载依赖
        # 修改配置文件
        vim /etc/ld.so.conf
        # 添加上面依赖所在文件夹
        include /etc/ld.so.conf.d/*.conf
        /usr/lib64
        # 保存，重启配置
        ldconfig
      5.启动
        systemctl start nfs
      ```

### 配置nfs

```
以以下服务器为例：
服务端IP:20.10.110.137
客户端IP:20.10.110.139
```

#### 配置服务端

```

服务端:
先进行137的服务端安装：
创建共享目录：
mkdir -p /home/zhongfu/share/appstore/data
配置共享目录信息：
vi /etc/exports
在最下面添加/home/zhongfu/share/appstore/data 20.10.110.0/24(rw,sync,no_root_squash)
（/home/zhongfu/share/appstore/data为共享目录，20.10.110.0表示20.10.110.x段的ip可以进行服
务挂载，具体情况依据现场环境来决定）

使配置生效：
exportfs -r

查看是否挂成功：
showmount -e

至此服务端操作完成。
```

#### 配置客户端

```
客户端：
再进行139的客户端安装：

查看是是否可以挂载137服务：
showmount -e 20.10.110.137

在139上创建挂载目录
mkdir -p /home/zhongfu/share/appstore/data
挂载137共享目录：
mount -t nfs 20.10.110.137:/home/zhongfu/share/appstore/data
/home/zhongfu/share/appstore/data/

查看是否挂载成功：
df -h /home/zhongfu/share/appstore/data

至此客户端挂载成功。
```

### 取消挂载

```
取消挂载
查看占用命令：fuser -mv /home/data_azkaban
杀死占用命令：fuser -kv /home/data_azkaban
取消挂载命令：umount -a /home/data_azkaban
```



