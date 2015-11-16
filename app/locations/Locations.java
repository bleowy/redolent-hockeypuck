package locations;

import controllers.Application;
import game.Game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Locations extends Game{
    Connection connect;
    ResultSet rs;
    Statement st;
    public Locations(){
        this.connect = super.connect;
    }

    public Location queryLocation(int x, int y){
        try{
            st = connect.createStatement();
            rs = st.executeQuery("SELECT * FROM locations WHERE locations_x = '" + x + "' AND locations_y ='" + y + "'");
            if(rs.next()){
                return new Location(rs.getString("locations_name"),rs.getString("locations_weather"),rs.getInt("locations_x"),rs.getInt("locations_y"));
            }
        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try{
                connect.close();
            }catch (SQLException e){

            }
            try{
                rs.close();
            }catch (SQLException e){

            }
            try{
                st.close();
            }catch (SQLException e){

            }
        }
        return null;

    }

}
