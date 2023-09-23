package helper;

import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AppointmentsQuery {

    public static void appSelect() throws SQLException {
        String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, a.Patient_ID, a.User_ID, a.Physician_ID, c.Physician_Name FROM appointments AS a " +
                "INNER JOIN physicians AS c ON a.Physician_ID = c.Physician_ID";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String desc = rs.getString("Description");
            String loc = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int patID = rs.getInt("Patient_ID");
            int useID = rs.getInt("User_ID");
            int physID = rs.getInt("Physician_ID");
            String phyName = rs.getString("Physician_Name");

            CollectionLists.addAppointment(new Appointments(appID, title, desc, loc, type, start, end, patID, useID, physID, phyName));
        }
    }

    public static int appInsert(int appID, String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int patID, int useID, int phyID) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Patient_ID, User_ID, Physician_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setInt(1, appID);
        ps.setString(2, title);
        ps.setString(3, desc);
        ps.setString(4, loc);
        ps.setString(5, type);
        ps.setTimestamp(6, Timestamp.valueOf(start));
        ps.setTimestamp(7, Timestamp.valueOf(end));
        ps.setInt(8, patID);
        ps.setInt(9, useID);
        ps.setInt(10, phyID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void appAdded() throws SQLException {
        String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, a.Patient_ID, a.User_ID, a.Physician_ID, c.Physician_Name FROM appointments AS a " +
                "INNER JOIN physicians AS c ON a.Physician_ID = c.Physician_ID where Appointment_ID = (select max(Appointment_ID) from appointments);";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String desc = rs.getString("Description");
            String loc = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int patID = rs.getInt("Patient_ID");
            int useID = rs.getInt("User_ID");
            int physID = rs.getInt("Physician_ID");
            String phyName = rs.getString("Physician_Name");

            CollectionLists.allAppointments.add(new Appointments(appID, title, desc, loc, type, start, end, patID, useID, physID, phyName));

        }
    }

    public static int appDelete(int appID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setInt(1, appID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int appUpdate(int appID, String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int patID, int useID, int phyID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Patient_ID = ?, User_ID = ?, Physician_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, desc);
        ps.setString(3, loc);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, patID);
        ps.setInt(8, useID);
        ps.setInt(9, phyID);
        ps.setInt(10, appID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void appUpdated(int appointID) throws SQLException {
        String sql = "SELECT * FROM appointments INNER JOIN physicians ON appointments.Physician_ID = physicians.Physician_ID WHERE Appointment_ID = ?;";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setInt(1, appointID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String desc = rs.getString("Description");
            String loc = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int patID = rs.getInt("Patient_ID");
            int useID = rs.getInt("User_ID");
            int phyID = rs.getInt("Physician_ID");
            String phyName = rs.getString("Physician_Name");

            CollectionLists.allAppointments.add(new Appointments(appID, title, desc, loc, type, start, end, patID, useID, phyID, phyName));
        }
    }






}
