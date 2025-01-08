package com.example.spareui.accommodations;

// class for facilities object stored in each instance of accommodation
public class Facilities {
    boolean washingMachine;
    boolean stove;
    boolean oven;
    boolean fridge;
    boolean pool;

    // constructor
    public Facilities(boolean washingMachine, boolean stove, boolean oven, boolean fridge, boolean pool) {
        this.washingMachine = washingMachine;
        this.stove = stove;
        this.oven = oven;
        this.fridge = fridge;
        this.pool = pool;
    }

    // getters
    public boolean getWashingMachine(){
        return washingMachine;
    }

    public boolean getStove(){
        return stove;
    }

    public boolean getOven(){
        return oven;
    }

    public boolean getFridge(){
        return fridge;
    }

    public boolean getPool(){
        return pool;
    }

    // methods
    public String printFacilities() { // function used to check available facilities of a property
        return "\nWashing machine: " + washingMachine + "\n"
                + "Stove: " + stove + "\n"
                + "Oven: " + oven + "\n"
                + "Fridge: " + fridge + "\n"
                + "Pool: " + pool;
    }
}
