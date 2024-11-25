package lk.ijse.theculinaryacademyhibernateproject.dao;

import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl.UserDAOImpl;

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
        USER,STUDENT
    }

    public SuperDAO getDAO(DAOTypes type) {

        switch (type) {
            case USER:return new UserDAOImpl();
            case STUDENT:return new StudentDAOImpl();
            default:return null;
        }

    }
}
