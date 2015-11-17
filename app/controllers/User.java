package controllers;

import game.Newspaper;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import user.*;


public class User {

	public User(){

	}

    public UserObject getUser(int id){
        return new UserVariables().getVariables(id);
    }

	public List getNews(){
		return new Newspaper().fetchNewspaper();
	}

	public List getOnline(){		//Last Active players 5 minutes ago
		return new UserActivity().getNamesOfUsers();
	}
}
