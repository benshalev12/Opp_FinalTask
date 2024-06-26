//sort results by price low to high
import java.util.Comparator;
import java.util.List;

public class SortByPriceLowToHigh implements SortStrategy {
    @Override
    public void sort(List<Hotel> hotels) {
        hotels.sort(Comparator.comparingDouble(Hotel::getBasePrice));
    }
}