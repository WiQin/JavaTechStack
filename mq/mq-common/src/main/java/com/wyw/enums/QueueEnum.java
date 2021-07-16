package com.wyw.enums;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/16 14:59
 */
public enum QueueEnum {

    BAISC("基本测试队列","test-basic"),
    WORK_QUEUE("工作模式测试队列","test-queue-work"),
    UNKNOWN("未知","UNKNOWN");

    private String name;
    private String code;

    QueueEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public static QueueEnum valueOfCode(String code) {
        for (QueueEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
