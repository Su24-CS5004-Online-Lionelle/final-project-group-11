import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import student.model.FreeGameItem;
import student.model.ItemModelImpl;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemModelImplTest {

    private ItemModelImpl model;

    @BeforeEach
    public void setUp() {
        model = new ItemModelImpl();
    }

    @Test
    public void testSaveList(@TempDir Path tempDir) {
        // Prepare some test data
        FreeGameItem game1 = new FreeGameItem(1, "Game1", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(2, "Game2", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        model.addItem(game1);
        model.addItem(game2);

        // Save the list to a file in the temporary directory
        Path testFilePath = tempDir.resolve("test_freegamerecords.json");
        model.saveList(testFilePath.toString());

        // Verify the file was created
        File file = testFilePath.toFile();
        assertTrue(file.exists(), "The file should exist after saving the list.");
        assertTrue(file.length() > 0, "The file should not be empty.");
    }

    @Test
    public void testLoadList(@TempDir Path tempDir) {
        // Prepare some test data and save it first
        FreeGameItem game1 = new FreeGameItem(1, "Game1", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(2, "Game2", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        model.addItem(game1);
        model.addItem(game2);
        Path testFilePath = tempDir.resolve("test_freegamerecords.json");
        model.saveList(testFilePath.toString());

        // Create a new model instance and load the list from the file
        ItemModelImpl newModel = new ItemModelImpl();
        newModel.loadList(testFilePath.toString());

        // Verify the loaded list
        List<FreeGameItem> loadedItems = newModel.getItems();
        assertEquals(2, loadedItems.size(), "The loaded list should contain 2 items.");
        assertEquals(Arrays.asList(game1, game2), loadedItems, "The loaded list should contain the original items.");
    }

    @Test
    public void testSearchByNameCaseInsensitive() {
        // Prepare some test data
        FreeGameItem game1 = new FreeGameItem(1, "Game1", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        model.addItem(game1);

        // Search for the game with different cases
        FreeGameItem result1 = model.SearchByName("game1");
        FreeGameItem result2 = model.SearchByName("GAME1");
        FreeGameItem result3 = model.SearchByName("GaMe1");

        // Verify the search results are the same
        assertEquals(game1, result1, "The search should return the correct game (case-insensitive).");
        assertEquals(game1, result2, "The search should return the correct game (case-insensitive).");
        assertEquals(game1, result3, "The search should return the correct game (case-insensitive).");
    }
}