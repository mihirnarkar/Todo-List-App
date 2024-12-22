package com.example.User_Service.service;

import java.util.List;

import com.example.User_Service.model.User;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id,User user);
    void deleteUser(Long id);
    
}