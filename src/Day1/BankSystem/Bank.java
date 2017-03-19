package Day1.BankSystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by gjp06 on 17.3.15.
 */
class Bank {
    private Scanner sc = new Scanner(System.in);
    private PreparedStatement ps = null;
    private User u = null;
    private String sql = null;
    private ResultSet rs = null;

    void showMenu() {
        u = new User();
        System.out.println("欢迎使用本系统");
        System.out.println("1. 登陆");
        System.out.println("2. 开户");
        System.out.println("3. 离开");
        System.out.print("请输入数字选择操作：");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
            case 3:
                System.exit(0);
        }

    }

    private void signup() {
        String ck;
        while (true) {
            System.out.print("请输入你的姓名： ");
            u.setName(sc.next());
            System.out.print("请输入六位密码： ");
            u.setPassword(sc.next());
            System.out.print("请再次输入密码： ");
            ck = sc.next();
            System.out.print("请输入手机号码： ");
            u.setPhone(sc.next());
            System.out.print("请输入开户金额： ");
            u.setBalance(sc.nextDouble());
            if (!u.getPassword().equals(ck)) System.out.println("两次输入的密码不匹配！ ");
            else if (u.getPassword().length() != 6) System.out.println("密码长度不对！ ");
            else if (u.getBalance() < 0) System.out.println("开户金额不合法！ ");
            else break;
        }
        sql = "INSERT INTO USERS VALUES (USERS_SEQ.nextval, ?, ?, ?, ?)";

        ps = Connector.getPS(sql);
        try {
            ps.setString(1, u.getName());
            ps.setString(2, u.getPassword());
            ps.setDouble(4, u.getBalance());
            ps.setString(3, u.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String id = select("USERS_SEQ.currval", "dual", null);
        System.out.println("开户成功，账户为：" + id);
        showMenu();
    }

    private boolean correct(User u) {
        sql = "SELECT PASSWORD FROM USERS WHERE ID = ?";
        ps = Connector.getPS(sql);
        try {
            ps.setString(1, u.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (u.getPassword().equals(rs.getString(1))) return true;
                else System.out.println("密码错误");
            } else {
                System.out.println("不存在此账户");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void login() {
        while (true) {
            System.out.print("请输入卡号：");
            u.setId(sc.next());
            System.out.print("请输入密码：");
            u.setPassword(sc.next());
            if (correct(u)) break;
        }
        u.setName(select("name", "USERS", u.getId()));
        u.setPhone(select("phone", "USERS", u.getId()));
        u.setBalance(Double.valueOf(select("balance", "USERS", u.getId())));
        while (true) {
            System.out.println("欢迎登陆");
            System.out.println("1. 存款");
            System.out.println("2. 取款");
            System.out.println("3. 查询余额");
            System.out.println("4. 转账汇款");
            System.out.println("5. 修改密码");
            System.out.println("6. 注销账户");
            System.out.println("7. 退出登录");
            System.out.print("请输入数字选择操作：");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    changePwd();
                    break;
                case 6:
                    cancel();
                    break;
                case 7:
                    showMenu();
                    break;
                default:
                    break;
            }
        }


    }

    // 注销账户
    private void cancel() {
        sql = "DELETE FROM USERS WHERE ID = '" + u.getId() + "'";
        ps = Connector.getPS(sql);
        try {
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("注销成功");
                showMenu();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 改密码
    private void changePwd() {

        System.out.print("请输入旧密码：");
        String opwd = sc.next();
        System.out.print("请输入新密码:");
        String npwd = sc.next();
        System.out.print("请再次输入新密码：");
        String npwd2 = sc.next();
        if (opwd.equals(u.getPassword()))
            if (npwd.equals(npwd2)) {
                sql = "UPDATE USERS SET PASSWORD = ? WHERE ID = ?";
                ps = Connector.getPS(sql);
                try {
                    ps.setString(1, npwd);
                    ps.setString(2, u.getId());
                    int count = ps.executeUpdate();
                    if (count == 1) {
                        u.setPassword(npwd);
                        System.out.println("修改成功");
                    } else System.out.println("修改失败 ");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else System.out.println("两次密码不一致");
        else System.out.println("密码不正确");
    }

    // 转账
    private void transfer() {
        String pid;
        String idd;
        while (true) {
            System.out.print("请输入对方账户：");
            pid = sc.next();
            idd = select("id", "USERS", pid);
            if (idd != null) {
                System.out.print("请输入转账金额： ");
                double mon = sc.nextDouble();
                if (mon > 0) {
                    if (mon < u.getBalance()) {
                        try {
                            sql = "UPDATE USERS SET BALANCE = " + (u.getBalance() - mon) + " WHERE ID = " + u.getId();
                            ps = Connector.getPS(sql);
                            int count1 = ps.executeUpdate();
                            sql = "UPDATE USERS SET BALANCE = " + (u.getBalance() + mon) + " WHERE ID = " + pid;
                            ps = Connector.getPS(sql);
                            int count2 = ps.executeUpdate();
                            if (count1 == 1 && count2 == 1) {
                                u.setBalance(u.getBalance() - mon);
                                System.out.println(" 转账成功，余额：" + u.getBalance());
                                break;
                            } else {
                                System.out.println("转账失败");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else System.out.println("余额不足");
                } else System.out.println("金额不合法");
            } else System.out.println("对方账户不存在");
        }
    }

    // 查询
    private void search() {
        System.out.println("账号：" + u.getId() + "\n姓名："
                + u.getName() + "\n电话：" + u.getPhone() + "\n余额：" + u.getBalance());
    }

    // 取款
    private void withdraw() {
        double money;
        while (true) {
            System.out.print("请输入取款金额：");
            money = sc.nextDouble();
            if (money > 0)
                if (money > u.getBalance())
                    System.out.println("余额不足 。");
                else break;
        }
        sql = "UPDATE USERS SET BALANCE = ? WHERE ID = ? ";
        try {
            ps = Connector.getPS(sql);
            ps.setString(2, u.getId());
            ps.setDouble(1, (u.getBalance() - money));
            int count = ps.executeUpdate();
            if (count == 0) System.out.println("取款失败 ");
            else {
                u.setBalance(u.getBalance() - money);
                System.out.println("取款成功,  当前余额：" + u.getBalance());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 存款

    private void deposit() {
        double money;
        do {
            System.out.print("请输入存款金额 ：");
            money = sc.nextDouble();
        } while (money < 0);
        sql = "UPDATE USERS SET BALANCE = " + (u.getBalance() + money) + " WHERE ID = " + u.getId();
        ps = Connector.getPS(sql);
        try {
            int count = ps.executeUpdate();
            if (count == 0) System.out.println("存款失败 ");
            else {
                u.setBalance(u.getBalance() + money);
                System.out.println("存款成功, 当前余额 ：" + u.getBalance());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String select(String word, String table, String where) {
        if (where == null) sql = "SELECT " + word + " FROM " + table;
        else sql = "SELECT " + word + " FROM " + table + " WHERE ID = " + where;
//        System.out.println(sql);
        String res = null;
        try {
            ps = Connector.getPS(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
