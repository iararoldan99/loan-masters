package com.ar.loan.masters.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ClientDTO {
    private String fullName;
    private String email;
    private Integer age;
    private Integer creditScore;
    private Integer totalLoansTaken;
    private String jobTitle;
    private boolean criminal;
}
