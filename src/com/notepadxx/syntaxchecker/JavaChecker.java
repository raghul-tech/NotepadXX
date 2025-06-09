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

import com.notepadxx.antlr.java20.Java20Lexer;
import com.notepadxx.antlr.java20.Java20Parser;
import com.notepadxx.notepadxx.Texteditor;

public class JavaChecker {

    private Texteditor editor;
    private boolean advancedMode;
    private boolean tooltip;
    private hightlightline hightlight = new hightlightline();

    public JavaChecker(Texteditor editor) {
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
        SwingUtilities.invokeLater(() -> JavaSyntax(editor.getTextContent())); // Directly pass content
    }

    protected void JavaSyntax(String code) {
        if (code == null || code.trim().isEmpty()) return; // Prevent unnecessary execution

        SwingWorker<List<syntaxError>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<syntaxError> doInBackground() throws Exception {
                CharStream charStream = CharStreams.fromString(code);
                Java20Lexer lexer = new Java20Lexer(charStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                Java20Parser parser = new Java20Parser(tokens);

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


                parser.start_(); // Start parsing

                return syntaxErrors;
            }

            @Override
            protected void done() {
                try {
                    List<syntaxError> syntaxErrors = get();

                    SwingUtilities.invokeLater(() -> {
                        if (!syntaxErrors.isEmpty()) {
                            String errorMessage = hightlight.ErrorForCode1(syntaxErrors, editor, advancedMode, tooltip, "Java");

                            // Ensure errorMessage is not null
                            if (errorMessage != null && !errorMessage.isBlank()) {
                                editor.getTextArea().setToolTipText(errorMessage);
                            } else {
                                editor.getTextArea().setToolTipText(null); // Remove tooltip if empty or null
                            }
                        } else {
                            editor.getTextArea().setToolTipText(null);
                        }
                    });

                } catch (Exception e) {
                    
                }
            }
        };

        worker.execute();
        
    }
}
