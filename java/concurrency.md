# 并发专题 

## 1.基础知识 

-- 为什么用到并发？  
1.充分利用多CPU的计算能力  
2.方便进行业务拆分，提升应用性能  Z

缺点：  
1.频繁的上下文切换  
    -->减少上下文切换可以采用无锁并发编程，CAS算法，使用最少的线程和使用协程。  
2.线程安全  
    多线程编程中最难以把握的就是临界区线程安全问题，稍微不注意就会出现死锁的情况，一旦产生死锁就会造成系统功能不可用。  
    [com.java.advance.concurrency.thread.basic.D07_DeadLockDemo]
    
    如何避免死锁：
    1.避免一个线程同时获得多个锁
    2.便面一个线程在锁内部占用多个资源，尽量保证每个锁只占用一个资源
    3.尝试使用定时锁，使用lock.tryLock(timeOut),当超时等待时当前线程不会阻塞
    4.对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况
    
基本概念：  
1.同步&异步  
  用来描述一次方法调用，同步方法一开始，调用者必须等待被调用的方法结束后，调用者后面的代码才能执行；  
  异步调用指调用者不管被调用方法是否完成，都会继续执行后面的代码，当被调用的方法完成后会通知调用者。
    
2.并发&并行   
  并发：多个任务交替进行  
  并行：多个任务同时进行  

3.阻塞和非阻塞  

4.临界区  
可以被多个线程使用的公共资源或者共享数据

新建线程的三种方式 ：继承Thread,实现Runnable,实现Callable 

线程状态转换：  
线程状态：NEW RUNNABLE BLOCKED WAITING TIMED_WAITING TERMINATED 

    线程状态间的关系见思维导图  
    
线程状态基本操作：  
1.interrupted  
2.join()  
3.sleep  如果当前线程获得了锁，则不会失去锁  
3.yield  当前线程让出CPU,获取到CPU时间片后继续执行（让出的时间片只分配给当前线程相同优先级的线程）

守护线程
 
## 2.并发理论（JMM）  

### 2.1 java内存模型 
 
多线程重点：  
1.多个线程间相互通信告知彼此的状态和当前执行结果  
2.编译器指令重排序和处理器指令重排序  

### 2.2 JMM    
    并发编程需要解决的问题：    
    1.线程间如何通信 
    2.线程之间如何完成同步
通信方式：共享内存和消息传递  
Java内存模型是共享内存的并发模型，线程间通过读-写**共享变量**来完成隐式通信

1).Java程序中所有实例域，静态域和数组元素都放在堆内存中，所有线程均可访问到，可以共享（会出现线程安全问题）；  
而局部变量，方法定义参数和异常处理器参数不在线程间共享。  
2)由于CPU处理速度和主存的读写速度不是一个量级，而每个CPU都会有自己的缓存。因此，共享变量会先放在主存中，而
每个线程都有自己的工作内存，并且会把位于主存中的变量拷贝到自己的工作内存中，之后的读写操作都会使用工作内存中
的变量副本，并在某个时刻将工作内存中的变量副本写回主存去。JMM就从抽象层次定义了这种方式，并决定了一个线程对
共享变量的写入何时对其他线程是可见的  
例：如果线程A对某共享变量进行写操作但并未及时写入主存，此时线程B督导的就是过期的脏数据。此时可以通过同步机制
（控制不同线程间发生的相对顺序）来解决，或者通过volatile关键字使得每次volatile变量都能够强制刷新主存，从而
对每个线程都是可见的   

### 2.3重排序  

为了提高性能，编译器和处理器通常会对指令进行重排序  



### 2.4happens-before原则  

JMM提供了具体的规则，我么可以根据规则去推论跨线程的内存可见性问题  

1.happens_before定义  
1）如果一个操作happens-before另一个操作，那么第一个操作的执行结果对第二个操作是可见的，而且第一个操作的执行顺序在第二个操作之前  
2）两个操作间存在happens-before关系，那么编译器和处理器重排序的执行结果必须与按happens-before执行的结果一致

