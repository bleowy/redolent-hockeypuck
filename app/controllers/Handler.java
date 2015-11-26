package controllers;

import game.Newspaper;

import java.util.List;

import user.*;


public class Handler {

	public Handler(){

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

	public void startExp(int playerId ,int expeditonId){
		new Expeditions(playerId,expeditonId).startExpedition();
	}

}
