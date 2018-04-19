package com.thoughtworks.movierental.model;

import org.junit.Test;

import static com.thoughtworks.movierental.model.Movie.CHILDRENS;
import static com.thoughtworks.movierental.model.Movie.NEW_RELEASE;
import static com.thoughtworks.movierental.model.Movie.REGULAR;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void shouldCreateStatement() {
        Rental regularRental1 = new Rental(new Movie("Rang de", REGULAR), 1);
        Rental regularRental2 = new Rental(new Movie("Padmavat", REGULAR), 3);
        Rental childrensRental1 = new Rental(new Movie("Despicable me", CHILDRENS), 4);
        Rental childrensRental2 = new Rental(new Movie("Despicable me 2", CHILDRENS), 3);
        Rental newRelease1 = new Rental(new Movie("Jagga Jasoos", NEW_RELEASE), 1);
        Rental newRelease2 = new Rental(new Movie("Avengers", NEW_RELEASE), 2);
        Customer customer = new Customer("Amitabh Bacchan", "amitabh@bacchan.com");

        customer.addRental(regularRental1);
        customer.addRental(regularRental2);
        customer.addRental(childrensRental1);
        customer.addRental(childrensRental2);
        customer.addRental(newRelease1);
        customer.addRental(newRelease2);

        String expectedStatement = "Rental Record for Amitabh Bacchan\n" +
                "\tRang de\t2.0\n" +
                "\tPadmavat\t3.5\n" +
                "\tDespicable me\t3.0\n" +
                "\tDespicable me 2\t1.5\n" +
                "\tJagga Jasoos\t3.0\n" +
                "\tAvengers\t6.0\n" +
                "Amount owed is 19.0\n" +
                "You earned 7 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    public void shouldCreateStatementWhenNoRentalsPresentForCustomer() {
        Customer customer = new Customer("Amitabh Bacchan", "amitabh@bacchan.com");

        String expectedStatement = "Rental Record for Amitabh Bacchan\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    public void shouldCreateHTMLStatement() {
        Rental regularRental1 = new Rental(new Movie("Rang de", REGULAR), 1);
        Rental regularRental2 = new Rental(new Movie("Padmavat", REGULAR), 3);
        Rental childrensRental1 = new Rental(new Movie("Despicable me", CHILDRENS), 4);
        Rental childrensRental2 = new Rental(new Movie("Despicable me 2", CHILDRENS), 3);
        Rental newRelease1 = new Rental(new Movie("Jagga Jasoos", NEW_RELEASE), 1);
        Rental newRelease2 = new Rental(new Movie("Avengers", NEW_RELEASE), 2);
        Customer customer = new Customer("Amitabh Bacchan", "amitabh@bacchan.com");

        customer.addRental(regularRental1);
        customer.addRental(regularRental2);
        customer.addRental(childrensRental1);
        customer.addRental(childrensRental2);
        customer.addRental(newRelease1);
        customer.addRental(newRelease2);

        String expectedStatement = "<h1>Rental Record for Amitabh Bacchan</h1>" +
                "<h3>Rang de</h3>-&nbsp<b>2.0</b></br><h3>Padmavat</h3>-&nbsp<b>3.5</b></br>" +
                "<h3>Despicable me</h3>-&nbsp<b>3.0</b></br><h3>Despicable me 2</h3>-&nbsp<b>1.5</b></br>" +
                "<h3>Jagga Jasoos</h3>-&nbsp<b>3.0</b></br><h3>Avengers</h3>-&nbsp<b>6.0</b></br>" +
                "<b>Amount owed is 19.0</b></br>" +
                "<b>You earned 7 frequent renter points</b>";

        assertEquals(expectedStatement, customer.htmlStatement());
    }
}