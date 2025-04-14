package org.sid.shbankingbackend.repositories;

import org.springframework.data.domain.Page;
import org.sid.shbankingbackend.dtos.CustomerDTO;
import org.sid.shbankingbackend.entities.AccountOperation;
import org.sid.shbankingbackend.entities.BankAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByBankAccountId(String accountId);
    Page<AccountOperation> findByBankAccountIdOrderByOperationDateDesc(String accountId, Pageable pageable);
}
