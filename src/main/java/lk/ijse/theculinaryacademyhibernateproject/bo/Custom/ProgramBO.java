package lk.ijse.theculinaryacademyhibernateproject.bo.Custom;

import lk.ijse.theculinaryacademyhibernateproject.bo.SuperBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.ProgramDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Program;

import java.util.List;

public interface ProgramBO extends SuperBO {
    boolean saveProgram(ProgramDTO programDTO);

    List<ProgramDTO> getAllPrograms();

    String generateProgramId();

    boolean updateProgram(ProgramDTO programDTO);

    boolean deleteProgram(String programId);

    List<String> getPrograms();

    Program searchByProgramName(String programName);

    Program searchByProgramId(String programId);
}
