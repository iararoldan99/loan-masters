package com.ar.loan.masters.controller;

import com.ar.loan.masters.dto.LoanDTO;
import com.ar.loan.masters.dto.Notification;
import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.service.impl.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    BankServiceImpl service;

    @PostMapping("/grant")
    public ResponseEntity<Loan> grantLoan(@Valid @RequestBody LoanDTO loan) {
        return ResponseEntity.ok(service.grantLoan.apply(loan));
    }

    @GetMapping("/totalMoneyBalance")
    public ResponseEntity<Double> getTotalMoneyBalance(){
        return ResponseEntity.ok(service.findTotalMoneyBalance.get());
    }

    @PostMapping("/seize")
    public ResponseEntity<Notification> seizeClient(@Valid Client client, Integer loanId){
        return ResponseEntity.ok(service.seizeClient(client, loanId));
    }



}
