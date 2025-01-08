package com.example.spareui.accommodations;

public class Hotel extends Accommodation {
    private int starRating;
    private int totalRoomNumber;
    private boolean roomService;

    // constructor
    public Hotel(String name, int ID, int rooms, double price, Address address, Facilities facilities, int starRating, int totalRoomNumber, boolean roomService) {
        super(name, ID, rooms, price, address, null);
        this.starRating = starRating;
        this.totalRoomNumber = totalRoomNumber;
        this.roomService = roomService;

        // sets the facilities available to the hotel
        Facilities hotelFacilities = new Facilities(false, false, false, true, true);
        setFacilities(hotelFacilities);
    }

    //getters
    public int getStarRating() {
        return starRating;
    }

    public int getTotalRoomNumber() {
        return totalRoomNumber;
    }

    public boolean isRoomService() {
        return roomService;
    }

    // setters
    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public void setTotalRoomNumber(int totalRoomNumber) {
        this.totalRoomNumber = totalRoomNumber;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    // methods
    public String printHotelExtras(){
        return "Star Rating:" + starRating + "\n"
                + "Total Room Number:" + totalRoomNumber + "\n"
                + "Room Service:" + roomService;
    }

    @Override
    public String printInformation() {
        return super.printInformation() + "\n"
                + printHotelExtras();
    }
}
