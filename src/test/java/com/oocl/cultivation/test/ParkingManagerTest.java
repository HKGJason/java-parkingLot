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
        ParkingLot p1 = new ParkingLot(5);
        ParkingBoy b1 = new ParkingBoy(p1);
        ParkingManager m1 = new ParkingManager();
        Car car = new Car();
        m1.addParkingBoy(b1);
        ParkingTicket ticket = m1.instructPark(b1, car);
        Car fetched = m1.instructFetch(b1, ticket);

        assertSame(car, fetched);

    }
    @Test
    void test_manager_park_and_fetch(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingManager m1 = new ParkingManager(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = m1.park(car);
        Car fetched = m1.fetch(ticket);
        assertSame(car, fetched);
    }
    @Test
    void test_manager_report_error(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingManager m1 = new ParkingManager();
        m1.addParkingBoy(parkingBoy);
        ParkingTicket ticket = m1.instructPark(parkingBoy, car);
        m1.instructFetch(parkingBoy,ticket);
        m1.instructFetch(parkingBoy,ticket);

        assertEquals(
                "Unrecognized parking ticket."+System.getProperty("line.separator"),
                outContent.toString()
        );
    }
}
