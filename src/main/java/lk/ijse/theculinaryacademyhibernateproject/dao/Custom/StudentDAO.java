package lk.ijse.theculinaryacademyhibernateproject.dao.Custom;

import lk.ijse.theculinaryacademyhibernateproject.dao.CrudDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;

public interface StudentDAO extends CrudDAO<Student>{

    String generateNextStudentId();

    boolean delete(String studentId);
}
