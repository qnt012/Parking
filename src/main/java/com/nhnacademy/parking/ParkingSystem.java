package com.nhnacademy.parking;

import com.nhnacademy.parking.car.Car;
import com.nhnacademy.parking.exception.ShortOfMoneyException;
import com.nhnacademy.parking.fee.Money;
import com.nhnacademy.parking.fee.ParkingTicket;
import com.nhnacademy.parking.parkinglot.Exit;
import com.nhnacademy.parking.parkinglot.ParkingLot;
import com.nhnacademy.parking.time.ParkingTime;
import com.nhnacademy.parking.time.ParkingTimeCalculator;

public class ParkingSystem {
    private final ParkingLot parkingLot;
    private ParkingTimeCalculator calculator;

    public ParkingSystem(ParkingLot parkingLot, ParkingTimeCalculator calculator) {
        this.parkingLot = parkingLot;
        this.calculator = calculator;
    }


    public ParkingTicket parkCar(Car car, String parkingSpacesName) {
        parkingLot.enterCar(car, parkingSpacesName);
        return new ParkingTicket(parkingSpacesName);
    }

    public Money leaveCar(Money money, ParkingTicket parkingTicket) {
        ParkingTime parkingTime = calculator.getParkingTime(parkingTicket.getEntranceDateTime());

        Money fee = Exit.calculateFee(parkingTime);

        if (fee.getAmount() > money.getAmount()) {
            throw new ShortOfMoneyException("fee: "+fee.getAmount());
        }

        parkingLot.exitCar(parkingTicket.getParkingSpaceName());

        return fee;
    }

    public int checkCarsNum() {
        return parkingLot.getParkingCarsNum();
    }


}
