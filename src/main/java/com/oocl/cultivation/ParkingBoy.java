package com.oocl.cultivation;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public void setLastErrorMessage(String msg){
        this.lastErrorMessage = msg;
    }
    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        if (parkingLot.getEmptyLotCount()<=0){
            setLastErrorMessage("The parking lot is full.");
            return null;
        }else {
            this.parkingLot.parkCar(car, ticket);
            setLastErrorMessage(null);
            return ticket;
        }

    }

    public Car fetch(ParkingTicket ticket) {
        if (ticket==null){
            setLastErrorMessage("Please provide your parking ticket.");
            return null;
        }
        Car car = this.parkingLot.fetch(ticket);
        if (car==null) {
            setLastErrorMessage("Unrecognized parking ticket.");
            return null;
        }
        setLastErrorMessage(null);
        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
