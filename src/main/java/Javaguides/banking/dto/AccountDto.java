package Javaguides.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //automatically generate getter setter and constructors
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
}

