package com.investk.app.routes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.codec.digest.DigestUtils;

import com.investk.app.model.Users;
import com.investk.app.repository.UsersRepository;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/users")

public class UsersControllers {
    
    @Autowired
    UsersRepository usersRepository;

    private class UserLoginRequest{
        private String email;
        private String password;
    }

    @PostMapping(value = "/create")
    public void createUser(@RequestBody Users user){
        try {
            Users _user = new Users(user.getEmail(), DigestUtils.md5Hex(user.getPassword()).toUpperCase(), user.getName(), user.getTotalInvested(), user.getTotalYield());
            usersRepository.save(_user);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Users> loginUser(@RequestBody UserLoginRequest user_req){
        try {
            String email_req = user_req.email;    
            String password_req = user_req.password;
            String password_req_encrypted = DigestUtils.md5Hex(password_req).toUpperCase();

            Users _user = usersRepository.findByEmail(email_req).get(0);
            if(_user.getPassword().equals(password_req_encrypted)){
                return new ResponseEntity<Users>(_user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
