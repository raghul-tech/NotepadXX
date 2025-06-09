package com.notepadxx.syntaxchecker;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.text.BadLocationException;

import com.notepadxx.notepadxx.ConfigFiles;
import com.notepadxx.notepadxx.Texteditor;

public class hightlightline {
	
private static final String CONFIG_FILE = ConfigFiles.getConfigToolTip();
private static final String TOOLTIP_KEY = "tooltip_enabled";
private static final String ADVANCED_MODE_KEY = "advanced_mode_enabled";;
	
    private void highlightLine1(int line,Texteditor editor) {
        try {

         int start = editor.getTextArea().getLineStartOffset(line - 1);
         int end = editor.getTextArea().getLineEndOffset(line - 1);

             editor.getTextArea().getHighlighter().addHighlight(
                start, end, new BackgroundHighlightPainter(new Color(255, 0, 0, 100))
            );
        } catch (BadLocationException ex) {
            //ex.printStackTrace();
        }
    }


	protected String ErrorForCode1(List<syntaxError> syntaxError,Texteditor editor,boolean advancedMode,boolean tooltip,String Lang) {
    	 
    	 boolean tooltipEnabled = isTooltipEnabled();
    	 
    	  /*if (!tooltipEnabled) {
              return null; // Tooltip is turned off
          }*/

    	 
    	if(tooltip || !tooltipEnabled) {
    	//	if(!tooltip) {
          	return "Ctrl+Shift+M to turn on";
          }
    	  
    	List<syntaxError> syntaxErrors =  syntaxError;
    if (!syntaxErrors.isEmpty()) {
    	StringBuilder tooltipBuilder = new StringBuilder(String.format("<html>Language: %s <br>Ctrl+Shift+M to turn off<br><font color='red'>Syntax Errors:<br>", Lang));
        for (syntaxError error : syntaxErrors) {
            highlightLine1(error.line,editor);


            tooltipBuilder.append(String.format("Line %d, Position %d: %s<br>",
            		      error.line , error.charPositionInLine, formatErrorMessage(error.msg,advancedMode)));
        }
        tooltipBuilder.append("Ctrl + M to change mode</font></html>");
       // editor.getTextArea().setToolTipText(tooltipBuilder.toString());
        
      
        
        return tooltipBuilder.toString();
    } else {
        //  editor.getTextArea().setToolTipText(null);
        return null;
    }
    }


    private String formatErrorMessage(String originalMsg, boolean advancedMode) {
        
    	boolean Mode = isAdvancedModeEnabled();
    	
    	if (advancedMode || Mode) {
    	//if (advancedMode) {
            return "Advanced: " + originalMsg;
        }

        if (originalMsg.contains("no viable alternative at input")) {
            return "Error: Unexpected input. Check your code near this line.";
        } else if (originalMsg.contains("mismatched input")) {
            return "Error: Mismatched input. Fix missing or extra symbols.";
        } else if (originalMsg.contains("missing")) {
            return "Error: Something is missing in your code.";
        } else if (originalMsg.contains("extraneous input")) {
            return "Error: Extra symbols or keywords detected.";
        } else if (originalMsg.contains("token recognition error")) {
            return "Error: Unrecognized symbol. Check your syntax.";
        } else if (originalMsg.contains("unterminated string")) {
            return "Error: String not closed properly.";
        } else if (originalMsg.contains("invalid character")) {
            return "Error: Invalid character found.";
        } else if (originalMsg.contains("expecting")) {
            return "Error: Missing symbol or keyword. Check your code.";
        } else if (originalMsg.contains("rule")) {
            return "Error: A rule was violated. Check your logic.";
        } else if (originalMsg.contains("cannot find symbol")) {
            return "Error: Undefined variable or function.";
        } else {
            return "Error: Check your code for syntax or logical issues.";
        }
    }
    
    private static void savePreference(String key, boolean value) {
        Properties properties = loadPreferences();
        properties.setProperty(key, value ? "true" : "false");

        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            properties.store(out, "Application Preferences");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Properties loadPreferences() {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            // Set default values if the file does not exist
            properties.setProperty(TOOLTIP_KEY, "true");  // Default: true
            properties.setProperty(ADVANCED_MODE_KEY, "false"); // Default: false
        }
        return properties;
    }
    
    public static void saveTooltipPreference(boolean enabled) {
        savePreference(TOOLTIP_KEY, enabled);
    }

    // Read tooltip preference
    public static boolean isTooltipEnabled() {
        Properties properties = loadPreferences();
        return Boolean.parseBoolean(properties.getProperty(TOOLTIP_KEY, "true"));
    }

    // Save advanced mode preference
    public static void saveAdvancedModePreference(boolean enabled) {
        savePreference(ADVANCED_MODE_KEY, enabled);
    }

    // Read advanced mode preference
    public  static boolean isAdvancedModeEnabled() {
        Properties properties = loadPreferences();
        return Boolean.parseBoolean(properties.getProperty(ADVANCED_MODE_KEY, "false"));
    }

}
