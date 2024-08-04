package Javaguides.banking.service;

import Javaguides.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> getAllAccounts();
    Void deleteAccount(long id);
    AccountDto transferMoney(Long fromAccountId, Long toAccountId, double amount);
    Double getBalance(Long id);
    AccountDto updateAccountDetails(Long id, AccountDto accountDto);
}
