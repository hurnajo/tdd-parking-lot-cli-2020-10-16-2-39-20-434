package com.oocl.cultivation;

import java.util.Objects;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    private String lastErrorMessage;


    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket == null){
            throw new UnrecognizedParkingTicketException("Please provide your parking ticket.");
        }
        return parkingLot.fetch(parkingTicket);
    }

    private void ParkingLotFullMessage(Car car){
        this.lastErrorMessage = parkingLot.isParkingLotFull(car) == true? "Not enough position." : null;
    }
}
