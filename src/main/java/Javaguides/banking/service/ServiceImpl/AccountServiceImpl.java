package Javaguides.banking.service.ServiceImpl;

import Javaguides.banking.Mapper.AccountMapper;
import Javaguides.banking.dto.AccountDto;
import Javaguides.banking.entity.Account;
import Javaguides.banking.repository.AccountRepo;
import Javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public AccountDto deposit(Long id, double amount) {
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

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        return accounts.stream()
                .map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public Void deleteAccount(long id) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account dose not exists..."));
        accountRepo.deleteById(id);
        return null;
    }

    @Override
    public AccountDto transferMoney(Long fromAccountId, Long toAccountId, double amount) {
        Account fromAccount = accountRepo.findById(fromAccountId)
                .orElseThrow(()-> new RuntimeException("Source Account dose not exists..."));
        Account toAccount = accountRepo.findById(toAccountId)
                .orElseThrow(()-> new RuntimeException("Destination Account dose not exists..."));
        if(fromAccount.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        fromAccount.setBalance(fromAccount.getBalance()-amount);
        toAccount.setBalance(toAccount.getBalance()+amount);
        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        return AccountMapper.mapToAccountDto(fromAccount);
    }

    @Override
    public Double getBalance(Long id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Account dose not exists..."));
        return account.getBalance();
    }

    @Override
    public AccountDto updateAccountDetails(Long id, AccountDto accountDto) {
        Account account = accountRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Account dose not exists..."));
        account.setAccountHolderName(accountDto.getAccountHolderName());
        Account updatedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }


}
