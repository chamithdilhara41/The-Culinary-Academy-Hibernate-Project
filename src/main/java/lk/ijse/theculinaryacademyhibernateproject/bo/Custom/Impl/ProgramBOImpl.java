package lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.ProgramBO;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.ProgramDAO;
import lk.ijse.theculinaryacademyhibernateproject.dao.DAOFactory;
import lk.ijse.theculinaryacademyhibernateproject.dto.ProgramDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean saveProgram(ProgramDTO programDTO) {
        return programDAO.save(new Program(programDTO.getProgramId(), programDTO.getProgramName() ,programDTO.getDuration(), programDTO.getFee()));
    }

    @Override
    public List<ProgramDTO> getAllPrograms() {
        List<Program> programs = programDAO.getAll();
        List<ProgramDTO> programDTOList = new ArrayList<>();
        for (Program program : programs) {
            programDTOList.add(
                    new ProgramDTO(
                            program.getProgramId(),
                            program.getProgramName(),
                            program.getDuration(),
                            program.getFee()
                    )
            );
        }
        return programDTOList;
    }

    @Override
    public String generateProgramId() {
        return programDAO.generateNextProgramId();
    }

    @Override
    public boolean updateProgram(ProgramDTO programDTO) {
        return programDAO.update(new Program(programDTO.getProgramId(), programDTO.getProgramName(), programDTO.getDuration(), programDTO.getFee()));
    }
}
