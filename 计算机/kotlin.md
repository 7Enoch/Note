​	







## 类型转换跟类型检查符

is：类型检查符

在 Kotlin 中，`is` 用于类型检查，用来判断一个对象是否属于某个特定的类型。

例：

```kotlin
val obj: Any = 42
if (obj is Int) {
    println("obj 是整数类型")
}
```



`is` 操作符返回一个布尔值，如果对象的类型与指定的类型匹配，返回 `true`，否则返回 `false` 。

as：类型转换符

在 Kotlin 中，`as` 用于类型转换。

当您确定一个对象实际上是某种特定类型时，可以使用 `as` 进行强制类型转换。但如果转换不成功，会抛出 `ClassCastException` 异常。

例：

```kotlin
val obj: Any = "Hello"
val str: String = obj as String 
```

这里将 `Any` 类型的 `obj` 强制转换为 `String` 类型。

## 扩展函数

```
fun 数据类型.方法名(参数):返回类型{
    this(代表传递进来的参数本身)
    对this进行处理后返回值，就达到了给自带数据类型添加内置方法的方法，但不对数据类型类本身代码进行修改
}
```

例子

```kotlin
//反转字符串
fun String.rever():String{
    return this.reversed()
}
//数字转化为字符
fun Int.convert():String{
    return this.toString()
}
```

## 继承

面向对象的基本概念，子类可以使用父类的属性跟方法，kotlin中 可以被继承的类跟属性要有**open**关键字

例子

```kotlin
fun main(){
    val dog = Dog()
    dog.w()


}
open class Animal (name:String){
    //父类属性
    var i = 11
    //父类方法
    open fun eat(food:String){
        println("父类吃$food")
    }
}
class Dog:Animal("狗"){
    //子类使用父类方法
    fun w(){
        println(i)
        //首先用的是自己重写的方法this可以省略
        this.eat("bone")
        //使用super就使用的父类方法
        super.eat("fish")
    }
    //子类重写父类方法
    override fun eat(food: String) {
        println("自己吃${food}")
    }

}
```



## 抽象类与接口

抽象类是不能被实例化的类，可以存在抽象方法（没有实现的方法），具体方法（实现了的方法）。

抽象类的作用主要是给子类提供通用的模板，和一些共同的属性与方法，强制子类实现特定的抽象方法，保证子类的完整性和一致性。

```kotlin
//定义抽象类
abstract class A{
    //定义抽象方法
    abstract fun a()
    //定义抽象类里的具体方法
    fun b(){
        println("b")
    }
}
//实现抽象类
class B:A(){
    //实现抽象方法
    override fun a() {
        println("a")
    }
    fun main(){
        a()
        b()
    }

}
```

接口是完全抽象的类型，可以存在抽象方法（没有实现的方法），具体方法（实现了的方法）。

接口的作用是定义行为，多个不相关的类实现同一个接口，以实现多态和代码灵活

```kotlin
//定义接口
interface C{
    //定义接口方法
    fun c()
    fun d()
}
//实现C接口
class D:C, A(){
    //实现接口方法
    override fun a() {
        println("a")
    }
    override fun c() {
        println("a")
    }
    override fun d() {
        println("a")
    }


```

抽象类跟接口区别：

单继承与多实现：一个类只能继承一个抽象类，但可以实现多个接口。 

成员变量：抽象类可以包含成员变量，而接口中不能包含成员变量，只能包含静态常量。

 构造函数：抽象类可以有构造函数，接口不能有构造函数。

## 数据类跟密封类

数据类主要用于封装数据，数据类会自动生成equals，hascode，toString方法，方便处理数据

```kotlin 
fun main(){
    val person = Person("小米",12)
    //解构分别赋值
    val (name1,age1) = person
    print(name1)
    print(age1)
    //copy方法
    val person2 = person.copy(age = 13,name="ww")
    print(person2.name)
    print(person2.age)
}
//定义数据类，类体可以不需要
data class Person(val name: String, val age: Int)
```

密封类是一种用于定义受限类层次结构的特殊类。它的主要特点和用途包括：

1. 明确的子类限制：密封类的所有直接子类必须在同一个文件中声明。这使得类的层次结构更加清晰和可控。
2. `when` 表达式的优化：在使用 `when` 表达式处理密封类的子类时，编译器可以确保所有可能的子类情况都被处理。如果没有处理完所有的子类，编译器会报错，从而避免了遗漏某些情况导致的潜在错误。

```kotlin
fun main() {
    val result = Result.Success("SS")
    handleResult(result)

}
fun handleResult(result: Result){
    when(result){
       is Result.Success -> print("S")
       is Result.Failure -> print("F")
    }
}
sealed class Result{
    data class Success(val data: String): Result()
    data class Failure(val error: String): Result()

}
```

## 协程

协程是一种在 Kotlin 中用于异步编程的机制，它可以让异步操作的代码看起来更像是同步的，从而提高代码的可读性和可维护性。

