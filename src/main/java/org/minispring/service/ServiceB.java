package org.minispring.service;

import org.minispring.annotation.Autowired;
import org.minispring.annotation.Component;
import org.minispring.annotation.InitializingBean;

@Component
public class ServiceB implements InitializingBean {
    @Autowired
    private ServiceA serviceA;

    public void run() {
        serviceA.hello();
        System.out.println("ServiceB is running");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("ServiceB готов к работе!");
    }
}
