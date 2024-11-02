## 数据库介绍

### mysql的登录

使用bin目录下的mysql.exe来连接mysql数据库服务器

```mysql
mysql -u username -p
```

输入密码即可登录成功

## 关于数据库的的操作

#### 查看数据库

```mysql
show databases;
```

mysql> show databases;
+-------------------------+
| Database                    |
+-------------------------+
| information_schema  |
| mysql                         |
| performance_schema |
| sys                              |
+--------------------------+

自带四个数据库

#### 使用数据库

```mysql
use databasename;
```

#### 创建数据库

```mysql
create database databasename;
```



## 关于表的操作

#### 查看数据库下的所有表（先使用数据库）

```
show tables;
```

#### 导入表（先使用数据库）

```mysql
source sql文件路径
```

#### 查看表中数据

```sql
select * from 表名;
```

#### 查看表结构

```sql
desc 表名;
```



----

## DQL

### 简单查询

#### 只查询一个字段

​	select 字段 from 表名;

​		select from都是关键字;

​			字段 表名都是标识符

#### 查询多个字段

select 字段,字段 from 表名;

## Mybatis

### 插入语句返回需要的字段



我们平常在多表插入的时候 往往要用一个请求体值 去两张表中做插入操作

一般来说 必须要有一张主表先插入 其中主表的某个字段是其他表的外键 所以在执行完主表的插入后我们需要拿到主表的插入行 这里我们使用mybatis的插入语句

```xml
<insert id="addDish" keyProperty="id" useGeneratedKeys="true">
    insert into dish (name, category_id, price, image, description, status, create_time, update_time, create_user,
    update_user)
    values (#{dish.name}, #{dish.categoryId}, #{dish.price}, #{dish.image}, #{dish.description}, #{dish.status},
    \#{dish.createTime}, #{dish.updateTime}, #{dish.createUser}, #{dish.updateUser})
</insert>
```

​	`keyProperty`="id"  则表示将自动生成的键值存储在 `id` 属性中。你可以根据你的数据表来替换 `id` 为相应的主键属性。

` useGeneratedKeys`="true"  表示启用自动生成的键值
