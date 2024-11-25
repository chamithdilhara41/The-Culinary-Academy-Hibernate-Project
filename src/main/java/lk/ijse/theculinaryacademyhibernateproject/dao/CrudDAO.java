package lk.ijse.theculinaryacademyhibernateproject.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T dto);
    boolean update(T dto);
    /*boolean delete(T dto);
    String getCurrentId();*/
    List<T> getAll();
}
