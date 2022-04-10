package com.nhnacademy.parking.parkinglot;

import com.nhnacademy.parking.fee.Money;
import com.nhnacademy.parking.time.ParkingTime;

public class Exit {
    public static Money calculateFee(ParkingTime parkingTime){
        int hour = parkingTime.getHour();
        int minute = parkingTime.getMinute();
        int second = parkingTime.getSecond();
        int day = hour / 24;

        int dayFeeAmount = 0;

        if (day > 0) {
            dayFeeAmount += 10000 * day;
            hour -= 24;
        }

        int amount = 1000;

        if (hour == 0 && minute < 30) {
            return new Money(dayFeeAmount + amount);
        }
        if (hour == 0){
            amount += (minute - 20) / 10 * 500;
        }
        else {
            amount += hour * 3000 + minute / 10 * 500;
        }
        if (hour == 1) {
            amount -= 1000;
        }
        if (minute % 10 == 0 && second == 0) {
            amount -= 500;
        }
        if (amount > 10000) {
            amount = 10000;
        }

        return new Money(dayFeeAmount + amount);
    }
}
