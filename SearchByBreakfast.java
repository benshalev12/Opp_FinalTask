
// This class implements the SearchStrategy interface and overrides the search method to search for hotels with breakfast option.
// It removes the hotels that do not have breakfast option and returns the filtered list of hotels.
// If no hotels are found with the given room type, it prints a message saying "No hotels found with the given room type".
import java.util.List;

public class SearchByBreakfast implements SearchStrategy {
    @Override
    public List<Hotel> search(List<Hotel> filters, SortStrategy sortStrategy) {
        for(int i=0;i<filters.size();i++){
            if(filters.get(i).hasBreakfast == false){
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