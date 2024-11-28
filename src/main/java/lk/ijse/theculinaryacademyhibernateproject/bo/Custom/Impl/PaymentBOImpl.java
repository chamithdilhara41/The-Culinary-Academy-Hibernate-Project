package lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.PaymentBO;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.PaymentDAO;
import lk.ijse.theculinaryacademyhibernateproject.dao.DAOFactory;
import lk.ijse.theculinaryacademyhibernateproject.dto.PaymentDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Payment;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public void savePayment(PaymentDTO paymentdto) {
        paymentDAO.save(new Payment(
                paymentdto.getPay_id(),
                paymentdto.getPay_date(),
                paymentdto.getBalance_amount(),
                paymentdto.getPay_amount(),
                paymentdto.getUpfront_amount(),
                paymentdto.getDescription(),
                paymentdto.getStudentId(),
                paymentdto.getRegistrationId()
                ));
    }

    @Override
    public String generatePaymentId() {
        return paymentDAO.generateNextPaymentId();
    }
}
