package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import play.db.*;

public class Login {
	
	ResultSet rs;
	Connection connect;
	
	public Login(Connection connect){
		this.connect = connect;
	}
	
	
	public int login(String username, String password){
	try{
		Statement stmt = connect.createStatement();
		rs = stmt.executeQuery("SELECT id FROM users WHERE user_login = '" + username + "' AND user_password = '" + password + "'");
			if(rs.next()){
				return rs.getInt("id");
			}
	}catch(Exception e){
		System.out.println(e);
	}
	return 0;
	}
	
	public boolean checkSession(String session){
		int i = parseSession(session);
		
		if(i > 0){
			return true;
		}
		
		return false;
	}

	public int parseSession(String session){
		int i = Integer.parseInt(session);

		return i;
	}
}
