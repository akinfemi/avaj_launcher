package avaj.vehicles;

import avaj.weather.WeatherTower;
import avaj.weather.Writer;

import java.io.BufferedWriter;
import java.io.IOException;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private BufferedWriter printer = null;
    JetPlane (String name, Coordinates coordinates){
        super(name,coordinates);
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
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                break;
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                try{
                    printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                            + this.getId() +")" +"Its raining. Watch out for lightnings\n");
                }catch (IOException e){
                    System.out.println(e);
                }
                break;
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 7);
                try{
                    printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                            + this.getId() +")" +" OMG! Winter is coming!\n");
                }catch (IOException e){
                    System.out.println(e);
                }
                if (coordinates.getHeight() < 0){
                    try{
                        printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                                + this.getId() +")" +" landing\n");
                    }catch (IOException e){
                        System.out.println(e);
                    }
                    weatherTower.unregister(this);
                }
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
}