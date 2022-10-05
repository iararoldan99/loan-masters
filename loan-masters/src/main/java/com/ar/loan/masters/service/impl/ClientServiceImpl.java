package com.ar.loan.masters.service.impl;

import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.repository.ClientRepository;
import com.ar.loan.masters.repository.LoanRepository;
import com.ar.loan.masters.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private LoanRepository loanRepository;
    Consumer<Client> saveClient = client -> repository.save(client);
    public Predicate<Long> clientExists = id -> repository.findById(id).isPresent();
    public Function<Long, Optional<Client>> findById = id -> repository.findById(id);
    public Supplier<List<Client>> getAllClients = () -> repository.findAll();

    public void payTotal(Integer loanId){

    }
}
