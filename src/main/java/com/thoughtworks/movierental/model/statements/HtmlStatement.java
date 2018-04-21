package com.thoughtworks.movierental.model.statements;

import com.thoughtworks.movierental.model.Rentals;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

public class HtmlStatement {
    private Rentals rentals;
    private String customerName;

    public HtmlStatement(Rentals rentals, String customerName) {
        this.rentals = rentals;
        this.customerName = customerName;
    }

    public String statement() {
        return header() + body() + footer();
    }

    private String header() {
        return "<h1>" + ("Rental Record for " + customerName) + "</h1>";
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
