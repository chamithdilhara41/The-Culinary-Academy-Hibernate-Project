package lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.config.FactoryConfiguration;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.PaymentDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
}
