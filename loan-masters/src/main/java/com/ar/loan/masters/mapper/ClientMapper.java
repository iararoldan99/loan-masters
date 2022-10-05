package com.ar.loan.masters.mapper;

import com.ar.loan.masters.dto.ClientDTO;
import com.ar.loan.masters.entity.Client;
import com.ar.loan.masters.entity.Loan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapper {
    public Client fromDTOToEntity(ClientDTO dto, Loan loan){
        var entity = new Client();
        entity.setFullName(dto.getFullName());
        entity.setAge(dto.getAge());
        entity.setCreditScore(dto.getCreditScore());
        entity.setJobTitle(dto.getJobTitle());
        entity.setEmail(dto.getEmail());
        entity.setCriminal(dto.isCriminal());
        entity.setBlackListed(dto.isBlackListed());
        entity.setTotalLoansTaken(dto.getTotalLoansTaken());
        entity.setDuesToPay(loan.getDuesToPay());
        entity.setDuesPayedAtTheMoment(0);
        entity.setOverDues(0);
        entity.setLoans(List.of(loan));
        return entity;
    }
}
