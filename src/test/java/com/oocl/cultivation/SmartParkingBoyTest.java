package com.oocl.cultivation;

import com.oocl.cultivation.smartParkingBoy.SmartParkingBoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    private Car car;
    private List<ParkingLot> parkingLotList;
    private SmartParkingBoy smartParkingBoy;

    @BeforeEach
    void setUp() {
        car = new Car();
        parkingLotList = new ArrayList<>();
        smartParkingBoy = new SmartParkingBoy(parkingLotList);
    }

    @Test
    public void should_return_parking_ticket_when_parking_given_a_car_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        // when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetching_given_a_parking_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //when
        Car fetchedCar = smartParkingBoy.fetchCar(parkingTicket);
        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_multiple_parking_ticket_to_smart_parking_boy() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket1 = smartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(car2);
        //when
        Car fetchedCar1 = smartParkingBoy.fetchCar(parkingTicket1);
        Car fetchedCar2 = smartParkingBoy.fetchCar(parkingTicket2);
        //then
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    public void should_return_exception_when_fetching_given_wrong_parking_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        smartParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();
        //when
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> {
                    smartParkingBoy.fetchCar(wrongTicket);
                });
        //then
        assertSame("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_when_fetching_given_no_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        smartParkingBoy.park(car);
        //when
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> {
                    smartParkingBoy.fetchCar(null);
                });
        //then
        assertSame("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_when_fetching_given_empty_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        smartParkingBoy.park(car);
        //when
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> {
                    smartParkingBoy.fetchCar(null);
                });
        //then
        assertSame("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_when_fetching_given_used_parking_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket ticket = smartParkingBoy.park(car);
        smartParkingBoy.fetchCar(ticket);
        //when
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> {
                    smartParkingBoy.fetchCar(ticket);
                });
        //then
        assertSame("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_when_parking_given_parking_lot_is_full_to_smart_parking_boy() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLotList.add(new ParkingLot(1));
        smartParkingBoy.park(car1);
        //when
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> {
                    smartParkingBoy.park(car2);
                });
        //then
        assertSame("Not enough position.", exception.getMessage());
    }

    @Test
    public void should_return_car_ticket_when_parking_given_parking_lot_1_is_full_to_smart_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkinglot2 = new ParkingLot(2);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkinglot2);
        Car car1 = new Car();
        Car car2 = new Car();
        smartParkingBoy.park(car1);
        //when
        smartParkingBoy.park(car2);
        //then
        assertEquals(1, parkinglot2.getTicketAndCarMap().size());
    }
}
