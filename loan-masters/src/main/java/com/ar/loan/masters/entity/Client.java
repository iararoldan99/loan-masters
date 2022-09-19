package com.ar.loan.masters.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Table
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private Integer age;
    private Integer creditScore;
    private Integer totalLoansTaken;
    private Integer totalLoansFullyPayed;
    private Integer duesToPay;
    private Integer overDues;
    private Integer duesPayedAtTheMoment;
    private String jobTitle;
    private boolean criminal;
    private boolean blackListed;
    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = {CascadeType.ALL})
    private List<Loan> loans = new ArrayList<>();
}
