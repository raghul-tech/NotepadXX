package com.notepadxx.menu;

import java.awt.Component;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import com.notepadxx.notepadxx.ConfigFiles;
import com.notepadxx.notepadxx.NotepadXXV1_2_1;
import com.notepadxx.notepadxx.Texteditor;

public class LanguageMenuBar {

    private static JMenu selectedCategory = null;
    private static JMenuItem selectedLanguage = null;
    private static final String CONFIG_FILE = ConfigFiles.getConfigLanguage();
   // private static NotepadXXV1_2_0 notepad = new NotepadXXV1_2_0();
    // Store the LangMenuBar instance 
    private static JMenu langMenuBar;

    public static JMenu LangMenuBar() { 
        if (langMenuBar == null) { 
            langMenuBar = createLangMenuBar(); 
        }
        return langMenuBar;  
    }
    private static JMenu createLangMenuBar() {
        // Define a map with categories as keys and lists of languages as values
        Map<String, List<String>> languageMap = new LinkedHashMap<>();
        languageMap.put("A", Arrays.asList("ActionScript", "Assembly x86", "Assembly 6502"));
        languageMap.put("B", Arrays.asList("BBCode", "Batch File", "Bash"));
        languageMap.put("C", Arrays.asList("C", "C++", "C#", "CSS", "Clojure", "CSV"));
        languageMap.put("D", Arrays.asList("D", "Dart", "Delphi", "Dockerfile", "DTD"));
        languageMap.put("F", Collections.singletonList("Fortran"));
        languageMap.put("G", Arrays.asList("Groovy", "Go"));
        languageMap.put("H", Arrays.asList("HTML", "Handlebars", "Hosts", "Htaccess"));
        languageMap.put("I", Arrays.asList("IML file", "INI file", "ipynb"));
        languageMap.put("J", Arrays.asList("Java", "JavaScript", "JSP", "JSON"));
        languageMap.put("K", Collections.singletonList("Kotlin"));
        languageMap.put("L", Arrays.asList("Lua", "Less", "LISP", "LaTex"));
        languageMap.put("M", Arrays.asList("Markdown", "Makefile", "MXML"));
        languageMap.put("N", Collections.singletonList("NSIS"));
        languageMap.put("P", Arrays.asList("Python", "PHP", "Perl", "Proto", "Properties file"));
        languageMap.put("R", Arrays.asList("Ruby", "Rust"));
        languageMap.put("S", Arrays.asList("SQL", "Scala", "SAS"));
        languageMap.put("T", Arrays.asList("TCL", "TypeScript"));
        languageMap.put("V", Collections.singletonList("Visual Basic"));
        languageMap.put("X", Collections.singletonList("XML"));
        languageMap.put("Y", Collections.singletonList("YAML"));

        // Main Language Menu
        JMenu languageMenu = new JMenu("Language");
        JMenuItem textItem = new JMenuItem("None (Normal Text)");
        JMenuItem resetItem = new JMenuItem("Reset Language");

        // Set action listeners
        textItem.addActionListener(e -> setlanguage("txt", null, textItem));
        resetItem.addActionListener(e -> resetLanguage());

        languageMenu.add(textItem);
        languageMenu.addSeparator();

        // Iterate through the map to dynamically create submenus and items
        for (Map.Entry<String, List<String>> entry : languageMap.entrySet()) {
            String category = entry.getKey();
            List<String> languages = entry.getValue();

            JMenu categoryMenu = new JMenu(category);

            for (String lang : languages) {
                JMenuItem langItem = new JMenuItem(lang);
                String langCode = getLanguageCode(lang); // Get language extension
             //   List<String> langCodes=  getLanguageCode(lang);
               // String langCode = langCodes.isEmpty() ? "" : langCodes.get(0); // Safely get first code
                langItem.addActionListener(e -> setlanguage(langCode, categoryMenu, langItem));
                categoryMenu.add(langItem);
            }

            languageMenu.add(categoryMenu);
        }

        languageMenu.addSeparator();
        languageMenu.add(resetItem);

        return languageMenu;
    }

