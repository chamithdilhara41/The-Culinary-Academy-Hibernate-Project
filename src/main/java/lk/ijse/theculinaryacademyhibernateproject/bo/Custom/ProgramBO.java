package lk.ijse.theculinaryacademyhibernateproject.bo.Custom;

import lk.ijse.theculinaryacademyhibernateproject.bo.SuperBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.ProgramDTO;

import java.util.List;

public interface ProgramBO extends SuperBO {
    boolean saveProgram(ProgramDTO programDTO);

    List<ProgramDTO> getAllPrograms();

    String generateProgramId();

    boolean updateProgram(ProgramDTO programDTO);
}
