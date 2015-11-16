package game;

import java.sql.Connection;
import java.sql.SQLException;

import play.*;
import play.db.DB;

public class Game{
	public Connection connect = DB.getConnection();


}