package Day3.TestThread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by gjp06 on 17.3.17.
 */
public class Utils {
    static {

        try {
            Class.forName("oracle.jdbc.OracleDriver");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "123123");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
