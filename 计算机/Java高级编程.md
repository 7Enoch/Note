

# Lambda表达式

### 概述

语法糖，简化匿名内部类的使用，函数式编程的重要一部分，不关注对象，只关注数据

### 核心原则

可推导可省略（接口并只有一个抽象方法）

### 基本格式

(数据/参数列表)->{代码块}



### 省略规则

参数类型可以忽略

方法体只有一句话时，大括号跟return和唯一一句代码的分号可以省略

方法只有一个参数时小括号可以省略

### 学习心得

关于参数中加入接口的好处

接口Test，里面存在一个test方法

```java
interface Test{
	int test(int a,int b);
}
```

类Calculate，里面存在一个方法，对两个参数进行操作

```java
class Calculate{
	int operation（int a,int b, Test t）{
		t.test(a,b);
	}
}
```

Main方法调用

```java
main{
	    System.out.println( operation(1, 2, (a, b) -> a + b));
        System.out.println( operation(1, 2, (a, b) -> a - b));
        System.out.println( operation(1, 2, (a, b) -> a * b));
        System.out.println( operation(1, 2, (a, b) -> a / b));
}
```

传入的接口(a, b) -> a + b) 决定了前面两个参数的行为，即将硬编码变成了软编码，不需要在Calculate类里面直接更改，只需要在客户端手动操作需要的功能编码;



# Stream流

Stream使用的是函数式编程模式，可以对集合数组进行链状流式的操作，可以方便让我们对集合和数组进行操作

###  入门

我们需要调用getAuthors获取到作者的集合。现在需要打印所有年龄小于18的作家名称，并且要去重。

```java
        //打印小于18岁的作家-普通做法
        List<Author> authorList = getAuthor();
        Author author1 = new Author();
        for (Author author : authorList) {
            if (author.getAge() < 18 && !author.equals(author1)) {
                System.out.println(author.getName());
            }
            author1 = author;
        }
        //打印小于18岁的作家-stream流做法
        authorList
        //创建流
        .stream()
        //去掉重复的作家
        .distinct()
        //过滤年龄小于18的
        .filter(author -> author.getAge() < 18)
        //打印结果
        .forEach(author -> System.out.println(author.getName()));
```

### 常用操作

#### 创建流

要将集合跟数组转化为stream流

单列集合：==集合对象.stream()==

```java
        List<Author> authors = getAuthors();
	    Stream<Author> stream = authors.stream();
```

数组：==Arrays.stream(数组)或者Stream.of(数组)==

```java
        Integer[] arr = {1, 2, 3};
        Stream<Integer> stream1 = Arrays.stream(arr);
        Stream<Integer> stream2 = Stream.of(arr);
```

双列集合：==集合.entrySet.stream()==

```java
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        Stream<Map.Entry<String, Integer>> stream3 = map.entrySet().stream();
        
        
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Stream stream5 = entries.stream();
```

#### 中间操作

##### filter

对流中的条件进行过滤，符合条件的留在流中

```java
list.stream()//获取流
	.filter(author->author.getName().length()>1)//过滤author中名字长度大于1的元素
	.forEach(author->System.out.print(author));//打印流中的元素
```

##### map

把流中元素进行计算或转换

```java
//z转换
authorList.stream()//获得流
		 .map(author -> author.age)
		 .forEach(age -> System.out.println(age)
//计算
authorList.stream()//获得流
		.map(author -> author.age)
		.map(age -> age + 1)
		.forEach(age -> System.out.println(age));
```

##### distinct

去除流中的重复元素

```java
        authorList.stream()//获得流
                .distinct()
                .forEach(author -> System.out.println(author));
```

需要重写equals

##### sorted

对流中的元素进行排序

```java
        authorList.stream()//获得流
                .sorted((o1, o2) -> {
                    if (o1.age == o2.age)
                        return 0;
                    return o1.age > o2.getAge() ? -1 : 1;
                })
                .forEach(author -> System.out.println(author));
```

需要让流的元素实现了比较器接口的

##### limit

保留流的最大长度，超出部分将被抛弃

```java
        authorList.stream()//获得流
                .distinct()
                .limit(2)//只保留流中前两个元素
                .forEach(author -> System.out.println(author));
```



##### skip

跳过流中的前n个元素，返回剩下的元素

```java
 authorList.stream()//获得流
                .distinct()
                .skip(2)
                .forEach(author -> System.out.println(author));
```



##### flatMap

将一个对象转化为多个对象作为流中的元素

```java
      authorList.stream()//获得流
                .distinct()
                .flatMap(author -> author.getBooks().stream())//获得一个List<Book>类型
                .distinct()
                .forEach(book -> System.out.println(book));
	//转化为多个对象
        authorList.stream()//获得流
                .distinct()
                .flatMap(author -> author.getBooks().stream())//获得一个List<Book>类型
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))//获得String数组
                .distinct()
                .forEach(category -> System.out.println(category));
```



