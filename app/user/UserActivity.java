package user;

import game.Game;

import java.text.ParseException;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class UserActivity extends Game{
    //To do later

    int id;

    public UserActivity(int id){
        this.id = id;
    }

    private ResultSet getLastActivity(){
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_activity WHERE user_id = " + id);

            return rs;
        }catch(SQLException e){

        }
            return null;
    }
    public Boolean checkActivity(){
        deleteActivity();
        try{
            if(getLastActivity().next()){
                updateActivity();
                return true;
            }else{
                addActivity();
            }
        }catch (SQLException | NullPointerException e){}

        return false;
    }

    private void updateActivity(){
        try{
            Statement st = connect.createStatement();
            st.executeUpdate("UPDATE user_activity SET user_lastactivity_time = '" + getCurrentTime() + "' WHERE user_id = "+ id);
        }catch (SQLException e){System.out.println(e);
        }finally{
         try{
             connect.close();
         }catch (SQLException e){}finally {
             try{
                 connect.close();
             }catch(SQLException e){}
         }
        }
    }

    private void addActivity(){
        try{
            System.out.println(getCurrentTime());
            Statement st = connect.createStatement();
            st.executeUpdate("INSERT INTO user_activity (user_id,user_lastactivity_time) VALUES ('" + id + "','" + getCurrentTime() + "')");
        }catch(SQLException e){
        }finally {
            try {
                connect.close();
            }catch (SQLException e){System.out.println(e);}
        }

    }

    private void deleteActivity(){
        String currentTime = getCurrentTime();
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_activity");
            if(rs.next()){
                String timeInDatabase = rs.getString("user_lastactivity_time");
                if(parseStringToDate(currentTime).getTime() - parseStringToDate(timeInDatabase).getTime() >= 5 * 60000){
                    st.executeUpdate("DELETE FROM user_activity WHERE user_id =" + rs.getInt("user_id"));
                }
            }
        }catch (SQLException e){

        }
    }

    private Date parseStringToDate(String a){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try{
        Date d1 = format.parse(a);
            return d1;

        }catch (ParseException e){System.out.println(e);}
            return null;

    }

    private String getCurrentTime(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            return dateFormat.format(cal.getTime());
    }

}
