package com.ar.loan.masters.repository;

import com.ar.loan.masters.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query("SELECT b.totalMoneyBalance from Bank b")
    Double findTotalMoneyBalance();


}
