package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.List;

import static com.oocl.cultivation.constant.Constant.*;

public class ParkingBoy {
    public List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        ParkingLot parkingLot = findParkingLot();
        return parkingLot.parkingTicket(vehicle);
    }

    public Vehicle fetchVehicle(ParkingTicket parkingTicket) {
        Vehicle fetchVehicle = new Vehicle();
        if (checkTicket(parkingTicket)) {
            for (ParkingLot parkingLot : parkingLotList) {
                fetchVehicle = parkingLot.fetchCar(parkingTicket);
            }
            return fetchVehicle;
        } else {
            throw new UnrecognizedParkingTicketException(UNRECOGNIZED_TICKET);
        }
    }

    private boolean checkTicket(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new NullParkingTicketException(PROVIDE_TICKET);
        }
        return parkingLotList.stream().anyMatch(parkingLots ->
                parkingLots.getTicketAndCarMap().containsKey(parkingTicket));
    }

    public ParkingLot findParkingLot() {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getTicketAndCarMap().size() != parkingLot.getParkingLotCapacity()) {
                return parkingLot;
            }
        }
        throw new FullParkingException(NOT_ENOUGH_SPACE);
    }

    public void setParkingLot(List<ParkingLot> listParkingLots) {
        this.parkingLotList = listParkingLots;
    }
}
