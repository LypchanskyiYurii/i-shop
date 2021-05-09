package com.yurii.ishop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok("The server is running");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error has occurred");
        }
    }

}
