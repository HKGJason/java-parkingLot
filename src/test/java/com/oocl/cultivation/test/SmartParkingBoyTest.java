package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.SmartParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SmartParkingBoyTest {
    /*
    //Story 4
    @Test
    void test_smart_park_boy_on_select_lot(){
        ParkingLot nonTargetLot = new ParkingLot(1);
        ParkingLot TargetLot = new ParkingLot(2);
        SmartParkingBoy boy = new SmartParkingBoy(nonTargetLot);
        boy.manageLot(TargetLot);
        boy.park(new Car());
        assertSame(TargetLot, boy.getCurrentParkingLot());

    }
    */
    //Story 4
    @Test
    void test_smart_boy_park_and_fetch_on_select_lot(){
        ParkingLot nonTargetLot = new ParkingLot(1);
        ParkingLot TargetLot = new ParkingLot(2);
        SmartParkingBoy boy = new SmartParkingBoy(nonTargetLot);
        boy.manageLot(TargetLot);
        Car car = new Car();
        ParkingTicket ticket = boy.park(car);
        assertSame(car, boy.fetch(ticket));

    }
}
