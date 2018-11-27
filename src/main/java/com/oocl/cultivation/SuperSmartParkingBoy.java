package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> lots = new ArrayList<>();
    public SuperSmartParkingBoy(ParkingLot lot){
        super(lot);
    }
    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        ParkingLot lot = setParkingLot();
        super.setCurrentParkingLot(lot);
        if (lot!=null){
            lot.parkCar(car, ticket);
            super.setLastErrorMessage(null);
            return ticket;
        }else{
            return null;
        }

    }
    private ParkingLot setParkingLot(){
        this.lots = super.getParkingLot();
        ParkingLot lot = findMostEmptyPercentage(this.lots);
        super.setCurrentParkingLot(lot);
        return findMostEmptyPercentage(this.lots);

    }

    private ParkingLot findMostEmptyPercentage(List<ParkingLot> lots) {
        ParkingLot lot = lots.get(0);
        float emptyRateCurrent, emptyRateBefore;
        for (ParkingLot p: lots){
            emptyRateBefore = getEmptyPercentage(lot);
            emptyRateCurrent = getEmptyPercentage(p);
            if (p.getEmptyLotCount()>0 && emptyRateCurrent>emptyRateBefore){
                lot = p;
            }
        }
        return lot;
    }
    private float getEmptyPercentage(ParkingLot parkinglot){
        return (parkinglot.getEmptyLotCount()*100.0f/parkinglot.getCapacity()*100.0f);
    }

}
