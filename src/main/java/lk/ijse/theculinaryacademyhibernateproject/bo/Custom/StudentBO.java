package lk.ijse.theculinaryacademyhibernateproject.bo.Custom;

import javafx.collections.ObservableList;
import lk.ijse.theculinaryacademyhibernateproject.bo.SuperBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudent();

    boolean updateStudent(StudentDTO studentDTO);

    String generateStudentId();

    boolean deleteStudent(String studentId);

    ObservableList<String> getContacts();

    StudentDTO getStudent(String selectedContact);

    Student searchStudentById(String studentId);
}
