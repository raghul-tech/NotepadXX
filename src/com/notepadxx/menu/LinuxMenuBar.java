package com.notepadxx.menu;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.notepadxx.exit.CLOSE;
import com.notepadxx.exit.ExitFrame;
import com.notepadxx.notepadxx.NotepadXXV1_2_1;
import com.notepadxx.notepadxx.Texteditor;

public class LinuxMenuBar {
//	private static Font defaultFont = new Font("Monospaced", Font.PLAIN, 12);  
	
	/*public static  JMenuBar LinuxcreateMenuBar1() {
        JMenuBar menuBar = new JMenuBar();

     
        
        // 'File' menu 
        JMenu fileMenu = new JMenu("File"); 
	    JMenuItem newFile = new JMenuItem(String.format("%-40s %s",     	    "New                        ", "      Ctrl+N"));
	    JMenuItem openFile = new JMenuItem(String.format("%-40s %s",    	    "Open File...               ", "      Ctrl+O"));
	    JMenuItem saveFile = new JMenuItem(String.format("%-40s %s",       	    "Save                       ", "      Ctrl+S"));
	    JMenuItem saveAsFile = new JMenuItem(String.format("%-40s %s",  	    "Save As...                 ", "  Ctrl+Alt+S"));
	    JMenuItem saveCopy = new JMenuItem(String.format("%-40s %s",   		    "Save a Copy As...          ", "            "));
	    JMenuItem saveAllFile = new JMenuItem(String.format("%-40s %s", 	    "Save All                   ", "Ctrl+Shift+S"));
	    JMenuItem RenameFile = new JMenuItem(String.format("%-40s %s", 	    "Rename...                   ", "               "));
	    JMenuItem closeTab = new JMenuItem(String.format("%-40s %s",    		"Close Tab                  ", "      Ctrl+W"));
	    JMenuItem closeAllTab = new JMenuItem(String.format("%-40s %s",         "Close All Tabs             ", "Ctrl+Shift+W"));
	    JMenuItem restoreRecentFile  = new JMenuItem(String.format("%-40s %s",  "Restore Recent Closed File ", "Ctrl+Shift+T"));
	    JMenuItem openAllRecentFiles = new JMenuItem(String.format("%-40s %s",  "Open All Recent Files      ", "            "));
	    JMenuItem emptyAllRecentFiles = new JMenuItem(String.format("%-40s %s", "Empty All Recent File List ", "            "));
	    JMenuItem ExitTab = new JMenuItem(String.format("%-40s %s",             "Exit                       ", "      Alt+F4"));
	    
	    
	    newFile.setFont(defaultFont);
	    openFile.setFont(defaultFont);
	    saveFile.setFont(defaultFont);
	    saveAsFile.setFont(defaultFont);
	    saveCopy.setFont(defaultFont);
	    saveAllFile.setFont(defaultFont);
	    RenameFile.setFont(defaultFont);
	    closeTab.setFont(defaultFont);
	    closeAllTab.setFont(defaultFont);
	    restoreRecentFile.setFont(defaultFont);
	    openAllRecentFiles.setFont(defaultFont);
	    emptyAllRecentFiles.setFont(defaultFont);
	    ExitTab.setFont(defaultFont);
	    
	    
	    
	    // 'Edit' menu
        JMenu editMenu = new JMenu("Edit");
	    JMenuItem undoText = new JMenuItem(String.format("%-20s %s",  "Undo ",  "Ctrl+Z"));
	    JMenuItem redoText = new JMenuItem(String.format("%-20s %s",  "Redo ",  "Ctrl+Y"));
	    JMenuItem cutText = new JMenuItem(String.format("%-20s %s",   "Cut  ",  "Ctrl+X"));
	    JMenuItem copyText = new JMenuItem(String.format("%-20s %s",  "Copy ",  "Ctrl+C"));
	    JMenuItem pasteText = new JMenuItem(String.format("%-20s %s", "Paste",  "Ctrl+V"));
	    JMenuItem printText = new JMenuItem(String.format("%-20s %s", "Print",  "Ctrl+P"));
	    
	    undoText.setFont(defaultFont);
	    redoText.setFont(defaultFont);
	    cutText.setFont(defaultFont);
	    copyText.setFont(defaultFont);
	    pasteText.setFont(defaultFont);
	    printText.setFont(defaultFont);
	    
	    
	    //search menu
	    JMenu searchMenu = new JMenu("Search");
	    JMenuItem findText = new JMenuItem(String.format("%-10s %s",    "Find    ", "Ctrl+F"));
	    JMenuItem replaceText = new JMenuItem(String.format("%-10s %s", "Replace ", "Ctrl+H"));
	   
	    findText.setFont(defaultFont);
	    replaceText.setFont(defaultFont);
	   
	    
	    
	    // 'View' menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem openExplorer = new JMenuItem(String.format("%-20s %s", "File Explorer  ", "Ctrl+Shift+E"));
	    JMenuItem openCmd = new JMenuItem(String.format("%-20s %s",      "Terminal       ", "Ctrl+Shift+C"));
	    JMenuItem adminCmd = new JMenuItem(String.format("%-20s %s",     "Root Terminal  ", "  Ctrl+Alt+A"));
	    JMenuItem firefox = new JMenuItem(String.format("%-20s %s",      "FireFox        ", "  Ctrl+Alt+F"));
	    JMenuItem chrome = new JMenuItem(String.format("%-20s %s",       "GoogleChrome   ", "  Ctrl+Alt+C"));
	    JMenuItem tor = new JMenuItem(String.format("%-20s %s",          "TorBrowser     ", "  Ctrl+Alt+T"));
	   
	    openExplorer.setFont(defaultFont);
	    openCmd.setFont(defaultFont);
	    adminCmd.setFont(defaultFont);
	    firefox.setFont(defaultFont);
	    chrome.setFont(defaultFont);
	    tor.setFont(defaultFont);
                
        // Tool menu
        JMenu toolMenu = new JMenu("Tools");
        JMenuItem chooseFont = new JMenuItem(String.format("%-20s %s",  "choose...   ", "      "));
	    JMenuItem largerFont = new JMenuItem(String.format("%-20s %s",  "Zoom In     ", "Ctrl+="));
	    JMenuItem smallerFont = new JMenuItem(String.format("%-20s %s", "Zoom out    ", "Ctrl+-"));
	    JMenuItem resetFont = new JMenuItem(String.format("%-20s %s",   "Reset size  ", "Ctrl+0"));
	   
        
	    chooseFont.setFont(defaultFont);
	    largerFont.setFont(defaultFont);
	    smallerFont.setFont(defaultFont);
	    resetFont.setFont(defaultFont);
	   
	    
	    
        //Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About NotepadXX");
        JMenuItem reportBugItem = new JMenuItem("Report a Bug");
        JMenuItem donateItem = new JMenuItem("<html>Support Us<font color='red'>&#x2764;</font></html>");
        JMenuItem DiscordItem = new JMenuItem("<html>Join Discord<font color='yellow'> \uD83D\uDC4D </font></html>");
        JMenuItem checkForUpdatesItem = new JMenuItem("Check for Updates...");
        
        aboutItem.setFont(defaultFont);
        reportBugItem.setFont(defaultFont);
	    donateItem.setFont(defaultFont);
	    DiscordItem.setFont(defaultFont);
	    checkForUpdatesItem.setFont(defaultFont);
	    
        
        
      
        
        //File Menu
        newFile.addActionListener(e -> texteditor().newFile());
        openFile.addActionListener(e -> texteditor().openFile());
        saveFile.addActionListener(e -> texteditor().saveFile());
        saveAsFile.addActionListener(e -> texteditor().saveFileAs());
        saveCopy.addActionListener(e -> texteditor().saveFileAs());
        saveAllFile.addActionListener(e -> texteditor().saveAll());
        RenameFile.addActionListener(e -> texteditor().RenameFile());
        closeTab.addActionListener(e -> CLOSE.closeTab(texteditor()));
        closeAllTab.addActionListener(e -> CLOSE.closeAllTab());
        restoreRecentFile.addActionListener(e ->NotepadXXV1_2_0.openRecentClosedTab());
        openAllRecentFiles.addActionListener(e ->NotepadXXV1_2_0.openAllRecentClosedTabs());
        emptyAllRecentFiles.addActionListener(e ->NotepadXXV1_2_0.clearAllRecentClosedTabs());
        ExitTab.addActionListener(e -> ExitFrame.exit());

        //Edit menu
        undoText.addActionListener(e -> texteditor().undoText());
        redoText.addActionListener(e -> texteditor().redoText());
        cutText.addActionListener(e -> texteditor().cutText());
        copyText.addActionListener(e -> texteditor().copyText());
        pasteText.addActionListener(e -> texteditor().pasteText());
        
        //Search Menu
        findText.addActionListener(e -> texteditor().findText());
        replaceText.addActionListener(e -> texteditor().replaceText());
      
        
        //view menu
        printText.addActionListener(e -> texteditor().printText());
        openExplorer.addActionListener(e -> texteditor().Explorer());
        openCmd.addActionListener(e -> texteditor().Cmd());
        adminCmd.addActionListener(e -> texteditor().adminCmd());
        firefox.addActionListener(e -> texteditor().FireFox());
        chrome.addActionListener(e -> texteditor().chromeBrowser());
        tor.addActionListener(e-> texteditor().TorBrowser());
        
        // Tools menu
        chooseFont.addActionListener(e -> texteditor().chooseFont());
        largerFont.addActionListener(e -> texteditor().setFontSize(+2));
        smallerFont.addActionListener(e -> texteditor().setFontSize(-2));
        resetFont.addActionListener(e -> texteditor().resetFontSize());
        
        //Help Menu
        aboutItem.addActionListener(e -> AboutMenu.showAboutDialog());
        checkForUpdatesItem.addActionListener(e ->NotepadXXV1_2_0.onCheckForUpdatesMenuSelected() );//.checkForUpdates());
        reportBugItem.addActionListener(e -> texteditor().reportBug());
        DiscordItem.addActionListener(e -> texteditor().Discord());
        donateItem.addActionListener(e -> texteditor().Donate());
     
        
        
        //file menu
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.add(saveCopy);
        fileMenu.add(saveAllFile);
        fileMenu.add(RenameFile);
        fileMenu.addSeparator();
        fileMenu.add(closeTab);
        fileMenu.add(closeAllTab);
        fileMenu.addSeparator();
        fileMenu.add(restoreRecentFile);
        fileMenu.add(openAllRecentFiles);
        fileMenu.add(emptyAllRecentFiles);
        fileMenu.addSeparator();
        fileMenu.add(ExitTab);
       
        
        //Edit menu 
        editMenu.add(undoText);
        editMenu.add(redoText);
        editMenu.addSeparator();
        editMenu.add(cutText);
        editMenu.add(copyText);
        editMenu.add(pasteText);
        editMenu.addSeparator();
        editMenu.add(printText);
        
        //Search menu
        searchMenu.add(findText);
        searchMenu.add(replaceText);

        //View Menu
         viewMenu.add(openExplorer);
         viewMenu.addSeparator();
         viewMenu.add(openCmd);
         viewMenu.add(adminCmd);
         viewMenu.addSeparator();
         viewMenu.add(firefox);
         viewMenu.add(chrome);
         viewMenu.add(tor);

        //tool menu
        toolMenu.add(chooseFont);
        toolMenu.addSeparator();
        toolMenu.add(largerFont);
        toolMenu.add(smallerFont);
        toolMenu.addSeparator();
        toolMenu.add(resetFont);
        
        //help menu
        helpMenu.add(aboutItem);
        helpMenu.addSeparator();
        helpMenu.add(reportBugItem);
        helpMenu.add(donateItem);
        helpMenu.add(DiscordItem);
        helpMenu.addSeparator();
        helpMenu.add(checkForUpdatesItem);
       
        
        JMenu language = LanguageMenuBar.LangMenuBar();
      //  setMenuFont(language, defaultFont); // Applies font to all submenus/items
       // setMenuFont(fileMenu, defaultFont); 
        
        //Theme Menu
	    //JMenu changeTheme = ThemesMenuBar.themesMenuBar();
	  //  setMenuFont(changeTheme, defaultFont);
        
        JMenu preference = PreferenceMenu.PreferenceMenuBar();
  	   // setMenuFont(preference, defaultFont);
        
	    JMenu CodeAnalysis = CodeAnalysisMenuBar.syntaxMenuBar();
	 //   setMenuFont(CodeAnalysis,defaultFont);
	    
	    JMenu Window = WindowMenu.createWindowMenu();
	   
        
       
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(searchMenu);
        menuBar.add(viewMenu);
        menuBar.add(language);
        menuBar.add(CodeAnalysis);
       // menuBar.add(changeTheme);
        menuBar.add(preference);
        menuBar.add(toolMenu);
        menuBar.add(Window);
        menuBar.add(helpMenu);
     
        return menuBar;
    }*/
	/*private static void setMenuFont(JMenu parentMenu, Font font) {
		
	    // Iterate through all components of the parent menu (submenus and items)
	    for (Component component : parentMenu.getMenuComponents()) {
	        if (component instanceof JMenu subMenu) {
	            // Apply font to the submenu (e.g., "A", "B", "C") and its children
	            subMenu.setFont(font);
	            setMenuFont(subMenu, font); // Recursively set fonts for nested submenus
	        } else if (component instanceof JMenuItem menuItem) {
	            // Apply font to menu items (e.g., "ActionScript", "Assembly x86")
	            menuItem.setFont(font);
	        }
	        // Ignore separators
	    }
	}
	*/
	
