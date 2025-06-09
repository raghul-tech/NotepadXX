package com.notepadxx.menu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import com.notepadxx.notepadxx.Texteditor;

public class Menubar {

	
public static JMenuBar createMenuBar() {
	JMenuBar MenuBar = new JMenuBar();
	 String os = System.getProperty("os.name").toLowerCase();
     if (os.contains("win")) {
    	 
        MenuBar =  WindowsMenuBar.WindowscreateMenuBar();
     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
        MenuBar =   LinuxMenuBar.LinuxcreateMenuBar();
     } else {
         JOptionPane.showMessageDialog(
             null,
             "Unsupported operating system.",
             "Error",
             JOptionPane.ERROR_MESSAGE 
         );
     }
	// MenuBar =  LinuxMenuBar.LinuxcreateMenuBar();
     return MenuBar;
} 

public static void  MenuComponentChange() {
	
	LanguageMenuBar.updateLanguageMenuForActiveTab();
	 
	CodeAnalysisMenuBar. updateCodeAnalysisActiveTab();
	
	WindowMenu.updateWindowMenu();
}

public static void EntryExist(Texteditor editor, String tabName) {
	if(tabName == null || editor == null) {
		return;
	}
	
	if(!LanguageMenuBar.doesLanguageEntryExist(tabName)) {
		editor.resetLanguage();
	}
	if(!CodeAnalysisMenuBar.doesCodeAnalysisEntryExist(tabName)) {
		editor.resetCheckCode();
	}
}


}