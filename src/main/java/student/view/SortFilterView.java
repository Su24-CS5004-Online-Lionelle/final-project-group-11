package student.view;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * SortFilterView is a JPanel that contains buttons for sorting, filtering, and listing actions.
 */
public class SortFilterView extends JPanel{
    private JButton sortButton;
    private JButton addAllButton;
    private JButton removeAllButton;
    private JButton filterButton;
    private JButton listButton;

    /**
     * Constructs a new SortFilterButtonsView.
     * Initializes the buttons and sets up the panel layout.
     */
    public SortFilterView() {
        setLayout(new GridLayout(1, 5));
        sortButton = new JButton("Sort");
        addAllButton = new JButton("Add All");
        removeAllButton = new JButton("Remove All");
        filterButton = new JButton("Filter");
        listButton = new JButton("List");

        add(sortButton);
        add(addAllButton);
        add(removeAllButton);
        add(filterButton);
        add(listButton);
    }

    public JButton getSortButton() {
        return sortButton;
    }

    public JButton getAddAllButton() {
        return addAllButton;
    }
    public JButton getRemoveAllButton() {
        return removeAllButton;
    }
    public JButton getFilterButton() {
        return filterButton;
    }

    /**
     * This method returns the list button.
     * @return the JButton type object associated with the list button.
     */
    public JButton getListButton() {
        return listButton;
    }

    /**
     * This method sets up the Action Listener to the list button.
     * @param listButtonListener the Action Listener is given as input.
     */
    public void setListButtonListener(ActionListener listButtonListener) {
        getListButton().addActionListener(listButtonListener);
    }

    /**
     * This method sets up the Action Listener to the Filter button.
     * @param filterButtonListener the Action Listener is given as input.
     */
    public void setFilterButtonListener(ActionListener filterButtonListener) {
        getFilterButton().addActionListener(filterButtonListener);
    }

    /**
     * This method sets up the Action Listener to the Sort button.
     * @param sortButtonListener the Action Listener is given as input.
     */
    public void setSortButtonListener(ActionListener sortButtonListener) {
        getSortButton().addActionListener(sortButtonListener);
    }

    /**
     * This method sets up the Action Listener to the Add All button.
     * @param addAllButtonListener the Action Listener is given as input.
     */
    public void setAddAllButtonListener(ActionListener addAllButtonListener) {
        getAddAllButton().addActionListener(addAllButtonListener);
    }

    /**
     * This method sets up the Action Listener to the Remove All button.
     * @param addAllButtonListener the Action Listener is given as input.
     */
    public void setRemoveAllButtonListener(ActionListener removeAllButtonListener) {
        getRemoveAllButton().addActionListener(removeAllButtonListener);
    }
}
