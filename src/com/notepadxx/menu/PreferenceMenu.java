package com.notepadxx.menu;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.syntaxchecker.hightlightline;

public class PreferenceMenu {
	private static JMenu preference;
	
	private static JCheckBoxMenuItem tooltipItem ;
	private static  JCheckBoxMenuItem modeItem;
	  private static JCheckBoxMenuItem wrapItem;
//	 private static final String CONFIG_FILE = ConfigFiles.getConfigToolTip();
	// private static final String WORD_WRAP_KEY = "WORD_WRAP_enabled";
	 private static boolean wrap_key = false;
	// private static  SyntaxChecker checker = new SyntaxChecker(NotepadXXV1_2_0.texteditor());
	
	protected static JMenu PreferenceMenuBar() {
		if(preference == null) {
			preference = createPreferenceMenuBar();
		}
	//	 refreshMenuStates();
		return preference;
	}

	 
	 
	 private static JMenu createPreferenceMenuBar() {
		    JMenu preferencesMenu = new JMenu("Preferences");

		    // Theme Switcher
		    preferencesMenu.add(ThemesMenuBar.themesMenuBar());
		    preferencesMenu.addSeparator();

		    // Tooltips
		    tooltipItem = new JCheckBoxMenuItem("Enable Tooltips");
		    tooltipItem.setSelected(hightlightline.isTooltipEnabled());
		    tooltipItem.setToolTipText("Toggle tooltip display");
		    tooltipItem.addActionListener(e -> {
		  
		        Action action = NotepadXXV1_2_0.texteditor().getTextArea()
		                         .getActionMap().get("tooltip on or off");
		        if (action != null) {
		            action.actionPerformed(null);
		        }
		    });
		    preferencesMenu.add(tooltipItem);

		    // Editor Mode
		     modeItem = new JCheckBoxMenuItem("Use Advanced Tooltips");
		    modeItem.setSelected(hightlightline.isAdvancedModeEnabled());
		    modeItem.setToolTipText("Toggle between Noraml/Advanced Tooltips");
		    modeItem.addActionListener(e -> {
		
		    	 Action action = NotepadXXV1_2_0.texteditor().getTextArea()
                         .getActionMap().get("Advanced mode");
        if (action != null) {
            action.actionPerformed(null);
        }
		    	
		    });
		    preferencesMenu.add(modeItem);

		    // Word Wrap
		    wrapItem = new JCheckBoxMenuItem("Word Wrap");
		    wrapItem.setSelected(isWordWrapEnabled());
		    wrapItem.setToolTipText("Toggle line wrapping");
		    wrapItem.addActionListener(e -> {
		        NotepadXXV1_2_0.updateLineWrapForAllTabs(wrapItem.isSelected());
		    //    savePreference(WORD_WRAP_KEY, wrapItem.isSelected());
		        wrap_key=wrapItem.isSelected();
		       
		    	
		    });
		    preferencesMenu.add(wrapItem);

		    return preferencesMenu;
		}
	 
	 public static void refreshToolTip(boolean tooltip) {
		 if (tooltipItem != null) {
		     //   boolean tooltipState = !hightlightline.isTooltipEnabled();
		        tooltipItem.setSelected(tooltip);
		   //     ToolTipManager.sharedInstance().setEnabled(tooltipState);
		    }
	 }
	 
	 public static void refreshAdvancneMode(boolean advance) {
		  if (modeItem != null) {
		        modeItem.setSelected(advance);
		    } 
	 }
	/* public static void refreshMenuStates() {
		    if (tooltipItem != null) {
		        tooltipItem.setSelected(hightlightline.isTooltipEnabled());
		    }

		    if (modeItem != null) {
		        modeItem.setSelected(hightlightline.isAdvancedModeEnabled());
		    }

		    if (wrapItem != null) {
		        wrapItem.setSelected(isWordWrapEnabled());
		    }

		//    System.out.println("All preferences refreshed");

		}*/



	public static boolean isWordWrapEnabled() {
		// TODO Auto-generated method stub
		return wrap_key;
	}

	/** word wrapping is causing a delay when it is true
	 * when the application is starting 
	 * 
	 */
	
	
	/*  private static void savePreference(String key, boolean value) {
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
	            properties.setProperty(WORD_WRAP_KEY, "false");  
	        }
	        return properties;
	    }
	    public static boolean isWordWrapEnabled() {
	        Properties properties = loadPreferences();
	        return Boolean.parseBoolean(properties.getProperty(WORD_WRAP_KEY, "false"));
	    }*/
	 
	    
}
