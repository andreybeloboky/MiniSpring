package org.minispring.service;

import org.minispring.annotation.Component;

@Component
public class ServiceA {
    public void hello() {
        System.out.println("Hello from ServiceA");
    }
}
