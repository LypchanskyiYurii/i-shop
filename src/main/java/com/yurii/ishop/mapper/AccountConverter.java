package com.yurii.ishop.mapper;

import com.yurii.ishop.entity.Account;
import com.yurii.ishop.entity.dto.account.AccountResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public AccountResponseDto toAccountResponseDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setId(account.getId());
        accountResponseDto.setAmount(account.getAmount());
        return accountResponseDto;
    }
}
