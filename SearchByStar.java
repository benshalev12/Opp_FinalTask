// Purpose: This file contains the SearchByStar class which implements the SearchStrategy interface.
// This class is used to search for hotels based on the number of stars they have.
import java.util.List;
import java.util.Scanner;

public class SearchByStar implements SearchStrategy {
    @Override
    public List<Hotel> search(List<Hotel> filters, SortStrategy sortStrategy) {
          Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of stars:");
        int str = scanner.nextInt();
        for(int i=0;i<filters.size();i++){
            if(filters.get(i).getStars() != str){
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