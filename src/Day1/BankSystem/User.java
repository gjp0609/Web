package Day1.BankSystem;

/**
 * Created by gjp06 on 17.3.15.
 */
public class User {

    private String id;
    private String name;
    private String password;
    private String phone;
    private double balance;

    public User(String id, String name, String password, String phone, double balance) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.balance = balance;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
