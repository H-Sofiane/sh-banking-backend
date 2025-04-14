package org.sid.shbankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("SA")
@NoArgsConstructor @AllArgsConstructor @Getter
@Setter
public class SavingAccount extends BankAccount{
    private double interestRate;
}
