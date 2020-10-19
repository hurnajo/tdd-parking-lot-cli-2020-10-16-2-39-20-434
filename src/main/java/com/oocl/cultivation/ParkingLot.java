package com.oocl.cultivation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private int capacity = 10;
    private ConcurrentHashMap<ParkingTicket, Vehicle> ticketAndCarMap = new ConcurrentHashMap<>();

    public ParkingLot() {
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    ParkingTicket parkingTicket(Vehicle vehicle) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketAndCarMap.put(parkingTicket, vehicle);
        return parkingTicket;
    }

    Vehicle fetchCar(ParkingTicket parkingTicket) {
        Vehicle vehicleParked = ticketAndCarMap.get(parkingTicket);
        ticketAndCarMap.remove(parkingTicket);
        return vehicleParked;
    }

    Map<ParkingTicket, Vehicle> getTicketAndCarMap() {
        return ticketAndCarMap;
    }

    public int getMapSize() {
        return ticketAndCarMap.size();
    }

    public int getParkingLotCapacity() {
        return capacity;
    }

    public double getRate() {
        double numberOfEmptySpace = getEmptySpace();
        return (numberOfEmptySpace / capacity) * 100;
    }

    public int getEmptySpace() {
        return capacity - ticketAndCarMap.size();
    }

}
