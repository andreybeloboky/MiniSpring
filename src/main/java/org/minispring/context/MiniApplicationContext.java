package org.minispring.context;

import org.minispring.annotation.Component;

import java.util.HashMap;
import java.util.Map;

public class MiniApplicationContext {

    private final Map<Class<?>, Object> beans = new HashMap<>();

    public MiniApplicationContext(String basePackage) {
        scanPackage(basePackage);
    }

    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(beans.get(clazz));
    }

    private void scanPackage(String basePackage) {
        try {
            Class<?> clazz = Class.forName(basePackage + ".ServiceA");

            if (clazz.isAnnotationPresent(Component.class)) {
                Object instance = clazz.getDeclaredConstructor().newInstance();

                beans.put(clazz, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
