package com.nhnacademy.parking.fee;

import java.time.LocalDateTime;

public class ParkingTicket {
    private final String parkingSpaceName;
    private final LocalDateTime entranceDateTime;

    public ParkingTicket(String parkingSpaceName) {
        this.parkingSpaceName = parkingSpaceName;
        this.entranceDateTime = LocalDateTime.now();
    }

    public LocalDateTime getEntranceDateTime() {
        return entranceDateTime;
    }


    public String getParkingSpaceName() {
        return parkingSpaceName;
    }
}
