package com.thoughtworks.movierental.model;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

public class HtmlStatement {
    private Rentals rentals;
    private String name;

    HtmlStatement(Rentals rentals, String name) {
        this.rentals = rentals;
        this.name = name;
    }

    public String statement() {
        return header() + body() + footer();
    }

    private String header() {
        return "<h1>" + ("Rental Record for " + name) + "</h1>";
    }

    private String body() {
        return rentals.stream()
                .map((rental) -> "<h3>" + rental.movieTitle() + "</h3>-&nbsp<b>" + valueOf(rental.amount()) + "</b></br>")
                .collect(joining());
    }
    private String footer() {
        return "<b>" + "Amount owed is " + valueOf(rentals.totalRentalAmount()) + "</b></br><b>" + "You earned " + valueOf(rentals.totalRenterPoints()) + " frequent renter points" + "</b>";
    }

}
