package Day2.Bank2;

import Day3.xkManager.Utils.JDBCUtils;

import java.sql.*;
import java.util.*;

/**
 * dao
 * Created by gjp06 on 17.3.16.
 */
class AccountDaoImpl {
    void insertAccount(Account a) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 插入新用户
        String sql = "INSERT INTO T_ACCOUNT VALUES (CARD_ID_SEQ.nextval,?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            update(ps, a);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取新用户id
        sql = "SELECT CARD_ID_SEQ.currval FROM dual";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString(1);
                // 设置用户id
                a.setCardId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, rs);
    }

    void deleteAccount(String cardId) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps;
        String sql = "DELETE FROM T_ACCOUNT WHERE CARD_ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cardId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateAccount(Account a) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE T_ACCOUNT SET PASSWORD = ?, NAME = ?, PHONE_NUM = ?, ADDRESS = ?, BALANCE = ? WHERE CARD_ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            update(ps, a);
            ps.setString(6, a.getCardId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, null);
    }

    Account selectAccountById(String cardId) {
        Account a = null;
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM T_ACCOUNT WHERE CARD_ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cardId);
            rs = ps.executeQuery();
            if (rs.next()) {
                a = new Account();
                a.setCardId(rs.getString("CARD_ID"));
                a.setPassword(rs.getString("PASSWORD"));
                a.setName(rs.getString("NAME"));
                a.setPhoneNum(rs.getString("PHONE_NUM"));
                a.setAddress(rs.getString("ADDRESS"));
                a.setBalance(rs.getDouble("BALANCE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, rs);
        return a;
    }

    List<Account> selectAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM T_ACCOUNT";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setCardId(rs.getString("CARD_ID"));
                a.setPassword(rs.getString("PASSWORD"));
                a.setName(rs.getString("NAME"));
                a.setPhoneNum(rs.getString("PHONE_NUM"));
                a.setAddress(rs.getString("ADDRESS"));
                a.setBalance(rs.getDouble("BALANCE"));
                accounts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, rs);
        return accounts;
    }

    private void update(PreparedStatement ps, Account a) throws Exception {
        ps.setString(1, a.getPassword());
        ps.setString(2, a.getName());
        ps.setString(3, a.getPhoneNum());
        ps.setString(4, a.getAddress());
        ps.setDouble(5, a.getBalance());
    }
}
