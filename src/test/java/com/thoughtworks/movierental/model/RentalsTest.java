package com.thoughtworks.movierental.model;

import org.junit.Test;

import static com.thoughtworks.movierental.model.Movie.CHILDRENS;
import static com.thoughtworks.movierental.model.Movie.REGULAR;
import static org.junit.Assert.*;

public class RentalsTest {

    @Test
    public void shouldCalculateTotalRenterPoints() {
        Rentals rentals = new Rentals();
        Rental regularRental = new Rental(new Movie("Rang de", REGULAR), 1);
        Rental childrensRental = new Rental(new Movie("Despicable me 2", CHILDRENS), 3);

        rentals.add(regularRental);
        rentals.add(childrensRental);

        assertEquals(2, rentals.totalRenterPoints());
    }

    @Test
    public void shouldCalculateTotalAmount() {
        Rentals rentals = new Rentals();
        Rental regularRental = new Rental(new Movie("Rang de", REGULAR), 1);
        Rental childrensRental = new Rental(new Movie("Despicable me 2", CHILDRENS), 3);

        rentals.add(regularRental);
        rentals.add(childrensRental);

        assertEquals(3.5, rentals.totalRentalAmount(), 0.0);
    }

}