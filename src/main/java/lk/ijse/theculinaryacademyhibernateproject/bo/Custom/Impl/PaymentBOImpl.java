package lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.PaymentBO;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.PaymentDAO;
import lk.ijse.theculinaryacademyhibernateproject.dao.DAOFactory;
import lk.ijse.theculinaryacademyhibernateproject.dto.PaymentDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Payment;
import lk.ijse.theculinaryacademyhibernateproject.tm.PaymentTm;

import java.util.ArrayList;
import java.util.List;

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
                paymentdto.getPayment_type(),
                paymentdto.getStudentId(),
                paymentdto.getRegistrationId()
                ));
    }

    @Override
    public String generatePaymentId() {
        return paymentDAO.generateNextPaymentId();
    }

    @Override
    public List<PaymentTm> getAllPayments() {
        List<PaymentTm> students = paymentDAO.getAllPayments();
        List<PaymentTm> studentDTOList = new ArrayList<>();
        for (PaymentTm payment : students) {
            studentDTOList.add(
                    new PaymentTm(
                            payment.getPay_id(),
                            payment.getPay_date(),
                            payment.getBalance_amount(),
                            payment.getPay_amount(),
                            payment.getUpfront_amount(),
                            payment.getDescription(),
                            payment.getPayment_type(),
                            payment.getStudent_id(),
                            payment.getRegistration_id()
                    )
            );
        }
        return studentDTOList;
    }

    @Override
    public Payment searchPaymentId(String paymentID) {
        return paymentDAO.searchByPaymentId(paymentID);
    }

    @Override
    public Payment searchRegisterId(String registerId) {
        return paymentDAO.searchByRegisterId(registerId);
    }

    @Override
    public void updatePayment(String payId, double upfrontAmount, double payAmount) {
        paymentDAO.updatePayment(payId,upfrontAmount,payAmount);
    }
}
