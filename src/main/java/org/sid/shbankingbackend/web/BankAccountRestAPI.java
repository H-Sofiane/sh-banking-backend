package org.sid.shbankingbackend.web;

import org.sid.shbankingbackend.dtos.*;
import org.sid.shbankingbackend.exceptions.BalanceNotSufficientException;
import org.sid.shbankingbackend.exceptions.BankAccountNotFoundException;
import org.sid.shbankingbackend.exceptions.CustomerNotFoundException;
import org.sid.shbankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts() {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @GetMapping("/customers/{customerId}/accounts")
    public List<BankAccountDTO> searchBankAccounts(@PathVariable Long customerId) {
        return bankAccountService.searchBankAccounts(customerId);
    }


    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.transfer(transferRequestDTO.getAccountSource(),transferRequestDTO.getAccountDestination(),transferRequestDTO.getAmount());
    }

    @PostMapping("/customers/{id}/current-account")
    public CurrentBankAccountDTO saveCurrentBankAccount(@RequestBody CurrentBankAccountDTO currentBankAccountDTO) throws CustomerNotFoundException {
        this.bankAccountService.saveCurrentBankAccount(currentBankAccountDTO.getBalance(),currentBankAccountDTO.getOverDraft(),currentBankAccountDTO.getCustomerDTO().getId());
        return currentBankAccountDTO;
    }

    @PostMapping("/customers/{id}/saving-account")
    public SavingBankAccountDTO saveSavingBankAccount(@RequestBody SavingBankAccountDTO savingBankAccountDTO) throws CustomerNotFoundException {
        this.bankAccountService.saveSavingBankAccount(savingBankAccountDTO.getBalance(),savingBankAccountDTO.getInterestRate(),savingBankAccountDTO.getCustomerDTO().getId());
        return savingBankAccountDTO;
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountService.deleteAccount(id);
    }
}
