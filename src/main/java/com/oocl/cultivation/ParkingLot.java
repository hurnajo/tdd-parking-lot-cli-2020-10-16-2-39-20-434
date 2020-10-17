package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private final Map<ParkingTicket,Car> ticketCarMap = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity){
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return ticketCarMap.size() - capacity;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketCarMap.put(parkingTicket,car);
        return parkingTicket;
    }
     Car fetch(ParkingTicket parkingTicket) {
        if(!ticketCarMap.containsKey(parkingTicket)){
            throw new UnrecognizedParkingTicketException("Unrecognized parking ticket.");
        }
        Car carParked = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        return carParked;
    }

    public int getCapacity(){
        return capacity;
    }

    public int countEmptySpot() {
        return capacity - ticketCarMap.size();
    }

    boolean isParkingLotFull(){
        return ticketCarMap.size() >= capacity;
    }

    public Map<ParkingTicket, Car> getTicketCarMap() {
        return ticketCarMap;
    }
}
