package com.oocl.cultivation;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    private String lastErrorMessage;


    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) throws RuntimeException {
        if(parkingTicket == null){
            throw new RuntimeException("Please provide your parking ticket.");
        }
        return parkingLot.fetch(parkingTicket);
    }
}
