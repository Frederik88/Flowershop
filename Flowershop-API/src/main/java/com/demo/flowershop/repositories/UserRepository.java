package com.demo.flowershop.repositories;

import com.demo.flowershop.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {
    
}