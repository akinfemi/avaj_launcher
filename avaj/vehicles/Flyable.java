package avaj.vehicles;

import avaj.weather.WeatherTower;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
    public String getName();
    public Coordinates getCoordinates();
    public long getId();
}