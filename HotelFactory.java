



// This class is responsible for creating a new hotel and adding it to the owner's list of hotels.
// It prompts the user to enter details about the hotel, such as its name, location, amenities, and room types.
// It also allows the user to delete a hotel from the list of hotels owned by the owner.
import java.util.*;

public class HotelFactory implements HotelFact {
    private boolean hasParking;
    private boolean hasWifi;
    private boolean allowsPets;
    private int stars;
    private String city;
    private String country;
    private double pricePerNight;
    private boolean hasBreakfast;
    private boolean hasLunch;
    private boolean spa;
    private boolean gym;
    private boolean bar;
    private boolean hasDinner;
    private boolean hasSwimmingPool;
    private double distanceFromCityCenter;
    private double adultPrice;
    private double childPrice;
    private double mealCost;

       private Scanner s = new Scanner(System.in);        
          @Override
         public void createHotel(OwnerHotel owner) {
           if (!owner.isConnected()) {
             System.out.println("You must be connected to create a hotel.");
             return;
           } 

    // Hotel details
    System.out.println("Enter hotel name:");
    String name = s.nextLine();

    System.out.println("Does the hotel have parking? (yes/no)");
    hasParking = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Does the hotel offer WiFi? (yes/no)");
    hasWifi = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Does the hotel allow pets? (yes/no)");
    allowsPets = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Enter hotel star rating (1-5):");
    stars = Integer.parseInt(s.nextLine());

    System.out.println("Enter city where the hotel is located:");
    city = s.nextLine();

    System.out.println("Enter country where the hotel is located:");
    country = s.nextLine();

    System.out.println("Enter base price per night:");
    pricePerNight = Double.parseDouble(s.nextLine());

    System.out.println("Does the hotel include breakfast? (yes/no)");
    hasBreakfast = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Does the hotel include lunch? (yes/no)");
    hasLunch = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Does the hotel have a spa? (yes/no)");
    spa = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Does the hotel have a gym? (yes/no)");
    gym = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Does the hotel have a bar? (yes/no)");
    bar = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Does the hotel include dinner? (yes/no)");
    hasDinner = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Does the hotel have a swimming pool? (yes/no)");
    hasSwimmingPool = s.nextLine().equalsIgnoreCase("yes");

    System.out.println("Enter distance from city center (in kilometers):");
    distanceFromCityCenter = Double.parseDouble(s.nextLine());

    System.out.println("Enter price per night for adults (2 first adults are include the basic price):");
    adultPrice = Double.parseDouble(s.nextLine());

    System.out.println("Enter price per night for children:");
    childPrice = Double.parseDouble(s.nextLine());
    
    System.out.println("Enter price per meal:");
    mealCost = Double.parseDouble(s.nextLine());

    Hotel hotel = new Hotel(name, hasParking, hasWifi, allowsPets, stars, city, country, pricePerNight, hasBreakfast, hasLunch, spa, gym, bar, hasDinner, hasSwimmingPool, distanceFromCityCenter, 0, adultPrice, childPrice,mealCost);
    owner.addHotel(hotel);

    // Create rooms (loop until user enters 'no')
    List<Room> rooms = new ArrayList<>();
    String addMoreRooms;
    do {
      System.out.println("Enter room type (e.g., Standard, Suite):");
      String roomType = s.nextLine();

      System.out.println("Enter number of beds in this room type:");
      int bedCount = Integer.parseInt(s.nextLine());

      System.out.println("Enter number of rooms from this kind:");
      int quantity = Integer.parseInt(s.nextLine());

      System.out.println("Enter price per night for this spacific room:");
      double pricePerNight = Double.parseDouble(s.nextLine());

      System.out.println("Does this room type have a balcony? (yes/no)");
      boolean hasBalcony = s.nextLine().equalsIgnoreCase("yes");

      System.out.println("Does this room type have a private bathroom? (yes/no)");
        boolean hasPrivateBathroom = s.nextLine().equalsIgnoreCase("yes");
        
              owner.addRoomsToHotel(name,roomType,quantity,pricePerNight,bedCount,hasBalcony,hasPrivateBathroom);
        
              System.out.println("Do you want to add another room type? (yes/no)");
              addMoreRooms = s.nextLine().toLowerCase();
            } while (addMoreRooms.equals("yes"));
        
            //
        
      
}
// This method allows the owner to delete a hotel from the list of hotels owned by the owner.
public void deleteHotel(OwnerHotel owner) {
    if (!owner.isConnected()) {
      System.out.println("You must be connected to delete a hotel.");
      return;
    }
  
    System.out.println("Enter hotel name:");
    String name = s.nextLine();
   owner.deleteHotel(owner.getHotel(name));
    System.out.println("Hotel " + name + " has been deleted.");
}
}