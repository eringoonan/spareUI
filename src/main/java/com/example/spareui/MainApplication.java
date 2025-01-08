package com.example.spareui;

import com.example.spareui.accommodations.Accommodation;
import com.example.spareui.accommodations.AccommodationManagementSingleton;
import com.example.spareui.accommodations.Address;
import com.example.spareui.accommodations.states.AccommodationStateBooked;
import com.example.spareui.information.Account;
import com.example.spareui.information.Booking;
import com.example.spareui.readers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// main application
public class MainApplication extends Application {
    private Account account;
    private List<Account> accountList;
    private Stage primaryStage;
    private AddressData addressData;
    private List<Address> addressList;
    private Map<Integer, Address> addressMap;
    private AccommodationManagementSingleton accommodationManagementSingleton;
    List<Booking> bookingList;

    // setters
    public void setAccounts(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    // start function to show primary stage
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("SpareBB");
        accountList = AccountReader.accountReader(); // creates list of account objects from csv file
        addressData = AddressReader.addressReader(); // creates address data object using read from file
        addressList = addressData.getAddressList(); // list of address objects is created
        addressMap = addressData.getAddressMap(); // map of address objects is created for assigning to accommodation
        accommodationManagementSingleton = AccommodationManagementSingleton.getInstance(); // creates instance of accommodation management singleton
        AccommodationReader.accommodationReader(addressMap, accommodationManagementSingleton); // reads the accommodations into the list within the singleton

        bookingList = BookingReader.bookingReader("./resources/bookings.csv"); // creates list of bookings from csv file

        // verifies state of accommodations using bookings
        for(Accommodation accommodation: accommodationManagementSingleton.getAccommodationList()){
            for(Booking booking: bookingList){
                if (accommodation.getID() == booking.getPropertyID() && booking.getBookingState()){ // if accommodation found in an active booking the accommodation state set to booked
                    accommodation.setAccommodationState(new AccommodationStateBooked(accommodation));
                }
            }
        }

        showLoginScene(); // show login page
        primaryStage.show();
    }

    // function that shows login page
    public void showLoginScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(loader.load());

        LoginController controller = loader.getController();
        controller.setAccounts(accountList); // set accounts
        controller.setMainApplication(this); // set main app

        primaryStage.setScene(scene); // show
    }

    // function that shows account create page
    public void showAccountCreateScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("accountCreation-view.fxml"));
        Scene scene = new Scene(loader.load());

        AccountCreateController controller = loader.getController();
        controller.setAccounts(accountList); // set accounts
        controller.setMainApplication(this); // set main app

        primaryStage.setScene(scene); // show
    }

    // function that shows menu page
    public void showMenuScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Scene scene = new Scene(loader.load());

        MenuController controller = loader.getController();
        controller.setMainApplication(this); // set main app
        controller.setAccommodationManagementSingleton(accommodationManagementSingleton); // set singleton
        controller.setBookingList(bookingList); // set bookings
        controller.updateScene(); // dynamic scene update function called on page showing

        primaryStage.setScene(scene); // show
    }

    // function that shows property page
    public void showPropertyScene(int propertyID) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("property-view.fxml"));
        Scene scene = new Scene(loader.load());

        PropertyController controller = loader.getController();
        controller.setMainApplication(this); // set main app
        controller.setAccommodationManagementSingleton(accommodationManagementSingleton); // set singleton
        controller.setBookingList(bookingList); // set bookings
        controller.updateScene(propertyID); // dynamic scene update called on page showing

        primaryStage.setScene(scene);
    }

    // function that shows category scene
    public void showCategoryScene(int accommodationType) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("category-view.fxml"));
        Scene scene = new Scene(loader.load());

        CategoryController controller = loader.getController();
        controller.setMainApplication(this); // set main app
        controller.setAccommodationManagementSingleton(accommodationManagementSingleton); // set singleton
        controller.setBookingList(bookingList); // set bookings
        controller.setAccount(account); // set account
        controller.updateScene(accommodationType); // dynamically update scene on page showing

        primaryStage.setScene(scene);
    }

    // function that shows booking scene
    public void showBookingScene(int propertyID) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking-view.fxml"));
        Scene scene = new Scene(loader.load());

        BookingController controller = loader.getController();
        controller.setMainApplication(this); // set main app
        controller.setAccommodationManagementSingleton(accommodationManagementSingleton); // set singleton
        controller.setBookingList(bookingList); // set bookings
        controller.updateScene(propertyID); // dynamically update scene on page showing
        controller.setAccount(account); // set account
        primaryStage.setScene(scene);
    }

    // function that shows profile scene
    public void showProfileScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile-view.fxml"));
        Scene scene = new Scene(loader.load());

        ProfileController controller = loader.getController();
        controller.setMainApplication(this); // set the main application
        controller.updateScene(account); // update scene based on user account details

        primaryStage.setScene(scene);
    }

    // launch main application
    public static void main(String[] args) {
        launch();
    }

}