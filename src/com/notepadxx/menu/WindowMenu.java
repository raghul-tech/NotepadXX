package com.notepadxx.menu;

import java.awt.Component;
import java.awt.Font;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import com.notepadxx.notepadxx.NotepadXXV1_2_1;
import com.notepadxx.notepadxx.Texteditor;
import com.notepadxx.resources.icon.GetImage;

public class WindowMenu {
    private static JMenu windowMenu;
   // private static NotepadXXV1_2_0 notepad = new NotepadXXV1_2_0();
    private static Font font = new Font("Monospaced", Font.PLAIN, 12);  
   

    protected static JMenu createWindowMenu() {
        if (windowMenu == null) {
            windowMenu = new JMenu("Window");
        }
        updateWindowMenu(); // Populate initially 
        return windowMenu;
    }

    public static void updateWindowMenu() {
    	 if (windowMenu == null) {
             windowMenu = new JMenu("Window");
         }
        windowMenu.removeAll(); // Clear previous entries
    	  
        JTabbedPane tabbedPane = NotepadXXV1_2_1.getTabbedPane();
        int selectedIndex = tabbedPane.getSelectedIndex();
        int tabCount = tabbedPane.getTabCount();

        // Show up to 15 tabs directly in the menu
        int maxVisibleTabs = 15;
        JMenu moreTabsMenu = new JMenu("More Tabs..."); // Submenu for overflow tabs
        if (isLinux()) {
        	 moreTabsMenu.setFont(font);
        }
        
        for (int i = 0; i < tabCount; i++) {
            String tabTitle = tabbedPane.getTitleAt(i);
            if (tabTitle == null || tabTitle.trim().isEmpty()) {
                tabTitle = "new " + (i + 1);
            }

         // Get icon from tab or fallback
            Icon tabIcon = tabbedPane.getIconAt(i);
            if (tabIcon == null) {
                // Try to get icon based on file extension
                Component tabComponent = tabbedPane.getComponentAt(i);
                if (tabComponent instanceof Texteditor) {
                    File currentFile = ((Texteditor) tabComponent).getCurrentFile();
                    if (currentFile != null) {
                        tabIcon = GetImage.getImage(currentFile);
                    }
                }
            }
            
            
            String displayTitle = formatMenuItemText(i, selectedIndex, tabTitle);
            JMenuItem tabItem = new JMenuItem(displayTitle, tabIcon);
            // Add checkmark to active tab      
          //  String displayTitle = (i == selectedIndex) ? "<html><b><font color='green'>✓</font></b> " + (i + 1) + ". " + tabTitle : (i + 1) + ". " + tabTitle;

            //JMenuItem tabItem = new JMenuItem(displayTitle);
            if (isLinux()) {
            	  tabItem.setFont(font);
            }
            final int index = i;
            tabItem.addActionListener(e -> tabbedPane.setSelectedIndex(index));

            // If more than 15 tabs, add to "More Tabs..." submenu
            if (i < maxVisibleTabs) {
            	if (windowMenu == null) {
                    windowMenu = new JMenu("Window");
                }
                windowMenu.add(tabItem);
            } else {
                moreTabsMenu.add(tabItem);
            }
        }

        // Only add "More Tabs..." if there are more than 15 tabs
        if (tabCount > maxVisibleTabs) {
            windowMenu.add(moreTabsMenu);
        }
        
        
    }
    
    private static String formatMenuItemText(int index, int selectedIndex, String title) {
        // Add checkmark to active tab
        if (index == selectedIndex) {
            return "<html><b><font color='green'>✓</font></b> " + (index + 1) + ". " + title;
        }
        return (index + 1) + ". " + title;
    }
    
    
 // Method to check if the OS is Linux
    private static boolean isLinux() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("nix") || os.contains("nux") || os.contains("aix");
    }
}
