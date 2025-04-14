package org.sid.shbankingbackend.services;

import org.sid.shbankingbackend.dtos.*;
import org.sid.shbankingbackend.entities.BankAccount;
import org.sid.shbankingbackend.entities.CurrentAccount;
import org.sid.shbankingbackend.entities.Customer;
import org.sid.shbankingbackend.entities.SavingAccount;
import org.sid.shbankingbackend.exceptions.BalanceNotSufficientException;
import org.sid.shbankingbackend.exceptions.BankAccountNotFoundException;
import org.sid.shbankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    //Customer saveCustomer(Customer customer);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;



    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);

    List<BankAccountDTO> searchBankAccounts(Long customerId);


    void deleteAccount(String id);
}
