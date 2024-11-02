## 什么是Docker?

Docker是一个开源的引擎，可以轻松的为任何应用`创建`一个`轻量级的`、`可移植的`、`自给自足`的`容器`。开发者在笔记本上编译测试通过的容器可以`批量`地在`生产环境`中`部署`，包括VMs（虚拟机）、 [bare metal](http://www.whatis.com.cn/word_5275.htm)、OpenStack 集群和其他的基础应用平台。 

#### Docker通常用于如下场景：

- web应用的自动化打包和发布；
- 自动化测试和持续集成、发布；
- 在服务型环境中部署和调整数据库或其他的后台应用；
- 从头编译或者扩展现有的OpenShift或Cloud Foundry平台来搭建自己的PaaS环境。

## Docker作用

常规开发

在虚拟机里面部署库 部署应用 占用内存过多

docker开发

在虚拟机里面上传docker镜像 部署镜像 占用内存少

## Docker安装

### Docker基本组成

镜像（image）：一个模板，可以通过这个模板来创建容器服务，tomcat镜像->run->tomcat01容器（提供服务器）。通过这个镜像可以创建多个容器（最终服务运行或者项目运行就是在容器中）

容器（container）：Docker利用容器技术，独立运行一个或一个组应用，通过镜像来创建。启动，停止，删除，基本命令，可以理解为简易的linux系统

仓库（repositsory）：存放镜像的地方，共有仓库，私有仓库

### 安装docker

centOS 7

#### 安装



#### **1.查看当前的内核版本**

```text
[root@localhost vagrant]# uname -r
3.10.0-327.4.5.el7.x86_64
[root@localhost vagrant]#
```

#### **2.卸载旧版本（如果之前安装过的话）**

```text
[root@localhost vagrant]# sudo yum remove docker \
>                   docker-client \
>                   docker-client-latest \
>                   docker-common \
>                   docker-latest \
>                   docker-latest-logrotate \
>                   docker-logrotate \
>                   docker-engine
Loaded plugins: fastestmirror
Repodata is over 2 weeks old. Install yum-cron? Or run: yum makecache fast
No Match for argument: docker
No Match for argument: docker-client
No Match for argument: docker-client-latest
No Match for argument: docker-common
No Match for argument: docker-latest
No Match for argument: docker-latest-logrotate
No Match for argument: docker-logrotate
No Match for argument: docker-engine
No Packages marked for removal
```

#### **3.安装需要的软件包**

yum-util 提供yum-config-manager功能，另两个是devicemapper驱动依赖

```text
yum install -y yum-utils device-mapper-persistent-data lvm2
```

#### **4.设置yum安装源**

```text
yum-config-manager --add-repo http://download.docker.com/linux/centos/docker-ce.repo（中央仓库）

yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo（国内建议安装阿里仓库）
```

#### **5.选择docker版本并安装**

1. 查看可用版本有哪些

```text
yum list docker-ce --showduplicates | sort -r
[root@localhost vagrant]# yum list docker-ce --showduplicates | sort -r
 * updates: mirrors.163.com
Loading mirror speeds from cached hostfile
Loaded plugins: fastestmirror
 * extras: mirrors.163.com
 * epel: fedora.cs.nctu.edu.tw
docker-ce.x86_64            3:19.03.9-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.8-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.7-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.6-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.5-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.4-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.3-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.2-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.14-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.1-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.13-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.12-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.11-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.10-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.0-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.9-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.8-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.7-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.6-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.5-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.4-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.3-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.2-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.1-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.0-3.el7                     docker-ce-stable
docker-ce.x86_64            18.06.3.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.06.2.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.06.1.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.06.0.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.03.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            18.03.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.12.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.12.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.09.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.09.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.2.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.3.ce-1.el7                    docker-ce-stable
docker-ce.x86_64            17.03.2.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.0.ce-1.el7.centos             docker-ce-stable
 * base: mirrors.163.com
Available Packages
```

1. 选择一个版本并安装：`yum install docker-ce-版本号`
   yum install -y docker-ce docker-ce-cli [http://containerd.io](https://link.zhihu.com/?target=http%3A//containerd.io) 最新版本

ce 社区版 ee企业版

#### **6.启动 Docker 并设置开机自启**

```text
systemctl start docker
systemctl enable docker
```

#### **7.测试docker是否安装成功**

```text
[root@localhost vagrant]# docker version
Client: Docker Engine - Community
 Version:           24.0.5
 API version:       1.43
 Go version:        go1.20.6
 Git commit:        ced0996
 Built:             Fri Jul 21 20:39:02 2023
 OS/Arch:           linux/amd64
 Context:           default

Server: Docker Engine - Community
 Engine:
  Version:          24.0.5
  API version:      1.43 (minimum version 1.12)
  Go version:       go1.20.6
  Git commit:       a61e2b4
  Built:            Fri Jul 21 20:38:05 2023
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          1.6.22
  GitCommit:        8165feabfdfe38c65b599c4993d227328c231fca
 runc:
  Version:          1.1.8
  GitCommit:        v1.1.8-0-g82f18fe
 docker-init:
  Version:          0.19.0
  GitCommit:        de40ad0
```

#### 8.**运行hello-world**

```shell
[root@master enoch]# docker run hello-world
Unable to find image 'hello-world:latest' locally #寻找镜像
latest: Pulling from library/hello-world#拉取镜像

#拉取成功的信息
719385e32844: Pull complete 
Digest: sha256:dcba6daec718f547568c562956fa47e1b03673dd010fe6ee58ca806767031d1c
Status: Downloaded newer image for hello-world:latest

#安装成功的信息
Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/


```

#### 9.**查看hello-world镜像**

```shell
[root@master enoch]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED        SIZE
hello-world   latest    9c7a54a9a43c   3 months ago   13.3kB
```

#### 10.**卸载docker**

```shell
yum remove docker docker-ce docker-ce-cli containerd.io
```

```shell
rm -rf /var/lib/docker #docker工作路径
```



#### 11**.添加添加阿里云镜像**

阿里云镜像地址：[https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors](https://link.zhihu.com/?target=https%3A//cr.console.aliyun.com/cn-hangzhou/instances/mirrors)

针对Docker客户端版本大于 1.10.0 的用户

您可以通过修改daemon配置文件/etc/docker/daemon.json来使用加速器

```shell

sudo mkdir -p /etc/docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://yqre8ban.mirror.aliyuncs.com"]
}
EOF

sudo systemctl daemon-reload

sudo systemctl restart docker
```

### 运行镜像流程

![image-20230813164556660](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230813164556660.png)

## Docker命令

### 帮助命令

#### docker version 

显示版本信息

#### docker info

 显示docker系统信息，包括镜像和容器的数量

#### docker 命令 -help 

万能命令

#### 

docker命令帮助文档：https://docs.docker.com/engine/reference/run/

### 镜像命令

#### docker images 

查看所有本地主机上的镜像

```shell
[root@master enoch]# docker images#查看所有本地主机上的镜像
REPOSITORY    TAG       IMAGE ID       CREATED        SIZE
hello-world   latest    9c7a54a9a43c   3 months ago   13.3kB

#解释
REPOSITORY     镜像的仓库源
TAG       	   镜像的标签
IMAGE ID       镜像id
CREATED        镜像的创建时间
SIZE		   镜像大小

#可选项
  -a, --all             #显示全部镜像

  -q, --quiet          #只显示镜像id
  docker images -aq

```

#### docker search

搜索镜像

```shell
[root@master enoch]# docker search mysql
NAME         DESCRIPTION                          STARS     OFFICIAL       AUTOMATED
mysql        MySQL is a widely used, open-source  14381     [OK]       
mariadb      MariaDB Server is a high performing  5490      [OK]       
#解释
NAME                名称             
DESCRIPTION         描述                           
STARS     			收藏
OFFICIAL   			
AUTOMATED
#选项
Options:
  -f, --filter          通过上面的标签来过滤
  #docker search mysql -f=stars=3000 这条命令会搜索 收藏数大于等于3000的
```

#### docker pull

拉取下载镜像

```shell
# docker pull 镜像名[:tag] 选择版本
[root@master enoch]# docker pull mysql
Using default tag: latest #如果不写tag 默认就是latest
latest: Pulling from library/mysql
72a69066d2fe: Pull complete #分层下载 docker images核心 联合文件系统
93619dbc5b36: Pull complete 
99da31dd6142: Pull complete 
626033c43d70: Pull complete 
37d5d7efb64e: Pull complete 
ac563158d721: Pull complete 
d2ba16033dad: Pull complete 
688ba7d5c01a: Pull complete 
00e060b6d11d: Pull complete 
1c04857f594f: Pull complete 
4d7cfa90e6ea: Pull complete 
e0431212d27d: Pull complete 
Digest: sha256:e9027fe4d91c0153429607251656806cc784e914937271037f7738bd5b8e7709 #签名
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest #真实地址

#等价于下面
docker pull mysql
docker pull docker.io/library/mysql:latest

#指定版本下载
docker pull mysql:5.7
[root@master enoch]# docker pull mysql:5.7
5.7: Pulling from library/mysql
72a69066d2fe: Already exists 
93619dbc5b36: Already exists 
99da31dd6142: Already exists 
626033c43d70: Already exists 
37d5d7efb64e: Already exists 
ac563158d721: Already exists 
d2ba16033dad: Already exists 
0ceb82207cd7: Pull complete 
37f2405cae96: Pull complete 
e2482e017e53: Pull complete 
70deed891d42: Pull complete 
Digest: sha256:f2ad209efe9c67104167fc609cca6973c8422939491c9345270175a300419f94
Status: Downloaded newer image for mysql:5.7
docker.io/library/mysql:5.7

```

#### docker rmi

删除镜像

```shell
 #根据id删除
 #docker rmi -f  容器id 容器id 容器id 容器id 空格批量删除
[root@master enoch]# docker rmi -f  c20987f18b13
Untagged: mysql:5.7
Untagged: mysql@sha256:f2ad209efe9c67104167fc609cca6973c8422939491c9345270175a300419f94
Deleted: sha256:c20987f18b130f9d144c9828df630417e2a9523148930dc3963e9d0dab302a76
Deleted: sha256:6567396b065ee734fb2dbb80c8923324a778426dfd01969f091f1ab2d52c7989
Deleted: sha256:0910f12649d514b471f1583a16f672ab67e3d29d9833a15dc2df50dd5536e40f
Deleted: sha256:6682af2fb40555c448b84711c7302d0f86fc716bbe9c7dc7dbd739ef9d757150
Deleted: sha256:5c062c3ac20f576d24454e74781511a5f96739f289edaadf2de934d06e910b92

#递归删除全部镜像
[root@master enoch]# docker rmi -f $(docker images -aq)
Untagged: hello-world:latest
Untagged: hello-world@sha256:dcba6daec718f547568c562956fa47e1b03673dd010fe6ee58ca806767031d1c
Deleted: sha256:9c7a54a9a43cca047013b82af109fe963fde787f63f9e016fdc3384500c2823d
Untagged: mysql:latest
Untagged: mysql@sha256:e9027fe4d91c0153429607251656806cc784e914937271037f7738bd5b8e7709
Deleted: sha256:3218b38490cec8d31976a40b92e09d61377359eab878db49f025e5d464367f3b
Deleted: sha256:aa81ca46575069829fe1b3c654d9e8feb43b4373932159fe2cad1ac13524a2f5
Deleted: sha256:0558823b9fbe967ea6d7174999be3cc9250b3423036370dc1a6888168cbd224d
Deleted: sha256:a46013db1d31231a0e1bac7eeda5ad4786dea0b1773927b45f92ea352a6d7ff9
Deleted: sha256:af161a47bb22852e9e3caf39f1dcd590b64bb8fae54315f9c2e7dc35b025e4e3
Deleted: sha256:feff1495e6982a7e91edc59b96ea74fd80e03674d92c7ec8a502b417268822ff
Deleted: sha256:8805862fcb6ef9deb32d4218e9e6377f35fb351a8be7abafdf1da358b2b287ba
Deleted: sha256:872d2f24c4c64a6795e86958fde075a273c35c82815f0a5025cce41edfef50c7
Deleted: sha256:6fdb3143b79e1be7181d32748dd9d4a845056dfe16ee4c827410e0edef5ad3da
Deleted: sha256:b0527c827c82a8f8f37f706fcb86c420819bb7d707a8de7b664b9ca491c96838
Deleted: sha256:75147f61f29796d6528486d8b1f9fb5d122709ea35620f8ffcea0e0ad2ab0cd0
Deleted: sha256:2938c71ddf01643685879bf182b626f0a53b1356138ef73c40496182e84548aa
Deleted: sha256:ad6b69b549193f81b039a1d478bc896f6e460c77c1849a4374ab95f9a3d2cea2

```



### 容器命令

有了镜像才可以创建容器 ，下载一个centos镜像来测试学习

```shell
docker pull centos
```

#### docker run

创建容器并运行

```shell
docker run -d//运行方式 --name=别名 -p2080外部端口:80容器端口 镜像名称/镜像id


#选项说明
--name=“Name" 容器名字 tomcat1 tomcat2 用来区分区域
-d            后台方式运行
-it 		  使用交互方式运行，进入容器查看内容
-p            指定容器端口 -p 8080:8080
   			-p 主机端口:容器端口(常用) //公网可以访问容器内部的端口
   			-p 容器端口
   			-p ip:主机端口:容器端口
-P            随机指定端口

#测试
[root@master enoch]#  docker run -it centos /bin/bash #启动并进入centos容器
[root@43dc75b71b7b /]# ls #查看centos容器的文件 基础版本,很多命令都不完善
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var

#容器中退回主机
[root@43dc75b71b7b /]# exit
exit
[root@master enoch]# 
```

#### docker ps

列出容器

```shell
 无选项      #列出当前正在运行的容器
 -a   #列出当前正在运行的容器+历史运行容器
 -n=? #显示最近创建n个容器 
 -q   #显示运行容器的编号

#查看现在运行的容器
[root@master enoch]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
#现在运行的容器+查看曾经运行的容器
[root@master enoch]# docker ps -a
CONTAINER ID   IMAGE          COMMAND       CREATED         STATUS                          PORTS     NAMES
43dc75b71b7b   centos         "/bin/bash"   5 minutes ago   Exited (0) About a minute ago             angry_maxwell
cb9072aa64b7   9c7a54a9a43c   "/hello"      2 hours ago     Exited (0) 2 hours ago                    magical_matsumoto

```



#### 退出容器

```
exit
直接退出
Ctrl + P + Q 
容器不停止退出
```



#### docker rm 

删除容器

```
docker rm 容器id #删除容器，正在运行的不能删除，如果要强制删除 rm -f
docker rm -f $(docker ps -aq)#强制删除所有容器
```

```shell
[root@master enoch]# docker rm -f $(docker ps -aq)
d56a48559fc0
2b308c0fc07f
95c2dea7abce
c9aef6084ca2
1cb67694b10d
37c2b3f31759
43dc75b71b7b
cb9072aa64b7

```

#### docker start

启动停止容器



```
docker start 容器id #启动容器
docker restart 容器id #重新启动容器
docker stop 容器id #停止当前正在运行的容器
docker kill 容器id #强制停止当前容器
```

#### docker att

进入容器运行终端

#### docker exec 

进入容器

```shell
docker exec -it 容器名称/容器id  /bin/bash 
```



如果要修改配置文件 就需要进入容器 我们想要实现在外部实现一个映射文件 通过外部修改达到修改内部配置文件的目的 ：-v 数据卷



### 其他命令

#### 后台启动容器

```shell
docker run -d 镜像名

[root@master enoch]# docker run -d centos
df22517fe0b5ca44e860c6e4544ed39ccd64a60de34843e50c6fe076d2e77836

# 使用后台运行时发现没有运行后台

[root@master enoch]# docker ps 
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@master enoch]# 

#docker使用后台运行就必须存在一个前台进程，docker发现没有应用 就会自动停止
#通俗一点讲 就是容器启动后发现自己没有提供服务，就会立刻停止，就没有程序了
```

#### 查看日志

docker logs

#### 访问端口

curl localhost:3344（地址）

#### 提交镜像

docker commit

```
docker commit 提交容器成为一个新的副本

docker commit -m="描述信息" -a="作者" 容器id 目标镜像名:[TAG]
```

这里修改了tomcat的webapps文件夹 我想将修改的tomcat单独做成一个镜像  以后就用这个镜像启动自己的应用

```
docker commit -m="添加了webapps的一些文件" -a="enoch" 901daf64f070 tomcat02:1.0
```

--------------

上面能解决90%的项目部署问题

#### 查看配置

docker inspect 容器/镜像 id/名称

#### 查看卷

docker volume 

### 小结

![image-20230813212945689](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230813212945689.png)

### 完整部署一个tomcat

```
//搜索tomcat
docker search tomcat
//拉取tomcat
docker pull tomcat
//运行容器
docker run -d --name=tomcat01 -p3355:8080 tomcat
//进入容器
docker exec -it tomcat01 /bin/bash
//查看容器
ls -l
total 132
-rw-r--r--. 1 root root 18994 Dec  2  2021 BUILDING.txt
-rw-r--r--. 1 root root  6210 Dec  2  2021 CONTRIBUTING.md
-rw-r--r--. 1 root root 60269 Dec  2  2021 LICENSE
-rw-r--r--. 1 root root  2333 Dec  2  2021 NOTICE
-rw-r--r--. 1 root root  3378 Dec  2  2021 README.md
-rw-r--r--. 1 root root  6905 Dec  2  2021 RELEASE-NOTES
-rw-r--r--. 1 root root 16517 Dec  2  2021 RUNNING.txt
drwxr-xr-x. 2 root root  4096 Dec 22  2021 bin
drwxr-xr-x. 1 root root    22 Aug 16 09:24 conf
drwxr-xr-x. 2 root root  4096 Dec 22  2021 lib
drwxrwxrwx. 1 root root    80 Aug 16 09:24 logs
drwxr-xr-x. 2 root root   159 Dec 22  2021 native-jni-lib
drwxrwxrwx. 2 root root    30 Dec 22  2021 temp
drwxr-xr-x. 2 root root     6 Dec 22  2021 webapps
drwxr-xr-x. 7 root root    81 Dec  2  2021 webapps.dist
drwxrwxrwx. 2 root root     6 Dec  2  2021 work

这里webapps里面是空的 导致访问tomcat没有主页 webapps.dist 下面存在完整项目 将下面的文件全部复制到 webapps下面就可以访问到主页

cp -r webapps.dist/* webapps
```

以后部署自己项目 如果都要进入容器更改自己的文件特别麻烦 

在外部提供映射路径 我们在外部放置项目 自动同步到内部

数据卷 -v

### 完整部署es + kibana

```
问题：
es暴露端口很多
es十分耗内存
es的数据一般需要防止到安全目录


安装elasticsearch


//查看docker 内存使用状况
docker stats

```

## 可视化

- portainer（暂时）

```shell
docker run -d -p 8088:9000 --restart =always -v /var/run/docker.sock:/var/run/docker.sock --privileged=true portainer/portainer
```

- rancher（集成环境再用）



### portainer

docker图形化面板 提供一个后台模板供我们操作



 



# Docker 精髓



## Docker数据卷

-v 主机目录:docker虚拟机目录 

```
docker run -it -v /home/test:/home/test centos /bin/bash

```

![image-20230821112520815](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230821112520815.png)

容器不运行也能同步

### 安装mysql

```shell
#获取镜像
docker pull mysql:5.7
#运行容器 -
docker run -d -p 3310:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name=mysql01 mysql:5.7

```

### 具名与匿名挂载

```shell
#匿名挂载 
-v 容器内路径
docker run -d -P --name=nginx01 -v /etc/nginx nginx
#查看卷信息 卷名是很长字符串 就是匿名挂载 
[root@master data]# docker volume ls
DRIVER    VOLUME NAME
local     97d67f4c4b419c7057151087fcebbab7162de2efadaaefeb2bed2c61ef36d677
#具名挂载 卷名:容器内路径
docker run -d -P --name=nginx02 -v juming:/etc/nginx nginx
#查看一下卷
[root@master data]# docker volume inspect juming
[
    {
        "CreatedAt": "2023-08-16T23:22:39+08:00",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/juming/_data",
        "Name": "juming",
        "Options": null,
        "Scope": "local"
    }
]
```

所有没有指定主机目录的情况 默认都在`/var/lib/docker/volumes/xxx/_data`

```

-v 容器内路径      #匿名挂载
-v 卷名:容器内路径  #具名挂载
-v /主机路径:/容器路径 #指定路径挂载
```

拓展：

```
#通过 -v 容器内路径:ro/rw 改变读写权限
ro 只读
rw 可读可写

#设定权限就不能改变容器内权限
docker run -d -P --name=nginx02 -v juming:/etc/nginx:ro nginx
docker run -d -P --name=nginx02 -v juming:/etc/nginx:rw nginx
```

### 初识DockerFile 

DockerFile就是用来构建docker镜像的构建文件！命令和脚本

通过脚本生成镜像，脚本就是命令，每一个命令都是一层

```yaml
#dockerfile文件
FROM centos

VOLUME ["volume01","volume02"] #匿名挂载

CMD echo "---end---"
CMD /bin/bash
```

```shell
#构建镜像
[root@master docker-test-volume]# docker build -f dockerfile1 -t enoch/centos .
[+] Building 0.1s (5/5) FINISHED                                                                                                                     docker:default
 => [internal] load build definition from dockerfile1                                                                                                          0.0s
 => => transferring dockerfile: 180B                                                                                                                           0.0s
 => [internal] load .dockerignore                                                                                                                              0.0s
 => => transferring context: 2B                                                                                                                                0.0s
 => [internal] load metadata for docker.io/library/centos:latest                                                                                               0.0s
 => [1/1] FROM docker.io/library/centos                                                                                                                        0.0s
 => exporting to image                                                                                                                                         0.0s
 => => exporting layers                                                                                                                                        0.0s
 => => writing image sha256:911727b472f3707bc9853148e545917be2a148b6c92ae520628b8d5e0378ad41                                                                   0.0s
 => => naming to docker.io/enoch/centos     
 
#查看运行镜像
[root@master docker-test-volume]# docker images
REPOSITORY            TAG       IMAGE ID       CREATED         SIZE
tomcat02              1.0       15427a90be66   4 hours ago     684MB
nginx                 latest    605c77e624dd   19 months ago   141MB
tomcat                latest    fb5657adc892   20 months ago   680MB
mysql                 5.7       c20987f18b13   20 months ago   448MB
enoch/centos          latest    911727b472f3   23 months ago   231MB
centos                latest    5d0da3dc9764   23 months ago   231MB
portainer/portainer   latest    580c0e4e98b0   2 years ago     79.1MB
[root@master docker-test-volume]# docker run -it 911727b472f3 /bin/bash
#查看文件 发现存在我们构建文件的文件夹 这两个卷和外部存在同步目录
[root@839321ec480a /]# ls -l
...
drwxr-xr-x.   2 root root   6 Aug 16 16:46 volume01
drwxr-xr-x.   2 root root   6 Aug 16 16:46 volume02



```

### 数据卷容器

多个mysql同步数据



## DockerFile

dockerfile是用来构建docker镜像的文件 命令参数脚本

构建步骤：

- 编写一个dockerfile
- docker build构建成为一个镜像
- docker run 运行镜像
- docker push 发布镜像（dockerhub 阿里云镜像仓库）

### DockerFile构建过程

1.指令 都必须是大写

2.指令 从上到下顺序执行

3.‘#’    表示注释

4.每一条指令都会创建提交一个新的镜像层，并提交

![image-20230822194334577](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230822194334577.png)



dockerfile是面向开发的，我们以后要发布项目做镜像都要编写dockerfile文件。



dockerfile ：构建文件，定义一切的步骤，源代码

dockerimage：通过dockerfile构建出来的镜像，最终发布和运行的产品

docker容器：容器就是镜像运行起来的服务器



### 指令

![image-20230822195153287](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230822195153287.png)

BUILD ：构建时的命令

BOTH：链接工作的命令

RUN：运行时的命令

#### 常用指令

![image-20230822195313958](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230822195313958.png)

```
FROM        	#基础镜像
MAINTAINER 		#镜像作者 姓名+邮箱
RUN 			#镜像构建时运行的命令
ADD 			#压缩文件 
WORKDIR		 	#镜像工作目录
VOLUME  		#挂载目录
EXPOSE  		#指定暴露端口 -p
CMD     		#指定容器启动的时候要运行的命令 只有最后一个会被生效，可被替代
ENTRYPOINT 		#指定容器启动的时候要运行的命令 可以追加命令
ONBUILD    		#当构建一个被继承DockerFile 的时候就会运行 触发指令
COPY  			#类似ADD 将文件拷贝到镜像中
ENV				#构建的时候设置环境变量


```

### 构建一个centos

dockerhub中99%镜像都是从这个基础镜像构建的 FROM scrtch,然后配置需要的软件和配置

![image-20230822200523915](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230822200523915.png)

自己的centos

1. 创建一个文件夹![image-20230822200808498](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230822200808498.png)

2. 创建一个dockerfile文件

   ```
   FROM centos:7 #基于centos
   
   MAINTAINER enoch<15328504005@163.com> #作者信息
   
   ENV MYPATH /usr/local #设置环境变量目录 
   WORKDIR $MYPATH #设置工作目录 这里设置的话进入终端就直接到这个目录
   
   RUN yum -y install vim
   RUN yum -y install net-tools
   
   EXPOSE 80
   
   CMD echo $MYPATH
   CMD echo "---end---"
   CMD /bin/bash     
   ```

3. 构建这个镜像

   ```
   docker build -f mydockerfile-centos -t mycentos:1.0 .
   ```

4. 测试这个镜像

   ```
   docker run -id c67a8d82ddac 
   #能够正常使用ifcofig vim
   ```

   列出镜像生成历史 docker history 镜像id![image-20230822205631896](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230822205631896.png)

###  CMD与ENTRYPOINT区别

1.  	创建dockerfile-cmd

   ```
   FROM centos
   
   CMD ["ls","-a"]
   ```

2. 构建docker镜像

   ```
   docker build -f dockerfile-cmd -t cmdtest .
   ```

3. 运行镜像

   ```
   docker run 06f2cc65ea4a 
   .
   ..
   .dockerenv
   anaconda-post.log
   bin
   dev
   etc
   home
   lib
   lib64
   media
   mnt
   opt
   proc
   root
   run
   sbin
   srv
   sys
   tmp
   usr
   var
   ```

4. 直接在运行时拼接指令 发现报错

   ```
   [root@master dockerFileCentos]# docker run 06f2cc65ea4a -l
   docker: Error response from daemon: failed to create task for container: failed to create shim task: OCI runtime create failed: runc create failed: unable to start container process: exec: "-l": executable file not found in $PATH: unknown.
   
   ```

5. 问题原因

   ```
   cmd命令下 -l 替换了CMD ["ls","-a"]命令,-l不是命令就报错 
   要使用完整的替换才行docker run 06f2cc65ea4a ls -al
   total 12
   drwxr-xr-x.   1 root root     6 Aug 16 20:51 .
   drwxr-xr-x.   1 root root     6 Aug 16 20:51 ..
   -rwxr-xr-x.   1 root root     0 Aug 16 20:51 .dockerenv
   -rw-r--r--.   1 root root 12114 Nov 13  2020 anaconda-post.log
   lrwxrwxrwx.   1 root root     7 Nov 13  2020 bin -> usr/bin
   drwxr-xr-x.   5 root root   340 Aug 16 20:51 dev
   drwxr-xr-x.   1 root root    66 Aug 16 20:51 etc
   drwxr-xr-x.   2 root root     6 Apr 11  2018 home
   lrwxrwxrwx.   1 root root     7 Nov 13  2020 lib -> usr/lib
   lrwxrwxrwx.   1 root root     9 Nov 13  2020 lib64 -> usr/lib64
   drwxr-xr-x.   2 root root     6 Apr 11  2018 media
   drwxr-xr-x.   2 root root     6 Apr 11  2018 mnt
   drwxr-xr-x.   2 root root     6 Apr 11  2018 opt
   dr-xr-xr-x. 179 root root     0 Aug 16 20:51 proc
   dr-xr-x---.   2 root root   114 Nov 13  2020 root
   drwxr-xr-x.  11 root root   148 Nov 13  2020 run
   lrwxrwxrwx.   1 root root     8 Nov 13  2020 sbin -> usr/sbin
   drwxr-xr-x.   2 root root     6 Apr 11  2018 srv
   dr-xr-xr-x.  13 root root     0 Aug 15 17:34 sys
   drwxrwxrwt.   7 root root   132 Nov 13  2020 tmp
   drwxr-xr-x.  13 root root   155 Nov 13  2020 usr
   drwxr-xr-x.  18 root root   238 Nov 13  2020 var
   
   ```

   



### 构建一个tomcat

1. ### 准备镜像文件tomcat压缩包，jdk压缩包![image-20230822222639153](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230822222639153.png)

2. ### 编写dockerfile文件

   ```
   #官网名称Dockerfile 使用这个不需要-f 指定文件了
   jdk-8u3810linux-aarch64.tar.gz
   apache-tomcat-9.0.79.tar.gz
   
   ------------------------------------------
   
   FROM centos:7
   MAINTAINER enoch<153@163.com>
   
   COPY readme.txt /usr/local/readme.txt
   
   ADD jdk-8u3810linux-aarch64.tar.gz /usr/local
   ADD apache-tomcat-9.0.79.tar.gz /usr/local
   
   RUN yum -y install vim
   
   ENV MYPATH /usr/local
   WORKDIR $MYPATH
   
   ENV JAVA_HOME /usr/local/jdk1.8.0_381
   ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
   ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.79
   ENV CATALINA_BASH /usr/local/apache-tomcat-9.0.79
   ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin
   
   
   EXPOSE 8080
   
   CMD /usr/local/apache-tomcat-9.0.79/bin/startup.sh && tail -F /url/local/apache-tomcat-9.0.79/bin/logs/catalina.out
   			
   ```

3. 运行镜像

   ```
   [root@master dockerFileCentos]# docker run -d -p9090:8080 --name=enochTomcat -v /home/enoch/build/tomcat/test:/url/local/apache-tomcat-9.0.79/webapps/test -v /home/enoch/build/tomcat/logs:/url/local/apache-tomcat-9.0.79/logs c9ac207e29fa
   
   ```

   





## Dokcer网络





## SpringBoot打包成Docker镜像

- 准备springboot项目

- 打包应用

使用maven打包成jar包

- 编写dockerfile

创建一个Dockerfile文件 在jar同级目录

编写下面内容

```
FROM java:8

COPY *.jar /app.jar

CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT["java","-jar","/app.jar"]
```

- 构建镜像

上传到服务器后 使用docker build 命令创建镜像 

- 发布运行

创建镜像后直接docker run即可
