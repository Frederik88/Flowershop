package com.demo.flowershop.repositories;

import com.demo.flowershop.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    
}