2.具体规则  
1）程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作。  
2）监视器锁规则：对一个锁的解锁，happens-before于随后对这个锁的加锁。  
3）.volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读。  
4).传递性：如果A happens-before B，且B happens-before C，那么A happens-before C。  
5).start()规则：如果线程A执行操作ThreadB.start()（启动线程B），那么A线程的ThreadB.start()操作happens-before于线程B中的任意操作。  
6).join()规则：如果线程A执行操作ThreadB.join()并成功返回，那么线程B中的任意操作happens-before于线程A从ThreadB.join()操作成功返回。  
7).程序中断规则：对线程interrupted()方法的调用先行于被中断线程的代码检测到中断时间的发生。  
8).对象finalize规则：一个对象的初始化完成（构造函数执行结束）先行于发生它的finalize()方法的开始。  

## 3.并发关键字

### 3.1 synchronized  

作用域：方法和代码块  
锁的重入性，即在同一锁程中，线程不需要再次获取同一把锁。Synchronized先天具有重入性。每个对象拥有一个计数器，当线程获取该对象锁后，计数器就会加一，释放锁后就会将计数器减一。

    CAS

### 3.2 volatile  
线程对volatile变量的修改会立刻被其他线程所感知，即不会出现数据脏读的现象，从而保证数据的“可见性”。  
被volatile修饰的变量能够保证每个线程能够获取该变量的最新值，从而避免出现数据脏读的现象。

volatile变量可以通过缓存一致性协议保证每个线程都能获得最新值

### 3.3 final 
修饰变量，方法和类  
1。当final修饰基本数据类型变量时，不能对基本数据类型变量重新赋值，因此基本数据类型变量不能被改变。
而对于引用类型变量而言，它仅仅保存的是一个引用，final只保证这个引用类型变量所引用的地址不会发生改变，即一直引用这个对象，但这个对象属性是可以改变的。
2.final修饰的方法是不能够被子类重写的，但可以重载
3.当一个类被final修饰时，表名该类是不能被子类继承的  

## 4.三大性质  
原子性，可见性，有序性 
4.1原子性是指一个操作是不可中断的，要么全部执行成功要么全部执行失败  
java内存模型中定义了8中操作都是原子的，不可再分的。

1).lock(锁定)：作用于主内存中的变量，它把一个变量标识为一个线程独占的状态；
2).unlock(解锁):作用于主内存中的变量，它把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定
3).read（读取）：作用于主内存的变量，它把一个变量的值从主内存传输到线程的工作内存中，以便后面的load动作使用；
4).load（载入）：作用于工作内存中的变量，它把read操作从主内存中得到的变量值放入工作内存中的变量副本
5).use（使用）：作用于工作内存中的变量，它把工作内存中一个变量的值传递给执行引擎，每当虚拟机遇到一个需要使用到变量的值的字节码指令时将会执行这个操作；
6).assign（赋值）：作用于工作内存中的变量，它把一个从执行引擎接收到的值赋给工作内存的变量，每当虚拟机遇到一个给变量赋值的字节码指令时执行这个操作；
7).store（存储）：作用于工作内存的变量，它把工作内存中一个变量的值传送给主内存中以便随后的write操作使用；
8).write（操作）：作用于主内存的变量，它把store操作从工作内存中得到的变量的值放入主内存的变量中。

4.2有序性  
4.3可见性  
可见性是指当一个线程修改了共享变量后，其他线程能够立即得知这个修改

    synchronized: 具有原子性，有序性和可见性； volatile：具有有序性和可见性
                           
## Lock体系
### lock  
提供与synchronized一样的锁功能，拥有锁获取和释放的显示操作，可中断式获取锁和超时获取锁等，使用方式：<br/>
~~~java_holder_method_tree
Lock lock = new ReentrantLock();
lock.lock();
try{
	.......
}finally{
	lock.unlock();
}
~~~
 synchronized同步块执行完成或者遇到异常时锁会自动释放，而lock必须调用unlock()方法释放锁，因此习惯在finally代码块中释放锁  
 
### AQS  同步器
AQS是构建锁和其他同步组件的基础框架，其他自定义内部组件会声明一个继承AbstractQueuedSynchronizer（AQS）了的静态内部类  
同步器自身不实现任何同步接口，而是定义了若干同步状态的获取和释放方法来供自定义同步组件使用  <br/>

