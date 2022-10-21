package com.ar.loan.masters.service.impl;

import com.ar.loan.masters.dto.ClientDTO;
import com.ar.loan.masters.dto.LoanDTO;
import com.ar.loan.masters.dto.Notification;
import com.ar.loan.masters.entity.Bank;
import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.entity.Loan;
import com.ar.loan.masters.exception.InsufficientFundsException;
import com.ar.loan.masters.mapper.ClientMapper;
import com.ar.loan.masters.mapper.LoanMapper;
import com.ar.loan.masters.repository.BankRepository;
import com.ar.loan.masters.repository.LoanRepository;
import com.ar.loan.masters.service.BankService;
import com.ar.loan.masters.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.ar.loan.masters.utils.Validator.*;
import static com.ar.loan.masters.utils.Validator.isUnderage;

@Service
public class BankServiceImpl implements BankService {
    private static final String SEIZURE_NOTIFICATION = "El cliente ha sido embargado por adeudar mas de 3 cuotas consecutivas y no podra volver a solicitar prestamos en esta entidad bancaria.";
    private static final String SEIZURE_WARNING_NOTIFICATION = "El cliente adeuda 2 cuotas consecutivas, en caso de no abonar la proxima cuota, quedara embargado y no podra volver a solicitar prestamos en esta entidad bancaria.";
    private static final String LOAN_FULLY_PAYED = "El cliente ha abonado la totalidad del prestamo.";
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

    public Function<ClientDTO, Validator.ValidationResult> isClientEligible = client ->
            hasBadCreditScore()
                    .or(isBlackListed())
                    .or(hasCriminalRecord())
                    .or(isUnemployed())
                    .or(isDebtor())
                    .or(isUnderage())
                    .apply(client);
    public Supplier<Double> findTotalMoneyBalance = () -> bankRepository.findTotalMoneyBalance();

    private Function<Double, Double> subtractMoneyFromBalance = loanAmount -> {
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

    public Notification seizeClient(Client client, Integer loanId){
        Optional<Loan> loan = loanService.findLoanById.apply(loanId);
        Bank bank = manager.find(Bank.class, 1);
        double seizureMoney = findTotalMoneyBalance.get() + loan.get().getAmount() + loan.get().getInterest();
        client.setCreditScore(1);
        client.setBlackListed(true);
        bank.setTotalMoneyBalance(seizureMoney);
        clientService.saveClient.accept(client);
        bankRepository.save(bank);
        Notification notification = new Notification();
        notification.setMessage(SEIZURE_NOTIFICATION);
        return notification;
    }

    public boolean isClientInRiskOfSeizure(Client client){
        return loanService.findAllLoansByClientId.apply(client.getId())
                .stream()
                .filter(l -> l.getOverDues() == 2) // hasta 2 cuotas atrasadas riesgo embargo
                .collect(Collectors.toList()).size() >= 1;
    }

    public boolean clientWillBeSeized(Client client){
        return loanService.findAllLoansByClientId.apply(client.getId())
                .stream()
                .filter(l -> l.getOverDues() >= 3) // 3 o mas cuotas atrasadas se embarga al cliente
                .collect(Collectors.toList()).size() >= 1;
    }

}
