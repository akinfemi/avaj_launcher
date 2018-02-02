package avaj.weather;

import avaj.vehicles.Coordinates;

public class WeatherProvider {
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static final WeatherProvider weatherProvider = new WeatherProvider();

    private WeatherProvider(){}

    public static WeatherProvider getProvider(){
        return weatherProvider;
    }
    public String getCurrentWeather(Coordinates coordinates) {
        int index = (coordinates.getLatitude() + coordinates.getHeight() + coordinates.getLongitude()) % 4;
        index = (index < 0) ? 0: index;
        return (weather[index]);
    }
}