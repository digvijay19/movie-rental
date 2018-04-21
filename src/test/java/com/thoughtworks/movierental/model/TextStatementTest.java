package com.thoughtworks.movierental.model;

import com.thoughtworks.movierental.model.statements.TextStatement;
import org.junit.Test;

import static com.thoughtworks.movierental.model.Movie.REGULAR;
import static org.junit.Assert.assertEquals;

public class TextStatementTest {

    @Test
    public void shouldGenerateTextStatement() {
        String name = "some Name";
        Rentals rentals = new Rentals();
        rentals.add(new Rental(new Movie("Rang de", REGULAR), 1));

        TextStatement htmlStatement = new TextStatement(rentals, name);

        String expectedStatement = "Rental Record for some Name\n" +
                "\tRang de\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expectedStatement, htmlStatement.statement());
    }
}