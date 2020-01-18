package ru.dragosh.tm.util;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/tmse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Error during connecting to database!");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error during load mysql Driver!");
            e.printStackTrace();
        }
    }

    @NotNull
    public static Connection getConnection() {
        return connection;
    }
}