锁和同步器的关系：
锁是面向使用者，定义了使用者与锁交互的接口，隐藏了实现细节；<br/>
同步器是面向锁的实现者，它简化了锁的实现方式，屏蔽了同步状态的管理，线程的排队，等待和唤醒等操作  

同步组件（这里不仅仅值锁，还包括CountDownLatch等）的实现依赖于同步器AQS，在同步组件实现中，使用AQS的方式被推荐定义继承AQS的静态内存类；<br/>
AQS采用模板方法进行设计，AQS的protected修饰的方法需要由继承AQS的子类进行重写实现，当调用AQS的子类的方法时就会调用被重写的方法；<br/>
AQS负责同步状态的管理，线程的排队，等待和唤醒这些底层操作，而Lock等同步组件主要专注于实现同步语义；<br/>
在重写AQS的方式时，使用AQS提供的getState(),setState(),compareAndSetState()方法进行修改同步状态

核心：同步队列，独占式锁，共享式锁  
独占式锁： 
void acquire(int arg) 独占式获取同步状态，如果获取失败则插入同步队列进行等待；
void acquireInterruptibly(int arg) 与acquire()相同，但在同步队列中进行等待时可以检测中断  
boolean tryAcquireNanos(int arg,long nanosTimeout) 超时等待，在超时时间内没有获得同步状态返回false  
boolean release(int arg) 释放同步状态，唤醒同步队列的下一个节点  
共享式锁：  
void acquireShared(int arg) 共享式获取同步状态，与独占式的区别在于同一时刻有多个线程获取同步状态  
void acquireSharedInterruptibly(int arg) 响应中断  
boolean tryAcquireShareNanos(int arg,long nanosTimeout) 超时等待；  
boolean releaseShared(int arg)  共享式释放同步状态  

### 同步队列  
当共享资源被某个线程占有，其他请求资源的线程会被阻塞，从而进入同步队列  
AQS的同步队列通过链式方式实现，通过双向队列实现
~~~java_holder_method_tree
static final Class Node{
    volatile int waitStatus;//节点状态
    volatile Node prev;//当前节点或线程的前驱节点
    volatile Node next;//当前节点或线程的后继节点
    volatile Thread thread;//加入同步队列的线程引用
    Node nextWaiter;//等待队列中的下一节点
}
~~~

节点下的状态：
    
    int CANCELLED = 1；//节点从同步队列中取消
    int SIGNAL = -1;//后继节点的线程处于等待状态，如果当前节点释放同步状态会通知后继节点，使得后继节点的线程能够运行
    int CONDITION = -2;//当前节点进入等待队列中  
    int PROPAGATE = -3；//表示下一次共享式同步状态获取将会无条件传播下去
    int INITIAL = 0;//初始状态
    
AQS通过头尾指针来管理同步队列

### 独占锁  

