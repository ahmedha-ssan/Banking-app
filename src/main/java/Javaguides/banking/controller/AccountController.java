package Javaguides.banking.controller;

import Javaguides.banking.dto.AccountDto;
import Javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController//spring init class
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //GEt all users
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    //DELETE ACCount
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }

    //Add account restAPI
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get account by id
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    //Deposit
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto>  deposit(@PathVariable Long id,@RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }
    //Withdraw
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto>  withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{fromAccountId}/transfer/{toAccountId}")
    public ResponseEntity<AccountDto> transferFunds(@PathVariable long fromAccountId,@PathVariable long toAccountId, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.transferMoney(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok(accountDto);
    }
    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getAccountBalance(@PathVariable long id) {
        double balance = accountService.getBalance(id);
        return ResponseEntity.ok(balance);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<AccountDto> updateAccountDetails(@PathVariable long id, @RequestBody AccountDto accountDto) {
        AccountDto updatedAccountDto = accountService.updateAccountDetails(id, accountDto);
        return ResponseEntity.ok(updatedAccountDto);
    }
}
