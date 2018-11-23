package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        if (parkingLot.getAvailableParkingPosition()<=0){
            return null;
        }else {
            this.parkingLot.parkCar(car, ticket);
        }
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        if (ticket.equals(null)){
            this.lastErrorMessage = "Please provide your parking ticket.";
            return null;
        }
        Car car = this.parkingLot.fetch(ticket);
        if (car.equals(null)) {
            this.lastErrorMessage = "Unrecognized parking ticket.";
            return null;
        }
        this.lastErrorMessage = null;
        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
