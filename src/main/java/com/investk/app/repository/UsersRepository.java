package com.investk.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investk.app.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByEmail(String email);
}