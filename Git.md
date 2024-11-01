

git clone 克隆项目

git config user.name 设置名称

git config user.email 设置邮箱

git config --global http.proxy http://127.0.0.1:7890 设置http代理

git config --global http.proxy https://127.0.0.1:7890设置https代理

git branch -r 查看本地分支

当前是在main分支

git branch -vv查看本地分支跟远程分支的关联

git checkout -b test origin/test 创建test仓库 并跟远程仓库的test关联

git checkout test 切换到test分支

git pull origin test 将远程的test分支更新到当前本地分支

git push origin test 将当前本地的分支更新到远程test分支

git pull将远程分支更新到与本地对应的分支

git push将本地分支推送到远程对应的分支

git add . 添加所有文件到暂存区

git commit -m "备注"  提交文件到本地仓库





基本操作

git clone 一个仓库过后 先绑定分支 

//远程有两个分支 一个是main 一个是develop分支

git checkout -b develop origin/develop 创建并绑定分支 （验证）*

git branch 查看本地分支

git branch -vv 查看本地与远程分支的对应关系

git checkout <本地分支名称> 切换分支

git pull origin <当前所在分支>

//写代码 更新文件

git add . 将文件修改状态添加到暂存区域

git commit -m "修改Git笔记" //将暂存区域的文件添加到本地仓库





