package ru.dragosh.tm.util;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.repository.ProjectRepository;
import ru.dragosh.tm.repository.SessionRepository;
import ru.dragosh.tm.repository.TaskRepository;
import ru.dragosh.tm.repository.UserRepository;

import javax.sql.DataSource;

public class MyBatisUtil {
    @NotNull
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    @NotNull
    private static final String URL = "mysql:jdbc://localhost:3306/";
    @NotNull
    private static final String USERNAME = "root";
    @NotNull
    private static final String PASSWORD = "root";

    public static SqlSessionFactory getSqlSessionFactory() {
        try {
            final DataSource dataSource = new PooledDataSource(DRIVER, URL, USERNAME, PASSWORD);
            final TransactionFactory transactionFactory = new JdbcTransactionFactory();
            final Environment environment = new Environment("development", transactionFactory, dataSource);
            final Configuration configuration = new Configuration(environment);
            configuration.addMapper(ProjectRepository.class);
            configuration.addMapper(TaskRepository.class);
            configuration.addMapper(UserRepository.class);
            configuration.addMapper(SessionRepository.class);
            return new SqlSessionFactoryBuilder().build(configuration);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
