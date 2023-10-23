package com.investk.app.model;

import jakarta.persistence.*;

@Entity
@Table(name="banks")
public class Banks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Users tenant;

    @Column(name="name")
    private String name;

    @Column(name="total_invested")
    private float total_invested;

    public Banks(){

    }

    // Constructor

    public Banks(Users tenant, String name, float total_invested){
        this.tenant = tenant;
        this.name = name;
        this.total_invested = total_invested;
    }

    // Gets

    public long getId(){
        return id;
    }

    public Users getTenant(){
        return tenant;
    }

    public String getName(){
        return name;
    }

    public float getTotalInvested(){
        return total_invested;
    }

    // Setters

    public void setName(String name){
        this.name = name;
    }

}
