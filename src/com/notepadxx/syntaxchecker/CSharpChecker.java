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

import com.notepadxx.antlr.CSharp.CSharpLexer;
import com.notepadxx.antlr.CSharp.CSharpParser;
import com.notepadxx.notepadxx.Texteditor;

public class CSharpChecker {

	 private Texteditor editor;
	    private boolean advancedMode;
	    private boolean tooltip;
	    private hightlightline hightlight = new hightlightline();

	    public CSharpChecker(Texteditor editor) {
	        this.editor = editor;
	        this.advancedMode = false;
	        this.tooltip = false;
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
	             CSharpSyntax(editor.getTextContent()); // Recheck syntax in updated mode
	         });
	    }

    protected synchronized void CSharpSyntax(String code) {
    	 SwingWorker<List<syntaxError>, Void> worker = new SwingWorker<>() {
	            @Override
	            protected List<syntaxError> doInBackground() throws Exception {
	                CharStream charStream = CharStreams.fromString(code);
	                CSharpLexer lexer = new CSharpLexer(charStream);
	                CommonTokenStream tokens = new CommonTokenStream(lexer);
	                CSharpParser parser = new CSharpParser(tokens);

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
	                        syntaxErrors.add(new syntaxError(line, charPositionInLine, "Parser Error: " + msg));
	                    }
	                });

	                // Start parsing
	                parser.compilation_unit();

	                return syntaxErrors; // Return collected errors
	            }

	            @Override
	            protected void done() {
	                try {
	                    List<syntaxError> syntaxErrors = get(); // Get results from background thread

	                    // Clear existing highlights on EDT
	                   // SwingUtilities.invokeLater(() -> editor.getTextArea().getHighlighter().removeAllHighlights());

	                    SwingUtilities.invokeLater(() -> {
	                        String errorMessage = hightlight.ErrorForCode1(syntaxErrors, editor, advancedMode, tooltip,"CSharp");
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
