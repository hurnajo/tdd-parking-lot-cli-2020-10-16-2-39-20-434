package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.parkingBoy.SuperSmartParkingBoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {
    private Car car;
    private List<ParkingLot> parkingLotList;
    private SuperSmartParkingBoy superSmartParkingBoy;

    @BeforeEach
    void setUp() {
        car = new Car();
        parkingLotList = new ArrayList<>();
        superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
    }


    @Test
    public void should_return_a_parking_ticket_when_parking_given_a_car_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());

        //when
        ParkingTicket ticket = superSmartParkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_a_car_when_fetching_given_a_parking_ticket_to_parking_boy() {
        //given

        parkingLotList.add(new ParkingLot());
        ParkingTicket ticket = superSmartParkingBoy.park(car);

        //when
        Car fetchedCar = superSmartParkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_multiple_parking_ticket_to_parking_boy() {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(car2);

        //WHEN
        Car fetchedCar1 = superSmartParkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = superSmartParkingBoy.fetch(parkingTicket2);

        //THEN
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    public void should_return_exception_when_fetching_given_wrong_parking_ticket_to_parking_boy() {
        //GIVEN
        parkingLotList.add(new ParkingLot());
        superSmartParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //WHEN
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> {
                    superSmartParkingBoy.fetch(wrongTicket);
                });
        //THEN
        assertSame("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_when_fetching_given_no_ticket_to_parking_boy() {
        //GIVEN

        parkingLotList.add(new ParkingLot());
        superSmartParkingBoy.park(car);

        //WHEN
        NullParkingTicketException exception = assertThrows(NullParkingTicketException.class,
                () -> {
                    superSmartParkingBoy.fetch(null);
                });
        //THEN
        assertSame("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_when_fetching_given_empty_ticket_to_parking_boy() {
        //GIVEN

        parkingLotList.add(new ParkingLot());
        superSmartParkingBoy.park(car);

        //WHEN
        NullParkingTicketException exception = assertThrows(NullParkingTicketException.class,
                () -> {
                    superSmartParkingBoy.fetch(null);
                });
        //THEN
        assertSame("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_when_fetching_given_used_parking_ticket_to_parking_boy() {
        //GIVEN

        parkingLotList.add(new ParkingLot());
        ParkingTicket ticket = superSmartParkingBoy.park(car);
        superSmartParkingBoy.fetch(ticket);

        //WHEN
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> {
                    superSmartParkingBoy.fetch(ticket);
                });
        //THEN
        assertSame("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_when_parking_given_car_while_parking_lot_is_at_max_capacity_to_parking_boy() {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(car1);

        //WHEN
        FullParkingException exception = assertThrows(FullParkingException.class,
                () -> {
                    superSmartParkingBoy.park(car2);
                });
        //THEN
        assertSame("Not enough position.", exception.getMessage());
    }

    @Test
    public void should_return_car_ticket_when_parking_given_car_while_parking_lot_1_is_at_max_capacity_to_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkinglot2 = new ParkingLot(20);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkinglot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();
        Car car7 = new Car();
        Car car8 = new Car();
        SuperSmartParkingBoy smartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        smartParkingBoy.park(car1);
        smartParkingBoy.park(car2);
        smartParkingBoy.park(car3);
        smartParkingBoy.park(car4);
        smartParkingBoy.park(car5);
        smartParkingBoy.park(car1);
        smartParkingBoy.park(car6);
        smartParkingBoy.park(car7);
        //WHEN
        smartParkingBoy.park(car8);
        //THEN
        assertEquals(3, parkingLot1.getMapSize());
        assertEquals(6, parkinglot2.getMapSize());
    }
}
