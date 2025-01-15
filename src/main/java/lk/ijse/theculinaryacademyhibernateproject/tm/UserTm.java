package lk.ijse.theculinaryacademyhibernateproject.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserTm {
    private String username;
    private String email;
    private String role;
}