    // Helper method to get file extensions based on language names
    private static String getLanguageCode(String lang) {
        Map<String, String> extensions = new HashMap<>();
        extensions.put("ActionScript", "as");
        extensions.put("Assembly x86", "asm");
        extensions.put("Assembly 6502", "a65");
        extensions.put("BBCode", "bbcode");
        extensions.put("Batch File", "cmd");
        extensions.put("Bash", "sh");
        extensions.put("C", "c");
        extensions.put("C++", "cpp");
        extensions.put("C#", "cs");
        extensions.put("CSS", "css");
        extensions.put("Clojure", "clj");
        extensions.put("CSV", "csv");
        extensions.put("D", "d");
        extensions.put("Dart", "dart");
        extensions.put("Delphi", "dpr");
        extensions.put("Dockerfile", "dockerfile");
        extensions.put("DTD", "dtd");
        extensions.put("Fortran", "f");
        extensions.put("Groovy", "groovy");
        extensions.put("Go", "go");
        extensions.put("HTML", "html");
        extensions.put("Handlebars", "hbs");
        extensions.put("Hosts", "hosts");
        extensions.put("Htaccess", "htaccess");
        extensions.put("IML file", "iml");
        extensions.put("INI file", "ini");
        extensions.put("ipynb", "ipynb");
        extensions.put("Java", "java");
        extensions.put("JavaScript", "js");
        extensions.put("JSP", "jsp");
        extensions.put("JSON", "json");
        extensions.put("Kotlin", "kt");
        extensions.put("Lua", "lua");
        extensions.put("Less", "less");
        extensions.put("LISP", "lisp");
        extensions.put("LaTex", "tex");
        extensions.put("Markdown", "md");
        extensions.put("Makefile", "Makefile");
        extensions.put("MXML", "mxml");
        extensions.put("NSIS", "nsi");
        extensions.put("Python", "py");
        extensions.put("PHP", "php");
        extensions.put("Perl", "pl");
        extensions.put("Proto", "proto");
        extensions.put("Properties file", "properties");
        extensions.put("Ruby", "rb");
        extensions.put("Rust", "rs");
        extensions.put("SQL", "sql");
        extensions.put("Scala", "scala");
        extensions.put("SAS", "sas");
        extensions.put("TCL", "tcl");
        extensions.put("TypeScript", "ts");
        extensions.put("Visual Basic", "vb");
        extensions.put("XML", "xml");
        extensions.put("YAML", "yaml");
        extensions.put("None (Normal Text)","txt");

        return extensions.getOrDefault(lang, "");
    }
    
    private static void resetLanguage() {
        texteditor().resetLanguage();
        clearMenuDots(langMenuBar); // Clear all dots from the menu
        selectedLanguage = null;
        selectedCategory = null;
    }

    private static void applyMenuSelection(JMenu langMenuBar, String currentLang) {
        currentLang = (currentLang != null) ? currentLang.trim() : "";
        clearMenuDots(langMenuBar); // Clear existing dots first

        if (currentLang.isEmpty()) return;

        // Iterate through the menu's components (now using getMenuComponents)
        for (Component component : langMenuBar.getMenuComponents()) {
            if (component instanceof JMenu categoryMenu) {
                // Check submenu items of the category
                for (Component subComponent : categoryMenu.getMenuComponents()) {
                    if (subComponent instanceof JMenuItem item) {
                        processMenuItem(item, currentLang, categoryMenu);
                    }
                }
            } else if (component instanceof JMenuItem item) {
                processMenuItem(item, currentLang, null);
            }
        }
    }

    // Helper method to avoid code duplication
    private static void processMenuItem(JMenuItem item, String targetLang, JMenu category) {
        String itemText = item.getText().replace("• ", "").trim();
        String itemLang = getLanguageCode(itemText);
       // List<String> langCodes = getLanguageCode(itemText);
       // String itemLang = langCodes.isEmpty() ? "" : langCodes.get(0); // Safely get first code
   /*     System.out.println("[DEBUG] Checking item: " + itemText 
            + " | Ext: " + itemLang 
            + " | Target: " + targetLang);*/

        if (targetLang.equals(itemLang)) {
         //   System.out.println("[MATCH] Found: " + itemText);
            item.setText("• " + itemText); // Add dot to language
            selectedLanguage = item;

            if (category != null) {
                // Add dot to category
                String categoryText = category.getText().replace("• ", "").trim();
                category.setText("• " + categoryText);
                selectedCategory = category;
            }

            // Force UI refresh
            SwingUtilities.invokeLater(() -> {
                item.revalidate();
                item.repaint();
                if (category != null) {
                    category.revalidate();
                    category.repaint();
                }
            });
        }
    }
    
    private static void clearMenuDots(JMenu langMenuBar) {
        for (Component component : langMenuBar.getMenuComponents()) {
            if (component instanceof JMenu categoryMenu) {
                // Clear dot from the category itself
                String categoryText = categoryMenu.getText().replace("• ", "");
                categoryMenu.setText(categoryText);

                // Clear dots from sub-items
                for (Component subComponent : categoryMenu.getMenuComponents()) {
                    if (subComponent instanceof JMenuItem item) {
                        item.setText(item.getText().replace("• ", ""));
                    }
                }
            } else if (component instanceof JMenuItem item) {
                item.setText(item.getText().replace("• ", ""));
            }
        }
    }
    
