package com.ar.loan.masters.service.impl;

import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.repository.LoanRepository;
import com.ar.loan.masters.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class LoanServiceImpl implements LoanService {
    // Un prestamo tiene un solo cliente, pero un cliente puede tener muchos prestamos

    @Autowired
    private LoanRepository repository;
    Consumer<Loan> saveLoan = loan -> repository.save(loan);
    private Supplier<List<Loan>> getAllLoans = () -> repository.findAll();
    private Function<Long, Optional<Loan>> getLoanById = id -> repository.findById(id);

}
