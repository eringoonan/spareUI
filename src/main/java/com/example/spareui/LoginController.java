package com.example.spareui;

import com.example.spareui.information.Account;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.List;

import static java.awt.Color.green;

// class to control the login FXML file
public class LoginController {
    @FXML
    private Label loginResult;
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameBox;
    @FXML
    private PasswordField passwordBox;
    @FXML
    private MainApplication mainApplication;

    private List<Account> accountList;

    // setters
    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void setAccounts(List<Account> accountList) {
        this.accountList = accountList;
    }

    // when user clicks to log in
    @FXML
    public void onLoginClick(ActionEvent actionEvent) {
        String username = usernameBox.getText();
        String password = passwordBox.getText();
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){ // check boxes aren't empty
            loginResult.setText("Username/Password cannot be empty");
        } else {
            loginResult.setText("");
            checkAccount(username, password); // calls to check account
        }
    }

    // function to find and verify account details
    public void checkAccount(String username, String password) {
        boolean accountFound = false;

        for (Account account : accountList) {
            if (account.getUserName().equals(username)) { // find account in list via username
                accountFound = true;
                if (account.getPassword().equals(password)) { // check the password matches
                    mainApplication.setAccount(account); // account saved in main application
                    loginResult.setText("Login successful! Loading main menu...");
                    loginResult.setStyle("-fx-text-fill: #65da8d;");
                    PauseTransition delay = new PauseTransition(Duration.seconds(1)); // give impression of system loading
                    delay.setOnFinished(event -> {
                        try {
                            mainApplication.showMenuScene(); // show the main menu
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    delay.play();
                } else {
                    // exit function and give error if incorrect
                    loginResult.setText("Error: Incorrect Password");
                    loginResult.setStyle("-fx-text-fill: #da6565;");
                    return;
                }
            }
        }

        // If no account with the username was found
        if (!accountFound) {
            loginResult.setText("Error: Username not found");
            loginResult.setStyle("-fx-text-fill: #da6565;");
        }
    }

    // exit application
    @FXML
    public void onExitClick(ActionEvent actionEvent){
        Platform.exit();
    }

    // if user wants to create account
    @FXML
    public void onCreateAccount(ActionEvent actionEvent) {
        try {
            mainApplication.showAccountCreateScene(); // show account creation scene
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}