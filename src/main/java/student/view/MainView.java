package student.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import student.controller.Controller;
import student.model.formatters.Formats;

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
        sortFilterView.setSortButtonListener(this::sortButtonListener);
        sortFilterView.setAddAllButtonListener(this::addAllButtonListener);
        exportButtonView.addExportListener(this::exportButtonListener);
    }

    /**
     * This method is the listener for the Search button.
     * @param e the action event is taken as input.
     */
    private void searchButtonListener(ActionEvent e) {
        String hostname = commandInputView.getCommandText();
        if (hostname == null || hostname.isEmpty()) {
            resultDisplayView.setResultText("Nothing to Search");
        }
        else {
            resultDisplayView.setResultText(this.controller.getSingleGame(hostname));
        }
    }

    /**
     * This method is the listener for the Add button.
     * @param e the action event is taken as input.
     */
    private void addButtonListener(ActionEvent e) {
        String input = JOptionPane.showInputDialog(searchAddRemoveView.getRemoveButton(),
                "Enter the Game Name to Add:", "Add Game", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.isEmpty()) {
            resultDisplayView.setResultText("Nothing to Add");
            return;
        }
        this.controller.addGameToList(input);
    }

    /**
     * This method is the listener for the List button.
     * @param e the action event is taken as input.
     */
    private void listButtonListener(ActionEvent e) {
        String[] options = {"JSON", "XML", "CSV", "PRETTY"};
        String input = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Choose the Format to Display:", "Formats Menu", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        switch (input) {
            case "JSON" -> resultDisplayView.setResultText(this.controller.getAllGamesList(Formats.JSON));
            case "XML" -> resultDisplayView.setResultText(this.controller.getAllGamesList(Formats.XML));
            case "CSV" -> resultDisplayView.setResultText(this.controller.getAllGamesList(Formats.CSV));
            case "PRETTY" -> resultDisplayView.setResultText(this.controller.getAllGamesList(Formats.PRETTY));
        }
    }

    /**
     * This method is the listener for the Remove button.
     * @param e the action event is taken as input.
     */
    private void removeButtonListener(ActionEvent e) {
        String input = JOptionPane.showInputDialog(searchAddRemoveView.getRemoveButton(),
                "Enter the Game Name to remove:", "Remove Game", JOptionPane.PLAIN_MESSAGE);
        resultDisplayView.setResultText(this.controller.removeGameFromList(input));
    }

    /**
     * This method is the listener for the Filter button.
     * @param e the action event is taken as input.
     */
    private void filterButtonListener(ActionEvent e) {
        String[] options = {"id", "title", "thumbnail", "short_description", "game_url",
                "genre", "platform", "publisher", "developer", "release_date"};
        String selectedValue = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a filter Parameter:", "Filter Parameter Menu", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        String[] options2 = {"==", "~=", "!=", ">", "<", ">=", "<="};
        String selectedValue2 = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a filter Type:", "Filter Type Menu", JOptionPane.QUESTION_MESSAGE,
                null, options2, options2[0]);

        String parameterValue = JOptionPane.showInputDialog(searchAddRemoveView.getRemoveButton(),
                "Enter the value:", "Filter Value", JOptionPane.PLAIN_MESSAGE);
        if (selectedValue != null && selectedValue2 != null && parameterValue != null) {
            String fullString = selectedValue + selectedValue2 + parameterValue;
            resultDisplayView.setResultText(this.controller.filterGames(fullString));
        } else {
            resultDisplayView.setResultText("Filter Incomplete");
        }
    }

    /**
     * This method is the listener for the Sort button.
     * @param e the action event is taken as input.
     */
    private void sortButtonListener(ActionEvent e) {
        String[] options = {"title", "genre", "publisher", "developer", "release_date"};

        String selectedValue = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a Sort Parameter:", "Sort Parameter Menu", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        String[] options2 = {"Ascending", "Descending"};
        String selectedValue2 = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a Sort Type:", "Sort Type Menu", JOptionPane.QUESTION_MESSAGE,
                null, options2, options2[0]);

        if (selectedValue == null || selectedValue2 == null) {
            resultDisplayView.setResultText("Sort option is null");
            return;
        }

        boolean choice = true;
        if (selectedValue2.equals("Descending")) {
            choice = false;
        }

        resultDisplayView.setResultText(this.controller.sortGames(selectedValue, choice));
    }

    /**
     * This method is the listener for the Add All button.
     * @param e the action event is taken as input.
     */
    private void addAllButtonListener(ActionEvent e) {
        resultDisplayView.setResultText(this.controller.addAllGamesToList());
    }

    private void exportButtonListener(ActionEvent e) {
        // Determine the file format based on user selection
        int userSelection = exportButtonView.showSaveDialog();

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = exportButtonView.getSelectedFile();
            String extension = exportButtonView.getFileExtension();

            Formats format;
            if (extension.equals("xml")) {
                format = Formats.XML;
            } else if (extension.equals("json")) {
                format = Formats.JSON;
            } else if (extension.equals("csv")) {
                format = Formats.CSV;
            } else {
                // Unsupported file format
                JOptionPane.showMessageDialog(this, "Unsupported file format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the current list is empty
            String data = controller.getAllGamesList(format);
            if (data.equals("Empty list")) {
                JOptionPane.showMessageDialog(this, "The current list is empty. Nothing to export.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (OutputStream out = new FileOutputStream(fileToSave)) {
                out.write(data.getBytes());
                JOptionPane.showMessageDialog(this, "Export successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error exporting file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}