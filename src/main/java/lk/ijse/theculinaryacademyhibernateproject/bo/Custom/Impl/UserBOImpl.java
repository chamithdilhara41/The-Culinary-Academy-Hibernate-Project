package lk.ijse.theculinaryacademyhibernateproject.bo.Custom.Impl;

import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.UserBO;
import lk.ijse.theculinaryacademyhibernateproject.dao.Custom.UserDAO;
import lk.ijse.theculinaryacademyhibernateproject.dao.DAOFactory;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;
import lk.ijse.theculinaryacademyhibernateproject.dto.UserDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;
import lk.ijse.theculinaryacademyhibernateproject.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public int checkTableEmpty() {
        return userDAO.checkUsers();
    }

    @Override
    public boolean isUsernameExists(String text) {
        return userDAO.isUsernameExists(text);
    }

    @Override
    public String getEmailByUsername(String username) {
        return userDAO.getEmailByUsername(username);
    }

    @Override
    public boolean changePassword(String username, String encryptedPassword) {
        return userDAO.changeEncryptedPassword(username,encryptedPassword);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> students = userDAO.getAll();
        List<UserDTO> studentDTOList = new ArrayList<>();
        for (User user : students) {
            studentDTOList.add(
                    new UserDTO(
                            user.getUsername(),
                            user.getPassword(),
                            user.getEmail(),
                            user.getRole()
                    )
            );
        }
        return studentDTOList;
    }


    @Override
    public User searchUser(String username) {
        return userDAO.searchUser(username);
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getRole()));
    }
}
