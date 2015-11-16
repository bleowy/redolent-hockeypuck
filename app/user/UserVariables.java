package user;

import game.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserVariables extends Game {

    private ResultSet rs;

    public UserObject getVariables(int id) {
        try {
            Statement st = connect.createStatement();
            rs = st.executeQuery("SELECT * FROM users WHERE id ='" + id + "'");
            if (rs.next()){
                return new UserObject(rs.getString("user_login"), rs.getInt("user_x"), rs.getInt("user_y"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {}
            try {
                rs.close();
            } catch (SQLException e) {}
        }
        return null;
    }

}
