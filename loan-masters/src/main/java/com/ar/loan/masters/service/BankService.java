package com.ar.loan.masters.service;

import com.ar.loan.masters.dto.ClientDTO;
import com.ar.loan.masters.dto.LoanDTO;
import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.mapper.ClientMapper;
import com.ar.loan.masters.mapper.LoanMapper;
import com.ar.loan.masters.repository.ClientRepository;
import com.ar.loan.masters.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.ar.loan.masters.utils.Validator.*;

@Service
public class BankService {
    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientRepository clientRepository;

    public Function<ClientDTO, ValidationResult> isEligible = client ->
            hasBadCreditScore()
                    .or(hasCriminalRecord())
                    .or(isUnemployed())
                    .or(isDebtor())
                    .or(isUnderage())
                    .apply(client);

    private Consumer<Client> saveClient = client -> clientRepository.save(client);
    private Consumer<Loan> saveLoan = loan -> loanRepository.save(loan);

    public Function<LoanDTO, Loan> grantLoan = l -> {
        Client client = clientMapper.fromDTOToEntity(l.getClient());
        saveClient.accept(client);
        Loan loan = loanMapper.fromDTOToEntity(l);
        saveLoan.accept(loan);
        return loan;
    };



}
