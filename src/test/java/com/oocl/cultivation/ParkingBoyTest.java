package com.oocl.cultivation;

import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {

    @Test
    public void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        //when
        ParkingLot ticket = parkingBoy.park(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_fetched_when_fetch_car_given_parking_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //when
        Car fetchedCard = parkingBoy.fetch(parkingTicket);
        //then
        assertSame(car, fetchedCard);
    }

    @Test
    public void should_return_corresponding_cars_when_fetch_two_car_given_parking_boy_two_cars() {
        //given
        Car car = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        //when
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);
        //then
        assertEquals(car, fetchedCar);
        assertEquals(car2, fetchedCar2);

    }

    @Test
    public void should_return_no_car_when_fetching_a_car_given_wrong_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        //when
        ParkingTicket originalParkingTicket = parkingBoy.park(car);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        //then
        assertNotSame(originalParkingTicket,wrongParkingTicket);
        Exception exception = assertThrows(RuntimeException.class,()->
                parkingBoy.fetch(wrongParkingTicket));
        assertEquals("Unrecognized parking ticket.",exception.getMessage());
    }

    @Test
    void should_return_no_car_when_fetching_a_car_given_no_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        ParkingTicket parkingTicket = parkingBoy.park(null);
        Car fetchCar = parkingBoy.fetch(parkingTicket);
        //then
        Exception exception = assertThrows(RuntimeException.class, () -> parkingBoy.fetch(null));
        assertEquals("Please provide your parking ticket.", exception.getMessage());

    }

    @Test
    void should_return_no_car_when_fetching_given_parking_ticket_been_used() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(null);
        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket);
        //then
        assertNull(fetchCar);
    }

    @Test
    void should_return_park_car_failed_and_no_ticket_returned_when_fetching_given_parking_lot_capacity_1() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //then
        assertNull(parkingTicket);
    }

}
