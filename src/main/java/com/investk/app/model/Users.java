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

    @Column(name = "total_invested")
    private float total_invested;

    @Column(name = "total_yield")
    private double total_yield;

    public Users() {

    }
    /** Persistent class user model constructor
     * @author Farias-sys
     * @param email
     * @param password
     */
    public Users(String email, String password, String name, float total_invested, double total_yield) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.total_invested = total_invested;
        this.total_yield = total_yield;
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

    public float getTotalInvested(){
        return total_invested;
    }

    public double getTotalYield(){
        return total_yield;
    }

    // Sets

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setTotalInvested(float total_invested){
        this.total_invested = total_invested;
    }

    public void setTotalYield(double total_yield){
        this.total_yield = total_yield;
    }
}
