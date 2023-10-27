package com.investk.app.routes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping(value = "/create/{investment_id}")
    public void createTransaction(@PathVariable("investment_id") long investment_id, @RequestBody Transactions transaction){
        try {
            Investments _investment = investmentsRepository.findById(investment_id).get();

            Transactions _transaction = new Transactions(_investment.getTenant(), _investment, transaction.getLabel(), transaction.getValue(), transaction.getDateCreated(), transaction.getType());
            transactionsRepository.save(_transaction);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @GetMapping(value = "/get/{tenant_id}")
    public ResponseEntity<List<Transactions>> getTransaction(@PathVariable("tenant_id") long tenant_id){
        try {
            Users _user = usersRepository.findById(tenant_id).get();
            List<Transactions> _transactions = new ArrayList<Transactions>();

            transactionsRepository.findByTenant(_user).forEach(_transactions::add);

            return new ResponseEntity<List<Transactions>>(_transactions, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/update/{transaction_id}")
    public void updateTransaction(@PathVariable("transaction_id") long transaction_id, @RequestBody Transactions transaction){
        try {
            Transactions _transaction = transactionsRepository.findById(transaction_id).get();
            _transaction.setDateCreated(transaction.getDateCreated() != null ? transaction.getDateCreated() : _transaction.getDateCreated());
            _transaction.setValue(transaction.getValue() != 0.00 ? transaction.getValue() : _transaction.getValue());
            _transaction.setLabel(transaction.getLabel() != null ? transaction.getLabel() : _transaction.getLabel());
            transactionsRepository.save(_transaction);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @DeleteMapping(value = "/delete/{transaction_id}")
    public void deleteTransaction(@PathVariable("transaction_id") long transaction_id){
        try {
            transactionsRepository.deleteById(transaction_id);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
