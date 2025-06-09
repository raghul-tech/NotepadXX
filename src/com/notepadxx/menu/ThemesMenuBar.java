package com.notepadxx.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import com.notepadxx.flatlaf.theme.Themes;

public class ThemesMenuBar {

    private static JMenu themesMenuBar;
    private static List<JMenuItem> menuItems = new ArrayList<>();
    
    public static JMenu themesMenuBar() {
        if (themesMenuBar == null) {
            themesMenuBar = createThemesMenuBar();
        }
        return themesMenuBar;
    }
	
	
	private static  JMenu createThemesMenuBar() {
		 //Theme Menu
	    JMenu changeTheme = new JMenu("Theme");
	    JMenuItem lightThemeItem = new JMenuItem("Light   ");
	    JMenuItem classicThemeItem = new JMenuItem("Classic  ");
	    JMenuItem macLightThemeItem = new JMenuItem("MacOS Light  ");
	    JMenuItem darkThemeItem = new JMenuItem("Dark  ");
	    JMenuItem darculaThemeItem = new JMenuItem("Darcula  ");
	    JMenuItem macDarkThemeItem = new JMenuItem("MacOS Dark  ");
	    
	    
	    // Add menu items to the list
        menuItems.add(lightThemeItem);
        menuItems.add(classicThemeItem);
        menuItems.add(macLightThemeItem);
        menuItems.add(darkThemeItem);
        menuItems.add(darculaThemeItem);
        menuItems.add(macDarkThemeItem);
	    
	    //Themes Menu
        lightThemeItem.addActionListener(e -> applyTheme("Light",lightThemeItem));
        classicThemeItem.addActionListener(e ->applyTheme("Classic",classicThemeItem));
        macLightThemeItem.addActionListener(e -> applyTheme("MacLight",macLightThemeItem));
        darkThemeItem.addActionListener(e ->applyTheme("Dark",darkThemeItem));
        darculaThemeItem.addActionListener(e -> applyTheme("Darcula",darculaThemeItem));
        macDarkThemeItem.addActionListener(e ->applyTheme("MacDark",macDarkThemeItem));
        
        //theme Menu
        changeTheme.add(lightThemeItem);
        changeTheme.add(darkThemeItem);
        changeTheme.addSeparator();
        changeTheme.add(classicThemeItem);
        changeTheme.add(darculaThemeItem);
        changeTheme.addSeparator();
        changeTheme.add(macLightThemeItem);
        changeTheme.add(macDarkThemeItem);
		
		return changeTheme;
	}
	
	  private static String getThemesName(String lang) {
	        Map <String,String> extensions = new HashMap<>();
	        extensions.put("Light","Light");
	        extensions.put( "Classic","Classic");
	        extensions.put("MacOS Light","MacLight");
	        extensions.put( "Dark","Dark");
	        extensions.put("Darcula","Darcula");
	        extensions.put("MacOS Dark" ,"MacDark");
	
	        return extensions.getOrDefault(lang,lang); 
	  }
	  
	  // Helper method to avoid code duplication
	    private static void processMenuItem(String theme, JMenuItem item) {
	        for (JMenuItem menuItem : menuItems) {
	            String itemText = menuItem.getText().replace("• ", "").trim();
	            String itemLang = getThemesName(itemText);

	            if (theme.equals(itemLang)) {
	                menuItem.setText("• " + itemText); // Add dot to selected theme
	            } else {
	                menuItem.setText(itemText); // Remove dot from non-selected themes
	            }

	            // Force UI refresh
	            SwingUtilities.invokeLater(() -> {
	                menuItem.revalidate();
	                menuItem.repaint();
	            });
	        }
	    }
	    public static void applyTheme(String theme,JMenuItem MenuTheme) {
	    	//if(!Themes.getTheme().equals(theme)) Themes.applyTheme(theme);
	    	Themes.applyTheme(theme);
	    	processMenuItem(theme,MenuTheme);
	    	
	    }
	    
	    
}
