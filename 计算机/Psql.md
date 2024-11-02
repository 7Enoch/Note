# 命令行操作

### 登录

```shell
psql   -U postgres
```

这里必须加上U否则默认的用户是不存在的 必须使用postgres默认登录

## 数据库操作

### 创建数据库

```sql
create database  数据库名称
```

### 选择数据库

```postgresql
\c 数据库名称
```

### 查看数据库列表

```postgresql
\l
```

### 删除数据库

```sql
DROP DATABASE 数据库名称
```

## 表操作

### 创建表

```postgresql
create table 表名称 (
字段名  类型   索引  空值设置
。。。
);
```

### 查看表列表

```postgresql
\d
\dt
```

### 查看表详细

```postgresql
\d 表名称
```

### 删除表名称

```
drop table 表名称
```

## 模式

在 PostgreSQL 中，模式（Schema）可以被理解为一种将数据库对象组织起来的方式，它实际上是一个命名空间，可以将相关的数据库对象放在一起。每个模式都可以包含表、视图、函数、触发器等数据库对象。

将模式看作是一个表的集合是一个合理的比喻。想象你有一个大的数据库，其中包含了多个不同的应用程序或者模块，每个应用程序或者模块都需要使用一些表、视图等。使用模式，你可以将这些相关的表、视图等组织到一个模式下面，从而更好地组织和管理数据库对象，避免名称冲突，并且在数据库中更清晰地表达出数据的结构和逻辑关系。

举例来说，你可以为一个电子商务网站创建一个名为 "ecommerce" 的模式，其中包含了与电子商务相关的所有表、视图、函数等。另外，你还可以为一个博客系统创建一个名为 "blog" 的模式，其中包含了博客相关的数据库对象。这样做有助于将不同的应用或者模块隔离开，使得数据库更加模块化、可维护，并且更容易理解。

总而言之，PostgreSQL 中的模式是一种组织和管理数据库对象的方式，将相关的对象分组存放，从而提供更好的结构化和命名空间隔离。



创建模式 下面存放表 视图 函数 触发器 一个项目里使用一个模式很直观



### 创建模式

```
create schema 模式名称; //只创建模式
```

### 在模式中创建表

```
create schema 模式名称.表名称(可选) //在创建模式的时候同时创建表
```

### 查看模式列表

```
\dn
```

### 删除模式

```
drop schema 模式名称; //模式下面存在表 视图等等不能删除
drop schema 模式名称 cascade;  //将模式下面的对象全部删除
```

## 插入

#### 插入记录

```
insert into 表名称 (字段1，字段2，字段3...)(可选) values(值1，值2，值3),(值1，值2，值3);
//若插入的值跟表字段数一样 可忽略表字段
```

### 删除

#### 删除一条记录

```
delete from 表名 where 条件;
```



### 更改

#### 更新数据

```
update 表名 set 字段1=值1，字段2=值2，字段3=值3 where 条件
```



## 查询

### 查询全部

```
select * from 表名称;
```

### 表达式查询

```
select 列与列的数学表达式 as 别名 from 表;
//将列与列的表达式结果 命名成别名 在打印表的时候就显示这个别名

```

### 查询当前时间

```
 SELECT CURRENT_TIMESTAMP;
 //current_timestamp
```

### 条件查询



#### AS

命令用于用别名重命名列或表。

#### WHERE查询

### 

以下是 SELECT 语句中使用 WHERE 子句从数据库中读取数据的通用语法：

```
SELECT 字段1，字段2，字段3
FROM table_name
WHERE 条件
```

我们可以在 WHERE 子句中使用比较运算符或逻辑运算符，例如 **>, <, =, LIKE, NOT** 等等。

```
runoobdb# select * from COMPANY;
 id | name  | age | address   | salary
----+-------+-----+-----------+--------
  1 | Paul  |  32 | California|  20000
  2 | Allen |  25 | Texas     |  15000
  3 | Teddy |  23 | Norway    |  20000
  4 | Mark  |  25 | Rich-Mond |  65000
  5 | David |  27 | Texas     |  85000
  6 | Kim   |  22 | South-Hall|  45000
  7 | James |  24 | Houston   |  10000
(7 rows)
```

以下几个实例我们使用逻辑运算符来读取表中的数据。

#### AND

找出 **AGE(年龄)** 字段大于等于 25，并且 **SALARY(薪资)** 字段大于等于 65000 的数据：

