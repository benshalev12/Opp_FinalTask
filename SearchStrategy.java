// Purpose: Interface for search strategy
import java.util.List;

public interface SearchStrategy {
    List<Hotel> search( List<Hotel> filters, SortStrategy sortStrategy);
}
