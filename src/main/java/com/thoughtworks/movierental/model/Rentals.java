package com.thoughtworks.movierental.model;

import java.util.ArrayList;

public class Rentals extends ArrayList<Rental> {

    public double totalRentalAmount() {
        return this.stream().mapToDouble(Rental::amount).sum();
    }

    public int totalRenterPoints() {
        return this.stream().mapToInt(Rental::frequentRenterPoints).sum();
    }

}
