package lk.ijse.theculinaryacademyhibernateproject.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentTm {
    private String pay_id;
    private String pay_date;
    private double balance_amount;
    private double pay_amount;
    private double upfront_amount;
    private String description;
    private String payment_type;

    private String student_id;
    private String registration_id;
}
