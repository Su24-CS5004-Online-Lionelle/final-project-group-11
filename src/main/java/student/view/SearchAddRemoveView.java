package student.view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * SearchAddRemoveView is a JPanel that contains buttons for search, add, and remove actions.
 */
public class SearchAddRemoveView extends JPanel{
    private JButton searchButton;
    private JButton addButton;
    private JButton removeButton;

    /**
     * Constructs a new ActionButtonsView.
     * Initializes the buttons and sets up the panel layout.
     */
    public SearchAddRemoveView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        searchButton = new JButton("Search");
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");

        gbc.insets = new Insets(5, 0, 5, 0); // add padding around components

       // Add the search button with gridwidth of 2
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.85; // Set weightx to 2.0 to be twice as wide
        add(searchButton, gbc);

        // Add the add button
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.72; 
        add(addButton, gbc);

        // Add the remove button
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.62; 
        add(removeButton, gbc);
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }
}
