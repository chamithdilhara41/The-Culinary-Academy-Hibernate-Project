package lk.ijse.theculinaryacademyhibernateproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.theculinaryacademyhibernateproject.bo.BOFactory;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.StudentBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;
import lk.ijse.theculinaryacademyhibernateproject.tm.StudentTm;
import lk.ijse.theculinaryacademyhibernateproject.util.AnimationUtil;
import lk.ijse.theculinaryacademyhibernateproject.util.Regex;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class StudentFormController {

    @FXML
    private AnchorPane mainPane;

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
    private JFXButton btnRegisterStudent;

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
        String studentId = txtStudentId.getText();

        boolean isDeleted = studentBO.deleteStudent(studentId);

        if (isDeleted){
            clearFields();
            getAllStudents();
            setCellValueFactory();
            generateId();
            new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully").show();
        }else {
            getAllStudents();
            setCellValueFactory();
            generateId();
            new Alert(Alert.AlertType.ERROR,"Could not find student with id " + studentId).showAndWait();
        }
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

        if (isValid()) {
            studentBO.saveStudent(studentDTO);
            new Alert(Alert.AlertType.INFORMATION, "Student Saved").show();
            clearFields();
            getAllStudents();
            setCellValueFactory();
            generateId();
        }else {
            new Alert(Alert.AlertType.ERROR, "Please check Text Fields... ").show();
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
            if (isValid()){
                studentBO.updateStudent(studentDTO);
                System.out.println(studentDTO);
                new Alert(Alert.AlertType.INFORMATION, "Student Updated").show();
                clearFields();
                getAllStudents();
                setCellValueFactory();
                generateId();
            }else {
                new Alert(Alert.AlertType.ERROR, "Please check Text Fields... ").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Student Update Error").show();
            getAllStudents();
            setCellValueFactory();
        }
    }

    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.ADDRESS, txtAddress);
    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.CONTACT, txtContact);
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.EMAIL, txtEmail);
    }

    @FXML
    void txtFirstNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.NAME, txtFirstName);
    }

    @FXML
    void txtLastNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.NAME, txtLastName);
    }

    @FXML
    void txtOnActionSearchId(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.ID, txtStudentId);
    }

    @FXML
    public void txtDateOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.DATE, txtDOB);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.ID,txtStudentId)) return false;
        if (!Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.NAME,txtFirstName)) return false;
        if (!Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.NAME,txtLastName)) return false;
        if (!Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.CONTACT,txtContact)) return false;
        if (!Regex.setTextColor(lk.ijse.theculinaryacademyhibernateproject.util.TextField.DATE,txtDOB)) return false;
        return true;
    }

    @FXML
    void btnOnActionRegisterStudent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/RegisterStudentForm.fxml"));
        AnchorPane contentPane = loader.load();

        // Add the loaded content to the main pane
        mainPane.getChildren().clear();
        mainPane.getChildren().add(contentPane);
        AnimationUtil.popUpAnimation(mainPane, contentPane);
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
