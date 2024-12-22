package com.example.User_Service.repository;

import org.springframework.stereotype.Repository;

import com.example.User_Service.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User findByEmail(String email);
    
}
