package com.ar.loan.masters.entity;

import com.ar.loan.masters.utils.JpaConverterJson;
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
    private Integer id;
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bankId;
    private String email;
    private Integer age;
    private Integer creditScore;
    private Integer totalLoansTaken;
    private String jobTitle;
    private boolean criminal;
    private boolean blackListed;
    private Integer totalLoansFullyPayed;
    @OneToMany(mappedBy = "clientId", orphanRemoval = true, cascade = {CascadeType.ALL})
    @Convert(converter = JpaConverterJson.class)
    private List<Loan> loans = new ArrayList<>();
}
