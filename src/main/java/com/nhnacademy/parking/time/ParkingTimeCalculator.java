package com.nhnacademy.parking.time;

import java.time.LocalDateTime;

public interface ParkingTimeCalculator {
    ParkingTime getParkingTime(LocalDateTime entranceDateTime);
}
