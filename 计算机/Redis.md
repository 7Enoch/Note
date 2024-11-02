

## 安装

下载地址https://github.com/tporadowski/redis/releases

## 使用

### 启动服务

windows

```shell
redis-server.exe redis.windows.conf
```

linux

```
redis-server
```

### 启动终端

windows

```
redis-cli.exe -h 127.0.0.1 -p 6379
```

linux

```
redis-cli
```



### 检测Redis服务

```
ping
```

### 远程登录

```
redis-cli -h host -p port -a password
```

## Key



删除键

```
del key
```

序列化

```
dump key
```

检验键

```
exists key
```

设置键过期时间

```
expire key seconds //接受单位为s
expireat key timestamp//接受单位时间参数是 UNIX 时间戳
rexpire key milliseconds //毫s
rexpire key milliseconds-timestamp //毫s
```

移除过期时间

```
persist key
```

返回键的类型

```
type key
```

移动键

```
move key db
```

随机返回键

```
randomkey
```

返回模糊匹配的键

```
keys key*

例
127.0.0.1:6379> keys enoch*
1) "enoch"
2) "enoch1"
```

返回全部键

```
keys *
```



## String

设置String

```
set key value
```

获取String

```
get key
```

获取String指定位置的字符串

```
getrange key start end

例子
enoch "10201;;"
127.0.0.1:6379> getrange enoch 1 2
"02"
```

设置键的新值，返回旧值

```
getset key value

```

