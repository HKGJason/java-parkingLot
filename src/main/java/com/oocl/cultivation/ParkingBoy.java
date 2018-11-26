package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private ParkingLot currentParkingLot;
    private String lastErrorMessage;
    private List<ParkingLot> parkingLot = new ArrayList<>();
    public ParkingBoy(){}
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot.add(parkingLot);
    }
    public void setLastErrorMessage(String msg){
        this.lastErrorMessage = msg;
    }
    List<ParkingLot> getParkingLot(){
        return this.parkingLot;
    }
    public void setCurrentParkingLot(ParkingLot lot){
        this.currentParkingLot = lot;
    }
    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        this.currentParkingLot = checkEmptyLot();
        if (this.currentParkingLot!=null){
            this.currentParkingLot.parkCar(car, ticket);
            setLastErrorMessage(null);
            return ticket;
        }else{
            return null;
        }

    }

    private ParkingLot checkEmptyLot() {
        for (ParkingLot lot: parkingLot){
            if (lot.getEmptyLotCount()>0)
                return lot;
        }
        setLastErrorMessage("The parking lot is full.");
        return null;
    }

    public Car fetch(ParkingTicket ticket) {
        if (ticket==null){
            setLastErrorMessage("Please provide your parking ticket.");
            return null;
        }
        Car car;
        for (ParkingLot lot : parkingLot) {
            car = lot.fetch(ticket);
            if (car!=null)
            {
                setLastErrorMessage(null);
                return car;
            }
        }
        setLastErrorMessage("Unrecognized parking ticket.");
        return null;

    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void manageLot(ParkingLot parkingLot2) {
        this.parkingLot.add(parkingLot2);
    }
}
