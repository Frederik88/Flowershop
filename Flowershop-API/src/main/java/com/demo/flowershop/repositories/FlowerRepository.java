package com.demo.flowershop.repositories;

import java.util.Optional;

import com.demo.flowershop.models.FlowerModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<FlowerModel, Long> {
    Optional <FlowerModel> findByName(String name);    
}