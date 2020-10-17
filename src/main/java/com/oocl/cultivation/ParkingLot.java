package com.oocl.cultivation;

import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<ParkingTicket, Car> ticketAndCarMap = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketAndCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetch(ParkingTicket parkingTicket) {
        if (!ticketAndCarMap.containsKey(parkingTicket)) {
            throw new UnrecognizedParkingTicketException("Unrecognized parking ticket.");
        }
        Car carParked = ticketAndCarMap.get(parkingTicket);
        ticketAndCarMap.remove(parkingTicket);
        return carParked;
    }

    boolean isFull() {
        return ticketAndCarMap.size() >= capacity;
    }

    public int getTicketAndCarMapSize(){
        return ticketAndCarMap.size();
    }

    public int getParkingLotCapacity(){
        return capacity;
    }
    public int getEmptySpace(){
        return  capacity- ticketAndCarMap.size();
    }

    Map<ParkingTicket, Car> getTicketAndCarMap() {
        return ticketAndCarMap;
    }
}
