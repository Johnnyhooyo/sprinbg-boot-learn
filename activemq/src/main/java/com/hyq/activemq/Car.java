package com.hyq.activemq;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dibulidohu
 * @classname Car
 * @date 2019/4/1910:40
 * @description
 */
@Data
public class Car implements Serializable {
    private Integer id;
    private String name;
    private BigDecimal price;
}
