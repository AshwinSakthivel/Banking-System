package org.bankingSystem.mavenproject.org;

import java.io.IOException;

public class Main {
    private static final String DATA_FILE_PATH = "bankData.json";

    public static void main(String[] args) {
        Bank bank;

        // Load data
        try {
            bank = DataPersistence.loadData(DATA_FILE_PATH);
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("No existing data found. Starting with a new bank.");
            bank = new Bank();
        }

        BankingApp app = new BankingApp(bank);
        app.start();

        // Save data
        try {
            DataPersistence.saveData(DATA_FILE_PATH, bank);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save data.");
            e.printStackTrace();
        }
    }
}
