package com.example.spareui;

import com.example.spareui.accommodations.Accommodation;
import com.example.spareui.accommodations.AccommodationManagementSingleton;
import com.example.spareui.accommodations.states.AccommodationStateBooked;
import com.example.spareui.information.Account;
import com.example.spareui.information.Booking;
import com.example.spareui.writers.BookingWriter;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

// class to control booking FXML file
public class BookingController {
    private MainApplication mainApplication;
    private List<Booking> bookingList;
    private Account account;
    private AccommodationManagementSingleton accommodationManagementSingleton;
    LocalDate checkinDate;
    LocalDate checkoutDate;
    long totalDays;
    double totalPrice;
    private ContextMenu bookingContextMenu;
    private ContextMenu contextMenu;
    @FXML
    Button bookingButton;
    @FXML
    Button dropdownButton;
    @FXML
    ImageView mainImage;
    @FXML
    Label titleLabel;
    @FXML
    Label priceLabel;
    @FXML
    Label locationLabel;
    @FXML
    Label totalPriceLabel;
    @FXML
    Label resultLabel;
    @FXML
    DatePicker checkinBox;
    @FXML
    DatePicker checkoutBox;
    @FXML
    Label totalLabel;
    @FXML
    Button bookButton;
    Accommodation chosenAccommodation = null;

