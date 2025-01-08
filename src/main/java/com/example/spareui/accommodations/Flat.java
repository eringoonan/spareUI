package com.example.spareui.accommodations;

public class Flat extends Accommodation {
    private boolean balcony;
    private boolean parking;
    private boolean petsAllowed;

    // constructor
    public Flat(String name, int ID, int rooms, double price, Address address, Facilities facilities, boolean balcony, boolean parking, boolean petsAllowed) {
        super(name, ID, rooms, price, address, null);
        this.balcony = balcony;
        this.parking = parking;
        this.petsAllowed = petsAllowed;

        // sets the facilities available to the flat
        Facilities flatFacilities = new Facilities(true, true, true, true, false);
        this.setFacilities(flatFacilities);
    }

    // getters
    public boolean isBalcony() {
        return balcony;
    }

    public boolean isParking() {
        return parking;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    // setters
    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    // methods
    public String printFlatExtras(){
        return "Balcony: " + balcony + "\n"
                + "Parking: " + parking + "\n"
                + "Pets Allowed: " + petsAllowed + "\n";
    }

    @Override
    public String printInformation(){
        return super.printInformation() + "\n"
                + printFlatExtras();
    }

}
