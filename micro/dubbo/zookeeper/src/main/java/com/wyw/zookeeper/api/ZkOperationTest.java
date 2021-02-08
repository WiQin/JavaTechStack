package com.wyw.zookeeper.api;

import com.wyw.zookeeper.callback.CreateCallback;
import com.wyw.zookeeper.callback.DeleteCallback;
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
public class ZkOperationTest implements Watcher {
    private static final String ADDRESS = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1；2183";
    
    private ZooKeeper zk;

    public ZkOperationTest() {
    }

    public ZkOperationTest(String address) {
        try {
            zk = new ZooKeeper(address,5000,new ZkOperationTest());
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
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步创建
     * @param path
     * @param data
     * @param acls
     */
    private void createAsync(String path, byte[] data, List<ACL> acls) throws InterruptedException {
        String result = "";

        String ctx = "test_callback";
        //ctx为object类型，可以做相关操作
        zk.create(path, data, acls, CreateMode.PERSISTENT, new CreateCallback(),ctx);
        Thread.sleep(2000);
    }


    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkOperationTest zkServer = new ZkOperationTest(ADDRESS);
        //创建节点(同步)
//        zkServer.create("/test" ,"2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        //创建节点（异步）
//        zkServer.createAsync("/test","asyn".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE);

        //修改节点
        Stat stat = zkServer.getZk().setData("/wyw", "update".getBytes(), 0);
        System.out.println(stat.getVersion());

        //删除节点(没有返回state参数，建议使用异步方式进行回调)
        zkServer.getZk().delete("/wyw",1);

        //异步方式删除
        String deleteCtx = "delete success";
        zkServer.getZk().delete("/wyw",1,new DeleteCallback(),deleteCtx);

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
