package com.thoughtworks.movierental.model;

import javax.persistence.*;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

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
        return new TextStatement(name, rentals).generate();
    }

    public String htmlStatement() {
        return new HtmlStatement(name, rentals).generate();
    }

    public String getEmail() {
        return email;
    }
}

