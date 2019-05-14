package smith.seaport;

import smith.util.TextPrompt;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class for initializing the program and constructing the GUI. The class also allows and handles
 * user input.
 * <p>
 * Program Description: Simulates some of the aspects of a number of Sea Ports.
 *
 * @author Hunter Smith
 */
@SuppressWarnings("FieldCanBeLocal")
public class SeaPortProgram extends JFrame {

  private World world;

  private JPanel toolBar, searchPanel;
  private JSplitPane splitPane;
  private JTabbedPane outputPane;
  private JTextArea worldOutArea, searchOutArea;
  private JTextField indexTxt, nameTxt, skillTxt;
  private JButton btnSearch;
  private JMenuBar menuBar;
  private JMenu fileMenu, helpMenu;
  private JMenuItem loadMenuItem, readmeMenuItem;

  /**
   * Instantiates a new SeaPort program by creating and presenting a GUI Seaport app to the user.
   */
  public SeaPortProgram() {
    setTitle("SeaPort Program");
    setSize(1280, 720);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setJMenuBar(initMenuBar());
    add(splitPaneTest(), BorderLayout.CENTER);
    this.setVisible(true);
  }

  /**
   * Class create and instantiates the SeaPrograms display components. Allows user to easily resize
   * the toolbar and output panes.
   * <p>
   * <b>modified from oracle demo</b>
   * <p> Source
   * <a href="https://docs.oracle.com/javase/tutorial/uiswing/components/splitpane.html">oracle
   * JSplitPane</a>
   *
   * @return the JSplitPane
   */
  private JSplitPane splitPaneTest() {
    //Create a split pane with the two scroll panes in it.
    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        initToolbar(), initOutputPane());
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerSize(10);
    splitPane.setDividerLocation(200);

