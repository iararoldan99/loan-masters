package com.ar.loan.masters.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Setter
@Getter
public class LoanDTO {
    public ClientDTO client;
    public Double amount;
    private Float interest;
    private Integer months;
    private Date start;
    private Date due;
}
