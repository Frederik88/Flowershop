package com.demo.flowershop.repositories;

import com.demo.flowershop.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    
}