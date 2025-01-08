package com.example.spareui.writers;

import com.example.spareui.information.Account;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// writes new account to file
public class AccountWriter {
    public static void accountWriter(Account newAccount){
        String csvFile = "./resources/accounts.csv";

        String[] newData = {newAccount.getUserName(), String.valueOf(newAccount.getAccountID()), String.valueOf(newAccount.getUserType()), newAccount.getPassword()};

        try (FileWriter fw = new FileWriter(csvFile, true); // appends csv file
        PrintWriter writer = new PrintWriter(fw)) {
            writer.println(String.join(",", newData));
            System.out.println("New account successfully written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
