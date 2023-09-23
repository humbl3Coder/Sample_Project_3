package helper;

import model.Coverage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CoverageQuery {

    public static void covSelect() throws SQLException {
        String sql = "SELECT * FROM coverage";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int covID = rs.getInt("Coverage_ID");
            String cov = rs.getString("Coverage");

            CollectionLists.allCoverage.add(new Coverage(covID, cov));

        }
    }
}
