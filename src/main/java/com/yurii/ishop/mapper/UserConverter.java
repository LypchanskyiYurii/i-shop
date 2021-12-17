package com.yurii.ishop.mapper;

import com.yurii.ishop.entity.User;
import com.yurii.ishop.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {

    private final AccountConverter accountConverter;

    public UserResponseDto toUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        if (user.getAccount() != null) {
            userResponseDto.setAccount(accountConverter.toAccountResponseDto(user.getAccount()));
        }
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUserName(user.getUsername());
        return userResponseDto;
    }
}
