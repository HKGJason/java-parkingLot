package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot lot){
        super(lot);
    }
    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        List<ParkingLot> lots = super.getParkingLot();
        ParkingLot lot = findMostEmptyPercentage(lots);
        super.setCurrentParkingLot(lot);
        if (lot!=null){
            lot.parkCar(car, ticket);
            super.setLastErrorMessage(null);
            return ticket;
        }else{
            return null;
        }

    }

    private ParkingLot findMostEmptyPercentage(List<ParkingLot> lots) {
        ParkingLot lot = lots.get(0);
        float emptyRateCurrent;
        float emptyRateBefore;
        for (ParkingLot p: lots){
            emptyRateBefore = (lot.getEmptyLotCount()*100.0f) / (lot.getCapacity()*100.0f);
            emptyRateCurrent = (p.getEmptyLotCount()*100.0f) / (p.getCapacity()*100.0f);
            if (p.getEmptyLotCount()>0 && emptyRateCurrent>emptyRateBefore){
                lot = p;
            }
        }
        return lot;
    }

}
