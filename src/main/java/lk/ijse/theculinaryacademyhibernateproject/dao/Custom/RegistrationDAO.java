package lk.ijse.theculinaryacademyhibernateproject.dao.Custom;

import lk.ijse.theculinaryacademyhibernateproject.dao.CrudDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Registration;
import lk.ijse.theculinaryacademyhibernateproject.tdm.StudentRegistrationTM;

import java.util.List;

public interface RegistrationDAO extends CrudDAO<Registration> {
    String generateNextRegistrationId();

    List<StudentRegistrationTM> getAllRegisteredStudent();

    Registration searchByRegisterId(String registerId);
}
