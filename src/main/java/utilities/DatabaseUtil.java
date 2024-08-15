package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    public static Connection getConnection(String JDBC_DRIVER,String DB_URL,String USER,String PASS) {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            if (connection != null) {
                System.out.println("Database Connection Successful");
            } else {
                System.err.println("Database Connection Failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        }
        return connection;
    }

    public static void executeQuery(Connection connection, String sql, String[] fields) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<String[]> results = processResultSet(resultSet, fields);
            for (String[] row : results) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    sb.append(fields[i]).append(": ").append(row[i]).append("\n");
                }
                System.out.println(sb.toString().trim());
                System.out.println("-------------");
            }

        } catch (SQLException e) {
            System.err.println("Error executing query");
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    private static List<String[]> processResultSet(ResultSet resultSet, String[] fields) throws SQLException {
        List<String[]> results = new ArrayList<>();
        while (resultSet.next()) {
            String[] row = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                row[i] = resultSet.getString(fields[i]);
            }
            results.add(row);
        }
        return results;
    }

    // Close the connection and other resources
    public static void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing resources");
            e.printStackTrace();
        }
    }
}
