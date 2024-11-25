package lk.ijse.theculinaryacademyhibernateproject.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProgramTm {

    private String programId;
    private String programName;
    private String Duration;
    private double Fee;
}
