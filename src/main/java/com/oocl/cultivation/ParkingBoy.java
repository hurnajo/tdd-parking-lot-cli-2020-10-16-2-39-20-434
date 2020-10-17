package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLotList = new ArrayList<>();
        this.parkingLotList.add(parkingLot);
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = getParkinglot();
        return parkingLot.park(car);
    }


    public Car fetch(ParkingTicket parkingTicket) {
        Car carFetched = new Car();
        if (checkTicket(parkingTicket)) {
            for (ParkingLot parkingLot : parkingLotList) {
                carFetched = parkingLot.fetch(parkingTicket);
            }
            return carFetched;
        } else {
            throw new UnrecognizedParkingTicketException("Unrecognized parking ticket.");
        }
    }

    public ParkingLot getParkinglot() {
        for(ParkingLot parkingLot: parkingLotList){
            if(!parkingLot.isFull()){
                return parkingLot;
            }
        }
        throw new FullParkingException("Not enough position.");
    }

    public void setParkingLot(List<ParkingLot> listParkingLots) {
        this.parkingLotList = listParkingLots;
    }

    private boolean checkTicket(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new NullParkingTicketException("Please provide your parking ticket.");
        }
        return parkingLotList.stream().anyMatch(parkingLots ->
                parkingLots.getTicketCarMap().containsKey(parkingTicket));
    }
}
