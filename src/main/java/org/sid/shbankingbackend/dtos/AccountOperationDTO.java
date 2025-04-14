package org.sid.shbankingbackend.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sid.shbankingbackend.entities.BankAccount;
import org.sid.shbankingbackend.enums.OperationType;

import java.util.Date;


@Getter
@Setter
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
