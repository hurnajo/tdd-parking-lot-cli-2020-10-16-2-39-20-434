package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.List;

public class ParkingBoy {
    public List<ParkingLot> parkingLotList;
    public static final String PROVIDE_TICKET = "Please provide your parking ticket.";
    public static final String UNRECOGNIZE_TICKET = "Unrecognized parking ticket.";
    public static final String NOT_ENOUGH_SPACE = "Not enough position.";

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = getParkinglot();
        return parkingLot.parkingTicket(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        Car fetchCar = new Car();
        if (checkTicket(parkingTicket)) {
            for (ParkingLot parkingLot : parkingLotList) {
                fetchCar = parkingLot.fetchCar(parkingTicket);
            }
            return fetchCar;
        } else {
            throw new UnrecognizedParkingTicketException(UNRECOGNIZE_TICKET);
        }
    }

    private boolean checkTicket(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new NullParkingTicketException(PROVIDE_TICKET);
        }
        return parkingLotList.stream().anyMatch(parkingLots ->
                parkingLots.getTicketAndCarMap().containsKey(parkingTicket));
    }

    public ParkingLot getParkinglot() {
        for(ParkingLot parkingLot: parkingLotList){
            if(parkingLot.getTicketAndCarMap().size()!=parkingLot.getParkingLotCapacity()){
                return parkingLot;
            }
        }
        throw new FullParkingException(NOT_ENOUGH_SPACE);
    }

    public void setParkingLot(List<ParkingLot> listParkingLots) {
        this.parkingLotList = listParkingLots;
    }
}
