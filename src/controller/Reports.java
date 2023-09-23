package controller;

import helper.CollectionLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Physicians;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Reports implements Initializable {

    @FXML
    private ComboBox<Physicians> phyComboBox;

    @FXML
    private TableView<Appointments> phyTableView;

    @FXML
    private TableColumn<Appointments, Integer> schAppCol;

    @FXML
    private TableColumn<Appointments, Integer> schPatCol;

    @FXML
    private TableColumn<Appointments, String> schDescCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> schEndCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> schStartCol;

    @FXML
    private TableColumn<Appointments, String> schTitleCol;

    @FXML
    private TableColumn<Appointments, String> schTypeCol;

    @FXML
    private TableColumn<Appointments, Integer> useAppCol;

    @FXML
    private ComboBox<Users> useComboBox;

    @FXML
    private TableColumn<Appointments, Integer> usePatCol;

    @FXML
    private TableColumn<Appointments, String> useDescCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> useEndCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> useStartCol;

    @FXML
    private TableView<Appointments> useTableView;

    @FXML
    private TableColumn<Appointments, String> useTitleCol;

    @FXML
    private TableColumn<Appointments, String> useTypeCol;

    @FXML
    void onActionRepCancel(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Landing.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSelect(ActionEvent event) {

        int phyID = phyComboBox.getSelectionModel().getSelectedItem().getPhysID();
        phyTableView.setItems(CollectionLists.getPhyApp(phyID));

    }

    @FXML
    void onActionSelectUse(ActionEvent event) {

        int useID = useComboBox.getSelectionModel().getSelectedItem().getUseID();
        useTableView.setItems(CollectionLists.getUseApp(useID));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        phyComboBox.setItems(CollectionLists.getAllPhysicians());
        phyComboBox.getSelectionModel().selectFirst();

        schAppCol.setCellValueFactory(new PropertyValueFactory<>("appID"));
        schTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        schDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        schTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        schStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        schEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        schPatCol.setCellValueFactory(new PropertyValueFactory<>("patID"));
        phyTableView.getSortOrder().add(schStartCol);

        useComboBox.setItems(CollectionLists.getAllUsers());
        useComboBox.getSelectionModel().selectFirst();

        useAppCol.setCellValueFactory(new PropertyValueFactory<>("appID"));
        useTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        useDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        useTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        useStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        useEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        usePatCol.setCellValueFactory(new PropertyValueFactory<>("patID"));
        useTableView.getSortOrder().add(useStartCol);

    }
}
