package com.investk.app.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Users tenant;

    @ManyToOne
    private Investments investment;

    @Column(name = "value")
    private float value;

    @Column(name = "date_created")
    private LocalDate date_created;

    @Column(name= "type")
    private boolean type;

    public Transactions(){
        
    }

    // Constructor

    public Transactions(Users tenant, Investments investment, float value, LocalDate date_created, boolean type){
        this.tenant = tenant;
        this.investment = investment;
        this.value = value;
        this.date_created = date_created;
        this.type = type;
    }

    // Getters

    public float getValue(){
        return value;
    }

    public LocalDate getDateCreated(){
        return date_created;
    }

    public boolean getType(){
        return type;
    }

    // Setters

    public void setValue(float value){
        this.value = value;
    }

    public void setDateCreated(LocalDate date_created){
        this.date_created = date_created;
    }

    public void setType(boolean type){
        this.type = type;
    }
}
