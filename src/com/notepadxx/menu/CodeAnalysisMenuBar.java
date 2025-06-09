package com.notepadxx.menu;

import java.awt.Component;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import com.notepadxx.notepadxx.ConfigFiles;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;

public class CodeAnalysisMenuBar {

    private static final Map<String, String> SUPPORTED_EXTENSIONS;
    private static JMenuItem selectedCode = null;
    private static JMenu syntaxMenuBar;
    private static String CONFIG_FILE = ConfigFiles.getConfigCodeAnalysis();
   // private  static NotepadXXV1_2_0 notepad = new NotepadXXV1_2_0();

    static {
        SUPPORTED_EXTENSIONS = new HashMap<>();
        SUPPORTED_EXTENSIONS.put("C", "c");
        SUPPORTED_EXTENSIONS.put("C#", "cs");
        SUPPORTED_EXTENSIONS.put("C++", "cpp");
        SUPPORTED_EXTENSIONS.put("CSS", "css"); 
        SUPPORTED_EXTENSIONS.put("CSV", "csv");
        SUPPORTED_EXTENSIONS.put("Dart", "dart");
        SUPPORTED_EXTENSIONS.put("Go", "go");
        SUPPORTED_EXTENSIONS.put("HTML", "html"); 
        SUPPORTED_EXTENSIONS.put("IML", "iml"); 
        SUPPORTED_EXTENSIONS.put("Java", "java");
        SUPPORTED_EXTENSIONS.put("JavaScript", "js");
        SUPPORTED_EXTENSIONS.put("JSON", "json");
        SUPPORTED_EXTENSIONS.put("IPYNB", "ipynb");
        SUPPORTED_EXTENSIONS.put("Kotlin", "kt");
        SUPPORTED_EXTENSIONS.put("PHP", "php");
        SUPPORTED_EXTENSIONS.put("Python", "py");
        SUPPORTED_EXTENSIONS.put("Rust", "rs"); 
        SUPPORTED_EXTENSIONS.put("SQL", "sql");
        SUPPORTED_EXTENSIONS.put("XML", "xml");
    }

 
    public static JMenu syntaxMenuBar() {
        if (syntaxMenuBar == null) {
            syntaxMenuBar = createSyntaxMenuBar();
        }
        return syntaxMenuBar;
    }

    private static JMenu createSyntaxMenuBar() {
        // Syntax Check Menu
        JMenu syntaxMenu = new JMenu("Code Analysis");
        JMenuItem textItem = new JMenuItem("None (Normal Text)");
        JMenuItem resetItem = new JMenuItem("Reset Code Analysis");
         
        syntaxMenu.add(textItem);
        syntaxMenu.addSeparator();
        
        // Set action listeners
        textItem.addActionListener(e -> setSyntaxCheck("txt", textItem));
        resetItem.addActionListener(e -> resetCode());
           
        // Create menu items for supported languages
        for (Map.Entry<String, String> entry : SUPPORTED_EXTENSIONS.entrySet()) {
            String langName = entry.getKey();
            String langExt = entry.getValue();

            JMenuItem syntaxItem = new JMenuItem(langName);
            syntaxItem.addActionListener(e -> setSyntaxCheck(langExt,syntaxItem));
            syntaxMenu.add(syntaxItem);
        }
        syntaxMenu.addSeparator();
        syntaxMenu.add(resetItem);

        return syntaxMenu;
    }
    
    
    private static String getSyntaxCheckLang(String lang) {
        Map<String, String> extensions = new HashMap<>();
       extensions.put("C", "c");
       extensions.put("C#", "cs");
       extensions.put("C++", "cpp");
       extensions.put("CSS", "css");
       extensions.put("CSV", "csv");
       extensions.put("Dart", "dart");
       extensions.put("Go", "go");
       extensions.put("HTML", "html");
       extensions.put("IML", "iml"); 
       extensions.put("Java", "java");
       extensions.put("JavaScript", "js");
       extensions.put("JSON", "json");
       extensions.put("IPYNB", "ipynb");
       extensions.put("Kotlin", "kt");
       extensions.put("PHP", "php");
       extensions.put("Python", "py");
       extensions.put("Rust", "rs");
       extensions.put("SQL", "sql");
       extensions.put("XML", "xml");
       extensions.put("None (Normal Text)", "txt");
        
        return extensions.getOrDefault(lang, "");
    }
    
