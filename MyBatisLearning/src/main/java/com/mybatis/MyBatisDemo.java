package com.mybatis;

import com.mybatis.entity.User;
import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MyBatis Demo Application
 * Demonstrates basic CRUD operations using MyBatis
 */
public class MyBatisDemo {
    
    private static SqlSessionFactory sqlSessionFactory;
    
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== MyBatis Demo ===\n");
        
        // Demonstrate CRUD operations
        demonstrateInsert();
        demonstrateSelect();
        demonstrateUpdate();
        demonstrateSelectAll();
        demonstrateDelete();
    }
    
    private static void demonstrateInsert() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            
            User newUser = new User();
            newUser.setId(1);
            newUser.setUsername("john_doe");
            newUser.setEmail("john@example.com");
            newUser.setAge(25);
            
            int result = mapper.insertUser(newUser);
            session.commit();
            
            System.out.println("Insert: " + result + " user inserted.");
        }
    }
    
    private static void demonstrateSelect() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            
            User user = mapper.selectUserById(1);
            System.out.println("Select by ID: " + user);
        }
    }
    
    private static void demonstrateUpdate() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            
            User user = new User();
            user.setId(1);
            user.setUsername("john_doe_updated");
            user.setEmail("john.updated@example.com");
            user.setAge(26);
            
            int result = mapper.updateUser(user);
            session.commit();
            
            System.out.println("Update: " + result + " user updated.");
        }
    }
    
    private static void demonstrateSelectAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            
            List<User> users = mapper.selectAllUsers();
            System.out.println("\nAll Users:");
            users.forEach(System.out::println);
        }
    }
    
    private static void demonstrateDelete() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            
            int result = mapper.deleteUser(1);
            session.commit();
            
            System.out.println("\nDelete: " + result + " user deleted.");
        }
    }
}
