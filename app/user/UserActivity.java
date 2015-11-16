package user;

import game.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UserActivity extends Game{
    //To do later
    private Boolean checkActivity(){
        return false;
    }

    public void UpdateActivity(int id){
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("UPDATE user_activity SET user_lastactivity_time = "+ getCurrentTime() +" WHERE id = "+ id);
        }catch (SQLException e){System.out.println(e);
        }finally{
         try{
             connect.close();
         }catch (SQLException e){}
        }
    }

    public void addActivity(int id){
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("INSERT INTO user_activity (`user_id`,`user_lastactivity_time`) VALUES (`" + id + "`," + getCurrentTime() + ")");
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
