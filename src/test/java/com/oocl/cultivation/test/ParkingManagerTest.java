package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingManagerTest {
    //Story 6 AC1
    @Test
    void test_manager_instruct_boy_park_and_fetch(){
        ParkingLot parkingLot = new ParkingLot(5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingManager parkingManager = new ParkingManager();
        Car car = new Car();
        parkingManager.addParkingBoy(parkingBoy);
        ParkingTicket ticket = parkingManager.instructPark(parkingBoy, car);
        Car fetched = parkingManager.instructFetch(parkingBoy, ticket);

        assertSame(car, fetched);

    }
    @Test
    void test_manager_park_and_fetch(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingManager parkingManager = new ParkingManager(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingManager.park(car);
        Car fetched = parkingManager.fetch(ticket);
        assertSame(car, fetched);
    }
    @Test
    void test_manager_report_error(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.addParkingBoy(parkingBoy);
        ParkingTicket ticket = parkingManager.instructPark(parkingBoy, car);
        parkingManager.instructFetch(parkingBoy,ticket);
        parkingManager.instructFetch(parkingBoy,ticket);

        assertEquals(
                "Unrecognized parking ticket."+System.getProperty("line.separator"),
                outContent.toString()
        );
    }
}
