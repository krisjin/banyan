


## 锁的作用域

- 1.普通方法，锁的范围是当前实例对象
- 2.静态方法，锁的是class的所有同步




## synchronized 与 volatile的区别

- 关键字volatile是线程同步的轻量级实现，性能比synchronized要好，并且volatile只能修饰变量，
  而synchronized可以修饰方法以及代码块。随着jdk版本的升级，synchronized关键字在执行效率
  上得到了很大的提升，在开发中使用synchronized的比率还是比较大的。
  
- 多线程访问volatile不会发生阻塞，而synchronized会发生阻塞。
 
- volatile能保线程的可见性，但不能保证原子性；而synchronized可以保证原子性，也可以保证可见性，
  因为他会将私有内存和公共内存中的数据做同步。
  
  
  
  
  
  
  
  
  参考：
  
  https://www.jianshu.com/p/73b9a8466b9c
  
  
  https://crossoverjie.top/2018/05/24/netty/Netty(1)TCP-Heartbeat/