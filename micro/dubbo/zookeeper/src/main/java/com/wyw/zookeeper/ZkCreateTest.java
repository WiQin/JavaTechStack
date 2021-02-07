package com.wyw.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * 创建zk节点
 *
 * @author wyw
 * @date 2021/02/07
 */
public class ZkCreateTest implements Watcher {
    private static final String ADDRESS = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1；2183delet";
    
    private ZooKeeper zk;

    public ZkCreateTest() {
    }

    public ZkCreateTest(String address) {
        try {
            zk = new ZooKeeper(address,5000,new ZkCreateTest());
        } catch (IOException e) {
            e.printStackTrace();
            if (null != zk) {
                try {
                    zk.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建zk节点
     * 
     * 同步或者异步创建节点，都不支持子节点的递归创建，异步有一个callback函数
     * 参数：
     * path：创建的路径
     * data：存储的数据的byte[]
     * acl：控制权限策略
     * 			Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
     * 			CREATOR_ALL_ACL --> auth:user:password:cdrwa
     * createMode：节点类型, 是一个枚举
     * 			PERSISTENT：持久节点
     * 			PERSISTENT_SEQUENTIAL：持久顺序节点
     * 			EPHEMERAL：临时节点
     * 			EPHEMERAL_SEQUENTIAL：临时顺序节点
     * @param path
     * @param data
     * @param acls
     */
    protected void create(String path, byte[] data, List<ACL> acls) {
        String result = "";

        try {
            result = zk.create(path, data, acls, CreateMode.PERSISTENT);
            Thread.sleep(2000);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkCreateTest zkServer = new ZkCreateTest(ADDRESS);
        //创建节点
        //zkServer.create("/test" ,"2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);
        //修改节点
        Stat stat = zkServer.getZk().setData("/test", "data".getBytes(), 0);
        System.out.println(stat);

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }
}
