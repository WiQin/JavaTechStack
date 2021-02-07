中间件，为分布式系统提供协调服务

分布式系统：很多台计算机组成一个整体，整体一直对外并处理一个请求  
    内部的计算机可以相互通信（rest/rpc）  
    客户端的请求从开始响应到结束会经过多台计算机  
    根据不同业务将系统拆分，再分别做集群  
    
zookeeper特性：
一致性：数据(最终)一致性，数据按照顺序分批入库  
原子性：事务要么成功要么失败，不会局部化  
单一视图：客户端连接zookeeper集群中的任一节点，数据都是一致的 
可靠性：每次对zk的操作都会保存在服务端  
实时性：客户端可以读取到zk服务端的最新数据 

zookeeper目录结构：  
bin:常用命令 .cmd  .sh  
conf:配置文件  zoo.cfg  
zookeeper-contrib:附加功能  
dist-maven:maven编译后的目录 包括jar包，pom文件等
lib:依赖的jar包
zookeeper-recipes：案例代码  
src: 源码

配置zoo.cfg 文件：
tickTime:用于计算的基本时间单元  N*tickTime  
initLimit: 允许从节点连接并同步到主节点的初始化连接时间 （用于集群）
syncLimit：master主节点和从节点的请求和应答机制（心跳机制，超过一个时间后从节点被抛弃）
dataDir：zk存储的数据位置  
dataLogDir：日志目录
clientPort：客户端连接服务端的端口


基本数据模型：
树形结构：  
每一个节点称为znode,它可以有子节点，也可以有数据    
每个节点分为临时节点和永久节点（持久化），临时节点在客户端断开后消失  
每个zk节点都有各自的版本号，可以通过命令行来显示节点信息    
每当节点数据发生变化时，该节点的版本号会累加  
删除/修改过时的节点，版本号不匹配会报错  
每个zk节点存储的数据不会过大  
节点可以设置acl（权限控制）,可以通过权限来控制用户访问  



作用：  
master节点选取：主节点挂了后，从节点就会接手工作，并且保证这个节点唯一  (首脑模式)  
统一配置文件管理：只需要部署一台服务器，则可以把相同的配置文件更新到其他所有服务器中  
发布与订阅：类似于消息队列mq,发布者把数据存在znode上，订阅者会读取这个数据  
提供了分布式锁：分布式环境中不同进程之间竞争资源，类似于多线程的锁  
集群管理：集群中保持数据的强一致性（主节点的数据同步到其他节点）

常用命令：
建立节点   create /zk  hello   （默认创建的为非顺序，持久化节点）
         -e 创建临时节点
         -s 创建顺序节点     
获得节点  get /zk 
设置（修改）节点 set /zk hello2 version   
        版本号用于验证当前修改是否合法
建立子节点  set /zk/subzk hello3
输出节点目录 ls /zk
删除节点  delete /zk version
        版本号用于验证当前修改是否合法
~~~text
       常用命令
       stat path [watch]
       set path data [version]
       ls path [watch]
       delquota [-n|-b] path
       ls2 path [watch]
       setAcl path acl
       setquota -n|-b val path
       history
       redo cmdno
       printwatches on|off
       delete path [version]
       sync path
       listquota path
       rmr path
       get path [watch]
       create [-s] [-e] path data acl
       addauth scheme auth
       quit
       getAcl path
       close
       connect host:port
~~~  

watcher机制  
事件监听，客户端向服务端注册一个watcher,当服务点节点数据或者子节点发生变化时，服务端会通知客户端，客户端进而进行相应处理  
watcher事件被单次触发后，事件就失效了
watche事件分为不同的事件类型，节点（数据）发生变化时会产品不同的事件类型。而客户端只会接收到事件通知和事件类型，具体改变内容不会通知到客户端


事件类型：NodeCreated  NodeDataChanged  NodeDeleted  NodeChildrenChanged（新增，删除子节点;修改子节点不会触发事件,需要为子节点单独设置watcher才会有事件触发）
使用场景：统一资源配置  

ACL  
权限字符串：c(创建子节点)r(读)d(写)w(删除)a(admin)  
world:anyone:权限字符串
getAcl path 
setAcl path world:anyone:权限字符串

auth:

digest:

ip:

集群配置：
Leader选举 
 ~~~
 https://zhuanlan.zhihu.com/p/60083015
 ~~~
 
 


    
