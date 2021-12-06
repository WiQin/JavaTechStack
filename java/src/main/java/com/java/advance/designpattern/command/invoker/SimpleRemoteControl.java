package com.java.advance.designpattern.command.invoker;

import com.java.advance.designpattern.command.Command;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/12/6 0006 22:02
 */
public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl() {
    }

    public SimpleRemoteControl(Command slot) {
        this.slot = slot;
    }

    public void setCommand(Command command) {
        this.slot = command;
    }

    public void invoke() {
        slot.execute();
    }
}
