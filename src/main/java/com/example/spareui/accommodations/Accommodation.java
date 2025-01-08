package com.example.spareui.accommodations;

import com.example.spareui.accommodations.states.AccommodationStateNotBooked;
import com.example.spareui.accommodations.states.IAccommodationState;

public abstract class Accommodation {
    private String name;
    private int ID;
    private int rooms;
    private double price;
    private Address address;
    private Facilities facilities;

    protected IAccommodationState accommodationState;

    // constructor function
    public Accommodation(String name, int ID, int rooms, double price, Address address, Facilities facilities) {
        this.name = name;
        this.ID = ID;
        this.rooms = rooms;
        this.price = price;
        this.facilities = facilities;
        this.address = address;
        this.accommodationState = new AccommodationStateNotBooked(this);
    }

    // getters
    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public Address getAddress() {
        return address;
    }

    public int getRooms() {
        return rooms;
    }

    public double getPrice() {
        return price;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    public boolean getAccommodationState(){ // returns boolean used for comparison
        return this.accommodationState.getAccommodationState();
    }

    public String getAccommodationStatus(){ // prints status of accommodation
        return this.accommodationState.accommodationStatus();
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setFacilities(Facilities facilities) {
        this.facilities = facilities;
    }

    public void setAccommodationState(IAccommodationState accommodationState){ // sets accommodation state
        this.accommodationState = accommodationState;
    }


    // methods
    public String printInformation(){ // override function for printing information about an accomm
        return "Accommodation: " + getName() + "\n"
                + "City: " + getAddress().printCity() + "\n"
                + "Facilities: " + getFacilities().printFacilities() + "\n"
                + "Price: " + getPrice() + "\n"
                + "Rooms: " + getRooms();
    }

}
