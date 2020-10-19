package com.oocl.cultivation.smartparkingboy;

import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.exception.FullParkingException;

import java.util.Comparator;
import java.util.List;

import static com.oocl.cultivation.constant.Constant.NOT_ENOUGH_SPACE;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingLot findParkingLot() {
        return  parkingLotList.stream()
                .filter(parkingLot -> parkingLot.getMapSize()!=parkingLot.getParkingLotCapacity())
                .max(Comparator.comparing(ParkingLot::getEmptySpace))
                .orElseThrow(() -> new FullParkingException(NOT_ENOUGH_SPACE));
    }

}
