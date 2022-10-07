## 并发编程


###  如何尽量保证线程安全

- 可变状态是至关重要的。  
       所有并发访问都可以归结为如何协调对并发状态的访问，可变状态越少，越容易确保线程安全性。 

- 尽量将域声明为final类型，除非需要它们是可变的。

- 不可变对象一定是线程安全的。

  不可变对象能极大地降低并发编程的复杂性。它们更为简单且安全，可以任意共享而无须使用加锁或保护性复制等机制。

  使用final关键字

- 封装有助于管理复杂性。
     在编写线程安全的程序时，虽然可以将所有数据都保存在全局变量，但为什么要这样做？ 将数据封装在对象中，更易于维护不变性条件：将同步机制封装在对象中，更易于遵循同步策略。

- 用锁保护每个可变变量。

- 当保护同一个不变性条件中的所有变量时，要使用同一个锁。

- 在执行复合操作期间，要持有锁。

- 如果从多个线程中访问同一个可变变量时没有同步机制，那么程序会可能出问题。

- 不要故作聪明地推断不需要使用同步。

- 在设计过程中考虑线程安全，或者在文档中明确地指出塔不是线程安全的。

- 将同步策略文档化。
- 使用ThreadLocal保存状态变量





###  死锁问题



**死锁原因**

1. 



**避免死锁**

1. 减小锁粒度：对于资源的加锁时间必须足够短，必要时进行加锁，并尽快释放   

2. 顺序访问资源：访问资源过程中的锁需要按照一致的顺序进行获取，否则需要提升出一个更大的锁来确保资源的获取。多数情况下，死锁是由于获取锁的顺序错误锁导致的。

3. 尝试使用定时锁：使用lock.tryLock(timeout)来替代使用内部锁机制，竞争锁时限制时长，当超过一定时间还没获取到锁时则返回失败。

4. 避免一个线程同时获取多个锁，一个线程在锁内同时占用多个资源，尽量保持一个锁只占用一个资源。  

5. 竞争锁可中断，在竞争锁期间，允许锁竞争动作被打断。

6. 尽量通过封装的形式，避免将锁暴露给外部，从而造成不必要的资源死锁 

7. 避免临界区的嵌套，尽量不要在释放锁之前竞争其它锁。





###  原子性
原子（atom）本意是“不能被进一步分割的最小粒子”，而原子操作（atomic operation）意为"不可被中断的一个或一系列操作" 。
在多处理器上实现原子操作就变得有点复杂。

####  对象类型
- 对象地址原子读写，线程安全。
- 并发读不可变状态，线程安全。
- 并发读写可变状态，非线程安全。

#### 基本类型
- int,char数值读写，线程安全。
- long,double高低位，非线程安全。
- i++ 等组合操作，非线程安全。

### 可见性

### final
- 初始化final字段确保可见性

### volatile
- 读写volatile字段确保可见性

### synchronized
- 同步块内读写字段确保可见性

### happen before
- 遵守happen before次序可见性

## 可排序性

### Happen Before法则

- 程序次序法则
  如果A一定在B之前发生，happen before
  
- 监视器法则
  对一个监视器的解锁一定发生在后续对同一个监视器加锁之前
  
- Volatile变量法则
  写volatile变量一定发生在后续对它读之前
  
- 线程启动法则
  Thread.start一定发生在线程中的动作之前
  
- 线程终结法则
  线程中的任何动作一定发生在以下操作的动作之前。
  
        其它线程检测到这个线程已经终止，从Thread.join调用成功返回，Thread.isAlive()返回false
  
- 中断法则
    一个线程调用另一个线程的interrupt一定发生在另一线程发现中断之前
    
- 终结法则
  一个对象的构造函数结束一定发生在对象的finalizer之前
  
- 传递性
  A发生在B之前，B发生在C之前，A一定发生在C之前


##


