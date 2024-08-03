package Javaguides.banking.Mapper;

import Javaguides.banking.dto.AccountDto;
import Javaguides.banking.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {
           Account account = new Account(
                   accountDto.getId(),
                   accountDto.getAccountHolderName(),
                   accountDto.getBalance()
           );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        //pass value to constructor
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
            return accountDto;
    }
}
