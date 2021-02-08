package com.wyw.zookeeper.api;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/07
 */
public class ZkConnectionTest implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(ZkConnectionTest.class);
    private static final String ADDRESS = "127.0.0.1:2181";

    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive the event : " + event);
    }

    /**
     * 客户端和zk服务端链接是一个异步的过程
     * 当连接成功后后，客户端会收的一个watch通知
     * 
     * 参数：
     * connectString：连接服务器的ip字符串，
     * 		比如: "192.168.1.1:2181,192.168.1.2:2181,192.168.1.3:2181"
     * 		可以是一个ip，也可以是多个ip，一个ip代表单机，多个ip代表集群
     * 		也可以在ip后加路径
     * sessionTimeout：超时时间，心跳收不到了，那就超时
     * watcher：通知事件，如果有对应的事件触发，则会收到一个通知；如果不需要，那就设置为null
     * canBeReadOnly：可读，当这个物理机节点断开后，还是可以读到数据的，只是不能写，
     * 					   此时数据被读取到的可能是旧数据，此处建议设置为false，不推荐使用
     * sessionId：会话的id
     * sessionPasswd：会话密码	当会话丢失后，可以依据 sessionId 和 sessionPasswd 重新获取会话
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ZooKeeper zk = new ZooKeeper(ADDRESS, 5000, new ZkConnectionTest());
        System.out.println("zookeeper start to connect:"+zk.getState());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long sessionId = zk.getSessionId();
        byte[] passwd = zk.getSessionPasswd();
        System.out.println("zk connect state:"+ zk.getState()+":"+zk.getSessionId());

        System.out.println("-----------");
        System.out.println("开始重连会话");

        //回复之前会话，注意传参
        ZooKeeper zooKeeper = new ZooKeeper(ADDRESS, 5000, new ZkConnectionTest(), sessionId, passwd);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zk reconnect state:"+ zk.getState()+":"+zooKeeper.getSessionId());
    }




}
