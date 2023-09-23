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
import model.Coverage;
import model.States;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddPatient implements Initializable {

    @FXML
    private ComboBox<States> StateComboBox;

    @FXML
    private ComboBox<Coverage> CoverageComboBox;

    @FXML
    private TextField addCarTxt;

    @FXML
    private TextField pAddTxt;

    @FXML
    private TextField pNameTxt;

    @FXML
    private TextField pPhoneTxt;

    @FXML
    private TextField pPostTxt;

    @FXML
    private Label patErrMsgLbl;

    @FXML
    private Label addCovTxt;

    @FXML
    void onActionPatCancel(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Landing.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSavePat(ActionEvent event) throws IOException {

        try {

            if (Coverage.DEFAULT_COVERAGE_ID == CoverageComboBox.getSelectionModel().getSelectedItem().getCovID()) {
                String patName = pNameTxt.getText();
                String patAdd = pAddTxt.getText();
                String patPos = pPostTxt.getText();
                int sID = StateComboBox.getSelectionModel().getSelectedItem().getsID();
                String patPhone = pPhoneTxt.getText();
                int patCovID = CoverageComboBox.getSelectionModel().getSelectedItem().getCovID();
                String discRate = addCarTxt.getText();

                if (patName.isEmpty() || patAdd.isEmpty() || patPos.isEmpty() || patPhone.isEmpty()) {
                    patErrMsgLbl.setText("You must enter a valid value in all fields!");
                    return;
                }

                PatientsQuery.patInsert(patName, patAdd, patPos, sID, patPhone, patCovID, null, discRate);
                PatientsQuery.patAdded();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Landing.fxml"));
                loader.load();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            }

            else {
                String patName = pNameTxt.getText();
                String patAdd = pAddTxt.getText();
                String patPos = pPostTxt.getText();
                int sID = StateComboBox.getSelectionModel().getSelectedItem().getsID();
                String patPhone = pPhoneTxt.getText();
                int patCovID = CoverageComboBox.getSelectionModel().getSelectedItem().getCovID();
                String carrier = addCarTxt.getText();

                if (patName.isEmpty() || patAdd.isEmpty() || patPos.isEmpty() || patPhone.isEmpty() || carrier.isEmpty()) {
                    patErrMsgLbl.setText("You must enter a valid value in all fields!");
                    return;
                }

                PatientsQuery.patInsert(patName, patAdd, patPos, sID, patPhone, patCovID, carrier, null);
                PatientsQuery.patAdded();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Landing.fxml"));
                loader.load();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            }
        }

        catch (NullPointerException | SQLException e) {

            System.out.println(e);
            patErrMsgLbl.setText("Please enter valid values in all fields!");
        }


    }

    @FXML
    void onActionSelectCoverage(ActionEvent event) {

        //addCarTxt.setDisable(Coverage.DEFAULT_COVERAGE_ID == CoverageComboBox.getSelectionModel().getSelectedItem().getCovID());

        if (Coverage.DEFAULT_COVERAGE_ID == CoverageComboBox.getSelectionModel().getSelectedItem().getCovID()) {
            addCovTxt.setText("Discounted Rate:");
        }

        else {
            addCovTxt.setText("Carrier:");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        StateComboBox.setItems(CollectionLists.getAllStates());
        StateComboBox.getSelectionModel().selectFirst();

        CoverageComboBox.setItems(CollectionLists.getAllCoverage());

    }
}
