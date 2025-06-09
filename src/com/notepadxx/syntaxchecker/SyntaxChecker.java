package com.notepadxx.syntaxchecker;


import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import com.notepadxx.menu.CodeAnalysisMenuBar;
import com.notepadxx.menu.PreferenceMenu;
import com.notepadxx.notepadxx.Texteditor;

public class SyntaxChecker {

    private Texteditor editor;

    private JavaChecker javachecker = null;
    private PythonChecker pychecker= null;
    private CSSChecker csschecker= null;
    private HTMLChecker htmlchecker= null;
    private JavaScriptChecker jschecker= null;
    private Cchecker cchecker= null;
    private CPPchecker cppchecker= null;
    private CSVchecker csvchecker= null;
    private CSharpChecker csharpchecker= null;
    private JSONChecker jsonchecker= null;
    private SQLChecker sqlchecker= null;
    private goChecker gochecker= null;
    private xmlChecker xmlchecker= null;
    private phpChecker phpchecker= null;
    private rustChecker rustchecker= null;
    private dartChecker dartchecker= null;
    private kotlinChecker kotlinchecker= null;
    private boolean enableToolTip ;
    private boolean enableAdvanceMode;


    public SyntaxChecker(Texteditor editor) {
    	if(editor == null) return;
        this.editor = editor;
    }
    
    private JavaChecker getJavaChecker() {
        if (javachecker == null) {
            javachecker = new JavaChecker(editor);
        }
        return javachecker;
    }

    private PythonChecker getPythonChecker() {
        if (pychecker == null) {
            pychecker = new PythonChecker(editor);
        }
        return pychecker;
    }

    private CSSChecker getCSSChecker() {
        if (csschecker == null) {
            csschecker = new CSSChecker(editor);
        }
        return csschecker;
    }

    private HTMLChecker getHTMLChecker() {
        if (htmlchecker == null) {
            htmlchecker = new HTMLChecker(editor);
        }
        return htmlchecker;
    }

    private JavaScriptChecker getJavaScriptChecker() {
        if (jschecker == null) {
            jschecker = new JavaScriptChecker(editor);
        }
        return jschecker;
    }

    private Cchecker getCChecker() {
        if (cchecker == null) {
            cchecker = new Cchecker(editor);
        }
        return cchecker;
    }

    private CPPchecker getCPPChecker() {
        if (cppchecker == null) {
            cppchecker = new CPPchecker(editor);
        }
        return cppchecker;
    }

    private CSVchecker getCSVChecker() {
        if (csvchecker == null) {
            csvchecker = new CSVchecker(editor);
        }
        return csvchecker;
    }

    private CSharpChecker getCSharpChecker() {
        if (csharpchecker == null) {
            csharpchecker = new CSharpChecker(editor);
        }
        return csharpchecker;
    }

    private JSONChecker getJSONChecker() {
        if (jsonchecker == null) {
            jsonchecker = new JSONChecker(editor);
        }
        return jsonchecker;
    }

    private SQLChecker getSQLChecker() {
        if (sqlchecker == null) {
            sqlchecker = new SQLChecker(editor);
        }
        return sqlchecker;
    }

    private goChecker getGoChecker() {
        if (gochecker == null) {
            gochecker = new goChecker(editor);
        }
        return gochecker;
    }

    private xmlChecker getXMLChecker() {
        if (xmlchecker == null) {
            xmlchecker = new xmlChecker(editor);
        }
        return xmlchecker;
    }

    private phpChecker getPHPChecker() {
        if (phpchecker == null) {
            phpchecker = new phpChecker(editor);
        }
        return phpchecker;
    }

    private rustChecker getRustChecker() {
        if (rustchecker == null) {
            rustchecker = new rustChecker(editor);
        }
        return rustchecker;
    }

    private dartChecker getDartChecker() {
        if (dartchecker == null) {
            dartchecker = new dartChecker(editor);
        }
        return dartchecker;
    }

    private kotlinChecker getKotlinChecker() {
        if (kotlinchecker == null) {
            kotlinchecker = new kotlinChecker(editor);
        }
        return kotlinchecker;
    }
    
