package helper;

import model.States;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class StatesQuery {

    public static void stateSelect() throws SQLException {
        String sql = "SELECT * FROM states";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int sID = rs.getInt("State_ID");
            String sName = rs.getString("State");
            String sAbbr = rs.getString("State_Abbr");

            CollectionLists.allStates.add(new States(sID, sName, sAbbr));

        }
    }
}
