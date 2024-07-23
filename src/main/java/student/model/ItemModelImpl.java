package student.model;

import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import student.model.net.NetUtils;

/**
 * ItemModel class to handle the game items.
 */
public class ItemModelImpl implements ItemModel{

    /** Game list that contains the free games. */
    private List<FreeGameItem> gameList;

    public ItemModelImpl() {
        this.gameList = new ArrayList<>();
        // Load the initial data from the database file
        try {
            loadList(DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FreeGameItem> getItems() {
        throw new NotImplementedException("no completed yet");
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

}
