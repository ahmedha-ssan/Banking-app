package Javaguides.banking.service;

import Javaguides.banking.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(long id);
    AccountDto deposite(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
}
