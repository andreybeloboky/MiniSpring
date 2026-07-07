package org.minispring;

import org.junit.jupiter.api.Test;
import org.minispring.annotation.Autowired;
import org.minispring.context.MiniApplicationContext;
import org.minispring.service.ServiceA;
import org.minispring.service.ServiceB;
import org.minispring.test.ServiceC;

import java.lang.reflect.Field;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MiniApplicationContextTest {

    @Test
    void contextLoadsTest() {
        MiniApplicationContext context = new MiniApplicationContext("org.minispring.service");
        assertNotNull(context.getBean(ServiceA.class));
        ServiceB b = context.getBean(ServiceB.class);
        b.run();
        assertNotNull(b);
        assertNull(context.getBean(ServiceC.class));
    }

    @Test
    void packageNotFoundThrowsTest() {
        assertThrows(RuntimeException.class, () -> new MiniApplicationContext("com.minispring.nonexistent"));
    }

    @Test
    void dependencyInjectedTest() {
        MiniApplicationContext context = new MiniApplicationContext("org.minispring.service");
        ServiceB controller = context.getBean(ServiceB.class);
        assertNotNull(controller);
        Class<?> object = controller.getClass();
        for (Field field : object.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                assertNotNull(field);
            }
        }
    }

    @Test
    void lifecycleCallbackWorksTest() {
        MiniApplicationContext context = new MiniApplicationContext("org.minispring.service");
        ServiceB controller = context.getBean(ServiceB.class);
        assertTrue(controller.isInitialized());
    }
}
