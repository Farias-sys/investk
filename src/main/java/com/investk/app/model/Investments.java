package com.investk.app.model;

import jakarta.persistence.*;

@Entity
@Table(name="investments")
public class Investments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Users tenant;

    @Column(name="type")
    private char type;

    @Column(name="label")
    private String label;

    @Column(name="description")
    private String description;

    @ManyToOne
    private Banks bank;

    @Column(name = "initial_value")
    private float initial_value;

    @Column(name = "yield")
    private float yield;

    public Investments(){

    }

    public Investments(Users tenant, char type, String label, String description, float initial_value, float yield){
        this.tenant = tenant;
        this.type = type;
        this.label = label;
        this.description = description;
    }

    /*Gets */

    public char getType(){
        return type;
    }

    public String getLabel(){
        return label;
    }

    public String getDescription(){
        return description;
    }

    public float getInitialValue(){
        return initial_value;
    }

    public float getYield(){
        return yield;
    }

    /*Sets */

    public void setYield(float yield){
        this.yield = yield;
    }

}
