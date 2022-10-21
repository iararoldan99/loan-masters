package com.ar.loan.masters.service.impl;

import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.repository.ClientRepository;
import com.ar.loan.masters.repository.LoanRepository;
import com.ar.loan.masters.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanServiceImpl loanService;

    public Consumer<Client> saveClient = client -> repository.save(client);
    public Predicate<Long> clientExists = id -> repository.findById(id).isPresent();
    public Function<Long, Optional<Client>> findById = id -> repository.findById(id);
    public Supplier<List<Client>> getAllClients = () -> repository.findAll();

    public void payTotal(Integer loanId, Client client){
        Loan loan = loanService.findLoanById.apply(loanId).get();
        loan.setEnd(new Date());
        loan.setDuesPayed(loan.getDues());
        loan.setOverDues(0);
        loan.setDuesToPay(0);
        loan.setFullyPayed(true);
        client.setTotalLoansFullyPayed(client.getTotalLoansFullyPayed() + 1);
        loanRepository.save(loan);
    }

    public void payMonthlyDue(Integer loanId){
        Loan loan = loanService.findLoanById.apply(loanId).get();
        if(loan.getOverDues() < 3){
            loan.setDuesPayed(loan.getDuesToPay() + 1);
            loan.setOverDues(loan.getOverDues() - 1);
        }
    }

    public Supplier<List<Client>> findAllClients =  () -> repository.findAll();

    public Supplier<List<Client>> findAllBlackListedClients = () -> repository.findAll().stream().filter(c -> c.isBlackListed()).collect(Collectors.toList());

    public Supplier<List<Client>> findAllBestClients = () -> repository.findAll().stream().filter(c -> c.getTotalLoansFullyPayed() > 5 && c.getCreditScore() == 10).collect(Collectors.toList());

}
