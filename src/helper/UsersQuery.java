package helper;

import model.Physicians;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UsersQuery {

    public static void userSelect() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int useID = rs.getInt("User_ID");
            String useName = rs.getString("User_Name");
            String pass = rs.getString("Password");

            CollectionLists.addUser(new Users(useID, useName, pass));

        }
    }

    public static boolean userLogin (String user, String pass) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = ClientSchedule.connection.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }
}
