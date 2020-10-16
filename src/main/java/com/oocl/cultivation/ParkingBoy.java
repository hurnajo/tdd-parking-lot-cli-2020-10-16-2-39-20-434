package com.oocl.cultivation;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingLot park(Car car) {
        return parkingLot.park(car);
    }
}