如果多个线程访问同一个可变的状态变量时，没有使用合适的同步，那么程序就会出现错误。有三种方式可以修复这个问题：

1. 不在线程之间共享该状态变量
2. 将状态变量修改为不可变的变量
3. 在访问状态变量时使用同步

什么是线程安全性

在线程安全性的定义中，最核心的概念就是正确性。 
当多个线程访问某个类时，不管运行时环境采用何种调度方式或者这些线程将如何交替执行，并且在主调用代码中不需要任何额外的同步或协同，这个类都能表现出正确的行为，那么就称这个类是线程安全的。

在线程安全类中封装了必要的同步机制，因此客户端无需进一步采取同步措施。 

无状态对象一定是线程安全的。


熟练使用线程安全类

要保持状态的一致性，就需要在单个原子操作中更新所有相关的状态变量。

并非所有的数据都需要锁的保护，只有被多个线程同时访问的可变数据才需要通过锁来保护。 

对非原子的64位操作，如long,double类型的变量，在多线程下存在这样的共享变量时，请把变量定义成volatile

加锁机制既可以确保可见性又可以确保原子性 ，volatile变量只可以确保可见性。

当访问共享的可变数据时，通常需要同步，一种避免使用同步方式就是不共享数据。

满足不可变对象的条件： 

- 对象创建以后其状态不能修改
- 对象的所有域都是final类型
- 对象是正确创建的（在对象创建的期间，this引用没有逸出）


final域能确保初始化过程中的安全性

# 不变模式
在并行程序开发过程中，同步操作似乎是必不可少的。当多线程对同一个对象进行读写操作时，为了保证数据对象对一致性
和正确性，有必要对对象使用同步。故而同步操作对性能有相当对消耗。为了尽可能的去除这些同步操作，提高并行程序的
性能，可以使用一种不可变的对象，依靠对象的不变性，可以确保其在没有使用同步操作的多线程环境下依然始终保持
内部状态的一致性和正确性。

不变模式天生就是多线程友好的，它的核心思想是，一个对象一旦被创建，则它的内部状态将永远不会发生改变。所以，没有
一个线程可以修改其内部状态和数据。

不变模式使用场景介绍

- 当对象被创建后，其内部状态和数据不再发生任何变化
- 对象需要被共享、被多线程频繁访问

# JAVA的并发控制方式
- 内部锁
- 重入锁
- 读写锁
- ThreadLocal变量3
- 信号量

# 隐藏比较深的数据竞争
|竞争情况描述 | 线程T1 | 线程T2 |
|-----------|--------|-----|
|编译器将这个表达式扩展成temp=x ,和 x=temp+1 | x+=1 | x+=2 |
|下标i和j相同时可能会出现数据竞争| a[i]+=1 | a[j]+=1 |
|指针q和p指向同一个目标时可能会出现数据竞争| *q+=2 | *p+=1 |
|foo函数可能使用参数对一个共享变量进行修改|foo(1) | foo(2) |
|即使在指令集，硬件还是会将【edi】对更新操作扩展成独立对对读操作和写操作改|add [edi],1 | add [edi] ,2|


# 锁优化

## 偏向锁

- 大部分情况是没有竞争的，所以可以通过偏向来提高性能
- 所谓的偏向，就是偏心，即锁会偏向于当前已经占有锁的线程
- 将对象头Mark的标记设置为偏向，并将线程ID写入对象头Mark
- 只要没有竞争，获得偏向锁的线程，在将来进入同步块，不需要做同步
- 当其他线程请求相同的锁时，偏向模式结束
- -XX:+UseBiasedLocking  默认启用

在竞争激烈的场合，偏向锁会增加系统负担



### 参考资料

https://github.com/LeonardoZ/java-concurrency-patterns
https://github.com/PacktPublishing/Mastering-Concurrency-Programming-with-Java-9-Second-Edition.git
http://tutorials.jenkov.com/java-nio/selectors.html


