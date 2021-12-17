package com.yurii.ishop.dto.user;

import com.yurii.ishop.dto.account.AccountRequestDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {

    @NotBlank(message = "{name.not.empty}")
    private String username;

    @NotBlank(message = "{password.not.empty}")
    private String password;

    @Email(message = "{email.not.valid}")
    @NotBlank(message = "email.not.blank")
    private String email;

    @Valid
    private AccountRequestDto account;

}
