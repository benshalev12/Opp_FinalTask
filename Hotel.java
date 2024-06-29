


// This class represents a hotel with its properties and methods
// It implements the Observer interface to be able to send notifications to the customers
//for each hotel has a list of rooms and reservations
//the hotel can add rooms and book rooms
import com.sun.source.doctree.SystemPropertyTree;
import java.util.*;
public class Hotel implements Observer {
    public final String name;
    public final boolean hasParking;
    public final boolean hasWifi;
    public final boolean allowsPets;
    protected double rating=0;
    protected final int stars;
    protected final String city;
    protected final String country;
    protected double pricePerNight;
    protected boolean hasBreakfast;
    protected boolean hasLunch;
    protected final boolean spa;
    protected final boolean gym;
    protected final boolean bar;
    protected  boolean hasDinner;
    protected final boolean hasSwimmingPool;
    protected final double distanceFromCityCenter;
    private  int ratingCounter=0;
    protected Map<String, List<Room>> rooms = new HashMap<>();
    protected Map<Integer, List<Reservation>> reservations = new HashMap<>();
    protected double adultPrice;
    protected double childPrice;
    protected double mealCost ;
    private List<String> observers = new ArrayList<>();



    //constructor
    public Hotel(String name, boolean hasParking, boolean hasWifi, boolean allowsPets, 
                 int stars, String city, String country, double pricePerNight,
                 boolean hasBreakfast, boolean hasLunch, boolean spa, boolean gym, boolean bar, boolean hasDinner, boolean hasSwimmingPool,
                 double distanceFromCityCenter, int ratingCounter, double adultPrice, double childPrice,double mealCost ) {
        this.name = name;
        this.hasParking = hasParking;
        this.hasWifi = hasWifi;
        this.allowsPets = allowsPets;
        this.rating = rating;
        this.stars = stars;
        this.city = city;
        this.country = country;
        this.pricePerNight = pricePerNight;
        this.hasBreakfast = hasBreakfast;
        this.gym = gym;
        this.bar = bar;
        this.spa = spa;
        this.hasLunch = hasLunch;
        this.hasDinner = hasDinner;
        this.hasSwimmingPool = hasSwimmingPool;
        this.distanceFromCityCenter = distanceFromCityCenter;
        this.ratingCounter = ratingCounter;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.mealCost = mealCost;
    }
    //getters
    public double getMealCost() {
        return mealCost;
        
    }
    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public boolean hasSwimmingPool() {
        return hasSwimmingPool;
    }

    public boolean hasSpa() {
        return spa;
    }

    public boolean hasGym() {
        return gym;
    }

    public double getRating() {
        return rating;
    }

    public String getLocationCity() {
        return city;
    }

    public String getLocationCountry() {
        return country;
    }

    public double getBasePrice() {
        return pricePerNight;
    }
   
    public boolean hasBar() {
        return bar;
    }

    public boolean hasParking() {
        return hasParking;
    }

    public boolean hasWiFi() {
        return hasWifi;
    }
    public boolean hasBreakfast() {
        return hasBreakfast;
    }

    public double getAdultPrice() {
        return adultPrice;
    }
    public int getStars() 
    {
        return stars;
    }
    public double getChildPrice() {
        return childPrice;
    }
    public boolean hasRoomType(String roomType) {
        return rooms.containsKey(roomType);
    }

