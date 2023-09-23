package helper;

import model.Insured;
import model.Uninsured;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PatientsQuery {

    public static void patSelect() throws SQLException {

        String sql = "SELECT a.Patient_ID, a.Patient_Name, a.Address, a.Postal_Code, a.State_ID, c.State, a.Phone, b.Coverage_ID, b.Coverage, a.Carrier," +
                " a.Discounted_Rate FROM patients AS a INNER JOIN coverage AS b ON a.Coverage_ID = b.Coverage_ID" +
                " INNER JOIN states AS c ON a.State_ID = c.State_ID";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int patID = rs.getInt("Patient_ID");
            String patName = rs.getString("Patient_Name");
            String patAdd = rs.getString("Address");
            String patPos = rs.getString("Postal_Code");
            int sID = rs.getInt("State_ID");
            String stateName = rs.getString("State");
            String patPhone = rs.getString("Phone");
            int covID = rs.getInt("Coverage_ID");
            String cov = rs.getString("Coverage");
            String carrier = rs.getString("Carrier");
            String discRate = rs.getString("Discounted_Rate");

            if (carrier == null) {
                CollectionLists.addPatient(new Uninsured(patID, patName, patAdd, patPos, sID, stateName, patPhone, covID, cov, discRate));
            }

            else {
                CollectionLists.addPatient(new Insured(patID, patName, patAdd, patPos, sID, stateName, patPhone, covID, cov, carrier));
            }
        }
    }

    public static int patUpdate(int patID, String patName, String patAdd, String patPos, int sID, String patPhone, int covID, String carrier, String discRate) throws SQLException {
        String sql = "UPDATE patients SET Patient_Name = ?, Address = ?, Postal_Code = ?, State_ID = ?, Phone = ?, Coverage_ID = ?, Carrier = ?, Discounted_Rate = ? WHERE Patient_ID = ?";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setString(1, patName);
        ps.setString(2, patAdd);
        ps.setString(3, patPos);
        ps.setInt(4, sID);
        ps.setString(5, patPhone);
        ps.setInt(6, covID);
        ps.setString(7, carrier);
        ps.setString(8, discRate);
        ps.setInt(9, patID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int patInsert(String patName, String patAdd, String patPos, int sID, String patPhone, int covID, String carrier, String discRate) throws SQLException {
        String sql = "INSERT INTO patients (Patient_Name, Address, Postal_Code, State_ID, Phone, Coverage_ID, Carrier, Discounted_Rate) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setString(1, patName);
        ps.setString(2, patAdd);
        ps.setString(3, patPos);
        ps.setInt(4, sID);
        ps.setString(5, patPhone);
        ps.setInt(6, covID);
        ps.setString(7, carrier);
        ps.setString(8, discRate);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int patDelete(int patID) throws SQLException {
        String sql = "DELETE FROM patients WHERE Patient_ID = ?";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setInt(1, patID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void patAdded() throws SQLException {
        String sql = "SELECT a.Patient_ID, a.Patient_Name, a.Address, a.Postal_Code, a.State_ID, b.State, a.Phone, a.Coverage_ID, c.Coverage, a.Carrier, a.Discounted_Rate FROM patients AS a " +
                "INNER JOIN states AS b ON a.State_ID = b.State_ID " +
                "INNER JOIN coverage AS c ON a.Coverage_ID = c.Coverage_ID " +
                "WHERE Patient_ID = (SELECT max(Patient_ID) FROM patients);";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int patID = rs.getInt("Patient_ID");
            String patName = rs.getString("Patient_Name");
            String patAdd = rs.getString("Address");
            String patPos = rs.getString("Postal_Code");
            int sID = rs.getInt("State_ID");
            String stateName = rs.getString("State");
            String patPhone = rs.getString("Phone");
            int covID = rs.getInt("Coverage_ID");
            String cov = rs.getString("Coverage");
            String carrier = rs.getString("Carrier");
            String discRate = rs.getString("Discounted_Rate");

            if (carrier == null) {
                CollectionLists.addPatient(new Uninsured(patID, patName, patAdd, patPos, sID, stateName, patPhone, covID, cov, discRate));
            }

            else {
                CollectionLists.addPatient(new Insured(patID, patName, patAdd, patPos, sID, stateName, patPhone, covID, cov, carrier));
            }

        }
    }

    public static void patUpdated(int patUID) throws SQLException {
        String sql = "SELECT * FROM patients INNER JOIN states ON patients.State_ID = states.State_ID " +
                "INNER JOIN coverage ON patients.Coverage_ID = coverage.Coverage_ID WHERE Patient_ID = ?;";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setInt(1, patUID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int patID = rs.getInt("Patient_ID");
            String patName = rs.getString("Patient_Name");
            String patAdd = rs.getString("Address");
            String patPos = rs.getString("Postal_Code");
            int sID = rs.getInt("State_ID");
            String stateName = rs.getString("State");
            String patPhone = rs.getString("Phone");
            int covID = rs.getInt("Coverage_ID");
            String cov = rs.getString("Coverage");
            String carrier = rs.getString("Carrier");
            String discRate = rs.getString("Discounted_Rate");

            if (carrier == null) {
                CollectionLists.addPatient(new Uninsured(patID, patName, patAdd, patPos, sID, stateName, patPhone, covID, cov, discRate));
            }

            else {
                CollectionLists.addPatient(new Insured(patID, patName, patAdd, patPos, sID, stateName, patPhone, covID, cov, carrier));
            }
        }

    }

}
