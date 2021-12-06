package com.wyw.zookeeper.callback;

import org.apache.zookeeper.AsyncCallback;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/08
 */
public class DeleteCallback implements AsyncCallback.VoidCallback {

    @Override
    public void processResult(int rc, String path, Object ctx) {
        System.out.println("删除节点" + path);
        System.out.println((String)ctx);
    }
}
