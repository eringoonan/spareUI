package com.example.spareui;

import com.example.spareui.accommodations.Accommodation;
import com.example.spareui.accommodations.AccommodationManagementSingleton;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// class that controls menu scene
public class MenuController {
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
    Label city1;
    @FXML
    Label city2;
    @FXML
    Label city3;
    @FXML
    Label city4;
    @FXML
    Label city5;
    @FXML
    Label city6;

    @FXML
    Label price1;
    @FXML
    Label price2;
    @FXML
    Label price3;
    @FXML
    Label price4;
    @FXML
    Label price5;
    @FXML
    Label price6;
    @FXML
    Button dropdownButton;
    @FXML
    Button bookingButton;

    // create icon lists and context menus
    private List<ImageView> propertyIcons = new ArrayList<>();
    private List<Label> titleList = new ArrayList<>();
    private List<Label> cityList = new ArrayList<>();
    private List<Label> priceList = new ArrayList<>();
    private ContextMenu contextMenu;
    private ContextMenu bookingContextMenu;

    private MainApplication mainApplication;
    private AccommodationManagementSingleton accommodationManagementSingleton;
    private List<Booking> bookingList;

    public void initialize(){
        // add elements to lists
        propertyIcons.add(image1);
        propertyIcons.add(image2);
        propertyIcons.add(image3);
        propertyIcons.add(image4);
        propertyIcons.add(image5);
        propertyIcons.add(image6);

        titleList.add(title1);
        titleList.add(title2);
        titleList.add(title3);
        titleList.add(title4);
        titleList.add(title5);
        titleList.add(title6);

        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        cityList.add(city4);
        cityList.add(city5);
        cityList.add(city6);

        priceList.add(price1);
        priceList.add(price2);
        priceList.add(price3);
        priceList.add(price4);
        priceList.add(price5);
        priceList.add(price6);

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
                mainApplication.showCategoryScene(4); // show bookings
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // show context menu when button pushed
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
                mainApplication.showCategoryScene(1); // show category scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        hotelsItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(2); // show category scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        villasItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(3); // show category scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        // show booking menu on click
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

    // function to dynamically update the panels on the front page
    @FXML
    public void updateScene(){
        int panelIndex = 0;

       for (Accommodation accommodation : accommodationManagementSingleton.getAccommodationList()){
           if (!accommodation.getAccommodationState()){ // only if the accommodation isnt booked
               if(panelIndex<propertyIcons.size()){ // index only runs for the amount of icons in the list
                   showNewInformation(accommodation,panelIndex); // shows new information
                   panelIndex++;
               } else {
                   break;
               }
           }
       }

        while (panelIndex < propertyIcons.size()) {
            clearPanel(panelIndex); // clears the panel
            panelIndex++;
        }
    }

    // function to clear elements on a panel
    private void clearPanel(int index){
        propertyIcons.get(index).setImage(null);
        titleList.get(index).setText("");
        cityList.get(index).setText("");
        priceList.get(index).setText("");
    }

    // shows new information on a panel
    public void showNewInformation(Accommodation accommodation, int index){
        String directoryPath = "/images/" + accommodation.getID() + "/"; // directories saved based on accommodation ID
        URL mainImageUrl = getClass().getResource(directoryPath + "mainImage.png"); // generate link to main image

        // set new main image
        if (mainImageUrl != null) {
            Image newMainImage = new Image(mainImageUrl.toExternalForm());
            propertyIcons.get(index).setImage(newMainImage);
        } else {
            System.out.println("mainImage.png not found in " + directoryPath);
        }

        // set new information
        titleList.get(index).setText(accommodation.getName());
        cityList.get(index).setText(accommodation.getAddress().getCity());
        priceList.get(index).setText("£"+String.valueOf(accommodation.getPrice())+" P/N");
    }

    // exit application
    @FXML
    public void onExitClick(ActionEvent actionEvent) {
        Platform.exit();
    }

    // find properties on image clicks
    @FXML
    public void onImage1Click(MouseEvent mouseEvent){
        String propertyName = title1.getText();
        findPropertyScene(propertyName);
    }

    @FXML
    public void onImage2Click(MouseEvent mouseEvent) {
        String propertyName = title2.getText();
        findPropertyScene(propertyName);
    }

    @FXML
    public void onImage3Click(MouseEvent mouseEvent){
        String propertyName = title3.getText();
        findPropertyScene(propertyName);
    }

    @FXML
    public void onImage4Click(MouseEvent mouseEvent){
        String propertyName = title4.getText();
        findPropertyScene(propertyName);
    }

    @FXML
    public void onImage5Click(MouseEvent mouseEvent){
        String propertyName = title5.getText();
        findPropertyScene(propertyName);
    }

    @FXML
    public void onImage6Click(MouseEvent mouseEvent){
        String propertyName = title6.getText();
        findPropertyScene(propertyName);
    }

    // shows category scenes based on user property type choice
    @FXML
    public void onFlatClick(ActionEvent actionEvent) {
        try {
            mainApplication.showCategoryScene(1); // show flats in category scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onHotelClick(ActionEvent actionEvent){
        try {
            mainApplication.showCategoryScene(2); // show hotels in category scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onVillaClick(ActionEvent actionEvent){
        try {
            mainApplication.showCategoryScene(3); // show villas in category scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // function to find the correct property scene to show
    public void findPropertyScene(String propertyName){
        for (Accommodation accommodation : accommodationManagementSingleton.getAccommodationList()){
            if (Objects.equals(accommodation.getName(), propertyName)){ // matches name from property with name in accommodation list
                try {
                    mainApplication.showPropertyScene(accommodation.getID()); // shows property scene chosen by user
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
