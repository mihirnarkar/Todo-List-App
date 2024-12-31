package com.example.User_Service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.User_Service.exception.InvalidDataException;
import com.example.User_Service.exception.UserNotFoundException;
import com.example.User_Service.model.User;
import com.example.User_Service.repository.UserRepository;

@Service
public class UserServiceImp1 implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; 

    @Override
    public User createUser(User user){
        if(user.getName() == null || user.getEmail() == null || user.getPassword() == null){
            throw new InvalidDataException("Name ,email and password are required fields");
        }
        
        // check if email is already registered
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new InvalidDataException("Email is already registered");
        }

        // Hash the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not found with id: "+id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id,User user){
        User existingUser = getUserById(id);

        // Update user fields
        if(user.getName() != null){
            existingUser.setName(user.getName());
        }

        if(user.getEmail() != null){
            // Check if new email is already use by another user
            User userWithEmail = userRepository.findByEmail(user.getEmail());
            if(userWithEmail != null && !userWithEmail.getId().equals(id)){
                throw new InvalidDataException("Email is already in use by another user");
            }

            existingUser.setEmail(user.getEmail());
        }

        if(user.getAge() != 0){
            existingUser.setAge(user.getAge());
        }

        if(user.getPassword() != null){
            // Hash the new password before updating
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id){
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
    
}
