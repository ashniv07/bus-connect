package com.bus.tracking.bus_app.service;

import com.bus.tracking.bus_app.model.Role;
import com.bus.tracking.bus_app.model.User;
import com.bus.tracking.bus_app.dto.UserDto;
import com.bus.tracking.bus_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ Register User
    public User registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use!");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password
        user.setRole(Role.valueOf(userDto.getRole().toUpperCase())); // Convert String to Enum

        return userRepository.save(user);
    }

    // ✅ Login User (Using UserDto)
    public User loginUser(UserDto userDto) {
        Optional<User> userOpt = userRepository.findByEmail(userDto.getEmail());

        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        User user = userOpt.get();

        // 🔥 Check Password Match
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        return user; // Return user details if login is successful
    }
}