#### 独占锁的获取（acquire）
   lock()方法会获取独占式锁，调用失败则将当前线程加入同步队列，成功则线程执行。  
   zxhuanglock方法实际上调用AQS的acquire()方法
   
   ~~~java_holder_method_tree
   public final void acquire(int arg) {
            //1.先看同步状态是否获取成功，如果成功则方法结束返回
            //2.若失败则先调用addWaiter()方法再调用acquireQueued()方法
           if (!tryAcquire(arg) &&
               acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
               selfInterrupt();
       }
   
   ~~~
1.获取同步状态失败，入队操作<br/>
线程获取独占式锁失败后会将当前线程加入到同步队列--addWaiter()方法
   ~~~java_holder_method_tree
   private Node addWaiter(Node mode) {
            // 1. 将当前线程构建成Node类型
           Node node = new Node(Thread.currentThread(), mode);
           // Try the fast path of enq; backup to full enq on failure
           Node pred = tail;
           if (pred != null) {
                //2.2 当前节点为节点不为null,说明队列中有等待线程
                // 当前节点采用尾插入的方式插入同步队列中
                //如果compareAndSetTail()为false则执行到enq()自旋进行再次尝试
                //enq()源码解析见下方代码
               node.prev = pred;
               if (compareAndSetTail(pred, node)) {
                   pred.next = node;
                   return node;
               }
           }
           // 2.1. 当前同步队列尾节点为null，说明当前线程是第一个加入同步队列进行等待的线程，调用enq()方法插入
           enq(node);
           return node;
       }
   ~~~
   
   ~~~java_holder_method_tree
   private Node enq(final Node node) {
           for (;;) {
               Node t = tail;
               //1.尾节点为空。即同步队列没有等待线程时，会创建头节点
               if (t == null) { // Must initialize
               //1.2 带头节点的链式存储结构会在入队和出队时获得更大的便捷性
               //这个时候队列必须进行初始化（new Node()）
                   if (compareAndSetHead(new Node()))
                       tail = head;
               } else {
               //2.尾节点不为空，即调用addWaiter().compareAndSetTail()失败会走到这里
               //2.2 compareAndSetTail(t, node)方法会利用CAS操作设置尾节点
               //2.3 如果CAS操作失败会在for (;;)for死循环中不断尝试，直至成功return返回为止
                   node.prev = t;
                   if (compareAndSetTail(t, node)) {
                       t.next = node;
                       return t;
                   }
               }
           }
       }
       
    enq()方法总结：
    1.在当前线程是第一个加入同步队列时，调用compareAndSetHead(new Node())方法，完成链式队列的头结点的初始化；
    2.自旋不断尝试CAS尾插入节点直至成功为止。
   ~~~
入队后同步队列中线程如何获取独占式锁？--- acquireQueued()  排队获取锁 

~~~java_holder_method_tree
    final boolean acquireQueued(final Node node, int arg) {
            boolean failed = true;
            try {
                boolean interrupted = false;
                //自旋
                for (;;) {
                //1.获取当前节点的前驱节点
                    final Node p = node.predecessor();
                    //2.如果前驱节点是头节点 并且成功获取独占式锁
                    if (p == head && tryAcquire(arg)) {
                        //2.1将队列头指针指向当前节点
                        setHead(node);
                        //2.2 释放前驱节点
                        p.next = null; // help GC
                        //当前借钱指向的线程能够获得锁
                        failed = false;
                        return interrupted;
                    }
                    //3.获取锁失败，线程进入等待状态等待获取独占式锁
                    if (shouldParkAfterFailedAcquire(p, node) &&
                        parkAndCheckInterrupt())
                        interrupted = true;
                }
            } finally {
                if (failed)
                    cancelAcquire(node);
            }
        }
~~~ 

2.获取锁成功，出队操作<br/>
出队逻辑：<br/>
将队列头节点引用指向当前节点，并释放当前节点的前驱节点，即上面代码2操作  
~~~java_holder_method_tree
    setHead(node);
    p.next = null; // help GC
    failed = false;
    return interrupted;
~~~  
setHead()方法：
~~~java_holder_method_tree
    head = node;
    node.thread = null;
    node.next = null;
~~~  
如果获取锁失败，则调用shouldParkAfterFailedAcquire()和parkAndCheckInterrupt()
~~~java_holder_method_tree
    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
            int ws = pred.waitStatus;
            if (ws == Node.SIGNAL)
                /*
                 * This node has already set status asking a release
                 * to signal it, so it can safely park.
                 */
                return true;
            if (ws > 0) {
                /*
                 * Predecessor was cancelled. Skip over predecessors and
                 * indicate retry.
                 */
                do {
                    node.prev = pred = pred.prev;
                } while (pred.waitStatus > 0);
                pred.next = node;
            } else {
                /*
                 * waitStatus must be 0 or PROPAGATE.  Indicate that we
                 * need a signal, but don't park yet.  Caller will need to
                 * retry to make sure it cannot acquire before parking.
                 */
                compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
            }
            return false;
        }
~~~

~~~java_holder_method_tree
    private final boolean parkAndCheckInterrupt() {
            //使得该线程阻塞
    		LockSupport.park(this);
            return Thread.interrupted();
    }
~~~

总结：<br/>
acquireQueued()在自旋过程中主要完成了两件事情：<br/>
1.如果当前节点的前驱节点是头节点，并且能够获得同步状态的话，当前线程能够获得锁该方法执行结束退出；
2.获取锁失败的话，先将节点状态设置成SIGNAL，然后调用LookSupport.park方法使得当前线程阻塞。

    