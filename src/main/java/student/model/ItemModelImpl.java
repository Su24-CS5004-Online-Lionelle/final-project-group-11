package student.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.NotImplementedException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import student.model.formatters.Display;
import student.model.formatters.Formats;


/**
 * ItemModel class to handle the game items.
 */
public class ItemModelImpl implements ItemModel{

    /** Game list that contains the free games. */
    private List<FreeGameItem> gameList = new ArrayList<>();

    /** Game list that contains the temporary free games. */
    private List<FreeGameItem> tempGameList = new ArrayList<>();

    /** HashMap that contains the game name as key and record as value. */
    private Map<String, FreeGameItem> gamesMap = new HashMap<>();

    public ItemModelImpl() {
        //this.gameList = new ArrayList<>();
        // Load the initial data from the database file
        //try {
            //loadList(DATABASE);
        //} catch (Exception e) {
            //e.printStackTrace();
        //}
    }

    /**
     * This method returns the list of games records.
     * @return it returns the list of records.
     */
    @Override
    public List<FreeGameItem> getItems() {
        return List.copyOf(this.gamesMap.values());
    }

    /**
     * This method returns the temporary game list.
     * @return it returns the temporary game list.
     */
    public List<FreeGameItem> getTempGames() {
        return this.tempGameList;
    }

    /**
     * This method retrieves value associated with the game title given as input from the gamesMap.
     * @param gameName the game title/name is given as String input.
     * @return it returns the FreeGameItem value associated with that game title key.
     */
    public FreeGameItem getGameFromMap(String gameName) {
        return this.gamesMap.get(gameName);
    }

    /**
     * This method adds the game object temporarily to a list.
     * @param item the FreeGameItem object is stored in the temporary list.
     */
    public void addTempItem(FreeGameItem item) {
        this.tempGameList.add(item);
    }

    @Override
    public void addItem(FreeGameItem item) {
        this.gameList.add(item);
    }

    /**
     * This method returns a list of FreeGameItem objects.
     * @return the list of FreeGameItem objects.
     */
    public List<FreeGameItem> getGameList() {
        return this.gameList;
    }

    @Override
    public void removeItem(FreeGameItem game) {
        this.gameList.remove(game);
    }

    @Override
    public void saveList(String filePath) {
        throw new NotImplementedException("no completed yet");
    }

    @Override
    public void loadList(String filePath) {
        throw new NotImplementedException("no completed yet");
    }

    /**
     * This method helps to create an instance of ItemModelImpl class.
     * It also creates a hashmap containing game title as key and record as value.
     * @param database the json database file path given as string.
     * @return it returns a newly created instance of ItemModelImpl class.
     * @throws Exception when the instance cannot be created.
     */
    public ItemModelImpl createDatabase(String database) throws Exception {
        File file = new File(database);
        if (file.exists() && file.length() == 0) {
            GamesDatabase instance = new GamesDatabase(); // creates the json database.
        }
        ItemModelImpl ItemModelObj = new ItemModelImpl();
        //try {
            File jsonFile = new File(database);
            ObjectMapper objectMapper = new ObjectMapper();
            Set<FreeGameItem> recordsSet = objectMapper.readValue(jsonFile, new TypeReference<>() { });
            ItemModelObj.gamesMap= recordsSet.stream().collect(Collectors.toMap(FreeGameItem::getTitle,
                    item->item));
        //} catch (Exception e) {
            //System.out.println("Cannot create an instance");
        //}
        return ItemModelObj;

    }

    /**
     * This method searches the gamesMap using name and returns the FreeGameItem associated with it.
     * @param name the game name given as string.
     * @return it returns the FreeGameItem object associated with that game name.
     */
    public FreeGameItem SearchByName(String name) {
        return this.gamesMap.get(name);
    }

    /**
     * This method invokes the write method from Display class to print the records as output.
     * @param records the list of records.
     * @param format the format of the output given.
     * @param out the instance of the output stream.
     */
    public void writeRecords(List<FreeGameItem> records, Formats format, OutputStream out) {
        Display.write(records, format, out);
    }
}