    public static void updateUI(Texteditor editor) {
    	
		   SwingUtilities.invokeLater(() -> {
		   editor.getTextArea().setToolTipText(null);
		//   editor.getTextArea().revalidate();
		    editor.getTextArea().repaint();
		    //  Get current mouse position relative to the text area
	        Point mousePos = editor.getTextArea().getMousePosition();
	        if (mousePos != null) {
	            //  Simulate a tiny movement at the current mouse position
	            editor.getTextArea().dispatchEvent(new MouseEvent(
	                editor.getTextArea(),
	                MouseEvent.MOUSE_MOVED,
	                System.currentTimeMillis(),
	                0,
	                mousePos.x + 1, mousePos.y + 1, // Small movement
	                0,
	                false
	            ));
	        }
	    });
	    }
    
    private String getlanguageExt() {
        String ext = CodeAnalysisMenuBar.getCodeAnalysisForFile(editor.getTabTitle());

        if (ext == null || ext.isEmpty()) {
            ext = editor.getFileExtension(editor.getCurrentFile());
        }
        
        return (ext != null) ? ext.toLowerCase() : "";
    }
    
    public   void toggleToolTip() {
    	
    	// boolean tooltip = hightlightline.isTooltipEnabled();
         //hightlightline.saveTooltipPreference(!tooltip);
     //    enableToolTip = tooltip;
        // PreferenceMenu.refreshToolTip(tooltip);
    	
    	  boolean currentTooltip = hightlightline.isTooltipEnabled();
    	    boolean tooltip = !currentTooltip;
    	    hightlightline.saveTooltipPreference(tooltip);
    	    PreferenceMenu.refreshToolTip(tooltip);

         try {
         //	String fileExtension = editor.getFileExtension(editor.getCurrentFile());

        	 String fileExtension = getlanguageExt();
        	 
         	if (fileExtension == null || fileExtension.isEmpty()) {
                return; // Prevent NullPointerException
            }

         	 fileExtension = fileExtension.toLowerCase();

            switch (fileExtension){
                case "java":
             	   getJavaChecker().setToolTip(tooltip);
                    break;

                case "py":
                case"ipynb":
             	   getPythonChecker().setToolTip(tooltip);
                    break;

                case "css":
             	   getCSSChecker().setToolTip(tooltip);
                	break;

                case "html":
                case "htm":
                	getHTMLChecker().setToolTip(tooltip);
                	break;

                case "js":
             	   getJavaScriptChecker().setToolTip(tooltip);
             	   break;

                case "c":
             	   getCChecker().setToolTip(tooltip);
             	   break;

                case "cpp":
             	   getCPPChecker().setToolTip(tooltip);
                	break;

                case "csv":
             	   getCSVChecker().setToolTip(tooltip);
             	   break;

                case "cs":
             	   getCSharpChecker().setToolTip(tooltip);
                	break;

                case "json":
             	   getJSONChecker().setToolTip(tooltip);
             	   break;

                case "sql":
             	   getSQLChecker().setToolTip(tooltip);
             	   break;

                case "go":
             	   getGoChecker().setToolTip(tooltip);
             	   break;

                case "xml":
                case "iml":
                	getXMLChecker().setToolTip(tooltip);
                	break;

                case "php":
             	   getPHPChecker().setToolTip(tooltip);
             	   break;

                case "rs":
             	   getRustChecker().setToolTip(tooltip);
             	   break;

                case "dart":
             	   getDartChecker().setToolTip(tooltip);
             	   break;

                case "kt":
             	   getKotlinChecker().setToolTip(tooltip);
             	   break;


                default:
                    break;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public   void toggleAdvancedMode() {
    //	boolean advancedMode = hightlightline.isAdvancedModeEnabled();
    	//hightlightline.saveAdvancedModePreference(!advancedMode);
    	//enableAdvanceMode = advancedMode;
    	//PreferenceMenu.refreshAdvancneMode(advancedMode);
    	
    	 boolean currentMode = hightlightline.isAdvancedModeEnabled();
    	    boolean advancedMode = !currentMode;
    	    hightlightline.saveAdvancedModePreference(advancedMode);
    	    PreferenceMenu.refreshAdvancneMode(advancedMode);
    	    
        try {
        //	String fileExtension = editor.getFileExtension(editor.getCurrentFile());

        	 String fileExtension = getlanguageExt();
        	
        	if (fileExtension == null || fileExtension.isEmpty()) {
                return; // Prevent NullPointerException
            }

        	 fileExtension = fileExtension.toLowerCase();
        	 
           switch (fileExtension){
               case "java":
            	   getJavaChecker().setAdvancedMode(advancedMode);
                   break;

               case "py":
               case"ipynb":
            	   getPythonChecker().setAdvancedMode(advancedMode);
                   break;

               case "css":
            	   getCSSChecker().setAdvancedMode(advancedMode);
               	break;

               case "html":
               case "htm":
               	getHTMLChecker().setAdvancedMode(advancedMode);
               	break;

               case "js":
            	   getJavaScriptChecker().setAdvancedMode(advancedMode);
            	   break;

               case "c":
            	   getCChecker().setAdvancedMode(advancedMode);
            	   break;

               case "cpp":
            	   getCPPChecker().setAdvancedMode(advancedMode);
               	break;

               case "csv":
            	   getCSVChecker().setAdvancedMode(advancedMode);
            	   break;

               case "cs":
            	   getCSharpChecker().setAdvancedMode(advancedMode);
               	break;

               case "json":
            	   getJSONChecker().setAdvancedMode(advancedMode);
            	   break;

               case "sql":
            	   getSQLChecker().setAdvancedMode(advancedMode);
            	   break;

               case "go":
            	   getGoChecker().setAdvancedMode(advancedMode);
            	   break;

               case "xml":
               case "iml":
               	getXMLChecker().setAdvancedMode(advancedMode);
               	break;

               case "php":
            	   getPHPChecker().setAdvancedMode(advancedMode);
            	   break;

               case "rs":
            	   getRustChecker().setAdvancedMode(advancedMode);
            	   break;

               case "dart":
            	   getDartChecker().setAdvancedMode(advancedMode);
            	   break;

               case "kt":
            	   getKotlinChecker().setAdvancedMode(advancedMode);
            	   break;


               default:
                   break;
           }



       } catch (Exception e) {
           e.printStackTrace();
       }


    }


    public   void checkJavaSyntax(String code) {
      getJavaChecker().JavaSyntax(code);
    }

    public   void checkPythonSyntax(String code) {
    	  getPythonChecker().PythonSyntax(code);
    }

    public   void checkcssSyntax(String code) {
        getCSSChecker().CSSSyntax(code);
    }

	public    void checkHtmlSyntax(String code) {
	      getHTMLChecker().HTMLSyntax(code);
	}

	 public   void checkjsSyntax(String code) {
	        getJavaScriptChecker().JavaScriptSyntax(code);
	    }

	 public   void checkCSyntax(String code) {
	        getCChecker().CSyntax(code);
	    }

	 public   void checkCPPSyntax(String code) {
	        getCPPChecker().CPPSyntax(code);
	    }

	 public   void checkCSVSyntax(String code) {
	        getCSVChecker().CSVSyntax(code);
	    }

	 public   void checkCSharpSyntax(String code) {
	        getCSharpChecker().CSharpSyntax(code);
	    }

	 public   void checkJSONSyntax(String code) {
	        getJSONChecker().JSONSyntax(code);
	    }

	 public   void checkSQLSyntax(String code) {
	        getSQLChecker().SQLSyntax(code);
	    }

	 public   void checkgoSyntax(String code) {
	        getGoChecker().goSyntax(code);
	    }

	 public   void checkxmlSyntax(String code) {
	        getXMLChecker().xmlSyntax(code);
	    }

	 public   void checkphpSyntax(String code) {
	        getPHPChecker().phpSyntax(code);
	    }

	 public   void checkrustSyntax(String code) {
	        getRustChecker().rustSyntax(code);
	    }

	 public  void checkdartSyntax(String code) {
	        getDartChecker().dartSyntax(code);
	    }

	 public   void checkkotlinSyntax(String code) {
	        getKotlinChecker().kotlinSyntax(code);
	    }


	 public boolean getToolTip() {
		 return enableToolTip;
	 }
	 public boolean getAdvancedMode() {
		 return enableAdvanceMode;
	 }
	 

}



