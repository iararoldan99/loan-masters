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
    private Integer id;
    private Integer clientId;
    private Double amount;
    public Integer dues; // cuotas totales del prestamo
    private Integer duesPayed; // cuotas pagadas de ese prestamo en particular
    private Integer duesToPay; // cuotas a pagar NO VENCIDAS
    private Integer overDues; // cuotas vencidas
    private boolean fullyPayed; // prestamo cancelado por el cliente
    private Float interest;
    private Integer months;
    private Date start;
    private Date end;

}
