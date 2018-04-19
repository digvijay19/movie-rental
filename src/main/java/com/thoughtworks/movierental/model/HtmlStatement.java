package com.thoughtworks.movierental.model;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

public class HtmlStatement {
    private final String name;
    private final Rentals rentals;

    HtmlStatement(String name, Rentals rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String generate() {
        return header() + body() + footer();
    }

    private String header() {
        return "<h1>" + ("Rental Record for " + name) + "</h1>";
    }

    private String body() {
        return rentals.stream()
                .map((rental) -> "<h3>" + rental.getMovie().getTitle() + "</h3>-&nbsp<b>" + valueOf(rental.amount()) + "</b></br>")
                .collect(joining());
    }

    private String footer() {
        return "<b>" + "Amount owed is " + valueOf(rentals.totalRentalAmount()) + "</b></br><b>" + "You earned " + valueOf(rentals.totalRenterPoints()) + " frequent renter points" + "</b>";
    }
}
