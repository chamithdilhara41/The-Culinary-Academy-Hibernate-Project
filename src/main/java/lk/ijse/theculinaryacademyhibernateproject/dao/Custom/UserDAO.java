package lk.ijse.theculinaryacademyhibernateproject.dao.Custom;

import lk.ijse.theculinaryacademyhibernateproject.dao.CrudDAO;
import lk.ijse.theculinaryacademyhibernateproject.dto.UserDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    User searchUser(String username);

    int checkUsers();

    boolean isUsernameExists(String text);

    String getEmailByUsername(String username);

    boolean changeEncryptedPassword(String username, String encryptedPassword);


}
