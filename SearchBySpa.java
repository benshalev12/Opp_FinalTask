// Purpose: This file contains the SearchBySpa class which implements the SearchStrategy interface.
// This class is used to search for hotels with spa facilities.
import java.util.List;

public class SearchBySpa implements SearchStrategy {
    @Override
    public List<Hotel> search(List<Hotel> filters, SortStrategy sortStrategy) {
        for(int i=0;i<filters.size();i++){
            if(filters.get(i).hasSpa() == false){
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