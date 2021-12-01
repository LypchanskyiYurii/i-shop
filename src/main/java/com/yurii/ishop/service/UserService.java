package com.yurii.ishop.service;

import com.yurii.ishop.entity.UserEntity;
import com.yurii.ishop.exception.UserAlreadyExistException;
import com.yurii.ishop.exception.UserNotFoundException;
import com.yurii.ishop.model.User;
import com.yurii.ishop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this name already exists");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return User.toModel(user);
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }

}
