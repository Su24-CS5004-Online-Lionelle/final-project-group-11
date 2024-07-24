package student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Interface to the item model.
 */
public interface ItemModel {
    /** Do not change the file address! */
    String DATABASE = "data/freegamerecords.json";

    /**
     * Get the list of items.
     *
     * @return the list of items
     */
    List<ItemModel.GamesRecord> getItems();

    /**
     * Add an item to the list.
     *
     * @param item the item to add
     */
    void addItem(FreeGameItem item);

    /**
     * Remove an item from the list.
     *
     * @param item the item to remove
     */
    void removeItem(FreeGameItem item);

    /**
     * Save the list to a file.
     *
     * @param filePath the path to save the file to
     */
    void saveList(String filePath);

    /**
     * Load the list from a file.
     *
     * @param filePath the path to load the file from
     */
    void loadList(String filePath);

    /**
     * Primary record to pass around between objects.
     * @param id                  the unique identifier of the game item
     * @param title               the title of the game
     * @param thumbnail           the URL of the game's thumbnail image
     * @param shortDescription    a brief description of the game
     * @param gameUrl             the URL to access the game
     * @param genre               the genre of the game
     * @param platform            the platform on which the game can be played
     * @param publisher           the publisher of the game
     * @param developer           the developer of the game
     * @param releaseDate         the release date of the game
     * @param freetogameProfileUrl the URL to the game's profile on the FreeToGame website
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JacksonXmlRootElement(localName = "Games")
    @JsonPropertyOrder({"id", "title", "thumbnail", "short_description", "game_url", "genre", "platform",
            "publisher", "developer", "release_date", "freetogame_profile_url"})
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record GamesRecord(int id, String title, String thumbnail, String shortDescription, String gameUrl,
                       String genre, String platform, String publisher, String developer, String releaseDate,
                       String freetogameProfileUrl) {
    }
}
