package com.oocl.cultivation.smartParkingBoy;

import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.exception.FullParkingException;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingLot getParkinglot() {
        return parkingLotList.stream().max(Comparator.comparing(ParkingLot::getRate))
                .filter(c -> c.getMapSize() != c.getParkingLotCapacity())
                .orElseThrow(() -> new FullParkingException(NOT_ENOUGH_SPACE));
    }

}
