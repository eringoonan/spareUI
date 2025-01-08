package com.example.spareui.readers;

import com.example.spareui.information.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// static method class used to read account object instances from a csv file
public class AccountReader {
    public static List<Account> accountReader(){
        String csvFile = "./resources/accounts.csv";
        String line = "";
        String separator = ",";
        List<Account> accountList = new ArrayList<Account>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) { // uses buffered reader to read lines, separated by ,
            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);

                String userName = data[0];
                int accountId = Integer.parseInt(data[1]);
                int userType = Integer.parseInt(data[2]);
                String password = data[3];

                accountList.add(new Account(userName, accountId, userType, password)); // adds to account list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully read accounts file");
        return accountList;
    }
}
