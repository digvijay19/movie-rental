package com.thoughtworks.movierental.model.statements;

import com.thoughtworks.movierental.model.Rentals;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

public class TextStatement {
    private final Rentals rentals;
    private final String customerName;

    public TextStatement(Rentals rentals, String customerName) {
        this.rentals = rentals;
        this.customerName = customerName;
    }

    public String statement() {
        return statementHeader() + statementBody() + statementFooter();
    }

    private String statementHeader() {
        return "Rental Record for " + customerName + "\n";
    }

    private String statementBody() {
        return rentals.stream()
                .map((rental) -> "\t" + rental.movieTitle() + "\t" + valueOf(rental.amount()) + "\n")
                .collect(joining());
    }

    private String statementFooter() {
        return "Amount owed is " + valueOf(rentals.totalRentalAmount()) + "\n" + "You earned " + valueOf(rentals.totalRenterPoints()) + " frequent renter points";
    }

}
