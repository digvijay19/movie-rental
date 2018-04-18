package com.thoughtworks.movierental.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

  protected Customer(){}

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
    String result = "Rental Record for " + getName() + "\n";
    result += rentals.stream().map((rental) -> "\t" + rental.getMovie().getTitle() + "\t" + valueOf(rental.amount()) + "\n").collect(joining());

    //add footer lines result
    result += "Amount owed is " + valueOf(totalRentalAmount()) + "\n";
    result += "You earned " + valueOf(totalRenterPoints()) + " frequent renter points";
    return result;
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

