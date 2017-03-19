package Day2.Bank2;

/**
 * CARD_ID
 * PASSWORD
 * NAME
 * PHONE_NUM
 * ADDRESS
 * BALANCE
 * Created by gjp06 on 17.3.16.
 */
public class Account {
    private String cardId;
    private String password;
    private String name;
    private String phoneNum;
    private String address;
    private Double balance;

    public Account(String cardId, String password, String name, String phoneNum, String address, Double balance) {
        this.cardId = cardId;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.balance = balance;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "账户信息：" +
                "  \n卡号：" + cardId +
                "  \n姓名：'" + name +
                "  \n电话：'" + phoneNum +
                "  \n地址：'" + address +
                "  \n余额：" + balance;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
