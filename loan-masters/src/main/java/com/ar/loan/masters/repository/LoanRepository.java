package com.ar.loan.masters.repository;

import com.ar.loan.masters.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    @Query(value= "SELECT Loan from Loan l WHERE l.clientId=?1", nativeQuery = true)
    List<Loan> findAllByClientId(Integer id);

    List<Loan> findByClientId(Integer clientId);
}
