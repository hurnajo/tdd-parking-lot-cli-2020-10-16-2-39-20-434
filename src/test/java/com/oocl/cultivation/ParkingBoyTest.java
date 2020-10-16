package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
