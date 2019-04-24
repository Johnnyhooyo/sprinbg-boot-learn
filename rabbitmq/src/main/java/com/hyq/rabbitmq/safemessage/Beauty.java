package com.hyq.rabbitmq.safemessage;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dibulidohu
 * @classname Bueaty
 * @date 2019/4/2310:03
 * @description
 */
@Data
public class Beauty implements Serializable {
    private String name;
    private String introduce;
    private int age;
    private double weight;
}
