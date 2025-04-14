package org.sid.shbankingbackend;

import org.sid.shbankingbackend.dtos.BankAccountDTO;
import org.sid.shbankingbackend.dtos.CurrentBankAccountDTO;
import org.sid.shbankingbackend.dtos.CustomerDTO;
import org.sid.shbankingbackend.dtos.SavingBankAccountDTO;
import org.sid.shbankingbackend.entities.*;
import org.sid.shbankingbackend.enums.AccountStatus;
import org.sid.shbankingbackend.enums.OperationType;
import org.sid.shbankingbackend.exceptions.BalanceNotSufficientException;
import org.sid.shbankingbackend.exceptions.BankAccountNotFoundException;
import org.sid.shbankingbackend.exceptions.CustomerNotFoundException;
import org.sid.shbankingbackend.repositories.AccountOperationRepository;
import org.sid.shbankingbackend.repositories.BankAccountRepository;
import org.sid.shbankingbackend.repositories.CustomerRepository;
import org.sid.shbankingbackend.services.BankAccountService;
import org.sid.shbankingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ShbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShbankingBackendApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService)
    {
        return args -> {
            Stream.of("Toto","Tata","Titi").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name.toLowerCase()+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
                bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000, 9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*120000, 5.5, customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });

            List<BankAccountDTO> bankAccounts =  bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i = 0; i < 10; i++) {
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO) {
                        accountId = ((SavingBankAccountDTO)bankAccount).getId();
                    }
                    else {
                        accountId = ((CurrentBankAccountDTO)bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }
            }
        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Toto","Tata","Titi").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust ->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*9000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*9000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(acc ->{
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }

}
