package lk.ijse.theculinaryacademyhibernateproject.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentRegistrationTM {
    private String registrationId;
    private String studentId;
    private String firstName;
    private String lastName;
    private String programName;
    private String contact;
    private String registrationDate;
}
