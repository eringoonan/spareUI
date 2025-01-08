package com.example.spareui;

import com.example.spareui.accommodations.*;
import com.example.spareui.information.Booking;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;

// controls property view FXML file
public class PropertyController {
    private MainApplication mainApplication;
    private AccommodationManagementSingleton accommodationManagementSingleton;
    private List<Booking> bookingList;
    private Accommodation accommodation = null;
    private ContextMenu contextMenu;
    private ContextMenu bookingContextMenu;
    @FXML
    Button dropdownButton;
    @FXML
    Button bookingButton;
    @FXML
    private Label propertyName;
    @FXML
    private Label propertyPrice;
    @FXML
    private Label propertyLocation;
    @FXML
    private ImageView mainImage;
    @FXML
    private ImageView smallImage1;
    @FXML
    private ImageView smallImage2;
    @FXML
    private ImageView smallImage3;
    @FXML
    private ImageView smallImage4;
    @FXML
    private ImageView extraFacVec1;
    @FXML
    private ImageView extraFacVec2;
    @FXML
    private ImageView extraFacVec3;
    @FXML
    private Label washingLabel;
    @FXML
    private Label stoveLabel;
    @FXML
    private Label ovenLabel;
    @FXML
    private Label fridgeLabel;
    @FXML
    private Label poolLabel;
    @FXML
    private Label extraFacLab1;
    @FXML
    private Label extraFacLab2;
    @FXML
    private Label extraFacLab3;
    ColorAdjust grayscale = new ColorAdjust();

