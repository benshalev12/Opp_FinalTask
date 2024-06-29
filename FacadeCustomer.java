// Purpose: This class is the facade for the customer. 
//It is responsible for handling the customer's options and interactions with the system.


import java.util.List;
import java.util.Scanner;

public class FacadeCustomer {
    private Scanner scanner = new Scanner(System.in);

    //let the customer loggin(if he is already registered) or register(if he is not registered
    private Customer getIn() {
        System.out.println("Enter your id:");
        Long id = scanner.nextLong();
        scanner.nextLine(); 
    
        Customer customer = CustomerDataBase.getInstance().getCustomer(id);
        if (customer == null) {//if the customer is not registered
            System.out.println("You are not registered, please enter your name and password to register");
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            customer = new Customer(name, id, password);
            CustomerDataBase.getInstance().addCustomer(customer);
            customer.login(id, password);
            if (customer.isConnected()) {
            } else {
                System.out.println("Your access failed. Please try again.");
                getIn();//if the access failed, the customer should try again
            }
        } else {//if the customer is already registered
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            customer.login(id, password);
            if (customer.isConnected()) {
                System.out.println("You are connected.");
            } else {
                System.out.println("Your access failed. Please try again.");
                getIn();//if the access failed, the customer should try again
            }
        }
        return customer;
    }
//the customer can make a reservation by entering the hotel name, room type, arriving day, leaving day, number of adults and children
//this function use reservation class and reservationDecorator classes to make the reservation.
    private void makeOrder(Customer customer) {
        System.out.println("Enter hotel name:");
        String hotelName = scanner.nextLine();
        Hotel hotel = HotelDatabase.getInstance().getHotelByName(hotelName);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }
        System.out.println("Enter room type (e.g., Premium, Standard, etc.):");
        String roomType = scanner.nextLine();

        System.out.println("Enter arriving day:(yyyy-mm-dd)");
        String arrive = scanner.nextLine();
        
        int startDay = DateUtil.convertDateToDays(arrive);

        System.out.println("Enter leaving day:(yyyy-mm-dd)");
        String leave = scanner.nextLine();
        int endDay = DateUtil.convertDateToDays(leave);

        System.out.println("Press 1 for breakfast addition");
        int breakfast = scanner.nextInt();
        boolean option1 = (breakfast == 1);

        System.out.println("Press 1 for lunch addition");
        int lunch = scanner.nextInt();
        boolean option2 = (lunch == 1);

        System.out.println("Press 1 for dinner addition");
        int dinner = scanner.nextInt();
        boolean option3 = (dinner == 1);

