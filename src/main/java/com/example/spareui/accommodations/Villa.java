package com.example.spareui.accommodations;

public class Villa extends Accommodation {
    private int bedsNumber;
    private boolean garden;
    private boolean beachNearby;

    // constructor
    public Villa(String name, int ID, int rooms, double price, Address address, Facilities facilities, int bedsNumber, boolean garden, boolean beachNearby) {
        super(name, ID, rooms, price, address, null);
        this.bedsNumber = bedsNumber;
        this.garden = garden;
        this.beachNearby = beachNearby;

        // sets the facilities available to the villa
        Facilities villaFacilities = new Facilities(true, true, true, true, true);
        setFacilities(villaFacilities);
    }

    // getters
    public int getBedsNumber() {
        return bedsNumber;
    }

    public boolean getGarden() {
        return garden;
    }

    public boolean getBeachNearby() {
        return beachNearby;
    }

    // setters
    public void setBedsNumber(int bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public void setBeachNearby(boolean beachNearby) {
        this.beachNearby = beachNearby;
    }

    // methods
    public String printVillaExtras(){
        return "Number of beds:" + bedsNumber + "\n"
                + "Garden: " + garden + "\n"
                + "Beach Nearby: " + beachNearby;
    }

    @Override
    public String printInformation() {
        return super.printInformation() + "\n"
                + printVillaExtras();
    }
}

