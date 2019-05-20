package com.hyq.zookeeper.core.service;

import com.hyq.zookeeper.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author dibulidohu
 * @classname ServiceProvider
 * @date 2019/5/2015:41
 * @description
 */
@Slf4j
@Component
public class ServiceProvider implements BeanPostProcessor {

    @Autowired
    ServiceRegistry serviceRegistry;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        String errorMethod = "";
        try {
            if (bean.getClass().getAnnotation(RestController.class) != null) {
                String[] value = bean.getClass().getAnnotation(RequestMapping.class).value();
                Method[] declaredMethods = bean.getClass().getDeclaredMethods();
                for (Method method : declaredMethods) {
                    errorMethod = method.getName();
                    if (method.getAnnotation(ZooService.class) != null) {
                        String[] value1 = method.getAnnotation(RequestMapping.class).value();
                        String port = null == System.getProperty("port") ? "8080" : System.getProperty("port");
                        String servicePath = IPUtil.LOCAL_IP_ADDRESS + ":" + port + value[0] + value1[0];
                        ServiceInfo serviceInfo = new ServiceInfo();
                        serviceInfo.setServiceAddr(servicePath);
                        serviceInfo.setServiceDesc(method.getAnnotation(ZooService.class).serviceDesc());
                        serviceInfo.setServiceName(method.getAnnotation(ZooService.class).serviceName());
                        serviceInfo.setInstanceId(UUID.randomUUID().toString());
                        serviceRegistry.registerService(method.getAnnotation(ZooService.class).serviceName(), serviceInfo.toByte());
                    }
                }
            }
        } catch (SecurityException e) {
            log.error("method {} registry to zookeeper error:{}", errorMethod, e);
        }
        return bean;
    }

}
