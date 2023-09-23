package main;

import helper.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.EndHours;
import model.StartHours;

import java.sql.SQLException;
import java.time.LocalTime;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        stage.setTitle("Well Being General Medicine Health System");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) throws SQLException {


        ClientSchedule.openConnection();

        StatesQuery.stateSelect();
        PhysiciansQuery.physicianSelect();
        UsersQuery.userSelect();
        CoverageQuery.covSelect();

        AppointmentsQuery.appSelect();
        PatientsQuery.patSelect();

        for (int i = 0; i < 24; ++i) {
            CollectionLists.allStartHours.add( new StartHours(LocalTime.of(i, 0)));
        }

        for (int i = 2; i < 25; ++i) {
            CollectionLists.allEndHours.add( new EndHours(LocalTime.of(i - 1, 0)));
        }

        CollectionLists.allEndHours.add(new EndHours(LocalTime.of(0,0)));

        launch(args);

        ClientSchedule.closeConnection();

    }
}
