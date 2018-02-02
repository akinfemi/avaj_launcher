package avaj.weather;

import avaj.vehicles.AircraftFactory;
import avaj.vehicles.Flyable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<Flyable>();
    private static BufferedWriter writer = null;

    public static void main (String[] args) throws InterruptedException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            writer = Writer.getWriter();
            String line = reader.readLine();
            if (line != null){
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
                if (simulations < 0){
                    System.out.println("Invalid simulations count " + simulations);
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null) {
                    Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0],
                            line.split(" ")[1], Integer.parseInt(line.split(" ")[2]),
                            Integer.parseInt(line.split(" ")[3]), Integer.parseInt(line.split(" ")[4]));
                    flyables.add(flyable);
                }
                for (Flyable flyable: flyables){
                    flyable.registerTower(weatherTower);
                }
                for (int i = 0; i <= simulations; i++){
                    weatherTower.changeWeather();
                }
                writer.close();
            }
        }catch (FileNotFoundException e){
            System.out.println("Could not find file "+ args[0]);
        }catch (IOException io){
            System.out.println("File could not be read");
        }catch (ArrayIndexOutOfBoundsException a){
            System.out.println("Array Out of bounds "+a);
        }
    }
}
