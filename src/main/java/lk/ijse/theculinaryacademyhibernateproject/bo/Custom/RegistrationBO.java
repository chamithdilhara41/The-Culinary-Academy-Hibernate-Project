package lk.ijse.theculinaryacademyhibernateproject.bo.Custom;

import lk.ijse.theculinaryacademyhibernateproject.bo.SuperBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.RegistrationDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Registration;
import lk.ijse.theculinaryacademyhibernateproject.tdm.StudentRegistrationTM;

import java.util.List;

public interface RegistrationBO extends SuperBO {

    String generateRegistrationId();

    void RegisterStudentDetails(RegistrationDTO registrationDTO);

    List<StudentRegistrationTM> getAllRegisteredStudent();

    Registration searchByRegisterId(String registerId);
}
