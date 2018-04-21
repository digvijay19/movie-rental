package com.thoughtworks.movierental.model;

import org.junit.Test;

import static com.thoughtworks.movierental.model.Movie.REGULAR;
import static org.junit.Assert.*;

public class HtmlStatementTest {

    @Test
    public void shouldGenerateHtmlStatement() {
        String name = "some Name";
        Rentals rentals = new Rentals();
        rentals.add(new Rental(new Movie("Rang de", REGULAR), 1));

        HtmlStatement htmlStatement = new HtmlStatement(rentals, name);

        String expectedStatement = "<h1>Rental Record for some Name</h1><h3>Rang de</h3>-&nbsp<b>2.0</b></br><b>Amount owed is 2.0</b></br><b>You earned 1 frequent renter points</b>";
        assertEquals(expectedStatement, htmlStatement.statement());
    }
}