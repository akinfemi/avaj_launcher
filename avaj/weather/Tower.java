package avaj.weather;

import avaj.vehicles.Flyable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tower {

    private List<Flyable> observers = new ArrayList<>();
    private List<Flyable> landed = new ArrayList<>();
    private BufferedWriter printer = null;

    public void register(Flyable flyable){
        try {
            printer = Writer.getWriter();
        }catch (IOException e){
            System.out.println(e);
        }
        observers.add(flyable);
        try{
            printer.write("Tower says: "+flyable.getClass().getSimpleName() + "#" + flyable.getName() + "("
                    + flyable.getId() +")" +" registered to weather tower.\n");
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void unregister (Flyable flyable){
        try{
            printer.write("Tower says: "+ flyable.getClass().getSimpleName() + "#" + flyable.getName() + "("
                    + flyable.getId()+")" +" unregistered\n");
        }catch (IOException e){
            System.out.println(e);
        }
    }

    protected void conditionsChanged(){
        for(Flyable flyable : observers){
            flyable.updateConditions();
            if (flyable.getCoordinates().getHeight() <= 0){
                landed.add(flyable);
            }
        }
        observers.removeAll(landed);
    }
}