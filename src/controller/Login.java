package controller;

import helper.UsersQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private Label errMsgLbl;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField passTxt;

    @FXML
    void onActionLogIn(ActionEvent event) throws IOException, SQLException {

        String userName = nameTxt.getText();
        String password = passTxt.getText();

        if (UsersQuery.userLogin(userName, password)) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Landing.fxml"));
            loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }

        else {

            errMsgLbl.setText("Please enter valid User Name / Password!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
