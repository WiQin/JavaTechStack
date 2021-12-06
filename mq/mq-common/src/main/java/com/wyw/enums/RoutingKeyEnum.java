package com.wyw.enums;

import jdk.nashorn.internal.ir.UnaryNode;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/20 13:51
 */
public enum RoutingKeyEnum {
    ROUTING_KEY_1("routing_key_1","路由策略1"),
    ROUTING_KEY_2("routing_key_2","路由策略2"),
    UNKNOWN("unkonwn","未知");

    private String code;
    private String name;

    RoutingKeyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    RoutingKeyEnum valueOfCode(String code) {
        for (RoutingKeyEnum value : values()) {
            if (!value.getCode().equals(code)) {
                break;
            }
            return value;
        }
        return UNKNOWN;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
