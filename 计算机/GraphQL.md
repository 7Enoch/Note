# GraphQL基本理论

## GraphQL基本概念

1. GraphQL查询语言 

	GraphQL是一种用于API的查询语言，允许客户端精确描述需要从服务器获取的数据，它强调的是强类型，可预测的结构结果和高效的网络通信
	
1. Schema(模式)
	Schema是GraphQL服务的核心。定义了服务所能提供的数据类型(Type)以及这些类型之间的关系。Schema定义了客户端可以查询的根类型(通常是Query和Muration)以及他们的字段，下面是一个示例	
    ```
	type Query {
    book(id: ID!): Book
	}
	
	type Mutation {
    addBook(title: String!, author: String!): Book
	}
	
	type Book {
    id: ID!
    title: String!
    author: Author!
	}
	
	type Author {
    name: String!
	}
	 ```
1. Resolver(解析器)
Resolver是实现数据获取逻辑的函数。对于Schema中的每一个字段，都需要一个对应的Resolver来决定如何获取或计算该字段的值。Resolver接收查询上下文和可能的参数，返回字段的值







# Spring Boot整合GraphQL

maven依赖 ：高版本SpringBoot暂时无法构建

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.enoch</groupId>
    <artifactId>GraphQL</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>GraphQL</name>
    <description>GraphQL</description>
    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.graphql-java-kickstart</groupId>
            <artifactId>graphql-spring-boot-starter</artifactId>
            <version>15.1.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.graphql-java-kickstart/graphql-java-tools -->
        <dependency>
            <groupId>com.graphql-java-kickstart</groupId>
            <artifactId>graphql-java-tools</artifactId>
            <version>13.1.1</version>
        </dependency>

        <!-- GraphiQL tool -->
        <dependency>
            <groupId>com.graphql-java-kickstart</groupId>
            <artifactId>graphiql-spring-boot-starter</artifactId>
            <version>11.1.0</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

yml配置

```yaml
#Server
server:
  port: 8080
#GraphQL
graphql:
  servlet:
    mapping: /graphql
    enabled: true
    corsEnabled: true
    # if you want to @ExceptionHandler annotation for custom GraphQLErrors
    exception-handlers-enabled: true
    contextSetting: PER_REQUEST_WITH_INSTRUMENTATION
  tools:
    schema-location-pattern: "**/*.graphqls"
    # Enable or disable the introspection query. Disabling it puts your server in contravention of the GraphQL
    # specification and expectations of most clients, so use this option with caution

#GraphiQL Tool
graphiql:
  mapping: /graphiql
  endpoint:
    graphql: /graphql
  subscriptions:
    timeout: 30
    reconnect: false
  static:
    basePath: /
  enabled: true
  pageTitle: GraphiQL
  props:
    resources:
      query: /graphql/types.graphqls
      variables: /graphql/types.graphqls
    variables:
      editorTheme: "solarized light"
  headers:
    Authorization: "Bearer <your-token>"

```




# 构建GraphQL Schema



# 数据绑定与Spring Data Integration



# 高级主题与最佳实践