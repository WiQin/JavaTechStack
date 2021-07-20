package com.wyw.enums;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/19 17:11
 */
public enum ExchangeEnum {

    FANOOUT_INFORM("exchange_fanout_form","fanout交换机","fanout"),
    DIRECT_INFORM("exchange_direct_form","direct交换机","fanout"),
    UNKNOWN("unkonwn","未知","unknown");


    private String code;
    private String name;
    private String type;

    ExchangeEnum(String code, String name, String type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    public static ExchangeEnum valueOfCode(String code) {
        for (ExchangeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
