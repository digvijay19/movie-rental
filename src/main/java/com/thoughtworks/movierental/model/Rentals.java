package com.thoughtworks.movierental.model;

import java.util.ArrayList;

public class Rentals extends ArrayList<Rental> {

    double totalRentalAmount() {
        return stream().mapToDouble(Rental::amount).sum();
    }

    int totalRenterPoints() {
        return stream().mapToInt(Rental::frequentRenterPoints).sum();
    }
}
