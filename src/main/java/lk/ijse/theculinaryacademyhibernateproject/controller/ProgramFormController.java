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
import lk.ijse.theculinaryacademyhibernateproject.bo.BOFactory;
import lk.ijse.theculinaryacademyhibernateproject.bo.Custom.ProgramBO;
import lk.ijse.theculinaryacademyhibernateproject.dto.ProgramDTO;
import lk.ijse.theculinaryacademyhibernateproject.tdm.ProgramTm;

import java.util.List;

public class ProgramFormController {

    @FXML
    private JFXComboBox<String> cmbDuration;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private Label lblBuyerForm;

    @FXML
    private TableView<ProgramTm> tblProgram;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;

    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAM);

    public void initialize() {
        getDurations();
        getAllPrograms();
        setCellValueFactory();
        generateId();
    }

    private void generateId() {
        txtProgramId.setText(programBO.generateProgramId());
    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    private void getAllPrograms() {
        ObservableList<ProgramTm> obList = FXCollections.observableArrayList();
        List<ProgramDTO> programsList = programBO.getAllPrograms();

        for ( ProgramDTO program: programsList){
            obList.add(new ProgramTm(
                    program.getProgramId(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()
            ));
        }
        tblProgram.setItems(obList);
    }

    @FXML
    void OnMouseClicked(MouseEvent event) {
        int index = tblProgram.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }
        String id = colProgramId.getCellData(index).toString();
        String programName = colProgramName.getCellData(index).toString();
        String duration = colDuration.getCellData(index).toString();
        String fee = colFee.getCellData(index).toString();

        txtProgramId.setText(id);
        txtProgramName.setText(programName);
        cmbDuration.setValue(duration);
        txtFee.setText(fee);
    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        String programId = txtProgramId.getText();

        boolean isDeleted = programBO.deleteProgram(programId);

        if (isDeleted){
            clearFields();
            getAllPrograms();
            setCellValueFactory();
            generateId();
            new Alert(Alert.AlertType.INFORMATION, "Program deleted successfully").show();
        }else {
            getAllPrograms();
            setCellValueFactory();
            generateId();
            new Alert(Alert.AlertType.ERROR,"Could not find program with id " + programId).showAndWait();
        }
    }

    @FXML
    void btnOnActionSave(ActionEvent event) {
        String programId = txtProgramId.getText();
        String programName = txtProgramName.getText();
        String duration = cmbDuration.getValue();
        double fee = Double.parseDouble(txtFee.getText());

        ProgramDTO programDTO = new ProgramDTO(programId, programName, duration, fee);

        try {
            programBO.saveProgram(programDTO);
            setCellValueFactory();
            getAllPrograms();
            clearFields();
            generateId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Program Save Error").show();
            getAllPrograms();
            setCellValueFactory();
        }

    }


    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        String programId = txtProgramId.getText();
        String programName = txtProgramName.getText();
        String duration = cmbDuration.getValue();
        double fee = Double.parseDouble(txtFee.getText());

        ProgramDTO programDTO = new ProgramDTO(programId, programName, duration, fee);


        try {
            programBO.updateProgram(programDTO);
            new Alert(Alert.AlertType.INFORMATION, "Program Updated").show();
            clearFields();
            getAllPrograms();
            setCellValueFactory();
            generateId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Program Update Error").show();
            getAllPrograms();
            setCellValueFactory();
        }
    }

    @FXML
    void cmbDurationOnAction(ActionEvent event) {

    }

    @FXML
    void txtFeeOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOnActionSearchId(ActionEvent event) {

    }

    @FXML
    void txtProgramIdOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtProgramNameOnKeyReleased(KeyEvent event) {

    }

    private void getDurations() {
        ObservableList<String> obList = FXCollections.observableArrayList(
                "3 months",
                "6 months",
                "1 year"
        );

        cmbDuration.setItems(obList);

    }

    private void clearFields() {
        txtFee.setText("");
        txtProgramName.setText("");
        txtProgramId.setText("");
        cmbDuration.setValue("");
    }


}