	private static Texteditor texteditor() {
	        return NotepadXXV1_2_1.texteditor();
	    }
	/***
	 * 
	 * @return
	 */
	
	public static  JMenuBar LinuxcreateMenuBar() {
	    JMenuBar menuBar = new JMenuBar();

	    // Create TriFunction interface for our needs
	    @FunctionalInterface
	    interface TriFunction<A,B,C,R> {
	        R apply(A a, B b, C c);
	    }

	    // Menu item creator function
	    TriFunction<String, String, ActionListener, JMenuItem> createMenuItem = (text, accelerator, action) -> {
	        JMenuItem item = new JMenuItem(text);
	        
	        if (!accelerator.isEmpty()) {
	            KeyStroke keyStroke = switch (accelerator) {
	                case "Ctrl++", "Ctrl+=" -> KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK);
	                case "Ctrl+-" -> KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK);
	                case "Ctrl+0" -> KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.CTRL_DOWN_MASK);
	                default -> KeyStroke.getKeyStroke(
	                    accelerator.replace("Ctrl+", "control ")
	                              .replace("Alt+", "alt ")
	                              .replace("Shift+", "shift ")
	                );
	            };
	            item.setAccelerator(keyStroke);
	        }

	        item.addActionListener(action);
	        return item;
	    };

	    // File Menu
	    JMenu fileMenu = new JMenu("File");
	    fileMenu.add(createMenuItem.apply("New", "Ctrl+N", e -> texteditor().newFile()));
	    fileMenu.add(createMenuItem.apply("Open File...", "Ctrl+O", e -> texteditor().openFile()));
	    fileMenu.add(createMenuItem.apply("Save", "Ctrl+S", e -> texteditor().saveFile()));
	    fileMenu.add(createMenuItem.apply("Save As...", "Ctrl+Alt+S", e -> texteditor().saveFileAs()));
	    fileMenu.add(createMenuItem.apply("Save a Copy As...", "", e -> texteditor().saveFileAs()));
	    fileMenu.add(createMenuItem.apply("Save All", "Ctrl+Shift+S", e -> texteditor().saveAll()));
	    fileMenu.add(createMenuItem.apply("Rename...", "", e -> texteditor().RenameFile()));
	    fileMenu.addSeparator();
	    fileMenu.add(createMenuItem.apply("Close Tab", "Ctrl+W", e -> CLOSE.closeTab(texteditor())));
	    fileMenu.add(createMenuItem.apply("Close All Tabs", "Ctrl+Shift+W", e -> CLOSE.closeAllTab()));
	    fileMenu.addSeparator();
	    fileMenu.add(createMenuItem.apply("Restore Recent Closed File", "Ctrl+Shift+T", e -> NotepadXXV1_2_1.openRecentClosedTab()));
	    fileMenu.add(createMenuItem.apply("Open All Recent Files", "", e -> NotepadXXV1_2_1.openAllRecentClosedTabs()));
	    fileMenu.add(createMenuItem.apply("Empty All Recent File List", "", e -> NotepadXXV1_2_1.clearAllRecentClosedTabs()));
	    fileMenu.addSeparator();
	    fileMenu.add(createMenuItem.apply("Exit", "Alt+F4", e -> ExitFrame.exit()));

	    // Edit Menu
	    JMenu editMenu = new JMenu("Edit");
	    editMenu.add(createMenuItem.apply("Undo", "Ctrl+Z", e -> texteditor().undoText()));
	    editMenu.add(createMenuItem.apply("Redo", "Ctrl+Y", e -> texteditor().redoText()));
	    editMenu.addSeparator();
	    editMenu.add(createMenuItem.apply("Cut", "Ctrl+X", e -> texteditor().cutText()));
	    editMenu.add(createMenuItem.apply("Copy", "Ctrl+C", e -> texteditor().copyText()));
	    editMenu.add(createMenuItem.apply("Paste", "Ctrl+V", e -> texteditor().pasteText()));
	    editMenu.addSeparator();
	    editMenu.add(createMenuItem.apply("Print", "Ctrl+P", e -> texteditor().printText()));

	    // Search Menu
	    JMenu searchMenu = new JMenu("Search");
	    searchMenu.add(createMenuItem.apply("Find", "Ctrl+F", e -> texteditor().findText()));
	    searchMenu.add(createMenuItem.apply("Replace", "Ctrl+H", e -> texteditor().replaceText()));

	    // View Menu
	    JMenu viewMenu = new JMenu("View");
	    viewMenu.add(createMenuItem.apply("File Explorer", "Ctrl+Shift+E", e -> texteditor().Explorer()));
	    viewMenu.addSeparator();
	    viewMenu.add(createMenuItem.apply("Terminal", "Ctrl+Shift+C", e -> texteditor().Cmd()));
	    viewMenu.add(createMenuItem.apply("Root Terminal", "Ctrl+Alt+A", e -> texteditor().adminCmd()));
	    viewMenu.addSeparator();
	    viewMenu.add(createMenuItem.apply("FireFox", "Ctrl+Alt+F", e -> texteditor().FireFox()));
	    viewMenu.add(createMenuItem.apply("GoogleChrome", "Ctrl+Alt+C", e -> texteditor().chromeBrowser()));
	    viewMenu.add(createMenuItem.apply("TorBrowser", "Ctrl+Alt+T", e -> texteditor().TorBrowser()));

	    // Tools Menu
	    JMenu toolMenu = new JMenu("Tools");
	    toolMenu.add(createMenuItem.apply("Choose Font...  ", "   ", e -> texteditor().chooseFont()));
	    toolMenu.addSeparator();
	    toolMenu.add(createMenuItem.apply("Zoom In", "Ctrl+=", e -> texteditor().setFontSize(+2)));
	    toolMenu.getItem(toolMenu.getItemCount() - 1).setAccelerator(
	        KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK)
	    );

	    toolMenu.add(createMenuItem.apply("Zoom Out", "Ctrl+-", e -> texteditor().setFontSize(-2)));
	    toolMenu.getItem(toolMenu.getItemCount() - 1).setAccelerator(
	        KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK)
	    );

	    toolMenu.addSeparator();
	    toolMenu.add(createMenuItem.apply("Reset Size", "Ctrl+0", e -> texteditor().resetFontSize()));

	    // Help Menu
	    JMenu helpMenu = new JMenu("Help");
	    helpMenu.add(createMenuItem.apply("About NotepadXX", "", e -> AboutMenu.showAboutDialog()));
	    helpMenu.addSeparator();
	    helpMenu.add(createMenuItem.apply("Report a Bug", "", e -> texteditor().reportBug()));
	    
	    // Special styled items
	    JMenuItem donateItem = new JMenuItem("<html>Support Us<font color='red'>&#x2764;</font></html>");
	    donateItem.addActionListener(e -> texteditor().Donate());
	    helpMenu.add(donateItem);
	    
	    JMenuItem discordItem = new JMenuItem("<html>Join Discord<font color='#7289DA'> \uD83D\uDEEB </font></html>");
	    discordItem.addActionListener(e -> texteditor().Discord());
	    helpMenu.add(discordItem);
	    helpMenu.addSeparator(); 
	    helpMenu.add(createMenuItem.apply("Check for Updates...", "", e -> NotepadXXV1_2_1.onCheckForUpdatesMenuSelected()));

	    // Add all menus to menu bar
	    menuBar.add(fileMenu);
	    menuBar.add(editMenu);
	    menuBar.add(searchMenu);
	    menuBar.add(viewMenu);
	    menuBar.add(LanguageMenuBar.LangMenuBar());
	       // menuBar.add(changeTheme);
	        menuBar.add(CodeAnalysisMenuBar.syntaxMenuBar());
	       // menuBar.add(ThemesMenuBar.themesMenuBar());
	        menuBar.add(PreferenceMenu.PreferenceMenuBar());
	        menuBar.add(toolMenu);
	        menuBar.add(WindowMenu.createWindowMenu());
	    menuBar.add(helpMenu);

	    return menuBar;
	}
	
}
