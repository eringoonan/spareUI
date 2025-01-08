package com.example.spareui.readers;

import com.example.spareui.accommodations.*;
import com.example.spareui.accommodations.states.AccommodationStateNotBooked;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

// static method class used to read accommodation object instances from a csv file
public class AccommodationReader {
    public static void accommodationReader(Map<Integer, Address> addressMap, AccommodationManagementSingleton accommodationManagement) { // returns accommodation list, takes a map as parameter
        String csvFile = "./resources/properties.csv";
        String line = "";
        String separator = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) { // uses buffered reader to read lines, separated by ,
            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);

                String type = data[0];
                String name = data[1];
                int id = Integer.parseInt(data[2]);
                int beds = Integer.parseInt(data[3]);
                double price = Double.parseDouble(data[4]);
                Address address = addressMap.get(id); // assigns addresses based on matching ID
                Accommodation accommodation = null;

                if (Objects.equals(type, "Flat")) { // based on type assigns additional unique variables
                    boolean balcony = Boolean.parseBoolean(data[5]);
                    boolean parking = Boolean.parseBoolean(data[6]);
                    boolean petsAllowed = Boolean.parseBoolean(data[7]);
                    accommodation = new Flat(name, id, beds, price, address, null, balcony, parking, petsAllowed);

                } else if (Objects.equals(type, "Hotel")) { // based on type assigns additional unique variables
                    int starRating = Integer.parseInt(data[5]);
                    int totalRoomNumber = Integer.parseInt(data[6]);
                    boolean roomService = Boolean.parseBoolean(data[7]);
                    accommodation = new Hotel(name, id, beds, price, address, null, starRating, totalRoomNumber, roomService);

                } else if (Objects.equals(type, "Villa")) { // based on type assigns additional unique variables
                    int bedsNumber = Integer.parseInt(data[5]);
                    boolean hasGarden = Boolean.parseBoolean(data[6]);
                    boolean beachNearby = Boolean.parseBoolean(data[7]);
                    accommodation = new Villa(name, id, beds, price, address, null, bedsNumber, hasGarden, beachNearby);
                }

                if (accommodation != null) {
                    accommodation.setAccommodationState(new AccommodationStateNotBooked(accommodation));
                    accommodationManagement.addAccommodation(accommodation); // adds to list within the singleton
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully read properties file");
    }
}
