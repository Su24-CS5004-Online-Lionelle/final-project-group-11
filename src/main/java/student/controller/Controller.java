package student.controller;

import student.model.FreeGameItem;
import student.model.ItemModelImpl;
import student.model.formatters.Formats;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

public class Controller {

    /** The variable holds the instance of ItemModelImpl class. */
    private final ItemModelImpl model;

    /** Thw variable holds the path of the database file as string. */
    private static final String DATABASE = "data/freegamerecords.json";

    /**
     * The default constructor creates the database file and receives the instance of the model.
     * @param model the instance of the model given as parameter.
     * @throws Exception it throws when the database cannot be created.
     */
    public Controller(ItemModelImpl model) throws Exception {
        this.model = model.createDatabase(DATABASE); //creates the database during the start of program.
    }

    /**
     * This method gets the record of a single game using the given name and returns the details as string.
     * @param gameName the game name given as string.
     * @return it returns the details as the json format string.
     */
    public String getSingleGame(String gameName) {
        this.model.getTempGames().clear();
        OutputStream out = new ByteArrayOutputStream();
        FreeGameItem game = this.model.SearchByName(gameName);
        if (game == null) {
            return "No game found";
        }
        this.model.addTempItem(game); // adds the game item to the temporary list/often used as cache.
        printGame(game, out);
        return out.toString();
    }

    /**
     * This method gets the details of all games from the gameList and invokes the printGamesList method.
     * @return it returns the json strings from the list as output.
     */
    public String getAllGamesList() {
        OutputStream out = new ByteArrayOutputStream();
        List<FreeGameItem> gamesList = this.model.getGameList();
        if (gamesList.isEmpty()) {
            return "Empty list";
        }
        printGamesList(gamesList, out);
        return out.toString();
    }

    /**
     * This method adds the game from temp list after the add button is clicked.
     */
    public void AddGameToList() {
        if (!this.model.getTempGames().isEmpty()) {
            this.model.addItem(this.model.getTempGames().get(0));
        }
    }

    /**
     * This method removes the game from gameList.
     * The game name is given as input after the remove button is clicked.
     * @param name the game name is given as string.
     */
    public void removeGameFromList(String name){
        if (!name.isEmpty()) {
            this.model.removeItem(this.model.getGameFromMap(name.toLowerCase()));
        }
    }

    /**
     * This method invokes the writeRecords method to write a single game as output.
     * @param game a single FreeGameItem object is given as input.
     * @param out the output stream object given as parameter.
     */
    public void printGame(FreeGameItem game, OutputStream out) {
        this.model.writeRecords(List.of(game), Formats.JSON, out);
    }

    /**
     * This method invokes the writeRecords method to write the list of games as output.
     * @param games the list of FreeGameItem objects is given as input.
     * @param out the output stream object given as parameter.
     */
    public void printGamesList(List<FreeGameItem> games, OutputStream out) {
        this.model.writeRecords(games, Formats.JSON, out);
    }
}
