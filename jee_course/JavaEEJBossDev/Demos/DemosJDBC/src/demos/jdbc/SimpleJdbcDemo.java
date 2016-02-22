package demos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleJdbcDemo {

    public static void main(String[] args) {
        
        // Use database name if provided, else use "jdbc:odbc:Employees"
        String dbName;
        if (args.length == 1) {
            dbName = args[0];
        } else {
            dbName = "jdbc:odbc:Employees";
        }


        // Load JDBC/ODBC bridge driver.
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC/ODBC driver: " + e);
        }


        // Connect to a database.
        @SuppressWarnings("unused")
        Connection cnEmps = null;
        try {
            cnEmps = DriverManager.getConnection(dbName);
            System.out.println("Hooray!");
        } catch (SQLException e) {
            System.out.println("Error connecting to a database: " + e);
        }

    }
}
