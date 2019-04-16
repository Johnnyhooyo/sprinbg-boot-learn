package com.hyq.learning.appcontext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * @author dibulidohu
 * @classname AppContext
 * @date 2019/4/1612:08
 * @description
 */
@Component
public class AppContextBean implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public synchronized static ApplicationContext getApplicationContext() {
        return context;
    }
}
