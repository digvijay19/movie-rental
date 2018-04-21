package com.thoughtworks.movierental.model;

import com.thoughtworks.movierental.model.statements.HtmlStatement;
import com.thoughtworks.movierental.model.statements.TextStatement;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @OneToMany(targetEntity = Rental.class, mappedBy = "customer")
    private Rentals rentals = new Rentals();

    protected Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addRental(Rental arg) {
        arg.setCustomer(this);
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return new TextStatement(rentals, name).statement();
    }

    public String htmlStatement() {
        return new HtmlStatement(rentals, name).statement();
    }

    public String getEmail() {
        return email;
    }
}

