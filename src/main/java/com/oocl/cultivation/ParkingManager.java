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

    public ParkingTicket instructPark(ParkingBoy parkingboy, Car car) {
        if (this.allBoy.contains(parkingboy)) {
            ParkingTicket ticket = parkingboy.park(car);
            if (parkingboy.getLastErrorMessage()!=null)
                System.out.println(parkingboy.getLastErrorMessage());
            return ticket;
        }
        return null;
    }

    public Car instructFetch(ParkingBoy parkingBoy, ParkingTicket ticket) {
        if (this.allBoy.contains(parkingBoy)) {
            Car car = parkingBoy.fetch(ticket);
            if (parkingBoy.getLastErrorMessage() != null)
                System.out.println(parkingBoy.getLastErrorMessage());
            return car;
        }
        return null;
    }
    public void manageLot(ParkingLot lot){
        this.actParkingBoy.manageLot(lot);
    }
}
