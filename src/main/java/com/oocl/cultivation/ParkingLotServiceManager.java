package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private List<ParkingBoy> managementList = new ArrayList<>();

    public ParkingLotServiceManager(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public ParkingTicket commandToPark(ParkingBoy parkingBoy, Car car) {
        return parkingBoy.park(car);
    }

    public Car commandToFetch(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        return parkingBoy.fetch(parkingTicket);
    }

    public void setManagementList(List<ParkingBoy> parkingBoys) {
        this.managementList = parkingBoys;
    }

    public List<ParkingBoy> getManagementList() {
        return managementList;
    }

}
