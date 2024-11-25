package lk.ijse.theculinaryacademyhibernateproject.bo.Custom;

import lk.ijse.theculinaryacademyhibernateproject.bo.SuperBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.UserDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.User;

public interface UserBO extends SuperBO {

    boolean saveUser(UserDTO userDTO);

    User searchUser(String username);

    int checkTableEmpty();

    boolean isUsernameExists(String text);

    String getEmailByUsername(String username);

    boolean changePassword(String username, String encryptedPassword);
}
