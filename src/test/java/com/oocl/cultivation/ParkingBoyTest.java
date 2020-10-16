package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

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
        assertSame(car,fetchedCard);
    }
}
