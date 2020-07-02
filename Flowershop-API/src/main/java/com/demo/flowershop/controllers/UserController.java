package com.demo.flowershop.controllers;

import com.demo.flowershop.models.UserModel;
import com.demo.flowershop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public @ResponseBody Iterable<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addNewUser(@RequestParam String name, @RequestParam String email){
        UserModel n = new UserModel();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
    }
}
