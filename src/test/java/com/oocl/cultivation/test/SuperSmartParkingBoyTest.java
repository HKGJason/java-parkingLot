package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperSmartParkingBoyTest {
    /*
    //Story 5
    @Test
    void test_super_park_boy_on_select_lot(){
        ParkingLot nonTargetLot = new ParkingLot(5);
        ParkingLot TargetLot = new ParkingLot(10);
        SuperSmartParkingBoy boy = new SuperSmartParkingBoy(nonTargetLot);
        boy.manageLot(TargetLot);
        boy.park(new Car());
        boy.park(new Car());
        boy.park(new Car());
        assertSame(TargetLot, boy.getCurrentParkingLot());
    }
*/
    //Story 5
    @Test
    void test_super_park_boy_park_and_fetch_on_select_lot(){
        ParkingLot nonTargetLot = new ParkingLot(5);
        ParkingLot TargetLot = new ParkingLot(10);
        SuperSmartParkingBoy boy = new SuperSmartParkingBoy(nonTargetLot);
        boy.manageLot(TargetLot);
        boy.park(new Car());
        boy.park(new Car());
        Car car = new Car();
        ParkingTicket ticket = boy.park(car);
        assertSame(car, boy.fetch(ticket));
    }
}
