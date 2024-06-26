
// This class is an iterator for the Hotel class.
// It iterates over a collection of hotels, which is a map of countries to cities to hotels.
// It first iterates over the countries, then the cities, and finally the hotels. 
//It uses three iterators to keep track of the current country, city, and hotel. 
//It uses the hasNext() method to check if there are more hotels to iterate over, and the next() method to get the next hotel. 
//If there are no more hotels to iterate over, it throws a NoSuchElementException.
import java.util.*;

class HotelIterator implements Iterator<Hotel> {
    private Iterator<Map<String, List<Hotel>>> countryIterator;
    private Iterator<List<Hotel>> cityIterator;
    private Iterator<Hotel> hotelIterator;

    public HotelIterator(Collection<Map<String, List<Hotel>>> hotels) {
        countryIterator = hotels.iterator();
        cityIterator = Collections.emptyIterator();
        hotelIterator = Collections.emptyIterator();
    }

    @Override
    public boolean hasNext() {
        while (!hotelIterator.hasNext()) {
            if (cityIterator.hasNext()) {
                hotelIterator = cityIterator.next().iterator();
            } else if (countryIterator.hasNext()) {
                cityIterator = countryIterator.next().values().iterator();
            } else {
                return false;
            }
        }
        return hotelIterator.hasNext();
    }

    @Override
    public Hotel next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return hotelIterator.next();
    }
}
