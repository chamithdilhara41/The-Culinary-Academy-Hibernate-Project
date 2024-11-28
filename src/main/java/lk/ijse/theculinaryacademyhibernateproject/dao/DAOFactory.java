package lk.ijse.theculinaryacademyhibernateproject.dao;

import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            return daoFactory = new DAOFactory();
        }else {
            return daoFactory;
        }
    }

    public enum DAOTypes{
        USER,STUDENT,PROGRAM,REGISTRATION,PAYMENT
    }

    public SuperDAO getDAO(DAOTypes type) {

        switch (type) {
            case USER:return new UserDAOImpl();
            case STUDENT:return new StudentDAOImpl();
            case PROGRAM:return new ProgramDAOImpl();
            case REGISTRATION:return new RegistrationDAOImpl();
            case PAYMENT:return new PaymentDAOImpl();
            default:return null;
        }

    }
}
