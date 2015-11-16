package user;

import game.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by patrick on 11/16/15.
 */
public class UserActivity extends Game{

    private Boolean checkActivity(){
        return false;
    }

    public void addActivity(int id){
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("INSERT INTO user_activity (`user_id`,`user_lastactivity_time`) VALUES()");
        }catch(SQLException e){

        }finally {
            try {
                connect.close();
            }catch (SQLException e){}
        }

    }

    private String getCurrentTime(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            return dateFormat.format(cal.getTime());
    }

}
