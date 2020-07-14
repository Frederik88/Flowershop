package com.demo.flowershop.controllers;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.demo.flowershop.dtos.UserDto;
import com.demo.flowershop.models.UserModel;
import com.demo.flowershop.repositories.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
@CrossOrigin()
public class UserController {

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static Logger logger = Logger.getLogger("FlowerController.java");

    @GetMapping("/users")
    public @ResponseBody Iterable<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.save(convertToEntity(user));
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestParam long id) {
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(UserModel user) {
        return modelmapper.map(user, UserDto.class);
    }

    private UserModel convertToEntity(UserDto userDto) {
        return modelmapper.map(userDto, UserModel.class);
    }
}