    private static void updateMenuLanuageActiveTab(JMenu langMenuBar) {
        SwingUtilities.invokeLater(() -> {
            int selectedIndex = NotepadXXV1_2_1.getTabbedPane().getSelectedIndex();
            if (selectedIndex == -1) {
           //     System.out.println("No tab selected. Resetting language."); // Debug print
                resetLanguage();
                return; // No open tab, clear dot
            }

          //  String tabName = texteditor().getTabTitle().replace(" *", ""); // Normalize name
           // System.out.println("Current tab: " + tabName); // Debug print

            String tabName;
            try {
            if(texteditor().getCurrentFile()!=null) {
             tabName = texteditor().getCurrentFile().toString();
            }else if (texteditor().getTabTitle() != null){
            	tabName = texteditor().getTabTitle().replace(" *", "");
            }else {
            	 resetLanguage();
            	return;
            }
            } catch (Exception e) {
              //  resetLanguage();
                return;
            }
            
            String currentLang = loadLanguageFromFile(tabName); // Fetch saved language
        //    System.out.println("Language for current tab: " + currentLang); // Debug print

            if (langMenuBar == null) {
           //     System.out.println("MenuBar is null!"); // Debug print
                return;
            }

            if(currentLang == null || currentLang.equals("")|| currentLang.isEmpty()) {
            	currentLang = "txt";
            }
            
            // Apply the language selection
            applyMenuSelection(langMenuBar, currentLang);

            // Ensure the text editor updates as well
          //  System.out.println("Setting language in text editor: " + currentLang); // Debug print
          //  texteditor().setlanguage(currentLang);
        });
    }

    public static void updateLanguageMenuForActiveTab() {
        if (langMenuBar == null) {
            langMenuBar = LangMenuBar(); // Ensure langMenuBar is initialized
        }
        updateMenuLanuageActiveTab(langMenuBar);
    }
  
    private static void setlanguage(String ext, JMenu category, JMenuItem language) {
        int selectedIndex = NotepadXXV1_2_1.getTabbedPane().getSelectedIndex();
        if (selectedIndex == -1) return; // No open tab

//       String tabName = texteditor().getTabTitle().replace(" *", "");
        String tabName;
        
        if(texteditor().getCurrentFile()!=null) {
         tabName = texteditor().getCurrentFile().toString();
        }else {
        	tabName = texteditor().getTabTitle().replace(" *", "");
        }
        
        // Save the new language setting
        saveLanguageToFile(tabName, ext);
        texteditor().setlanguage(ext);

        // Remove previous dot
        if (selectedLanguage != null) selectedLanguage.setText(selectedLanguage.getText().replace("• ", ""));
        if (selectedCategory != null) selectedCategory.setText(selectedCategory.getText().replace("• ", ""));

        // Apply new selection
        if (language != null) language.setText("• " + language.getText());
        if (category != null) category.setText("• " + category.getText());

        // Store selection
        selectedLanguage = language;
        selectedCategory = category;
     /*   SwingUtilities.invokeLater(() -> {
            SwingUtilities.updateComponentTreeUI(langMenuBar); // Update the actual menu bar
        });*/
    }

    public static void clearConfigLanguageFile() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(""); // Clear file
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static String loadLanguageFromFile(String tabName) {
    	if(tabName == null) return"";
    
        Properties properties = new Properties();
        File file = new File(CONFIG_FILE);

        if (!file.exists()) return ""; // No file, return empty

        try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
            return properties.getProperty(tabName, ""); // Get language for tab, default empty
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String getLanguageForFile(String tabname) {
    	return loadLanguageFromFile(tabname);
    }
    
    private static void saveLanguageToFile(String tabName, String ext) {
        Properties properties = new Properties();
        File file = new File(CONFIG_FILE);

        // Load existing properties if file exists
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                properties.load(reader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Update the language for the tab
        properties.setProperty(tabName, ext);

        // Save back to file
        try (FileWriter writer = new FileWriter(file)) {
            properties.store(writer, "Language settings for each tab");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setLanguageForFile(String tabname, String ext) {
    	saveLanguageToFile(tabname,ext);
    }
    
    
    public static void removeLanguageFromFile(String tabName) {
        Properties properties = new Properties();
        File file = new File(CONFIG_FILE);

        // Load existing properties if the file exists
        if (!file.exists()) {
           // System.out.println("Configuration file not found.");
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Remove the entry if it exists
        if (properties.remove(tabName) != null) { 
            try (FileWriter writer = new FileWriter(file)) {
                properties.store(writer, "Updated language settings for each tab");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
         //   System.out.println("Tab name not found: " + tabName);
        }
    }

 // Method to check if the language entry exists
    protected static boolean doesLanguageEntryExist(String tabName) {
        Properties properties = new Properties();
        File file = new File(CONFIG_FILE);

        if (!file.exists() || tabName == null) {
            return false;
        }

        try (FileReader reader = new FileReader(file)) { 
            properties.load(reader);
            return properties.containsKey(tabName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    private static Texteditor texteditor() {
        return NotepadXXV1_2_1.texteditor();
    }

    
}