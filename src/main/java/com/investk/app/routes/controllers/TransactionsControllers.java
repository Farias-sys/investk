package com.investk.app.routes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investk.app.model.Investments;
import com.investk.app.model.Transactions;
import com.investk.app.model.Users;
import com.investk.app.repository.InvestmentsRepository;
import com.investk.app.repository.TransactionsRepository;
import com.investk.app.repository.UsersRepository;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/transactions")
public class TransactionsControllers {
    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    InvestmentsRepository investmentsRepository;

    @Autowired
    UsersRepository usersRepository;

    // TODO: [TenantArbitrarySet] Esse ID deve ser obtido originalmente e não pode ser estático
    long tenant_id = 1;

    @PostMapping(value = "/create/{investment_id}")
    public void createTransaction(@PathVariable("investment_id") long investment_id, @RequestBody Transactions transaction){
        try {
            Users _user = usersRepository.findById(tenant_id).get();
            Investments _investment = investmentsRepository.findById(investment_id).get();

            Transactions _transaction = new Transactions(_user, _investment, transaction.getValue(), transaction.getDateCreated(), transaction.getType());
            transactionsRepository.save(_transaction);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
