package org.minispring.service;

import org.minispring.annotation.Autowired;
import org.minispring.annotation.Component;

@Component
public class ServiceB {
    @Autowired
    private ServiceA serviceA;

    public void run() {
        serviceA.hello();
        System.out.println("ServiceB is running");
    }
}
