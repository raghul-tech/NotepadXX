package com.notepadxx.flexmark;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.notepadxx.notepadxx.Texteditor;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gfm.users.GfmUsersExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import netscape.javascript.JSObject;

public class MarkdownPreviewWindow {
    private static volatile boolean javaFXInitialized = false;
    private ExecutorService executor;
    private Stage previewStage;
    private WebEngine engine;
    private Texteditor sourceEditor;
    private javax.swing.Timer updateTimer;
    private volatile boolean disposed = false;
    private static final String LOGO_PATH = "/icons/NotepadXXLogo.png"; 
    private final CountDownLatch cleanupLatch = new CountDownLatch(1);
     
    public MarkdownPreviewWindow(Texteditor sourceEditor) {
        this.sourceEditor = sourceEditor; 
        try {
        initializeJavaFX(); 
        createExecutor();
        setupUpdateTimer();
        createAndShowPreviewWindow();
        } catch (Exception e) {
            dispose(); // Clean up if initialization fails
            throw e;
        }
    }

    private synchronized void createExecutor() {
        if (executor == null || executor.isShutdown()) {
            executor = Executors.newSingleThreadExecutor(r -> {
                Thread t = new Thread(r, "MarkdownPreviewWindow-" + hashCode());
                t.setDaemon(true);
                return t;
            });
        }
    }
    private void initializeJavaFX() {
        if (!javaFXInitialized) {
            try {
            	 if (Platform.isFxApplicationThread()) {
 		            Platform.exit();
 		        }
                Platform.startup(() -> {
                    Platform.setImplicitExit(false);
                    javaFXInitialized = true;
                });
            } catch (IllegalStateException e) {
                // JavaFX is already running
                Platform.runLater(() -> {});
                javaFXInitialized = true;
            }
        }
    }


   /* private void initializeJavaFX1() {
        if (!javaFXInitialized) {
          //  synchronized (MarkdownPreviewTab.class) {
               // if (!javaFXInitialized) {
                    try {
                       /* Platform.startup(() -> {
                            Platform.setImplicitExit(false);
                            javaFXInitialized = true;
                        });*/
                    /*	 Platform.runLater(() -> {});
                         javaFXInitialized = true;*/
                //    } catch (IllegalStateException e) {
                       /* if (!e.getMessage().contains("Toolkit already running")) {
                            throw new RuntimeException("Failed to initialize JavaFX", e);
                        }
                        javaFXInitialized = true;*/
                    	/*  Platform.startup(() -> {
                              Platform.setImplicitExit(false);
                              javaFXInitialized = true;
                          });
                    }
              //  }
         //   }
        }
    }*/

    private void setupUpdateTimer() {
        updateTimer = new javax.swing.Timer(700, e -> {
            if (!disposed && sourceEditor != null) {
                updatePreviewContent();
            }
        });
        updateTimer.setRepeats(false);
    }

