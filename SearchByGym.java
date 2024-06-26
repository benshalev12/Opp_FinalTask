
// This class implements the SearchStrategy interface and overrides the search method to search for hotels with gym facilities.
// It removes hotels that do not have gym facilities from the list of hotels and returns the filtered list.
// If no hotels are found with gym facilities, it prints a message saying "No hotels found with the given room type". It also calls the sort method of the SortStrategy interface to sort the filtered list of hotels.
import java.util.List;

public class SearchByGym implements SearchStrategy {
    @Override
    public List<Hotel> search(List<Hotel> filters, SortStrategy sortStrategy) {
        for(int i=0;i<filters.size();i++){
            if(filters.get(i).hasGym() == false){
                filters.remove(i);
              
            }
        }
        if(filters.size() == 0){
            System.out.println("No hotels found with the given room type");
        }
        sortStrategy.sort(filters);
        return filters;
    }
}