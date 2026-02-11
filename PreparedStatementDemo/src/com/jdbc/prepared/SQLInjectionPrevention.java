package com.jdbc.prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * SQL Injection Prevention Demo
 * Shows how PreparedStatement prevents SQL injection attacks
 */
public class SQLInjectionPrevention {
    
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASS = "password";
    
    public static void main(String[] args) {
        // Simulating a malicious input
        String maliciousInput = "' OR '1'='1";
        
        System.out.println("Testing with potentially malicious input: " + maliciousInput);
        System.out.println();
        
        // Safe approach using PreparedStatement
        demonstrateSafeQuery(maliciousInput);
    }
    
    /**
     * PreparedStatement safely handles special characters
     */
    private static void demonstrateSafeQuery(String userInput) {
        String sql = "SELECT * FROM employees WHERE name = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // PreparedStatement treats the input as a literal string value
            pstmt.setString(1, userInput);
            
            var rs = pstmt.executeQuery();
            
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println("Found: " + rs.getString("name"));
            }
            
            if (count == 0) {
                System.out.println("No records found - PreparedStatement prevented SQL injection!");
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
