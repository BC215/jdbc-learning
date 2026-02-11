package com.jdbc.prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * PreparedStatement Demo
 * Demonstrates the use of PreparedStatement for secure and efficient SQL execution
 */
public class PreparedStatementDemo {
    
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASS = "password";
    
    public static void main(String[] args) {
        demonstratePreparedStatement();
        demonstrateParameterBinding();
        demonstrateBatchProcessing();
    }
    
    /**
     * Basic PreparedStatement usage
     */
    private static void demonstratePreparedStatement() {
        String sql = "SELECT * FROM employees WHERE salary > ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set parameter value
            pstmt.setDouble(1, 50000);
            
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("--- Employees with salary > 50000 ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("emp_id") + 
                                 ", Name: " + rs.getString("name") +
                                 ", Salary: " + rs.getDouble("salary"));
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Multiple parameter binding example
     */
    private static void demonstrateParameterBinding() {
        String sql = "INSERT INTO employees (emp_id, name, salary) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Insert first record
            pstmt.setInt(1, 100);
            pstmt.setString(2, "Alice Johnson");
            pstmt.setDouble(3, 75000);
            pstmt.executeUpdate();
            
            // Reuse the PreparedStatement with different values
            pstmt.setInt(1, 101);
            pstmt.setString(2, "Bob Williams");
            pstmt.setDouble(3, 82000);
            pstmt.executeUpdate();
            
            System.out.println("Records inserted using PreparedStatement.");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Batch processing for efficient bulk operations
     */
    private static void demonstrateBatchProcessing() {
        String sql = "INSERT INTO employees (emp_id, name, salary) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Disable auto-commit for batch
            conn.setAutoCommit(false);
            
            // Add multiple batches
            for (int i = 200; i < 210; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "Employee " + i);
                pstmt.setDouble(3, 50000 + (i * 100));
                pstmt.addBatch();
            }
            
            // Execute batch
            int[] updateCounts = pstmt.executeBatch();
            conn.commit();
            
            System.out.println("Batch insert completed: " + updateCounts.length + " records inserted.");
            
        } catch (Exception e) {
            System.err.println("Batch error: " + e.getMessage());
        }
    }
}
