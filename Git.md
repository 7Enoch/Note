

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