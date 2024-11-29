package lk.ijse.theculinaryacademyhibernateproject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.theculinaryacademyhibernateproject.bo.BOFactory;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.UserBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.UserDTO;
import lk.ijse.theculinaryacademyhibernateproject.entity.User;
import lk.ijse.theculinaryacademyhibernateproject.tdm.UserTm;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserFormController {

    @FXML
    private JFXComboBox<String> cmbRole;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private Label lblBuyerForm;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        getAllUsers();
        getRolls();
        setCellValueFactory();
    }

    private void getAllUsers() {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();
        List<UserDTO> studentList = userBO.getAllUsers();

       /* System.out.println(studentList.get());*/

        for ( UserDTO student: studentList){
            obList.add(new UserTm(
                    student.getUsername(),
                    student.getEmail(),
                    student.getRole()

            ));
        }
        tblUser.setItems(obList);
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    @FXML
    void OnMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnOnActionAddUser(ActionEvent event) {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
        String role = cmbRole.getValue();

        UserDTO user = new UserDTO(username, password, email, role);


        if (user != null) {
            boolean b = userBO.saveUser(user);
            getAllUsers();
            setCellValueFactory();
            new Alert(Alert.AlertType.INFORMATION, "User added successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }

    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        txtEmail.clear();
        txtPassword.clear();
        txtUsername.clear();
        cmbRole.setValue("");
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {

    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOnActionRole(ActionEvent event) {

    }

    @FXML
    void txtOnActionUsername(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnKeyReleased(KeyEvent event) {

    }

    private void getRolls() {
        ObservableList<String> obList = FXCollections.observableArrayList(
                "admin",
                "coordinator"
        );

        cmbRole.setItems(obList);
    }

}
