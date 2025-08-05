import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class StreamUtils {

    /**
     * Filters a list based on a given predicate.
     *
     * @param list      The list to filter.
     * @param predicate The condition to apply for filtering.
     * @param <T>       The type of elements in the list.
     * @return A new list containing only the elements that match the predicate.
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream()
                   .filter(predicate)
                   .collect(Collectors.toList());
    }

    /**
     * Groups a list of objects by a specific classifier function.
     *
     * @param list       The list to group.
     * @param classifier The function to extract the grouping key.
     * @param <T>        The type of elements in the list.
     * @param <K>        The type of the key.
     * @return A map where keys are the result of the classifier and values are lists of corresponding elements.
     */
    public static <T, K> Map<K, List<T>> groupBy(List<T> list, Function<? super T, ? extends K> classifier) {
        return list.stream()
                   .collect(Collectors.groupingBy(classifier));
    }

    /**
     * Sorts a list based on a given comparator.
     *
     * @param list       The list to sort.
     * @param comparator The comparator to determine the order.
     * @param <T>        The type of elements in the list.
     * @return A new list sorted according to the comparator.
     */
    public static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        return list.stream()
                   .sorted(comparator)
                   .collect(Collectors.toList());
    }
}
