package expeditions;

import locationList.AbaddonHospital;

/**
 * Created by patrick on 11/18/15.
 */
public class Expeditions {

    private int playerId;
    private int locationId;

    public Expeditions(int playerId,int locationId){
        this.playerId = playerId;
        this.locationId = locationId;
    }

    public Expedition getExpedition(){
        if(locationId == 1){
            AbaddonHospital ah = new AbaddonHospital();
            return new Expedition(playerId,ah.getGoldReward(),ah.getDuration(),ah.getChance());
        }
        return null;
    }

    public Boolean checkExpedition(){
        return false;
    }

}
