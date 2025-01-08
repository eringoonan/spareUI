package com.example.spareui;
import com.example.spareui.accommodations.*;
import com.example.spareui.information.Account;
import com.example.spareui.information.Booking;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.ColorAdjust;


import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// class to control the category fxml file
public class CategoryController {
    private MainApplication mainApplication;
    private AccommodationManagementSingleton accommodationManagementSingleton;
    private List<Booking> bookingList;
    private Account account;
    private ContextMenu contextMenu;
    private ContextMenu bookingContextMenu;
    @FXML
    Button dropdownButton;
    @FXML
    Button bookingButton;
    @FXML
    ImageView image1;
    @FXML
    ImageView image2;
    @FXML
    ImageView image3;
    @FXML
    ImageView image4;
    @FXML
    ImageView image5;
    @FXML
    ImageView image6;
    @FXML
    Label title1;
    @FXML
    Label title2;
    @FXML
    Label title3;
    @FXML
    Label title4;
    @FXML
    Label title5;
    @FXML
    Label title6;
    @FXML
    Label location1;
    @FXML
    Label location2;
    @FXML
    Label location3;
    @FXML
    Label location4;
    @FXML
    Label location5;
    @FXML
    Label location6;
    @FXML
    Label cost1;
    @FXML
    Label cost2;
    @FXML
    Label cost3;
    @FXML
    Label cost4;
    @FXML
    Label cost5;
    @FXML
    Label cost6;
    @FXML
    Label categoryTitleLabel;

    // lists of dynamic panel elements
    private List<ImageView> propertyIcons = new ArrayList<>();
    private List<Label> titles = new ArrayList<>();
    private List<Label> locations = new ArrayList<>();
    private List<Label> costs = new ArrayList<>();
    ColorAdjust grayscale = new ColorAdjust();

    public void initialize() {
        // adding elements to respective lists
        propertyIcons.add(image1);
        propertyIcons.add(image2);
        propertyIcons.add(image3);
        propertyIcons.add(image4);
        propertyIcons.add(image5);
        propertyIcons.add(image6);

        titles.add(title1);
        titles.add(title2);
        titles.add(title3);
        titles.add(title4);
        titles.add(title5);
        titles.add(title6);

        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);
        locations.add(location5);
        locations.add(location6);

        costs.add(cost1);
        costs.add(cost2);
        costs.add(cost3);
        costs.add(cost4);
        costs.add(cost5);
        costs.add(cost6);

        // initialise grayscale
        grayscale.setSaturation(-1);

        // account context menu
        contextMenu = new ContextMenu();
        MenuItem profileItem = new MenuItem("Profile");
        MenuItem bookingItem = new MenuItem("Bookings");
        contextMenu.getItems().addAll(profileItem, bookingItem);

