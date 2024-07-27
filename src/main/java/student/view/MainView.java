package student.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

    /**
     * Constructs a new MainView.
     * Sets the title, default close operation, size, and layout of the frame.
     * Initializes and adds the various panels to the frame.
     */
    public MainView() {
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
    }
}