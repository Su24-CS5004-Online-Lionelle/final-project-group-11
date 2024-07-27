package student.view;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * SortFilterView is a JPanel that contains buttons for sorting, filtering, and listing actions.
 */
public class SortFilterView extends JPanel{
    private JButton sortAscButton;
    private JButton sortDescButton;
    private JButton filterButton;
    private JButton listButton;

    /**
     * Constructs a new SortFilterButtonsView.
     * Initializes the buttons and sets up the panel layout.
     */
    public SortFilterView() {
        setLayout(new GridLayout(1, 4));
        sortAscButton = new JButton("Sort (asc)");
        sortDescButton = new JButton("Sort (desc)");
        filterButton = new JButton("Filter");
        listButton = new JButton("List");

        add(sortAscButton);
        add(sortDescButton);
        add(filterButton);
        add(listButton);
    }

    public JButton getSortAscButton() {
        return sortAscButton;
    }

    public JButton getSortDescButton() {
        return sortDescButton;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getListButton() {
        return listButton;
    }
}
