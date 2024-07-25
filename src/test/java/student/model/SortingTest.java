package student.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortingTest {

    private List<FreeGameItem> items;

    @BeforeEach
    void setup(){
        items = new ArrayList<>();
        items.add(new FreeGameItem(1, "Game One", "url1", "Desc1", "url1", "Action", "PC", "Publisher1", "Developer1", "2024-01-01", "url1"));
        items.add(new FreeGameItem(2, "Game Two", "url2", "Desc2", "url2", "Adventure", "Console", "Publisher2", "Developer2", "2023-01-02", "url2"));
    }

    @Test
    public void testSortItemsByTitle() {
        List<FreeGameItem> sortedItems = Sorting.sortItems(items, "title", true);

        assertEquals("Game One", sortedItems.get(0).getTitle());
        assertEquals("Game Two", sortedItems.get(1).getTitle());
    }

    @Test
    public void testSortItemByGenre() {
        List<FreeGameItem> sortedItems = Sorting.sortItems(items, "genre", true);

        assertEquals("Action", sortedItems.get(0).getGenre());
        assertEquals("Adventure", sortedItems.get(1).getGenre());
    }
}
