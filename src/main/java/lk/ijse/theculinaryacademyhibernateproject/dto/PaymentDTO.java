package lk.ijse.theculinaryacademyhibernateproject.dto;

import lk.ijse.theculinaryacademyhibernateproject.entity.Registration;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentDTO {
    private String pay_id;
    private String pay_date;
    private double balance_amount;
    private double pay_amount;
    private double upfront_amount;
    private String description;

    private Student studentId;
    private Registration registrationId;

}