协程的主要特点和优势包括：

1. 轻量级：协程的开销相对较小，比传统的线程更高效。
2. 非阻塞：可以在等待异步操作完成时挂起协程，而不会阻塞线程。
3. 简洁的异步代码：能够以更直观的方式编写异步逻辑。

启动方式：

`runBlocking`、`launch` 和 `async` 这几种方法的区别、对比和使用场景的详细讲解：

1. `runBlocking`：
   - 特点：会阻塞当前线程，直到协程内的操作完成。
   - 用途：通常用于测试或在主函数中启动协程，以确保整个程序在协程完成之前不会结束。
   - 使用场景：适用于简单的示例、测试代码，或者在程序的入口（如 `main` 函数）中，需要等待协程完成来获取结果或确保某些初始化操作完成。
2. `launch`：
   - 特点：在后台启动一个新的协程，不会阻塞当前线程。
   - 用途：用于执行一些不需要返回结果的异步任务，例如执行后台的耗时操作，如文件下载、数据更新等。
   - 使用场景：当您希望在不阻塞当前线程的情况下执行一些异步操作，并且不关心操作的结果时，适合使用 `launch` 。
3. `async`：
   - 特点：启动一个异步任务并返回一个 `Deferred` 对象，可以通过 `await` 来获取结果。
   - 用途：适用于需要获取异步操作结果的情况。
   - 使用场景：当您需要执行一个异步任务并获取其返回值，以便在后续的代码中使用这个结果时，使用 `async` 。

对比来说：

- `runBlocking` 是阻塞式的，而 `launch` 和 `async` 是非阻塞式的。
- `launch` 不返回结果，`async` 可以获取返回值。

```kotlin

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    println("runBlocking开始")
    runBlocking{
        //延时操作
        delay(1000)
        fetchData()
    }
    println("runBlocking结束")
    // 使用 launch
    GlobalScope.launch {
        println("launch: Starting")
        delay(2000)
        println("launch: Finished")
    }
    // 使用 async
    val deferredResult = GlobalScope.async {
        println("async: Starting")
        delay(3000)
        "Async Result"
    }
    //获取到的async只能在协程环境中使用
    runBlocking {
        val result = deferredResult.await()
        println("async result: $result")
    }

}

//创建一个协程函数（协程环境）
@OptIn(DelicateCoroutinesApi::class)
suspend fun fetchData():List<User>{

    GlobalScope.launch{
        println("你好")
    }
    //获取async的数据
   val result= GlobalScope.async {
       println("nisas")
       "111djaos"
   }
    //打印数据
    println(result.await())

    //模拟异步获取数据的操作
    return getUserInfo()
}
fun getUserInfo():List<User>{
    val userList = listOf(
        User("1",20),
        User("2",20),
        User("3",20)
    )
    return userList
}
data class User(val name:String,val age:Int)
```

## 泛型

```kotlin
//泛型类
class Box<T>(){
    //泛型方法
    fun x(list:T){

        if(list is List<*>){
            var count:Int = 0
            (list as List<Int>).forEach { item ->
                count+=item
            }
            println(count)
        }else{
            println("不是整数列表")
        }

    }

}
```

## 集合与序列

`measureTimeMillis` 是一个用于测量一段代码执行所花费时间（以毫秒为单位）的函数。

```kotlin
fun main() {
    // 创建一个整数集合
    val intSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // 创建一个对应的整数序列
    val intSequence = sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // 集合的遍历、过滤和映射操作
    //measureTimeMillis
    val setTime = measureTimeMillis {
        val filteredSet = intSet.filter { it % 2 == 0 }.map { it * 2 }
        filteredSet.forEach { println(it) }
    }
    println("集合操作耗时: $setTime 毫秒")

    // 序列的遍历、过滤和映射操作
    val sequenceTime = measureTimeMillis {
        val filteredSequence = intSequence.filter { it % 2 == 0 }.map { it * 2 }
        filteredSequence.forEach { println(it) }
    }
    println("序列操作耗时: $sequenceTime 毫秒")
}
```

过滤操作`filter`

`filter { }` 是使用 lambda 表达式来定义过滤条件，而 `filter()` 通常需要传入一个单独定义的函数对象或者函数引用。

修改操作 `map` 

去重操作 `distinct`

使用场景：

集合（如 `List`、`Set`、`Map` ）和序列（`Sequence` ）在不同的场景中有各自的优势和适用情况。

集合的使用场景

1. 当需要快速随机访问元素、查找元素是否存在、获取集合的大小等操作时，集合通常更合适。
2. 如果需要确保元素的唯一性（如 `Set` ）或者通过键值对来存储和检索数据（如 `Map` ），集合是首选。
3. 当数据量相对较小且需要频繁进行修改操作（添加、删除元素）时，集合的性能较好。

序列的使用场景

