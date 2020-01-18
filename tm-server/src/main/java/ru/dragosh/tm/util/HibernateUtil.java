package ru.dragosh.tm.util;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.User;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {
    @NotNull
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    @NotNull
    private static final String URL = "jdbc:mysql://localhost:3306/tmse?serverTimezone=UTC";
    @NotNull
    private static final String USERNAME = "root";
    @NotNull
    private static final String PASSWORD = "root";

    public static javax.persistence.EntityManagerFactory factory() {
        final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, DRIVER);
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, USERNAME);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}
