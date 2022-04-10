package com.nhnacademy.parking.parkinglot;

import com.nhnacademy.parking.car.Car;

public class ParkingSpace {
    private Car car;
    private String parkingSpaceName;

    public ParkingSpace(Car car, String parkingSpaceName) {
        this.car = car;
        this.parkingSpaceName = parkingSpaceName;
    }
}
