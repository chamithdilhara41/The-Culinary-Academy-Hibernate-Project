package lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.RegistrationBO;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.RegistrationDAO;
import lk.ijse.theculinaryacademyhibernateproject.dao.DAOFactory;
import lk.ijse.theculinaryacademyhibernateproject.dto.RegistrationDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Registration;
import lk.ijse.theculinaryacademyhibernateproject.tm.StudentRegistrationTM;

import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);


    @Override
    public String generateRegistrationId() {
        return registrationDAO.generateNextRegistrationId();
    }

    @Override
    public void RegisterStudentDetails(RegistrationDTO registrationDTO) {
        registrationDAO.save(new Registration(
                registrationDTO.getRegistrationId(),
                registrationDTO.getStudentId(),
                registrationDTO.getProgramId(),
                registrationDTO.getRegistrationDate(),
                registrationDTO.getPaymentAmount()

        ));
    }

    @Override
    public List<StudentRegistrationTM> getAllRegisteredStudent() {

        return registrationDAO.getAllRegisteredStudent();
    }

    @Override
    public Registration searchByRegisterId(String registerId) {
        return registrationDAO.searchByRegisterId(registerId);
    }
}
