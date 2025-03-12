package com.bus.tracking.bus_app.controller;

import com.bus.tracking.bus_app.dto.UserDto;
import com.bus.tracking.bus_app.model.User;
import com.bus.tracking.bus_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //  Register User
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        User registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok("successfully registered");
    }

    //  Login User
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        User loggedInUser = userService.loginUser(userDto);
        return ResponseEntity.ok(loggedInUser);
    }
}

