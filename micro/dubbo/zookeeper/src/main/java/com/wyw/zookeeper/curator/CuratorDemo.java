package com.wyw.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Curator框架
 *
 * @author wyw
 * @date 2021/02/08
 */
public class CuratorDemo {
    /**
     * 会话超时时间
     */
    private final int SESSION_TIMEOUT = 30000;
    /**
     * 连接超时时间
     */
    private final int CONNECT_TIMEOUT = 3000;
    /**
     * 服务地址
     */
    private static final String SERVER = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1；2183";
    /**
     * 连接实例
     */
    private CuratorFramework client = null;
    /**
     * baseSleepTimeMs：初始的重试等待时间
     * maxRetries：最多重试次数
     */
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);



}
