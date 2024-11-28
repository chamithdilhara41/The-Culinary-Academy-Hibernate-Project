package lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.theculinaryacademyhibernateproject.config.FactoryConfiguration;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.StudentDAO;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;
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
    public boolean delete(Student dto) {
        return false;
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
    public boolean delete(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Student student = session.get(Student.class, studentId);

            if (student != null) {
                //CascadeType.REMOVE,then can delete student
                session.remove(student);
                transaction.commit();
                return true;
            } else {
                return false; // Entity not found
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public ObservableList<String> getContactNo() {
        Session session = null;
        ObservableList<String> contactNumbers = FXCollections.observableArrayList();

        try {
            // Open a Hibernate session
            session = FactoryConfiguration.getInstance().getSession();

            // HQL query to fetch contact numbers from Student table
            String hql = "SELECT s.contact FROM Student s";
            Query<String> query = session.createQuery(hql, String.class);

            // Fetch the result list and add to the ObservableList
            List<String> result = query.getResultList();
            contactNumbers.addAll(result);

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        } finally {
            if (session != null) {
                session.close(); // Ensure session is closed
            }
        }

        return contactNumbers;
    }

    @Override
    public StudentDTO getStudentForReg(String selectedContact) {
        Session session = null;
        try {
            // Open a Hibernate session
            session = FactoryConfiguration.getInstance().getSession();

            // HQL query to find the student with the specified contact number
            String hql = "FROM Student WHERE contact = :contact";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("contact", selectedContact);

            // Get the student entity
            Student student = query.uniqueResult();

            // If student is found, map to StudentDTO
            if (student != null) {
                return new StudentDTO(
                        student.getStudentId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getAddress(),
                        student.getEmail(),
                        student.getContact(),
                        student.getDOB()
                );
            }

            return null; // Return null if no student is found
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of failure
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public Student searchStudentById(String studentId) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Open a Hibernate session

            // HQL query to find the student with the specified studentId
            String hql = "FROM Student WHERE studentId = :studentId";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentId", studentId);

            // Return the student if found
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of failure
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
