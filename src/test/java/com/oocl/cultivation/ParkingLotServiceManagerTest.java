package com.oocl.cultivation;

import com.oocl.cultivation.smartParkingBoy.SmartParkingBoy;
import com.oocl.cultivation.smartParkingBoy.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotServiceManagerTest {
    Car car = new Car();
    private List<ParkingLot> parkingLot1 = new ArrayList<>();
    private List<ParkingLot> parkingLot2 = new ArrayList<>();
    private List<ParkingLot> parkingLot3 = new ArrayList<>();
    private List<ParkingLot> parkingLotManager = new ArrayList<>();
    ParkingBoy parkingBoy = new ParkingBoy(parkingLot1);
    ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
    ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot3);
    ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotManager);

    @Test
    void should_return_management_List_when_adding_parking_boys_given_management_List() {
        //given
        parkingLot1.add(new ParkingLot());
        parkingLot2.add(new ParkingLot());
        parkingLot3.add(new ParkingLot());
        parkingLotManager.add(new ParkingLot());
        List<ParkingBoy> parkingBoyList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
        parkingLotServiceManager.setManagementList(parkingBoyList);
        //when
        List<ParkingBoy> managementList = parkingLotServiceManager.getManagementList();
        //then
        assertNotNull(managementList);
    }
}
