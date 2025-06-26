package com.notepadxx.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.notepadxx.exit.CLOSE;
import com.notepadxx.exit.ExitFrame;
import com.notepadxx.notepadxx.NotepadXXV1_2_1;
import com.notepadxx.notepadxx.Texteditor;


public class WindowsMenuBar {
	 
	
	
	public static JMenuBar WindowscreateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        /** 
         * Menu Bar Items
         */

        // 'File' menu
        JMenu fileMenu = new JMenu("File");
	    JMenuItem newFile = new JMenuItem(String.format("%-67s %s", "New ", "Ctrl+N"));
	    JMenuItem openFile = new JMenuItem(String.format("%-64s %s", "Open File...", "Ctrl+O"));
	    JMenuItem saveFile = new JMenuItem(String.format("%-68s %s", "Save", "Ctrl+S"));
	    JMenuItem saveAsFile = new JMenuItem(String.format("%-58s %s", "Save As...", "Ctrl+Alt+S"));
	    JMenuItem saveCopy = new JMenuItem(String.format("%-10s %s", "Save a Copy As...", ""));
	    JMenuItem saveAllFile = new JMenuItem(String.format("%-56s %s", "Save All", "Ctrl+Shift+S"));
	    JMenuItem RenameFile = new JMenuItem(String.format("%-56s %s", "Rename... ", ""));
	    JMenuItem closeTab = new JMenuItem(String.format("%-63s %s", "Close Tab", "Ctrl+W"));
	    JMenuItem closeAllTab = new JMenuItem(String.format("%-50s %s", "Close All Tabs", "Ctrl+Shift+W"));
	    JMenuItem restoreRecentFile  = new JMenuItem(String.format("%-41s %s", "Restore Recent Closed File", "Ctrl+Shift+T"));
	    JMenuItem openAllRecentFiles = new JMenuItem(String.format("%-30s %s", "Open All Recent Files", ""));
	    JMenuItem emptyAllRecentFiles = new JMenuItem(String.format("%-28s %s", "Empty All Recent File List ", ""));
	    JMenuItem ExitTab = new JMenuItem(String.format("%-69s %s", "Exit", "Alt+F4"));

	    // 'Edit' menu
        JMenu editMenu = new JMenu("Edit");
	    JMenuItem undoText = new JMenuItem(String.format("%-24s %s", "Undo", "Ctrl+Z"));
	    JMenuItem redoText = new JMenuItem(String.format("%-24s %s", "Redo", "Ctrl+Y"));
	    JMenuItem cutText = new JMenuItem(String.format("%-26s %s", "Cut", "Ctrl+X"));
	    JMenuItem copyText = new JMenuItem(String.format("%-24s %s", "Copy", "Ctrl+C"));
	    JMenuItem pasteText = new JMenuItem(String.format("%-25s %s", "Paste", "Ctrl+V"));
	    JMenuItem printText = new JMenuItem(String.format("%-26s %s", "Print", "Ctrl+P"));

	    //search menu
	    JMenu searchMenu = new JMenu("Search");
	    JMenuItem findText = new JMenuItem(String.format("%-26s %s", "Find", "Ctrl+F"));
	    JMenuItem replaceText = new JMenuItem(String.format("%-23s %s", "Replace", "Ctrl+H"));


	    // 'View' menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem openExplorer = new JMenuItem("Explorer             Ctrl+Shift+E ");
	    JMenuItem openCmd = new JMenuItem("cmd                   Ctrl+Shift+C");
	    JMenuItem adminCmd = new JMenuItem("cmd (Admin)      Ctrl+Alt+A");
	    JMenuItem edge = new JMenuItem(String.format("%-24s %s", "Edge", "Ctrl+Alt+E"));
	    JMenuItem chrome = new JMenuItem(String.format("%-20s %s", "Chrome", "Ctrl+Alt+C"));
	    JMenuItem firefox = new JMenuItem(String.format("%-24s %s", "FireFox ", "Ctrl+Alt+F"));

	   

        // Tool menu
        JMenu toolMenu = new JMenu("Tools");
        JMenuItem chooseFont = new JMenuItem("Choose...       ");
        JMenuItem largerFont = new JMenuItem("Zoom In           Ctrl + =");
	    JMenuItem smallerFont = new JMenuItem("Zoom out         Ctrl + -");
	    JMenuItem resetFont = new JMenuItem("Reset Size        Ctrl + 0"); 
	    
	    
        //Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About NotepadXX");
        JMenuItem reportBugItem = new JMenuItem("Report a Bug");
        JMenuItem donateItem = new JMenuItem("<html>Support Us <font color='red'> &#x2764;</font></html>");
        JMenuItem DiscordItem = new JMenuItem("<html>Join Discord<font color='yellow'> \uD83D\uDC4D </font></html>");
        JMenuItem checkForUpdatesItem = new JMenuItem("Check for Updates...");


        /**
         * Action listener for MenuBar item
         */

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
        restoreRecentFile.addActionListener(e -> NotepadXXV1_2_1.openRecentClosedTab());
        openAllRecentFiles.addActionListener(e -> NotepadXXV1_2_1.openAllRecentClosedTabs());
        emptyAllRecentFiles.addActionListener(e -> NotepadXXV1_2_1.clearAllRecentClosedTabs());
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
        edge.addActionListener(e -> texteditor().edgeBrowser());
        chrome.addActionListener(e -> texteditor().chromeBrowser());
        firefox.addActionListener(e -> texteditor().FireFox());

       

        // Tools menu
        chooseFont.addActionListener(e -> texteditor().chooseFont());
        largerFont.addActionListener(e -> texteditor().setFontSize(+2));
        smallerFont.addActionListener(e -> texteditor().setFontSize(-2));
        resetFont.addActionListener(e -> texteditor().resetFontSize());

        //Help Menu
        checkForUpdatesItem.addActionListener(e -> NotepadXXV1_2_1.onCheckForUpdatesMenuSelected() );//.checkForUpdates());
        aboutItem.addActionListener(e -> AboutMenu.showAboutDialog());
        reportBugItem.addActionListener(e -> texteditor().reportBug());
       DiscordItem.addActionListener(e -> texteditor().Discord());
       donateItem.addActionListener(e -> texteditor().Donate());

        /**
         * Adding Menu Bar
         */

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

        // viewMenu.addSeparator();
         viewMenu.add(openExplorer);
         viewMenu.addSeparator();
         viewMenu.add(openCmd);
         viewMenu.add(adminCmd);
         viewMenu.addSeparator();
         viewMenu.add(edge);
         viewMenu.add(chrome);
         viewMenu.add(firefox);
         


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

        /**
         * creating menu bar
         */
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(searchMenu);
        menuBar.add(viewMenu);
       // menuBar.add(Language);
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
	private static Texteditor texteditor() {
        return NotepadXXV1_2_1.texteditor();
    }

}