#### 终结操作

##### forEach

循环打印流对象

##### count

打印流对象个数

##### max&min

求流当中的最值

```java
        System.out.println(authorList.stream()//获得流
                .distinct()
                .flatMap(author -> author.getBooks().stream())//获得一个List<Book>类型
                .map(book -> book.getScore())
                .distinct()
                .min((o1, o2) -> o1 - o2).get());

        System.out.println(authorList.stream()//获得流
                .distinct()
                .flatMap(author -> author.getBooks().stream())//获得一个List<Book>类型
                .map(book -> book.getScore())
                .distinct()
                .max((o1, o2) -> o1 - o2).get());
```



##### collect

将流转化为集合

转化为List

```java
		//获取作家名字List
        List<String> collect = authorList.stream()//获得流
                .distinct()
                .flatMap(author -> Stream.of(author.getName()))
                .distinct()
                .collect(Collectors.toList());
        collect.stream()
                .forEach(System.out::println);
```

转化为Set

```java
  //获取书名的Set集合
        Set<Book> set = authorList.stream()//获得流
                .distinct()
                .flatMap(author -> author.getBooks().stream())//获得书籍
                .distinct()
                .collect(Collectors.toSet());
        set.stream()
                .distinct()
                .forEach(book -> System.out.println(book.name));
```

转化为Map

```java
  Map<Object, List<Book>> map = authorList.stream()
                .collect(Collectors.toMap(new Function<Author, Object>() {
                    @Override
                    public Object apply(Author author) {
                        return author.getName();//key
                    }
                }, new Function<Author, List<Book>>() {
                    @Override
                    public List<Book> apply(Author author) {
                        return author.getBooks();//value
                    }
                }));    
        map.entrySet().stream()
                .distinct()
                .forEach(result -> System.out.println(result));
 //lambda表达式
                        //获取一个Map集合，key为作者名 value为List<Book>
        Map<Object, List<Book>> map = authorList.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()//value
                ));
        map.entrySet().stream()
                .distinct()
                .forEach(result -> System.out.println(result));
```

# 注解&反射



## 注解



元注解：注解的注解（自定义注解就要使用元注解来定义）

`@Target`

定义：@Target 用于指定被其注解的注解可以应用于哪些类型的程序元素。它通过 ElementType 枚举值来限定注解的使用范围，如类、方法、字段、参数等。

ElementType 

```
TYPE：表示类、接口（包括注解类型）或枚举声明。
FIELD：表示字段声明（包括枚举常量）。
METHOD：表示方法声明。
PARAMETER：表示形式参数声明。
CONSTRUCTOR：表示构造函数声明。
LOCAL_VARIABLE：表示局部变量声明。
ANNOTATION_TYPE：表示注解类型声明。
PACKAGE：表示包声明。
TYPE_PARAMETER（自 Java 1.8 起新增）：表示类型参数声明。 注解参数使用
TYPE_USE（自 Java 1.8 起新增）：表示类型使用的任何位置。
```

`@Retention`

定义：@Retention 用于指定被其注解的注解的保留策略，即注解在何时依然有效。它通过 RetentionPolicy 枚举值来设置保留期限，可选值包括：
SOURCE：仅在源代码级别保留，编译器处理完后丢弃，不会进入字节码文件。
CLASS：编译器将注解记录在类文件中，但不会被虚拟机加载，可通过反射读取。
RUNTIME：注解不仅存在于类文件中，还会被虚拟机加载，在运行时可以通过反射访问。

`@Documented`
定义：@Documented 标记一个注解是否应当被包含在自动产生的 Javadoc 文档中。如果一个注解使用了 @Documented，则该注解及其使用信息将在生成的 Javadoc 中体现，有助于其他开发者理解其作用和使用场景

`@Inherited`

定义：@Inherited 使得被其注解的注解可以被子类继承。当一个类使用了带有 @Inherited 的注解时，无需在子类中再次声明，子类就可以“继承”该注解的效果。注意，只有标记在类上的注解才可能被继承，且仅限于类层次的继承，接口和接口实现之间不适用。

`@Repeatable`

定义：@Repeatable 允许在同一程序元素上多次使用同一个注解类型。使用 @Repeatable 时需要指定一个容器注解（通常是包含数组属性的注解），该容器注解用于存放重复的注解实例。



在注解中，如果存在一个字段叫value 那么在使用注解的时候可以不用写名称

## 反射

```
Declared 翻译->已声明，已存在
```



1. 认识反射，获取类

加载类，获取类的字节码：Class对象

```
三种方法获取的class类是一个对象，因为在内存中class只有一份 
1.Class类的方法：Class.forName("全类名")
2.Object类的方法（需要存在对象)：类名.class
3.Object类的方法（需要存在对象) ：对象.getClass()
```

2. 获取类的构造器

Constructor对象 