    private void createAndShowPreviewWindow() {
        Platform.runLater(() -> {
            try {
            	  if (disposed) return;
            	  
                previewStage = new Stage();
                previewStage.setTitle("Markdown Preview: " + sourceEditor.getTabTitle());
                
                try {
                    InputStream logoStream = getClass().getResourceAsStream(LOGO_PATH);
                    if (logoStream != null) {
                        Image logo = new Image(logoStream);
                        previewStage.getIcons().add(logo);
                    }
                } catch (Exception e) {
                 //   System.err.println("Could not load logo: " + e.getMessage());
                }
                
                WebView webView = new WebView();
                engine = webView.getEngine();
                
                // Add window listener for close event
                previewStage.setOnCloseRequest(event -> {
                    if (!disposed) {
                        dispose();
                    }
                });
                
                
                JavaLinkHandler linkHandler = new JavaLinkHandler(this::handleExternalLink);
                engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                    if (newState == Worker.State.SUCCEEDED && !disposed) {
                        try {
                            JSObject window = (JSObject) engine.executeScript("window");
                            window.setMember("javaBridge", linkHandler);
                            
                            engine.executeScript(
                                "document.addEventListener('click', function(e) {" +
                                "   var target = e.target.closest('a');" +
                                "   if (target) {" +
                                "       e.preventDefault();" +
                                "       window.javaBridge.handleLink(target.href);" +
                                "   }" +
                                "});"
                            );
                        } catch (Exception e) {
                        	if(!disposed) {
                        //    System.err.println("Error setting up JavaScript bridge: " + e.getMessage());
                        	}
                        	}
                    }
                });
                
                engine.locationProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal == null || newVal.isEmpty() || newVal.startsWith("data:text/html")) return;
                    Platform.runLater(() -> {
                    	if(!disposed && engine != null) {
                        engine.load(null);
                        handleExternalLink(newVal);
                    	}
                    });
                });
                
                engine.setJavaScriptEnabled(true);
                
                Scene scene = new Scene(webView, 800, 600);
                previewStage.setScene(scene);
                
                previewStage.setOnShown(e -> {
                	   if (!disposed) {
                    updatePreviewContent();
                    attachDocumentListener();
                	   }
                });
                
                previewStage.setOnCloseRequest(this::handleWindowClose);
                
                previewStage.show();
                
            } catch (Exception e) {
            	 if (!disposed) {
                     showErrorDialog("Failed to create preview window", e);
                 }
            }
        });
    }

    private void attachDocumentListener() {
        sourceEditor.getTextArea().getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { triggerPreviewUpdate(); }
            @Override public void removeUpdate(DocumentEvent e) { triggerPreviewUpdate(); }
            @Override public void changedUpdate(DocumentEvent e) { triggerPreviewUpdate(); }
        });
    }

    private void triggerPreviewUpdate() {
        if (!disposed && updateTimer != null) {
            updateTimer.restart();
        }
    }

  /*  private void updatePreviewContent() {
        if (disposed || engine == null) return;
        
        String markdown = sourceEditor.getTextArea().getText();
        File currentFile = sourceEditor.getCurrentFile();
        String html = renderMarkdown(markdown);
        String styledHtml = getStyledHtml(html, currentFile);
        
        Platform.runLater(() -> {
            try {
                if (!disposed && engine != null) {
                    engine.loadContent(styledHtml);
                }
            } catch (Exception e) {
                if (!disposed) {
                    System.err.println("Error updating preview content: " + e.getMessage());
                }
            }
        });
    }*/
    private void updatePreviewContent() {
        if (disposed || engine == null) return;

        String markdown = sourceEditor.getTextArea().getText();
        File currentFile = sourceEditor.getCurrentFile(); 
        String html = renderMarkdown(markdown);
        String styledHtml = getStyledHtml(html, currentFile);

        // Wrap content in a <div id="content"> so we can replace it dynamically
        String fullHtml = "<html><head><meta charset='UTF-8'></head><body><div id='content'>"
                          + styledHtml + "</div></body></html>";

        Platform.runLater(() -> {
            if (disposed || engine == null) return;

            // On first load, use loadContent
            if (engine.getDocument() == null) {
                engine.loadContent(fullHtml);
                return;
            }

            // Otherwise, update the content dynamically
            String encoded = Base64.getEncoder().encodeToString(styledHtml.getBytes());
            engine.executeScript(
                "var content = document.getElementById('content');" +
                "if (content) {" +
                "  var scrollY = window.scrollY;" +
                "  content.innerHTML = atob('" + encoded + "');" +
                "  window.scrollTo(0, scrollY);" +
                "}"
            );
        });
    }


    private void handleWindowClose(WindowEvent event) {
        dispose();
    }

    private void handleExternalLink(String url) {
        if (disposed) return;
        
        try {
            if (executor == null || executor.isShutdown()) {
                createExecutor();
            }
            
            executor.execute(() -> {
                if (disposed) return;
                
                try {
                    URI uri = new URI(url);
                    String scheme = uri.getScheme().toLowerCase();

                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        
                        switch (scheme) {
                            case "mailto":
                                handleMailToLink(uri);
                                break;
                            case "http":
                            case "https":
                                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                    desktop.browse(uri);
                                }
                                break;
                            case "file":
                                handleFileLink(uri);
                                break;
                            default:
                               // System.err.println("Unsupported scheme: " + scheme);
                        }
                    }
                } catch (Exception e) {
                    if (!disposed) {
                        SwingUtilities.invokeLater(() -> 
                            showErrorDialog("Error handling link", e)
                        );
                    }
                }
            });
        } catch (Exception e) {
            if (!disposed) {
                //System.err.println("Error submitting link handling task: " + e.getMessage());
            }
        }
    }

    private void handleMailToLink(URI uri) throws Exception {
        if (Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
            String mailTo = uri.toString()
                .replace(" ", "%20")
                .replace("+", "%2B");
            Desktop.getDesktop().mail(new URI(mailTo));
        } else {
            throw new UnsupportedOperationException("Mailto not supported");
        }
    }

    private void handleFileLink(URI uri) throws Exception {
        File file = new File(uri);
        if (file.exists()) {
            if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(file);
            } else {
                Desktop.getDesktop().browse(uri);
            }
        } else {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
    }
    


    private String renderMarkdown(String markdown) {
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(
            TablesExtension.create(),
            TaskListExtension.create(),
            StrikethroughExtension.create(),
            GfmUsersExtension.create()
        ));
        
        options.set(HtmlRenderer.SOFT_BREAK, "<br />");
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        
        String html = renderer.render(parser.parse(markdown));
        return processAllEmojis(html);
    }
    
    private String processAllEmojis(String html) {
        // This pattern matches all known emoji ranges in Unicode
    	Pattern emojiPattern = Pattern.compile(
    		    "[\u00A9\u00AE\u203C\u2049\u2122\u2139\u2194-\u2199\u21A9\u21AA\u231A\u231B\u2328\u23CF" +
    		    "\u23E9-\u23F3\u23F8-\u23FA\u24C2\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE\u2600-\u2604\u260E" +
    		    "\u2611\u2614\u2615\u2618\u261D\u2620\u2622\u2623\u2626\u262A\u262E\u262F\u2638-\u263A\u2640" +
    		    "\u2642\u2648-\u2653\u265F\u2660\u2663\u2665\u2666\u2668\u267B\u267E\u267F\u2692-\u2697\u2699" +
    		    "\u269B\u269C\u26A0\u26A1\u26AA\u26AB\u26B0\u26B1\u26BD\u26BE\u26C4\u26C5\u26C8\u26CE\u26CF" +
    		    "\u26D1\u26D3\u26D4\u26E9\u26EA\u26F0-\u26F5\u26F7-\u26FA\u26FD\u2702\u2705\u2708-\u270D" +
    		    "\u270F\u2712\u2714\u2716\u271D\u2721\u2728\u2733\u2734\u2744\u2747\u274C\u274E\u2753-\u2755" +
    		    "\u2757\u2763\u2764\u2795-\u2797\u27A1\u27B0\u27BF\u2934\u2935\u2B05-\u2B07\u2B1B\u2B1C\u2B50" +
    		    "\u2B55\u3030\u303D\u3297\u3299" +

    		    // Regional Indicators & Enclosed
    		    "\uD83C\uDC04\uD83C\uDD70\uD83C\uDD71\uD83C\uDD7E\uD83C\uDD7F\uD83C\uDD8E\uD83C\uDD92-\uD83C\uDD9A" +
    		    "\uD83C\uDDE6-\uD83C\uDDFF\uD83C\uDE01\uD83C\uDE02\uD83C\uDE1A\uD83C\uDE2F\uD83C\uDE32-\uD83C\uDE3A" +
    		    "\uD83C\uDE50\uD83C\uDE51" +

    		    // Misc symbols & pictographs
    		    "\uD83C\uDF00-\uD83C\uDFFF" +

    		    // Emoticons
    		    "\uD83D\uDC00-\uD83D\uDDFF" +

    		    // Transport, faces, hands, people
    		    "\uD83D\uDE00-\uD83D\uDE4F\uD83D\uDE80-\uD83D\uDEFF" +

    		    // Supplemental symbols (Unicode 9+)
    		    "\uD83E\uDD00-\uD83E\uDDFF" +

    		    // Unicode 13+ emojis (including 🪟 U+1FA9F)
    		    "\uD83E\uDE70-\uD83E\uDE74\uD83E\uDE78-\uD83E\uDE7A\uD83E\uDE80-\uD83E\uDE86" +
    		    "\uD83E\uDE90-\uD83E\uDE95\uD83E\uDEA0-\uD83E\uDEA5\uD83E\uDEB0-\uD83E\uDEB6" +
    		    "\uD83E\uDEB7-\uD83E\uDEB8\uD83E\uDEB9-\uD83E\uDEBC\uD83E\uDEBD-\uD83E\uDEC0" +

    		    // Windows 11 emoji: 🪟 (U+1FA9F)
    		    "\uD83E\uDE9F" +

    		    // Supplemental Symbols and Pictographs U+1FA00–U+1FAFF (many new emojis live here)
    		    "\uD83E\uDE00-\uD83E\uDEFF" +

    		    "]",
    		    Pattern.UNICODE_CHARACTER_CLASS
    		);

        Matcher matcher = emojiPattern.matcher(html);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String emoji = matcher.group();
            String code = toCodePoint(emoji);
            String replacement = String.format(
                "<span class=\"emoji-container\">" +
                "<img class=\"emoji\" alt=\"%s\" src=\"https://github.githubassets.com/images/icons/emoji/unicode/%s.png\" " +
                "onerror=\"this.style.display='none'; this.nextElementSibling.style.display='inline'\">" +
                "<span class=\"emoji-fallback\" style=\"display:none\">%s</span>" +
                "</span>",
                emoji, code, emoji
            );
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);
        
        return result.toString();
    }

    private String toCodePoint(String emoji) {
        if (emoji.codePointCount(0, emoji.length()) == 1) {
            return Integer.toHexString(emoji.codePointAt(0));
        }
        
        // Handle surrogate pairs
        StringBuilder codePoints = new StringBuilder();
        int offset = 0;
        while (offset < emoji.length()) {
            int codePoint = emoji.codePointAt(offset);
            codePoints.append(Integer.toHexString(codePoint));
            if (codePoint > 0xFFFF) {
                offset += 2;
            } else {
                offset++;
            }
            if (offset < emoji.length()) {
                codePoints.append("-");
            }
        }
        return codePoints.toString();
    }

    private String getStyledHtml(String content, File mdFile) {
        String basePath = getBasePathForFile(mdFile);
        if (basePath == null) basePath = "";

    //    Color fg = UIManager.getColor("Label.foreground");
      //  Color bg = UIManager.getColor("Panel.background");
     //   boolean isDarkMode = (fg != null && bg != null) && (calculateLuminance(fg) > calculateLuminance(bg));
        boolean isDarkMode = false;
        
        String cssColors = String.format("""
            :root {
                --bg-color: %s;
                --text-color: %s;
                --table-border: %s;
                --table-header-bg: %s;
                --code-bg: %s;
                --pre-border: %s;
                --blockquote-border: %s;
                --hr-color: %s;
                --link-color: %s;
            }
            """,
            isDarkMode ? "#2b2b2b" : "#ffffff",
            isDarkMode ? "#bbbbbb" : "#333333",
            isDarkMode ? "#555555" : "#cccccc",
            isDarkMode ? "#3c3f41" : "#f2f2f2",
            isDarkMode ? "#4a4a4a" : "#e8e8e8",
            isDarkMode ? "#555555" : "#dddddd",
            isDarkMode ? "#4a9cff" : "#0366d6",
            isDarkMode ? "#444" : "#eee",
            isDarkMode ? "#58a6ff" : "#0366d6"
        );

        String mdStyles = """
            body {
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
                line-height: 1.6;
                padding: 20px;
                max-width: 800px;
                margin: 0 auto;
            }
            
            h1, h2, h3, h4, h5, h6 {
                margin: 1.2em 0 0.6em;
                font-weight: 600;
                line-height: 1.25;
            }
            
            h1 { font-size: 2em; border-bottom: 1px solid var(--table-border); }
            h2 { font-size: 1.5em; }
            h3 { font-size: 1.25em; }
            
            p { margin: 1em 0; }
            
            a { color: var(--link-color); text-decoration: none; }
            a:hover { text-decoration: underline; }
            
            ul, ol { padding-left: 2em; }
            li { margin: 0.4em 0; }
            li > p { margin: 0.4em 0; }
            
            blockquote {
                margin: 1em 0;
                padding: 0 1em;
                color: var(--text-color);
                border-left: 4px solid var(--blockquote-border);
                background-color: rgba(0,0,0,0.05);
            }
            
            pre {
                background: var(--code-bg);
                border: 1px solid var(--pre-border);
                border-radius: 6px;
                padding: 16px;
                overflow: auto;
                line-height: 1.45;
                margin: 1em 0;
            }
            
            code {
                font-family: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, monospace;
                background: var(--code-bg);
                padding: 0.2em 0.4em;
                border-radius: 3px;
                font-size: 0.9em;
            }
            
            table {
                border-collapse: collapse;
                margin: 1.5em 0;
                width: 100%;
                display: block;
                overflow-x: auto;
            }
            
            th, td {
                border: 1px solid var(--table-border);
                padding: 8px 12px;
                text-align: left;
            }
            
            th {
                background-color: var(--table-header-bg);
                font-weight: 600;
            }
            
            tr:nth-child(even) { background-color: rgba(0,0,0,0.03); }
            
            hr {
                border: 0;
                height: 1px;
                background-color: var(--hr-color);
                margin: 2em 0;
            }
            
            img { max-width: 100%; }
             .emoji-container {
    display: inline-block;
    vertical-align: middle;
    line-height: 1;
}

.emoji {
    height: 1.2em;
    width: 1.2em;
    vertical-align: middle;
    margin: 0 0.05em;
}

.emoji-fallback {
    font-family: "Apple Color Emoji", "Segoe UI Emoji", "Noto Color Emoji", sans-serif;
}
            """;

        String fontCss = """
        	    @font-face {
        	        font-family: 'Twemoji Mozilla';
        	        src: local('Apple Color Emoji'), 
        	             local('Segoe UI Emoji'), 
        	             local('Segoe UI Symbol'),
        	             local('Noto Color Emoji');
        	        unicode-range: U+1F300-1F5FF, U+1F600-1F64F, U+1F680-1F6FF,
        	                     U+2600-26FF, U+2700-27BF, U+FE00-FE0F, U+1F900-1F9FF;
        	    }
        	    body, body * {
        	        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, 
        	                    Helvetica, Arial, sans-serif, "Twemoji Mozilla";
        	    }
        	    """;

        return String.format("""
            <!DOCTYPE html>
            <html>
              <head>
                <meta charset="UTF-8">
                <base href="%s">
                <style>
                    %s
                    %s
                    %s
                </style>
              </head>
              <body>%s</body>
            </html>
            """, basePath, cssColors, mdStyles, fontCss, content);
    }

    private String getBasePathForFile(File mdFile) {
        if (mdFile != null && mdFile.getParentFile() != null) {
            try {
                return mdFile.getParentFile().toURI().toString();
            } catch (Exception e) {
            //    System.err.println("Error creating base path URI: " + e.getMessage());
            }
        }
        return null;
    }
    
  /*  private double calculateLuminance(Color color) {
        if (color == null) return 0.5;
        double r = color.getRed() / 255.0, g = color.getGreen() / 255.0, b = color.getBlue() / 255.0;
        r = (r <= 0.03928) ? r/12.92 : Math.pow((r+0.055)/1.055, 2.4);
        g = (g <= 0.03928) ? g/12.92 : Math.pow((g+0.055)/1.055, 2.4);
        b = (b <= 0.03928) ? b/12.92 : Math.pow((b+0.055)/1.055, 2.4);
        return 0.2126*r + 0.7152*g + 0.0722*b;
    }*/

    private void showErrorDialog(String message, Exception e) {
        if (disposed) return;
        
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null,
                message + ":\n" + e.getClass().getSimpleName() + ": " + e.getMessage(),
                "Markdown Preview Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    public synchronized void dispose() {
        if (disposed) return;
        disposed = true;
        
        // Stop any pending updates
        if (updateTimer != null && updateTimer.isRunning()) {
            updateTimer.stop();
        }
        
        // Shutdown executor
        if (executor != null) {
            executor.shutdownNow();
        }
        
        // Clean up JavaFX resources
        if (Platform.isFxApplicationThread()) {
            cleanupFxResources();
        } else {
          
            Platform.runLater(() -> {
                try {
                    cleanupFxResources();
                } finally {
                    cleanupLatch.countDown();
                }
            });
            try {
                cleanupLatch.await(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void cleanupFxResources() {
        try {
            if (engine != null) {
                engine.load(null);
            }
        } catch (Exception e) {
           // System.err.println("Error cleaning up WebEngine: " + e.getMessage());
        }
        
        try {
            if (previewStage != null) {
                previewStage.close();
            }
        } catch (Exception e) {
          //  System.err.println("Error closing stage: " + e.getMessage());
        }
        
        // Clear references
        engine = null;
        previewStage = null;
    }
    
    public synchronized void reopen() {
        if (!disposed) {
            dispose();
        }
        disposed = false;
        createExecutor();
        setupUpdateTimer();
        createAndShowPreviewWindow();
    }

    public class JavaLinkHandler {
        private final Consumer<String> linkHandler;

        public JavaLinkHandler(Consumer<String> linkHandler) {
            this.linkHandler = linkHandler;
        }

        public void handleLink(String url) {
            if (!disposed) {
                linkHandler.accept(url);
            }
        }
    }
}