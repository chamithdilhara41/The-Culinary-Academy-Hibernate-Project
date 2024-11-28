package lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl;

import javafx.collections.ObservableList;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.StudentBO;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.StudentDAO;
import lk.ijse.theculinaryacademyhibernateproject.dao.DAOFactory;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    
    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getStudentId(), studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getAddress(), studentDTO.getEmail(), studentDTO.getContact(), studentDTO.getDOB()));
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : students) {
            studentDTOList.add(
                    new StudentDTO(
                            student.getStudentId(),
                            student.getFirstName(),
                            student.getLastName(),
                            student.getAddress(),
                            student.getContact(),
                            student.getEmail(),
                            student.getDOB()
                    )
            );
        }
        return studentDTOList;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(new Student(studentDTO.getStudentId(), studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getAddress(), studentDTO.getEmail(), studentDTO.getContact(), studentDTO.getDOB()));
    }

    @Override
    public String generateStudentId() {
        return studentDAO.generateNextStudentId();
    }

    @Override
    public boolean deleteStudent(String studentId) {
        return studentDAO.delete(studentId);
    }

    @Override
    public ObservableList<String> getContacts() {
        return studentDAO.getContactNo();
    }

    @Override
    public StudentDTO getStudent(String selectedContact) {
        return studentDAO.getStudentForReg(selectedContact);
    }

    @Override
    public Student searchStudentById(String studentId) {
        return studentDAO.searchStudentById(studentId);
    }
}
