package controller;

import helper.CollectionLists;
import helper.PatientsQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyPatient implements Initializable {

    Patients modifiedPatient = null;

    @FXML
    private TextField modPAddTxt;

    @FXML
    private TextField modPIDtxt;

    @FXML
    private TextField modPNameTxt;

    @FXML
    private TextField modPPhoneTxt;

    @FXML
    private TextField modPPostTxt;

    @FXML
    private TextField modCarTxt;

    @FXML
    private Label modPatErrMsgLbl;

    @FXML
    private Label modCovTxt;

    @FXML
    private ComboBox<States> modStateComboBox;

    @FXML
    private ComboBox<Coverage> modCoverageComboBox;


    @FXML
    void onActionModSelectCoverage(ActionEvent event) {

        //modCarTxt.setDisable(Coverage.DEFAULT_COVERAGE_ID == modCoverageComboBox.getSelectionModel().getSelectedItem().getCovID());
        //modCarTxt.clear();

        if (Coverage.DEFAULT_COVERAGE_ID == modCoverageComboBox.getSelectionModel().getSelectedItem().getCovID()) {
            modCarTxt.clear();
            modCovTxt.setText("Discounted Rate:");
        }

        else {
            modCarTxt.clear();
            modCovTxt.setText("Carrier:");
        }

    }

    @FXML
    void onActionModPatCancel(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Landing.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModSavePat(ActionEvent event) throws IOException {

        try {

            if (Coverage.DEFAULT_COVERAGE_ID == modCoverageComboBox.getSelectionModel().getSelectedItem().getCovID()) {

                int patID = modifiedPatient.getPatID();
                String patName = modPNameTxt.getText();
                String patAdd = modPAddTxt.getText();
                String patPos = modPPostTxt.getText();
                int sID = modStateComboBox.getValue().getsID();
                String patPhone = modPPhoneTxt.getText();
                int patCovID = modCoverageComboBox.getSelectionModel().getSelectedItem().getCovID();
                String discRate = modCarTxt.getText();

                if (patName.isEmpty() || patAdd.isEmpty() || patPos.isEmpty() || patPhone.isEmpty()) {
                    modPatErrMsgLbl.setText("You must enter a valid value in all fields!");
                    return;
                }

                modifiedPatient.setPatID(patID);
                modifiedPatient.setPatName(patName);
                modifiedPatient.setPatAdd(patAdd);
                modifiedPatient.setPatPos(patPos);
                modifiedPatient.setsID(sID);
                modifiedPatient.setPatPhone(patPhone);

                PatientsQuery.patUpdate(patID, patName, patAdd, patPos, sID, patPhone, patCovID, null, discRate);
                CollectionLists.deletePatient(modifiedPatient);
                PatientsQuery.patUpdated(patID);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Landing.fxml"));
                loader.load();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            }

            else {

                int patID = modifiedPatient.getPatID();
                String patName = modPNameTxt.getText();
                String patAdd = modPAddTxt.getText();
                String patPos = modPPostTxt.getText();
                int sID = modStateComboBox.getValue().getsID();
                String patPhone = modPPhoneTxt.getText();
                int patCovID = modCoverageComboBox.getSelectionModel().getSelectedItem().getCovID();
                String carrier = modCarTxt.getText();

                if (patName.isEmpty() || patAdd.isEmpty() || patPos.isEmpty() || patPhone.isEmpty() || carrier.isEmpty()) {
                    modPatErrMsgLbl.setText("You must enter a valid value in all fields!");
                    return;
                }

                modifiedPatient.setPatID(patID);
                modifiedPatient.setPatName(patName);
                modifiedPatient.setPatAdd(patAdd);
                modifiedPatient.setPatPos(patPos);
                modifiedPatient.setsID(sID);
                modifiedPatient.setPatPhone(patPhone);

                PatientsQuery.patUpdate(patID, patName, patAdd, patPos, sID, patPhone, patCovID, carrier, null);
                CollectionLists.deletePatient(modifiedPatient);
                PatientsQuery.patUpdated(patID);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Landing.fxml"));
                loader.load();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            }
        }

        catch (NullPointerException | SQLException e) {}

    }

    public void sendPatient (Patients patient) {

        modifiedPatient = patient;
        modPIDtxt.setText(String.valueOf(patient.getPatID()));
        modPNameTxt.setText(patient.getPatName());
        modPAddTxt.setText(patient.getPatAdd());
        modPPostTxt.setText(patient.getPatPos());
        modPPhoneTxt.setText(patient.getPatPhone());

        int s = patient.getsID();
        modStateComboBox.setItems(CollectionLists.getState(s));
        modStateComboBox.getSelectionModel().selectFirst();
        modStateComboBox.setItems(CollectionLists.getAllStates());


        if (patient instanceof Insured) {

            modCovTxt.setText("Carrier:");
            modCarTxt.setText(((Insured) patient).getCarrier());
            int c = Integer.parseInt(String.valueOf(patient.getPatCovID()));
            modCoverageComboBox.setItems(CollectionLists.getCov(c));
            modCoverageComboBox.getSelectionModel().selectFirst();
            modCoverageComboBox.setItems(CollectionLists.getAllCoverage());

        }

        if (patient instanceof Uninsured) {

            modCovTxt.setText("Discounted Rate:");
            modCarTxt.setText(((Uninsured) patient).getDiscRate());
            int c = Integer.parseInt(String.valueOf(patient.getPatCovID()));
            modCoverageComboBox.setItems(CollectionLists.getCov(c));
            modCoverageComboBox.getSelectionModel().selectFirst();
            //modCarTxt.setDisable(Coverage.DEFAULT_COVERAGE_ID == modCoverageComboBox.getSelectionModel().getSelectedItem().getCovID());
            modCoverageComboBox.setItems(CollectionLists.getAllCoverage());

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //modStateComboBox.setItems(CollectionLists.getAllStates());

    }
}