    //after creating the hotel, the owner can add rooms to the hotel
    public void addRooms(String roomType, int quantity, double price, int bedCount, boolean hasBalconey, boolean hasPrivateBathroom) {
        if  (!roomType.equals("Standard") && !roomType.equals("e.g") && !roomType.equals("Premium") && !roomType.equals("etc")) {
        System.out.println("Invalid room type");
            return;
        }
        if (!rooms.containsKey(roomType)) {
            rooms.put(roomType, new ArrayList<>());
        }//each room get roomNumber
        for (int i = 0; i < quantity; i++) {
            rooms.get(roomType).add(new Room(roomType, bedCount, hasBalconey, hasPrivateBathroom, price, rooms.get(roomType).size() + 1));
        }
    }
    //when the customer books a room, the room is removed from the list of available rooms
    //the hotel book the room  acording the reservation details and room availability
    public Room bookRoom(String roomType, int startDay, int endDay, boolean breakfast, boolean lunch, boolean dinner, int numberOfAdults, int numberOfChildren) {
       //checkin if the room type is registered in the hotel
        if (!rooms.containsKey(roomType) || rooms.get(roomType).isEmpty()) {
            System.out.println("No available rooms of type " + roomType);
            return null;
        }

        Room availableRoom = null;
        for (Room room : rooms.get(roomType)) {
            boolean isAvailable = true;
            //check if the room is available for the requested dates
            for (int day = startDay; day <= endDay; day++) {
                if (room.getBookedDates().contains(day)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableRoom = room;
                break;
            }
        }

        if (availableRoom == null) {
            System.out.println("No available rooms of type " + roomType + " for the requested dates.");
            return null;
        }
        //remove the room from the list of available rooms
        rooms.get(roomType).remove(availableRoom);
        
        
        Reservation reservation = new Reservation(this, roomType, startDay, endDay, getRoomPrice(roomType), breakfast, lunch, dinner, numberOfAdults, numberOfChildren, availableRoom);
        Set<Integer> addedDates = new HashSet<>(); // Set to store added reservation dates
      
        //add the reservation to the list of reservations
        for (int day = startDay; day <= endDay; day++) {
          if (!addedDates.contains(day)) {
            reservations.computeIfAbsent(day, k -> new ArrayList<>()).add(reservation);
            addedDates.add(day);
          }
          availableRoom.addBookedDate(day);
        }

    
        return availableRoom;    }

     //the hotel can cancel the reservation
    public void cancelReservation(String roomType, int startDay, int endDay, Room room) {
        //check if the reservation is registered in the hotel
        for (int day = startDay; day <= endDay; day++) {
            if (reservations.containsKey(day)) {
                reservations.get(day).removeIf(reservation -> reservation.getRoomType().equals(roomType) && reservation.getHotel().equals(this));
            }
            //remove the room from the list of booked rooms
            room.removeBookedDate(day);
        }
        //add the room to the list of available rooms
        rooms.computeIfAbsent(roomType, k -> new ArrayList<>()).add(room);
    }

    //get the price of the room(basic price per night)
    public double getRoomPrice(String roomType) {
   for (Map.Entry<String, List<Room>> entry : rooms.entrySet()) {
            if (entry.getKey().equals(roomType)) {
                return entry.getValue().get(0).getPricePerNight();
            }
        }
        return 0;
    }


    //update the rating of the hotel
    public void updateRating(double newRating) {
        this.rating = (this.rating * ratingCounter + newRating) / (ratingCounter + 1);
        this.ratingCounter++;
      }
      
    
    

    public Map<Integer, List<Reservation>> getReservations() {
        return reservations;
    }

    public Map<String, List<Room>> getRooms() {
        return rooms;
    }

    //sort the hotels by rating
    public static List<Hotel> rankHotels(List<Hotel> hotels) {
        hotels.sort(Comparator.comparingDouble(Hotel::getRating).reversed());
        return hotels;
    }
  
   
   
      //get all the reservations
      public List<Reservation> getAllReservations() {
        List<Reservation> allReservations = new ArrayList<>();
        for (List<Reservation> reservationsByDate : reservations.values()) {
          allReservations.addAll(reservationsByDate);
        }
        return allReservations;
      }
    
      //add an observer by owner hotel
    public void getMassege() {
        if(observers.isEmpty()){
            System.out.println("No messages available");
        }
        for(int i=0;i<observers.size();i++){
            System.out.println("Message from the hotel " + this.name + " : " + observers.get(i));        
      }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    @Override
    public void update( String message) {
        observers.add(message);
    }
    
}
