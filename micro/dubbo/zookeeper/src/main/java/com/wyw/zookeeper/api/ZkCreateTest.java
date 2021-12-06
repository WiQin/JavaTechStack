package com.wyw.zookeeper.api;

import com.wyw.zookeeper.callback.Children2CallBack;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 节点查询
 *
 * @author wyw
 * @date 2021/02/08
 */
public class ZkCreateTest implements Watcher {

    private static final String connectPath = "127.0.0.1:2181";

    private static CountDownLatch countDown = new CountDownLatch(1);
    private static Stat stat = new Stat();
    private ZooKeeper zk;

    public ZkCreateTest() {
    }

    public ZkCreateTest(String serverpath) {
        try {
            zk = new ZooKeeper(serverpath, 5000, new ZkCreateTest());
        } catch (IOException e) {
            e.printStackTrace();
            if (zk != null) {
                try {
                    zk.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取节点数据
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    private static void getNodeData() throws KeeperException, InterruptedException {
        ZkCreateTest zkServer = new ZkCreateTest(connectPath);
        byte[] data = zkServer.getZk().getData("/wyw", true, stat);
        String result = new String(data);
        System.out.println("获取到节点数据：" + result);
        countDown.await();
    }

    /**
     * 获取子节点
     */
    private static void getChildNode() throws InterruptedException {
        ZkCreateTest zkServer = new ZkCreateTest(connectPath);

        /**
         * 参数：
         * path：父节点路径
         * watch：true或者false，注册一个watch事件
         */
//		List<String> strChildList = zkServer.getZookeeper().getChildren("/imooc", true);
//		for (String s : strChildList) {
//			System.out.println(s);
//		}

        // 异步调用
        String ctx = "{'callback':'ChildrenCallback'}";
//		zkServer.getZk().getChildren("/wyw", true, new ChildrenCallBack(), ctx);
        zkServer.getZk().getChildren("/wyw", true, new Children2CallBack(), ctx);

        countDown.await();
    }

    /**
     * 判断节点是否存在
     */
    private static void isExists() throws KeeperException, InterruptedException {
        ZkCreateTest zkServer = new ZkCreateTest(connectPath);

        /**
         * 参数：
         * path：节点路径
         * watch：watch
         */
        Stat stat = zkServer.getZk().exists("/wyw", true);
        if (stat != null) {
            System.out.println("查询的节点版本为dataVersion：" + stat.getVersion());
        } else {
            System.out.println("该节点不存在...");
        }

        countDown.await();
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        getNodeData();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if (Event.EventType.NodeDataChanged.equals(watchedEvent.getType())) {
                ZkCreateTest zkServer = new ZkCreateTest(connectPath);
                System.out.println("节点内容发生变化");
                byte[] resByte = zkServer.getZk().getData("/wyw", false, stat);
                String result = new String(resByte);
                System.out.println("更改后的值:" + result);
                System.out.println("版本号变化dversion：" + stat.getVersion());
                countDown.countDown();
            } else if (Event.EventType.NodeChildrenChanged.equals(watchedEvent.getType())) {
                System.out.println("NodeChildrenChanged");
                ZkCreateTest zkServer = new ZkCreateTest(connectPath);
                List<String> strChildList = zkServer.getZk().getChildren(watchedEvent.getPath(), false);
                for (String s : strChildList) {
                    System.out.println(s);
                }
                countDown.countDown();
            } else if (Event.EventType.NodeCreated.equals(watchedEvent.getType())) {
                System.out.println("节点创建");
                countDown.countDown();
            } else if (Event.EventType.NodeDataChanged.equals(watchedEvent.getType())) {
                System.out.println("节点数据改变");
                countDown.countDown();
            } else if (Event.EventType.NodeDeleted.equals(watchedEvent.getType())) {
                System.out.println("节点删除");
                countDown.countDown();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }
}
