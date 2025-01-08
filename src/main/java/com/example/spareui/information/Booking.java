package com.example.spareui.information;

import com.example.spareui.information.bookingStates.BookingStateActive;
import com.example.spareui.information.bookingStates.BookingStateInactive;
import com.example.spareui.information.bookingStates.IBookingState;

import java.time.LocalDate;

// class for booking
public class Booking {
    private int bookingID;
    private int propertyID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int accountID;

    protected IBookingState bookingState;

    // constructor
    public Booking(int bookingID, int propertyID, LocalDate startDate, LocalDate endDate, int accountID) {
        this.bookingID = bookingID;
        this.propertyID = propertyID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accountID = accountID;
        this.bookingState = new BookingStateActive(this);
        verifyBookingState(bookingState); // verifies booking state as object is initialised
    }

    // getters
    public int getBookingID() {
        return bookingID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getAccountID() {
        return accountID;
    }

    public boolean getBookingState(){
        return this.bookingState.getBookingState();
    }

    public String getBookingStatus(){
        return this.bookingState.bookingStatus();
    }

    // methods
    public void verifyBookingState(IBookingState bookingState){
        if(getEndDate().isBefore(LocalDate.now())){ // checks if the date is in the past and adjusts booking state
            this.bookingState = new BookingStateInactive(this);
            System.out.println("Booking is now inactive");
        }
    }

    public void setBookingState(IBookingState bookingState){
        this.bookingState = bookingState;
    } // sets booking state

    public String printBooking(){
        return "Booking ID: " + bookingID +
                "\nProperty ID: " + propertyID +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate;
    }
}
