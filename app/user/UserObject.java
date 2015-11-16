package user;

import locations.Locations;

/**
 * Created by patrick on 11/15/15.
 */
public class UserObject {
    private String name;
    private int x;
    private int y;


    public UserObject(String name, int x,int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName(){
        return name;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getLocation(){
        return new Locations().queryLocation(x,y).getName();
    }

    public String getLocationWeather(){
        return new Locations().queryLocation(x,y).getWeather();
    }


}
