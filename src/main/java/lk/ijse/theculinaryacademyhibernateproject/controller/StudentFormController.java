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
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.StudentBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;
import lk.ijse.theculinaryacademyhibernateproject.tdm.StudentTm;

import java.time.LocalDate;
import java.util.List;

public class StudentFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private Label lblBuyerForm;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtDOB;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        getAllStudents();
        setCellValueFactory();
        generateId();
    }

    private void generateId() {
        txtStudentId.setText(studentBO.generateStudentId());
    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {

    }

    @FXML
    void btnOnActionSave(ActionEvent event) {
        String studentId = txtStudentId.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        LocalDate DOB = LocalDate.parse(txtDOB.getText());

        StudentDTO studentDTO = new StudentDTO(studentId,firstName,lastName,address,email,contact,DOB);

        try {
            studentBO.saveStudent(studentDTO);
            System.out.println(studentDTO);
            new Alert(Alert.AlertType.INFORMATION, "Student Saved").show();
            clearFields();
            getAllStudents();
            setCellValueFactory();
            generateId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Student Save Error").show();
            getAllStudents();
            setCellValueFactory();
        }
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
    }

    private void getAllStudents() {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();
        List<StudentDTO> studentList = studentBO.getAllStudent();

        for ( StudentDTO student: studentList){
            obList.add(new StudentTm(
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getEmail(),
                    student.getDOB()
            ));
        }
        tblStudent.setItems(obList);
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        String studentId = txtStudentId.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        LocalDate DOB = LocalDate.parse(txtDOB.getText());

        StudentDTO studentDTO = new StudentDTO(studentId,firstName,lastName,address,email,contact,DOB);

        try {
            studentBO.updateStudent(studentDTO);
            System.out.println(studentDTO);
            new Alert(Alert.AlertType.INFORMATION, "Student Updated").show();
            clearFields();
            getAllStudents();
            setCellValueFactory();
            generateId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Student Update Error").show();
            getAllStudents();
            setCellValueFactory();
        }
    }

    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtFirstNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtLastNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOnActionSearchId(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void OnMouseClicked(MouseEvent event) {
        int index = tblStudent.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }
        String id = colStudentId.getCellData(index).toString();
        String firstname = colFirstName.getCellData(index).toString();
        String lastName = colLastName.getCellData(index).toString();
        String address = colAddress.getCellData(index).toString();
        String email = colEmail.getCellData(index).toString();
        String contact = colContact.getCellData(index).toString();
        String DOB = colDOB.getCellData(index).toString();

        txtStudentId.setText(id);
        txtFirstName.setText(firstname);
        txtLastName.setText(lastName);
        txtAddress.setText(address);
        txtEmail .setText(email);
        txtContact .setText(contact);
        txtDOB.setText(DOB);
    }

    private void clearFields() {
        txtEmail.setText("");
        txtDOB.setText("");
        txtStudentId.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        txtLastName.setText("");
        txtFirstName.setText("");
    }
}