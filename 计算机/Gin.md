# GoWeb开发之Gin框架



## Gin简介

###  介绍

- Gin是一个golang的微框架，封装比较优雅，API友好，源码注释比较明确，具有快速灵活，容错方便等特点

- 对于golang而言，web框架的依赖要远比Python，Java之类的要小。自身的`net/http`足够简单，性能也非常不错

- 借助框架开发，不仅可以省去很多常用的封装带来的时间，也有助于团队的编码风格和形成规范

- 官网https://gin-gonic.com/zh-cn/

  

### 安装

安装命令 go get 本质是git -clone

go.mod 文件就是用来保存包结构的类似maven



使用Mod依赖管理工具，在项目根目录执行 ：

```go
go mod init <项目名称>(在根目录运行就不需要)

```

然后项目根目录下会生成 go.mod文件，修改文件添加gin相关依赖

```go
go env -w GOPROXY=https://goproxy.cn,direct //修改代理 保证下面命令不报错
```

```go
go get -u github.com/gin-gonic/gin//拉取依赖
```

在go.mod 项目寻找依赖 如果爆红 删除require 清除缓存重新运行上面代码

```mod
require (
	github.com/bytedance/sonic v1.10.0-rc3 // indirect
	....
	)
```

运行项目时会自动下载相关依赖。



## Hello

要做测试 我们需要一个访问地址 一个服务器端口

### 创建Web服务

```go
//创建服务
ginServer := gin.Default()	
```

### 增加功能

```go
//增加Server环境  需要导入包
ginServer.Use(favicon.New("./favicon.ico"))
```

### 设置请求

```go
//在go中直接调用对应方法就可以了 
ginServer.请求方法("/请求路径", func(context *gin.Context) {
    //这里是回调函数 请求后的操作 可以返回值 也可以返回页面
    context.JSON(200, gin.H{"get": "hello"})
})
```

```go
//处理请求 Request Response 
ginServer.GET("/get", func(context *gin.Context) {
    context.JSON(200, gin.H{"get": "hello"})
})
ginServer.POST("/post", func(c *gin.Context) {
    c.JSON(200, gin.H{"post": "hello"})
})
ginServer.PUT("/put", func(c *gin.Context) {
    c.JSON(200, gin.H{"put": "hello"})
})
ginServer.DELETE("/delete", func(c *gin.Context) {
    c.JSON(200, gin.H{"delete": "hello"})
})
```

### 设置服务器端口

```go
//服务器端口
err := ginServer.Run(":8082")
if err != nil {
    return
}
```

### 返回数据



#### 返回JSON数据

在请求方法的回调函数中调用JSON方法即可

```go
ginServer.GET("/get", func(context *gin.Context) {
    context.JSON(200, gin.H{"get": "hello"})
})
```

#### 返回静态页面

首先需要设置静态页面文件夹

```go
ginServer.LoadHTMLGlob("html文件夹/*") //这里的*代表文件夹下面的所有文件
```

如果要在加载的html中使用js css img还需要 ==加载静态资源==

```go
ginServer.Static("资源文件夹","根目录")
```

在请求方法的回调函数中调用HTML方法即可

```go
ginServer.请求方法("/请求路径", func(context *gin.Context) {
    context.HTML(状态码, "html文件名",返回的数据)
})
```

```go
ginServer.GET("/index", func(context *gin.Context) {
    context.HTML(http.StatusOK, "index.html", gin.H{"get": "hello"})
})
```

### 获得url参数数据/get请求

#### 

- 拼接风格获取

```
/请求?username=xxx&password=xxx
```

```go
ginServer.GET("/请求", func(context *gin.Context) {
    username := context.Query("username")
    password :=  context.Query("password")
    context.JSON(200,gin.H{
        "username":username,
        "password":password,
    })
})
```



- RestFul风格获取

```
/请求/xxx/xxx
```

```go
ginServer.GET("/请求/:username/:pasword", func(context *gin.Context) {
    username := context.Param("username")
    password := context.Param("password")
    context.JSON(200, gin.H{
        "username": username,
        "password": password,
    })
})
```

### 获得前端JSON数据/post请求

```go
ginServer.POST("/请求", func(context *gin.Context) {
    username := context.GetRawData("username")

    context.JSON(200, gin.H{
        "username": username,
        "password": password,
    })
})
```





### go语言的服务打包

在运行go程序的时候 go会构建一个exe文件到 

C:\Users\G\AppData\Local\JetBrains\IntelliJIdea2023.1\tmp\GoLand 文件夹 

要想使用直接点击==运行==一个==服务就开启==了

## Jsoniter



### 使用 [jsoniter](https://github.com/json-iterator/go) 编译

Gin 使用 `encoding/json` 作为默认的 json 包，但是你可以在编译中使用标签将其修改为 [jsoniter](https://github.com/json-iterator/go)。

```sh
$ go build -tags=jsoniter .
```