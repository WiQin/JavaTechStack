package com.wyw.zookeeper.callback;

import org.apache.zookeeper.AsyncCallback;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/08
 */
public class CreateCallback implements AsyncCallback.StringCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("创建节点: " + path);
        System.out.println((String)ctx);
    }
}
