package user;

import game.Game;

import java.sql.Statement;
import java.sql.SQLException;

/**
 * Created by patrick on 11/21/15.
 */
public class UserUpdate extends Game {
    int id;

    public UserUpdate(int id){
        this.id = id;
    }

    public void updateMysql(int amount,String row,String table){
        try{
            Statement st = connect.createStatement();
            st.executeUpdate("UPDATE" + table + "SET" + row + "=" + amount + "WHERE id =" + id);
        }catch (SQLException e) {
            System.out.println("updateUser()" + e);
        }finally {
            try {
                connect.close();
            }catch (SQLException e){}
        }
    }

    public void updateMysql(String text,String row,String table){
        try{
            Statement st = connect.createStatement();
            st.executeUpdate("UPDATE" + table + "SET" + row + "=" + text + "WHERE id =" + id);
        }catch (SQLException e) {
            System.out.println("updateUser()" + e);
        }finally {
            try {
                connect.close();
            }catch (SQLException e){}
        }
    }
}
