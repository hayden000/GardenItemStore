import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

//Import all required modules
public class GardenItemStoreTest {
    public GardenItemStoreTest() {
        //All entities should have constructor
    }

    public static void main(String[] args) throws AssertionError, IOException {
        GardenItemStore store = new GardenItemStore();
        //Add the items asked for
        store.put("a", "azalea");
        store.put("ba", "burning bush");
        store.put("ba", "bursting heart");
        store.put("a", "amur chokecherry");
        //Throw error if a random one was not found
        if (!store.m.containsKey("amur chokecherry")) {
            throw new java.lang.AssertionError(); //but -> throws AssertionError so doesn't hault
        }
        //Select an item from the file and see if it exisits
        GardenItemStore storeNew = new GardenItemStore("orchids.txt");
        try {
            File file = new File("orchids.txt");
            Scanner reader = new Scanner(file);
            String word = reader.nextLine();
            if (!storeNew.m.containsKey(word)) {
                throw new java.lang.AssertionError();

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found try a diffrent one" + e);
        }
        System.out.println(storeNew);
    }
}