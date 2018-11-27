package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> lots = new ArrayList<>();
    public SmartParkingBoy(ParkingLot lot){
        super(lot);
    }
    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        ParkingLot lot = setParkingLot();
        if (lot!=null){
            lot.parkCar(car, ticket);
            super.setLastErrorMessage(null);
            return ticket;
        }
        return null;
    }

    private ParkingLot setParkingLot() {
        this.lots = super.getParkingLot();
        ParkingLot lot = findMostEmpty();
        super.setCurrentParkingLot(lot);
        return lot;
    }

    private ParkingLot findMostEmpty() {
        ParkingLot lot = this.lots.get(0);
        for (ParkingLot p: this.lots){
            if (p.getEmptyLotCount()>0 && p.getEmptyLotCount()>lot.getEmptyLotCount()){
                lot = p;
            }
        }
        return lot;
    }


}
