package ru.dragosh.tm;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dragosh.tm.bootstrap.Bootstrap;

public final class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        @NotNull Bootstrap bootstrap = (Bootstrap) applicationContext.getBean("bootstrapBean");
        bootstrap.init();
    }
}