package Javaguides.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //automatically generate getter setter and constructors
@AllArgsConstructor
public class AccountDto {//Account Data Transfer Object
    private Long id;
    private String accountHolderName;
    private double balance;
}

