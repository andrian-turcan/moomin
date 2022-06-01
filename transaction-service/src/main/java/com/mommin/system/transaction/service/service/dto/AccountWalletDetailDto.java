package com.mommin.system.transaction.service.service.dto;

import com.mommin.system.transaction.service.domain.AccountWallet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public final class AccountWalletDetailDto {

    private final BigDecimal balance;

    public static AccountWalletDetailDto valueOf(AccountWallet accountWallet) {
        return new AccountWalletDetailDto(accountWallet.getBalance().value());
    }
}
