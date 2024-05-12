import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<Integer, Customer> customers;
    private Map<Integer, Account> accounts;
    private Map<Integer, List<Transaction>> transactions;
    private int CustomerId = 1;
    private int AccountId = 1;
    private int transactionId = 1;

    public Bank() {
        customers = new HashMap<>();
        accounts = new HashMap<>();
        transactions = new HashMap<>();
    }

    public int addCustomer(String name, String username, String password) {
        Customer customer = new Customer(CustomerId++, name, username, password);
        customers.put(customer.getId(), customer);
        return customer.getId();
    }

    public boolean authenticateCustomer(int customerId, String password) {
        if (!customers.containsKey(customerId)) {
            return false;
        }
        return customers.get(customerId).authenticate(password);
    }

    public int openAccount(int customerId, String currency) {
        if (!customers.containsKey(customerId)) {
            throw new IllegalArgumentException("Customer not found");
        }
        Account account = new Account(AccountId++, customerId, currency);
        accounts.put(account.getId(), account);
        customers.get(customerId).addAccount(account.getId());
        transactions.put(account.getId(), new ArrayList<>());
        return account.getId();
    }

    public void deposit(int accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        Account account = accounts.get(accountId);
        account.deposit(amount);
        addTransaction(accountId, amount, TransactionType.DEPOSIT);
    }

    public void withdraw(int accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        Account account = accounts.get(accountId);
        account.withdraw(amount);
        addTransaction(accountId, amount, TransactionType.WITHDRAWAL);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        if (!accounts.containsKey(fromAccountId) || !accounts.containsKey(toAccountId)) {
            throw new IllegalArgumentException("Accounts not found");
        }
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        addTransaction(fromAccountId, amount, TransactionType.TRANSFER);
        addTransaction(toAccountId, amount, TransactionType.TRANSFER);
    }

    private void addTransaction(int accountId, double amount, TransactionType type) {
        List<Transaction> accountTransactions = transactions.get(accountId);
        Transaction transaction = new Transaction(transactionId++, amount, type);
        accountTransactions.add(transaction);
    }

    public String getAccountDetails(int accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        Account account = accounts.get(accountId);
        StringBuilder sb = new StringBuilder();
        sb.append("Account Details:").append("\n")
                .append(account.toString()).append("\n")
                .append("Transaction History:").append("\n");
        List<Transaction> accountTransactions = transactions.get(accountId);
        for (Transaction transaction : accountTransactions) {
            sb.append(transaction.toString()).append("\n");
        }
        return sb.toString();
    }
}