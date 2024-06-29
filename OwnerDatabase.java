

//Purpose: This class is a singleton class that stores all the owners in a HashMap.
//the key of the HashMap is the owner's id and the value is the owner object.
//It has methods to add, get and remove owners from the database.

import java.util.HashMap;
import java.util.Map;

public class OwnerDatabase {
    private static OwnerDatabase instance;
    private Map<Long, OwnerHotel> owners;

    private OwnerDatabase() {
        owners = new HashMap<>();
    }

    //singleton pattern
    public static OwnerDatabase getInstance() {
        if (instance == null) {
            instance = new OwnerDatabase();
        }
        return instance;
    }

    public void addOwner(OwnerHotel owner) {
        owners.put(owner.id, owner);
    }

    public OwnerHotel getOwner(Long id) {
        return owners.get(id);
    }

    public void removeOwner(Long id) {
        owners.remove(id);
    }
}