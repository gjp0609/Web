package Day3.xkManager.Dao;

import Day3.xkManager.Entity.Student;
import Day3.xkManager.Utils.JDBCUtils;

import java.sql.*;
import java.util.List;

/**
 * SID
 * NAME
 * SEX
 * ID_CARD
 * EMAIL
 * ADDRESS
 * Created by gjp06 on 17.3.17.
 */
public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean insertStudent(Student s) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "INSERT INTO T_STUDENT VALUES (STUDENT_SEQ.nextval,?,?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getSex());
            ps.setString(3, s.getIdCard());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getAddress());
            ps.executeUpdate();
            sql = "SELECT STUDENT_SEQ.currval FROM dual";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            String id = null;
            if (rs.next()) {
                id = rs.getString(1);
                s.setId(id);
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return true;
    }

    @Override
    public boolean deleteStudent(String sid) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "DELETE FROM T_STUDENT WHERE SID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, null);
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student s) {
        return false;
    }

    @Override
    public Student selectStudent(String id) {
        return null;
    }

    @Override
    public List<Student> selectAllStudents() {
        return null;
    }
}
