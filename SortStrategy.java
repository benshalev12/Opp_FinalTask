

// This interface is used to implement the strategy pattern for sorting hotels
import java.util.List;

public interface SortStrategy {
    void sort(List<Hotel> hotels);
}