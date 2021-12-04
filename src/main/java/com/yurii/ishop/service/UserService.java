package com.yurii.ishop.service;

import com.yurii.ishop.entity.User;
import com.yurii.ishop.exception.UserAlreadyExistException;
import com.yurii.ishop.exception.UserNotFoundException;
import com.yurii.ishop.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepo userRepo;

    public User registration(User user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this name already exists");
        }
        return userRepo.save(user);
    }

    public com.yurii.ishop.dto.User getOne(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return com.yurii.ishop.dto.User.toModel(user);
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }

}
