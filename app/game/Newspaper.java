package game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 11.11.15.
 */
public class Newspaper extends Game {
    public Connection connect;

    public Newspaper(){
        this.connect = super.connect;
    }

    public List<String> fetchNewspaper(){
        try{
            ResultSet rs;
            List<String> newspaper = new ArrayList<String>();
            Statement st = connect.createStatement();
            rs = st.executeQuery("SELECT * FROM newspaper ORDER BY id DESC");
            while(rs.next()){
                newspaper.add(rs.getString("news"));
            }
            return newspaper;
        }catch(SQLException e) {
            System.out.println("fetchNewspaper error:" + e);
        }finally {
            try{
                connect.close();
            }catch(SQLException e){

            }
        }
        return null;
    }

}
