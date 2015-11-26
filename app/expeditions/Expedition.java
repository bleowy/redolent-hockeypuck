package expeditions;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Expedition{
    private int expeditionId;
    private int goldReward;
    private int playerId;
    private int duration;
    private int chance;
    private Connection connection = DB.getConnection();

    /**
     * This class contains a functions to control progress of Expedition
     *
     * @param expeditionId id of a expedition.
     * @param playerId  id of a player.
     */
    public Expedition(int expeditionId,int playerId){
        this.expeditionId = expeditionId;
        this.playerId = playerId;
        try{
            ResultSet rs = obtainExpeditionInfo();
            this.goldReward = rs.getInt("expeditionGold");
            this.duration = rs.getInt("expeditionDuration");
            this.chance = rs.getInt("expeditionChance");
        }catch (SQLException e){}

    }

    public Expedition(int playerId){
        this.playerId = playerId;
        try{
            ResultSet rs = obtainExpeditionInfo();
            this.goldReward = rs.getInt("expeditionGold");
            this.duration = rs.getInt("expeditionDuration");
            this.chance = rs.getInt("expeditionChance");
        }catch (SQLException e){}
    }

    /**
     *
     * It start the expedition, make sure that you checked some values(doesOtherExpeditionExist()) before you use that method.
     *
     * @return It returns true if expedition starts
     */
    protected boolean startExpedition(){
        if(doesOtherExpeditionExist() == false){
            insertExpedition();
            return true;
        }
        return false;
    }

    /**
     * Use this method to end expeditions.
     * @return
     */
    protected boolean isPlayerReadyToEndExpedition(){
        if(doesOtherExpeditionExist()){
            if(isExpeditionDone()){
                endExpedition();
            }
        }else{
            return false;
        }
        return false;
    }

    /**
     * This is a main method of this class It ends a expedition ofc.
     */
    private void endExpedition(){
        System.out.println("Expedition done"); //it is only here for testing purposes;
        deleteExpedition();
    }

    private void insertExpedition(){
        try{
            Statement st = connection.createStatement();
            st.execute("INSERT INTO expeditions (`userId`,`expeditionTime`,`expeditionId`) VALUES" +
                    " ('"+ playerId +"','" + new DateTime().plusMinutes(duration).toString() +"','"+ expeditionId +"')");
        }catch (SQLException e){}
    }

    private void deleteExpedition(){
        try{
            Statement st = connection.createStatement();
            st.execute("DELETE FROM expeditions WHERE userId =" + playerId);
        }catch (SQLException e){}
    }

    /**
     * @return It returns true if expedition is done.
     */
    private boolean isExpeditionDone(){
        try{
            DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss");
            DateTime timeInDatabase = formatter.parseDateTime(obtainExpeditionsRecords().getTimestamp("expeditionTime").toString());

            DateTime now = new DateTime();
            Period diffrence = new Period(now,timeInDatabase);
            if(diffrence.getMinutes() >= duration){
                return true;
            }
        }catch (SQLException e){}
        return false;
    }
    /**
     * This method have a big meaning in this class.
     * It obtains a data from database.
     *
     * @return it returns a ResultSet which contains all the data from expeditionList, it also can return null if expeditionId was wrong.
     */
    private ResultSet obtainExpeditionInfo(){
        try{
          Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM expeditionList WHERE expeditionId =" + expeditionId);
            return rs;
        }catch (SQLException e){

        }
        return null;
    }

    /**
     * @return  It returns a ResultSet which contains a player records from expeditions table.
     */

    private ResultSet obtainExpeditionsRecords(){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM expeditions WHERE userId =" + playerId);
            if(rs.next()){}
            return rs;
        }catch (SQLException e){}
        return null;
    }

    /**
     * @return It returns true if player is in Expedition or false when he didn't join any expedition.
     */
    private boolean doesOtherExpeditionExist(){
        try{
            if(obtainExpeditionInfo().wasNull()){
                return false;
            }
        }catch (SQLException e){}

        return true;
    }

    /**
     *  Always remember to use that method after you're done with EVERY Expedition object you create.
     *  @return closes a connection to database in Expedition class.
     */
    public void closeConnection(){
        try{
            connection.close();
        }catch (SQLException e){

        }
    }



}
