package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager{
    private List<ParkingBoy> allBoy = new ArrayList<>();
    private ParkingBoy actParkingBoy;
    public ParkingManager(ParkingLot lot){
        this.actParkingBoy = new ParkingBoy(this);
        actParkingBoy.manageLot(lot);
    }
    public ParkingManager(){}

    public ParkingTicket park(Car car){
        ParkingTicket ticket = this.actParkingBoy.park(car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket){
        Car car = this.actParkingBoy.fetch(ticket);
        return car;
    }

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
    public void manageLot(ParkingLot lot){
        this.actParkingBoy.manageLot(lot);
    }
}
