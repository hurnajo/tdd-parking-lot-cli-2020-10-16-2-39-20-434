package com.oocl.cultivation.parkingBoy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.FullParkingException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> parkingLotList;
    public  SmartParkingBoy(List<ParkingLot> parkingLotList){
        super(parkingLotList);
    }
    public ParkingTicket park(Car car) {
        ParkingLot lot = getParkinglot(super.getParkingLotList());
        return lot.park(car);
    }
    public ParkingLot getParkinglot(List<ParkingLot> parkingLotList) {
        return  parkingLotList.stream().max(Comparator.comparing(ParkingLot::getEmpty))
                .filter(c -> c.getTickatCarMapSize()!=c.getCapacity())
                .orElseThrow(() -> new FullParkingException("Not enough position."));
    }
}
