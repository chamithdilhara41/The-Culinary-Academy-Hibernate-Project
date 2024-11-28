package lk.ijse.theculinaryacademyhibernateproject.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.theculinaryacademyhibernateproject.bo.BOFactory;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.UserBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.UserDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblLogin1;

    @FXML
    private PasswordField txtPasswordLogin;

    @FXML
    private TextField txtUsernameLogin;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        checkUserTable();
    }

    private void checkUserTable() {
        int number = userBO.checkTableEmpty();
        String password = BCrypt.hashpw("Admin123", BCrypt.gensalt());
        if (number == 0) {
            UserDTO userDTO = new UserDTO("admin",password,"","admin");
            userBO.saveUser(userDTO);
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

        String username = txtUsernameLogin.getText();
        String password = txtPasswordLogin.getText();

        User user = userBO.searchUser(username);

        if (user != null) {
            // Compare the provided password with the encrypted password in the database
            if (BCrypt.checkpw(password, user.getPassword()) || user.getPassword().equals(password)) {

                new Alert(Alert.AlertType.INFORMATION, "Login successful!", ButtonType.OK).show();

                System.out.println(password+" "+user.getPassword());

                loadDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password.", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found.", ButtonType.OK).show();
        }


        /*String username = txtUsernameLogin.getText();
        String password = txtPasswordLogin.getText();
        System.out.println("tn click");

        UserDTO userDTO = new UserDTO(username,password,"chamith@123","admin");

        userBO.saveUser(userDTO);*/
    }

    private void loadDashboard() {
        try {

            Parent rootNode = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/MainForm.fxml"));
            Scene scene = new Scene(rootNode);

            // Get the Stage from the current window
            Stage stage = (Stage) txtPasswordLogin.getScene().getWindow();
            //AnimationUtil.popUpAnimation1(stage, rootNode);
            // Set the new scene to the stage
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Main Form (Tea leaves stock Management System)");


        } catch (IOException e) {
            e.printStackTrace();
            // Optionally, display an error alert to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText("Failed to Load Main Form");
            alert.setContentText("Please check the FXML file path and try again.");
            alert.showAndWait();
        }
    }


    @FXML
    void hyperOnActionForgetPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/ForgetGetUsernameEmailForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Password Forget Form");
        stage.centerOnScreen(); // Center the window on the screen
        stage.show();
    }


    @FXML
    void txtOnActionLogin(ActionEvent event) {


    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtUsernameOnKeyReleased(KeyEvent event) {

    }

}

