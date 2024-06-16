package org.bankingSystem.mavenproject.org;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
    private int transactionId;
    private double amount;
    private TransactionType type;
    private int accountId;
    private int toAccountId;  // Add this field to handle transfers
    private int timestamp;

    // Existing constructors
    public Transaction(int transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public Transaction(int transactionId, double amount, TransactionType type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
    }

    // New constructor
    public Transaction(int transactionId, double amount, TransactionType type, int accountId, int timestamp) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.accountId = accountId;
        this.timestamp = timestamp;
    }

    public Transaction(int transactionId, double amount, TransactionType type, int accountId) {
		this.transactionId = transactionId;
		this.amount = amount;
	    this.type = type;
	    this.accountId = accountId;
	}

	// Getters and setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public int getToAccountId() { return toAccountId; }
    public void setToAccountId(int toAccountId) { this.toAccountId = toAccountId; }
}

enum TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER
}





