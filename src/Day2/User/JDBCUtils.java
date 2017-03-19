package Day2.User;

import java.sql.*;
import java.util.Properties;

/**
 * Created by gjp06 on 17.3.16.
 */
public class JDBCUtils {

    private static final Properties prop = new Properties();

    static {
        String driver = null;
        try {
            prop.load(JDBCUtils.class.getResourceAsStream("/Day2/User/jdbc.properties"));
            driver = prop.getProperty("driver");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
