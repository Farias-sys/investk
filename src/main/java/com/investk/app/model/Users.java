package com.investk.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    public Users() {

    }
    /** Persistent class user model constructor
     * @author Farias-sys
     * @param email
     * @param password
     */
    public Users(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Gets

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    // Sets

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
