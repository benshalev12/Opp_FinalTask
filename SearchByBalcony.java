
// Purpose: This class is a concrete class that implements the SearchStrategy interface.
// It is used to search for hotels that have a room with a balcony.
// It overrides the search method and filters out hotels that do not have a room with a balcony. 
//It then sorts the remaining hotels based on the sort strategy provided.
import java.util.List;
import java.util.Scanner;

public class SearchByBalcony  implements SearchStrategy {
    @Override
    public List<Hotel> search(List<Hotel> filters, SortStrategy sortStrategy) {
          Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room type:");
        Scanner stringScanner = new Scanner(System.in);

        String str =stringScanner.next();
        for(int i=0;i<filters.size();i++){
            if(filters.get(i).hasRoomType(str) == false||filters.get(i).rooms.get(str).get(0).hasBalcony() == false){
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



