package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingBoy {
    private List<ParkingBoy> allBoy = new ArrayList<>();
    public ParkingManager(ParkingLot lot){
        super(lot);
    }
    public ParkingManager(){}


    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.allBoy.add(parkingBoy);
    }

    public ParkingTicket instructPark(ParkingBoy b1, Car car) {
        if (this.allBoy.contains(b1)) {
            ParkingTicket ticket = b1.park(car);
            if (b1.getLastErrorMessage()!=null)
                System.out.println(b1.getLastErrorMessage());
            return ticket;
        }
        return null;
    }

    public Car instructFetch(ParkingBoy b1, ParkingTicket ticket) {
        if (this.allBoy.contains(b1)) {
            Car car = b1.fetch(ticket);
            if (b1.getLastErrorMessage() != null)
                System.out.println(b1.getLastErrorMessage());
            return car;
        }
        return null;
    }
}
