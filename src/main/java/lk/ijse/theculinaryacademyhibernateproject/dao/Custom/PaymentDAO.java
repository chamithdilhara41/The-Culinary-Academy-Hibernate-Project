package lk.ijse.theculinaryacademyhibernateproject.dao.Custom;

import lk.ijse.theculinaryacademyhibernateproject.dao.CrudDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Payment;
import lk.ijse.theculinaryacademyhibernateproject.tm.PaymentTm;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    String generateNextPaymentId();

    List<PaymentTm> getAllPayments();

    Payment searchByPaymentId(String paymentID);

    Payment searchByRegisterId(String registerId);

    void updatePayment(String payId, double upfrontAmount, double payAmount);
}
