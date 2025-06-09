package com.notepadxx.notepadxx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.notepadxx.menu.CodeAnalysisMenuBar;
import com.notepadxx.syntaxchecker.SyntaxChecker;

public class CheckCode {

    private  SyntaxChecker checker;
    private  Texteditor editor;
    private SyntaxWorker syntaxWorker; // SwingWorker instance
    private Timer debounceTimer; // Timer for debounce mechanism
    private  static final Set<String> SUPPORTED_EXTENSIONS;
    private DocumentListener documentListener;
    private boolean enabled = false;
    
   static  {
        SUPPORTED_EXTENSIONS = new HashSet<>();
        SUPPORTED_EXTENSIONS.add("java"); 
        SUPPORTED_EXTENSIONS.add("py");
        SUPPORTED_EXTENSIONS.add("ipynb");
        SUPPORTED_EXTENSIONS.add("css");
        SUPPORTED_EXTENSIONS.add("html");
        SUPPORTED_EXTENSIONS.add("htm");
        SUPPORTED_EXTENSIONS.add("js");
        SUPPORTED_EXTENSIONS.add("c");
        SUPPORTED_EXTENSIONS.add("cpp");
        SUPPORTED_EXTENSIONS.add("csv");
        SUPPORTED_EXTENSIONS.add("cs");
        SUPPORTED_EXTENSIONS.add("json");
        SUPPORTED_EXTENSIONS.add("sql");
        SUPPORTED_EXTENSIONS.add("go");
        SUPPORTED_EXTENSIONS.add("xml");
        SUPPORTED_EXTENSIONS.add("iml");
        SUPPORTED_EXTENSIONS.add("php");
        SUPPORTED_EXTENSIONS.add("rs");
        SUPPORTED_EXTENSIONS.add("dart");
        SUPPORTED_EXTENSIONS.add("kt");
    }
   
    public CheckCode(Texteditor editor) {
    	
    	
        this.editor = editor;
        this.checker = new SyntaxChecker(editor);     
        // Add a DocumentListener to detect text changes
       // editor.getTextArea().getDocument().addDocumentListener(new DocumentListener() {
        setupDocumentListener();
    	 
    }

    /*private void adjustDelayBasedOnFileSize() {
        int fileSize = editor.getTextContent().length(); // Get the text content size
        //double fileSize = (double) editor.getTextContent().length() / (1024 * 1024);
        int delay;
        if (fileSize < 10_000) {
            delay = 300; // 300ms for small files
        } else if (fileSize < 100_000) {
            delay = 500; // 500ms for medium files
        } else if (fileSize < 1_000_000) {
            delay = 1000; // 1 second for large files
        } else {
            delay = 1500; // 1.5 seconds for very large files
        }
        
        
        System.out.println("File size: " + fileSize + " - Adjusted delay: " + delay + "ms");
        debounceTimer.setDelay(delay); // Set the new delay
    }
    */
   
    	 private void setupDocumentListener() {
    		  documentListener = new DocumentListener() { 
    	           @Override
    	            public void insertUpdate(DocumentEvent e) {
    	            	//adjustDelayBasedOnFileSize();
    	                debounceTimer.restart();
    	            }

    	            @Override
    	            public void removeUpdate(DocumentEvent e) {
    	            	//adjustDelayBasedOnFileSize();
    	                debounceTimer.restart();
    	            }

    	            @Override
    	            public void changedUpdate(DocumentEvent e) {
    	            	//adjustDelayBasedOnFileSize();
    	                debounceTimer.restart();
    	            }
    	        };
    	        
    	        editor.getTextArea().getDocument().addDocumentListener(documentListener);
    	        
    	     // Initialize debounce timer with a delay (e.g., 500ms)
    	        debounceTimer = new Timer(500, new ActionListener() {
    	            @Override
    	            public void actionPerformed(ActionEvent e) {
    	             //   scheduleSyntaxCheck();
    	            	 if(enabled) {
    	        			 scheduleSyntaxCheck();
    	        		 }	
    	        			
    	            	 
    	            	 
    	            }
    	        });
    	        debounceTimer.setRepeats(false); // Ensure the timer only runs once per event

    	 }
    	 
    	 
 
    	 protected void setEnabled(boolean enabled) {
    		 this.enabled = enabled;
    	 }
    	 
