package controller;

import helper.AppointmentsQuery;
import helper.ClientSchedule;
import helper.CollectionLists;
import helper.PatientsQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Patients;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Landing implements Initializable {

    Stage stage;

    @FXML
    private TableView<Appointments> AppTableView;

    @FXML
    private TableView<Patients> PatTableView;

    @FXML
    private RadioButton weekRBtn;

    @FXML
    private RadioButton monthRBtn;

    @FXML
    private RadioButton allRBtn;

    @FXML
    private RadioButton allPatsBtn;

    @FXML
    private RadioButton InsBtn;

    @FXML
    private RadioButton UnBtn;

    @FXML
    private Label appErrMsgLbl;

    @FXML
    private Label patErrMsgLbl;

    @FXML
    private TableColumn<Appointments, String> appDescCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appEndCol;

    @FXML
    private TableColumn<Appointments, Integer> appIDCol;

    @FXML
    private TableColumn<Appointments, String> appLocCol;

    @FXML
    private TableColumn<Appointments, Integer> appPatIDCol;

    @FXML
    private TableColumn<Appointments, String> appPhyCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appStartCol;

    @FXML
    private TableColumn<Appointments, String> appTitleCol;

    @FXML
    private TableColumn<Appointments, String> appTypeCol;

    @FXML
    private TableColumn<Appointments, Integer> appUseIDCol;

    @FXML
    private TableColumn<Patients, String> patAddCol;

    @FXML
    private TableColumn<Patients, String> patCnameCol;

    @FXML
    private TableColumn<Patients, Integer> patIDCol;

    @FXML
    private TableColumn<Patients, String> patCovCol;

    @FXML
    private TableColumn<Patients, String> patPhoCol;

    @FXML
    private TableColumn<Patients, String> patPosCol;

    @FXML
    private TableColumn<Patients, String> patStCol;

    @FXML
    private TextField searchAppTxt;

    @FXML
    private TextField searchPatTxt;



    @FXML
    void onActionAddAppForm(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddAppointment.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDelApp(ActionEvent event) {

        try {

            String delType = AppTableView.getSelectionModel().getSelectedItem().getType();
            int delAppID = AppTableView.getSelectionModel().getSelectedItem().getAppID();

            AppointmentsQuery.appDelete(AppTableView.getSelectionModel().getSelectedItem().getAppID());
            CollectionLists.deleteAppointment(AppTableView.getSelectionModel().getSelectedItem());
            appErrMsgLbl.setText("Appointment # " + delAppID + "  (" + delType + ") has been cancelled." );

        }

        catch (NullPointerException | SQLException e) {
            appErrMsgLbl.setText(("Please select an Appointment to delete."));
        }

    }

    @FXML
    void onActionDelPat(ActionEvent event) {

        try {
            int delPatID = PatTableView.getSelectionModel().getSelectedItem().getPatID();

            boolean match = false;

            for(Appointments a : CollectionLists.allAppointments ) {

                if (a.getPatID() == delPatID) {
                    match = true;
                    break;
                }
            }

            if (match) {
                patErrMsgLbl.setText("Cannot delete. Customer has active appointment(s).");
            }

            else {

                PatientsQuery.patDelete(PatTableView.getSelectionModel().getSelectedItem().getPatID());
                CollectionLists.deletePatient(PatTableView.getSelectionModel().getSelectedItem());
                patErrMsgLbl.setText("Customer has been deleted.");

            }
        }

        catch (NullPointerException | SQLException e) {
            patErrMsgLbl.setText(("Please select a Customer to delete."));
        }

    }

    @FXML
    void onActionExitBtn(ActionEvent event) {

        ClientSchedule.closeConnection();
        System.exit(0);

    }

    @FXML
    void onActionLogoutBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Login.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModAppForm(ActionEvent event) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyAppointment.fxml"));
            loader.load();

            ModifyAppointment MAController = loader.getController();
            MAController.sendAppointment(AppTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

        catch (NullPointerException e) {
            appErrMsgLbl.setText("Please select an Appointment to modify.");
        }

    }

    @FXML
    void onActionModPatForm(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPatient.fxml"));
            loader.load();

            ModifyPatient MCController = loader.getController();
            MCController.sendPatient(PatTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

        catch (NullPointerException e) {
            patErrMsgLbl.setText("Please select a Customer to modify.");
        }

    }

    @FXML
    void onActionPatAddForm(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddPatient.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionReportsBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Reports.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSearchApp(ActionEvent event) {

        try {
            int id = Integer.parseInt(searchAppTxt.getText());
            Appointments app = CollectionLists.lookupApp(id);
            AppTableView.getSelectionModel().select(app);

        } catch(NumberFormatException e) {
            String q = searchAppTxt.getText();
            ObservableList<Appointments> apps = CollectionLists.lookupApp(q);
            AppTableView.setItems(apps);

        }

    }

    @FXML
    void onActionSearchPat(ActionEvent event) {

        try {
            int id = Integer.parseInt(searchPatTxt.getText());
            Patients pat = CollectionLists.lookupPat(id);
            PatTableView.getSelectionModel().select(pat);

        } catch(NumberFormatException e) {
            String q = searchPatTxt.getText();
            ObservableList<Patients> pats = CollectionLists.lookupPat(q);
            PatTableView.setItems(pats);

        }

    }

    @FXML
    void onAll(ActionEvent event) {

        if (allRBtn.isSelected()) {
            AppTableView.setItems(CollectionLists.getAllAppointments());
        }

    }

    @FXML
    void onMonthly(ActionEvent event) {

        if (monthRBtn.isSelected()) {
            AppTableView.setItems(CollectionLists.getMonthApp());
        }

    }

    @FXML
    void onWeekly(ActionEvent event) {

        if (weekRBtn.isSelected()) {
            AppTableView.setItems(CollectionLists.getWeekApp());
        }

    }

    @FXML
    void OnUninsured(ActionEvent event) {

        PatTableView.setItems(CollectionLists.getUnInsured());

    }

    @FXML
    void onInsured(ActionEvent event) {

        PatTableView.setItems(CollectionLists.getInsured());

    }

    @FXML
    void onActionAllPats(ActionEvent event) {

        PatTableView.setItems(CollectionLists.getAllPatients());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AppTableView.setItems(CollectionLists.getAllAppointments());
        appIDCol.setCellValueFactory(new PropertyValueFactory<>("appID"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        appLocCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
        appPhyCol.setCellValueFactory(new PropertyValueFactory<>("phyName"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appPatIDCol.setCellValueFactory(new PropertyValueFactory<>("patID"));
        appUseIDCol.setCellValueFactory(new PropertyValueFactory<>("useID"));
        AppTableView.getSortOrder().add(appIDCol);

        PatTableView.setItems(CollectionLists.getAllPatients());
        patIDCol.setCellValueFactory(new PropertyValueFactory<>("patID"));
        patCnameCol.setCellValueFactory(new PropertyValueFactory<>("patName"));
        patAddCol.setCellValueFactory(new PropertyValueFactory<>("patAdd"));
        patPosCol.setCellValueFactory(new PropertyValueFactory<>("patPos"));
        patPhoCol.setCellValueFactory(new PropertyValueFactory<>("patPhone"));
        patCovCol.setCellValueFactory(new PropertyValueFactory<>("patCov"));
        patStCol.setCellValueFactory(new PropertyValueFactory<>("stateName"));
        PatTableView.getSortOrder().add(patIDCol);

        allPatsBtn.fire();
        allRBtn.fire();
    }
}
