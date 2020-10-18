package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.smartParkingBoy.SuperSmartParkingBoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    public void should_return_parking_ticket_when_parking_given_car_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());

        //when
        ParkingTicket ticket = superSmartParkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_fetching_car_given_parking_ticket_to_super_smart_parking_boy() {
        //given

        parkingLotList.add(new ParkingLot());
        ParkingTicket ticket = superSmartParkingBoy.park(car);

        //when
        Car fetchedCar = superSmartParkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_correct_car_when_fetching_car_given_multiple_parking_ticket_to_super_smart_parking_boy() {
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
    public void should_return_exception_when_fetching_car_given_wrong_parking_ticket_to_super_smart_parking_boy() {
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
    public void should_return_exception_when_fetching_car_given_no_ticket_to_super_smart_parking_boy() {
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
    public void should_return_exception_when_fetching_car_given_empty_ticket_to_super_smart_parking_boy() {
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
    public void should_return_exception_when_fetching_car_given_used_parking_ticket_to_super_smart_parking_boy() {
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
    public void should_return_exception_when_parking_given_one_lot_is_at_max_capacity_to_super_smart_parking_boy() {
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
    public void should_return_3_and_6_when_parking_given_2_parking_lot_base_on_rate_to_super_smart_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkinglot2 = new ParkingLot(20);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkinglot2);
        //WHEN
        IntStream.rangeClosed(0,8).forEach(car->superSmartParkingBoy.park(new Car()));
        //THEN
        assertEquals(3, parkingLot1.getMapSize());
        assertEquals(6, parkinglot2.getMapSize());
    }
}
