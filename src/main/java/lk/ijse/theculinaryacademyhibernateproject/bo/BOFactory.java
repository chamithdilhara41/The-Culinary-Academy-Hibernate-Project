package lk.ijse.theculinaryacademyhibernateproject.bo;

import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl.ProgramBOImpl;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl.StudentBOImpl;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        USER,STUDENT,PROGRAM
    }

    public SuperBO getBO(BOTypes boType) {

        switch (boType) {
            case USER -> {
                return new UserBOImpl();
            }
            case STUDENT -> {
                return new StudentBOImpl();
            }
            case PROGRAM -> {
                return new ProgramBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
