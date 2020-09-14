package com.demo.flowershop.repositories;

import com.demo.flowershop.models.FlowerModel;
import com.demo.flowershop.models.UserModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<FlowerModel, Long> {
	 Optional<FlowerModel> findByNameContains(String name);
	    void deleteByName(String name);
}