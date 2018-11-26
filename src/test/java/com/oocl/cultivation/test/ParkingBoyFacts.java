package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    //Story1 AC1
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);
        assertSame(car, fetched);

    }
    //Story 1 AC2
    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }
    // Story 1 AC3
    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(ticket));
    }
    //Story 2 AC1
    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        assertNotNull(parkingBoy.getLastErrorMessage());

        ParkingTicket ticket = parkingBoy.park(new Car());
        assertNotNull(ticket);
        assertNull(parkingBoy.getLastErrorMessage());
    }
    // Story 2 AC2
    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(null));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.fetch(null);

        assertEquals(
            "Please provide your parking ticket.",
            parkingBoy.getLastErrorMessage());
    }
    // Story 1 AC4
    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertNull(parkingBoy.fetch(ticket));
    }
    // Story 2 AC1
    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);

        assertEquals(
            "Unrecognized parking ticket.",
            parkingBoy.getLastErrorMessage()
        );
    }
    // Story 1 AC5
    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(new Car());

        assertNull(parkingBoy.park(new Car()));
    }
    //Story 2 AC3
    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    //Story 3 AC1
    @Test
    void test_park_next_lot_when_init_lot_full(){
        final int capacity = 1;
        ParkingLot parkingLot1 = new ParkingLot(capacity);
        ParkingLot parkingLot2 = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1);
        parkingBoy.manageLot(parkingLot2);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        assertSame(parkingLot2, parkingBoy.getCurrentParkingLot());

    }
    //Story 4
    @Test
    void test_smart_park_boy_on_select_lot(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        SmartParkingBoy boy = new SmartParkingBoy(parkingLot1);
        boy.manageLot(parkingLot2);
        boy.park(new Car());
        assertSame(parkingLot2, boy.getCurrentParkingLot());

    }
    //Story 5
    @Test
    void test_super_park_boy_on_select_lot(){
        ParkingLot p1 = new ParkingLot(5);
        ParkingLot p2 = new ParkingLot(10);
        SuperSmartParkingBoy boy = new SuperSmartParkingBoy(p1);
        boy.manageLot(p2);
        boy.park(new Car());
        boy.park(new Car());
        boy.park(new Car());
        assertSame(p2, boy.getCurrentParkingLot());
    }
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
