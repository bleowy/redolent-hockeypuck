package locations;

import locations.Locations;



public class Location {
    private String name;
    private String weather;
    private int x;
    private int y;

    public Location(String name,String weather, int x,int y){
        this.name = name;
        this.weather = weather;
        this.x = x;
        this.y = y;
    }

    public String getName(){
        return name;
    }
    public String getWeather(){
        return weather;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }


}
