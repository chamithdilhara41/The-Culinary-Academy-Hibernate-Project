package lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.config.FactoryConfiguration;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.RegistrationDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Registration;
import lk.ijse.theculinaryacademyhibernateproject.tdm.StudentRegistrationTM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(dto.toString());
        session.save(dto);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Registration dto) {
        return false;
    }

    @Override
    public boolean delete(Registration dto) {
        return false;
    }

    @Override
    public List<Registration> getAll() {
        return List.of();
    }

    @Override
    public String generateNextRegistrationId() {
        Session session = null;

        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // HQL query to get the maximum registrationId
            String hql = "SELECT MAX(RegistrationID) FROM Registration";  // Assuming the table name is "Registration"
            Query query = session.createQuery(hql);

            // Get the result (e.g., "RG0001")
            String lastRegistrationId = (String) query.uniqueResult();

            if (lastRegistrationId != null) {
                // Extract the numeric part and increment it
                int nextId = Integer.parseInt(lastRegistrationId.substring(2)) + 1;

                // Format the new ID with leading zeros (e.g., "RG0002")
                return String.format("RG%04d", nextId);
            } else {
                // If no IDs exist, start with "RG0001"
                return "RG0001";
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
    public List<StudentRegistrationTM> getAllRegisteredStudent() {
        Session session = null;
        List<StudentRegistrationTM> registeredStudents = new ArrayList<>();

        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // HQL query to join tables and fetch required columns
            String hql = "SELECT r.RegistrationID, s.studentId, s.firstName, s.lastName, " +
                    "p.programName, s.contact, r.registrationDate " +
                    "FROM Registration r " +
                    "JOIN r.student s " +
                    "JOIN r.program p";

            Query query = session.createQuery(hql);

            // Execute the query and get the result list
            List<Object[]> results = query.list();

            // Iterate over the results and create StudentRegistrationTM objects
            for (Object[] row : results) {
                String registrationId = (String) row[0];
                String studentId = (String) row[1];
                String firstName = (String) row[2];
                String lastName = (String) row[3];
                String programName = (String) row[4];
                String contact = (String) row[5];
                String registrationDate = row[6].toString();

                // Create StudentRegistrationTM object and add it to the list
                StudentRegistrationTM studentRegistrationTM = new StudentRegistrationTM(
                        registrationId, studentId, firstName, lastName, programName, contact, registrationDate
                );

                registeredStudents.add(studentRegistrationTM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }

        return registeredStudents;
    }

    @Override
    public Registration searchByRegisterId(String registerId) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Open a Hibernate session

            // HQL query to find the student with the specified studentId
            String hql = "FROM Registration WHERE RegistrationID = :RegistrationID";
            Query<Registration> query = session.createQuery(hql, Registration.class);
            query.setParameter("RegistrationID", registerId);

            // Return the student if found
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of failure
        }
    }


}
