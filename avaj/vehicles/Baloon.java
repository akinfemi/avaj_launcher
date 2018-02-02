package avaj.vehicles;

import avaj.weather.WeatherTower;
import avaj.weather.Writer;

import java.io.BufferedWriter;
import java.io.IOException;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private BufferedWriter printer = null;
    Baloon (String name, Coordinates coordinates){
        super(name, coordinates);
    }
    public void updateConditions(){
        try{
            printer =  Writer.getWriter();
        }catch (IOException e){
            System.out.println(e);
        }
        String currentWeather = weatherTower.getWeather(coordinates);
        switch (currentWeather){
            case "SUN":
                coordinates.setHeight(coordinates.getHeight() + 4);
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                try{
                    printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                            + this.getId() +")" +" Damn you rain! You messed up my baloon.\n");
                }catch (IOException e){
                    System.out.println(e);
                }
                break;
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                if (coordinates.getHeight() < 0){

                    try{
                        printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                                + this.getId() +")" +" Baloon landing\n");
                    }catch (IOException e) {
                        System.out.println(e);
                    }
                    weatherTower.unregister(this);
                }
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 15);
                try{
                    printer.write(this.getClass().getSimpleName() + "#" + this.getName() + "("
                            + this.getId() +")" +" Its snowing. We're gonna crash.\n");
                }catch (IOException e) {
                    System.out.println(e);
                }
                if (coordinates.getHeight() < 0){
                    try{
                        printer.write(this.getClass().getName() + "#" + this.getName() + "("
                                + this.getId() +")" +" landing\n");
                    }catch (IOException e) {
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