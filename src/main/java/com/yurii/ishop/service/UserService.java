package com.yurii.ishop.service;

import com.yurii.ishop.entity.User;
import com.yurii.ishop.dto.user.UserResponseDto;
import com.yurii.ishop.exception.UserNotFoundException;
import com.yurii.ishop.mapper.UserConverter;
import com.yurii.ishop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND_MSG = "User with id = %s not found";

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    public UserResponseDto getById(Long id) {
        User user = getUserById(id);
        return userConverter.toUserResponseDto(user);
    }

    User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));
    }
}
