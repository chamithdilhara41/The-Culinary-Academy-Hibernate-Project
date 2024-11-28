package lk.ijse.theculinaryacademyhibernateproject.dto;

import lk.ijse.theculinaryacademyhibernateproject.entity.Program;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String registrationId;
    private String paymentAmount;
    private String registrationDate;
    private Program programId;
    private Student studentId;
}
