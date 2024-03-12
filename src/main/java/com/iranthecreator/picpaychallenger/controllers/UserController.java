package com.iranthecreator.picpaychallenger.controllers;

import com.iranthecreator.picpaychallenger.domain.user.User;
import com.iranthecreator.picpaychallenger.dto.UserDTO;
import com.iranthecreator.picpaychallenger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return this.userService.getAllUsers();
    }


}
