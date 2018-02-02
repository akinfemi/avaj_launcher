package avaj.weather;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer extends BufferedWriter {
    private static Writer writer = null;

    private Writer() throws IOException {
        super(new FileWriter("Simulation.txt"));
    }

    public static BufferedWriter getWriter() throws IOException{
        if (writer == null)
            writer = new Writer();
        return writer;
    }
}
