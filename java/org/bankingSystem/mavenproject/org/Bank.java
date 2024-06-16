package org.bankingSystem.mavenproject.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<Integer, Customer> customers;
    private Map<Integer, Account> accounts;
    private Map<Integer, List<Transaction>> transactions;
    private int customerIdCounter = 1;
    private int accountIdCounter = 1;
    private int transactionIdCounter = 1;

    public Bank() {
        customers = new HashMap<>();
        accounts = new HashMap<>();
        transactions = new HashMap<>();
    }

    // Getters and setters
    public Map<Integer, Customer> getCustomers() { return customers; }
    public void setCustomers(Map<Integer, Customer> customers) { this.customers = customers; }

    public Map<Integer, Account> getAccounts() { return accounts; }
    public void setAccounts(Map<Integer, Account> accounts) { this.accounts = accounts; }

    public Map<Integer, List<Transaction>> getTransactions() { return transactions; }
    public void setTransactions(Map<Integer, List<Transaction>> transactions) { this.transactions = transactions; }

    public int getCustomerIdCounter() { return customerIdCounter; }
    public void setCustomerIdCounter(int customerIdCounter) { this.customerIdCounter = customerIdCounter; }

    public int getAccountIdCounter() { return accountIdCounter; }
    public void setAccountIdCounter(int accountIdCounter) { this.accountIdCounter = accountIdCounter; }

    public int getTransactionIdCounter() { return transactionIdCounter; }
    public void setTransactionIdCounter(int transactionIdCounter) { this.transactionIdCounter = transactionIdCounter; }
    public int addCustomer(String name, String username, String password) {
        Customer customer = new Customer(customerIdCounter++, name, username, password);
        customers.put(customer.getId(), customer);
        return customer.getId();
    }

    public int authenticateCustomer(String username, String password) {
        for (Customer customer : customers.values()) {
            if (customer.getUsername().equals(username) && customer.authenticate(password)) {
                return customer.getId();
            }
        }
        return -1;
    }

    public int openAccount(int customerId, CurrencyType currency) {
        if (!customers.containsKey(customerId)) {
            throw new IllegalArgumentException("Customer not found");
        }
        Account account = new Account(accountIdCounter++, customerId, currency);
        accounts.put(account.getId(), account);
        customers.get(customerId).addAccount(account.getId());
        transactions.put(account.getId(), new ArrayList<>());
        return account.getId();
    }

    public boolean deposit(int accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        Account account = accounts.get(accountId);
        account.deposit(amount);
        processTransaction(new Transaction(transactionIdCounter++, amount, TransactionType.DEPOSIT, accountId));
        return true;
    }

    public boolean withdraw(int accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        Account account = accounts.get(accountId);
        boolean success = account.withdraw(amount);
        if (success) {
            processTransaction(new Transaction(transactionIdCounter++, amount, TransactionType.WITHDRAWAL, accountId));
        }
        return success;
    }

    public boolean transfer(int fromAccountId, int toAccountId, double amount) {
        if (!accounts.containsKey(fromAccountId) || !accounts.containsKey(toAccountId)) {
            throw new IllegalArgumentException("Accounts not found");
        }
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);
        if (!fromAccount.withdraw(amount)) {
            return false;
        }
        toAccount.deposit(amount);
        processTransaction(new Transaction(transactionIdCounter++, amount, TransactionType.TRANSFER, fromAccountId, toAccountId));
        return true;
    }

    private void processTransaction(Transaction transaction) {
        int accountId = transaction.getAccountId();
        transactions.get(accountId).add(transaction);
        if (transaction.getType() == TransactionType.TRANSFER) {
            int toAccountId = transaction.getToAccountId();
            transactions.get(toAccountId).add(transaction);
        }
    }

    public Account getAccountDetails(int accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        return accounts.get(accountId);
    }

    public List<Account> getCustomerAccounts(int customerId) {
        if (!customers.containsKey(customerId)) {
            throw new IllegalArgumentException("Customer not found");
        }
        List<Account> customerAccounts = new ArrayList<>();
        for (int accountId : customers.get(customerId).getAccounts()) {
            customerAccounts.add(accounts.get(accountId));
        }
        return customerAccounts;
    }
}


enum TransactionResult {
    SUCCESS,
    ACCOUNT_NOT_FOUND,
    INSUFFICIENT_FUNDS
}
