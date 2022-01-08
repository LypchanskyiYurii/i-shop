package com.yurii.ishop.service;

import com.yurii.ishop.dto.user.UserRequestDto;
import com.yurii.ishop.dto.user.UserResponseDto;
import com.yurii.ishop.entity.Account;
import com.yurii.ishop.entity.User;
import com.yurii.ishop.exception.UserAlreadyExistException;
import com.yurii.ishop.exception.UserNotFoundException;
import com.yurii.ishop.mapper.UserConverter;
import com.yurii.ishop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public UserResponseDto create(UserRequestDto userRequestDto) {
        User userFromDB = userRepository.findByUsername(userRequestDto.getUsername());

        if (userFromDB != null) {
            try {
                throw new UserAlreadyExistException("User with username=[" + userRequestDto.getUsername() + "] already exists");
            } catch (UserAlreadyExistException e) {
                e.printStackTrace();
            }
        }

        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());

        if (userRequestDto.getAccount() != null) {
            Account account = new Account();
            account.setAmount(userRequestDto.getAccount().getAmount());
            user.setAccount(account);
            account.setUser(user);
        }

        User createdUser = userRepository.save(user);
        return userConverter.toUserResponseDto(createdUser);
    }

    public Page<UserResponseDto> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userConverter::toUserResponseDto);
    }

    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User user = getUserById(id);
        user.setEmail(userRequestDto.getEmail());
        user.setUsername(userRequestDto.getUsername());

        if (userRequestDto.getAccount() != null) {
            Account account = user.getAccount();
            account.setAmount(userRequestDto.getAccount().getAmount());
        }

        User updateUser = userRepository.save(user);
        return userConverter.toUserResponseDto(updateUser);
    }

}
