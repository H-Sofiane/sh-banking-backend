package org.sid.shbankingbackend.repositories;

import org.sid.shbankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

    List<BankAccount> findBankAccountByCustomerId(Long customerId);

    List<BankAccount> findBankAccountByCustomerName(String customerName);
}
