package org.sid.shbankingbackend.dtos;

import lombok.Getter;
import lombok.Setter;
import org.sid.shbankingbackend.enums.AccountStatus;

import java.util.Date;

@Getter @Setter
public class BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private String type;
}
