package com.yurii.ishop.controller;

import com.yurii.ishop.entity.UserEntity;
import com.yurii.ishop.repository.UserRepo;
import com.yurii.ishop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            if (userRepo.findByUsername(user.getUsername()) != null) {
                return ResponseEntity.badRequest().body("User with this name already exists");
            }
            userRepo.save(user);
            return ResponseEntity.ok("User saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error has occurred");
        }
    }

    @GetMapping
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok("The server is running");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error has occurred");
        }
    }

}
