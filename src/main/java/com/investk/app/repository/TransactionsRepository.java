package com.investk.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investk.app.model.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Long>{}
