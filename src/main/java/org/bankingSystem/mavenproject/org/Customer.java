package org.bankingSystem.mavenproject.org;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    private int id;
    private String name;
    private String username;
    private String password;
    private List<Integer> accounts;

    @JsonCreator
    public Customer(@JsonProperty("id") int id, 
                    @JsonProperty("name") String name, 
                    @JsonProperty("username") String username, 
                    @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public List<Integer> getAccounts() { return accounts; }
    public void addAccount(int accountId) { accounts.add(accountId); }
    public boolean authenticate(String password) { return this.password.equals(password); }
}

