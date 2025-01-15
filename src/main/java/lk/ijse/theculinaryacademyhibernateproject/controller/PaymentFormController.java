package lk.ijse.theculinaryacademyhibernateproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.theculinaryacademyhibernateproject.bo.BOFactory;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.PaymentBO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Payment;
import lk.ijse.theculinaryacademyhibernateproject.tm.PaymentTm;

import java.util.List;

public class PaymentFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colLastPayment;

    @FXML
    private TableColumn<?, ?> colMethod;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colUpFrontAmount;

    @FXML
    private Label lblBuyerForm;

    @FXML
    private Label lblUpfrontAmount;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtPayAmount;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtRegisterId;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);


    public void initialize() {
        getAllPayments();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("pay_date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("balance_amount"));
        colLastPayment.setCellValueFactory(new PropertyValueFactory<>("pay_amount"));
        colUpFrontAmount.setCellValueFactory(new PropertyValueFactory<>("upfront_amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("registration_id"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        //colPaymentId.setCellValueFactory(new PropertyValueFactory<>("payment_type"));
    }

    private void getAllPayments() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();
        List<PaymentTm> studentList = paymentBO.getAllPayments();

        for ( PaymentTm payment: studentList){
            obList.add(new PaymentTm(
                    payment.getPay_id(),
                    payment.getPay_date(),
                    payment.getBalance_amount(),
                    payment.getPay_amount(),
                    payment.getUpfront_amount(),
                    payment.getDescription(),
                    payment.getPayment_type(),
                    payment.getStudent_id(),
                    payment.getRegistration_id()
            ));
        }
        tblPayment.setItems(obList);
    }

    @FXML
    void OnMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnOnActionUpdatePayment(ActionEvent event) {
        String pay_id = txtPaymentId.getText();
        double upfrontAmount = Double.parseDouble(lblUpfrontAmount.getText());
        double payAmount = Double.parseDouble(txtPayAmount.getText());

        if (upfrontAmount != 0 & payAmount <= upfrontAmount & upfrontAmount >= payAmount){
            paymentBO.updatePayment(pay_id,upfrontAmount,payAmount);
            getAllPayments();
            setCellValueFactory();
            new Alert(Alert.AlertType.INFORMATION, "Payment Updated").show();
            txtPaymentId.clear();
            txtPayAmount.clear();
            lblUpfrontAmount.setText("");
            txtPayAmount.setText("");
            txtRegisterId.clear();
        }else {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid payment amount").show();
        }
    }

    @FXML
    void txtLastNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOnActionSearchPaymentID(ActionEvent event) {
        String paymentID = txtPaymentId.getText();

        Payment paymentDTO = paymentBO.searchPaymentId(paymentID);

        if (paymentDTO != null) {
            new Alert(Alert.AlertType.INFORMATION,"payment found").show();
            txtRegisterId.setText(String.valueOf(paymentDTO.getRegistration().getRegistrationID()));
            lblUpfrontAmount.setText(String.valueOf(paymentDTO.getUpfront_amount()));
        }else {
            new Alert(Alert.AlertType.ERROR,"No payment found").show();
        }

    }

    @FXML
    void txtOnActionSearchRegisterID(ActionEvent event) {
        String registerId = txtRegisterId.getText();

        Payment paymentDTO = paymentBO.searchRegisterId(registerId);

        if (paymentDTO != null) {
            new Alert(Alert.AlertType.INFORMATION,"payment found").show();
            txtPaymentId.setText(String.valueOf(paymentDTO.getPay_id()));
            lblUpfrontAmount.setText(String.valueOf(paymentDTO.getUpfront_amount()));
        }else {
            new Alert(Alert.AlertType.ERROR,"No payment found").show();
        }
    }

    @FXML
    void txtStudentIdOnKeyReleased(KeyEvent event) {

    }

}
