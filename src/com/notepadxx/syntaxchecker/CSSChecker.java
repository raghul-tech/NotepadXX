package com.notepadxx.syntaxchecker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import com.notepadxx.antlr.css3.css3Lexer;
import com.notepadxx.antlr.css3.css3Parser;
import com.notepadxx.notepadxx.Texteditor;

public class CSSChecker {

	private Texteditor editor;
	private boolean advancedMode;
	private hightlightline hightlight = new hightlightline();
	//private int varline = -1;
	private boolean tooltip;
    public CSSChecker(Texteditor editor) {
        this.editor = editor;
        this.advancedMode = false; // Default to simplified messages
    }

    public void setAdvancedMode(boolean advancedMode) {
        this.advancedMode = advancedMode;
        checkAgain();
        SyntaxChecker.updateUI(editor);
}

public void setToolTip(boolean tooltip) {
    this.tooltip = tooltip;
   checkAgain();
   SyntaxChecker.updateUI(editor);
}

private void checkAgain() {
	 SwingUtilities.invokeLater(() -> {
		// StringBuilder currentCode = new StringBuilder(editor.getTextContent());
         //String currentCode = editor.getTextArea().getText();
         CSSSyntax(editor.getTextContent()); // Recheck syntax in updated mode
     });
}
    protected void CSSSyntax(String code) {
    	SwingWorker <List<syntaxError>,Void> worker = new SwingWorker<>() {
       
			@Override
			protected List<syntaxError> doInBackground() throws Exception {
				// TODO Auto-generated method stub
				 CharStream charStream = CharStreams.fromString(code);
			        css3Lexer lexer = new css3Lexer(charStream);
			        CommonTokenStream tokens = new CommonTokenStream(lexer);
			        css3Parser parser = new css3Parser(tokens);

			        // Clear existing highlights
			        SwingUtilities.invokeLater(() -> editor.getTextArea().getHighlighter().removeAllHighlights());

			        parser.removeErrorListeners();
			        lexer.removeErrorListeners();

			        List<syntaxError> syntaxErrors = new ArrayList<>();

			        lexer.addErrorListener(new BaseErrorListener() {
			            @Override
			            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
			                                     int line, int charPositionInLine, String msg, RecognitionException e) {
			                syntaxErrors.add(new syntaxError(line, charPositionInLine, "Lexer Error: " + msg));
			            }
			        });
			            parser.addErrorListener(new BaseErrorListener() {
			                @Override
			                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
			                                         int line, int charPositionInLine, String msg, RecognitionException e) {
			                	 /*if (msg.contains("var")|| isVarInLine(line) ) { // Check if the error relates to 'var'
			                         varline = line;
			                         // i want to store which line has the var in the text content
			                		 // syntaxErrors.add(new syntaxError(line, charPositionInLine, "Parser Error: Likely an issue with 'var()' parsing. Check grammar."));
			                      } else {
			                    	  if (line != varline) {
			                              syntaxErrors.add(new syntaxError(line, charPositionInLine, "Parser Error: " + msg));
			                          }
			                         	 }*/
			                	 syntaxErrors.add(new syntaxError(line, charPositionInLine, "Parser Error: " + msg));
			                }
			            /*    private boolean isVarInLine(int line) {
			                    String currentCode = editor.getTextContent();
			                    // Split the code into lines
			                    String[] lines = currentCode.split("\n");

			                    // Ensure that the line number is within the bounds of the text
			                    if (line - 1 >= 0 && line - 1 < lines.length) {
			                        return lines[line - 1].contains("var");
			                    }

			                    return false;
			                }*/
			            });
			            parser.stylesheet();
			            
				return syntaxErrors;
			}
       
			 @Override
	            protected void done() {
	                try {
	                    List<syntaxError> syntaxErrors = get(); // Get results from background thread

	                    // Clear existing highlights on EDT
	                   // SwingUtilities.invokeLater(() -> editor.getTextArea().getHighlighter().removeAllHighlights());

	                    SwingUtilities.invokeLater(() -> {
	                        String errorMessage = hightlight.ErrorForCode1(syntaxErrors, editor, advancedMode, tooltip,"CSS");
	                        editor.getTextArea().setToolTipText(errorMessage);
	                    });
	                } catch (Exception e) {
	                   // e.printStackTrace();
	                }
	            }
	        };

	        worker.execute(); // Start the background task
	    }

}
