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
        double emptyRateCurrent;
        double emptyRateBefore;
        for (ParkingLot p: lots){
            emptyRateBefore = lot.getEmptyLotCount() / lot.getCapacity();
            emptyRateCurrent = p.getEmptyLotCount() / p.getCapacity();
            if (p.getEmptyLotCount()>0 && emptyRateCurrent>emptyRateBefore){
                lot = p;
            }
        }
        return lot;
    }

}
