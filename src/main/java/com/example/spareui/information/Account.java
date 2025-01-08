package com.example.spareui.information;

// class for account
public class Account {
    private String userName;
    private int accountID;
    private int userType;
    private String password;

    // constructor
    public Account(String userName, int accountID, int userType, String password) {
        this.userName = userName;
        this.accountID = accountID;
        this.userType = userType;
        this.password = password;
    }

    // getters
    public String getUserName() {
        return userName;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }

    // methods
    public String printAccount(){
        String tempType;
        if (userType == 1) {
            tempType = "Customer";
        }
        else {
            tempType = "Admin";
        }
        return "Username: " + userName +
                "\n User Type: " + tempType;
    }
}

