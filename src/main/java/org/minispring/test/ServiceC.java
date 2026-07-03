package org.minispring.test;

import org.minispring.annotation.Autowired;
import org.minispring.annotation.Component;
import org.minispring.annotation.InitializingBean;
import org.minispring.service.ServiceA;

@Component
public class ServiceC implements InitializingBean {
    @Autowired
    private ServiceA serviceA;

    public void run() {
        serviceA.hello();
        System.out.println("ServiceC is running");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("ServiceC initialized after!");
    }
}
