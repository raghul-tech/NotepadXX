package com.notepadxx.toolbar;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import com.notepadxx.exit.CLOSE;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;

public class Toolbar {
	
     /*
     * Tool Bar
     */
	  
	public static  JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		 String os = System.getProperty("os.name").toLowerCase();
	     if (os.contains("win")) {
	        toolBar =  WindowscreateToolBar();
	     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	        toolBar =  LinuxcreateToolBar();
	     } else {
	         JOptionPane.showMessageDialog(
	             null,
	             "Unsupported operating system.",
	             "Error", 
	             JOptionPane.ERROR_MESSAGE
	         );
	     }
	     return toolBar; 
	}
	
	 protected static JToolBar WindowscreateToolBar() {
	        JToolBar toolBar = new JToolBar();

	        // Create buttons with icons
	        JButton newFileButton = createToolbarButton("/icons/new.png", e -> texteditor().newFile(),"new (Ctrl+N)");
	        JButton openFileButton = createToolbarButton("/icons/open.png", e -> texteditor().openFile(),"open (Ctrl+O)");
	        JButton saveFileButton = createToolbarButton("/icons/save3.png", e -> texteditor().saveFile(),"save (Ctrl+S)");
	        JButton saveAllFileButton = createToolbarButton("/icons/saveall3.png", e -> texteditor().saveAll(),"Save All (Ctrl+Shift+S)");
	        JButton closeButton = createToolbarButton("/icons/file (1).png", e -> CLOSE.closeTab(texteditor()),"Close (Ctrl+W)");
	        JButton closeAllButton = createToolbarButton("/icons/file (2).png", e -> CLOSE.closeAllTab(),"CloseAll (Ctrl+Shift+W)");
	        JButton printButton = createToolbarButton("/icons/print.png", e -> texteditor().printText(),"print (Ctrl+P)");
	        JButton cutButton = createToolbarButton("/icons/cut (1).png", e -> texteditor().cutText(),"cut (Ctrl+X)");
	        JButton copyButton = createToolbarButton("/icons/files.png", e -> texteditor().copyText(),"copy (Ctrl+C)");
	        JButton pasteButton = createToolbarButton("/icons/paste.png", e -> texteditor().pasteText(),"paste (Ctrl+V)");
	        JButton findButton = createToolbarButton("/icons/find.png", e -> texteditor().findText(),"find (Ctrl+F)");
	        JButton replaceButton = createToolbarButton("/icons/alter.png", e -> texteditor().replaceText(),"replace (Ctrl+H)");
	        JButton undoButton = createToolbarButton("/icons/undo.png", e -> texteditor().undoText(),"Undo (Ctrl+Z)");
	        JButton redoButton = createToolbarButton("/icons/redo.png", e -> texteditor().redoText(),"redo (Ctrl+Y)");
	        JButton cmdButton = createToolbarButton("/icons/command (3).png", e -> texteditor().Cmd(),"cmd (Ctrl+Shift+C)");
	        JButton adminCmdButton = createToolbarButton("/icons/command (2).png", e -> texteditor().adminCmd(),"cmd (Admin) (Ctrl+Alt+A)");
	        JButton explorerButton = createToolbarButton("/icons/folder.png", e -> texteditor().Explorer(),"File Explorer (Ctrl+Shift+E)");
	        JButton chooseButton = createToolbarButton("/icons/choose.png", e -> texteditor().chooseFont(),"Choose... ");
	        JButton increaseButton = createToolbarButton("/icons/increase.png", e -> texteditor().setFontSize(+2),"Zoom In (Ctrl + =)");
	        JButton decreaseButton = createToolbarButton("/icons/decrease.png", e -> texteditor().setFontSize(-2),"Zoom Out (Ctrl + -)");
	        JButton resetButton = createToolbarButton("/icons/reset.png", e -> texteditor().resetFontSize(),"Reset Size (Ctrl + 0)");
	        //JButton lightButton = createToolbarButton("/icons/light.png", e -> notepad.applyTheme("Light"),"Light Mode");
	        //JButton darkButton = createToolbarButton("/icons/dark.png", e -> notepad.applyTheme("Dark"),"Dark Mode");
	        JButton reportButton = createToolbarButton("/icons/bug.png", e -> texteditor().reportBug(),"Report a bug");
	        JButton updateButton = createToolbarButton("/icons/update.png", e ->NotepadXXV1_2_0.onCheckForUpdatesMenuSelected(),"Check for Updates...");
	        JButton edgeButton = createToolbarButton("/icons/edge.png", e -> texteditor().edgeBrowser(),"Open Edge (Ctrl+Alt+E)");
	        JButton chromeButton = createToolbarButton("/icons/chrome.png", e -> texteditor().chromeBrowser(),"Open Chrome (Ctrl+Alt+C)");
	        JButton FireFoxButton = createToolbarButton("/icons/firefox (2).png", e -> texteditor().FireFox(),"Open FireFox (Ctrl+Alt+F)");
	     //   JButton donateButton = createToolbarButton("/icons/donation.png", e -> texteditor().Donate(),"<html>Support Us <font color='red'>&#x2764;</font></html>");

	        // Add buttons to the toolbar

	        toolBar.add(newFileButton);
	        toolBar.add(openFileButton);
	        toolBar.add(saveFileButton);
	        toolBar.add(saveAllFileButton);
	        toolBar.add(closeButton);
	        toolBar.add(closeAllButton);
	        toolBar.add(printButton);
	        toolBar.addSeparator(); // Adds a separator for visual clarity
	        toolBar.add(cutButton);
	        toolBar.add(copyButton);
	        toolBar.add(pasteButton);
	        toolBar.addSeparator();
	        toolBar.add(undoButton);
	        toolBar.add(redoButton);
	        toolBar.addSeparator();
	        toolBar.add(findButton);
	        toolBar.add(replaceButton);
	        toolBar.addSeparator();
	        toolBar.add(cmdButton);
	        toolBar.add(adminCmdButton);
	        toolBar.add(explorerButton);
	        toolBar.addSeparator();
	        toolBar.add(edgeButton);
	        toolBar.add(chromeButton);
	        toolBar.add(FireFoxButton);
	        toolBar.addSeparator();
	        toolBar.add(chooseButton);
	        toolBar.add(increaseButton);
	        toolBar.add(decreaseButton);
	        toolBar.add(resetButton);
	        toolBar.addSeparator();
	      //  toolBar.add(lightButton);
	       // toolBar.add(darkButton);
	       // toolBar.addSeparator();
	        toolBar.add(reportButton);
	    //    toolBar.add(donateButton);
	        toolBar.add(updateButton);

	        return toolBar;
	    }

	
	
	
	private static JToolBar LinuxcreateToolBar() {
        JToolBar toolBar = new JToolBar();

        // Create buttons with icons   
        JButton newFileButton = createToolbarButton("/icons/new.png", e -> texteditor().newFile(),"new (Ctrl+N)");
        JButton openFileButton = createToolbarButton("/icons/open.png", e -> texteditor().openFile(),"open (Ctrl+O)");
        JButton  saveFileButton = createToolbarButton("/icons/save3.png", e -> texteditor().saveFile(),"save (Ctrl+S)");
        JButton saveAllFileButton = createToolbarButton("/icons/saveall3.png", e -> texteditor().saveAll(),"Save All (Ctrl+Shift+S)");
        JButton closeButton = createToolbarButton("/icons/file (1).png", e -> CLOSE.closeTab(texteditor()),"Close (Ctrl+W)");
        JButton closeAllButton = createToolbarButton("/icons/file (2).png", e -> CLOSE.closeAllTab(),"CloseAll (Ctrl+Shift+W)");
        JButton printButton = createToolbarButton("/icons/print.png", e -> texteditor().printText(),"print (Ctrl+P)");
        JButton cutButton = createToolbarButton("/icons/cut (1).png", e -> texteditor().cutText(),"cut (Ctrl+X)");
        JButton copyButton = createToolbarButton("/icons/files.png", e -> texteditor().copyText(),"copy (Ctrl+C)");
        JButton pasteButton = createToolbarButton("/icons/paste.png", e -> texteditor().pasteText(),"paste (Ctrl+V)");
        JButton findButton = createToolbarButton("/icons/find.png", e -> texteditor().findText(),"find (Ctrl+F)");
        JButton replaceButton = createToolbarButton("/icons/alter.png", e -> texteditor().replaceText(),"replace (Ctrl+H)");
        JButton undoButton = createToolbarButton("/icons/undo.png", e -> texteditor().undoText(),"Undo (Ctrl+Z)");
        JButton redoButton = createToolbarButton("/icons/redo.png", e -> texteditor().redoText(),"redo (Ctrl+Y)");
        JButton cmdButton = createToolbarButton("/icons/command (3).png", e -> texteditor().Cmd(),"Terminal (Ctrl+Shift+C)");
        JButton adminCmdButton = createToolbarButton("/icons/terminal (2).png", e -> texteditor().adminCmd(),"Root Terminal (Ctrl+Alt+A)");
        JButton explorerButton = createToolbarButton("/icons/folder.png", e -> texteditor().Explorer(),"File Explorer (Ctrl+Shift+E)");
        JButton chooseButton = createToolbarButton("/icons/choose.png", e -> texteditor().chooseFont(),"Choose... ");
        JButton increaseButton = createToolbarButton("/icons/increase.png", e -> texteditor().setFontSize(+2),"Zoom In (Ctrl + =)");
        JButton decreaseButton = createToolbarButton("/icons/decrease.png", e -> texteditor().setFontSize(-2),"Zoom Out (Ctrl + -)");
        JButton resetButton = createToolbarButton("/icons/reset.png", e -> texteditor().resetFontSize(),"Reset Size (Ctrl + 0)");
        //JButton lightButton = createToolbarButton("/icons/light.png", e -> notepad.applyTheme("Light"),"Light Mode");
        //JButton darkButton = createToolbarButton("/icons/dark.png", e -> notepad.applyTheme("Dark"),"Dark Mode");
        JButton reportButton = createToolbarButton("/icons/bug.png", e -> texteditor().reportBug(),"Report a bug");
        JButton updateButton = createToolbarButton("/icons/update.png", e -> NotepadXXV1_2_0.onCheckForUpdatesMenuSelected(),"Check for Updates...");
        JButton edgeButton = createToolbarButton("/icons/firefox (2).png", e -> texteditor().FireFox(),"Open FireFox (Ctrl+Alt+F)");
        JButton chromeButton = createToolbarButton("/icons/chrome.png", e -> texteditor().chromeBrowser(),"Open Chrome (Ctrl+Alt+C)");
        JButton torButton = createToolbarButton("/icons/tor.png", e -> texteditor().TorBrowser(),"Open Tor (Ctrl+Alt+T)");
      //  JButton donateButton = createToolbarButton("/icons/donation.png", e -> texteditor().Donate(),"<html>Donate now <font color='red'>&#x2764;</font></html>");
        
        // Add buttons to the toolbar
       
        toolBar.add(newFileButton);
        toolBar.add(openFileButton);
        toolBar.add(saveFileButton);
        toolBar.add(saveAllFileButton);
        toolBar.add(closeButton);
        toolBar.add(closeAllButton);        
        toolBar.add(printButton);
        toolBar.addSeparator(); // Adds a separator for visual clarity
        toolBar.add(cutButton);
        toolBar.add(copyButton);
        toolBar.add(pasteButton);
        toolBar.addSeparator();
        toolBar.add(undoButton);
        toolBar.add(redoButton);
        toolBar.addSeparator();
        toolBar.add(findButton);
        toolBar.add(replaceButton);
        toolBar.addSeparator();
        toolBar.add(cmdButton);
        toolBar.add(adminCmdButton);
        toolBar.add(explorerButton);
        toolBar.addSeparator();
        toolBar.add(edgeButton);
        toolBar.add(chromeButton);
        toolBar.add(torButton);
        toolBar.addSeparator();
        toolBar.add(chooseButton);
        toolBar.add(increaseButton);
        toolBar.add(decreaseButton);
        toolBar.add(resetButton);
        toolBar.addSeparator();
      //  toolBar.add(lightButton);
       // toolBar.add(darkButton);
       // toolBar.addSeparator();
        toolBar.add(reportButton);
      //  toolBar.add(donateButton);
        toolBar.add(updateButton);
       
        return toolBar;
    }

	

    private  static JButton createToolbarButton(String iconPath, ActionListener action, String tooltip) {
        JButton button = new JButton(new ImageIcon(NotepadXXV1_2_0.class.getResource(iconPath)));
        button.addActionListener(action);
        button.setToolTipText(tooltip); // Set the tooltip text
        button.setFocusable(false); // Make sure the button is not focusable
    	  return button;
    }

	private static Texteditor texteditor() {
        return NotepadXXV1_2_0.texteditor();
    }
	}
