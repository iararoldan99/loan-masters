package com.ar.loan.masters.mapper;

import com.ar.loan.masters.dto.LoanDTO;
import com.ar.loan.masters.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    @Autowired
    private ClientMapper clientMapper;

    public Loan fromDTOToEntity(LoanDTO loanDTO){
        var loanEntity = new Loan();
        loanEntity.setAmount(loanDTO.getAmount());
        loanEntity.setMonths(loanDTO.getMonths());
        loanEntity.setStart(loanDTO.getStart());
        loanEntity.setEnd(loanDTO.getEnd());
        loanEntity.setDues(loanDTO.getDues());
        loanEntity.setDuesPayed(0);
        loanEntity.setDuesToPay(loanDTO.getDues());
        loanEntity.setOverDues(0);
        loanEntity.setFullyPayed(false);
        loanEntity.setInterest(loanDTO.getInterest());
        return loanEntity;
    }
}
