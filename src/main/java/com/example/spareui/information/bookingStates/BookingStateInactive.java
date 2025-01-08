package com.example.spareui.information.bookingStates;

import com.example.spareui.information.Booking;

// class for active booking state
public class BookingStateInactive implements IBookingState {
    private Booking booking;

    public BookingStateInactive(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        this.booking = booking;
    }

    @Override
    public String bookingStatus() {
        return "This booking is inactive";
    } // returns printable status

    @Override
    public boolean getBookingState() {
        return false;
    } // returns boolean value
}

