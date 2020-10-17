package com.oocl.cultivation.parkingBoy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.FullParkingException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy implements iSmartParkingBoy {
    private List<ParkingLot> parkingLotList;
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }
    public  SmartParkingBoy(List<ParkingLot> parkingLotList){
        super(parkingLotList);
    }
    @Override
    public ParkingLot getParkingLot(){
        return parkingLotList.stream().max(Comparator.comparing(ParkingLot::countEmptySpot))
                .orElseThrow(()->new FullParkingException("Not enough position."));
    }

    @Override
    public void setParkingLot(List<ParkingLot> parkingLots) {
        this.parkingLotList = parkingLots;
    }
}
