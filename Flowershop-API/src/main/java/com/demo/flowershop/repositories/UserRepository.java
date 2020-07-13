package com.demo.flowershop.repositories;

import java.util.Optional;

import com.demo.flowershop.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByNameContains(String name);
}