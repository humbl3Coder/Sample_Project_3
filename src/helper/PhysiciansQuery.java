package helper;

import model.Physicians;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PhysiciansQuery {

    public static void physicianSelect() throws SQLException {
        String sql = "SELECT * FROM physicians";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int physID = rs.getInt("Physician_ID");
            String pName = rs.getString("Physician_Name");
            String pEmail = rs.getString("Email");

            CollectionLists.addPhysician(new Physicians(physID, pName, pEmail));

        }
    }
}
