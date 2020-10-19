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
    private Car car;
    private List<ParkingLot> parkingLotList;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        car = new Car();
        parkingLotList = new ArrayList<>();
        parkingBoy = new ParkingBoy(parkingLotList);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        parkingLotList.add(new ParkingLot());
        // when
        ParkingTicket ticket = parkingBoy.parking(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_fetched_when_fetch_car_given_parking_ticket() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.parking(car);
        // when
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
        //then
        assertSame(car, fetchedCar);
    }

    @Test
    void should_return_corresponding_cars_when_fetch_two_car_given_parking_boy_two_cars() {
        //given
        Car car = new Car();
        Car car2 = new Car();
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.parking(car);
        ParkingTicket parkingTicket2 = parkingBoy.parking(car2);
        //when
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
        Car fetchedCar2 = parkingBoy.fetchCar(parkingTicket2);
        //then
        assertEquals(car, fetchedCar);
        assertEquals(car2, fetchedCar2);

    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetching_a_car_given_wrong_ticket() {
        //given
        parkingLotList.add(new ParkingLot());
        //when
        ParkingTicket originalParkingTicket = parkingBoy.parking(car);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        //then
        assertNotSame(originalParkingTicket, wrongParkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetchCar(wrongParkingTicket);
        });
        assertEquals(UNRECOGNIZED_TICKET, exception.getMessage());
    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetching_given_parking_ticket_been_used() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket validTicket = parkingBoy.parking(car);
        // when
        Car returnCar = parkingBoy.fetchCar(validTicket);
        //then
        assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetchCar(validTicket);
        });
        assertSame(car, returnCar);
    }

    @Test
    void should_return_no_car_when_fetching_a_car_given_no_ticket() {
        //given
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.parking(car);
        //when
        Car returnCar = parkingBoy.fetchCar(parkingTicket);
        //then
        Exception exception = assertThrows(NullParkingTicketException.class,
                () -> parkingBoy.fetchCar(null));
        assertEquals(PROVIDE_TICKET, exception.getMessage());
        assertSame(car, returnCar);
    }

    @Test
    void should_return_FullParkingException_when_fetching_given_parking_lot_capacity_1() {
        //given
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.parking(car);
        //when
        Exception exception =
                assertThrows(FullParkingException.class,
                        () -> parkingBoy.parking(new Car()));
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
        ParkingTicket ticket = parkingBoy.parking(new Car());
        //then
        assertNotNull(ticket);
    }
}
