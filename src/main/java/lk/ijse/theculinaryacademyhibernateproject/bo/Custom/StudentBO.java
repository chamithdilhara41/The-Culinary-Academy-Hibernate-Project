package lk.ijse.theculinaryacademyhibernateproject.bo.Custom;

import lk.ijse.theculinaryacademyhibernateproject.bo.SuperBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudent();

    boolean updateStudent(StudentDTO studentDTO);

    String generateStudentId();

    boolean deleteStudent(String studentId);
}
