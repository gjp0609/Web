package Day2.User;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

/**
 * Created by gjp06 on 17.3.16.
 */
public class MyTestClass {

    @Test
    public void testJDBC() {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testInsertUser() {
        UserDaoImpl usi = new UserDaoImpl();
        User u = new User();
        u.setName("zhangSan");
        u.setAge(18);
        u.setSex("F");
        u.setEmail("123@163.com");
        u.setAddress("Beijing");
        u.setPassword("hello");
        usi.insertUser(u);
        System.out.println(u);
    }

    @Test
    public void testDeleteUser() {
        String id = "100";
        UserDaoImpl usi = new UserDaoImpl();
        usi.deleteUser(id);
    }

    @Test
    public void testUpdateUser() {
        UserDaoImpl usi = new UserDaoImpl();
        User u = new User();
        u.setId("106");
        u.setName("123");
        u.setAge(99);
        u.setSex("F");
        u.setEmail("123@163.com");
        u.setAddress("Bei Jing");
        u.setPassword("hello");
        usi.updateUser(u);
    }

    @Test
    public void testSelectUserById() {
        UserDaoImpl usi = new UserDaoImpl();
        usi.selectUserById("100");
    }

    @Test
    public void testSelectAllUsers() {
        UserDaoImpl usi = new UserDaoImpl();
        List<User> users = usi.selectAllUser();
        for (User u : users) {
            System.out.println(u);
        }
    }
}