    public void initialize(){ // on page initialisation
        if (checkinDate == null || checkoutDate == null){
            bookButton.setDisable(true); // book button automatically disabled
        }

        // context menu creation
        bookingContextMenu = new ContextMenu();
        MenuItem flatsItem = new MenuItem("Flats");
        MenuItem hotelsItem = new MenuItem("Hotels");
        MenuItem villasItem = new MenuItem("Villas");
        bookingContextMenu.getItems().addAll(flatsItem, hotelsItem, villasItem);

        // show scenes based on context menu clicks
        flatsItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(1); // Transition to the login scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        hotelsItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(2); // Transition to the login scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        villasItem.setOnAction(actionEvent -> {
            try {
                mainApplication.showCategoryScene(3); // Transition to the login scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        // showing context menu on booking button click
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

        // account context menu
        contextMenu = new ContextMenu();
        MenuItem profileItem = new MenuItem("Profile");
        MenuItem bookingItem = new MenuItem("Bookings");
        contextMenu.getItems().addAll(profileItem, bookingItem);

        // show relevant scenes on context menu clicks
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

        // show account options context menu
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

    }

    // setters
    public void setAccommodationManagementSingleton(AccommodationManagementSingleton accommodationManagementSingleton){
        this.accommodationManagementSingleton = accommodationManagementSingleton;
    }

    public void setBookingList(List<Booking> bookingList){
        this.bookingList = bookingList;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    // on-click functions
    public void onExitClick(ActionEvent actionEvent) {
        Platform.exit();
    } // exit application

    public void onMenuClick(ActionEvent actionEvent){
        try {
            mainApplication.showMenuScene(); // Transition to the menu scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateScene(int propertyID){ // updates scene based on chosen accommodation from previous page
        for (Accommodation accommodation : accommodationManagementSingleton.getAccommodationList()){
            if(accommodation.getID() == propertyID)
                this.chosenAccommodation = accommodation; // matches via property ID
        }
        // update visual items
        titleLabel.setText(chosenAccommodation.getName());
        locationLabel.setText(chosenAccommodation.getAddress().getCity());
        priceLabel.setText("£"+String.valueOf(chosenAccommodation.getPrice())+" P/N");
        resultLabel.setText("");

        String directoryPath = "/images/" + chosenAccommodation.getID() + "/"; // images stored based on ID
        URL mainImageUrl = getClass().getResource(directoryPath + "mainImage.png"); // url generated based on path

        // set main image pannel
        if (mainImageUrl != null) {
            Image newMainImage = new Image(mainImageUrl.toExternalForm());
            mainImage.setImage(newMainImage);
        } else {
            System.out.println("mainImage.png not found in " + directoryPath);
        }
    }

    // verification of parameters on filling check-in date box
    public void onCheckinBoxFilled(ActionEvent actionEvent){
        if (checkoutBox.getValue() != null && checkoutBox.getValue().isBefore(checkinBox.getValue())) { // if checkout is before check-in throw errors and reset values
            checkinBox.setValue(null);
            checkoutBox.setValue(null);
            checkoutBox.setPromptText("Checkout must be after check-in");
            resultLabel.setText("Error: checkout is before check-in");
            resultLabel.setStyle("-fx-text-fill: #da6565;");

            totalLabel.setText("Total Days: ");
            totalPriceLabel.setText("£");
            bookButton.setDisable(true); // button disabled
        } else if (checkinBox.getValue().isBefore(LocalDate.now())) { // if check-in is in past throw errors and reset
            checkinBox.setValue(null);
            checkoutBox.setValue(null);
            checkinBox.setPromptText("Check-in cannot be in the past");
            resultLabel.setText("Error: check-in in the past");
            resultLabel.setStyle("-fx-text-fill: #da6565;");

            totalLabel.setText("Total Days: ");
            totalPriceLabel.setText("£");
            bookButton.setDisable(true); // button disabled
        }
    }

    // verification of parameters on filling check-out date box
    public void onCheckoutBoxFilled(ActionEvent actionEvent){

        // verify both date boxes are filled
        if(checkinBox.getValue() == null || checkoutBox.getValue() == null){
            checkinBox.setValue(null);
            checkoutBox.setValue(null);
            checkinBox.setPromptText("Please enter a check-in date");
            resultLabel.setText("Error: check-in date not selected");
            resultLabel.setStyle("-fx-text-fill: #da6565;");
            totalLabel.setText("Total Days: ");
            totalPriceLabel.setText("£");
            bookButton.setDisable(true); // button disabled

        }
        // verify checkout is not before check-in
        else if (checkoutBox.getValue().isBefore(checkinBox.getValue())) {
            checkinBox.setValue(null);
            checkoutBox.setValue(null);
            checkoutBox.setPromptText("Checkout must be after check-in");
            resultLabel.setText("Error: checkout is before check-in");
            resultLabel.setStyle("-fx-text-fill: #da6565;");
            totalLabel.setText("Total Days: ");
            totalPriceLabel.setText("£");
            bookButton.setDisable(true); // button disabled
        }
        // verify checkout is not in the past
        else if (checkoutBox.getValue().isBefore(LocalDate.now())){
            checkinBox.setValue(null);
            checkoutBox.setValue(null);
            checkoutBox.setPromptText("Checkout cannot be in the past");
            resultLabel.setText("Error: date is in the past");
            resultLabel.setStyle("-fx-text-fill: #da6565;");
            totalLabel.setText("Total Days: ");
            totalPriceLabel.setText("£");
            bookButton.setDisable(true); // button disabled

        } else {
            checkinDate = checkinBox.getValue(); // get checkout/in values
            checkoutDate = checkoutBox.getValue();
            totalDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate); // find total days
            totalPrice = Math.round(totalDays * chosenAccommodation.getPrice() * 100.0) / 100.0; // find total price to 2dp
            totalLabel.setText("Total Days: "+ String.valueOf(totalDays)); // show total details
            totalPriceLabel.setText("£" + String.valueOf(totalPrice));
            resultLabel.setText("");
            bookButton.setDisable(false); // enable booking button
        }
    }

    // function to create new booking on click
    @FXML
    public void onBookClick(ActionEvent actionEvent){
        int bookingID = bookingList.stream().mapToInt(Booking::getPropertyID).max().orElse(0) + 1; // booking ID set to higher than current highest
        Booking newBooking = new Booking(bookingID, chosenAccommodation.getID(), checkinDate, checkoutDate, account.getAccountID()); // new booking created
        chosenAccommodation.setAccommodationState(new AccommodationStateBooked(chosenAccommodation)); // accommodation state changed to booked
        BookingWriter.bookingWriter(newBooking, "./resources/bookings.csv"); // booking written to file

        // success text
        resultLabel.setText("Booking confirmed: " + chosenAccommodation.getName() +"\n"+"Returning to main menu...");
        resultLabel.setStyle("-fx-text-fill: #65da8d;");
        bookingList.add(newBooking);

        // delay to give loading impression
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            try {
                mainApplication.showMenuScene(); // Transition to the login scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

}
