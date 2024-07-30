package student.controller;

import student.model.FreeGameItem;
import student.model.ItemModelImpl;
import student.model.Sorting;
import student.model.formatters.Filter;
import student.model.formatters.Formats;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        OutputStream out = new ByteArrayOutputStream();
        FreeGameItem game = this.model.SearchByName(gameName);
        if (game == null) {
            return "No game found";
        }
        this.model.updateTempGameList(List.of(game));
        printGame(game, out);
        return out.toString();
    }

    /**
     * This method gets the details of all games from the gameList and invokes the printGamesList method.
     * @return it returns the json strings from the list as output.
     */
    public String getAllGamesList(Formats formats) {
        OutputStream out = new ByteArrayOutputStream();
        List<FreeGameItem> gamesList = this.model.getGameList();
        if (gamesList.isEmpty()) {
            return "Empty list";
        }
        printGamesList(gamesList, formats, out);
        return out.toString();
    }

    /**
     * This method adds all the games from the temporary list to the main games list.
     * @return it returns the messages as string.
     */
    public String addAllGamesToList() {
        OutputStream out = new ByteArrayOutputStream();
        if (this.model.getTempGamesList().isEmpty()) {
            return "Nothing to add";
        }

        if (this.model.getGameList().isEmpty()) {
            for (FreeGameItem gameItem : this.model.getTempGamesList()) {
                this.model.addItem(gameItem);
            }
            return "Done";
        }

        List<FreeGameItem> tempGames = this.model.getTempGamesList();
        List<FreeGameItem> games = this.model.getGameList();
        Set<Integer> gameIds = new HashSet<>();
        for (FreeGameItem game : games) {
            gameIds.add(game.getId());
        }

        for (FreeGameItem game : tempGames) {
            if (!gameIds.contains(game.getId())) {
                this.model.addItem(game);
            }
        }

        printGamesList(this.model.getGameList(), Formats.JSON, out);
        return "Done";
    }

    /**
     * This method adds the game from temp list after the add button is clicked.
     */
    public void addGameToList(String name) {
        if (this.model.getGameList().isEmpty() && this.model.checkGameExists(name)) {
            this.model.addItem(this.model.getGameFromMap(name));
        }
        else if (this.model.checkGameExists(name) && !this.model.checkGameList(name)) {
            this.model.addItem(this.model.getGameFromMap(name));
        }
    }

    /**
     * This method removes the game from gameList.
     * The game name is given as input after the remove button is clicked.
     * @param name the game name is given as string.
     */
    public String removeGameFromList(String name){
        if (name == null || name.isEmpty()) {
            return "No Name given";
        }
        else if (!name.isEmpty() && model.checkGameList(name)) {
            this.model.removeItem(this.model.getGameFromMap(name));
            return "Removed Game";
        }
        return "Game not found/not exist";
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
    public void printGamesList(List<FreeGameItem> games, Formats format, OutputStream out) {
        this.model.writeRecords(games, format, out);
    }

    /**
     * This method filters the games and updates the temporary games list.
     * @param filterString the filter string is given as input.
     * @return it returns the filtered games in json format as output.
     */
    public String filterGames(String filterString) {
        List <FreeGameItem> filteredGamesList = Filter.filterSingle(this.model.getItems().stream(),
                filterString).toList();
        this.model.updateTempGameList(filteredGamesList);
        OutputStream out = new ByteArrayOutputStream();;
        printGamesList(this.model.getTempGamesList(), Formats.JSON, out);
        return out.toString();
    }

    /**
     * This method sorts the games and updates the temporary games list.
     * @param column the column on which the sorting needs to be performed.
     * @return it returns the sorted games in json format as output.
     */
    public String sortGames(String column, Boolean order) {
        List <FreeGameItem> sortedGamesList = Sorting.sortItems(this.model.getTempGamesList(), column,
                order);
        if (this.model.getTempGamesList().isEmpty()) {
            return "Empty list";
        }
        this.model.updateTempGameList(sortedGamesList);
        OutputStream out = new ByteArrayOutputStream();
        printGamesList(sortedGamesList, Formats.JSON, out);
        return out.toString();
    }
}
