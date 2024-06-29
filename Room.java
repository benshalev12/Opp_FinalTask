

// This class is used to create Room objects.
// Each Room object has a room type, bed count, balcony, private bathroom, price per night, room number, and a list of booked dates. 
//The class also has methods to add and remove booked dates from the list of booked dates.
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String roomType;
    private int bedCount;
    private boolean hasBalcony;
    private boolean hasPrivateBathroom;
    private double pricePerNight;
    private int roomNumber;
    private List<Integer> bookedDates = new ArrayList<>(); 
   
    
    //constructor
    public Room(String roomType, int bedCount, boolean hasBalcony, boolean hasPrivateBathroom, double pricePerNight, int roomNumber) {
        this.roomType = roomType;
        this.bedCount = bedCount;
        this.hasBalcony = hasBalcony;
        this.hasPrivateBathroom = hasPrivateBathroom;
        this.pricePerNight = pricePerNight;
        this.roomNumber = roomNumber;
    }

    //getters
    public String getRoomType() {
        return roomType;
    }

    public int getBedCount() {
        return bedCount;
    }

    public boolean hasBalcony() {
        return hasBalcony;
    }

    public boolean hasPrivateBathroom() {
        return hasPrivateBathroom;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
    public Room getRoom() {
        return this;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public List<Integer> getBookedDates() {
        return bookedDates;
    }

    public void addBookedDate(int date) {
        bookedDates.add(date);
    }

    public void removeBookedDate(int date) {
        bookedDates.remove(Integer.valueOf(date));
    }
}
