package Day3.xkManager.Utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * utils
 * Created by gjp06 on 17.3.16.
 */
public class JDBCUtils {
    private static final Properties prop = new Properties();
    private static final ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        try {
            String src = "/Day3/xkManager/Utils/jdbc.properties";
            prop.load(JDBCUtils.class.getResourceAsStream(src));
            Class.forName(prop.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        Connection conn = tl.get();
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, username, password);
                tl.set(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement getPS(String sql) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean rollback() {
        Connection conn = tl.get();
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } else return false;
    }

    public static boolean commit() {
        Connection conn = tl.get();
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } else return false;
    }
}
