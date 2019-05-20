package com.hyq.zookeeper.core.service;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ZooService {
    //Class<?> value();
    String serviceName();
    String serviceDesc();
}
