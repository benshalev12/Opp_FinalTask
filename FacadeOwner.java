
// This class is a facade for the owner of the hotel. 
//It provides the owner with a menu of options to manage their hotels and reservations.
import java.util.List;
import java.util.Scanner;

public class FacadeOwner {
    private Scanner scanner = new Scanner(System.in);
    private HotelFactory hotelFactory = new HotelFactory();


    // This method prompts the owner to log in or register if they are not already registered.
    private OwnerHotel getIn() {
        Long id = readLong("Enter your id:");
        OwnerHotel owner = OwnerDatabase.getInstance().getOwner(id);
        if (owner == null)
         {//if the owner is not registered
            System.out.println("You are not registered. Please enter your name and password to register:");
            String name = readString("Enter your name:");
            String password = readString("Enter your password (4-10 charcters):");
            owner = new OwnerHotel(name, id, password);
            owner.login(id, password);
        } 

        else {//if the owner is registered
            String password = readString("Enter your password(4-10 charcters):");
            owner.login(id, password);
        }
        if (owner.isConnected()==true) {
         
        } else {
            System.out.println("your access faile please try again.");
             getIn();
        }
        return owner;
    }
    
    //use hotelFactory to create a hotel
    private void createHotel(OwnerHotel owner) {
        hotelFactory.createHotel(owner);
    }
    //use hotelFactory to delete a hotel
    private void deleteHotel(OwnerHotel owner) {
        hotelFactory.deleteHotel(owner);
    }

    // List all hotels owned by the owner
    private void listHotels(OwnerHotel owner) {
        System.out.println("Your hotels:");
        for (Hotel hotel : owner.listHotels().values()) {
            System.out.println(hotel.getName());
        }
    }

    // Check if a hotel is under or over capacity for a given period
    private void checkHotelCapacity(OwnerHotel owner) {
        String hotelName = readString("Enter hotel name:");
        int threshold = readInt("Enter threshold percentage:");
        String startDateStr = readString("Enter start day (yyyy-mm-dd):");
        int startDay = DateUtil.convertDateToDays(startDateStr);
        String endDateStr = readString("Enter end day (yyyy-mm-dd):");
        int endDay = DateUtil.convertDateToDays(endDateStr);

        boolean underCapacity = owner.checkHotelUnderCapacity(hotelName, threshold, startDay, endDay);
        boolean overCapacity = owner.checkHotelOverCapacity(hotelName, threshold, startDay, endDay);

        if (underCapacity) {
            System.out.println("The hotel is under capacity for the given period.");
        } else if (overCapacity) {
            System.out.println("The hotel is over capacity for the given period.");
        } else {
            System.out.println("The hotel is within normal capacity range for the given period.");
        }
    }

    // Get the rank of a hotel
    private void getHotelRank(OwnerHotel owner) {
        String hotelName = readString("Enter hotel name:");
        double rank = owner.getHotelRank(hotelName);
        if (rank != -1) {
            System.out.println("Rank of " + hotelName + ": " + rank);
        } else {
            System.out.println("Hotel not found.");
        }
    }

     // Get the next reservation for a hotel
    private void getNextReservation(OwnerHotel owner) {
        String hotelName = readString("Enter hotel name:");
        Reservation nextReservation = owner.getNextReservation(hotelName);
        if (nextReservation != null) {
            System.out.println("Next reservation: " + nextReservation.getHotel().getName() + " room type: " + nextReservation.getRoomType()
             + " start date: " + DateUtil.convertDaysToDate(nextReservation.getStartDay()) + " end date: " + 
             DateUtil.convertDaysToDate(nextReservation.getEndDay()) + " price: " + nextReservation.getPrice() + " breakfast: " + nextReservation.hasBreakfast() 
             + " lunch: " + nextReservation.hasLunch() + " dinner: " + nextReservation.hasDinner() + " number of adults: "
              + nextReservation.getNumberOfAdults() + " number of children: " + nextReservation.getNumberOfChildren() + " room number: "
               + nextReservation.getRoom().getRoomNumber());
        } else {
            System.out.println("No upcoming reservations found.");
        }
    }
     // Get reservations for a hotel on a given date
    private void getReservationsByDate(OwnerHotel owner) {
        String hotelName = readString("Enter hotel name:");
        String dateStr = readString("Enter date (yyyy-mm-dd):");
        int day = DateUtil.convertDateToDays(dateStr);
        List<Reservation> reservations = owner.getHotelReservationsByDate(hotelName, day);
        if (!reservations.isEmpty()) {
            System.out.println("Reservations for " + dateStr + ":");
            for (Reservation res : reservations) {
                System.out.println("Reservation to hotel:" + res.getHotel().getName() + " room type: " + res.getRoomType()
                 + " start date: " + DateUtil.convertDaysToDate(res.getStartDay()) + " end date: " + 
                 DateUtil.convertDaysToDate(res.getEndDay()) + " price: " + res.getPrice() + " breakfast: " + res.hasBreakfast() 
                 + " lunch: " + res.hasLunch() + " dinner: " + res.hasDinner() + " number of adults: "
                  + res.getNumberOfAdults() + " number of children: " + res.getNumberOfChildren() + " room number: "
                   + res.getRoom().getRoomNumber());
            }
        } else {
            System.out.println("No reservations found for the given date.");
        }
    }

    // Send a notification to all guests of a hotel using observer pattern
    
    private void sendMessage(OwnerHotel owner) {
        String hotelName = readString("Enter hotel name:");
        String mes = readString("Enter massege to the guests:");
        Hotel hotel = owner.getHotel(hotelName);
        if (hotel != null) {
            hotel.update(mes);
          //  owner.notifayMassege(hotel, date);
           // System.out.println("Message sent to guests of " + hotelName + " for " + date);
        } else {
            System.out.println("Hotel not found.");
        }
    }
    //  menu for owner options
    public void OwnerOptions() {
        OwnerHotel owner = getIn();
        if (owner == null || !owner.isConnected()) {
            System.out.println("Invalid user id or password.");
            return;
        }

        boolean run = true;
        while (run) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create a new hotel");
            System.out.println("2. Delete a hotel");
            System.out.println("3. List my hotels");
            System.out.println("4. Check hotel capacity");
            System.out.println("5. Get hotel rank");
            System.out.println("6. Get next reservation");
            System.out.println("7. Get reservations by date");
            System.out.println("8. Send notification to hotel guests");
            System.out.println("9. Exit");
    
            int option = readInt("Enter your choice:");
    
            switch (option) {
                case 1:
                    createHotel(owner);
                    break;
                case 2:
                    deleteHotel(owner);
                    break;
                case 3:
                    listHotels(owner);
                    break;
                case 4:
                    checkHotelCapacity(owner);
                    break;
                case 5:
                    getHotelRank(owner);
                    break;
                case 6:
                    getNextReservation(owner);
                    break;
                case 7:
                    getReservationsByDate(owner);
                    break;
                case 8:
                    sendMessage(owner);
                    break;
                case 9:
                System.out.println("Exiting Owner Options. Goodbye!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Helper methods for reading input
    private String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private long readLong(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}