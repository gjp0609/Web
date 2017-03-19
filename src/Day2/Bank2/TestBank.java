package Day2.Bank2;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * AccountDAO
 * Created by gjp06 on 17.3.16.
 */
public class TestBank {
    private AccountDaoImpl adi = new AccountDaoImpl();
    private Account a = null;
    private String cardId = null;
    private String password = "123123";
    private String name = "gjp";
    private String phoneNum = "17643575457";
    private String address = "Beijing";
    private Double balance = 3000.0;

    @Test
    public void testInsert() {
        a = new Account(cardId, password, name, phoneNum, address, balance);
        adi.insertAccount(a);
        System.out.println(a);
    }

    @Test
    public void testDelete() {
        String cardId = "622001";
        adi.deleteAccount(cardId);
    }

    @Test
    public void testUpdate() {
        cardId = "622022";
        a = new Account(cardId, password, name, phoneNum, address, balance - 200);
        adi.updateAccount(a);
    }

    @Test
    public void testSelectAccountByCardId() {
        cardId = "622021";
        a = adi.selectAccountById(cardId);
        System.out.println(a);
    }

    @Test
    public void testSelectAllAccounts() {
        List<Account> accounts = adi.selectAllAccounts();
        for (Account a : accounts) {
            System.out.println(a);
        }
    }


    public static void main(String[] args) {
        ATM a=new ATM();
        a.showMenu();
    }
}
