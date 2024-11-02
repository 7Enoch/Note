## Git

### 初始化仓库

```
//初始化仓库
git init
```



### 拉取仓库文件

```
git pull 本地设置的远程仓库名称(一般是origin) 设置本次本地分支名称
```

### 提交到远程仓库

```
git push 本地设置的远程仓库名称（一般是origin） 远程分支名称
```





### 添加文件

```
//添加全部文件
git add .
```



### 提交文件

```
git commit -m "提交文件的描述"
```



### 查看工作目录提交状态

```
git status
```

### 分支

#### 查看分支

```
//查看本地分支
git branch
//查看远程分支
git branch -r
//查看全部分支
git branch -a
//查看分支关联情况
git branch -vv
```

#### 新建/切换分支

```
//切换本地分支
git checkout 本地分支名
//创建并切换本地分支
git checkout -b 新分支名
//创建远程分支
git push 本地设置的远程仓库名称(一般是origin) 本地分支名:新创建的远程分支名
git push 本地设置的远程仓库名称(一般是origin) 分支名(在远程需要创建的本地分支名一样)
```

#### 关联分支

```
//本地分支关联远程分支
git branch --set-upstream-to=本地设置的远程仓库名称(一般是origin)/远程分支名称
```

#### 删除分支

```
//删除本地分支
git branch -d 本地分支名称
//删除远程分支
git push 本地设置的远程仓库名称(一般是origin) --delete 远程分支名称
//推送一个空分支给远程分支，就相当于删除分支
git push origin :远程分支名称
//删除克隆仓库时的HEAD分支 remotes/origin/HEAD
git remote set-head 本地设置的远程仓库名称(一般是origin) -d
```

#### 重命名分支

```
//重命名本地分支
git branch -m 
```

### 仓库

#### 查看仓库

```
//查看本地仓库名称
git remote 
//查看远程仓库名称地址
git remote -v
```

#### 添加仓库

```shell
git remote add 设置本地看见的远程仓库名称(一般是origin) 远程仓库地址链接
//git remote add origin git@github.com:yourName/repositoryname.git
//git remote add origin https://github.com/yourName/repositoryname.git
```

#### 克隆仓库

```
git clone 远程仓库地址链接
```



#### 删除仓库

```
//删除远程仓库
git remote rm （本地设置的远程仓库名称）origin

```

