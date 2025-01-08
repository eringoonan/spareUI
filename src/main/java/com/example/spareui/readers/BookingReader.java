package com.example.spareui.readers;

import com.example.spareui.information.Booking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// reads bookings from csv file
public class BookingReader {
    public static List<Booking> bookingReader(String bookingFile) {
        String line;
        String separator = ",";
        List<Booking> bookings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(bookingFile))) {
            boolean isFileEmpty = true; // Track if the file has data

            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                isFileEmpty = false; // File has data if we reach here
                String[] bookingData = line.split(separator);

                // Ensure there are enough fields in bookingData to avoid array out of bounds exception
                if (bookingData.length >= 5) {
                    int bookingID = Integer.parseInt(bookingData[0]);
                    int propertyID = Integer.parseInt(bookingData[1]);
                    LocalDate startDate = LocalDate.parse(bookingData[2]);
                    LocalDate endDate = LocalDate.parse(bookingData[3]);
                    int accountID = Integer.parseInt(bookingData[4]);

                    bookings.add(new Booking(bookingID, propertyID, startDate, endDate, accountID));
                }
            }

            if (isFileEmpty) {
                System.out.println("The file is empty. Returning an empty list.");
            } else {
                System.out.println("Successfully read bookings file");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bookings;
    }
}
