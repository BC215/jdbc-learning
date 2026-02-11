package com.mybatis.mapper;

import com.mybatis.entity.User;
import java.util.List;

/**
 * UserMapper interface for database operations
 * MyBatis will automatically implement this interface
 */
public interface UserMapper {
    
    /**
     * Select user by ID
     */
    User selectUserById(int id);
    
    /**
     * Select all users
     */
    List<User> selectAllUsers();
    
    /**
     * Insert a new user
     */
    int insertUser(User user);
    
    /**
     * Update user information
     */
    int updateUser(User user);
    
    /**
     * Delete user by ID
     */
    int deleteUser(int id);
    
    /**
     * Select users by age range
     */
    List<User> selectUsersByAgeRange(int minAge, int maxAge);
}
