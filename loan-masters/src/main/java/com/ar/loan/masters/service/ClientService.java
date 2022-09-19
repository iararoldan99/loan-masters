package com.ar.loan.masters.service;

import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class ClientService {
    @Autowired
    private static ClientRepository repository;
    // Un prestamo tiene un solo cliente, pero un cliente puede tener muchos prestamos
    public static Predicate<Long> clientExists = id -> repository.findById(id) != null;
    public static Function<Long, Optional<Client>> findById = id -> repository.findById(id);

}
