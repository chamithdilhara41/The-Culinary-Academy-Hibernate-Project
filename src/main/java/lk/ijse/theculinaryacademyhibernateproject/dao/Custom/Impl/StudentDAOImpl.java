package lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.config.FactoryConfiguration;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.StudentDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(dto.toString());
        session.save(dto);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Student dto) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a session and start a transaction
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Create query object
            Query query = session.createQuery("UPDATE Student " +
                    "SET DOB = :dob, " +
                    "    address = :address, " +
                    "    contact = :contact, " +
                    "    email = :email, " +
                    "    firstName = :firstName, " +
                    "    lastName = :lastName " +
                    "WHERE studentId = :studentId");

            // Set parameters
            query.setParameter("dob", dto.getDOB());
            query.setParameter("address", dto.getAddress());
            query.setParameter("contact", dto.getContact());
            query.setParameter("email", dto.getEmail());
            query.setParameter("firstName", dto.getFirstName());
            query.setParameter("lastName", dto.getLastName());
            query.setParameter("studentId", dto.getStudentId());

            // Execute the update query
            int result = query.executeUpdate();

            // Commit the transaction
            transaction.commit();

            // Check if any rows were updated
            return result > 0;

        } catch (Exception e) {
            // Rollback the transaction if something goes wrong
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;

        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }
    }
    @Override
    public String generateNextStudentId() {
        Session session = null;

        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // HQL query to get the maximum studentId
            String hql = "SELECT MAX(studentId) FROM Student";
            Query query = session.createQuery(hql);

            // Get the result (e.g., "ST0001")
            String lastStudentId = (String) query.uniqueResult();

            if (lastStudentId != null) {
                // Extract the numeric part and increment it
                int nextId = Integer.parseInt(lastStudentId.substring(2)) + 1;

                // Format the new ID with leading zeros (e.g., "ST0002")
                return String.format("ST%04d", nextId);
            } else {
                // If no IDs exist, start with "ST0001"
                return "ST0001";
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
    public List<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> students = session.createQuery("FROM Student", Student.class).list();

        transaction.commit();
        session.close();

        return students;
    }
}
