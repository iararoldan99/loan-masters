package com.ar.loan.masters.service;

import com.ar.loan.masters.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private static ClientRepository repository;
    // Un prestamo tiene un solo cliente, pero un cliente puede tener muchos prestamos

}
