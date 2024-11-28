package lk.ijse.theculinaryacademyhibernateproject.bo;

import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        USER,STUDENT,PROGRAM,PAYMENT,REGISTRATION
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
            case REGISTRATION -> {
                return new RegistrationBOImpl();
            }
            case PAYMENT -> {
                return new PaymentBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
