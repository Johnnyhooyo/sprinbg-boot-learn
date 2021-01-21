package com.hyq.file;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author：huyuanqiang
 * @time: 2020-05-21 19:03
 * @description:
 **/
@Component
public class PostProcessTest implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return bean;
    }
}
