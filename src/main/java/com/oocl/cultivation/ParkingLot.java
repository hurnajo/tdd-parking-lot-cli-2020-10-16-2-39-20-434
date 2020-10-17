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
        ParkingTicket ticket = new ParkingTicket();
        ticketCarMap.put(ticket,car);
        if(ticketCarMap.size()>capacity){
            throw new FullParkingException("Not enough position.");
        }
        return getAvailableParkingPosition()<0?ticket:null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(!ticketCarMap.containsKey(parkingTicket)){
            throw new UnrecognizedParkingTicketException("Unrecognized parking ticket.");
        }
        Car car =ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        return car;
    }

    public Map<ParkingTicket, Car> getTicketCarMap() {
        return ticketCarMap;
    }

    public int getCapacity(){
        return capacity;
    }
}
