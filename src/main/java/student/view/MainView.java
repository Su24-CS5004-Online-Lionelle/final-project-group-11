package student.view;

import student.controller.Controller;
import student.model.FreeGameItem;
import student.model.ItemModelImpl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * MainView is a JFrame that combines various views into a single window.
 * It includes panels for command input, action buttons, sort/filter buttons, result display, and export functionality.
 */
public class MainView extends JFrame {
    private CommandInputView commandInputView;
    private SearchAddRemoveView searchAddRemoveView;
    private SortFilterView sortFilterView;
    private ResultDisplayView resultDisplayView;
    private ExportButtonView exportButtonView;
    private Controller controller;

    /**
     * Constructs a new MainView.
     * Sets the title, default close operation, size, and layout of the frame.
     * Initializes and adds the various panels to the frame.
     */
    public MainView(Controller obj) {
        this.controller = obj;
        setTitle("Free Game Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        commandInputView = new CommandInputView();
        searchAddRemoveView = new SearchAddRemoveView();
        sortFilterView = new SortFilterView();
        resultDisplayView = new ResultDisplayView();
        exportButtonView = new ExportButtonView();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(commandInputView, BorderLayout.NORTH);
        topPanel.add(searchAddRemoveView, BorderLayout.CENTER);
        topPanel.add(sortFilterView, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(resultDisplayView, BorderLayout.CENTER);
        add(exportButtonView, BorderLayout.SOUTH);

        searchAddRemoveView.setSearchListener(this::searchButtonListener);
        searchAddRemoveView.setAddListener(this::addButtonListener);
        searchAddRemoveView.setRemoveListener(this::removeButtonListener);
        sortFilterView.setListButtonListener(this::listButtonListener);
        sortFilterView.setFilterButtonListener(this::filterButtonListener);

    }

    /**
     * This method is the listener for the Search button.
     * @param e the action event is taken as input.
     */
    private void searchButtonListener(ActionEvent e) {
        String hostname = commandInputView.getCommandText();
        if (hostname == null || hostname.isEmpty()) {
            resultDisplayView.setResultText("Nothing to search");
        }
        else {
            resultDisplayView.setResultText(this.controller.getSingleGame(hostname.toLowerCase()));
        }
    }

    /**
     * This method is the listener for the Add button.
     * @param e the action event is taken as input.
     */
    private void addButtonListener(ActionEvent e) {
        String input = JOptionPane.showInputDialog(searchAddRemoveView.getRemoveButton(),
                "Enter the Game Name to Add:", "Add Game", JOptionPane.PLAIN_MESSAGE);
        this.controller.addGameToList(input.toLowerCase());
    }

    /**
     * This method is the listener for the List button.
     * @param e the action event is taken as input.
     */
    private void listButtonListener(ActionEvent e) {
        resultDisplayView.setResultText(this.controller.getAllGamesList());
    }

    /**
     * This method is the listener for the Remove button.
     * @param e the action event is taken as input.
     */
    private void removeButtonListener(ActionEvent e) {
        String input = JOptionPane.showInputDialog(searchAddRemoveView.getRemoveButton(),
                "Enter the Game Name to remove:", "Remove Game", JOptionPane.PLAIN_MESSAGE);
        this.controller.removeGameFromList(input.toLowerCase());
    }

    private void filterButtonListener(ActionEvent e) {
        String[] options = {"id", "title", "thumbnail", "short_description", "game_url",
                "genre", "platform", "publisher", "developer", "release_date", "NONE"};

        String selectedValue = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a filter Parameter:", "Filter Parameter Menu", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (selectedValue != null) {
            resultDisplayView.setResultText("Filter Parameter: " + selectedValue);
        } else {
            resultDisplayView.setResultText("Filter Parameter: None");
        }

        String[] options2 = {"EQUALS", "CONTAINS", "NOT_EQUAL", "GREATER_THAN", "LESS_THAN",
                "GREATER_EQUAL", "LESS_EQUAL", "NONE"};

        String selectedValue2 = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a filter Type:", "Filter Type Menu", JOptionPane.QUESTION_MESSAGE,
                null, options2, options2[0]);

        if (selectedValue2 != null) {
            resultDisplayView.setResultText("Filter Parameter: " + selectedValue + "\n" +
                            "Filter Type: " + selectedValue2);
        } else {
            resultDisplayView.setResultText("Filter Parameter: None");
        }
    }
}