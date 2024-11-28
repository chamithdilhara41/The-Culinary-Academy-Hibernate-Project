package lk.ijse.theculinaryacademyhibernateproject.dao.Custom;

import lk.ijse.theculinaryacademyhibernateproject.dao.CrudDAO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Program;

import java.util.List;

public interface ProgramDAO extends CrudDAO<Program> {
    String generateNextProgramId();

    boolean delete(String programId);

    List<String> getNames();

    Program searchByName(String programName);

    Program searchById(String programId);
}