```
runoobdb=# SELECT * FROM COMPANY WHERE AGE >= 25 AND SALARY >= 65000;
 id | name  | age |  address   | salary
----+-------+-----+------------+--------
  4 | Mark  |  25 | Rich-Mond  |  65000
  5 | David |  27 | Texas      |  85000
(2 rows)
```

#### OR

找出 **AGE(年龄)** 字段大于等于 25，或者 **SALARY(薪资)** 字段大于等于 65000 的数据：

```
runoobdb=# SELECT * FROM COMPANY WHERE AGE >= 25 OR SALARY >= 65000;
id | name  | age | address     | salary
----+-------+-----+-------------+--------
  1 | Paul  |  32 | California  |  20000
  2 | Allen |  25 | Texas       |  15000
  4 | Mark  |  25 | Rich-Mond   |  65000
  5 | David |  27 | Texas       |  85000
(4 rows)
```

#### NOT NULL

在公司表中找出 **AGE(年龄)** 字段不为空的记录：

```
runoobdb=#  SELECT * FROM COMPANY WHERE AGE IS NOT NULL;
  id | name  | age | address    | salary
 ----+-------+-----+------------+--------
   1 | Paul  |  32 | California |  20000
   2 | Allen |  25 | Texas      |  15000
   3 | Teddy |  23 | Norway     |  20000
   4 | Mark  |  25 | Rich-Mond  |  65000
   5 | David |  27 | Texas      |  85000
   6 | Kim   |  22 | South-Hall |  45000
   7 | James |  24 | Houston    |  10000
(7 rows)
```

#### LIKE

在 COMPANY 表中找出 **NAME(名字)** 字段中以 Pa 开头的的数据：

```
runoobdb=# SELECT * FROM COMPANY WHERE NAME LIKE 'Pa%';
id | name | age |address    | salary
----+------+-----+-----------+--------
  1 | Paul |  32 | California|  20000
```

#### IN

以下 SELECT 语句列出了 **AGE(年龄)** 字段为 25 或 27 的数据：

```
runoobdb=# SELECT * FROM COMPANY WHERE AGE IN ( 25, 27 );
 id | name  | age | address    | salary
----+-------+-----+------------+--------
  2 | Allen |  25 | Texas      |  15000
  4 | Mark  |  25 | Rich-Mond  |  65000
  5 | David |  27 | Texas      |  85000
(3 rows)
```

#### NOT IN

以下 SELECT 语句列出了 **AGE(年龄)** 字段不为 25 或 27 的数据：

```
runoobdb=# SELECT * FROM COMPANY WHERE AGE NOT IN ( 25, 27 );
 id | name  | age | address    | salary
----+-------+-----+------------+--------
  1 | Paul  |  32 | California |  20000
  3 | Teddy |  23 | Norway     |  20000
  6 | Kim   |  22 | South-Hall |  45000
  7 | James |  24 | Houston    |  10000
(4 rows)
```

#### BETWEEN

between

以下 SELECT 语句列出了 **AGE(年龄)** 字段在 25 到 27 的数据：

```
runoobdb=# SELECT * FROM COMPANY WHERE AGE BETWEEN 25 AND 27;
 id | name  | age | address    | salary
----+-------+-----+------------+--------
  2 | Allen |  25 | Texas      |  15000
  4 | Mark  |  25 | Rich-Mond  |  65000
  5 | David |  27 | Texas      |  85000
(3 rows)
```

#### ORDER BY

在 PostgreSQL 中，**ORDER BY** 用于对一列或者多列数据进行`升序ASC`或者`降序DESC`排列。

**ORDER BY** 子句的基础语法如下：

```sql
SELECT column-list
FROM table_name
[WHERE condition]
[ORDER BY 字段1, 字段2, .. 字段N] [ASC | DESC];
```

#### GROUP BY

在 PostgreSQL 中，**GROUP BY** 语句和 SELECT 语句一起使用，用来对`相同的数据`进行`分组`。

GROUP BY 在一个 SELECT 语句中，放在 WHRER 子句的后面，ORDER BY 子句的前面。



一张表若存在所有工资组成 就可以使用该表达式 

比如一个名为小明的员工 它存在基本工资3000 加班工资1500 那么就有一张表

| 姓名 | 工资类型        | 工资 |
| ---- | --------------- | ---- |
| 小明 | 1(代表基本工资) | 3000 |
| 小明 | 2(代表加班工资) | 1500 |

那么就可以使用 这里的group by

select sum(工资),姓名 where 姓名=小明 group by name;

结果就是

