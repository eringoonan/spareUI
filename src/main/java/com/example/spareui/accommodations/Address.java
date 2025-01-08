package com.example.spareui.accommodations;

// class for address object stored within each accommodation instance
public class Address {
    private int number;
    private String street;
    private String city;
    private String county;
    private String postCode;
    private int id;

    public Address(int number, String street, String city, String county, String postCode, int id) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.county = county;
        this.postCode = postCode;
        this.id = id;
    }

    // getters

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getPostCode() {
        return postCode;
    }

    public int getId() {
        return id;
    }

    public String printCity() { // prints the city of the accommodation
        return city;
    }

    public String printAddress() { // prints street address, used after booking is complete
        return "Street: " + number + street + "\n"
                + "City: " + city + "\n"
                + "County: " + county + "\n"
                + "Post Code: " + postCode;
    }

}
