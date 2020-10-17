package com.oocl.cultivation;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    private String lastErrorMessage;
    private ParkingTicket parkingTicket;


    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if(!parkingLot.isParkingLotFull()){
            return parkingLot.park(car);
        }
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket == null){
            throw new UnrecognizedParkingTicketException("Please provide your parking ticket.");
        }
        return parkingLot.fetch(parkingTicket);
    }
}
