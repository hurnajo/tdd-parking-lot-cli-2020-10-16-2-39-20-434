package com.oocl.cultivation;

import com.oocl.cultivation.parkingBoy.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperSmartParkingBoyTest {

    @Test
    public void should_return_a_parking_ticket_when_parking_given_a_car_to_smart_parking_boy() {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        SuperSmartParkingBoy smartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        ParkingTicket ticket = smartParkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }
}
