package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<ParkingTicket,Car> ticketCarMap = new HashMap<>();
    private int numberOfCarsParked;

    public ParkingLot() {
        this.capacity = 10;
        this.numberOfCarsParked = 0;
    }

    public ParkingLot(int capacity,int numberOfCarsParked){
        this.capacity = capacity;
        this.numberOfCarsParked = numberOfCarsParked;
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
        Car originalTicket = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        return originalTicket;
    }


    public boolean isParkingLotFull() {
        return capacity == numberOfCarsParked;
    }
}
