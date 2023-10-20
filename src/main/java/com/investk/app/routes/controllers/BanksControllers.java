package com.investk.app.routes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investk.app.model.Banks;
import com.investk.app.model.Users;
import com.investk.app.repository.BanksRepository;
import com.investk.app.repository.UsersRepository;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/banks")
public class BanksControllers {
    @Autowired
    BanksRepository banksRepository;

    @Autowired
    UsersRepository usersRepository;
    
    // TODO: [TenantArbitrarySet] Esse ID deve ser obtido originalmente e não pode ser estático
    long tenant_id = 1;

    @PostMapping(value = "/create")
    public void createBank(@RequestBody Banks bank){
        try {
            Users _users = usersRepository.findById(tenant_id).get();
            Banks _bank = new Banks(_users, bank.getName(), bank.getTotalInvested());
            banksRepository.save(_bank);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    // TODO: [TenantArbitrarySet] Esse ID deve ser obtido originalmente e não pode ser estático
    @GetMapping("/get")
    public ResponseEntity<List<Banks>> listBanks(){
        try {
            Users _users = usersRepository.findById(tenant_id).get();
            List<Banks> banks = new ArrayList<Banks>();

            banksRepository.findByTenant(_users).forEach(banks::add);
            // TODO: Do not return user object inside each bank
            return new ResponseEntity<List<Banks>>(banks, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        }
    }


}
