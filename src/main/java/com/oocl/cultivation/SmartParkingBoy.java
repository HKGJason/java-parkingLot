package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot lot){
        super(lot);
    }
    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        List<ParkingLot> lots = super.getParkingLot();
        ParkingLot lot = findMostEmpty(lots);
        super.setCurrentParkingLot(lot);
        if (lot!=null){
            lot.parkCar(car, ticket);
            super.setLastErrorMessage(null);
            return ticket;
        }else{
            return null;
        }

    }

    private ParkingLot findMostEmpty(List<ParkingLot> lots) {
        ParkingLot lot = lots.get(0);
        for (ParkingLot p: lots){
            if (p.getEmptyLotCount()>0 && p.getEmptyLotCount()>lot.getEmptyLotCount()){
                lot = p;
            }
        }
        return lot;
    }


}
