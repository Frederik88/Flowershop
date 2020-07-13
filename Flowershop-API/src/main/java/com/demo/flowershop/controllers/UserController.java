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
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private UserRepository userRepository;

    private static Logger logger = Logger.getLogger("FlowerController.java");

    @GetMapping("/users")
    public @ResponseBody Iterable<UserModel> getUsers() {
        return userRepository.findAll();
    }

    /*@GetMapping("/user")
    public @ResponseBody UserDto getUser(@RequestParam long id) throws Exception {
        UserModel user = userRepository.findById(id)
        .orElseThrow(() -> new Exception("User not found"));

        return convertToDto(user);
    }*/

    @PostMapping("/user")
    public UserModel login(@RequestBody UserDto userDto) throws Exception {
        UserModel user = convertToEntity(userDto);
        return userRepository.findByNameContains(user.getName())
        .orElseThrow(() -> new Exception("User not found"));
    }

    @GetMapping("/login")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
          .decode(authToken));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addNewUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        UserDto user = new UserDto();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(convertToEntity(user));
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
