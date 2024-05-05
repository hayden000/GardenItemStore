import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
//Take all required imports

class GardenItemStore {
    //Create a new map called m and instansiate it
    public Map<String, ArrayList<String>> m;

    //Use arrayList becuase add items without coppying items to bigger list
    public GardenItemStore() {
        this.m = new HashMap<String, ArrayList<String>>();
    }

    //Returns true if the map contains the passed string
    public Boolean containsKey(String key) {
        return this.m.containsKey(key) ? true : false;
    }

    public void put(String key, String plant) {
        ArrayList<String> lst;
        if (this.m == null) {
            this.m = new HashMap<String, ArrayList<String>>();
        }
        if (m.containsKey(key)) { //If key is in map
            lst = this.m.get(key); //Get the key
            lst.add(plant); //Add the item to list
        } else {
            lst = new ArrayList<String>(); //Else create new list
            lst.add(plant); //Add the item to the list
            this.m.put(key, lst); //Put the new list with the key in map
        }
    }


    public String getRandomItem(String key) {
        ArrayList<String> list = new ArrayList<String>(); //This list will contain all that satisfy crieria
        for (ArrayList val : this.m.values()) { //Itterate through map
            for (Object item : val) { //Itterate though list in the map
                String flower = item.toString();
                if (flower.substring(0, key.length()).equalsIgnoreCase(key)) {
                    String suggestion = flower.substring(0, 1).toUpperCase() + flower.substring(1);
                    list.add(suggestion); //Add item as it meets criteria
                }
            }
        }
        if (!(list.isEmpty())) {
            Random randint = new Random();
            int refrence = randint.nextInt(list.size()); //Select random ordinal number in list
            return list.get(refrence); //Return a random item that meets criteria
        }
        return null;
    }

    public GardenItemStore(String filename) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) { //Put all items in the map
                line = reader.readLine();
                if (line != null) {
                    this.put(line.substring(0, 1).toLowerCase(), line.toLowerCase());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (reader != null) { //Only in the item was opened
                reader.close();
            }
        }
    }
}

