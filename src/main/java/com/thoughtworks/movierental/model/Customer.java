package com.thoughtworks.movierental.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    for (Rental rental : rentals) {
      double thisAmount = rental.amount();
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
          &&
          rental.getDaysRented() > 1) frequentRenterPoints++;

      //show figures for this rental
      result += "\t" + rental.getMovie().getTitle() + "\t" +
          String.valueOf(thisAmount) + "\n";
      totalAmount += thisAmount;
    }

    //add footer lines result
    result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
    result += "You earned " + String.valueOf(frequentRenterPoints)
        + " frequent renter points";
    return result;
  }

  public String getEmail() {
    return email;
  }
}

