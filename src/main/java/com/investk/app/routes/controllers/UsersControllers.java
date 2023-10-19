package com.investk.app.routes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investk.app.model.Users;
import com.investk.app.repository.UsersRepository;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/users")
public class UsersControllers {
    
    @Autowired
    UsersRepository usersRepository;

    @PostMapping(value = "/create")
    public void createUser(@RequestBody Users user){
        try {
            Users _user = new Users(user.getEmail(), user.getPassword(), user.getName(), user.getTotalInvested(), user.getTotalYield());
            usersRepository.save(_user);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
