# Dubbo <!-- {docsify-ignore} -->
## 基础知识
### 核心角色
Dubbo架构核心角色:<br/>
Registry:**注册中心**,负责服务地址的注册与查找。服务的provider与consumer只在启动时与注册中心交互，注册中心通过长连接感知provider的存在；
当服务中心宕机时，注册中心会立即推送相关事件通知consumer<br> 
Provider:**服务提供者**。启动时向registry进行注册操作，将自己服务的地址和相关配置封装成url添加到注册中心<br/>
Consumer:**服务消费者**。在它启动的时候，会向 Registry 进行订阅操作。订阅操作会从 ZooKeeper 中获取 Provider 注册的 URL，并在 ZooKeeper 中添加相应的监听器。获取到 Provider URL 之后，Consumer 会根据负载均衡算法从多个 Provider 中选择一个 Provider 并与其建立连接，最后发起对 Provider 的 RPC 调用。 如果 Provider URL 发生变更，Consumer 将会通过之前订阅过程中在注册中心添加的监听器，获取到最新的 Provider URL 信息，进行相应的调整，比如断开与宕机 Provider 的连接，并与新的 Provider 建立连接。Consumer 与 Provider 建立的是长连接，且 Consumer 会缓存 Provider 信息，所以一旦连接建立，即使注册中心宕机，也不会影响已运行的 Provider 和 Consumer。
Monitor:**监控中心**。用于统计服务的调用次数和调用时间。Provider 和 Consumer 在运行过程中，会在内存中统计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。监控中心在上面的架构图中并不是必要角色，监控中心宕机不会影响 Provider、Consumer 以及 Registry 的功能，只会丢失监控数据而已。

dubbo暴露的url解析：
dubbo://192.168.31.33:20880/com.wyw.micro.api.UserService?anyhost=true&application=dubbo-provider&dubbo=2.5.6&generic=false&interface=com.wyw.micro.api.UserService&methods=getUserInfo&pid=12116&side=provider&timestamp=1606745309143,
1).dubbo://192.168.31.33:20880/com.wyw.micro.api.UserService 协议：//ip:端口/接口
2).配置的参数

### 配置总线  
每个信息资源在网络上都有唯一的url（统一资源定位符）,而url是dubbo中的一个核心组件，也被称为配置总线  
Dubbo使用url来统一描述所有对象和配置信息，示例如上。  
优点：1).方便进行上下文传递，代码更加易读易懂；
2).使用url作为方法入参，便于扩展追加参数；
3).降低沟通成本。

使用实例：
1.Dubbo SPI 适配器方法  与@Adaptive注解一起选择合适的扩展实现类  
 示例：RegistryFactory 中getRegistry()根据protocol确定扩展名称，从而确定使用的具体扩展实现类 
 org.apache.dubbo.registry.integration.RegistryProtocol#getRegistry  调用RegistryFactory.getRegistry()方法，根据url的protocol选择适配
2.服务暴露  
示例：org.apache.dubbo.registry.zookeeper.ZookeeperRegistry#doRegister(URL url)  
传入的url包含的提供方地址，暴露接口等信息  toUrlPath() 方法会根据传入的 URL 参数确定在 ZooKeeper 上创建的节点路径，还会通过 URL 中的 dynamic 参数值确定创建的 ZNode 是临时节点还是持久节点。
3.服务订阅
Consumer 启动后会向注册中心进行订阅操作，并监听自己关注的 Provider  
示例：org.apache.dubbo.registry.zookeeper.ZookeeperRegistry#doSubscribe  
ZookeeperRegistry 会在 toCategoriesPath() 方法中将其整理成一个 ZooKeeper 路径，然后调用 zkClient 在其上添加监听。






