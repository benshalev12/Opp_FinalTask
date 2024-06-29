//sort results by rating
import java.util.Comparator;
import java.util.List;

public class SortByRating implements SortStrategy {
    @Override
    public void sort(List<Hotel> hotels) {
        hotels.sort(Comparator.comparingDouble(Hotel::getRating).reversed());
    }
}