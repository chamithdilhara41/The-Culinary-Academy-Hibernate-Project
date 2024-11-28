package lk.ijse.theculinaryacademyhibernateproject.bo.Custom;

import lk.ijse.theculinaryacademyhibernateproject.bo.SuperBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.PaymentDTO;

public interface PaymentBO extends SuperBO {
    void savePayment(PaymentDTO paymentdto);

    String generatePaymentId();

}
