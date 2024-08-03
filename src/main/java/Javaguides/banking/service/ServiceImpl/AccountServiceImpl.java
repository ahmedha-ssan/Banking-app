package Javaguides.banking.service.ServiceImpl;

import Javaguides.banking.Mapper.AccountMapper;
import Javaguides.banking.dto.AccountDto;
import Javaguides.banking.entity.Account;
import Javaguides.banking.repository.AccountRepo;
import Javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        // Convert DTO to Entity
        Account account = AccountMapper.mapToAccount(accountDto);
        // Save Entity
        Account savedAccount = accountRepo.save(account);
        // Convert Entity to DTO
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account dose not exists..."));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposite(Long id, double amount) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account dose not exists..."));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account dose not exists..."));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

}