| sum  | 姓名 |
| ---- | ---- |
| 4500 | 小明 |



下面给出了 GROUP BY 子句的基本语法:

```sql
SELECT column-list
FROM table_name
WHERE [ conditions ]
GROUP BY column1, column2....columnN
ORDER BY column1, column2....columnN
```

GROUP BY 子句必须放在 WHERE 子句中的条件之后，必须放在 ORDER BY 子句之前。

在 GROUP BY 子句中，你可以对一列或者多列进行分组，但是被分组的列必须存在于列清单中。

#### WITH 子句

WITH 子句提供了一种编写辅助语句的方法，以便在更大的查询中使用。

WITH 子句有助于将复杂的大型查询分解为更简单的表单，便于阅读。这些语句通常称为通用表表达式（Common Table Express， CTE），也可以当做一个为查询而存在的临时表。

WITH 子句是在多次执行子查询时特别有用，允许我们在查询中通过它的名称(可能是多次)引用它。

WITH 子句在使用前必须先定义。

WITH 查询的基础语法如下：

```
WITH
   name_for_summary_data AS (
      SELECT Statement)
   SELECT columns
   FROM name_for_summary_data
   WHERE conditions <=> (
      SELECT column
      FROM name_for_summary_data)
   [ORDER BY columns]
```

**name_for_summary_data** 是 WITH 子句的名称，**name_for_summary_data** 可以与现有的表名相同，并且具有优先级。

可以在 WITH 中使用数据 INSERT, UPDATE 或 DELETE 语句，允许您在同一个查询中执行多个不同的操作。

##### WITH 递归

在 WITH 子句中可以使用自身输出的数据。

公用表表达式 (CTE) 具有一个重要的优点，那就是能够引用其自身，从而创建递归 CTE。递归 CTE 是一个重复执行初始 CTE 以返回数据子集直到获取完整结果集的公用表表达式。

下面将使用 WITH 子句在上表中查询数据：

```sql
With CTE AS
(Select
 ID
, NAME
, AGE
, ADDRESS
, SALARY
FROM COMPANY )
Select * From CTE;
```

得到结果如下：

```sql
id | name  | age | address   | salary
----+-------+-----+-----------+--------
  1 | Paul  |  32 | California|  20000
  2 | Allen |  25 | Texas     |  15000
  3 | Teddy |  23 | Norway    |  20000
  4 | Mark  |  25 | Rich-Mond |  65000
  5 | David |  27 | Texas     |  85000
  6 | Kim   |  22 | South-Hall|  45000
  7 | James |  24 | Houston   |  10000
(7 rows)
```

接下来让我们使用 **RECURSIVE** 关键字和 WITH 子句编写一个查询，查找 **SALARY(工资)** 字段小于 20000 的数据并计算它们的和：

```
WITH RECURSIVE t(n) AS (
   VALUES (0)
   UNION ALL
   SELECT SALARY FROM COMPANY WHERE SALARY < 20000
)
SELECT sum(n) FROM t;
```

####  DISTINCT 关键字

在 PostgreSQL 中，DISTINCT 关键字与 SELECT 语句一起使用，用于去除重复记录，只获取唯一的记录。

我们平时在操作数据时，有可能出现一种情况，在一个表中有多个重复的记录，当提取这样的记录时，DISTINCT 关键字就显得特别有意义，它只获取唯一一次记录，而不是获取重复记录。



用于去除重复记录的 DISTINCT 关键字的基本语法如下：

```
SELECT DISTINCT column1, column2,.....columnN
FROM table_name
WHERE [condition]
```

```
runoobdb# select * from COMPANY;
 id | name  | age | address   | salary
----+-------+-----+-----------+--------
  1 | Paul  |  32 | California|  20000
  2 | Allen |  25 | Texas     |  15000
  3 | Teddy |  23 | Norway    |  20000
  4 | Mark  |  25 | Rich-Mond |  65000
  5 | David |  27 | Texas     |  85000
  6 | Kim   |  22 | South-Hall|  45000
  7 | James |  24 | Houston   |  10000
(7 rows)
```

让我们插入两条数据：

```
INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
VALUES (8, 'Paul', 32, 'California', 20000.00 );

INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
VALUES (9, 'Allen', 25, 'Texas', 15000.00 );
```

现在数据如下：

