

//Purpose: This class is a subclass of User and
//it represents the owner of a hotel. It has a map of hotels that the owner owns.
// This class is responsible for creating a new hotel and adding it to the owner's list of hotels.
// It prompts the user to enter details about the hotel, such as its name, location, amenities, and room types.
// It also allows the user to delete a hotel from the list of hotels owned by the owner.
// It also allows the owner to check if a hotel is under or over capacity for a given period.
// It also allows the owner to get the rank of a hotel in a list of hotels.
// It also allows the owner to get the next reservation for a hotel.
// It also allows the owner to get the reservations for a hotel on a specific date.
// It also allows the owner to notify the hotel about a message.
// It also allows the owner to add a hotel to the list of hotels owned by the owner.
// It also allows the owner to add rooms to a hotel.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class OwnerHotel extends User {
    private Map<String, Hotel> hotels = new HashMap<>();
        protected Set<Observer> observers_Hotels = new HashSet<>();

//constructor
    public OwnerHotel(String name, Long id, String password) {
        super(name, id, password);
        OwnerDatabase.getInstance().addOwner(this);

    }

    public void addHotel(Hotel hotel) {
        hotels.put(hotel.getName(), hotel);
        HotelDatabase.getInstance().addHotel(hotel);

    }
//add a room to the hotel
    public void addRoomsToHotel(String hotelName, String roomType, int quantity, double price, int bedCount, boolean hasBalcony, boolean hasPrivateBathroom) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            hotel.addRooms(roomType, quantity, price, bedCount, hasBalcony, hasPrivateBathroom);
        }
    }

    public Hotel getHotel(String name) {
        return hotels.get(name);
    }

    public Map<String, Hotel> listHotels() {
        return hotels;
    }
     // Method to delete a hotel
    public void deleteHotel(Hotel hotel) {
        hotels.remove(hotel.getName());
        HotelDatabase.getInstance().deleteHotel(hotel);
    }
    // Method to check if a hotel is under capacity for a given period
    public boolean checkHotelUnderCapacity(String hotelName, int threshold, int startDay, int endDay) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            int totalRooms = hotel.getRooms().values().stream().mapToInt(List::size).sum();
            int reservedRooms = 0;
            for (int day = startDay; day <= endDay; day++) {
                List<Reservation> reservations = hotel.getReservations().getOrDefault(day, new ArrayList<>());
                reservedRooms += reservations.size();
            }
            return (reservedRooms * 100.0 / totalRooms) < threshold;
        }
        return false; // Hotel not found
    }
     
    // Method to check if a hotel is over capacity for a given period
    public boolean checkHotelOverCapacity(String hotelName, int threshold, int startDay, int endDay) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            int totalRooms = hotel.getRooms().values().stream().mapToInt(List::size).sum();
            int reservedRooms = 0;
            for (int day = startDay; day <= endDay; day++) {
                List<Reservation> reservations = hotel.getReservations().getOrDefault(day, new ArrayList<>());
                reservedRooms += reservations.size();
            }
            return (reservedRooms * 100.0 / totalRooms) > threshold;
        }
        return false; // Hotel not found
    }
    
     // Method to get the rank of a hotels of the owner
    public double getHotelRank(String hotelName) {
       
        Hotel hotel = hotels.get(hotelName);
        return hotel.getRating();
    }

    // Method to notify the hotel about a message
    @Override
    public void update(String message) {
        addNotification(message);
    }

    // Method to get the next reservation for a hotel
    public Reservation getNextReservation(String hotelName) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            Map<Integer, List<Reservation>> reservations = hotel.getReservations();
    
            // Find the earliest reservation day
            int earliestDay = Integer.MAX_VALUE;
            for (int day : reservations.keySet()) {
                earliestDay = Math.min(earliestDay, day);
            }
    
            // Check reservations starting from the earliest day
            for (int day = earliestDay; day <= earliestDay + 365; day++) {
                if (reservations.containsKey(day)) {
                    for (Reservation reservation : reservations.get(day)) {
                        if (reservation.getEndDay() >= day) { // Check if reservation is ongoing
                            return reservation;
                        }
                    }
                }
            }
        }
        return null;
    }
    

    // Method to get hotel reservations per date
    public List<Reservation> getHotelReservationsByDate(String hotelName, int day) {
        List<Reservation> reservationsOnDate = new ArrayList<>();
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null && hotel.getReservations().containsKey(day)) {
            reservationsOnDate.addAll(hotel.getReservations().get(day));
        }
        return reservationsOnDate;
    }


 public void notifayMassege(Hotel hotel, String date) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the message you want to send to the customers:");
        String mes = scanner1.nextLine();
        hotel.notifyObservers(mes);
    }
    
}

