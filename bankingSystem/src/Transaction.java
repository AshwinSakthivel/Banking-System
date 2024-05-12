public class Transaction {
    private int id;
    private double amount;
    private TransactionType type;

    public Transaction(int id, double amount, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", amount=" + amount + ", type=" + type + '}';
    }
}

 enum TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER
}

