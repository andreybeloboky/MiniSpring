package org.minispring;

import org.minispring.context.MiniApplicationContext;
import org.minispring.service.ServiceB;

public class App {
    public static void main(String[] args) {
        MiniApplicationContext context = new MiniApplicationContext("org.minispring.service");
    }
}