```java
 Constructor<?>[] constructor = studentsClass.getDeclaredConstructors();
        for (Constructor<?> constructor1 : constructor) {
            String name = constructor1.getName();
            String parameterCount = Arrays.toString(constructor1.getParameterTypes());
            System.out.println(name + " -> " + parameterCount);
        }
```

3. 获取类的成员变量

Field对象

```java
		//获取成员变量
        Field[] fields = studentsClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("变量->" + field.getName());
        }
```



4. 获取类的成员方法

Method对象

```java
 		//获取成员方法
        Method[] methods = studentsClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("方法名->" + method.getName());
        }
```

5. 获取注解

   ```java
   		//获取类上注解
           Annotation[] annotations = studentsClass.getDeclaredAnnotations();
           for (Annotation annotation : annotations) {
               System.out.println(studentsClass.getName()+"注解->" + annotation.annotationType());
           }
           //获取成员变量注解
   		//先获取成员变量
           Field[] fields2 = studentsClass.getDeclaredFields();
   		//循环成员变量
           for (Field field : fields2) {
               //获取成员变量注解
               Annotation[] annotations1 = field.getDeclaredAnnotations();
               for (Annotation annotation : annotations1) {
                   System.out.println(field.getName() +"成员变量注解->" + annotation.annotationType());
               }
           }
           //获取成员方法注解
           Method[] methods2 = studentsClass.getDeclaredMethods();
           for (Method method : methods2) {
               Annotation[] annotations2 = method.getDeclaredAnnotations();
               for (Annotation annotation : annotations2) {
                   System.out.println(method.getName() +"成员方法注解->" + annotation.annotationType());
               }
           }
   ```

   

6. 作用，应用场景

反射核心作用：

1. 得到一个类的全部成分然后操作
2. 破坏封装性
3. 做java的框架，基本上，主流的框架都会基于反射来实现一些通用功能

# 动态代理

为什么需要代理，为了将重复的一些跟业务逻辑无关的逻辑抽离出来，简化代码，降低耦合。

```java
public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
    
  
```

参数说明：

1. ClassLoader loader：指定生成代理对象的类加载器。通常使用目标对象或其接口的类加载器。如果为 null，将使用当前线程的上下文类加载器。
2. Class<?>[] interfaces：一个接口类型的数组，表示代理对象需要实现的接口列表。代理对象将实现这些接口，并在调用这些接口方法时触发相应的代理逻辑。注意，Java 动态代理只能代理接口，不能直接代理类（若要代理类，可使用 CGLIB、Javassist 等第三方库）。
3. InvocationHandler h：一个实现了 java.lang.reflect.InvocationHandler 接口的对象。当通过代理对象调用接口方法时，实际会调用到此 InvocationHandler 对象的 invoke() 方法。在 invoke() 方法中，可以编写自定义的代理逻辑，如预处理请求、转发调用、添加额外行为、记录日志、进行权限控制、异常处理等。

返回值：

1. 该方法返回一个实现了所指定接口列表的代理对象。这个对象可以被当作接口的实例来使用，所有对代理对象的接口方法调用都将被转发到 InvocationHandler 的 invoke() 方法

例子：

接口

```java
public interface Service {
    //声明三个服务方法
    public void service1();

    public void service2();

    public void service3();

}

```

接口实现类

```java

public class ServiceImpl implements Service {
    //实现三个服务类
    @Override
    public void service1() {
        System.out.println("service1");
    }

    @Override
    public void service2() {
        System.out.println("service2");
    }

    @Override
    public void service3() {
        System.out.println("service3");
    }
}

```

代理类

```java

public class ProxyDemo {
    //使用动态代理实现
    //第一个参数 类加载器
    //第二个参数 需要代理的接口
    //第三个参数 代理对象：实现了InvocationHandler的对象、

    static int serviceCount = 0;

    static Class<?>[] services = new Class<?>[1];


    //添加需要的service
    public static void addService(Object o) {
        services[serviceCount] = o.getClass().getInterfaces()[0];
        serviceCount++;
        if (services.length < serviceCount) {
            //动态扩容
            Class<?>[] newServices = new Class<?>[services.length * 2];
            System.arraycopy(services, 0, newServices, 0, services.length);
            services = newServices;
        }
    }
	//创建代理
    public static Object createProxy() {
        Object proxyRs = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), services, (proxy, method, args1) -> {
            System.out.println("代理对象开始");
            //实现被代理的类方法
            Object rs = method.invoke(new ServiceImpl(), args1);
            System.out.println("代理对象结束");
            return rs;
        });

        return proxyRs;
    }
}

```

测试

```java
public class Test {
    @org.junit.Test
    public void test() {
        Service service = new ServiceImpl();
        ProxyDemo.addService(service);
        Service proxy = (Service) ProxyDemo.createProxy();
        proxy.service1();
        proxy.service2();
        proxy.service3();
    }
}
```

代理模式是AOP的基础，后续使用AOP进行
