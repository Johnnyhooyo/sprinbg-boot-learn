package com.hyq.lettuce;

import java.io.Serializable;

/**
 * @author dibulidohu
 * @classname User
 * @date 2019/4/915:57
 * @description
 */
public class User implements Serializable {
    private Long id;
    private String name;
    private String desc;

    public User() {
    }

    public User(Long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
