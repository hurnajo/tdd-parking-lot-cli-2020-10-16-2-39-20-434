package com.oocl.cultivation;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return (ParkingTicket) parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return null;
    }
}