1. 处理大规模数据时，特别是当您不需要一次性将所有数据加载到内存中，而是可以逐步处理时，序列可以节省内存。
2. 当需要对数据进行多个连续的链式操作（如过滤、映射、排序等），并且不希望每个操作都立即执行，而是在最终遍历或获取结果时才执行，序列可以提高效率。
3. 如果操作的逻辑较为复杂，并且可能不需要处理所有元素，序列的惰性计算特性可以避免不必要的计算。

## 高阶函数跟lambda表达式

高阶函数：

是指将函数作为参数或者返回函数的函数。

```kotlin
//定义高阶函数
fun test(list:List<Any>,opt:(Any)->Boolean):List<*>{
    return list.filter(opt)

}
```

例子：

```kotlin

fun main() {
    loginAction("name","password"){
        //这里就是函数体 作为参数传递
        if(it) println("登录成功") else println("登录失败")
    }

}
fun loginAction(name:String,password:String,loginResult:(Boolean)->Unit){
    //模拟前端校验
    if(name==null||password==null){
        return
    }
    check(name,password,loginResult)
}
//模拟后端校验
fun check(name: String, password: String,loginResult: (Boolean) -> Unit) {
    if(name=="name"&&password=="password")
        loginResult(true)
    else
        loginResult(false)
}
```



lambda表达式：

Lambda 表达式是一种匿名函数，可以方便地作为参数传递给其他函数，或者用于创建函数类型的变量。

Lambda 表达式具有简洁、灵活的特点。它的基本语法是：

```
{ 参数列表 -> 函数体 }
```

```kotlin
val multiply = { x: Int, y: Int -> x * y }
```

Lambda 表达式可以访问外部作用域中的变量，这被称为闭包。

例如：

```kotlin
val factor = 2
val multiplyByFactor = { x: Int -> x * factor }
```

在函数式编程中，如 `filter`、`map` 等操作常使用 Lambda 表达式来定义具体的操作逻辑。

例如：

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)
val evenNumbers = numbers.filter { it % 2 == 0 } 
```

这里的 `{ it % 2 == 0 }` 就是一个 Lambda 表达式，用于判断数字是否为偶数。

lambda声明函数

```kotlin
//两个传入参数写在后面 不需要写返回值
val method ={x:Int,y:Int -> x+y}
//两个传入参数写在前面 需要写返回值
val method01:(Int,Int) -> Unit = {x,y -> print(x+y) }
val method02:(String,String) -> Int = {x,y -> x.length+y.length}
//一个参数可以省略变量名的声明
val method03:(String) -> Int = {it.length}
val method04:(Int) -> Int ={
    when(it){
        1 -> 1+1
        else -> 0
    }
}
```

## 委托*

> 类委托：

类委托是指一个类将其某些方法的实现委托给另一个类。这意味着当您在委托类中调用被委托的方法时，实际上是由被委托的对象来执行这些方法。

> 属性委托：

属性委托是将属性的读、写操作委托给另一个对象来处理。

## 函数式基础

> 纯函数：

对于相同的输入有相同的输出，不对函数外部的变量或者其他值进行更改。

> 不可变数据：

使用val创建不可变数据，初始化过后就不能被重新赋值

> 柯里化函数：

将接受多个参数的函数转换为一系列接受单个参数的函数链。

```kotlin
//常规写法
fun add(a: Int, b: Int): Int { return a + b }
//柯里化写法1 返回一个带有参数的函数
fun addCurried(a: Int): (Int) -> Int {
    return { b -> a + b }
}
//柯里化写法2 返回一个带有参数的函数
fun addCurried(a: Int): (Int) -> Int {
    return fun(b: Int): Int {
        return a + b
    }
}
//要返回函数 的定义必须带 ->数据类型
fun curriedMultiply2(a:Int):(Int)->Int{
    return fun(b:Int):Int{
        return b*a
    }

}
//返回具体数据则不用带 ->数据类型
fun curriedMultiply3(a:Int):Int{
    return a

}

 //获取调用一次的函数
val addresult = addCurried(5)
 //再给上面的函数一个参数
    println(addresult(3))
```

Kotlin跟Java互操作

> kotlin调用java

直接创建对应Java对象即可

KotlinMain.kt

```kotlin
fun main() {
 val javaAdd = JavaMain()
     javaAdd.add(1,2)
}
fun curriedMultiply3(a:Int,b:Int):Int{
    return a*b
}
```

处理 Java 的 null 值

```kotlin
   val value: String? = javaObject?.nullableField
```



> java调用kotlin

JavaMain.java

```java
public class Main1 {
    public static void main(String[] args) {
        System.out.println(KotlinMainKt.curriedMultiply3(2,3));
    }
    public int add(int a, int b){
        return a+b;
    }
}

```

处理 Kotlin 的特性

- Java 可以处理 Kotlin 中的一些特性，如扩展函数和属性。这些会被编译成静态方法，Java 可以通过类名和方法名来调用。
- 例如，Kotlin 中的扩展函数 `String.myExtension()` 在 Java 中可以通过 `KotlinClassKt.myExtension(String)` 来调用。