package ru.dragosh.tm;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import ru.dragosh.tm.api.*;
import ru.dragosh.tm.bootstrap.Bootstrap;
import ru.dragosh.tm.endpoint.*;
import ru.dragosh.tm.repository.ProjectRepository;
import ru.dragosh.tm.repository.SessionRepository;
import ru.dragosh.tm.repository.TaskRepository;
import ru.dragosh.tm.repository.UserRepository;
import ru.dragosh.tm.service.ProjectServiceImplement;
import ru.dragosh.tm.service.SessionServiceImplement;
import ru.dragosh.tm.service.TaskServiceImplement;
import ru.dragosh.tm.service.UserServiceImplement;
import ru.dragosh.tm.service.serializer.*;
import ru.dragosh.tm.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
public class ApplicationConfiguration {
    @Bean(name = "bootstrapBean")
    public Bootstrap getBootstrap() {
        return new Bootstrap();
    }

    @Bean(name = "serviceLocatorBean")
    public ServiceLocator getServiceLocator() {
        return new Bootstrap();
    }

    @Bean
    public DataBinEndPoint getDataBinEndPoint() {
        return new DataBinEndPointImplement();
    }

    @Bean
    public FasterJsonEndPoint getFasterJsonEndPoint() {
        return new FasterJsonEndPointImplement();
    }

    @Bean
    public FasterXmlEndPoint getFasterXmlEndPoint() {
        return new FasterXmlEndPointImplement();
    }

    @Bean
    public JaxbJsonEndPoint getJaxbJsonEndPoint() {
        return new JaxbJsonEndPointImplement();
    }

    @Bean
    public JaxbXmlEndPoint getJaxbXmlEndPoint() {
        return new JaxbXmlEndPointImplement();
    }

    @Bean
    public ProjectEndPoint getProjectEndPoint() {
        return new ProjectEndPointImplement();
    }

    @Bean
    public TaskEndPoint getTaskEndPoint() {
        return new TaskEndPointImplement();
    }

    @Bean
    public UserEndPoint getUserEndPoint() {
        return new UserEndPointImplement();
    }

    @Bean
    public ProjectService getProjectService() {
        return new ProjectServiceImplement();
    }

    @Bean
    public TaskService getTaskService() {
        return new TaskServiceImplement();
    }

    @Bean
    public UserService getUserService() {
        return new UserServiceImplement();
    }

    @Bean
    public SessionService getSessionService() {
        return new SessionServiceImplement();
    }

    @Bean
    public DataBinServiceImplement getDataBinService() {
        return new DataBinServiceImplement();
    }

    @Bean
    public FasterJsonServiceImplement getFasterJsonService() {
        return new FasterJsonServiceImplement();
    }

    @Bean
    public FasterXmlServiceImplement getFasterXmlService() {
        return new FasterXmlServiceImplement();
    }

    @Bean
    public JaxbJsonServiceImplement getJaxbJsonService() {
        return new JaxbJsonServiceImplement();
    }

    @Bean
    public JaxbXmlServiceImplement getJaxbXmlService() {
        return new JaxbXmlServiceImplement();
    }

    @Bean(name = "entityManagerBean")
    public EntityManager getEntityManager() {
        return HibernateUtil.factory().createEntityManager();
    }

    @Bean
    public ProjectRepository getProjectRepository(@Qualifier("entityManagerBean") EntityManager entityManager) {
        return new ProjectRepository(entityManager);
    }

    @Bean
    public TaskRepository getTaskRepository(@Qualifier("entityManagerBean") EntityManager entityManager) {
        return new TaskRepository(entityManager);
    }

    @Bean
    public UserRepository getUserRepository(@Qualifier("entityManagerBean") EntityManager entityManager) {
        return new UserRepository(entityManager);
    }

    @Bean
    public SessionRepository getSessionRepository(@Qualifier("entityManagerBean") EntityManager entityManager) {
        return new SessionRepository(entityManager);
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource =
                new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/tmse?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final DataSource dataSource
    ) {
        final LocalContainerEntityManagerFactoryBean factoryBean;
        factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("ru.dragosh.tm");
        final Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            final LocalContainerEntityManagerFactoryBean emf
    ) {
        final JpaTransactionManager transactionManager =
                new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }
}
