
// sort resuits by price high to low
import java.util.Comparator;
import java.util.List;

public class SortByPriceHighToLow implements SortStrategy {
    @Override
    public void sort(List<Hotel> hotels) {
        hotels.sort(Comparator.comparingDouble(Hotel::getBasePrice).reversed());
    }
}