import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int ID;
    private String name;
    private String username;
    private String password;
    private List<Integer> accounts;

    Customer(int ID, String name, String username, String password){
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
        accounts = new ArrayList<>;
    }

    public int getId() {
        return ID;
    }

    public void addAccount(int accountId) {
        accounts.add(accountId);
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
