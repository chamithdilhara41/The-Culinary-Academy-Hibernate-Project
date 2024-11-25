package lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.config.FactoryConfiguration;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.ProgramDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Program;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean save(Program dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(dto.toString());
        session.save(dto);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Program dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(dto.toString());
        session.update(dto);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(Program dto) {
        return false;
    }

    @Override
    public List<Program> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Program> programs = session.createQuery("FROM Program ", Program.class).list();

        transaction.commit();
        session.close();

        return programs;
    }

    @Override
    public String generateNextProgramId() {
        Session session = null;

        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // HQL query to get the maximum programId
            String hql = "SELECT MAX(programId) FROM Program"; // Adjust table/entity name as needed
            Query query = session.createQuery(hql);

            // Get the result (e.g., "CA1001")
            String lastProgramId = (String) query.uniqueResult();

            if (lastProgramId != null) {
                // Extract the numeric part and increment it
                int nextId = Integer.parseInt(lastProgramId.substring(2)) + 1;

                // Format the new ID with leading zeros (e.g., "CA1002")
                return String.format("CA%04d", nextId);
            } else {
                // If no IDs exist, start with "CA1001"
                return "CA1001";
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
