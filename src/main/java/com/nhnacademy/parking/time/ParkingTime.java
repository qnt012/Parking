package com.nhnacademy.parking.time;

public class ParkingTime {
    int hour;
    int minute;
    int second;

    public ParkingTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
