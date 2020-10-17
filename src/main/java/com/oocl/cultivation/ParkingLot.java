package com.oocl.cultivation;

import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity = 10;
    private Map<ParkingTicket, Car> ticketAndCarMap = new HashMap<>();

    public ParkingLot() {}

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketAndCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetch(ParkingTicket parkingTicket) {
        Car carParked = ticketAndCarMap.get(parkingTicket);
        ticketAndCarMap.remove(parkingTicket);
        return carParked;
    }

    Map<ParkingTicket, Car> getTicketAndCarMap() {
        return ticketAndCarMap;
    }

    public int getMapSize(){
        return ticketAndCarMap.size();
    }

    public int getParkingLotCapacity(){
        return capacity;
    }

    public float getRate(){
        float numberOfEmptySpace = capacity-ticketAndCarMap.size();
        return (numberOfEmptySpace/capacity) *100;
    }

    public int getEmptySpace(){
        return  capacity- ticketAndCarMap.size();
    }

}
