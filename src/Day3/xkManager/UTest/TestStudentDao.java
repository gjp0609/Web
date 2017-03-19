package Day3.xkManager.UTest;

import Day3.xkManager.Dao.StudentDaoImpl;
import Day3.xkManager.Entity.Student;
import Day3.xkManager.Utils.DateUtils;
import org.junit.jupiter.api.Test;

/**
 * ----
 * Created by gjp06 on 17.3.19.
 */
public class TestStudentDao {
    private Student s = new Student(null, "name",
            "FM", "342154199906218888", "123@163.con", "HaiDian");

    @Test
    public void testInsert() {
        StudentDaoImpl sdi = new StudentDaoImpl();
        boolean b = sdi.insertStudent(s);
        System.out.println(b);
        System.out.println(s);
        System.out.println(DateUtils.getDate(s.getIdCard()));
    }
}
