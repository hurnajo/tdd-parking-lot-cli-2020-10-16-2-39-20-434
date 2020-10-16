package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket,Car> ticketCarMap = new HashMap<>();
    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        ticketCarMap.put(ticket,car);
        return ticket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return ticketCarMap.get(parkingTicket);
    }
}
