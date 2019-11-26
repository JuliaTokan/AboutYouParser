package parser;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AboutYouParser parser = new AboutYouParser();
        List<Item> items = parser.parse("https://www.aboutyou.de/maenner/bekleidung");

        writeToFile(items);
    }

    private static void writeToFile(List<Item> items) {
        Gson gson = new Gson();
        String json = gson.toJson(items);

        try {
            FileWriter fileWriter = new FileWriter("result_items.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(json);

            writer.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Oops... Unable to write to file");
        }
    }
}
