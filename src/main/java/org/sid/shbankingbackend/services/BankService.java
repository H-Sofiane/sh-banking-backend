package org.sid.shbankingbackend.services;

import org.sid.shbankingbackend.entities.BankAccount;
import org.sid.shbankingbackend.entities.CurrentAccount;
import org.sid.shbankingbackend.entities.SavingAccount;
import org.sid.shbankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter() {
        BankAccount bankAccount =
                bankAccountRepository.findById("6e7c0f38-0a00-4a7b-ae49-c6cdb4da9aec").orElse(null);
        if(bankAccount != null) {
            System.out.println("*************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over Draft => "+((CurrentAccount)bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate => "+((SavingAccount)bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType()+"\t"+op.getOperationDate()+"\t"+op.getAmount());
            });
        }
    }
}
