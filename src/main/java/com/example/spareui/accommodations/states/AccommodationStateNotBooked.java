package com.example.spareui.accommodations.states;

import com.example.spareui.accommodations.Accommodation;

// class for accommodation state booked
public class AccommodationStateNotBooked implements IAccommodationState {
    private Accommodation accommodation;

    public AccommodationStateNotBooked(Accommodation accommodation){
        this.accommodation = accommodation;
    }

    public String accommodationStatus(){ // returned printable status
        return("\nThis accommodation is available\n");
    }

    public boolean getAccommodationState(){ // returns boolean status
        return false;
    }

}
