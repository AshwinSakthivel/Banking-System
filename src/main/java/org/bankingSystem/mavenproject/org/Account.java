package org.bankingSystem.mavenproject.org;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private int id;
    private int customerId;
    private CurrencyType currency;
    private double balance;

    @JsonCreator
    public Account(@JsonProperty("id") int id, 
                   @JsonProperty("customerId") int customerId, 
                   @JsonProperty("currency") CurrencyType currency) {
        this.id = id;
        this.customerId = customerId;
        this.currency = currency;
        this.balance = 0;
    }

    // Getters and setters
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public CurrencyType getCurrency() { return currency; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public void deposit(double amount) { balance += amount; }
    public boolean withdraw(double amount) { 
        if (balance < amount) return false; 
        balance -= amount; 
        return true; 
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", customerId=" + customerId + ", currency=" + currency + ", balance=" + balance + '}';
    }
}


enum CurrencyType {
    USD,
    GBP
}
