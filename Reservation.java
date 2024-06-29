


//this class is responsible for creating a reservation object and updating the price of the reservation.(using the decorator pattern)
//It also implements the observer interface to get messages from the hotel class.(observer pattern)
import java.util.ArrayList;
import java.util.List;


public class Reservation  implements Observer {
    private Hotel hotel;
    private Room room;
    private String roomType;
    private int startDay;
    private int endDay;
    private double price;
    private boolean breakfast;
    private boolean lunch;
    private boolean dinner;
    private int numberOfAdults;
    private int numberOfChildren;
     int reservationID ;
     protected List<String> messages = new ArrayList<>();

    //constructor
    public Reservation(Hotel hotel, String roomType, int startDay, int endDay, double price, boolean breakfast, boolean lunch, boolean dinner, int numberOfAdults, int numberOfChildren, Room room) {
        this.room = room;
        this.hotel = hotel;
        this.roomType = roomType;
        this.startDay = startDay;
        this.endDay = endDay;
        this.price = price;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        reservationID=-1;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    
    }
   
    //getters
    public Hotel getHotel() {
        return hotel;
    }

    public String getRoomType() {
        return roomType;
    }
    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
    public int getReservationID() {
        return reservationID;
    }  
     

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public double getPrice() {
        return price;
    }
    public void updatePrice() {
         this.price=this.price*(endDay-startDay);
    }
    public boolean hasBreakfast() {
        return breakfast;
    }

    public boolean hasLunch() {
        return lunch;
    }

    public boolean hasDinner() {
        return dinner;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }
    public Room getRoom() {
        return room;
    }

    //update the messages
  

   
    //get the messages
    public void getMessages() {
        if (messages.isEmpty()) {
            System.out.println("There are no messages");
        }
    
       
        for (String message : messages) {
            System.out.println(message);
        }
       
    }

    public void clearMessages() {
        messages.clear();
    }

    @Override
    public void update( String message) {
        messages.add(message);}
    
}
