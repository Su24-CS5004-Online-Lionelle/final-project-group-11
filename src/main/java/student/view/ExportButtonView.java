package student.view;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * ExportButtonView is a JPanel that contains a button for exporting results.
 */
public class ExportButtonView extends JPanel {
    private JButton exportButton;
    private JFileChooser fileChooser;

    /**
     * Constructs a new ExportButtonView.
     * Initializes the export button and sets up the panel layout.
     */
    public ExportButtonView() {
        setLayout(new BorderLayout());
        exportButton = new JButton("Export");
        add(exportButton, BorderLayout.CENTER);

        // Initialize JFileChooser
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Export List");
        /*
         * feel free to delete file formats that you don't want to support
         */
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV (*.csv)", "csv"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text (*.txt)", "txt"));
    }

    /**
     * Adds an ActionListener to the export button.
     * 
     * @param listener the ActionListener to be added
     */
    public void addExportListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }

    /**
     * Displays the save dialog and returns the user's action.
     * 
     * @return the return state of the file chooser on pop-up
     */
    public int showSaveDialog() {
        return fileChooser.showSaveDialog(this);
    }

    /**
     * Gets the selected file from the file chooser.
     * 
     * @return the selected file
     */
    public File getSelectedFile() {
        return fileChooser.getSelectedFile();
    }

    /**
     * Gets the selected file extension from the file chooser.
     * 
     * @return the selected file extension
     */
    public String getFileExtension() {
        FileNameExtensionFilter filter = (FileNameExtensionFilter) fileChooser.getFileFilter();
        String[] extensions = filter.getExtensions();
        return extensions[0];
    }
}