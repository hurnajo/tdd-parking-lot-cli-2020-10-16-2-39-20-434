package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private List<ParkingBoy> managementList = new ArrayList<>();

    public ParkingLotServiceManager(List<ParkingLot> parkingLotList){
        super(parkingLotList);
    }

    public void setManagementList(List<ParkingBoy> parkingBoys){
        this.managementList=parkingBoys;
    }

    public List<ParkingBoy> getManagementList() {
        return managementList;
    }
}
