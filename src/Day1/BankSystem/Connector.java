package Day1.BankSystem;

import java.sql.*;

/**
 * Created by gjp06 on 17.3.15.
 */
class Connector {
    private static Connection conn = null;
    private static PreparedStatement ps = null;

    static Connection getConnection() {
        ResultSet rs = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, "hr", "123123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    static PreparedStatement getPS(String sql) {
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

//    public static
}
