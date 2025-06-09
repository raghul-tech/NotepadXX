package com.notepadxx.rsyntaxtextarea.syntaxhightlighter;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.TokenTypes;

public class SyntaxHighlighter { 
    private RSyntaxTextArea textArea;
    private Timer highlightTimer; // Timer for debouncing
    private boolean isSaved; // Flag to track if the file is saved
    private boolean isHighlighting; // Flag to prevent changes during highlighting
    private String fileExtension;
   private SyntaxScheme scheme;
    private Font defaultFont = new Font("Monospaced", Font.PLAIN, 18);
   // private Font defaultFont = new Font("Apple Color", Font.PLAIN, 18);
   // private Font defaultFont = new Font("Courier New", Font.PLAIN, 18);
    private DocumentListener documentListener;



    public SyntaxHighlighter(RSyntaxTextArea textArea) {
        this.textArea = textArea ;
        this.isSaved = false; // Initially, the file is considered unsaved
        this.isHighlighting = false; // Initially, not highlighting
        this.fileExtension = "txt"; // Default to a common file type
        this.scheme = textArea.getSyntaxScheme();
        setupDocumentListener();
    }

    public synchronized void setSaved(boolean saved) {
        isSaved = saved; // Update the saved state
        if (isSaved) {
            highlightSyntax(); // If saved, highlight the text
        } else {
            clearHighlights(); // If not saved, clear any highlights
        }
    }
  
    public void setFileExtension(String fileExtension) {
    	if(isScheme(fileExtension))scheme.restoreDefaults(defaultFont);
        if (fileExtension != null && !fileExtension.isEmpty()) {
            this.fileExtension = fileExtension.toLowerCase(); // Store file extension for highlighting
       
        }
      
        }