    private static void resetCode() {
        texteditor().resetCheckCode();
        clearMenuDots(syntaxMenuBar); // Clear all dots from the menu
        selectedCode = null;
      //  selectedCategory = null;
    }
    
    
    public static void updateMenuCodeAnalysisActiveTab(JMenu langMenuBar) {
        SwingUtilities.invokeLater(() -> {
            int selectedIndex = NotepadXXV1_2_0.getTabbedPane().getSelectedIndex();
            if (selectedIndex == -1) {
           //     System.out.println("No tab selected. Resetting language."); // Debug print
                resetCode();
                return; // No open tab, clear dot
            }
            String tabName;
            try {
            if(texteditor().getCurrentFile()!=null) {
                tabName = texteditor().getCurrentFile().toString();
               }else if (texteditor().getTabTitle() != null){
               	tabName = texteditor().getTabTitle().replace(" *", "");
               }else {
            	   resetCode();
               	return;
               }
            } catch (Exception e) {
              //  resetCode();
                return;
            }
            
        //    System.out.println("Current tab: " + tabName); // Debug print
            String currentLang = loadCodeAnalysisFromFile(tabName); // Fetch saved language
           // System.out.println("Language for current tab: " + currentLang); // Debug print

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
          // System.out.println("Setting language in text editor: " + langMenuBar.getText()); // Debug print
            texteditor().setCheckCode();
        });
    }
    
    
    private static void applyMenuSelection(JMenu langMenuBar, String currentLang) {
        currentLang = (currentLang != null) ? currentLang.trim() : "";
        clearMenuDots(langMenuBar); // Clear existing dots first

        if (currentLang.isEmpty()) return;

        // Iterate through the menu's components (now using getMenuComponents)
        for (Component component : langMenuBar.getMenuComponents()) {
              if (component instanceof JMenuItem item) {
                        processMenuItem(item, currentLang);
                    
                
            } else if (component instanceof JMenuItem item) {
                processMenuItem(item, currentLang);
            }
            }    
    }

    // Helper method to avoid code duplication
    private static void processMenuItem(JMenuItem item, String targetLang) {
        String itemText = item.getText().replace("• ", "").trim();
        String itemLang = getSyntaxCheckLang(itemText);

   /*     System.out.println("[DEBUG] Checking item: " + itemText 
            + " | Ext: " + itemLang 
            + " | Target: " + targetLang);*/
        
        if (targetLang.equals(itemLang)) {
         //   System.out.println("[MATCH] Found: " + itemText);
            item.setText("• " + itemText); // Add dot to language
            selectedCode = item;

            // Force UI refresh
            SwingUtilities.invokeLater(() -> {
                item.revalidate();
                item.repaint();
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

    private static void setSyntaxCheck(String ext,  JMenuItem language) {
        int selectedIndex = NotepadXXV1_2_0.getTabbedPane().getSelectedIndex();
        if (selectedIndex == -1) return; // No open tab

//       String tabName = texteditor().getTabTitle().replace(" *", "");
        String tabName;
        
        if(texteditor().getCurrentFile()!=null) {
         tabName = texteditor().getCurrentFile().toString();
        }else {
        	tabName = texteditor().getTabTitle().replace(" *", "");
        }
        
        // Save the new language setting
        saveCodeAnalysisToFile(tabName, ext);
        texteditor().setCheckCode();

        // Remove previous dot
        if (selectedCode != null) selectedCode.setText(selectedCode.getText().replace("• ", ""));

        // Apply new selection
        if (language != null) language.setText("• " + language.getText());

        // Store selection
        selectedCode = language;
     /*   SwingUtilities.invokeLater(() -> {
            SwingUtilities.updateComponentTreeUI(langMenuBar); // Update the actual menu bar
        });*/
    }
    
    public static void updateCodeAnalysisActiveTab() {
        if (syntaxMenuBar == null) {
            syntaxMenuBar = syntaxMenuBar(); 
        }
        updateMenuCodeAnalysisActiveTab(syntaxMenuBar);
    }
    
    
    public static void clearConfigCodeAnalysisFile() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(""); // Clear file
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static String loadCodeAnalysisFromFile(String tabName) {
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
    public static String getCodeAnalysisForFile(String tabname) {
    	return loadCodeAnalysisFromFile(tabname);
    }
    
    private static void saveCodeAnalysisToFile(String tabName, String ext) {
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
            properties.store(writer, "Code Analysis settings for each tab");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setLanguageForFile(String tabname, String ext) {
    	saveCodeAnalysisToFile(tabname,ext);
    }
    
    public static void removeCodeAnalysisFromFile(String tabName) {
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
                properties.store(writer, "Updated Code Analysis settings for each tab");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
         //   System.out.println("Tab name not found: " + tabName);
        }
    }

 // Method to check if the language entry exists
    protected static boolean doesCodeAnalysisEntryExist(String tabName) {
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
    	
        return NotepadXXV1_2_0.texteditor();
    }


}
