package com.ar.loan.masters.mapper;

import com.ar.loan.masters.dto.LoanDTO;
import com.ar.loan.masters.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    @Autowired
    private ClientMapper clientMapper;

    public Loan fromDTOToEntity(LoanDTO entry){
        var entity = new Loan();
        entity.setClient(clientMapper.fromDTOToEntity(entry.getClient()));
        entity.setAmount(entity.getAmount());
        entity.setMonths(entry.getMonths());
        entity.setInterest(entry.getInterest());
        entity.setStart(entry.getStart());
        entity.setDue(entry.getDue());
        return entity;
    }
}
