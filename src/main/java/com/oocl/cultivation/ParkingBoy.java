package com.oocl.cultivation;

import java.util.Objects;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingBoy that = (ParkingBoy) o;
        return Objects.equals(parkingLot, that.parkingLot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingLot);
    }
}
