package lk.ijse.theculinaryacademyhibernateproject.bo.Custom;

import lk.ijse.theculinaryacademyhibernateproject.bo.SuperBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.PaymentDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Payment;
import lk.ijse.theculinaryacademyhibernateproject.tdm.PaymentTm;

import java.util.List;

public interface PaymentBO extends SuperBO {
    void savePayment(PaymentDTO paymentdto);

    String generatePaymentId();

    List<PaymentTm> getAllPayments();

    Payment searchPaymentId(String paymentID);

    Payment searchRegisterId(String registerId);

    void updatePayment(String payId, double upfrontAmount, double payAmount);
}
