package com.ar.loan.masters.mapper;

import com.ar.loan.masters.dto.ClientDTO;
import com.ar.loan.masters.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client fromDTOToEntity(ClientDTO entry){
        var entity = new Client();
        entity.setFullName(entry.getFullName());
        entity.setAge(entry.getAge());
        entity.setCreditScore(entry.getCreditScore());
        entity.setJobTitle(entry.getJobTitle());
        entity.setEmail(entry.getEmail());
        entity.setTotalLoansTaken(entry.getTotalLoansTaken());
        entity.setCriminal(entry.isCriminal());
        return entity;
    }
}
