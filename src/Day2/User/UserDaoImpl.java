package Day2.User;

import java.sql.*;
import java.util.*;

/**
 * Created by gjp06 on 17.3.16.
 */
public class UserDaoImpl {
    /**
     * id     * name     * age     * sex     * email     * address
     *
     * @param u
     */
    void insertUser(User u) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 插入新用户
        String sql = "INSERT INTO t_user VALUES (user_seq.nextval, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setInt(2, u.getAge());
            ps.setString(3, u.getSex());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getAddress());
            ps.setString(6, u.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 获取新用户id
        sql = "SELECT user_seq.currval FROM dual";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString(1);
                // 设置用户id
                u.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, rs);
    }

    void deleteUser(String id) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_user WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User u) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE t_user SET name = ?, age = ?, sex = ?, email = ?, address = ?, PASSWORD = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setInt(2, u.getAge());
            ps.setString(3, u.getSex());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getAddress());
            ps.setString(6, u.getPassword());
            ps.setString(7, u.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, null);
    }

    User selectUserById(String id) {
        User u = null;
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM t_user WHERE ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setAge(rs.getInt("age"));
                u.setSex(rs.getString("sex"));
                u.setEmail(rs.getString("email"));
                u.setAddress(rs.getString("address"));
                u.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, rs);
        return u;
    }

    List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM t_user";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setAge(rs.getInt("age"));
                u.setSex(rs.getString("sex"));
                u.setEmail(rs.getString("email"));
                u.setAddress(rs.getString("address"));
                u.setPassword(rs.getString("password"));
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, rs);
        return users;
    }
}
