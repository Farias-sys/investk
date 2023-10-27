package com.investk.app.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="investments")
public class Investments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Users tenant;

    @Column(name="type")
    private String type;

    @Column(name="label")
    private String label;

    @Column(name="description")
    private String description;

    @ManyToOne
    private Banks bank;

    @Column(name = "initial_value")
    private float initialValue;

    @Column(name = "yield")
    private float yield;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "planed_interval")
    private float planedInterval;

    public Investments(){

    }

    public Investments(Users tenant, String type, String label, String description, float initial_value, float yield, LocalDate dateCreated, float planedInterval){
        this.tenant = tenant;
        this.type = type;
        this.label = label;
        this.description = description;
        this.initialValue = initial_value;
        this.yield = yield;
        this.dateCreated = dateCreated;
        this.planedInterval = planedInterval;
    }

    /*Gets */

    public long getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public String getLabel(){
        return label;
    }

    public String getDescription(){
        return description;
    }

    public float getInitialValue(){
        return initialValue;
    }

    public float getYield(){
        return yield;
    }

    public Users getTenant(){
        return tenant;
    }

    public LocalDate getDateCreated(){
        return dateCreated;
    }

    public float getPlanedInterval(){
        return planedInterval;
    }

    /*Sets */

    public void setLabel(String label){
        this.label = label;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setYield(float yield){
        this.yield = yield;
    }
    
    public void setBank(Banks bank){
        this.bank = bank;
    }
}
