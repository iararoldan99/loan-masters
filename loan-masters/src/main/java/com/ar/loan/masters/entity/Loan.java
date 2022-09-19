package com.ar.loan.masters.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Table
@ToString
@RequiredArgsConstructor
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_id")
    private Client client;
    private Double amount;
    private Integer duesPayed;
    private Integer duesToPay;
    private boolean fullyPayed;
    private Float interest;
    private Integer months;
    private Date start;
    private Date end;

}
