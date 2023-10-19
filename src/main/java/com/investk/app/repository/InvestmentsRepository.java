package com.investk.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investk.app.model.Investments;

public interface InvestmentsRepository extends JpaRepository<Investments, Long>{}
