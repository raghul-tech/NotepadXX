package com.notepadxx.syntaxchecker;

import com.notepadxx.menu.CodeAnalysisMenuBar;
import com.notepadxx.notepadxx.Texteditor;

public class syntaxError{
        int line;
        int charPositionInLine;
        String msg;
        
       syntaxError(int line, int charPositionInLine, String msg) {
            this.line = line;
            this.charPositionInLine = charPositionInLine;
            this.msg = msg;
        }
       
       protected static String getLangForThis(Texteditor editor) {  
    	    String tabName;

    	    if (editor.getCurrentFile() != null) {
    	        tabName = editor.getCurrentFile().toString();
    	    } else {
    	        tabName = editor.getTabTitle().replace(" *", "");
    	    }

    	    String Lang = CodeAnalysisMenuBar.getCodeAnalysisForFile(tabName);
    	    
    	    if (Lang != null) {
    	        switch (Lang.toLowerCase()) {  // Make it case insensitive
    	            case "py":
    	                Lang = "Python";
    	                break;
    	            case "ipynb":
    	                Lang = "IPYNB";
    	                break;
    	            case "iml":
    	                Lang = "IML";
    	                break;
    	            case "xml":
    	                Lang = "XML";
    	                break;
    	            default:
    	                break; // Keep Lang as is if it's not one of these
    	        }
    	    } else {
    	        Lang = "Unknown"; // Set a default value instead of returning null
    	    }
    	    
    	    return Lang;
    	}


}

