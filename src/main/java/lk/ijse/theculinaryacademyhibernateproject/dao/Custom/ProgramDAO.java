package lk.ijse.theculinaryacademyhibernateproject.dao.Custom;

import lk.ijse.theculinaryacademyhibernateproject.dao.CrudDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Program;

public interface ProgramDAO extends CrudDAO<Program> {
    String generateNextProgramId();
}
