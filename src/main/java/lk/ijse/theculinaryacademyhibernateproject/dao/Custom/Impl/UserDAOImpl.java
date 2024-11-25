package lk.ijse.theculinaryacademyhibernateproject.dao.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.config.FactoryConfiguration;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.UserDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean isUsernameExists(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Long> query = session.createQuery("SELECT COUNT(u.id) FROM User u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        Long count = query.uniqueResult();

        transaction.commit();
        session.close();

        return count != null && count > 0;
    }

    @Override
    public String getEmailByUsername(String username) {
        String email = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "SELECT u.email FROM User u WHERE u.username = :username";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("username", username);
            email = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return email;
    }

    @Override
    public boolean changeEncryptedPassword(String username, String encryptedPassword) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        boolean isUpdated = false;

        try {
            // HQL update query
            String hql = "UPDATE User u SET u.password = :encryptedPassword WHERE u.username = :username";
            Query<?> query = session.createQuery(hql);
            query.setParameter("encryptedPassword", encryptedPassword);
            query.setParameter("username", username);

            // Execute update
            int result = query.executeUpdate();
            transaction.commit();

            // Check if update was successful
            isUpdated = (result > 0);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        return isUpdated;
    }

    @Override
    public int checkUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Long> query = session.createQuery("SELECT COUNT(u) FROM User u", Long.class);
        long userCount = query.uniqueResult();

        transaction.commit();
        session.close();

        return (int) userCount;
    }

    @Override
    public boolean save(User dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(User dto) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return List.of();//user table ek hadanna one
    }

    @Override
    public User searchUser(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        User user = query.uniqueResult();

        transaction.commit();
        session.close();

        return user;
    }
}
/*
@Override
public boolean save(Customer dto) {
    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction = session.beginTransaction();

    session.save(dto);

    transaction.commit();
    session.close();
    return false;

}*/
