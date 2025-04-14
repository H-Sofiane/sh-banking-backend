package org.sid.shbankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.shbankingbackend.dtos.AccountOperationDTO;
import org.sid.shbankingbackend.dtos.CustomerDTO;
import org.sid.shbankingbackend.entities.Customer;
import org.sid.shbankingbackend.exceptions.CustomerNotFoundException;
import org.sid.shbankingbackend.services.BankAccountService;
import org.springdoc.core.service.OperationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private final OperationService operationService;
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    private List<CustomerDTO> searchCustomers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/search")
    private List<CustomerDTO> customers(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public  CustomerDTO saveCustomer( @RequestBody CustomerDTO customerDTO) {
        bankAccountService.saveCustomer(customerDTO);
        return customerDTO;
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
    }



}
