package com.example.spareui.accommodations;


import java.util.ArrayList;
import java.util.List;

// singleton used to manage accommodations
public class AccommodationManagementSingleton {
    private static AccommodationManagementSingleton instance = null;
    private static List<Accommodation> accommodationList = new ArrayList<>();

    private AccommodationManagementSingleton(List<Accommodation> accommodationList){
        this.accommodationList = accommodationList;
    }

    public List<Accommodation> getAccommodationList(){
        return accommodationList;
    }

    public void addAccommodation(Accommodation accommodation){
        accommodationList.add(accommodation);
    } // add accommodation to list

    public void removeAccommodation(Accommodation accommodation){
        accommodationList.remove(accommodation);
    } // remove accommodation from list

    public void printAvailableAccommodation(){
        for (Accommodation accommodation: accommodationList){
            if(accommodation.getAccommodationState()){
                System.out.println("\nAvailable Accommodation: \n" + accommodation.printInformation());
            }
        }
    } // print accommodations based on state

    public void printUnavailableAccommodation(){
        for(Accommodation accommodation: accommodationList){
            if(!accommodation.getAccommodationState()){
                System.out.println("\nUnavailable Accommodation: \n" +accommodation.printInformation());
            }
        }
    } // print accommodations based on state

    // printing based on accommodation derrived class type

    public void printFlats(){
        for(Accommodation accommodation: accommodationList){
            if(accommodation instanceof Flat flat){
                String availability = flat.getAccommodationState() ? "Unavailable" : "Available";
                System.out.println(flat.printInformation() + "\nAvailability Status: " + availability + "\n");
            }
        }
    }

    public void printHotels(){
        for(Accommodation accommodation: accommodationList){
            if(accommodation instanceof Hotel hotel){
                String availability = hotel.getAccommodationState() ? "Unavailable" : "Available";
                System.out.println(hotel.printInformation() + "\nAvailability Status: " + availability + "\n");
            }
        }
    }

    public void printVillas(){
        for(Accommodation accommodation: accommodationList){
            if(accommodation instanceof Villa villa){
                String availability = villa.getAccommodationState() ? "Unavailable" : "Available";
                System.out.println(villa.printInformation() + "\nAvailability Status: " + availability + "\n");
            }
        }
    }

    public static AccommodationManagementSingleton getInstance(){
        if (instance == null){
            instance = new AccommodationManagementSingleton(accommodationList);
            System.out.println("Singleton created");
        }
        return instance;
    } // get instance of singleton
}
