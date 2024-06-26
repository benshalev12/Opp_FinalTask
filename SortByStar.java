
//sort results by star
import java.util.Comparator;
import java.util.List;

public class SortByStar implements SortStrategy {
    @Override
    public void sort(List<Hotel> hotels) {
        hotels.sort(Comparator.comparingInt(Hotel::getStars).reversed());
    }
}