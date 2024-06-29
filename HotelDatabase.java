
// the class implements the iterable interface and has a nested class that implements the iterator interface
//this class is a singleton class that stores all the hotels in a HashMap of HashMaps of Lists.
//this sturcturre is used to store the hotels by country and city.
//its makes the search more efficient.
import java.util.*;

public class HotelDatabase implements Iterable<Hotel> {
    private static HotelDatabase instance;
    private Map<String, Map<String, List<Hotel>>> hotels; // Country -> City -> List of Hotels

    private HotelDatabase() {
        hotels = new HashMap<>();
    }
 //
    public static HotelDatabase getInstance() {
        if (instance == null) {
            instance = new HotelDatabase();
        }
        return instance;
    }
    //add a hotel to the database
    public void addHotel(Hotel hotel) {
        String country = hotel.getCountry();
        String city = hotel.getCity();

        hotels.computeIfAbsent(country, k -> new HashMap<>())
              .computeIfAbsent(city, k -> new ArrayList<>())
              .add(hotel);
    }
    //delete a hotel from the database
    public void deleteHotel(Hotel hotel) {
        String country = hotel.getCountry();
        String city = hotel.getCity();

        hotels.getOrDefault(country, new HashMap<>())
              .getOrDefault(city, new ArrayList<>())
              .remove(hotel);
    }
    //get a hotel by city
    public List<Hotel> getHotelsByCity(String country, String city) {
        return hotels.getOrDefault(country, new HashMap<>())
                      .getOrDefault(city, new ArrayList<>());
    }

    //get a hotel by name
    public Hotel getHotelByName(String name) {
        for (Map<String, List<Hotel>> citiesForCountry : hotels.values()) {
            for (List<Hotel> hotelsForCity : citiesForCountry.values()) {
                for (Hotel hotel : hotelsForCity) {
                    if (hotel.getName().equals(name)) {
                        return hotel;
                    }
                }
            }
        }

        return null;
    }
  //get a hotel by country
    public List<Hotel> getHotelsByCountry(String country) {
        List<Hotel> hotelsByCountry = new ArrayList<>();
        Map<String, List<Hotel>> citiesForCountry = hotels.getOrDefault(country, new HashMap<>());

        for (List<Hotel> hotelsForCity : citiesForCountry.values()) {
            hotelsByCountry.addAll(hotelsForCity);
        }

        return hotelsByCountry;
    }
      //get all the hotels
    public List<Hotel> getAllHotels() {
        List<Hotel> allHotels = new ArrayList<>();

        for (Map<String, List<Hotel>> citiesForCountry : hotels.values()) {
            for (List<Hotel> hotelsForCity : citiesForCountry.values()) {
                allHotels.addAll(hotelsForCity);
            }
        }

        return allHotels;
    }

    public Collection<Map<String, List<Hotel>>> values() {
        return hotels.values();
    }
    //iterator for the hotels
    @Override
    public Iterator<Hotel> iterator() {
        return new HotelIterator(values());
    }
}
