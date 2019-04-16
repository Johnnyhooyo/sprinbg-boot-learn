package com.hyq.learning.appcontext;

import org.springframework.context.ApplicationContext;

import java.util.Properties;

/**
 * @author dibulidohu
 * @classname Context
 * @date 2019/4/1613:15
 * @description
 */
public class Context {

    public void read() {
        ApplicationContext applicationContext = AppContextBean.getApplicationContext();
        Properties properties = System.getProperties();
    }
}