        profileItem.setOnAction(event -> {
            try {
                mainApplication.showProfileScene(); // show profile scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        bookingItem.setOnAction(event -> {
            try {
                mainApplication.showCategoryScene(4); // show category scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // show context menu on account button click
        dropdownButton.setOnAction(e -> {
            if (!contextMenu.isShowing()) {
                // set MenuItem widths dynamically to match the button width
                double buttonWidth = dropdownButton.getWidth();

                contextMenu.getItems().forEach(item -> {
                    item.setStyle("-fx-pref-width: " + buttonWidth + "px;");
                });

                // show the ContextMenu directly below the button
                double x = dropdownButton.localToScreen(0, 0).getX();
                double y = dropdownButton.localToScreen(0, 0).getY() + dropdownButton.getHeight();

                contextMenu.show(dropdownButton, x, y);
            } else {
                contextMenu.hide();
            }
        });

        // booking context menu
        bookingContextMenu = new ContextMenu();
        MenuItem flatsItem = new MenuItem("Flats");
        MenuItem hotelsItem = new MenuItem("Hotels");
        MenuItem villasItem = new MenuItem("Villas");
        bookingContextMenu.getItems().addAll(flatsItem, hotelsItem, villasItem);

        flatsItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(1); // show flats in category scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        hotelsItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(2); // show hotels in category scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        villasItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(3); // show villas in category scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        // show context menu on button click
        bookingButton.setOnAction(e -> {
            if (!bookingContextMenu.isShowing()) {
                // set MenuItem widths dynamically to match the button width
                double buttonWidth = bookingButton.getWidth();

                bookingContextMenu.getItems().forEach(item -> {
                    item.setStyle("-fx-pref-width: " + buttonWidth + "px;");
                });

                // show the ContextMenu directly below the button
                double x = bookingButton.localToScreen(0, 0).getX();
                double y = bookingButton.localToScreen(0, 0).getY() + bookingButton.getHeight();

                bookingContextMenu.show(bookingButton, x, y);
            } else {
                bookingContextMenu.hide();
            }
        });
    }

    // setters
    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void setAccommodationManagementSingleton(AccommodationManagementSingleton accommodationManagementSingleton){
        this.accommodationManagementSingleton = accommodationManagementSingleton;
    }

    public void setBookingList(List<Booking> bookingList){
        this.bookingList = bookingList;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    // dynamically updates scene based on chosen accommodation type, called when the main application initialises the scene
    public void updateScene(int accommodationType){
        if(accommodationType == 1){
            int index = 0;
            categoryTitleLabel.setText("Browse Our Flats");
            for (Accommodation accommodation : AccommodationManagementSingleton.getInstance().getAccommodationList()){
                if (accommodation instanceof Flat flat){
                    showNewInformation(accommodation, index, false, null); // shows new information for flats
                    ++index; // uses an index to cycle through earlier lists and set elements correctly
                }
            }
            resetUnusedComponents(index); // sets unused panel elements to blank
        }
        else if (accommodationType == 2){
            int index = 0;
            categoryTitleLabel.setText("Browse Our Hotels");
            for (Accommodation accommodation : AccommodationManagementSingleton.getInstance().getAccommodationList()){
                if (accommodation instanceof Hotel hotel){
                    showNewInformation(accommodation, index, false, null); // shows new information for hotel
                    ++index; // uses an index to cycle through earlier lists and set elements correctly
                }
            }
            resetUnusedComponents(index); // sets unused panel elements to blank
        }
        else if (accommodationType == 3){
            int index = 0;
            categoryTitleLabel.setText("Browse Our Villas");
            for (Accommodation accommodation : AccommodationManagementSingleton.getInstance().getAccommodationList()){
                if (accommodation instanceof Villa villa){
                    showNewInformation(accommodation, index, false, null); // shows new information for villa
                    ++index; // uses an index to cycle through earlier lists and set elements correctly
                }
            }
            resetUnusedComponents(index); // sets unused panel elements to blank
        }
        else if (accommodationType == 4){ // special type only called when user wants to see their own bookings
            int index = 0;
            categoryTitleLabel.setText("Your bookings");
            for (Booking booking : bookingList){
                if(booking.getAccountID() == account.getAccountID()){ // matches bookings and account ID
                    for(Accommodation accommodation : accommodationManagementSingleton.getAccommodationList()){
                        if(accommodation.getID()==booking.getPropertyID()){
                            showNewInformation(accommodation, index, true, booking); // function takes the booking as parameter
                            ++index; // uses an index to cycle through earlier lists and set elements correctly
                        }
                    }
                }
            }
            resetUnusedComponents(index); // sets unused panel elements to blank
        }
    }

    // function used to update information panels based on user choice of accommodation or booking show
    public void showNewInformation(Accommodation accommodation, int index, boolean bookingsShow, Booking booking){
        String directoryPath = "/images/" + accommodation.getID() + "/"; // directories saved based on ID
        URL mainImageUrl = getClass().getResource(directoryPath + "mainImage.png"); // url generated for image panel

        // setting main image
        if (mainImageUrl != null) {
            Image newMainImage = new Image(mainImageUrl.toExternalForm());
            propertyIcons.get(index).setImage(newMainImage);
        } else {
            System.out.println("mainImage.png not found in " + directoryPath);
        }

        // name and location set using index
        titles.get(index).setText(accommodation.getName());
        locations.get(index).setText(accommodation.getAddress().getCity());

        // if user wants to see bookings the cost section now shows the date of the booking and the icon is disabled
        if(bookingsShow){
            costs.get(index).setText(String.valueOf(booking.getStartDate()));
            propertyIcons.get(index).setDisable(true);
        } else {
            costs.get(index).setText("£"+String.valueOf(accommodation.getPrice())+" P/N"); // otherwise cost is shown and user can click the icons to book the property
        }

        // if the accommodation is booked
        if (!bookingsShow && accommodation.getAccommodationState()) {
            propertyIcons.get(index).setEffect(grayscale); // grey out icon
            propertyIcons.get(index).setDisable(true); // button disabled
            titles.get(index).setText("UNAVAILABLE");
        }
        else if(bookingsShow){ // if user wants to see bookings
            if(!booking.getBookingState()){ // and if the booking is inactive
                propertyIcons.get(index).setEffect(grayscale); // grey out icon
                propertyIcons.get(index).setDisable(true);
                titles.get(index).setText("INACTIVE"); // booking shown as inactive
            }
        }
    }

    public void resetUnusedComponents(int startIndex) { // start index is final index used in the update scene function
        for (int i = startIndex; i < propertyIcons.size(); i++) {
            // reset all elements to be blank
            propertyIcons.get(i).setImage(null);
            propertyIcons.get(i).setEffect(null);
            propertyIcons.get(i).setDisable(false);

            titles.get(i).setText("");
            locations.get(i).setText("");
            costs.get(i).setText("");
        }
    }

    // functions to find correct property scene when user selects a property
    public void onImage1Click(MouseEvent mouseEvent){
        String propertyName = title1.getText();
        findPropertyScene(propertyName);
    }

    public void onImage2Click(MouseEvent mouseEvent){
        String propertyName = title2.getText();
        findPropertyScene(propertyName);
    }

    public void onImage3Click(MouseEvent mouseEvent){
        String propertyName = title3.getText();
        findPropertyScene(propertyName);
    }

    public void onImage4Click(MouseEvent mouseEvent){
        String propertyName = title4.getText();
        findPropertyScene(propertyName);
    }

    public void onImage5Click(MouseEvent mouseEvent){
        String propertyName = title5.getText();
        findPropertyScene(propertyName);
    }

    public void onImage6Click(MouseEvent mouseEvent){
        String propertyName = title6.getText();
        findPropertyScene(propertyName);
    }

    // finds and shows property scene using the name from the property selected
    public void findPropertyScene(String propertyName){
        for (Accommodation accommodation : accommodationManagementSingleton.getAccommodationList()){
            if (Objects.equals(accommodation.getName(), propertyName)){ // searches through singleton list and matches
                try {
                    mainApplication.showPropertyScene(accommodation.getID()); // shows property scene using found ID
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // exit application
    public void onExitClick(ActionEvent actionEvent) {
        Platform.exit();
    }

    // show menu
    public void onMenuClick(ActionEvent actionEvent){
        try {
            mainApplication.showMenuScene(); // shows menu scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
