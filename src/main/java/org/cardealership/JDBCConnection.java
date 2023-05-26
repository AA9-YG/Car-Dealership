package org.cardealership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/car_db";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "1234";


    public static Connection getConnection() {

        Connection connection = null;

        // Load the JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to database
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();;
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }

}
