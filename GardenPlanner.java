import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

//Import all required modules
class GardenPlanner {
    public GardenItemStore garden;
    public GardenItemStore orchid;
    public GardenItemStore tree;
    public GardenItemStore shrub;

    //Create fields
    public GardenPlanner() throws IOException {
        garden = new GardenItemStore();
        //Use @Override
        orchid = new GardenItemStore("orchids.txt");
        tree = new GardenItemStore("trees.txt");
        shrub = new GardenItemStore("shrubs.txt");
    }

    public ArrayList<String> generate(String input) throws IOException, NumberFormatException {
        ArrayList<String> plan;
        plan = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            Random randint = new Random();
            int refrence = randint.nextInt(3);
            String filename; //Reopen files to use them with this new class
            if (refrence == 0) {
                filename = "orchids.txt";
            } else if (refrence == 1) {
                filename = "trees.txt";
            } else {
                filename = "shrubs.txt";
            }
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    if (input.substring(0, 1).equalsIgnoreCase(line.substring(0, 1))) {
                        if (refrence == 0) { //orchid
                            line = line + "(orchid)";
                            plan.add(line);
                        } else if (refrence == 1) { //tree
                            String value = line.substring(line.lastIndexOf(' ') + 1).trim();
                            value = value.substring(0, value.length() - 1);
                            int val;
                            val = Integer.parseInt(value);
                            if (80 < val) {
                                plan.add(line.substring(0, line.lastIndexOf(' ') + 1) + "(very tall tree)");
                            } else if (val > 15) {
                                plan.add(line.substring(0, line.lastIndexOf(' ') + 1) + "(tall tree)");
                            } else {
                                plan.add(line.substring(0, line.lastIndexOf(' ') + 1) + "(small tree)");
                            }
                        } else { //shrub plant
                            plan.add(line);
                        }
                    }
                }
            }
        } catch (IOException e) { //No file
            System.err.println(e.getMessage());
        } finally {
            if (reader != null) { //Attempt to close a file when opened
                reader.close();
            }
        }
        return plan; //Return the list
    }

    public static void main(String[] args) throws IOException, NumberFormatException {
        ArrayList<String> output;
        output = new ArrayList<String>();
        boolean criteria = false;
        //Use classes already made
        GardenItemStore PlantStore = new GardenItemStore("shrubs.txt");
        GardenItemStore TreeStore = new GardenItemStore("orchids.txt");
        GardenItemStore OrchidStore = new GardenItemStore("trees.txt");
        while (!criteria) {
            output.clear();
            if (args.length == 0) {
                criteria = true;
            }
            for (int i = 0; i < args[0].length(); i++) {
                Boolean condition = false;
                while (!condition) {
                    GardenPlanner item = new GardenPlanner();
                    Random randint = new Random();
                    ArrayList<String> temp;
                    temp = new ArrayList<String>();
                    temp = item.generate(args[0].substring(i, i + 1));
                    int refrenceValue = randint.nextInt(temp.size());
                    int letter = 0;
                    if (!(refrenceValue == 0)) {
                        if (args.length > 1) {
                            String plantItem = args[0].substring(0, Integer.parseInt(args[1]));
                            letter = 0;
                            for (int j = 0; j < plantItem.length(); j++) {
                                if (plantItem.substring(j, j + 1).equalsIgnoreCase(temp.get(refrenceValue).toString().substring(j, j + 1))) {
                                    letter = j; //Number of characters from start than need to be uppercase
                                }
                            }
                            output.add(temp.get(refrenceValue).toString().substring(0, letter + 1).toUpperCase() + temp.get(refrenceValue).toString().substring(letter + 1));
                            condition = true;
                            i = i + letter; //Move on to next letters
                        } else {
                            output.add(temp.get(refrenceValue).toString().substring(0, letter).toUpperCase() + temp.get(refrenceValue).toString().substring(letter));
                            condition = true; //Just use 1 letter at a time
                        }
                    }
                }
            }
            //Count how many times each type was used
            int orchidCount = 0;
            int treeCount = 0;
            int shrubCount = 0;
            int plantCount = 0;
            for (String item : output) {
                plantCount++;
                //Based on what the string contains
                if (item.contains("(orchid)")) {
                    orchidCount++;
                } else if (item.contains("(small tree)") || item.contains("(tall tree)") || item.contains("(very tall tree)")) {
                    treeCount++;
                } else {
                    shrubCount++;
                }
            }
            //If it meets plan criteria
            if (plantCount >= 1 && shrubCount >= 1 && orchidCount >= treeCount) {
                criteria = true;
            } else if (plantCount >= 2 && orchidCount >= 1 && shrubCount > 1 && orchidCount >= treeCount) {
                criteria = true;
            } else if (plantCount >= 3 && treeCount >= 1 && shrubCount >= 1 && orchidCount > 1 && orchidCount >= treeCount) {
                criteria = true;
            } else {
                if (output.contains(null)) {
                    System.err.println("Exception no match found");
                    criteria = true;
                }
            }
        }
        //Print each to a new line
        if (!(output.contains(null))) {
            for (String outputItem : output) {
                System.out.println(outputItem);
            }
        }
    }
}