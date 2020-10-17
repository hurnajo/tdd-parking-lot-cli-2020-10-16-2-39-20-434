package com.oocl.cultivation.parkingBoy;

import com.oocl.cultivation.ParkingLot;

import java.util.List;

public interface iSmartParkingBoy {
    ParkingLot getParkingLot();
    void setParkingLot(List<ParkingLot> parkingLots);
}
