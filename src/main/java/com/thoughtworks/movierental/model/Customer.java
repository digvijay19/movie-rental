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
    int totalRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";

    for (Rental rental : rentals) {
      double rentalAmount = rental.amount();
      totalRenterPoints += rental.frequentRenterPoints();

      //show figures for this rental
      result += "\t" + rental.getMovie().getTitle() + "\t" +
          String.valueOf(rentalAmount) + "\n";
      totalAmount += rentalAmount;
    }

    //add footer lines result
    result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
    result += "You earned " + String.valueOf(totalRenterPoints)
        + " frequent renter points";
    return result;
  }

  public String getEmail() {
    return email;
  }
}

