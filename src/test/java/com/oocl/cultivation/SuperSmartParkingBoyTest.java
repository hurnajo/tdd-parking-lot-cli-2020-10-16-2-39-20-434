package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.smartparkingboy.SuperSmartParkingBoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.oocl.cultivation.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyTest {
    private Vehicle vehicle;
    private List<ParkingLot> parkingLotList;
    private SuperSmartParkingBoy superSmartParkingBoy;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle();
        parkingLotList = new ArrayList<>();
        superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
    }


    @Test
    void should_return_parking_ticket_when_parking_given_car_to_smart_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());

        //when
        ParkingTicket ticket = superSmartParkingBoy.parkVehicle(vehicle);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetching_car_given_parking_ticket_to_super_smart_parking_boy() {
        //given

        parkingLotList.add(new ParkingLot());
        ParkingTicket ticket = superSmartParkingBoy.parkVehicle(vehicle);

        //when
        Vehicle fetchedVehicle = superSmartParkingBoy.fetchVehicle(ticket);

        //then
        assertSame(vehicle, fetchedVehicle);
    }

    @Test
    void should_return_correct_car_when_fetching_car_given_multiple_parking_ticket_to_super_smart_parking_boy() {
        //GIVEN
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket1 = superSmartParkingBoy.parkVehicle(vehicle1);
        ParkingTicket parkingTicket2 = superSmartParkingBoy.parkVehicle(vehicle2);

        //WHEN
        Vehicle fetchedVehicle1 = superSmartParkingBoy.fetchVehicle(parkingTicket1);
        Vehicle fetchedVehicle2 = superSmartParkingBoy.fetchVehicle(parkingTicket2);

        //THEN
        assertSame(vehicle1, fetchedVehicle1);
        assertSame(vehicle2, fetchedVehicle2);
    }

    @Test
    void should_return_exception_when_fetching_car_given_wrong_parking_ticket_to_super_smart_parking_boy() {
        //GIVEN
        parkingLotList.add(new ParkingLot());
        superSmartParkingBoy.parkVehicle(vehicle);
        ParkingTicket wrongTicket = new ParkingTicket();

        //WHEN
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> {
                    superSmartParkingBoy.fetchVehicle(wrongTicket);
                });
        //THEN
        assertSame(UNRECOGNIZED_TICKET, exception.getMessage());
    }

    @Test
    void should_return_exception_when_fetching_car_given_no_ticket_to_super_smart_parking_boy() {
        //GIVEN

        parkingLotList.add(new ParkingLot());
        superSmartParkingBoy.parkVehicle(vehicle);

        //WHEN
        NullParkingTicketException exception = assertThrows(NullParkingTicketException.class,
                () -> {
                    superSmartParkingBoy.fetchVehicle(null);
                });
        //THEN
        assertSame(PROVIDE_TICKET, exception.getMessage());
    }

    @Test
    void should_return_exception_when_fetching_car_given_empty_ticket_to_super_smart_parking_boy() {
        //GIVEN

        parkingLotList.add(new ParkingLot());
        superSmartParkingBoy.parkVehicle(vehicle);

        //WHEN
        NullParkingTicketException exception = assertThrows(NullParkingTicketException.class,
                () -> {
                    superSmartParkingBoy.fetchVehicle(null);
                });
        //THEN
        assertSame(PROVIDE_TICKET, exception.getMessage());
    }

    @Test
    void should_return_exception_when_fetching_car_given_used_parking_ticket_to_super_smart_parking_boy() {
        //GIVEN

        parkingLotList.add(new ParkingLot());
        ParkingTicket ticket = superSmartParkingBoy.parkVehicle(vehicle);
        superSmartParkingBoy.fetchVehicle(ticket);

        //WHEN
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> {
                    superSmartParkingBoy.fetchVehicle(ticket);
                });
        //THEN
        assertSame(UNRECOGNIZED_TICKET, exception.getMessage());
    }

    @Test
    void should_return_exception_when_parking_given_one_lot_is_at_max_capacity_to_super_smart_parking_boy() {
        //GIVEN
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.parkVehicle(vehicle1);

        //WHEN
        FullParkingException exception = assertThrows(FullParkingException.class,
                () -> {
                    superSmartParkingBoy.parkVehicle(vehicle2);
                });
        //THEN
        assertSame(NOT_ENOUGH_SPACE, exception.getMessage());
    }

    @Test
    void should_return_3_and_6_when_parking_given_2_parking_lot_base_on_rate_to_super_smart_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkinglot2 = new ParkingLot(20);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkinglot2);
        //WHEN
        IntStream.rangeClosed(0,8).forEach(car->superSmartParkingBoy.parkVehicle(new Vehicle()));
        //THEN
        assertEquals(3, parkingLot1.getMapSize());
        assertEquals(6, parkinglot2.getMapSize());
    }
}
