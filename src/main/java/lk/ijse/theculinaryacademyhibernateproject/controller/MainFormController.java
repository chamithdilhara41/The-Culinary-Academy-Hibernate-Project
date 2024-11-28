package lk.ijse.theculinaryacademyhibernateproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.theculinaryacademyhibernateproject.util.AnimationUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainFormController {

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnProgram;

    @FXML
    private JFXButton btnSettings;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnUser;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblName;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane rootNode;

    public void initialize() throws IOException {
        AnimationUtil.addPulseAnimation(btnProgram);
        AnimationUtil.addPulseAnimation(btnDashboard);
        AnimationUtil.addPulseAnimation(btnLogout);
        AnimationUtil.addPulseAnimation(btnPayment);
        AnimationUtil.addPulseAnimation(btnSettings);
        AnimationUtil.addPulseAnimation(btnStudent);
        AnimationUtil.addPulseAnimation(btnUser);
        AnimationUtil.addPulseAnimation(btnLogout);
        setDate();
        setTime();
        btnOnActionDashboard(new ActionEvent());
    }

    @FXML
    void btnOnActionDashboard(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/DashboardForm.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);
        AnimationUtil.popUpAnimation(mainPane,dashboardPane);
    }

    @FXML
    void btnOnActionLogout(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/LoginForm.fxml"));

        Scene scene = new Scene(root);

        Stage loginStage = new Stage();
        loginStage.setScene(scene);
        loginStage.setTitle("Login Form");

        loginStage.show();
    }

    @FXML
    void btnOnActionPayment(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/PaymentForm.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);
        AnimationUtil.popUpAnimation(mainPane,dashboardPane);
    }

    @FXML
    void btnOnActionProgram(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/ProgramForm.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);
        AnimationUtil.popUpAnimation(mainPane,dashboardPane);
    }

    @FXML
    void btnOnActionSettings(ActionEvent event) {

    }

    @FXML
    void btnOnActionStudent(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/StudentForm.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);
        AnimationUtil.popUpAnimation(mainPane,dashboardPane);
    }

    @FXML
    void btnOnActionUser(ActionEvent event) {

    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            LocalTime currentTime = LocalTime.now();

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = currentTime.format(timeFormatter);

            lblTime.setText(formattedTime);
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);

        clock.play();
    }
}
