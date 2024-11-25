package lk.ijse.theculinaryacademyhibernateproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.theculinaryacademyhibernateproject.bo.BOFactory;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.UserBO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class ForgetNewPasswordFormController {

    @FXML
    private Label lblForgetPassword;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtReNewPassword;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) throws IOException {
        String newPassword1 = txtNewPassword.getText();
        String newPassword2 = txtReNewPassword.getText();
        String username = ForgetGetUsernameEmailController.Username;
        if (newPassword2.equals(newPassword1)){
            String encryptedPassword = BCrypt.hashpw(newPassword2, BCrypt.gensalt());

            boolean isChanged = userBO.changePassword(username,encryptedPassword);

            if (isChanged){
                Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/LoginForm.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login Form");
                stage.centerOnScreen(); // Center the window on the screen
                stage.show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Passwords does not match", ButtonType.OK).show();
        }

    }

    @FXML
    void hyperOnActionLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/LoginForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen(); // Center the window on the screen
        stage.show();
    }

    @FXML
    void txtNewPasswordOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtReNewPasswordOnKeyReleased(KeyEvent event) {

    }

}
