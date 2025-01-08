package com.example.spareui.writers;

import com.example.spareui.information.Booking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// writes bookings to csv file
public class BookingWriter {
    public static void bookingWriter(Booking newBooking, String csvFile){

        String[] newData = {String.valueOf(newBooking.getBookingID()), String.valueOf(newBooking.getPropertyID()), String.valueOf(newBooking.getStartDate()), String.valueOf(newBooking.getEndDate()), String.valueOf(newBooking.getAccountID())};

        try(FileWriter fw = new FileWriter(csvFile, true); // appends file
        PrintWriter writer = new PrintWriter(fw)){
            writer.println(String.join(",", newData));
            System.out.println("New booking successfully written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
