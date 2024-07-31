package student.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private List<FreeGameItem> gameList;

    /** HashMap that contains the game name as key and record as value. */
    private Map<String, FreeGameItem> gamesMap;

    /** List to store the temporary Filtered/Sorted games. */
    private List<FreeGameItem> tempGamesList;

    /** Constructor for the ItemModelImpl class */
    public ItemModelImpl() {
        this.gameList = new ArrayList<>();
        this.gamesMap = new HashMap<>();
        this.tempGamesList = new ArrayList<>();
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
     * This method retrieves value associated with the game title given as input from the gamesMap.
     * @param gameName the game title/name is given as String input.
     * @return it returns the FreeGameItem value associated with that game title key.
     */
    public FreeGameItem getGameFromMap(String gameName) {
        return this.gamesMap.get(gameName.toLowerCase());
    }

    /**
     * This method checks if the game exists in the database.
     * @param gameName the game name given as string.
     * @return it returns boolean true if exists. Else, it returns false.
     */
    public Boolean checkGameExists(String gameName) {
        return this.gamesMap.containsKey(gameName.toLowerCase());
    }

    /**
     * This method checks if the game exists in the user's gameList.
     * @param gameName the game name given as string.
     * @return it returns boolean true if exists. Else, it returns false.
     */
    public Boolean checkGameList(String gameName) {
        for (FreeGameItem gameItem : this.gameList) {
            if (gameItem.getTitle().equals(gameName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method updates the temporary gamesList.
     * @param newGamesList the newly filtered/sorted list of games.
     */
    public void updateTempGameList(List<FreeGameItem> newGamesList) {
        this.tempGamesList.clear();
        this.tempGamesList.addAll(newGamesList);
    }

    /**
     * This method returns the temporary games list.
     * @return it returns the temporary games list.
     */
    public List<FreeGameItem> getTempGamesList() {
        return this.tempGamesList;
    }

    @Override
    public void addItem(FreeGameItem item) {
        this.gamesMap.put(item.getTitle().toLowerCase(), item);
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
        this.gamesMap.remove(game.getTitle().toLowerCase());
        this.gameList.remove(game);
    }

    @Override
    public void saveList(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), this.gameList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the list to the file.");
        }
    }

    @Override
    public void loadList(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<FreeGameItem> loadedList = objectMapper.readValue(new File(filePath), new TypeReference<>() { });
            this.gameList.clear();
            this.gameList.addAll(loadedList);
            this.updateTempGameList(this.gameList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the list from the file.");
        }
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
        try {
            File jsonFile = new File(database);
            ObjectMapper objectMapper = new ObjectMapper();
            Set<FreeGameItem> recordsSet = objectMapper.readValue(jsonFile, new TypeReference<>() { });
            ItemModelObj.gamesMap= recordsSet.stream().collect(Collectors.toMap(item -> item.getTitle().toLowerCase(),
                    item->item));
        } catch (Exception e) {
            System.out.println("Cannot create an instance");
        }
        return ItemModelObj;

    }

    /**
     * This method searches the gamesMap using name and returns the FreeGameItem associated with it.
     * @param name the game name given as string.
     * @return it returns the FreeGameItem object associated with that game name.
     */
    public FreeGameItem SearchByName(String name) {
        return this.gamesMap.get(name.toLowerCase());
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
