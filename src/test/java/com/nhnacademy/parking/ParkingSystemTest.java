package com.nhnacademy.parking;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.nhnacademy.parking.car.Car;
import com.nhnacademy.parking.exception.ShortOfMoneyException;
import com.nhnacademy.parking.fee.Money;
import com.nhnacademy.parking.fee.ParkingTicket;
import com.nhnacademy.parking.parkinglot.Entrance;
import com.nhnacademy.parking.parkinglot.ParkingLot;
import com.nhnacademy.parking.time.ParkingTime;
import com.nhnacademy.parking.time.ParkingTimeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ParkingSystemTest {
    private ParkingLot parkingLot;
    private ParkingSystem system;
    private ParkingTimeCalculator calculator;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
        calculator = mock(ParkingTimeCalculator.class);
        system = new ParkingSystem(parkingLot, calculator);
    }

    @DisplayName("주차장에 들어온 차의 번호판을 인식한다.")
    @Test
    void scan() {
        Car car = new Car("1111");

        assertEquals("1111", Entrance.scan(car));
    }

    @DisplayName("차를 A-1에 주차한다.")
    @Test
    void parkCar() {
        Car car = new Car("1111");
        String parkingSpaceName = "A1";

        system.parkCar(car, parkingSpaceName);
        assertEquals(1, system.checkCarsNum());
    }


    @DisplayName("주자장에서 차가 나간다. 나가려면 주차 시간만큼 결제를 해야한다.")
    @ParameterizedTest
    @CsvSource({
            "0, 29, 0, 1000",
            "0, 30, 1, 1500",
            "0, 50, 0, 2000",
            "1, 1, 0, 3000",
            "6, 0, 0, 10000",
            "24, 0, 1, 11000"
    })
    void leaveCar(int hour, int minute, int second, int feeAmount) {
        Money money = new Money(50000);
        ParkingTicket parkingTicket = new ParkingTicket("A1");

        ParkingTime parkingTime = new ParkingTime(hour, minute, second);
        when(calculator.getParkingTime(parkingTicket.getEntranceDateTime())).thenReturn(parkingTime);

        Money fee = new Money(feeAmount);
        Money result = system.leaveCar(money, parkingTicket);
        assertEquals(fee ,result);

        verify(calculator).getParkingTime(parkingTicket.getEntranceDateTime());
    }

    @DisplayName("돈이 없으면 차가 나가지 못하고 ShortOfMoney 예외를 던진다.")
    @Test
    void leaveCar_leastMoney_throwShortOfMoneyException() {
        Money money = new Money(1000);
        ParkingTicket parkingTicket = new ParkingTicket("A1");

        ParkingTime parkingTime = new ParkingTime(0, 30, 1);
        when(calculator.getParkingTime(parkingTicket.getEntranceDateTime())).thenReturn(parkingTime);

        assertThatThrownBy(() -> system.leaveCar(money, parkingTicket))
                    .isInstanceOf(ShortOfMoneyException.class)
                    .hasMessageContainingAll("Short of Money");

        verify(calculator).getParkingTime(parkingTicket.getEntranceDateTime());
    }
}