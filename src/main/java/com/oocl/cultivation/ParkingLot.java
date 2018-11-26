package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }
    public void parkCar(Car car, ParkingTicket ticket){
        cars.put(ticket, car);
    }
    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Car fetch(ParkingTicket ticket){
        Car car = cars.get(ticket);
        if (car!=null) {
            cars.remove(ticket);
            return car;
        }
        return null;
    }
    int getEmptyLotCount(){
        return capacity - cars.size();
    }
    int getCapacity(){
        return capacity;
    }

}
