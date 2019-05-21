package com.hyq.zookeeper.core.service;

/**
 * @author dibulidohu
 * @classname ServiceEnum
 * @date 2019/5/219:17
 * @description
 */
public enum  ServiceEnum {
    DEMO_SERVICE("HYQ.ZOO.HELLO", "哈喽用例"),
    ;
    private String name;
    private String desc;

    ServiceEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
