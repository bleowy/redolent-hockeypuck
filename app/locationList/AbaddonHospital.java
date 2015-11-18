package locationList;

/**
 * Created by patrick on 11/18/15.
 */
public class AbaddonHospital implements LocationExample {

    String name = "Abaddon Hospital";
    String duration = "";
    int chance = 30;
    int goldReward = 5;

    @Override
    public int getGoldReward() {
        return goldReward;
    }

    @Override
    public String getDuration() {
        return duration;
    }

    @Override
    public int getChance() {
        return chance;
    }

    @Override
    public String getLocationName() {
        return name;
    }
}
