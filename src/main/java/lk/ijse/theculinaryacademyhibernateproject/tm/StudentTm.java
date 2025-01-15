package lk.ijse.theculinaryacademyhibernateproject.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentTm {
    private String studentId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String contact;
    private LocalDate DOB;
}
