package lk.ijse.theculinaryacademyhibernateproject.bo;

import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl.StudentBOImpl;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        USER,STUDENT
    }

    public SuperBO getBO(BOTypes boType) {

        switch (boType) {
            case USER -> {
                return new UserBOImpl();
            }
            case STUDENT -> {
                return new StudentBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
