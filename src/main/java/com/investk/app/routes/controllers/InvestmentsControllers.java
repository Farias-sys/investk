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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investk.app.model.Banks;
import com.investk.app.model.Investments;
import com.investk.app.model.Users;
import com.investk.app.repository.BanksRepository;
import com.investk.app.repository.InvestmentsRepository;
import com.investk.app.repository.UsersRepository;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/investments")
public class InvestmentsControllers {
    @Autowired
    InvestmentsRepository investmentsRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BanksRepository banksRepository;

    @PostMapping(value = "/create/{tenant_id}/{bank_id}")
    public void createInvestment(@PathVariable("tenant_id") long tenant_id,@PathVariable("bank_id") long bank_id,@RequestBody Investments investment){
        try {
            Users _users = usersRepository.findById(tenant_id).get();
            Banks _bank = banksRepository.findById(bank_id).get();
            Investments _investment = new Investments(_users, _bank,investment.getType(), investment.getLabel(), investment.getDescription(), investment.getInitialValue(), investment.getYield(), investment.getDateCreated(), investment.getDateDeadline());
            investmentsRepository.save(_investment);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @PutMapping(value = "/setbank/{investment_id}/bank/{bank_id}")
    public void setInvestmentBank(@PathVariable("investment_id") long investment_id, @PathVariable("bank_id") long bank_id){
        Investments investment = investmentsRepository.findById(investment_id).get();
        if(investment!=null){
            try {
                Banks _bank = banksRepository.findById(bank_id).get();
                investment.setBank(_bank);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            // TODO: Handle exception
        }

    }
    
    @PostMapping(value = "/update/{investment_id}")
    public void updateInvestment(@PathVariable("investment_id") long investment_id, @RequestBody Investments investment){
        try {
            Investments _investment = investmentsRepository.findById(investment_id).get();
            _investment.setLabel(investment.getLabel() != null ? investment.getLabel() : _investment.getLabel());
            _investment.setDescription(investment.getDescription() != null ? investment.getDescription() : _investment.getDescription());
            _investment.setYield(investment.getYield() != 0.00 ? investment.getYield() : _investment.getYield());    
            _investment.setDateCreated(investment.getDateCreated() != null ? investment.getDateCreated() : _investment.getDateCreated());
            _investment.setDateDeadline(investment.getDateDeadline() != null ? investment.getDateDeadline() : _investment.getDateDeadline());
            investmentsRepository.save(_investment);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @GetMapping(value = "/get/{tenant_id}")
    public ResponseEntity<List<Investments>> listInvestments(@PathVariable("tenant_id") long tenant_id){
        try {
            Users _users = usersRepository.findById(tenant_id).get();
            List<Investments> investments = new ArrayList<Investments>();

            investmentsRepository.findByTenant(_users).forEach(investments::add);
            return new ResponseEntity<List<Investments>>(investments, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/delete/{investment_id}")
    public void deleteInvestment(@PathVariable("investment_id") long investment_id){
        try {
            investmentsRepository.deleteById(investment_id);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
