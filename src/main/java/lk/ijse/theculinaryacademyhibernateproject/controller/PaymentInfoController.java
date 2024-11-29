package lk.ijse.theculinaryacademyhibernateproject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.theculinaryacademyhibernateproject.bo.BOFactory;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.PaymentBO;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.RegistrationBO;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.StudentBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.PaymentDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Registration;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;

import java.time.LocalDate;

public class PaymentInfoController {

    @FXML
    private JFXComboBox<String> comBoxPaymentType;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblProgramFee;

    @FXML
    private Label lblRegisterId;

    @FXML
    private Label lblStudentId;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private TextField txtPayAmount;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize() {
        getPaymentMethods();
        lblStudentId.setText(RegisterStudentFormController.studentId);
        lblProgramFee.setText(RegisterStudentFormController.paymentAmount);
        lblRegisterId.setText(RegisterStudentFormController.registrationId);
        lblDate.setText(String.valueOf(LocalDate.now()));
        generateId();

    }

    private void generateId() {
        lblPaymentId.setText(paymentBO.generatePaymentId());
    }

    @FXML
    void btnOnActionPayNow(ActionEvent event) {
        String paymentId = lblPaymentId.getText();
        double programFee = Double.parseDouble(lblProgramFee.getText());
        String registerId = lblRegisterId.getText();
        String studentId = lblStudentId.getText();
        String description = txtAreaDescription.getText();
        double payAmount = Double.parseDouble(txtPayAmount.getText());
        String pay_date = lblDate.getText();
        String paymentType = comBoxPaymentType.getValue();
        double upfront_amount = 0;


        Registration registration = registrationBO.searchByRegisterId(registerId);
        Student student = studentBO.searchStudentById(studentId);

        try {
            if (payAmount <= programFee & programFee >= payAmount) {

                upfront_amount = programFee - payAmount;
                PaymentDTO paymentdto = new PaymentDTO(paymentId, pay_date, programFee, payAmount, upfront_amount, description, paymentType, student, registration);
                paymentBO.savePayment(paymentdto);

                // Show success alert
                new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully!").show();

                // Automatically close the payment info window
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            }else {
                new Alert(Alert.AlertType.ERROR,"Check Pay Amount & Program Fee").show();
            }

        } catch (Exception e) {
            // Show error alert
            new Alert(Alert.AlertType.ERROR, "Failed to save payment: " + e.getMessage()).show();
            e.printStackTrace(); // Log the exception for debugging purposes
        }


    }

    private void getPaymentMethods() {
        ObservableList<String> obList = FXCollections.observableArrayList(
                "Cash",
                "Credit Card",
                "Cheque",
                "Bank Transfer"
        );

        comBoxPaymentType.setItems(obList);
    }
}
