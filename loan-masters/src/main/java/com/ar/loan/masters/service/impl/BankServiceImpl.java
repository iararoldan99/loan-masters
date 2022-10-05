package com.ar.loan.masters.service.impl;

import com.ar.loan.masters.dto.ClientDTO;
import com.ar.loan.masters.dto.LoanDTO;
import com.ar.loan.masters.entity.Bank;
import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.exception.InsufficientFundsException;
import com.ar.loan.masters.mapper.ClientMapper;
import com.ar.loan.masters.mapper.LoanMapper;
import com.ar.loan.masters.repository.BankRepository;
import com.ar.loan.masters.service.BankService;
import com.ar.loan.masters.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.ar.loan.masters.utils.Validator.*;
import static com.ar.loan.masters.utils.Validator.isUnderage;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    LoanMapper loanMapper;
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    LoanServiceImpl loanService;
    @Autowired
    EntityManager manager;

    public Function<ClientDTO, Validator.ValidationResult> isEligible = client ->
            hasBadCreditScore()
                    .or(isBlackListed())
                    .or(hasCriminalRecord())
                    .or(isUnemployed())
                    .or(isDebtor())
                    .or(isUnderage())
                    .apply(client);
    public Supplier<Double> findTotalMoneyBalance = () -> bankRepository.findTotalMoneyBalance();

    public Function<Double, Double> subtractMoneyFromBalance = loanAmount -> {
        Bank bank = manager.find(Bank.class, 1);
        Double totalBalance = findTotalMoneyBalance.get();
        double subtraction = totalBalance - loanAmount;
        if (totalBalance > 0 && bank.getId() != null) {
            bank.setTotalMoneyBalance(subtraction);
            bankRepository.save(bank);
        } else {
            try {
                throw new InsufficientFundsException("Not enough money to grant loan");
            } catch (InsufficientFundsException e) {
                throw new RuntimeException(e);
            }
        }
        return subtraction;
    };

    public Function<LoanDTO, Loan> grantLoan = loanDTO -> {
        Loan loan = loanMapper.fromDTOToEntity(loanDTO);
        subtractMoneyFromBalance.apply(loan.getAmount());
        Client client = clientMapper.fromDTOToEntity(loanDTO.getClient(), loan);
        clientService.saveClient.accept(client);
        loanService.saveLoan.accept(loan);
        return loan;
    };
}
