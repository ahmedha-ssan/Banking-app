package Javaguides.banking.repository;

import Javaguides.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    //AccountRepo will get role db methods to perform from account db based on Long ID

}
