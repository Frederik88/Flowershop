package com.demo.flowershop.repositories;

import com.demo.flowershop.models.FlowerModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<FlowerModel, Long> {   
}