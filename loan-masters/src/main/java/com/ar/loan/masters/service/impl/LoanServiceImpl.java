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
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository repository;
    public Consumer<Loan> saveLoan = loan -> repository.save(loan);

    private Supplier<List<Loan>> findAllLoans = () -> repository.findAll();
    public Function<Integer, Optional<Loan>> findLoanById = id -> repository.findById(id);
    public Function<Integer, List<Loan>> findAllLoansByClientId = id -> repository.findAllByClientId(id);

    public Function<Integer, List<Loan>> getAllFullyPayedLoansByClientId = id -> repository.findAllByClientId(id).stream().filter(l -> l.isFullyPayed()).collect(Collectors.toList());

    public Function<Integer, List<Loan>> getAllUnpaidLoansAtTheMomentByClientId = id -> repository.findAllByClientId(id).stream().filter(l -> !l.isFullyPayed()).collect(Collectors.toList());

    public Function<Integer, Integer> getAllDuesPayedAtTheMoment = loanId -> repository.findById(loanId).get().getDuesPayed();

    public Function<Integer, Integer> getAllDuesToPay = loanId -> repository.findById(loanId).get().getDuesToPay();

    public Function<Integer, Integer> getAllOverdues = loanId -> repository.findById(loanId).get().getOverDues();

    public Supplier<List<Loan>> getAllFullyPayedLoans = () -> repository.findAll().stream().filter(l -> l.isFullyPayed()).collect(Collectors.toList());

    public Supplier<List<Loan>> getAllLoansUnpaidAtTheMoment = () -> repository.findAll().stream().filter(l -> !l.isFullyPayed()).collect(Collectors.toList());

}
