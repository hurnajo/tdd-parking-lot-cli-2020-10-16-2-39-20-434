package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private List<ParkingBoy> managementList = new ArrayList<>();

    public ParkingLotServiceManager(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }


    public ParkingTicket commandToPark(ParkingBoy parkingBoy, Vehicle vehicle) {
        return parkingBoy.parkVehicle(vehicle);
    }

    public Vehicle commandToFetch(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        return parkingBoy.fetchVehicle(parkingTicket);
    }

    public void setManagementList(List<ParkingBoy> parkingBoys) {
        this.managementList = parkingBoys;
    }

    public List<ParkingBoy> getManagementList() {
        return managementList;
    }

}
