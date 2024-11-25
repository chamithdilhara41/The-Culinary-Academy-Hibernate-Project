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
import lk.ijse.theculinaryacademyhibernateproject.util.JavaMailUtil;

import java.io.IOException;
import java.util.Random;

public class ForgetGetUsernameEmailController {

    @FXML
    private Label lblCheckUsername;

    @FXML
    private Label lblForgetPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUsername;

    public static int OTP;
    public static String Username;

    public void initialize() {

    }

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnForEmailOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();

        String email = userBO.getEmailByUsername(username);
        if (email != null) {
            txtEmail.setText(email);
        } else {
            txtEmail.clear();
            new Alert(Alert.AlertType.ERROR, "Can't find Email...", ButtonType.OK).show();
        }
    }

    @FXML
    void btnSendOtpOnAction(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        String username = txtUsername.getText();

        // Check if username is empty
        if (!username.isEmpty()) {
            // Generate OTP
            Random random = new Random();
            int otp = 1000 + random.nextInt(9000); // Generate a 4-digit OTP

            // Send OTP email
            boolean sendingOTP = JavaMailUtil.sendMail(email, otp);
            if (sendingOTP) {
                new Alert(Alert.AlertType.INFORMATION, "OTP Sent", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "OTP Failed", ButtonType.OK).show();
            }

            // Store OTP and username for later use
            Username = username;
            OTP = otp;
            System.out.println("Generated OTP: " + otp);

            // Load the OTP form FXML
            Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/ForgetPasswordOTPForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("OTP Form");
            stage.centerOnScreen(); // Center the OTP form on the screen
            stage.show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please enter a username", ButtonType.OK).show();
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
    void txtUsernameOnKeyReleased(KeyEvent event) {

        boolean isValidUsername = userBO.isUsernameExists(txtUsername.getText());

        if(isValidUsername) {
            lblCheckUsername.setText("Valid Username and press Enter get email...");
            lblCheckUsername.setStyle("-fx-text-fill: #00b600;");
        } else {
            lblCheckUsername.setText("Invalid Username...");
            lblCheckUsername.setStyle("-fx-text-fill: #f33232;");
        }
    }

}