    private void setupDocumentListener() {

      //  textArea .getDocument().addDocumentListener(new DocumentListener() {
    	 documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleChange();
            }
      //  });
    };
    textArea.getDocument().addDocumentListener(documentListener);
    }
    
    public void removeSyntaxHightlighterDoc() {
    	textArea.getDocument().removeDocumentListener(documentListener);
    }
   
    private synchronized void handleChange() {
        // Start the highlight timer
        if (highlightTimer != null) {// && highlightTimer.isRunning()) {
            highlightTimer.restart(); // Restart timer if already running
           //highlightTimer.stop();
          //  System.out.println("Timer Stopped...");
        } else {
            highlightTimer = new Timer(500, e -> {
                highlightTimer.stop(); // Stop the timer
                highlightSyntax(); // Highlight syntax after delay
               // System.out.println("hightlighting Syntax... "+highlightTimer.getInitialDelay());
               
            });
            highlightTimer.setRepeats(false); // Only execute once
            highlightTimer.start(); // Start the timer
            //highlightTimer.stop();
        }
        highlightTimer.stop(); // Start the timer
        //System.out.println("Timer Started...");
    }

    private synchronized void highlightSyntax() {
    	    if (isHighlighting)
			 {
				return; // Avoid recursive calls
			}

    	    isHighlighting = true; // Set the highlighting flag

    	    String text = textArea.getText();

    	    // Check if the text is empty or whitespace
    	    if (text.trim().isEmpty()) {
    	        // If there is no content, exit without highlighting
    	        isHighlighting = false; // Reset the flag
    	        return; // No text to highlight
    	    }

    	    // Enable built-in syntax highlighting based on the current syntax style
    	    updateKeywordColorMap(); // Ensure the syntax highlighting style is set based on the file type
    	   

    	    isHighlighting = false; // Reset the highlighting flag
    	}

    private void clearHighlights() {
    	// Reset the syntax editing style to none
        textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        // Optionally, you can also set a default font if needed
        textArea .setFont(defaultFont); // Set to default font
    }
    
    private boolean isScheme(String ext) {
    	switch (ext) {
    	 case "html":
         case "htm":
         case"php":
         case "phps":
         case "hbs":
         case "handlebars":
        	 return true;
        default:
        	return false;
    	}
    		
    }

    private void updateKeywordColorMap() {
    //	 SyntaxScheme scheme = textArea .getSyntaxScheme();
    	  //System.out.println("Syntax hightlight is  "+fileExtension);
    	if(textArea == null)return;
    	try {
        switch (fileExtension) {

        case "html":
        case "htm":

            // Set the syntax style for HTML (standard HTML highlighting)
            textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
          
            scheme.getStyle(TokenTypes.RESERVED_WORD).foreground = Color.BLUE;  // Remove color for reserved words (JS)
            scheme.getStyle(TokenTypes.OPERATOR).foreground = Color.RED;  // Remove color for operators
           scheme.getStyle(TokenTypes.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.ORANGE;  // Remove color for strings
            scheme.getStyle(TokenTypes.LITERAL_BACKQUOTE).foreground = Color.PINK;
            scheme.getStyle(TokenTypes.FUNCTION).foreground = Color.CYAN;
            scheme.getStyle(TokenTypes.IDENTIFIER).foreground = Color.MAGENTA;
            //scheme.getStyle(Token.SEPARATOR).foreground = null; // For symbols like ;, :, etc.
            // Apply the modified scheme
           textArea .setSyntaxScheme(scheme);
         
           //textArea .setFont(defaultFont);


            break;

            case "css":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
            	break;

            case "js":
            case "mjs":
            case "cjs":
                textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);

                break;

             case "java":
                 textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                 break;

            case "py":
            case "pyw":
            case "pyd":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
            	break;

            case"ipynb":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
            	break;
            	
            case "c":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);

            	break;

            case "cpp":
            case "hpp":
            case "h":
            case "cxx":
            case "cc":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
            	 break;

            case "cs":
                 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
               	 break;

            case "xml":
            case "iml":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
                 break;

            case "yml":
            case "yaml":
           	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_YAML);
                break;

            case "json":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON_WITH_COMMENTS);

            	break;

            case "md":
            case "markdown":
            case "mdown":
            case "mkd":
            case "mkdn":
            case "mdtext":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MARKDOWN);

            	break;

            case"php":
            case "phps":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
            	 scheme.getStyle(TokenTypes.RESERVED_WORD).foreground = Color.BLUE;  // Remove color for reserved words (JS)
                 scheme.getStyle(TokenTypes.OPERATOR).foreground = Color.RED;  // Remove color for operators
                scheme.getStyle(TokenTypes.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.ORANGE;  // Remove color for strings
                 scheme.getStyle(TokenTypes.LITERAL_BACKQUOTE).foreground = Color.PINK;
                // scheme.getStyle(Token.FUNCTION).foreground = Color.CYAN;
                 scheme.getStyle(TokenTypes.IDENTIFIER).foreground = Color.MAGENTA;
                 //scheme.getStyle(Token.SEPARATOR).foreground = null; // For symbols like ;, :, etc.
                 // Apply the modified scheme
                textArea .setSyntaxScheme(scheme);

            	break;

            case "sql":
                textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
                break;

            case "sh":
            case "bash":
            case "zsh":
            case "csh":
            	textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL);

              break;

            case "rb":
            	// Ruby Keywords
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);

            	break;

          /*  case "swift":

            	textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SWIFT);

            	break;*/

            case "groovy":
                 
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);  
                break;

            case "scala":
                 
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SCALA);  
                break;

             case "tex":
             case "sty":
             case "dtx":
             case "bib":
                     
               	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);  
                   break;

             case "as":
                  
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT);  
                break;

             case "bbcode":
                  
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_BBCODE);  
                break;

             case "asm":
             case "s": 
             case "a51":
             case "a":
             case "lst":
            	 // For Assembly (x86)
                 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
              break;

             case "a65":
             case "inc":
                 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_6502);
                 break;

             case "csv":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSV);
                break;

             case "d":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_D);
                break;

             case "dart":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DART);
                break;

             case "pas":
             case "dpr":
             case "dfr":
             case "dpm":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DELPHI);
                break;

             case "dockerfile":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DOCKERFILE);
                break;

             case "dtd":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DTD);
                break;

             case "clj":
             case "cljs":
             case "cljc":
             case "clojure":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CLOJURE);
                break;

             case "f":
             case "for":
             case "f90":
             case "f95":
             case "f77":
             case "f03":
             case "f08":
             case "f18":
             case "mod":
             case "ftn":
             case "fpp":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_FORTRAN);
                break;

             case "go":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GO);
                break;

             case "hbs":
             case "handlebars":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HANDLEBARS);
          
            	 scheme.getStyle(TokenTypes.RESERVED_WORD).foreground = Color.BLUE;  // Remove color for reserved words (JS)
                 scheme.getStyle(TokenTypes.OPERATOR).foreground = Color.RED;  // Remove color for operators
                scheme.getStyle(TokenTypes.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.ORANGE;  // Remove color for strings
                 scheme.getStyle(TokenTypes.LITERAL_BACKQUOTE).foreground = Color.PINK;
                // scheme.getStyle(Token.FUNCTION).foreground = Color.CYAN;
                 scheme.getStyle(TokenTypes.IDENTIFIER).foreground = Color.MAGENTA;
                 //scheme.getStyle(Token.SEPARATOR).foreground = null; // For symbols like ;, :, etc.
                 // Apply the modified scheme
                textArea .setSyntaxScheme(scheme);
            	 break;

             case "hosts":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HOSTS);
                break;

             case "htaccess":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTACCESS);
                break;

             case "ini":
             case "cfg":
             case "conf":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_INI);
                break;

             case "jsp":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSP);
                break;

             case "kt":
             case "kts":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_KOTLIN);
                break;

             case "less":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LESS);
                break;

             case "lisp":
             case "lsp":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LISP);
                break;

             case "lua":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
                break;

             case "makefile":
             case "Makefile":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MAKEFILE);
                break;

             case "mxml":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MXML);
                break;

             case "nsi":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NSIS);
                break;

             case "pl":
             case "pm":
             case "t":
             case "cgi":
             case "perl":
             case "plx":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PERL);
                break;

             case "properties":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE);
                break;

             case "proto":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PROTO);
                break;

             case "rs":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUST);
                break;

             case "sas":
             case "sas7bdat":
             case "sas7bvew":
             case "sas7bcat":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SAS);
                break;

             case "tcl":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_TCL);
                break;

             case "ts":
             case "tsx":
             case "jsx":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT);
                break;

             case "vb":
             case "vbs":
             case "bas":
             case "frm":
             case "cls":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC);
                break;

             case "bat":
             case "cmd":
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH);
                break;


           case "txt":
                        
                  	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
                      break;

            default:
                // Optionally, handle other file types
            	 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
            		 //textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_VHDL);
            	
            	break;
        }
    	}catch(Error e) {
    		 textArea .setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
    		
    	}
       // textArea .revalidate();
     //   textArea .repaint();
    }

}