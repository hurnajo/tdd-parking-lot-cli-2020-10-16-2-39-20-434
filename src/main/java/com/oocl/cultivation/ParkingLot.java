package com.oocl.cultivation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private int capacity = 10;
    private ConcurrentHashMap<ParkingTicket, Car> ticketAndCarMap = new ConcurrentHashMap<>();

    public ParkingLot() {
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    ParkingTicket parkingTicket(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketAndCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetchCar(ParkingTicket parkingTicket) {
        Car carParked = ticketAndCarMap.get(parkingTicket);
        ticketAndCarMap.remove(parkingTicket);
        return carParked;
    }

    Map<ParkingTicket, Car> getTicketAndCarMap() {
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
