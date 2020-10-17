package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

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
        throw new FullParkingException("Not enough position.");
    }

    public Car fetch(ParkingTicket parkingTicket) throws NullParkingTicketException {
        if(parkingTicket == null){
            throw new NullParkingTicketException("Please provide your parking ticket.");
        }
        return parkingLot.fetch(parkingTicket);
    }
}
