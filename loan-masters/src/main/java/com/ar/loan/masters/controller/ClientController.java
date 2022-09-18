package com.ar.loan.masters.controller;

import com.ar.loan.masters.dto.ClientDTO;
import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.service.BankService;
import com.ar.loan.masters.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    BankService service;

    @PostMapping("/apply")
    public ResponseEntity<Validator.ValidationResult> apply(@Valid @RequestBody ClientDTO client) {
        return ResponseEntity.ok(service.isEligible.apply(client));
    }
}
