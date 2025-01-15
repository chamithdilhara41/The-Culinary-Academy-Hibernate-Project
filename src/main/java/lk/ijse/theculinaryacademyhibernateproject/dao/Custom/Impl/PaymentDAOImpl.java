package lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.config.FactoryConfiguration;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.PaymentDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Payment;
import lk.ijse.theculinaryacademyhibernateproject.tm.PaymentTm;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(dto.toString());
        session.save(dto);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Payment dto) {
        return false;
    }

    @Override
    public boolean delete(Payment dto) {
        return false;
    }

    @Override
    public List<Payment> getAll() {
        return List.of();
    }


    @Override
    public String generateNextPaymentId() {
        Session session = null;

        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // HQL query to get the maximum registrationId
            String hql = "SELECT MAX(pay_id) FROM Payment ";  // Assuming the table name is "Registration"
            Query query = session.createQuery(hql);

            // Get the result (e.g., "RG0001")
            String lastRegistrationId = (String) query.uniqueResult();

            if (lastRegistrationId != null) {
                // Extract the numeric part and increment it
                int nextId = Integer.parseInt(lastRegistrationId.substring(2)) + 1;

                // Format the new ID with leading zeros (e.g., "RG0002")
                return String.format("PA%04d", nextId);
            } else {
                // If no IDs exist, start with "RG0001"
                return "PA0001";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of failure
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public List<PaymentTm> getAllPayments() {
        Session session = null;
        List<PaymentTm> paymentList = new ArrayList<>();

        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // HQL query to fetch all payment records
            String hql = "SELECT p.pay_id, p.balance_amount, p.pay_amount, p.pay_date, p.upfront_amount, " +
                    "p.registration.RegistrationID, p.registration.student.studentId, " +
                    "p.description, p.payment_type " +
                    "FROM Payment p";

            Query query = session.createQuery(hql);

            // Execute query and get results
            List<Object[]> results = query.list();

            // Map results to PaymentTm objects
            for (Object[] row : results) {
                String payId = (String) row[0];
                double balanceAmount = (Double) row[1];
                double payAmount = (Double) row[2];
                String payDate = (String) row[3];
                double upfrontAmount = (Double) row[4];
                String registrationId = (String) row[5];
                String studentId = (String) row[6];
                String description = (String) row[7];
                String paymentType = (String) row[8];

                PaymentTm paymentTm = new PaymentTm(
                        payId, payDate, balanceAmount, payAmount, upfrontAmount, description, registrationId, studentId,paymentType
                );
                paymentList.add(paymentTm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return paymentList;
    }

    @Override
    public Payment searchByPaymentId(String paymentID) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Open a Hibernate session

            // HQL query to find the student with the specified studentId
            String hql = "FROM Payment WHERE pay_id = :pay_id";
            Query<Payment> query = session.createQuery(hql, Payment.class);
            query.setParameter("pay_id", paymentID);

            // Return the student if found
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of failure
        }
    }

    @Override
    public Payment searchByRegisterId(String registerId) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Open a Hibernate session

            // HQL query to find the payment with the specified registration ID
            String hql = "FROM Payment WHERE registration.RegistrationID = :registrationID";
            Query<Payment> query = session.createQuery(hql, Payment.class);
            query.setParameter("registrationID", registerId);

            // Return the payment if found
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of failure
        }
    }

    @Override
    public void updatePayment(String payId, double upfrontAmount, double payAmount) {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // HQL query to update the Payment
            String hql = "UPDATE Payment " +
                    "SET upfront_amount = upfront_amount - :payAmount, " +
                    "pay_amount = pay_amount + :payAmount " +
                    "WHERE pay_id = :payId";
            Query query = session.createQuery(hql);
            query.setParameter("payAmount", payAmount);
            query.setParameter("payId", payId);

            // Execute the update
            int rowsAffected = query.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Payment updated successfully for Pay ID: " + payId);
            } else {
                System.out.println("No payment found with Pay ID: " + payId);
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if something goes wrong
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

}
