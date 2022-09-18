package com.ar.loan.masters.service;

import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class LoanService {
    // Un prestamo tiene un solo cliente, pero un cliente puede tener muchos prestamos

    @Autowired
    private static LoanRepository repository;

    private static Supplier<List<Loan>> getAllLoans = () -> repository.findAll();
    private static Function<Long, Optional<Loan>> getLoanById = id -> repository.findById(id);

}
