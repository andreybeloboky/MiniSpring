package org.minispring.context;

import org.minispring.annotation.Autowired;
import org.minispring.annotation.Component;
import org.minispring.annotation.InitializingBean;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
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
            String path = basePackage.replace('.', '/');
            URL resource = Thread.currentThread().getContextClassLoader().getResource(path);

            if (resource == null) {
                throw new RuntimeException("Package " + basePackage + " isn't found in classpath");
            }

            File directory = new File(resource.getFile());

            for (File file : directory.listFiles()) {
                if (file.getName().endsWith(".class")) {
                    String className = basePackage + "." + file.getName().replace(".class", "");
                    Class<?> clazz = Class.forName(className);

                    if (clazz.isAnnotationPresent(Component.class)) {
                        Object instance = clazz.getDeclaredConstructor().newInstance();

                        injectDependencies(instance);

                        initializeBean(instance);

                        beans.put(clazz, instance);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error when scanning a package: " + basePackage, e);
        }
    }

    private void injectDependencies(Object bean) {
        Class<?> clazz = bean.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dependency = beans.get(field.getType());
                if (dependency != null) {
                    field.setAccessible(true);
                    try {
                        field.set(bean, dependency);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void initializeBean(Object bean) {
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
    }
}
