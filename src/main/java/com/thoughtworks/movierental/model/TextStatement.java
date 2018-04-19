package com.thoughtworks.movierental.model;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

public class TextStatement {
    private final String name;
    private final Rentals rentals;

    TextStatement(String name, Rentals rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String generate() {
        return header() + body() + footer();
    }

    private String header() {
        return "Rental Record for " + name + "\n";
    }

    private String body() {
        return rentals.stream()
                .map((rental) -> "\t" + rental.getMovie().getTitle() + "\t" + valueOf(rental.amount()) + "\n")
                .collect(joining());
    }

    private String footer() {
        return "Amount owed is " + valueOf(rentals.totalRentalAmount()) + "\n" + "You earned " + valueOf(rentals.totalRenterPoints()) + " frequent renter points";
    }
}
