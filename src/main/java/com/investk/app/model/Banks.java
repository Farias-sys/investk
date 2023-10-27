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

    public Banks(){

    }

    // Constructor

    public Banks(Users tenant, String name){
        this.tenant = tenant;
        this.name = name;
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

    // Setters

    public void setName(String name){
        this.name = name;
    }

}
