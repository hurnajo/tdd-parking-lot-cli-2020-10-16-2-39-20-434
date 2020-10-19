package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.smartparkingboy.SmartParkingBoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.oocl.cultivation.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {
    //TODO constant put here
    private Vehicle vehicle;
    private List<ParkingLot> parkingLotList;
    private SmartParkingBoy smartParkingBoy;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle();
        parkingLotList = new ArrayList<>();
        smartParkingBoy = new SmartParkingBoy(parkingLotList);
    }

    @Test
    void should_return_parking_ticket_when_parking_given_a_car_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        // when
        ParkingTicket parkingTicket = smartParkingBoy.parkVehicle(vehicle);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetching_given_a_parking_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = smartParkingBoy.parkVehicle(vehicle);
        //when
        Vehicle fetchedVehicle = smartParkingBoy.fetchVehicle(parkingTicket);
        //then
        assertSame(vehicle, fetchedVehicle);
    }

    @Test
    void should_return_correct_car_when_fetching_given_multiple_parking_ticket_to_smart_parking_boy() {
        //given
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket1 = smartParkingBoy.parkVehicle(vehicle1);
        ParkingTicket parkingTicket2 = smartParkingBoy.parkVehicle(vehicle2);
        //when
        Vehicle fetchedVehicle1 = smartParkingBoy.fetchVehicle(parkingTicket1);
        Vehicle fetchedVehicle2 = smartParkingBoy.fetchVehicle(parkingTicket2);
        //then
        assertSame(vehicle1, fetchedVehicle1);
        assertSame(vehicle2, fetchedVehicle2);
    }

    @Test
    void should_return_exception_when_fetching_given_wrong_parking_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        smartParkingBoy.parkVehicle(vehicle);
        ParkingTicket wrongTicket = new ParkingTicket();
        //when
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> {
                    smartParkingBoy.fetchVehicle(wrongTicket);
                });
        //then
        assertSame(UNRECOGNIZED_TICKET, exception.getMessage());
    }

    @Test
    void should_return_exception_when_fetching_given_no_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        smartParkingBoy.parkVehicle(vehicle);
        //when
        NullParkingTicketException exception = assertThrows(NullParkingTicketException.class,
                () -> {
                    smartParkingBoy.fetchVehicle(null);
                });
        //then
        assertSame(PROVIDE_TICKET, exception.getMessage());
    }

    @Test
    void should_return_exception_when_fetching_given_empty_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        smartParkingBoy.parkVehicle(vehicle);
        //when
        NullParkingTicketException exception = assertThrows(NullParkingTicketException.class,
                () -> {
                    smartParkingBoy.fetchVehicle(null);
                });
        //then
        assertSame(PROVIDE_TICKET, exception.getMessage());
    }

    @Test
    void should_return_exception_when_fetching_given_used_parking_ticket_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket ticket = smartParkingBoy.parkVehicle(vehicle);
        smartParkingBoy.fetchVehicle(ticket);
        //when
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> {
                    smartParkingBoy.fetchVehicle(ticket);
                });
        //then
        assertSame(UNRECOGNIZED_TICKET, exception.getMessage());
    }

    @Test
    void should_return_exception_when_parking_given_parking_lot_is_full_to_smart_parking_boy() {
        //given
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        parkingLotList.add(new ParkingLot(1));
        smartParkingBoy.parkVehicle(vehicle1);
        //when
        FullParkingException exception = assertThrows(FullParkingException.class,
                () -> {
                    smartParkingBoy.parkVehicle(vehicle2);
                });
        //then
        assertSame(NOT_ENOUGH_SPACE, exception.getMessage());
    }

    @Test
    void should_return_car_ticket_when_parking_given_parking_lot_1_is_full_to_smart_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkinglot2 = new ParkingLot(2);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkinglot2);
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        smartParkingBoy.parkVehicle(vehicle1);
        //when
        smartParkingBoy.parkVehicle(vehicle2);
        //then
        assertEquals(1, parkinglot2.getTicketAndCarMap().size());
    }
}
