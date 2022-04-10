package com.nhnacademy.parking.parkinglot;

import com.nhnacademy.parking.car.Car;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<String, ParkingSpace> parkingSpaces = new HashMap<>();

    public void enterCar(Car car, String name) {
        ParkingSpace parkingSpace = new ParkingSpace(car, name);
        parkingSpaces.put(name, parkingSpace);
    }

    public int getParkingCarsNum()  {
        return parkingSpaces.size();
    }

    public void exitCar(String parkingSpaceName) {
        parkingSpaces.remove(parkingSpaceName);
    }
}
