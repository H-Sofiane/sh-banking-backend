package org.sid.shbankingbackend.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String photos;
    private String phoneNumber;
}
