public class Account {
    private int id;
    private int customerId;
    private String currency;
    private double balance;

    public Account(int id, int customerId, String currency) {
        this.id = id;
        this.customerId = customerId;
        this.currency = currency;
        this.balance = 0;
    }

    public int getId() {
        return id;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", customerId=" + customerId + ", currency='" + currency + '\'' + ", balance=" + balance + '}';
    }
}
