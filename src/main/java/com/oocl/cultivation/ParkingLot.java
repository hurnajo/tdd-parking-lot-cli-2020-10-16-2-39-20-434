package com.oocl.cultivation;

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
        return getAvailableParkingPosition()<0?ticket:null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return ticketCarMap.get(parkingTicket);
    }

    public boolean isParkingLotFull(Car car){
        return park(car) == null ? true : false;
    }

}
