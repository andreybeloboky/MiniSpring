package org.minispring;

import org.minispring.context.MiniApplicationContext;

public class App {
    public static void main(String[] args) {
        MiniApplicationContext context = new MiniApplicationContext("org.minispring.service");
    }
}
