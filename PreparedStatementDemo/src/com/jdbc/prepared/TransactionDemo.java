package com.jdbc.prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Transaction Management Demo
 * Demonstrates ACID properties and transaction handling
 */
public class TransactionDemo {
    
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASS = "password";
    
    public static void main(String[] args) {
        demonstrateSuccessfulTransaction();
        demonstrateRollback();
    }
    
    /**
     * Successful transaction with commit
     */
    private static void demonstrateSuccessfulTransaction() {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); // Start transaction
            
            // Transfer money between accounts
            String debitSql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
            pstmt1 = conn.prepareStatement(debitSql);
            pstmt1.setDouble(1, 1000);
            pstmt1.setInt(2, 1);
            pstmt1.executeUpdate();
            
            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
            pstmt2 = conn.prepareStatement(creditSql);
            pstmt2.setDouble(1, 1000);
            pstmt2.setInt(2, 2);
            pstmt2.executeUpdate();
            
            // Commit transaction
            conn.commit();
            System.out.println("Transaction committed successfully!");
            
        } catch (SQLException e) {
            System.err.println("Transaction failed: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Demonstrating rollback on error
     */
    private static void demonstrateRollback() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            
            pstmt = conn.prepareStatement(
                "INSERT INTO employees (emp_id, name, salary) VALUES (?, ?, ?)");
            
            pstmt.setInt(1, 500);
            pstmt.setString(2, "Test Employee");
            pstmt.setDouble(3, 60000);
            pstmt.executeUpdate();
            
            // Simulate an error condition
            if (true) { // Intentional error for demo
                throw new SQLException("Simulated error - rolling back transaction");
            }
            
            conn.commit();
            
        } catch (SQLException e) {
            System.err.println("Error occurred: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back successfully.");
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
