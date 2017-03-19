package Day1.TestJDBC;

import java.sql.*;

/**
 * Created by gjp06 on 17.3.15.
 */
public class TestConn {
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "123123");
        String sql = "select * from employees where employee_id = 102";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String[] s = new String[12];
        if (rs != null && rs.next()) {
            for (int i = 1; i < 11; i++) {
                System.out.println(rs.getString(i));
                s[i] = rs.getString(i);
            }
        }
        rs.close();
        ps.close();
        conn.close();
//		for (String string : s) {
//			System.out.println(string);
//		}
    }
}
