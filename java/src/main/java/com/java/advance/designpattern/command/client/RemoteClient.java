package com.java.advance.designpattern.command.client;

import com.java.advance.designpattern.command.command_object.LightOnCommand;
import com.java.advance.designpattern.command.receiver.Light;
import com.java.advance.designpattern.command.invoker.SimpleRemoteControl;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/12/6 0006 22:03
 */
public class RemoteClient {
    public static void main(String[] args) {
        //调用者，可以用来发出请求
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();
        //命令传给调用者
        simpleRemoteControl.setCommand(
                //创建命令
                new LightOnCommand(new Light())
        );
        simpleRemoteControl.invoke();
    }
}
