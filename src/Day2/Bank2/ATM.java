package Day2.Bank2;

import java.util.Scanner;

/**
 * atm
 * Created by gjp06 on 17.3.16.
 */
class ATM {
    private static final String address = "Beijing";
    private Scanner sc = new Scanner(System.in);
    private AccountDaoImpl adi = new AccountDaoImpl();

    void showMenu() {
        System.out.println("欢迎使用本系统");
        System.out.println("1. 开户");
        System.out.println("2. 登陆");
        System.out.println("3. 离开");
        System.out.print("请输入数字选择操作：");
        int cho = sc.nextInt();
        switch (cho) {
            case 1:
                signUp();
                break;
            case 2:
                login();
                break;
            case 3:
                System.exit(0);
        }
    }

    private void signUp() {
        String ck;
        Account a = new Account();
        while (true) {
            System.out.print("请输入你的姓名：");
            a.setName(sc.next());
            if (a.getName().equals("exit")) showMenu();
            System.out.print("请输入六位密码：");
            a.setPassword(sc.next());
            System.out.print("请再次输入密码：");
            ck = sc.next();
            System.out.print("请输入手机号码：");
            a.setPhoneNum(sc.next());
            System.out.print("请输入开户金额：");
            a.setBalance(sc.nextDouble());
            if (!a.getPassword().equals(ck)) System.err.println("两次输入的密码不一致！");
            else if (a.getPassword().length() != 6) System.err.println("密码长度不对！");
            else if (a.getBalance() < 0) System.err.println("开户金额不合法！");
            else if (a.getPhoneNum().length() > 11) System.err.println("手机号码错误！");
            else break;
        }
        a.setAddress(address);
        adi.insertAccount(a);
        System.out.println(a);
    }

    private void login() {
        Account a;
        String cardId;
        String pwd;
        do {
            System.out.print(" 请输入卡号：");
            cardId = sc.next();
            if (cardId.equals("-1")) showMenu();
            System.out.print(" 请输入密码：");
            pwd = sc.next();
            a = adi.selectAccountById(cardId);
            if (a == null) System.err.println("账号不存在");
            else if (!a.getPassword().equals(pwd)) System.err.println("密码不正确");
            else break;
        } while (true);
        do {
            System.out.println("欢迎使用ATM");
            System.out.println("1. 存款");
            System.out.println("2. 取款");
            System.out.println("3. 查询账户信息");
            System.out.println("4. 转账汇款");
            System.out.println("5. 修改密码");
            System.out.println("6. 注销账户");
            System.out.println("7. 退出登录");
            System.out.print("请输入数字选择操作：");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    deposit(a);
                    break;
                case 2:
                    withDraw(a);
                    break;
                case 3:
                    accountInfo(a);
                    break;
                case 4:
                    transfer(a);
                    break;
                case 5:
                    changePwd(a);
                    break;
                case 6:
                    cancelAccount(a);
                    break;
                case 7:
                    showMenu();
                    break;
                default:
                    break;
                case 425:
                    return;
            }
        } while (true);
    }

    private void deposit(Account a) {
        System.out.print("请输入存款金额：");
        double money = sc.nextDouble();
        if (money >= 0) {
            a.setBalance(a.getBalance() + money);
            System.out.println("存款成功，余额：" + a.getBalance());
        } else System.err.println("金额输入错误");
    }

    private void cancelAccount(Account a) {
        System.err.print("此操作不可取消，是否确认注销账户？ Y/N 是/否");
        String yes = sc.next();
        if (yes.startsWith("y") || yes.startsWith("Y") || yes.startsWith("是")) {
            adi.deleteAccount(a.getCardId());
            System.out.println("注销成功");
            showMenu();
        } else System.out.println("已停止操作");
    }

    private void changePwd(Account a) {
        System.out.print("请输入旧密码：");
        String oldPwd = sc.next();
        if (oldPwd.equals(a.getPassword())) {
            System.out.print("请输入新的六位密码：");
            String newPwd = sc.next();
            System.out.println("请再次输入：");
            String newPwd2 = sc.next();
            if (newPwd.length() == 6) {
                if (newPwd.equals(newPwd2)) {
                    a.setPassword(newPwd);
                    System.out.println("修改成功！");
                } else System.err.println("两次输入密码不一致！");
            } else System.err.println("新密码长度不合法！");
        } else System.err.println("密码输入错误！");
    }

    private void transfer(Account a) {
        System.out.print("请输入对方账户：");
        String pid = sc.next();
        Account p = adi.selectAccountById(pid);
        if (p != null) {
            System.out.print("请输入转账金额：");
            double money = sc.nextDouble();
            if (money > 0) {
                if (money < a.getBalance()) {
                    a.setBalance(p.getBalance() - money);
                    p.setBalance(p.getBalance() + money);
                    adi.updateAccount(a);
                    adi.updateAccount(p);
                    System.out.println("转账成功，余额：" + a.getBalance());
                } else System.err.println("余额不足");
            } else System.err.println("金额不合法");
        } else System.err.println("对方账户不存在");
    }

    private void withDraw(Account a) {
        do {
            System.out.print("请输入取款金额：");
            double money = sc.nextDouble();
            if (money < 0) System.err.println("金额输入错误");
            else if (money > a.getBalance()) System.err.println("余额不足");
            else {
                a.setBalance(a.getBalance() - money);
                adi.updateAccount(a);
                System.out.println("余额为：" + a.getBalance());
                break;
            }
        } while (true);
    }

    private void accountInfo(Account a) {
        System.out.println(a);
    }
}
