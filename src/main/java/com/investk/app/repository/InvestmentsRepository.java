package com.investk.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investk.app.model.Investments;
import com.investk.app.model.Users;

public interface InvestmentsRepository extends JpaRepository<Investments, Long>{
    List<Investments> findByTenant(Users tenant);
}
