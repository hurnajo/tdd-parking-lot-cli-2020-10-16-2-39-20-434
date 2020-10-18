package com.oocl.cultivation;

import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.smartParkingBoy.SmartParkingBoy;
import com.oocl.cultivation.smartParkingBoy.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotServiceManagerTest {
    Car car = new Car();
    private List<ParkingLot> lotForParkingBoy = new ArrayList<>();
    private List<ParkingLot> lotForSmartParkingBoy = new ArrayList<>();
    private List<ParkingLot> lotForSuperSmartParkingBoy = new ArrayList<>();
    private List<ParkingLot> lotForParkingLotManager = new ArrayList<>();
    private List<ParkingBoy> parkingBoyList = new ArrayList<>();
    List<ParkingLot> parkingLotList = new ArrayList<>();
    ParkingBoy parkingBoy = new ParkingBoy(lotForParkingBoy);
    ParkingBoy smartParkingBoy = new SmartParkingBoy(lotForSmartParkingBoy);
    ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(lotForSuperSmartParkingBoy);
    ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(lotForParkingLotManager);

    @Test
    void should_return_management_List_when_adding_parking_boys_given_management_List() {
        //given
        lotForParkingBoy.add(new ParkingLot());
        lotForSmartParkingBoy.add(new ParkingLot());
        lotForSuperSmartParkingBoy.add(new ParkingLot());
        lotForParkingLotManager.add(new ParkingLot());
        List<ParkingBoy> parkingBoyList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
        parkingLotServiceManager.setManagementList(parkingBoyList);
        //when
        List<ParkingBoy> managementList = parkingLotServiceManager.getManagementList();
        //then
        assertNotNull(managementList);
    }

    @Test
    void should_return_parking_ticket_when_giving_command_given_to_parking_boy(){
    //given
        lotForParkingBoy.add(new ParkingLot());
        lotForSmartParkingBoy.add(new ParkingLot());
        lotForSuperSmartParkingBoy.add(new ParkingLot());
        parkingLotServiceManager.setManagementList(parkingBoyList);
    //when
        ParkingTicket parkingTicket = parkingLotServiceManager.commandToPark(parkingBoy, car);
    //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetching_given_parking_ticket_to_parking_lot_service_manager(){
    //given
        lotForParkingLotManager.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
    //when
        Car fetchCar = parkingLotServiceManager.fetch(parkingTicket);
    //then
        assertSame(car,fetchCar);
    }

    @Test
    void should_return_exception_when_fetching_car_given_wrong_ticket_to_parking_boy(){
    //given
        parkingLotList.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();
        parkingBoyList.add(parkingBoy);
        parkingLotServiceManager.setManagementList(parkingBoyList);

    //when
    //then
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                ()->{parkingLotServiceManager.commandToFetch(parkingBoyList,wrongTicket);});
        assertSame("Unrecognized parking ticket.",exception.getMessage());
    }
}
