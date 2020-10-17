package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private  ParkingLot parkingLot;
    private List<ParkingLot> parkingLotList;
    private String lastErrorMessage;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        parkingLot = getParkinglot(parkingLotList);
        return parkingLot.park(car);
    }

    private ParkingLot getParkinglot(List<ParkingLot> parkingLotList) {
        for(ParkingLot parkingLot: parkingLotList){
            if(parkingLot.getTicketCarMap().size()<parkingLot.getCapacity()){
                return parkingLot;
            }
        }
        throw new RuntimeException("Not enough position.");
    }

    public Car fetch(ParkingTicket parkingTicket) throws RuntimeException {
        if(parkingTicket == null){
            throw new RuntimeException("Please provide your parking ticket.");
        }
        return parkingLot.fetch(parkingTicket);
    }
}