    public void initialize(){
        // create account context menu
        contextMenu = new ContextMenu();
        MenuItem profileItem = new MenuItem("Profile");
        MenuItem bookingItem = new MenuItem("Bookings");
        contextMenu.getItems().addAll(profileItem, bookingItem);

        profileItem.setOnAction(event -> {
            try {
                mainApplication.showProfileScene(); // show profile
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

        // show context menu on click
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

        // create booking context menu
        bookingContextMenu = new ContextMenu();
        MenuItem flatsItem = new MenuItem("Flats");
        MenuItem hotelsItem = new MenuItem("Hotels");
        MenuItem villasItem = new MenuItem("Villas");
        bookingContextMenu.getItems().addAll(flatsItem, hotelsItem, villasItem);

        flatsItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(1); // show flats
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        hotelsItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(2); // show hotels
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        villasItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(3); // show villas
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        // show context menu on click
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

    // show menu
    @FXML
    public void onMenuClick(){
        try {
            mainApplication.showMenuScene(); // show menu scene
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    // dynamically update scene with correct property
    public void updateScene(int propertyID) {
        grayscale.setSaturation(-1); // initialise grayscale
        for (Accommodation accommodation : accommodationManagementSingleton.getAccommodationList()){
            if(accommodation.getID() == propertyID){
                this.accommodation = accommodation; // find accommodation using propertyID
            }
        }

        // setting text values
        propertyName.setText(accommodation.getName());
        propertyPrice.setText("£"+accommodation.getPrice()+ " P/N");
        propertyLocation.setText(accommodation.getAddress().getCity());

        // setting images
        String directoryPath = "/images/" + propertyID + "/";
        URL mainImageUrl = getClass().getResource(directoryPath + "mainImage.png");
        URL small1URL = getClass().getResource(directoryPath + "small1.png");
        URL small2URL = getClass().getResource(directoryPath + "small2.png");
        URL small3URL = getClass().getResource(directoryPath + "small3.png");
        URL small4URL = getClass().getResource(directoryPath + "small4.png");

        // set main image
        if (mainImageUrl != null) {
            Image newMainImage = new Image(mainImageUrl.toExternalForm());
            mainImage.setImage(newMainImage);
        } else {
            System.out.println("mainImage.png not found in " + directoryPath);
        }

        // set smaller images
        if (small1URL != null) {
            Image newSmall1 = new Image(small1URL.toExternalForm());
            smallImage1.setImage(newSmall1);
        } else {
            System.out.println("small1.png not found in " + directoryPath);
        }

        if (small2URL != null) {
            Image newSmall2 = new Image(small2URL.toExternalForm());
            smallImage2.setImage(newSmall2);
        } else {
            System.out.println("small2.png not found in " + directoryPath);
        }

        if (small3URL != null) {
            Image newSmall3 = new Image(small3URL.toExternalForm());
            smallImage3.setImage(newSmall3);
        } else {
            System.out.println("small3.png not found in " + directoryPath);
        }

        if (small4URL != null) {
            Image newSmall4 = new Image(small4URL.toExternalForm());
            smallImage4.setImage(newSmall4);
        } else {
            System.out.println("small4.png not found in " + directoryPath);
        }

        // setting facility vectors
        String vectorDirectory = "/images/facilityVectors/";
        URL vec1URL = null;
        URL vec2URL = null;
        URL vec3URL = null;

        // set accommodation specific type vectors
        if (accommodation instanceof Flat flat){
            vec1URL = getClass().getResource(vectorDirectory + "balconyVector.png");
            vec2URL = getClass().getResource(vectorDirectory + "parkingVector.png");
            vec3URL = getClass().getResource(vectorDirectory + "petVector.png");
        } else if (accommodation instanceof Hotel hotel){
            vec1URL = getClass().getResource(vectorDirectory + "starVector.png");
            vec2URL = getClass().getResource(vectorDirectory + "doorVector.png");
            vec3URL = getClass().getResource(vectorDirectory + "dishVector.png");
        } else if (accommodation instanceof  Villa villa){
            vec1URL = getClass().getResource(vectorDirectory + "bedVector.png");
            vec2URL = getClass().getResource(vectorDirectory + "gardenVector.png");
            vec3URL = getClass().getResource(vectorDirectory + "beachVector.png");
        }

        // set the images with the assigned vectors
        if (vec1URL != null){
            Image newVector1 = new Image(vec1URL.toExternalForm());
            extraFacVec1.setImage(newVector1);
        } else {
            System.out.println("vector not found in " + vectorDirectory);
        }

        if (vec2URL != null){
            Image newVector2 = new Image(vec2URL.toExternalForm());
            extraFacVec2.setImage(newVector2);
        } else {
            System.out.println("vector not found in " + vectorDirectory);
        }

        if (vec3URL != null){
            Image newVector3 = new Image(vec3URL.toExternalForm());
            extraFacVec3.setImage(newVector3);
        } else {
            System.out.println("vector not found in " + vectorDirectory);
        }

        // settings values of the facilities using booleans from the facilities object found within each accommodation instance

        // set washing machine
        if (!accommodation.getFacilities().getWashingMachine()) {
            washingLabel.setText("");
            Text text = new Text("Washing Machine");
            text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
            washingLabel.setGraphic(text);
        } else {
            washingLabel.setText("Washing Machine");
        }

        // set stove
        if (!accommodation.getFacilities().getStove()) {
            stoveLabel.setText("");
            Text text = new Text("Stove");
            text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
            stoveLabel.setGraphic(text);
        } else {
            stoveLabel.setText("Stove");
        }

        // set oven
        if (!accommodation.getFacilities().getOven()) {
            ovenLabel.setText("");
            Text text = new Text("Oven");
            text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
            ovenLabel.setGraphic(text);
        } else {
            ovenLabel.setText("Oven");
        }

        // set fridge
        if (!accommodation.getFacilities().getFridge()) {
            fridgeLabel.setText("");
            Text text = new Text("Fridge");
            text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
            fridgeLabel.setGraphic(text);
        } else {
            fridgeLabel.setText("Fridge");
        }

        // set pool
        if (!accommodation.getFacilities().getPool()) {
            poolLabel.setText("");
            Text text = new Text("Pool");
            text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
            poolLabel.setGraphic(text);
        } else {
            poolLabel.setText("Pool");
        }

        // adjusting the accommodation subclass specific vectors
        if(accommodation instanceof Flat flat){

            extraFacLab1.setText("Balcony");
            extraFacLab2.setText("Parking");
            extraFacLab3.setText("Pets Allowed");

            // set balcony
            if(!flat.isBalcony()){
                extraFacLab1.setText("");
                Text text = new Text("Balcony");
                text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
                extraFacLab1.setGraphic(text);
            } else {
                extraFacLab1.setText("Balcony");
            }

            // set parking
            if(!flat.isParking()){
                extraFacLab2.setText("");
                Text text = new Text("Parking");
                text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
                extraFacLab2.setGraphic(text);
            } else {
                extraFacLab2.setText("Parking");
            }

            // set pets
            if(!flat.isPetsAllowed()){
                extraFacLab3.setText("");
                Text text = new Text("Pets Allowed");
                text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
                extraFacLab3.setGraphic(text);
            } else {
                extraFacLab3.setText("Pets Allowed");
            }
        }

        if(accommodation instanceof Hotel hotel){

            extraFacLab1.setText("Stars: "+ hotel.getStarRating()); // show star rating
            extraFacLab2.setText("Rooms: "+ hotel.getTotalRoomNumber()); // show room number
            extraFacLab3.setText("Room Service");

            // set room service
            if(!hotel.isRoomService()){
                extraFacLab3.setText("");
                Text text = new Text("Room Service");
                text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
                extraFacLab3.setGraphic(text);
            } else {
                extraFacLab3.setText("Room Service");
            }
        }

        if (accommodation instanceof Villa villa){
            extraFacLab1.setText("Beds: "+villa.getBedsNumber()); // show beds number
            extraFacLab2.setText("Garden");
            extraFacLab3.setText("Beach Nearby");

            // set garden
            if(!villa.getGarden()){
                extraFacLab2.setText("");
                Text text = new Text("Garden");
                text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
                extraFacLab2.setGraphic(text);
            } else {
                extraFacLab2.setText("Garden");
            }

            // set beach
            if(!villa.getBeachNearby()){
                extraFacLab3.setText("");
                Text text = new Text("Beach Nearby");
                text.setStyle("-fx-strikethrough: true;"); // strikethrough if not available
                extraFacLab3.setGraphic(text);
            } else {
                extraFacLab3.setText("Beach Nearby");
            }
        }
    }

    // show booking scene with chosen accommodation
    public void onBookClick(ActionEvent actionEvent) {
        int propertyID = accommodation.getID();
        try {
            mainApplication.showBookingScene(propertyID); // Transition to the login scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // exit system
    public void onExitClick(ActionEvent actionEvent) {
        Platform.exit();
    }
}
