



// This class is responsible for searching hotels based on multiple filters. 
//It implements the SearchStrategy interface and overrides the search method.
// It usesmany filter classes  the user's input.
// It also uses the SortStrategy interface to sort the filtered hotels based on the user's input. It returns the filtered hotels.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiSearch implements SearchStrategy {
    
    // This method allows the user to search for hotels based on multiple filters.
    @Override
    public List<Hotel> search(List<Hotel> filters, SortStrategy sortStrategy) {
        List<Hotel> filteredHotels = new ArrayList<>(filters); // Create a copy of the original list
        Scanner scanner = new Scanner(System.in);
        boolean continueSearching = true;
        // choose the filter option
            System.out.println(filteredHotels.size() + " hotels found.");
           
        while (continueSearching) {
            System.out.println("Choose a filter option:");
            System.out.println("1. Hotels with balcony");
            System.out.println("2. Hotels with breakfast");
            System.out.println("3. Hotels with pool");
            System.out.println("4. Hotels with private bathroom");
            System.out.println("5. Hotels with spa");
            System.out.println("6. Hotels with parking");
            System.out.println("7. Hotels with specific room type");
            System.out.println("8. Hotels with specific number of stars");
            System.out.println("9. Hotels with gym");
            System.out.println("0. get results");

            int filterOption = scanner.nextInt();
           // switch case for the filter option use the sort strategy choosen by the user
            switch (filterOption) {
                case 1:
                    filteredHotels = new SearchByBalcony().search(filteredHotels, sortStrategy);
                    break;
                case 2:
                    filteredHotels = new SearchByBreakfast().search(filteredHotels, sortStrategy);
                    break;
                case 3:
                    filteredHotels = new SearchByPool().search(filteredHotels, sortStrategy);
                    break;
                case 4:
                    filteredHotels = new SearchByBathRoom().search(filteredHotels, sortStrategy);
                    break;
                case 5:
                    filteredHotels = new SearchBySpa().search(filteredHotels, sortStrategy);
                    break;
                case 6:
                    filteredHotels = new SearchByParking().search(filteredHotels, sortStrategy);
                    break;
                case 7:
                    filteredHotels = new SearchByRoomType().search(filteredHotels, sortStrategy);
                    break;
                case 8:
                    filteredHotels = new SearchByStar().search(filteredHotels, sortStrategy);
                    break;
                case 9:
                    filteredHotels = new SearchByGym().search(filteredHotels, sortStrategy);
                    break;
                case 0:
                    continueSearching = false;
                    break;
                default:
                    System.out.println("Invalid searching option. Please try again.");
            }
        }

        return filteredHotels;
    }
}