    //Provide minimum sizes for the two components in the split pane
    toolBar.setMinimumSize(new Dimension(150, Integer.MAX_VALUE));
    outputPane.setMinimumSize(new Dimension(250, Integer.MAX_VALUE));
    splitPane.setResizeWeight(0.2);
    return splitPane;
  }

  /**
   * Creates and initializes a JMenuBar that contains file and help menus.
   *
   * @return JMenuBar that contains file and help menus
   */
  private JMenuBar initMenuBar() {
    menuBar = new JMenuBar();

    //create file menu
    fileMenu = new JMenu("File");
    loadMenuItem = new JMenuItem("Load FIle");
    loadMenuItem.getAccessibleContext().setAccessibleDescription("Loads seaport text file");
    loadMenuItem.addActionListener(e -> loadFile());
    fileMenu.add(loadMenuItem);
    menuBar.add(fileMenu);

    //create help menu
    helpMenu = new JMenu("Help");
    readmeMenuItem = new JMenuItem("Instructions");
    readmeMenuItem.getAccessibleContext()
        .setAccessibleDescription("Opens program's pdf instructions");
    readmeMenuItem.addActionListener(e -> openReadMe());
    helpMenu.add(readmeMenuItem);
    menuBar.add(helpMenu);

    return menuBar;
  }

  /**
   * Opens pdf instructions for SeaPort program.
   */
  private void openReadMe() {
    if (Desktop.isDesktopSupported()) {
      try {
        File myFile = new File("./src/res/SeaPortProgram_UserManual.pdf");
        Desktop.getDesktop().open(myFile);
      } catch (IOException ex) {
        // Missing PDF viewer
      }
    }
  }

  /**
   * Creates and initializes JPanel Toolbar to hold the search JPanel and future components.
   *
   * @return JPanel used for displaying toolbar components.
   */
  private JPanel initToolbar() {
    toolBar = new JPanel();
    toolBar.setPreferredSize(new Dimension(225, 750));
    toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));
    toolBar.add(Box.createRigidArea(new Dimension(10, 25)));
    toolBar.add(initSearchPanel());
    return toolBar;
  }

  /**
   * Creates and initializes a search JPanel to contain search components. Supports searching by
   * {@link Thing}'s index, name, and skill.
   * <p>
   * Searches can be multivariate with partial results.
   *
   * @return JPanel used for displaying search options and button.
   */

  private JPanel initSearchPanel() {
    searchPanel = new JPanel();
    searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
    searchPanel.setBorder(BorderFactory.createTitledBorder("Search Options"));

    searchPanel.add(indexTxt = new JTextField());
    new TextPrompt("Index", indexTxt);
    indexTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, indexTxt.getPreferredSize().height));

    searchPanel.add(nameTxt = new JTextField());
    new TextPrompt("Name", nameTxt);
    nameTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameTxt.getPreferredSize().height));

    searchPanel.add(skillTxt = new JTextField());
    new TextPrompt("Skill", skillTxt);
    skillTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, skillTxt.getPreferredSize().height));

    searchPanel.add(btnSearch = new JButton("Search"));
    btnSearch.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    btnSearch.addActionListener(e -> search());

    return searchPanel;
  }

  /**
   * Creates and initializes a JTabbedPane to contain output displays.
   *
   * @return JTabbedPane used for displaying output of searches and loading files.
   */
  private JTabbedPane initOutputPane() {
    outputPane = new JTabbedPane();

    //setup scrollpane with textarea
    JScrollPane scrollWorld = new JScrollPane(worldOutArea = new JTextArea());
    configScrollPane(scrollWorld, worldOutArea);

    JScrollPane scrollSearch = new JScrollPane(searchOutArea = new JTextArea());
    configScrollPane(scrollSearch, searchOutArea);

    //add tabs and disable tab if needed
    outputPane.addTab("World View", scrollWorld);
    outputPane.addTab("Search View", scrollSearch);
    outputPane.setEnabledAt(1,false);
    outputPane.addTab("Jobs View", new JLabel("Under Construction"));
    outputPane.setEnabledAt(2,false);

    //make tab contents not editable and set initial text
    worldOutArea.setEditable(false);
    worldOutArea.setText("<No File loaded>");
    searchOutArea.setEditable(false);


    //set font
    worldOutArea.setFont(new java.awt.Font("Monospaced", Font.PLAIN, 12));
    searchOutArea.setFont(new java.awt.Font("Monospaced", Font.PLAIN, 12));

    return outputPane;
  }

  /**
   * Configures Scrollbars policy to focus on the top of the content area instead of the bottom.
   * <p>
   * A helper method to avoid duplication of code.
   *
   * @param scrollPane the scrollPane to assign policy
   * @param contents the JComponent(TextArea) attached to the scrollPane
   */
  private void configScrollPane(JScrollPane scrollPane, JTextArea contents) {
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    DefaultCaret caret = (DefaultCaret) contents.getCaret();
    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
  }

  /**
   * Presents the user with a JFileChooser and loads selected text file into a scanner. The scanner
   * is used to instantiate and output a new SeaPort program 'World'.
   * <p>
   * method uses/modifies code from
   * <a href="https://www.mkyong.com/swing/java-swing-jfilechooser-example/">mkyong.com</a>.
   */
  private void loadFile() {
    JFileChooser jfc = new JFileChooser("./src/res"); //temp set to my res folder for convenience
    jfc.setDialogTitle("Select SeaPort text file to load: ");
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
    jfc.addChoosableFileFilter(filter);

    int returnValue = jfc.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File inputFile = new File(jfc.getSelectedFile().getPath());
      try (Scanner sc = new Scanner(new FileReader(inputFile))) {
        world = new World(sc);
        worldOutArea.setText(world.toString());
        outputPane.setSelectedIndex(0); //switch to world view
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Loading File Failed.");
      }
    }
  }

  /**
   * Performs a search given user inputted parameters. Search terms are allowed to pe partial
   * matches
   * <p>
   * Gets text inputted by the user in the search textfield's (index, name, and skill textfield's)
   * and if the {@link Thing} matches all non-empty text fields the result is outputted by the
   * {@link #outputSearchResults(List)} helper method.
   * <p>
   * <b>Note: The method also switches focus from the current selected tab to the search view
   * tab.</b>
   */
  private void search() {
    if (world == null) { //if no world loaded, show error message and return
      JOptionPane.showMessageDialog(this,
          "Please Load a File.\nSee instructions in help.");
      return;
    }
    outputPane.setEnabledAt(1,true); //enable tab
    outputPane.setSelectedIndex(1); //switch to search view
    List<Thing> results = new ArrayList<>();
    int count = 0; //counts the times a result should appear

    if (!indexTxt.getText().isEmpty()) {
      try {
        results.addAll(world.searchForIndex(Integer.parseInt(indexTxt.getText())));
        count++;
      } catch (NumberFormatException ex) {
        //Ignore for now
      }
    }
    if (!nameTxt.getText().isEmpty()) {
      results.addAll(world.searchForName(nameTxt.getText()));
      count++;
    }
    if (!skillTxt.getText().isEmpty()) {
      results.addAll(world.searchForSkill(skillTxt.getText()));
      count++;
    }

    final int freq = count; // convert count to a final variable for lambda usage.
    outputSearchResults(results
        .stream()
        .filter(thing -> Collections.frequency(results, thing) == freq)
        .distinct()
        .collect(Collectors.toList()));
  }


  /**
   * Outputs the results of a users search to the Search View tab's TextArea. A {@link #search()}
   * Helper method.
   *
   * @param searchResults the results of the users search inputs
   */
  private void outputSearchResults(List<Thing> searchResults) {
    if (searchResults.isEmpty()) {
      searchOutArea.setText("No Results found.");

    } else {
      //searchResults.sort(Comparator.naturalOrder());
      searchOutArea.setText("Search Results:\n\n");
      searchResults.forEach(result -> searchOutArea.append("\n>" + result));
    }
  }


  /**
   * The entry point of application.
   *
   * @param args the input arguments(Not used)
   */
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
      SeaPortProgram program = new SeaPortProgram();
    });
  }

}
