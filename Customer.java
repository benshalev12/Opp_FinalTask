// Purpose: This class is responsible for creating a customer object and managing the reservations of the customer.
// It also allows the customer to make a reservation, cancel a reservation, view the next reservation, and rate a hotel.
// Class name: Customer.java
// Methods: makeReservation, cancelReservation, getReservations, viewNextReservation, rateHotel, getName, getCustomer, update
// Variables: reservations, messages
//extand user class and implement observer interface




import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User implements Observer {
    private List<Reservation> reservations = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    // Constructor for Customer
    public Customer(String name, Long id, String password) {
        super(name, id, password);
       CustomerDataBase.getInstance().addCustomer(this);


    }

   
    // Method to make a reservation
    public void makeReservation(Hotel hotel, String roomType, int startDay, int endDay, boolean breakfast, boolean lunch, boolean dinner, int numberOfAdults, int numberOfChildren) {
        Room room = hotel.bookRoom(roomType, startDay, endDay, breakfast, lunch, dinner, numberOfAdults, numberOfChildren);
        if (room != null) {
            Reservation res = new Reservation(hotel, roomType, startDay, endDay, hotel.getRoomPrice(roomType), breakfast, lunch, dinner, numberOfAdults, numberOfChildren, room);
            if (breakfast) {
                res = new ReservationWithBreakfast(res, hotel.getMealCost());
            }
            if (lunch) {
                res = new ReservationWithLunch(res, hotel.getMealCost());
            }
            if (dinner) {
                res = new ReservationWithDinner(res, hotel.getMealCost());
            }
            if (numberOfChildren > 0) {
                res = new ReservationWithChildren(res, hotel.getChildPrice(), numberOfChildren);
            }
            if (numberOfAdults > 2) {
                res = new ReservationWithAdults(res, hotel.getAdultPrice(), numberOfAdults );
            }
            res.updatePrice();
            reservations.add(res);
            res.setReservationID(reservations.size());
    
            // Print reservation details
            System.out.println("Reservation Details:");
            System.out.println("Hotel: " + hotel.getName());
            System.out.println("Room Type: " + roomType);
            System.out.println("Start Date: " + DateUtil.convertDaysToDate(startDay));
            System.out.println("End Date: " + DateUtil.convertDaysToDate(endDay));
            System.out.println("Price: " + res.getPrice()*(endDay-startDay));
            System.out.println("Breakfast: " + (breakfast ? "Yes" : "No"));
            System.out.println("Lunch: " + (lunch ? "Yes" : "No"));
            System.out.println("Dinner: " + (dinner ? "Yes" : "No"));
            System.out.println("Number of Adults: " + numberOfAdults);
            System.out.println("Number of Children: " + numberOfChildren);
            System.out.println("Room Number: " + room.getRoomNumber());
            System.err.println("Reservation ID: " + res.getReservationID());
        }
    }

    // Method to cancel a reservation
    public void cancelReservation(Hotel hotel, Reservation reservation) {
        hotel.cancelReservation(reservation.getRoomType(), reservation.getStartDay(), reservation.getEndDay(), reservation.getRoom());
        reservations.remove(reservation);
        System.out.println("Reservation number"+ reservation.getReservationID() + "to hotel" +hotel.getName() +"btween" +DateUtil.convertDaysToDate(reservation.getStartDay()) + "to"+ DateUtil.convertDaysToDate(reservation.getEndDay()) + "has been canceled");
    }


    // Method to get reservations
    public List<Reservation> getReservations() {
        if (reservations.isEmpty()) {
            System.out.println("There are no reservations");
        }
        return reservations;
    }

    public Reservation getReservationById(int reservationID) {
        for (Reservation res : reservations) {
            if (res.getReservationID() == reservationID) {
                return res;
            }
        }
        System.out.println("Reservation not found");
        return null;
    }


    // Method to view the next reservation
    public Reservation viewNextReservation() {
        return reservations.stream()
                .min((r1, r2) -> Integer.compare(r1.getStartDay(), r2.getStartDay()))
                .orElse(null);
    }


    // Method to rate a hotel
    public void rateHotel(Hotel hotel, double rating) {
        hotel.updateRating(rating);
    }
    

    // Method to get the name of the customer
    public String getName () {
        return name ;
    }
    public Customer getCustomer(String name, Long id) {
        if (this.name.equals(name) && this.id.equals(id)) {
            return this;
        }
        System.out.println("Customer not found");
        return null;
    }

    @Override
    public void update(String message) {
        List<Reservation> reservations = this.getReservations();
        if (reservations.isEmpty()) {
            System.out.println("There are no reservations");
            return;
        } else {
            System.out.println("Enter name of the hotel");
            Scanner scanner = new Scanner(System.in);
            String hotelName = scanner.nextLine();
            for(Reservation res : reservations) {
                if(res.getHotel().getName().equals(hotelName)){
                    res.getHotel().update(message);
                }
            }
        }
    }
    

    // Method to update the customer

   

    public void getMessages() 
        {
        List<Reservation> reservations = this.getReservations();
        if (reservations.isEmpty()) {
            System.out.println("There are no reservations");
            return;
        } 
        else {
            for(Reservation res : reservations) {
                 res.getHotel().getMassege();
            }
            
        }      }
    
    public void clearMessages() {
        messages.clear();
    }

  
}
     
