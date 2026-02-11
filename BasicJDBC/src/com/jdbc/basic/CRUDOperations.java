package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * CRUD Operations Demo using Oracle JDBC
 * Demonstrates Create, Read, Update, Delete operations
 */
public class CRUDOperations {
    
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASS = "password";
    
    public static void main(String[] args) {
        // Demo: Create, Read, Update, Delete
        createTable();
        insertData();
        readData();
        updateData();
        deleteData();
        dropTable();
    }
    
    private static void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            String sql = "CREATE TABLE employees (" +
                        "emp_id NUMBER(10) PRIMARY KEY, " +
                        "name VARCHAR2(50), " +
                        "salary NUMBER(10,2))";
            
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.");
            
        } catch (Exception e) {
            System.out.println("Note: " + e.getMessage());
        }
    }
    
    private static void insertData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            String sql = "INSERT INTO employees VALUES (1, 'John Doe', 50000)";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO employees VALUES (2, 'Jane Smith', 60000)";
            stmt.executeUpdate(sql);
            
            System.out.println("Records inserted successfully.");
            
        } catch (Exception e) {
            System.err.println("Insert error: " + e.getMessage());
        }
    }
    
    private static void readData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
            
            System.out.println("\n--- Employee Records ---");
            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
            }
            
        } catch (Exception e) {
            System.err.println("Read error: " + e.getMessage());
        }
    }
    
    private static void updateData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            String sql = "UPDATE employees SET salary = 55000 WHERE emp_id = 1";
            int rowsAffected = stmt.executeUpdate(sql);
            
            System.out.println("\nUpdated " + rowsAffected + " record(s).");
            
        } catch (Exception e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }
    
    private static void deleteData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            String sql = "DELETE FROM employees WHERE emp_id = 2";
            int rowsAffected = stmt.executeUpdate(sql);
            
            System.out.println("Deleted " + rowsAffected + " record(s).");
            
        } catch (Exception e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }
    
    private static void dropTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            String sql = "DROP TABLE employees";
            stmt.executeUpdate(sql);
            System.out.println("Table dropped successfully.");
            
        } catch (Exception e) {
            System.out.println("Drop table: " + e.getMessage());
        }
    }
}
