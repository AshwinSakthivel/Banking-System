package org.bankingSystem.mavenproject.org;

import java.util.List;
import java.util.Scanner;

public class BankingApp {
    private Bank bank;

    public BankingApp(Bank bank) {
        this.bank = bank;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Banking System");
            System.out.println("1. Add Customer");
            System.out.println("2. Authenticate Customer");
            System.out.println("3. Open Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Transfer");
            System.out.println("7. View Account Details");
            System.out.println("8. View Customer Accounts");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    authenticateCustomer(scanner);
                    break;
                case 3:
                    openAccount(scanner);
                    break;
                case 4:
                    deposit(scanner);
                    break;
                case 5:
                    withdraw(scanner);
                    break;
                case 6:
                    transfer(scanner);
                    break;
                case 7:
                    viewAccountDetails(scanner);
                    break;
                case 8:
                    viewCustomerAccounts(scanner);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void addCustomer(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        int customerId = bank.addCustomer(name, username, password);
        System.out.println("Customer added with ID: " + customerId);
    }

    private void authenticateCustomer(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        int customerId = bank.authenticateCustomer(username, password);
        if (customerId != -1) {
            System.out.println("Authentication successful. Customer ID: " + customerId);
        } else {
            System.out.println("Authentication failed.");
        }
    }

    private void openAccount(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter currency (1 for USD, 2 for GBP): ");
        int currencyChoice = scanner.nextInt();
        CurrencyType currency = (currencyChoice == 1) ? CurrencyType.USD : CurrencyType.GBP;
        int accountId = bank.openAccount(customerId, currency);
        System.out.println("Account opened with ID: " + accountId);
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        boolean success = bank.deposit(accountId, amount);
        if (success) {
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Deposit failed.");
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        boolean success = bank.withdraw(accountId, amount);
        if (success) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Withdrawal failed.");
        }
    }

    private void transfer(Scanner scanner) {
        System.out.print("Enter from account ID: ");
        int fromAccountId = scanner.nextInt();
        System.out.print("Enter to account ID: ");
        int toAccountId = scanner.nextInt();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        boolean success = bank.transfer(fromAccountId, toAccountId, amount);
        if (success) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed.");
        }
    }

    private void viewAccountDetails(Scanner scanner) {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        Account account = bank.getAccountDetails(accountId);
        System.out.println(account);
    }

    private void viewCustomerAccounts(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        List<Account> accounts = bank.getCustomerAccounts(customerId);
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
