package Day3.xkManager.UTest;

import Day3.xkManager.Utils.JDBCUtils;

import java.sql.Connection;

/**
 * Created by gjp06 on 17.3.16.
 */
public class TestUtils {
    public static void main(String[] args) {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
        JDBCUtils.release(conn, null, null);
    }
}
