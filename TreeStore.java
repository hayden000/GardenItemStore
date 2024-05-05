import java.util.*;
import java.io.*;

class TreeStore extends GardenItemStore {
    public TreeStore() {
        super(); //Tell it which one is super()
    }

    @Override
    public String getRandomItem(String key) throws NumberFormatException {
        //Remove everything up to last space from string
        String value = super.getRandomItem(key).substring(super.getRandomItem(key).lastIndexOf(' ') + 1).trim();
        value = value.substring(0, value.length() - 1);
        int val;
        val = Integer.parseInt(value);
        //Based on size return string with tree height type
        if (80 < val) {
            return super.getRandomItem(key) + "(very tall tree)";
        } else if (val > 15) {
            return super.getRandomItem(key) + "(tall tree)";
        }
        return super.getRandomItem(key) + "(small tree)";
    }
}