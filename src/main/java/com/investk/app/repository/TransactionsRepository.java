package com.investk.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investk.app.model.Investments;
import com.investk.app.model.Transactions;
import com.investk.app.model.Users;

public interface TransactionsRepository extends JpaRepository<Transactions, Long>{
    List<Transactions> findByInvestment(Investments investment);
    List<Transactions> findByTenant(Users tenant);
}
