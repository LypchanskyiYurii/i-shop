package com.yurii.ishop.service;

import com.yurii.ishop.entity.UserEntity;
import com.yurii.ishop.exception.UserAlreadyExistException;
import com.yurii.ishop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this name already exists")
        }
        return
                userRepo.save(user);
    }
}