        System.out.println("Enter number of adults Over two adults each adult with the addition of" + hotel.getAdultPrice() + " per night:");
        int numAdults = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter number of children:");
        int numChildren = scanner.nextInt();
        scanner.nextLine(); 
        //makeReservation use ReservationDecorator classes to make the reservation
        customer.makeReservation(hotel, roomType, startDay, endDay, option1, option2, option3, numAdults, numChildren);
        
    }

    //the customer can view his reservations
    private List<Reservation> getOrder(Customer customer) {
        List<Reservation> reservations = customer.getReservations();
        if (reservations.isEmpty()) {
            System.out.println("There are no reservations");
            return null;
        } else {
            System.out.println("Reservations:");
            for (Reservation res : reservations) {
                System.out.println ("There is a reservation to hotel: " + res.getHotel().getName() +
                " from " + DateUtil.convertDaysToDate(res.getStartDay()) + " to " + DateUtil.convertDaysToDate(res.getEndDay()) +
                 " for " + res.getRoomType() + " room type. The price is " + res.getPrice()*(res.getEndDay()-res.getStartDay()) + " and the room number is " + res.getRoom().getRoomNumber() 
                 + " and the reservation id is " + res.getReservationID());
               
            }
            
        }
        return reservations;
    }
        //this function is used to search for hotels.
        //country and city are required fields to optimize the search time
        //this function use the MultiSearch class to search for hotels
 
    private void makeSearch(Customer customer) {
        System.out.println("Choose a sorting option:");
        System.out.println("1. Sort by price (low to high)");
        System.out.println("2. Sort by price (high to low)");
        System.out.println("3. Sort by rating");
        System.out.println("4. Sort by star rating");
        int sortOption = scanner.nextInt();
        scanner.nextLine(); 
        //choose the sorting strategy
        SortStrategy sortingStrategy;
        switch (sortOption) {
            case 1:
                sortingStrategy = new SortByPriceLowToHigh();
                break;
            case 2:
                sortingStrategy = new SortByPriceHighToLow();
                break;
            case 3:
                sortingStrategy = new SortByRating();
                break;
            case 4:
                sortingStrategy = new SortByStar();
                break;
            default:
                System.out.println("Invalid sorting option. Using default sorting.");
                sortingStrategy = new SortByRating();
        }

        System.out.println("Enter country -required field");
        String country = scanner.nextLine();

        System.out.println("Enter city -required field");
        String city = scanner.nextLine();
        
        List<Hotel> search = HotelDatabase.getInstance().getHotelsByCity(country, city); 
        System.out.println(search.size() + " hotels found.");
        MultiSearch multiSearch = new MultiSearch();
        
        //use the search function from the MultiSearch class to search for hotels
        List<Hotel> ans = multiSearch.search(search, sortingStrategy);
       
        System.out.println("Hotels by your filters:");
        if (ans.isEmpty()) {
            System.out.println("No hotels found matching the filters.");
        } else {

            //print the hotels that match the filters and let the customer choose if he wants to make a reservation
            for (Hotel hotel : ans) {
                System.out.println("Hotel"  +hotel.name+ " is found in "+hotel.city+" with "+hotel.stars+" stars and "+hotel.rating+" rating"
                +" and the base price is "+hotel.pricePerNight);
                System.out.println("Do you want to make a reservation for this hotel? (yes/no)");
                String choice = scanner.next().toLowerCase();
                scanner.nextLine(); 
                
                if (choice.equals("yes")) {
                    makeOrder(customer);
                    break;
                }
            }
        }
    }

     //this function is used to delete a reservation
    private void deleteOrder(Customer customer, List<Reservation> reservations) {
        System.out.println("Enter the reservation id:");
        int reservationNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Reservation reservation = customer.getReservationById(reservationNumber);
        customer.cancelReservation(reservation.getHotel(), reservation);
    }

    //this function is used to rate a hotel
    private void rateHotel(Customer customer) {
        System.out.println("Enter hotel name:");
        String hotelName = scanner.nextLine();
        Hotel hotel = HotelDatabase.getInstance().getHotelByName(hotelName);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }
        System.out.println("Enter rating (1-5):");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        customer.rateHotel(hotel, rating);
    }

    private void readMessages(Customer customer) {
      customer.getMessages();
    } 

//this function is used to handle the customer options
//the customer can search for hotels, make a reservation, view his reservations, delete a reservation, rate a hotel or back to the main menu
    public void CustomerOption(HotelDatabase hotelDatabase) {
        Customer customer = getIn();
        if (customer == null) {
            System.out.println("Invalid user id or password.");
            return;
        }
        boolean run = true;
        while (run) {
            System.out.println("Choose an option:");
            System.out.println("1. Search hotels");
            System.out.println("2. Make reservation");
            System.out.println("3. View reservations");
            System.out.println("4. Delete reservation");
            System.out.println("5. Rate hotel");
            System.out.println("6. Read messages");
            System.out.println("7. Exit");


            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    makeSearch(customer);
                    break;
                case 2:
                    makeOrder(customer);
                    break;
                case 3:
                    getOrder(customer);
                    break;
                case 4:
                    deleteOrder(customer, customer.getReservations());
                    break;
                case 5:
               rateHotel(customer);
                    break;
               case 6:
                    readMessages(customer);

                    break;
                case 7:        
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}