package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.oocl.cultivation.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    private Vehicle vehicle;
    private List<ParkingLot> parkingLotList;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle();
        parkingLotList = new ArrayList<>();
        parkingBoy = new ParkingBoy(parkingLotList);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        // when
        ParkingTicket ticket = parkingBoy.parkVehicle(vehicle);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_fetched_when_fetch_car_given_parking_ticket() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.parkVehicle(vehicle);
        // when
        Vehicle fetchedVehicle = parkingBoy.fetchVehicle(parkingTicket);
        //then
        assertSame(vehicle, fetchedVehicle);
    }

    @Test
    void should_return_corresponding_cars_when_fetch_two_car_given_parking_boy_two_cars() {
        //given
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.parkVehicle(vehicle);
        ParkingTicket parkingTicket2 = parkingBoy.parkVehicle(vehicle2);
        //when
        Vehicle fetchedVehicle = parkingBoy.fetchVehicle(parkingTicket);
        Vehicle fetchedVehicle2 = parkingBoy.fetchVehicle(parkingTicket2);
        //then
        assertEquals(vehicle, fetchedVehicle);
        assertEquals(vehicle2, fetchedVehicle2);

    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetching_a_car_given_wrong_ticket() {
        //given
        parkingLotList.add(new ParkingLot());
        //when
        ParkingTicket originalParkingTicket = parkingBoy.parkVehicle(vehicle);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        //then
        assertNotSame(originalParkingTicket, wrongParkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetchVehicle(wrongParkingTicket);
        });
        assertEquals(UNRECOGNIZED_TICKET, exception.getMessage());
    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetching_given_parking_ticket_been_used() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket validTicket = parkingBoy.parkVehicle(vehicle);
        // when
        Vehicle returnVehicle = parkingBoy.fetchVehicle(validTicket);
        //then
        assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetchVehicle(validTicket);
        });
        assertSame(vehicle, returnVehicle);
    }

    @Test
    void should_return_no_car_when_fetching_a_car_given_no_ticket() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.parkVehicle(vehicle);
        //when
        Vehicle returnVehicle = parkingBoy.fetchVehicle(parkingTicket);
        //then
        Exception exception = assertThrows(NullParkingTicketException.class,
                () -> parkingBoy.fetchVehicle(null));
        assertEquals(PROVIDE_TICKET, exception.getMessage());
        assertSame(vehicle, returnVehicle);
    }

    @Test
    void should_return_FullParkingException_when_fetching_given_parking_lot_capacity_1() {
        //given
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.parkVehicle(vehicle);
        //when
        Exception exception =
                assertThrows(FullParkingException.class,
                        () -> parkingBoy.parkVehicle(new Vehicle()));
        assertEquals(NOT_ENOUGH_SPACE, exception.getMessage());
        //then
    }

    @Test
    void should_return_car_ticket_when_parking_given_car_while_parking_lot_1_is_at_max_capacity_to_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkinglot2 = new ParkingLot(5);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkinglot2);
        parkingBoy.setParkingLot(parkingLotList);
        //when
        ParkingTicket ticket = parkingBoy.parkVehicle(new Vehicle());
        //then
        assertNotNull(ticket);
    }
}
