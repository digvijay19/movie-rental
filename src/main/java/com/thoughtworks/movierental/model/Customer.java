package com.thoughtworks.movierental.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Rental> rentals = new ArrayList<>();

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
        return statementHeader() + statementBody() + statementFooter();
    }

    public String htmlStatement() {
        return statementHeaderHtml() + statementBodyHtml() + statementFooterHtml();
    }

    private String statementHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String statementHeaderHtml() {
        return "<h1>" + ("Rental Record for " + getName()) + "</h1>";
    }

    private String statementBody() {
        return rentals.stream()
                .map((rental) -> "\t" + rental.getMovie().getTitle() + "\t" + valueOf(rental.amount()) + "\n")
                .collect(joining());
    }

    private String statementBodyHtml() {
        return rentals.stream()
                .map((rental) -> "<h3>" + rental.getMovie().getTitle() + "</h3>-&nbsp<b>" + valueOf(rental.amount()) + "</b></br>")
                .collect(joining());
    }

    private String statementFooter() {
        return "Amount owed is " + valueOf(totalRentalAmount()) + "\n" + "You earned " + valueOf(totalRenterPoints()) + " frequent renter points";
    }

    private String statementFooterHtml() {
        return "<b>" + "Amount owed is " + valueOf(totalRentalAmount()) + "</b></br><b>" + "You earned " + valueOf(totalRenterPoints()) + " frequent renter points" + "</b>";
    }

    private double totalRentalAmount() {
        return rentals.stream().mapToDouble(Rental::amount).sum();
    }

    private int totalRenterPoints() {
        return rentals.stream().mapToInt(Rental::frequentRenterPoints).sum();
    }

    public String getEmail() {
        return email;
    }
}

