package com.investk.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investk.app.model.Banks;
import com.investk.app.model.Users;

public interface BanksRepository extends JpaRepository<Banks, Long>{
    List<Banks> findByTenant(Users tenant);
}
