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
    private List<FreeGameItem> gameList;

    /** HashMap that contains the game name as key and record as value. */
    private Map<String, FreeGameItem> gamesMap = new HashMap<>();

    /** Variable to hold the database path. */
    private static final String DATABASE = "data/freegamerecords.json";

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

    @Override
    public void addItem(FreeGameItem item) {
        throw new NotImplementedException("no completed yet");
    }

    @Override
    public void removeItem(FreeGameItem item) {
        throw new NotImplementedException("no completed yet");
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
    public ItemModelImpl getInstance(String database) throws IOException {
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
     * This method invokes the write method from Display class to print the records as output.
     * @param records the list of records.
     * @param format the format of the output given.
     * @param out the instance of the output stream.
     */
    public void writeRecords(List<FreeGameItem> records, Formats format, OutputStream out) {
        Display.write(records, format, out);
    }


    public static void main(String[] args) throws Exception {
        File file = new File(DATABASE);
        if (file.exists() & file.length() == 0) {
            GamesDatabase instance = new GamesDatabase(); // creates the json database.
        }
        //(To check if it creates the database, clear the contents of the database).
        ItemModelImpl ItemModelObj = new ItemModelImpl(); //creates the instance of this class.
        ItemModelObj = ItemModelObj.getInstance(DATABASE); //creates the new instance and a record.
        OutputStream out = System.out;
        //System.out.println(ItemModelObj.getItems()); // prints a list of records.
        // prints the records as per the given format.
        ItemModelObj.writeRecords(ItemModelObj.getItems(), Formats.JSON, out);
    }
}
