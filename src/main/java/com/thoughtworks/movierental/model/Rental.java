package com.thoughtworks.movierental.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

import static com.thoughtworks.movierental.model.Movie.CHILDRENS;
import static com.thoughtworks.movierental.model.Movie.NEW_RELEASE;
import static com.thoughtworks.movierental.model.Movie.REGULAR;

@Entity
public class Rental {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name="DAYS_RENTED")
  private int daysRented;

  @Column(name="START_DATE")
  private Date startDate;

  @JoinColumn(name="MOVIE_ID")
  private Movie movie;

  @ManyToOne
  private Customer customer;

  protected Rental(){}

  public Rental(Movie movie, int daysRented) {
    this(movie, daysRented, Date.valueOf(LocalDate.now()));
  }

  Rental(Movie movie, int daysRented, Date startDate){
    this.movie = movie;
    this.daysRented = daysRented;
    this.startDate = startDate;
  }

  public int getDaysRented() {
    return daysRented;
  }

  public Movie getMovie() {
    return movie;
  }

  public Customer getCustomer() {
    return customer;
  }

  public LocalDate getStartDate() {
    return startDate.toLocalDate();
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  double amount() {
    double amount = 0;
    switch (movie.getPriceCode()) {
      case REGULAR:
        amount += 2;
        if (daysRented > 2)
          amount += (daysRented - 2) * 1.5;
        break;
      case NEW_RELEASE:
        amount += daysRented * 3;
        break;
      case CHILDRENS:
        amount += 1.5;
        if (daysRented > 3)
          amount += (daysRented - 3) * 1.5;
        break;
    }
    return amount;
  }
}