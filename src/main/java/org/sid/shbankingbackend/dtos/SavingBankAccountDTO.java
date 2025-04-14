package org.sid.shbankingbackend.dtos;


import lombok.Getter;

import lombok.Setter;

import org.sid.shbankingbackend.enums.AccountStatus;

import java.util.Date;



@Getter
@Setter
public class SavingBankAccountDTO extends BankAccountDTO {
    private double interestRate;
}
