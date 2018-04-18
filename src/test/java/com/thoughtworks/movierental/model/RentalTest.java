package com.thoughtworks.movierental.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.thoughtworks.movierental.model.Movie.CHILDRENS;
import static com.thoughtworks.movierental.model.Movie.NEW_RELEASE;
import static com.thoughtworks.movierental.model.Movie.REGULAR;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentalTest {

    @Mock
    Movie movie;

    @Test
    public void shouldCalculateAmountForRegularMovie() {
        when(movie.getPriceCode()).thenReturn(REGULAR);
        Rental rental = new Rental(movie, 1);

        assertEquals(2, rental.amount(), 0.0);
    }

    @Test
    public void shouldCalculateAmountForRegularMovieRentedForMoreThanTwoDays() {
        when(movie.getPriceCode()).thenReturn(REGULAR);
        Rental rental = new Rental(movie, 4);

        assertEquals(5.0, rental.amount(), 0.0);
    }

    @Test
    public void shouldCalculateAmountForNewReleaseMovie() {
        when(movie.getPriceCode()).thenReturn(NEW_RELEASE);
        Rental rental = new Rental(movie, 1);

        assertEquals(3, rental.amount(), 0.0);
    }

    @Test
    public void shouldCalculateAmountForChildrensMovie() {
        when(movie.getPriceCode()).thenReturn(CHILDRENS);

        Rental rental = new Rental(movie, 1);

        assertEquals(1.5, rental.amount(), 0.0);
    }

    @Test
    public void shouldCalculateAmountForChildrensMovieRentedForMoreThanThreeDays() {
        when(movie.getPriceCode()).thenReturn(CHILDRENS);
        Rental rental = new Rental(movie, 5);

        assertEquals(4.5, rental.amount(), 0.0);
    }
}