package com.yurii.ishop.controller;

import com.yurii.ishop.dto.user.UserRequestDto;
import com.yurii.ishop.dto.user.UserResponseDto;
import com.yurii.ishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.create(userRequestDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> get(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }
}
