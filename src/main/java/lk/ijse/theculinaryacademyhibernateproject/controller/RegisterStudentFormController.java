package lk.ijse.theculinaryacademyhibernateproject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.theculinaryacademyhibernateproject.bo.BOFactory;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.ProgramBO;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.RegistrationBO;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.StudentBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.RegistrationDTO;
import lk.ijse.theculinaryacademyhibernateproject.dto.StudentDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.Program;
import lk.ijse.theculinaryacademyhibernateproject.entity.Student;
import lk.ijse.theculinaryacademyhibernateproject.tm.StudentRegistrationTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class RegisterStudentFormController {

    @FXML
    public JFXComboBox<String> cmbContact;

    @FXML
    private JFXComboBox<String> cmbProgram;

    @FXML
    private Label lblProgramId;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colRegisterDate;

    @FXML
    private TableColumn<?, ?> colRegistrationId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private Label lblBuyerForm;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblFee;

    @FXML
    private TableView<StudentRegistrationTM> tblStudent;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtRegistrationId;

    @FXML
    private TextField txtStudentId;

    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAM);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);

    static String registrationId ;
    static String paymentAmount ;
    static String studentId ;

    public void initialize() {
        txtDate.setText(String.valueOf(LocalDate.now()));
        getAllRegisteredStudents();
        setCellValueFactory();
        getContacts();
        getPrograms();
        generateId();
    }

    private void getAllRegisteredStudents() {
        ObservableList<StudentRegistrationTM> obList = FXCollections.observableArrayList();
        List<StudentRegistrationTM> studentList = registrationBO.getAllRegisteredStudent();

        System.out.println(studentList);
        for (StudentRegistrationTM student: studentList){
            obList.add(new StudentRegistrationTM(
                    student.getRegistrationId(),
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getProgramName(),
                    student.getContact(),
                    student.getRegistrationDate()
            ));
        }
        tblStudent.setItems(obList);
    }

    private void setCellValueFactory() {
        colRegistrationId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colRegisterDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
    }

    private void generateId() {
        txtRegistrationId.setText(registrationBO.generateRegistrationId());
    }

    private void getContacts() {
        // Set ComboBox to be editable
        cmbContact.setEditable(true);

        // Fetch the contact list from the business object (BO)
        ObservableList<String> contactNumbers = studentBO.getContacts();

        // Create a filtered list to store the filtered contacts
        FilteredList<String> filteredContacts = new FilteredList<>(contactNumbers, s -> true);

        // Set the filtered list as the items of the ComboBox
        cmbContact.setItems(filteredContacts);

        // Listen to changes in the ComboBox editor's text property
        cmbContact.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final String filter = newValue.toLowerCase();

            // Update the predicate of the filtered list based on the user input
            filteredContacts.setPredicate(contact -> {
                if (filter.isEmpty()) {
                    return true; // Show all contacts if the input is empty
                }
                return contact.toLowerCase().contains(filter); // Filter based on the input
            });

            // Show ComboBox dropdown if there are matching results
            if (!filteredContacts.isEmpty() && cmbContact.getEditor().getText().length() > 0) {
                cmbContact.show();
            }
        });

        // Optionally handle selection change
        cmbContact.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                // Call the method to get student based on selected contact number
                studentBO.getStudent(newVal); // Assuming getStudent() method is implemented properly
            }
        });
    }


    private void getPrograms() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<String> NoList = programBO.getPrograms();
        obList.addAll(NoList);
        cmbProgram.setItems(obList);

    }

    @FXML
    void OnMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnOnActionClear(ActionEvent event) {

    }

    @FXML
    void btnOnActionRegister(ActionEvent event) {

        registrationId = txtRegistrationId.getText();
        paymentAmount = lblFee.getText();
        String registrationDate = txtDate.getText();
        String programId = lblProgramId.getText();
        studentId = txtStudentId.getText();



        Program program = programBO.searchByProgramId(programId);
        Student student = studentBO.searchStudentById(studentId);

        RegistrationDTO registrationDTO = new RegistrationDTO(registrationId, paymentAmount, registrationDate,program,student);

        try {
            registrationBO.RegisterStudentDetails(registrationDTO);

            generateId();
            getAllRegisteredStudents();
            setCellValueFactory();
            paymentInfo();
            new Alert(Alert.AlertType.INFORMATION, "Student Details Registered").show();
        } catch (Exception e) {
            generateId();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void cmbOnActionProgram(ActionEvent event) {
        String programName = cmbProgram.getSelectionModel().getSelectedItem();

        if (programName == null || programName.isEmpty()) {
            System.out.println("No program selected.");
            return;
        }

        Program program = programBO.searchByProgramName(programName);

        if (program != null) {
            lblProgramId.setText(program.getProgramId());
            lblDuration.setText(program.getDuration());
            lblFee.setText(String.valueOf(program.getFee()));
        } else {
            System.out.println("Program not found.");
            lblProgramId.setText("");
            lblDuration.setText("");
            lblFee.setText("");
        }
    }

    @FXML
    public void cmbOnKeyPressed(KeyEvent event) {
        String selectedContact = cmbContact.getEditor().getText();
        if (event.getCode() == KeyCode.ENTER) {

            StudentDTO student = studentBO.getStudent(selectedContact);

            txtStudentId.setText(student.getStudentId());
            txtFirstName.setText(student.getFirstName());
            txtLastName.setText(student.getLastName());
        }
    }

    @FXML
    void txtFirstNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtLastNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOnActionSearchRegistrationId(ActionEvent event) {

    }

    @FXML
    void txtOnActionSearchStudentId(ActionEvent event) {
        String studentId = txtStudentId.getText();

        Student student = studentBO.searchStudentById(studentId);

        txtFirstName.setText(student.getFirstName());
        txtLastName.setText(student.getLastName());
        cmbContact.setValue(student.getContact());
    }

    @FXML
    void txtStudentIdOnKeyReleased(KeyEvent event) {

    }

    @FXML
    public void cmbOnActionContact(ActionEvent actionEvent) {

    }

    private void paymentInfo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/PaymentInfoForm.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.setTitle("AddPayment Form");

        stage.show();
    }
}
