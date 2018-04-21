package com.thoughtworks.movierental.model;

import org.junit.Before;
import org.junit.Test;

import static com.thoughtworks.movierental.model.Movie.CHILDRENS;
import static com.thoughtworks.movierental.model.Movie.NEW_RELEASE;
import static com.thoughtworks.movierental.model.Movie.REGULAR;
import static org.junit.Assert.*;

public class RentalsTest {
    private Rentals rentals;

    @Before
    public void before() {
        rentals = new Rentals();
        Rental rang_de = new Rental(new Movie("Rang de", REGULAR), 1);
        Rental inception = new Rental(new Movie("Inception", NEW_RELEASE), 2);
        Rental minions = new Rental(new Movie("Minions", CHILDRENS), 3);

        rentals.add(rang_de);
        rentals.add(inception);
        rentals.add(minions);
    }

    @Test
    public void shouldCalculateTotalRentalAmount() {
        assertEquals(9.5, rentals.totalRentalAmount(), 0.0);
    }

    @Test
    public void shouldCalculateTotalFrequentRenterPoints() {
        assertEquals(9.5, rentals.totalRentalAmount(), 0.0);
    }

}