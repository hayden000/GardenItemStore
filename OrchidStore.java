import java.util.*;
import java.io.*;

class OrchidStore extends GardenItemStore {
    public OrchidStore() {
        super(); //Super() for this class
    }

    @Override
    public String getRandomItem(String key) {
        return super.getRandomItem(key) + "(orchid)"; //Use from GardenItemStore
    }
}