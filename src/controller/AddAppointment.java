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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    @FXML
    private TextField appDescTxt;

    @FXML
    private TextField appLocTxt;

    @FXML
    private TextField appTitleTxt;

    @FXML
    private TextField appTypeTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label errMessageLbl;

    @FXML
    private Label excMessageLbl;

    @FXML
    private ComboBox<Patients> patComboBox;

    @FXML
    private ComboBox<Physicians> physComboBox;

    @FXML
    private ComboBox<StartHours> startAppComboBox;

    @FXML
    private ComboBox<EndHours> endAppComboBox;

    @FXML
    private ComboBox<Users> useComboBox;

    @FXML
    void onActionAddApp(ActionEvent event) throws IOException {

        try {

            LocalDateTime open = LocalDateTime.of(datePicker.getValue(), LocalTime.of(8,0));
            LocalDateTime close = LocalDateTime.of(datePicker.getValue(), LocalTime.of(17, 0));
            LocalDateTime startM = LocalDateTime.of(datePicker.getValue(), startAppComboBox.getValue().getStartLT());
            LocalDateTime endM = LocalDateTime.of(datePicker.getValue(), endAppComboBox.getValue().getEndLT());

            int id = 0;
            String title = appTitleTxt.getText();
            String desc = appDescTxt.getText();
            String loc = appLocTxt.getText();
            String type = appTypeTxt.getText();
            LocalDateTime start = LocalDateTime.of(datePicker.getValue(), startAppComboBox.getValue().getStartLT());
            LocalDateTime end = LocalDateTime.of(datePicker.getValue(), endAppComboBox.getValue().getEndLT());
            int patID = patComboBox.getValue().getPatID();
            int useID = useComboBox.getValue().getUseID();
            int physID = physComboBox.getValue().getPhysID();

            if (title.isEmpty() || desc.isEmpty() || loc.isEmpty() || type.isEmpty()) {
                errMessageLbl.setText("You must enter a valid value in all fields!");
                return;
            }

            //checks appointment against other appointments for overlap
            for (Appointments a : CollectionLists.getAllAppointments()) {

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

            //checks appointment against if it's within business hours
            if (startM.isBefore(open) || endM.isAfter(close) || endM.isBefore(startM)) {
                errMessageLbl.setText("Start or End of Appointment is not within Business Hours!");
            }

            else if ((startM.isAfter(start) || startM.isEqual(start)) && (endM.isBefore(end) || endM.isEqual(end))) {

                AppointmentsQuery.appInsert(id, title, desc, loc, type, start, end, patID, useID, physID);
                AppointmentsQuery.appAdded();

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
            errMessageLbl.setText("Please enter a valid value in all fields!");
        }

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Landing.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        datePicker.setValue(LocalDate.now());

        physComboBox.setItems(CollectionLists.getAllPhysicians());
        physComboBox.getSelectionModel().selectFirst();
        physComboBox.setVisibleRowCount(4);

        startAppComboBox.setItems(CollectionLists.getAllStartHours());
        startAppComboBox.getSelectionModel().selectFirst();
        startAppComboBox.setVisibleRowCount(6);

        endAppComboBox.setItems(CollectionLists.getAllEndHours());
        endAppComboBox.getSelectionModel().selectFirst();
        endAppComboBox.setVisibleRowCount(6);

        patComboBox.setItems(CollectionLists.getAllPatients());
        patComboBox.setVisibleRowCount(3);

        useComboBox.setItems(CollectionLists.getAllUsers());
        useComboBox.setVisibleRowCount(3);

    }
}
