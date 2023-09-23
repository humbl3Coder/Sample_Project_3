package controller;

import helper.AppointmentsQuery;
import helper.CollectionLists;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ModifyAppointment implements Initializable {

    Appointments modifiedAppointment = null;

    @FXML
    private Label errMessageLbl;

    @FXML
    private Label excMessageLbl;

    @FXML
    private TextField modAppDescTxt;

    @FXML
    private TextField modAppIDTxt;

    @FXML
    private TextField modAppLocTxt;

    @FXML
    private TextField modAppTitleTxt;

    @FXML
    private TextField modAppTypeTxt;

    @FXML
    private DatePicker modDatePicker;

    @FXML
    private ComboBox<EndHours> modEndAppComboBox;

    @FXML
    private ComboBox<Patients> modPatComboBox;

    @FXML
    private ComboBox<Physicians> modPhysComboBox;

    @FXML
    private ComboBox<StartHours> modStartAppComboBox;

    @FXML
    private ComboBox<Users> modUseComboBox;

    @FXML
    void onActionModApp(ActionEvent event) throws IOException {

        try {
            LocalDateTime open = LocalDateTime.of(modDatePicker.getValue(), LocalTime.of(8,0));
            LocalDateTime close = LocalDateTime.of(modDatePicker.getValue(), LocalTime.of(17,0));
            LocalDateTime startM = LocalDateTime.of(modDatePicker.getValue(), modStartAppComboBox.getValue().getStartLT());
            LocalDateTime endM = LocalDateTime.of(modDatePicker.getValue(), modEndAppComboBox.getValue().getEndLT());

            int appID = modifiedAppointment.getAppID();
            String title = modAppTitleTxt.getText();
            String desc = modAppDescTxt.getText();
            String loc = modAppLocTxt.getText();
            String type = modAppTypeTxt.getText();
            LocalDateTime start = LocalDateTime.of(modDatePicker.getValue(), modStartAppComboBox.getValue().getStartLT());
            LocalDateTime end = LocalDateTime.of(modDatePicker.getValue(), modEndAppComboBox.getValue().getEndLT());
            int patID = modPatComboBox.getValue().getPatID();
            int useID = modUseComboBox.getValue().getUseID();
            int conID = modPhysComboBox.getValue().getPhysID();

            if (title.isEmpty() || desc.isEmpty() || loc.isEmpty() || type.isEmpty()) {
                errMessageLbl.setText("You must enter a valid value in all fields!");
                return;
            }

            for (Appointments a : CollectionLists.getAllAppointments()) {

                if (a.getAppID() == appID) {
                    continue;
                }

                if ((a.getStart().isAfter(start) || a.getStart().isEqual(start)) && (a.getStart().isBefore(end))) {
                    errMessageLbl.setText("Appointment overlaps with another appointment!");
                    return;
                }

                if ((a.getEnd().isAfter(start)) && (a.getEnd().isBefore(end) || a.getEnd().isEqual(end)) ) {
                    errMessageLbl.setText("Appointment overlaps with another appointment!");
                    return;
                }

                if ((a.getStart().isBefore(start) || (a.getStart().isEqual(start))) && (a.getEnd().isAfter(end) || a.getEnd().isEqual(end))) {
                    errMessageLbl.setText("Appointment overlaps with another appointment!");
                    return;
                }

                if (a.getStart().toLocalDate() == start.toLocalDate() || a.getEnd().toLocalDate() == end.toLocalDate()) {
                    errMessageLbl.setText("Patient already has an appointment on this date!");
                    return;
                }
            }

            if (startM.isBefore(open) || endM.isAfter(close)) {
                errMessageLbl.setText("Start or End of Appointment is not within Business Hours!");
            }

            else if ((startM.isAfter(start) || startM.isEqual(start)) && (endM.isBefore(end) || endM.isEqual(end))) {

                AppointmentsQuery.appUpdate(appID, title, desc, loc, type, start, end, patID, useID, conID);
                CollectionLists.deleteAppointment(modifiedAppointment);
                AppointmentsQuery.appUpdated(appID);

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
            errMessageLbl.setText("Please enter a valid value for each field!");
        }

    }

    @FXML
    void onModActionCancel(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Landing.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void sendAppointment (Appointments appointment) {

        modifiedAppointment = appointment;
        modAppIDTxt.setText(String.valueOf(appointment.getAppID()));
        modAppTitleTxt.setText(appointment.getTitle());
        modAppDescTxt.setText(appointment.getDesc());
        modAppLocTxt.setText(appointment.getLoc());
        modAppTypeTxt.setText(appointment.getType());

        int a = appointment.getPatID();
        modPatComboBox.setItems(CollectionLists.getPatient(a).sorted());
        modPatComboBox.getSelectionModel().selectFirst();
        modPatComboBox.setItems(CollectionLists.getAllPatients());

        int u = appointment.getUseID();
        modUseComboBox.setItems(CollectionLists.getUser(u).sorted());
        modUseComboBox.getSelectionModel().selectFirst();
        modUseComboBox.setItems(CollectionLists.getAllUsers());

        int p = appointment.getPhyID();
        modPhysComboBox.setItems(CollectionLists.getPhysician(p).sorted());
        modPhysComboBox.getSelectionModel().selectFirst();
        modPhysComboBox.setItems(CollectionLists.getAllPhysicians());

        LocalTime s = appointment.getStart().toLocalTime();
        modStartAppComboBox.setItems(CollectionLists.getStart(s).sorted());
        modStartAppComboBox.getSelectionModel().selectFirst();
        modStartAppComboBox.setItems(CollectionLists.getAllStartHours());
        modStartAppComboBox.setVisibleRowCount(6);

        LocalTime e = appointment.getEnd().toLocalTime();
        modEndAppComboBox.setItems(CollectionLists.getEnd(e).sorted());
        modEndAppComboBox.getSelectionModel().selectFirst();
        modEndAppComboBox.setItems(CollectionLists.getAllEndHours());
        modEndAppComboBox.setVisibleRowCount(6);

        modDatePicker.setValue(appointment.getStart().toLocalDate());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
