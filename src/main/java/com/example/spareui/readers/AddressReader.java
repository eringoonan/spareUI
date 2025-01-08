package com.example.spareui.readers;


import com.example.spareui.accommodations.Address;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressReader {
    public static AddressData addressReader(){ // returns addressData type object (list of address objects and an address map)
        String csvFile = "./resources/addresses.csv";
        String line = "";
        String separator = ",";
        List<Address> addressList = new ArrayList<>();
        Map<Integer, Address> addressMap = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){ // uses buffered reader to read lines, separated by ,
            while ((line = br.readLine()) != null){
                String[] data = line.split(separator);
                int number = Integer.parseInt(data[0]);
                String street = data[1];
                String city = data[2];
                String county = data[3];
                String postCode = data[4];
                int id = Integer.parseInt(data[5]);

                Address address = new Address(number, street, city, county, postCode, id);
                addressMap.put(id, address);
                addressList.add(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully read address file");
        return new AddressData(addressList, addressMap); // returns address data object
    }
}
