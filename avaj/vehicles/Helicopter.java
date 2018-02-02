package avaj.vehicles;

import avaj.weather.WeatherTower;
import avaj.weather.Writer;

import java.io.BufferedWriter;
import java.io.IOException;

public class Helicopter extends Aircraft implements Flyable {
    
    private WeatherTower weatherTower;
    private BufferedWriter printer = null;

    Helicopter (String name, Coordinates coordinates){
        super(name, coordinates);
    }
    public void updateConditions(){
        try{
            printer = Writer.getWriter();
        }catch (IOException e){
            System.out.println(e);
        }
        String currentWeather = weatherTower.getWeather(coordinates);
        switch (currentWeather){
            case "SUN":
                coordinates.setHeight(coordinates.getHeight() + 2);
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                try{
                    printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                            + this.getId() +")" +" This is hot.\n");
                }catch (IOException e){
                    System.out.println(e);
                }
                break;
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                break;
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 12);
                try{
                    printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                            + this.getId() +")" +" My rotor is going to freeze!\n");
                }catch (IOException e){
                    System.out.println(e);
                }
                if (coordinates.getHeight() <= 0){
                    try{
                        printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                                + this.getId() +")" +" landing\n");
                    }catch (IOException e){
                        System.out.println(e);
                    }
                    weatherTower.unregister(this);
                }
                break;
        }
    }
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public Coordinates getCoordinates() {
        return super.getCoordinates();
    }
    @Override
    public long getId() {
        return super.getId();
    }
}