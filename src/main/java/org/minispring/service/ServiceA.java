package org.minispring.service;

import org.minispring.annotation.Component;
import org.minispring.annotation.InitializingBean;

@Component
public class ServiceA implements InitializingBean {
    public void hello() {
        System.out.println("Hello from ServiceA");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("ServiceA initialized after!");
    }
}
