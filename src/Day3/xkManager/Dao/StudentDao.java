package Day3.xkManager.Dao;

import Day3.xkManager.Entity.Student;

import java.util.List;

/**
 * Created by gjp06 on 17.3.17.
 */
public interface StudentDao {
    public boolean insertStudent(Student s);

    public boolean deleteStudent(String sid);

    public boolean updateStudent(Student s);

    public Student selectStudent(String id);

    public List<Student> selectAllStudents();
}
