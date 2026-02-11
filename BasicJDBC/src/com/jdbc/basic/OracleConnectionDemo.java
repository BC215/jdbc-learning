package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Basic JDBC Connection Demo using Oracle Database
 * This demonstrates the fundamental steps for connecting to Oracle DB
 */
public class OracleConnectionDemo {
    
    // Database connection parameters
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASS = "password";
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Step 1: Load the Oracle JDBC driver (optional for JDBC 4.0+)
            // Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Step 2: Establish connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected successfully!");
            
            // Step 3: Create a statement
            stmt = conn.createStatement();
            
            // Step 4: Execute a query
            String sql = "SELECT SYSDATE FROM DUAL";
            rs = stmt.executeQuery(sql);
            
            // Step 5: Process the result set
            while (rs.next()) {
                System.out.println("Current Database Date/Time: " + rs.getDate(1));
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Step 6: Clean up resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("Connection closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
