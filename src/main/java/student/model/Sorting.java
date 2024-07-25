package student.model;

import java.util.Comparator;
import java.util.List;

public class Sorting {
    /**
     * Sorts a list of FreeGameItems based on the specified field and order.
     *
     * @param items     the list of items to be sorted
     * @param field     the field to sort by (e.g., "title", "releaseDate")
     * @param ascending true for ascending order, false for descending
     * @return sorted list of FreeGameItem
     */
    public static List<FreeGameItem> sortItems(List<FreeGameItem>  items, String field,boolean ascending){

    }

    /**
     * Creates a comparator based on the specified field.
     *
     * @param field the field to create the comparator for
     * @return a comparator for the field
     */
    private static Comparator<FreeGameItem> getComparatorByField(String field){

    }
}
