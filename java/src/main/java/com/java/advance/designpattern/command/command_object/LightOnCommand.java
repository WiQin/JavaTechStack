package com.java.advance.designpattern.command.command_object;

import com.java.advance.designpattern.command.Command;
import com.java.advance.designpattern.command.receiver.Light;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/12/6 0006 21:56
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }


    @Override
    public void execute() {
        light.on();
    }
}
