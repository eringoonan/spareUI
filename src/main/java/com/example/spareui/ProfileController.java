package com.example.spareui;

import com.example.spareui.information.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

// controls profile FXML file
public class ProfileController {
    private MainApplication mainApplication;
    private ContextMenu contextMenu;
    private ContextMenu bookingContextMenu;
    @FXML
    Button dropdownButton;
    @FXML
    Button bookingButton;
    @FXML
    Label usernameLabel;
    @FXML
    Label IDLabel;
    @FXML
    Label typeLabel;

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

        // show context menu on button click
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
                // Set menuItem widths dynamically to match the button width
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

    // set main application
    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    // update scene based on user account
    public void updateScene(Account account){
        // show user information
        usernameLabel.setText(account.getUserName());
        IDLabel.setText(String.valueOf(account.getAccountID()));
        if (account.getUserType() == 1){
           typeLabel.setText("Customer");
        } else if (account.getUserType() == 2){
            typeLabel.setText("Admin");
        }
    }

    // show main menu
    public void onMenuClick(ActionEvent actionEvent) {
        try {
            mainApplication.showMenuScene(); // menu scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
