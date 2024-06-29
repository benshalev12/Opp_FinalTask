
// Purpose: This class is a concrete implementation of the SearchStrategy interface.
// It is used to search for hotels based on the room type.
// It takes a list of hotels and a sort strategy as input and returns a list of hotels that have the given room type.
// If no hotels are found with the given room type, it prints a message saying "No hotels found with the given room type".
// It then sorts the list of hotels using the given sort strategy and returns the sorted list.
import java.util.List;
import java.util.Scanner;

public class SearchByRoomType implements SearchStrategy {
    @Override
    public List<Hotel> search(List<Hotel> filters, SortStrategy sortStrategy) {
          Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room type:");
        Scanner stringScanner = new Scanner(System.in);

        String str =stringScanner.next();
        for(int i=0;i<filters.size();i++){
            if(filters.get(i).hasRoomType(str) == false){
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