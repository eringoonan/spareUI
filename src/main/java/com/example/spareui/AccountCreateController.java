package com.example.spareui;

import com.example.spareui.information.Account;
import com.example.spareui.writers.AccountWriter;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.List;

// controls FXML account creation view file
public class AccountCreateController {
    private List<Account> accountList;
    private MainApplication mainApplication;
    @FXML
    private TextField usernameBox;
    @FXML
    private PasswordField passwordBox;
    @FXML
    private PasswordField confPassBox;
    @FXML
    private Label creationResult;

    // setters
    public void setAccounts(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    public void onLoginClick(ActionEvent actionEvent) { // goes back to log-in scene
        try{
            mainApplication.showLoginScene();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onSignUpClick(ActionEvent actionEvent) { // takes data from user input boxes
        String username = usernameBox.getText();
        String password = passwordBox.getText();
        String confPassword = confPassBox.getText();
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || confPassword == null || confPassword.isEmpty()){ // verifies all boxes were filled
            creationResult.setText("Error: Fields cannot be empty");
            creationResult.setStyle("-fx-text-fill: #da6565;");
        } else if (!password.equals(confPassword)){
            creationResult.setText("Error: Passwords do not match");
            creationResult.setStyle("-fx-text-fill: #da6565;");
        } else {
            creationResult.setText("");
            accountCreator(username, password); // calls creator when conditions satisfied
        }
    }

    public void accountCreator(String username, String password){ // creates a new account and goes back to log-in scene
        for (Account account : accountList){
            if (account.getUserName().equals(username)){
                creationResult.setText("Error: username already taken"); // verifies account doesn't exist already
                return;
            }
        }
        int userType = 1;
        int accountID = 0;
        for (Account account: accountList) { // new account ID based on highest current id
            int highestID = account.getAccountID();
            if(accountID < highestID){
                accountID = highestID;
            }
        }
        accountID +=1;
        Account newAccount = new Account(username, accountID, userType, password);
        accountList.add(newAccount); // adds to account list
        AccountWriter.accountWriter(newAccount); // writes to file
        creationResult.setText("Account successfully created! Returning to login...");
        creationResult.setStyle("-fx-text-fill: #65da8d;");
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            try {
                mainApplication.showLoginScene(); // Transition to the login scene with some delay to give loading impression
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }
}
