package com.example.spareui.information.bookingStates;

import com.example.spareui.information.Booking;

// class for active booking state
public class BookingStateActive implements IBookingState {
    private Booking booking;

    public BookingStateActive(Booking booking) {
        this.booking = booking;
    }

    public String bookingStatus(){
        return "This booking is active until: ";
    } // returns printable status

    public boolean getBookingState(){
        return true;
    } // returns boolean value
}
