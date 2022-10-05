package com.ar.loan.masters.service;

import com.ar.loan.masters.dto.ClientDTO;
import com.ar.loan.masters.dto.LoanDTO;
import com.ar.loan.masters.entity.Bank;
import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.mapper.ClientMapper;
import com.ar.loan.masters.mapper.LoanMapper;
import com.ar.loan.masters.repository.BankRepository;
import com.ar.loan.masters.repository.ClientRepository;
import com.ar.loan.masters.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.ar.loan.masters.utils.Validator.*;

@Service
public interface BankService {

}