```
 id | name  | age | address    | salary
----+-------+-----+------------+--------
  1 | Paul  |  32 | California |  20000
  2 | Allen |  25 | Texas      |  15000
  3 | Teddy |  23 | Norway     |  20000
  4 | Mark  |  25 | Rich-Mond  |  65000
  5 | David |  27 | Texas      |  85000
  6 | Kim   |  22 | South-Hall |  45000
  7 | James |  24 | Houston    |  10000
  8 | Paul  |  32 | California |  20000
  9 | Allen |  25 | Texas      |  15000
(9 rows)
```

接下来我们找出 COMPANY 表中的所有 NAME：

```
runoobdb=# SELECT name FROM COMPANY;
```

得到结果如下：

```
 name
-------
 Paul
 Allen
 Teddy
 Mark
 David
 Kim
 James
 Paul
 Allen
(9 rows)
```

现在我们在 SELECT 语句中使用 DISTINCT 子句：

```
runoobdb=# SELECT DISTINCT name FROM COMPANY;
```

得到结果如下：

```
name
-------
 Teddy
 Paul
 Mark
 David
 Allen
 Kim
 James
(7 rows)
```

从结果可以看到，重复数据已经被过滤。

### 子查询

以下的 SELECT 语句使用了 SQL 的子查询，子查询语句中读取 **SALARY(薪资)** 字段大于 65000 的数据，然后通过 **EXISTS** 运算符判断它是否返回行，如果有返回行则读取所有的 **AGE(年龄)** 字段。

```
runoobdb=# SELECT AGE FROM COMPANY
        WHERE EXISTS (SELECT AGE FROM COMPANY WHERE SALARY > 65000);
 age
-----
  32
  25
  23
  25
  27
  22
  24
(7 rows)
```

以下的 SELECT 语句同样使用了 SQL 的子查询，子查询语句中读取 **SALARY(薪资)** 字段大于 65000 的 **AGE(年龄)** 字段数据，然后用 **>** 运算符查询大于该 **AGE(年龄)** 字段数据：

```
runoobdb=# SELECT * FROM COMPANY
        WHERE AGE > (SELECT AGE FROM COMPANY WHERE SALARY > 65000);
 id | name | age | address    | salary
----+------+-----+------------+--------
  1 | Paul |  32 | California |  20000
```

### 模糊查询

```
select *from 表名 where 行名 like 'XXXX';
```

#### 通配符

##### %百分号

通配占位的全部元素

比如 

%2就通配末尾是2的所有元素 xxx2 xx2 x2 都能匹配

2%就通配开头是2的所有元素

%2%就通配包含2的所有元素

##### _下划线

占一个位置

比如

_2就通配2前面存在的一个字符的元素 x2 z2 e2

2_就通配2后面存在的一个字符的元素 2x 2f 2g

 _ 2 _就通配2前面后面都存在的一个字符的元素

### 分页查询

第一种用法

```sql
select * from 表名 limit x 
//从第一条开始 读取四条 包括第一条

```

下面实例将找出限定的数量的数据，即读取 4 条数据：

```sql
SELECT * FROM COMPANY LIMIT 4;
```

第二种用法

```sql
select select * from 表名 limit x offset y 
//从第x条开始 读取y条 不包括第x条
```

下面是一个实例，从第三位开始提取 3 个记录：

```sql
SELECT * FROM COMPANY LIMIT 3 OFFSET 2;
```









## 运算符

### 算数运算符

假设变量 a 为 2，变量 b 为 3，则：

| 运算符 |    描述    |        实例         |
| :----: | :--------: | :-----------------: |
|   +    |     加     |   a + b 结果为 5    |
|   -    |     减     |   a - b 结果为 -1   |
|   *    |     乘     |   a * b 结果为 6    |
|   /    |     除     |   b / a 结果为 1    |
|   %    | 模（取余） |   b % a 结果为 1    |
|   ^    |    指数    |   a ^ b 结果为 8    |
|  \|/   |   平方根   |  \|/ 25.0 结果为 5  |
| \|\|/  |   立方根   | \|\|/ 27.0 结果为 3 |

### 比较运算符

假设变量 a 为 10，变量 b 为 20，则：

| 运算符 |   描述   |        实例         |
| :----: | :------: | :-----------------: |
|   =    |   等于   | (a = b) 为 false。  |
|   !=   |  不等于  | (a != b) 为 true。  |
|   <>   |  不等于  | (a <> b) 为 true。  |
|   >    |   大于   | (a > b) 为 false。  |
|   <    |   小于   |  (a < b) 为 true。  |
|   >=   | 大于等于 | (a >= b) 为 false。 |
|   <=   | 小于等于 | (a <= b) 为 true。  |