    	 public  void   removeCheckCodeDoc() {
    		editor.getTextArea().getDocument().removeDocumentListener(documentListener);
    	 }
    	 
   
    // Schedule syntax checking using SwingWorker
    private  void scheduleSyntaxCheck() {
    	// System.out.println("schedule action performed.");
    	 String fileExtension = "";
    	 String tabName = "";
    	 if(editor.getCurrentFile()!=null) {
             tabName = editor.getCurrentFile().toString();
            }else {
            	tabName = editor.getTabTitle().replace(" *", "");
            }
            

        if (syntaxWorker != null && !syntaxWorker.isDone()) {
            syntaxWorker.cancel(true); // Cancel the previous task if it's still running
        }

        
		fileExtension = CodeAnalysisMenuBar.getCodeAnalysisForFile(tabName) ;
        
         if(fileExtension == null && editor.getCurrentFile() != null) {
        	 fileExtension = editor.getFileExtension(editor.getCurrentFile());
         }
        
         
       //  System.out.println("Syntax Check1 "+fileExtension);
        if (SUPPORTED_EXTENSIONS.contains(fileExtension.toLowerCase()) || isItMD(fileExtension.toLowerCase())) {
          //  System.out.println("Skipping check: Unsupported file extension.");
            //return;
        	 syntaxWorker = new SyntaxWorker(fileExtension);
             syntaxWorker.execute(); // Execute the new task
        }

    }
    
    
    public static boolean isItMD(String ext) {
 	   switch (ext) {
 	   case "md":
        case "markdown":
        case "mdown":
        case "mkd":
        case "mkdn":
        case "mdtext":
     	   return true;
     default :
     	return false;
 	   }
   }
   
   public void checkMD() {
   if(isItMD(editor.getFileExtension(editor.getCurrentFile()))) {
       editor.getTextArea().setToolTipText("<html>Ctrl+Shift+K<br>Ctrl+K to view the preview</html>");
         }
   }

    

    // SwingWorker class for performing syntax checking
    protected class SyntaxWorker extends SwingWorker<Void, Void> {

        private final String fileExtension;

        public SyntaxWorker(String fileExtension) {
            this.fileExtension = fileExtension;
        }

        @Override
        protected Void doInBackground() {
            checkSyntax(fileExtension); // Perform the heavy syntax-checking task in the background
            return null;
        }

        @Override
        protected void done() {
            // Optionally handle any UI updates after the background task completes
            // For example, updating status messages or displaying errors
        }
    }

    // Syntax checking logic
    private void checkSyntax(String fileExtension) {
        if (fileExtension == null || fileExtension.isEmpty()) {
            return; // Skip if no file extension is provided
        }

      //  System.out.println("Syntax Check2 "+fileExtension);
        try {
            String code = editor.getTextContent();
        	// StringBuilder code = new StringBuilder(editor.getTextContent());

            if (code == null || code.isEmpty()) {
                return;
            }

            switch (fileExtension.toLowerCase()) {
                case "java":
                    checker.checkJavaSyntax(code);
                    break;
                case "py":
                case "ipynb":
                    checker.checkPythonSyntax(code);
                    break;
                case "css":
                    checker.checkcssSyntax(code);
                    break;
                case "html":
                case "htm":
                    checker.checkHtmlSyntax(code);
                    break;
                case "js":
                    checker.checkjsSyntax(code);
                    break;
                case "c":
                    checker.checkCSyntax(code);
                    break;
                case "cpp":
                    checker.checkCPPSyntax(code);
                    break;
                case "csv":
                    checker.checkCSVSyntax(code);
                    break;
                case "cs":
                    checker.checkCSharpSyntax(code);
                    break;
                case "json":
                    checker.checkJSONSyntax(code);
                    break;
                case "sql":
                    checker.checkSQLSyntax(code);
                    break;
                case "go":
                    checker.checkgoSyntax(code);
                    break;
                case "xml":
                case "iml":
                    checker.checkxmlSyntax(code);
                    break;
                case "php":
                    checker.checkphpSyntax(code);
                    break;
                case "rs":
                    checker.checkrustSyntax(code);
                    break;
                case "dart":
                    checker.checkdartSyntax(code);
                    break;
                case "kt":
                    checker.checkkotlinSyntax(code);
                    break;
                default:
                	checkMD();
                    break;
            }
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }
    
}
