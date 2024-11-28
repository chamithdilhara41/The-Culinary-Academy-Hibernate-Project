package lk.ijse.theculinaryacademyhibernateproject.dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.theculinaryacademyhibernateproject.dao.CrudDAO;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;

public interface StudentDAO extends CrudDAO<Student>{

    String generateNextStudentId();

    boolean delete(String studentId);

    ObservableList<String> getContactNo();

    StudentDTO getStudentForReg(String selectedContact);

    Student searchStudentById(String studentId);
}
