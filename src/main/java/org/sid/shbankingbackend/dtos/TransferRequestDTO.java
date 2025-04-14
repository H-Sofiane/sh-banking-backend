package org.sid.shbankingbackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO {
    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;
}
