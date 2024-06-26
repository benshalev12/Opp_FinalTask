import java.util.Scanner;
//this project is a hotel booking system that allows customers to book rooms in hotels and owners to manage their hotels and rooms.
//The system has two types of users: customers and owners. Customers can search for hotels and book rooms in them.
// Owners can add and delete hotels and rooms, and check the capacity of their hotels.
//this project is a final Project of Object Oriented Programming course in Ariel University.
// written by Ben Shalev.

//this is the main class of the project.

class Main {
    public static void main(String[] args) {
        // Create owners and customers
        OwnerHotel owner1 = new OwnerHotel("John Smith", 123456789L, "password1");
        OwnerHotel owner2 = new OwnerHotel("Jane Doe", 987654321L, "password2");

     
        Customer customer1 = new Customer("Alice Johnson", 456789123L, "pass123");
        Customer customer2 = new Customer("Bob Williams", 789123456L, "pass456");

        // Login owners and customers
        owner1.login(123456789L, "password1");
        owner2.login(987654321L, "password2");
        customer1.login(456789123L, "pass123");
        customer2.login(789123456L, "pass456");

        // Create hotels for owner1.
        Hotel hotel1 = new Hotel("high", true, true, false,  5, "New York", "USA", 200.0, true, true, true, true, true, true, true, 1.5, 0, 150.0, 75.0,10.0);
        Hotel hotel2 = new Hotel("midle", false, true, true,  5, "New York", "USA", 180.0, true, false, false, true, false, true, false, 0.5, 0, 120.0, 60.0,10.0);
        Hotel hotel3 = new Hotel("low", true, false, false,  5, "New York", "USA", 150.0, false, true, false, false, true, false, false, 0.2, 0, 100.0, 50.0,10.0);
        Hotel hotel4 = new Hotel("Mountain View Lodge", true, true, true,  4, "Denver", "USA", 180.0, true, true, true, false, false, true, true, 10.0, 0, 130.0, 65.0,10.0);
        Hotel hotel5 = new Hotel("Seaside Retreat", false, true, true,  5, "Los Angeles", "USA", 250.0, true, true, true, true, true, true, true, 2.0, 0, 200.0, 100.0,10.0);
        Hotel hotel6 = new Hotel("one room", true, true, false,  5, "Las Vegas", "USA", 300.0, true, true, true, true, true, true, true, 0.8, 0, 250.0, 125.0,10.0);
        Hotel hotel7 = new Hotel("Countryside Inn", true, false, true,  3, "Nashville", "USA", 120.0, false, false, false, false, false, true, false, 8.0, 0, 90.0, 45.0,10.0);
        Hotel hotel8 = new Hotel("Urban Oasis", false, true, false,  4, "Seattle", "USA", 180.0, true, true, false, true, true, true, false, 1.0, 0, 140.0, 70.0,10.0);

        owner1.addHotel(hotel1);
        owner1.addHotel(hotel2);
        owner1.addHotel(hotel3);
        owner1.addHotel(hotel4);
        owner1.addHotel(hotel5);
        owner1.addHotel(hotel6);
        owner1.addHotel(hotel7);
        owner1.addHotel(hotel8);


        // Add rooms to hotels for owner1
        owner1.addRoomsToHotel("high", "Standard", 20, 200.0, 2, true, true);
        owner1.addRoomsToHotel("high", "Premium", 10, 300.0, 2, true, true);
        owner1.addRoomsToHotel("midle", "etc", 15, 250.0, 2, true, true);
        owner1.addRoomsToHotel("low", "Standard", 10, 150.0, 2, false, true);
        owner1.addRoomsToHotel("Mountain View Lodge", "e.g", 8, 250.0, 2, true, true);
        owner1.addRoomsToHotel("Seaside Retreat", "Premium", 12, 300.0, 2, true, true);
        owner1.addRoomsToHotel("one room", "Premium", 1, 400.0, 2, true, true);
        owner1.addRoomsToHotel("Countryside Inn", "Standard", 8, 100.0, 2, false, false);

        // Create hotels for owner2
        Hotel hotel9 = new Hotel("Lakeside Retreat", true, true, true,  4, "Austin", "USA", 170.0, true, false, true, true, false, true, true, 3.0, 0, 120.0, 60.0,10.0);
        Hotel hotel10 = new Hotel("Historic Manor", true, false, false,  5, "Boston", "USA", 220.0, false, true, false, false, true, true, false, 0.5, 0, 180.0, 90.0,10.0);
        Hotel hotel11 = new Hotel("Riverfront Inn", false, true, true, 3, "Portland", "USA", 160.0, true, false, false, true, false, true, false, 1.2, 0, 110.0, 55.0,10.0);
        Hotel hotel12 = new Hotel("Downtown Suites", true, true, false,  4, "San Francisco", "USA", 220.0, true, true, false, true, true, true, false, 0.8, 0, 180.0, 90.0,10.0);
        Hotel hotel13 = new Hotel("Beach Retreat", false, true, true,  5, "Honolulu", "USA", 300.0, true, true, true, true, true, true, true, 0.3, 0, 250.0, 125.0,10.0);
        Hotel hotel14 = new Hotel("Mountain Escape", true, false, true,  4, "Aspen", "USA", 250.0, false, true, true, false, false, true, false, 12.0, 0, 200.0, 100.0,10.0);
        Hotel hotel15 = new Hotel("City Luxury", true, true, false,  4, "New York", "USA", 350.0, true, true, true, true, true, true, true, 1.0, 0, 300.0, 150.0,10.0);

       owner2.addHotel(hotel9);
       owner2.addHotel(hotel10);
       owner2.addHotel(hotel11);
       owner2.addHotel(hotel12);
       owner2.addHotel(hotel13);
       owner2.addHotel(hotel14);
       owner2.addHotel(hotel15);

       // Add rooms to hotels for owner2
       owner2.addRoomsToHotel("Lakeside Retreat", "Standard", 15, 170.0, 2, true, true);
       owner2.addRoomsToHotel("Lakeside Retreat", "Premium", 5, 250.0, 2, true, true);
       owner2.addRoomsToHotel("Historic Manor", "etc", 12, 220.0, 2, false, true);
       owner2.addRoomsToHotel("Riverfront Inn", "Premium", 8, 200.0, 2, true, true);
       owner2.addRoomsToHotel("Downtown Suites", "e.g", 10, 300.0, 2, true, true);
       owner2.addRoomsToHotel("Beach Retreat", "e.g", 18, 400.0, 2, true, true);
       owner2.addRoomsToHotel("Mountain Escape", "Premium", 12, 350.0, 2, true, true);
       owner2.addRoomsToHotel("City Luxury", "etc", 6, 500.0, 2, true, true);

             Scanner scanner = new Scanner(System.in);
       
             boolean run = true;
             while (run) {
                 System.out.println("Welcome to the Hotel Management System");
                 System.out.println("1. Customer");
                 System.out.println("2. Hotel Owner");
                 System.out.println("3. Exit");
                 
                 int choice =scanner.nextInt();

                 switch (choice) {
                     case 1:
                         new FacadeCustomer().CustomerOption(HotelDatabase.getInstance());
                         break;
                     case 2:
                         new FacadeOwner().OwnerOptions();
                         break;
                     case 3:
                         run = false;
                         System.out.println("Thank you for using the Hotel Management System. Goodbye!");
                         break;
                     default:
                         System.out.println("Invalid option. Please try again.");
                 }
             }
             scanner.close();
         }
     
        }