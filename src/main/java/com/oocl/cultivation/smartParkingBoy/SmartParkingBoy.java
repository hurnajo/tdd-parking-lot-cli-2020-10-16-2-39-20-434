package com.oocl.cultivation.smartParkingBoy;

import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingLot getParkinglot() {
        return  parkingLotList.stream().max(Comparator.comparing(ParkingLot::getEmptySpace))
                .filter(c -> c.getMapSize()!=c.getParkingLotCapacity())
                .orElseThrow(() -> new RuntimeException(NOT_ENOUGH_SPACE));
    }

}
