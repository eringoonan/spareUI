package com.example.spareui.accommodations.states;

import com.example.spareui.accommodations.Accommodation;

// class for accommodation state booked
public class AccommodationStateBooked implements IAccommodationState {
    private Accommodation accommodation;

    public AccommodationStateBooked(Accommodation accommodation){
        this.accommodation = accommodation;
    }

    public String accommodationStatus(){ // returned printable status
        return("\nThis accommodation: " + accommodation.getName() + " " + "is booked and unavailable until: ");
    }

    public boolean getAccommodationState(){ // returns boolean status
        return true;
    }
}
