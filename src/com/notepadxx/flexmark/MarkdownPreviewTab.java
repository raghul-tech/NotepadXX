package com.notepadxx.flexmark;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.formdev.flatlaf.FlatLaf;
import com.notepadxx.notepadxx.Texteditor;
import com.notepadxx.resources.icon.GetImage;
import com.notepadxx.utils.JavaFXUtils;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gfm.users.GfmUsersExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MarkdownPreviewTab { 
	
private final JTabbedPane tabbedPane;
//private static volatile boolean javaFXInitialized = JavaFXUtils.isJavaFXAvailable(); // Make volatile for thread safety
private final Map<Texteditor, JFXPanel> previewMap = new HashMap<>();
private final Map<JFXPanel, WebEngine> engineMap = new HashMap<>();
private final Timer updateTimer;
private final ExecutorService executor;

public MarkdownPreviewTab(JTabbedPane tabbedPane) {
    this.tabbedPane = tabbedPane;
    // Use a single thread executor for background tasks like link opening
  
    this.executor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "MarkdownPreviewTab" + hashCode());
        t.setDaemon(true); // Allow JVM to exit if this is the only thread left
        return t;
    });
    
    updateTimer = new Timer(700, e -> {
        int editorHash = Integer.parseInt(e.getActionCommand());
        Texteditor sourceEditor = findEditorByHashCode(editorHash);
        if (sourceEditor != null) {
            JFXPanel fxPanel = previewMap.get(sourceEditor);
             if (fxPanel != null && tabbedPane.indexOfComponent(fxPanel) != -1) {
                 updatePreviewContent(sourceEditor);
             } else {
                 // Ignore timer event if preview tab is gone
             }
        }
    });
    tabbedPane.addChangeListener(e -> {
        int index = tabbedPane.getSelectedIndex();
        if (index != -1) {
            Component comp = tabbedPane.getComponentAt(index);
            if (comp instanceof JFXPanel) {
                JFXPanel fxPanel = (JFXPanel)comp;
                // Restore tab components if needed
                JPanel tabHeader = (JPanel)fxPanel.getClientProperty("tabHeader");
                if (tabHeader != null && tabbedPane.getTabComponentAt(index) != tabHeader) {
                    tabbedPane.setTabComponentAt(index, tabHeader);
                }
            }
        }
    });
    updateTimer.setRepeats(false);
  //  initializeJavaFX(); // Initialize JavaFX subsystem
}

// Helper to find editor by identity hash code
private Texteditor findEditorByHashCode(int hashCode) {
    // Use Map.entrySet for potentially slightly better performance
    for (Map.Entry<Texteditor, JFXPanel> entry : previewMap.entrySet()) {
        if (System.identityHashCode(entry.getKey()) == hashCode) {
            return entry.getKey();
        }
    }
    return null;
}


// --- Real-time Update Logic --- (No changes needed here)
 private void attachDocumentListener(Texteditor sourceEditor, JFXPanel fxPanel) {
    sourceEditor.getTextArea().getDocument().addDocumentListener(new DocumentListener() {
        @Override public void insertUpdate(DocumentEvent e) { triggerPreviewUpdate(sourceEditor); }
        @Override public void removeUpdate(DocumentEvent e) { triggerPreviewUpdate(sourceEditor); }
        @Override public void changedUpdate(DocumentEvent e) { triggerPreviewUpdate(sourceEditor); }
    });
   /* sourceEditor.addPropertyChangeListener("tabTitle", evt -> {
        if (previewMap.containsKey(sourceEditor)) {
            JFXPanel currentPanel = previewMap.get(sourceEditor);
            if (currentPanel != null && tabbedPane.indexOfComponent(currentPanel) != -1) {
                updateTabTitle(currentPanel, "Preview: " + sourceEditor.getTabTitle());
            }
        }
    });*/
    sourceEditor.addPropertyChangeListener("tabTitle", evt -> {
        if (previewMap.containsKey(sourceEditor)) {
            JFXPanel currentPanel = previewMap.get(sourceEditor);
            if (currentPanel != null && tabbedPane.indexOfComponent(currentPanel) != -1) {
                String newTitle = "Preview: " + sourceEditor.getTabTitle();
                updateTabTitle(currentPanel, newTitle);
            }
        }
    });
}
private void triggerPreviewUpdate(Texteditor sourceEditor) {
    updateTimer.setActionCommand(String.valueOf(System.identityHashCode(sourceEditor)));
    updateTimer.restart();
}


private void updatePreviewContent(Texteditor sourceEditor) {
    JFXPanel fxPanel = previewMap.get(sourceEditor);
    if (fxPanel == null || tabbedPane.indexOfComponent(fxPanel) == -1) {
        return;
    }

    WebEngine engine = engineMap.get(fxPanel);
    if (engine == null) return;

    String markdown =  new String(sourceEditor.getTextArea().getText().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    File currentFile = sourceEditor.getCurrentFile();
    String html = renderMarkdown(markdown)
    	    .replace(":emoji_name:", "<img class='emoji' alt=':emoji_name:' src='https://github.githubassets.com/images/icons/emoji/unicode/1f604.png' height='20' width='20'>");
    String styledHtml = getStyledHtml(html, currentFile);

    // Wrap content in a <div id="content"> so we can replace it dynamically
    String fullHtml = "<html><head><meta charset='UTF-8'></head><body><div id='content'>"
                    + styledHtml + "</div></body></html>";

    Platform.runLater(() -> {
        try {
            // On first load, use loadContent
            if (engine.getDocument() == null) {
                engine.loadContent(fullHtml);
                return;
            }

            // Otherwise, update the content dynamically using Base64 encoding
            String encoded = Base64.getEncoder().encodeToString(styledHtml.getBytes());
            engine.executeScript(
                "var content = document.getElementById('content');" +
                "if (content) {" +
                "  var scrollY = window.scrollY || window.pageYOffset;" +
                "  content.innerHTML = atob('" + encoded + "');" +
                "  window.scrollTo(0, scrollY);" +
                "} else {" +
                "  document.body.innerHTML = atob('" + encoded + "');" +
                "}"
            );
        } catch (Exception e) {
        //    System.err.println("Error updating preview: " + e.getMessage());
            // Fallback to full reload if JavaScript fails
            engine.loadContent(fullHtml);
        }
    });
}

// --- End Real-time Update Logic ---

// --- Tab Opening Logic ---
public void openMarkdownPreviewTabFX(Texteditor sourceEditor) { // Make public if called from outside
    // Ensure FX is ready before proceeding
	 if (!JavaFXUtils.isJavaFXAvailable()) {
         JOptionPane.showMessageDialog(
             sourceEditor,
             "Markdown Preview is not supported on this system.\n(JavaFX is not available.)",
             "Feature Not Available",
             JOptionPane.WARNING_MESSAGE
         );
         return;
     }

    // Check for existing preview tab
    if (previewMap.containsKey(sourceEditor)) {
        JFXPanel existingPanel = previewMap.get(sourceEditor);
        int existingIndex = tabbedPane.indexOfComponent(existingPanel);
        if (existingIndex != -1) {
            tabbedPane.setSelectedIndex(existingIndex);
          //   System.out.println("Existing preview tab selected for: " + sourceEditor.getTabTitle());
            return; // Tab exists, just show it
        } else {
            // Tab was closed externally or map is out of sync - cleanup
           //  System.out.println("Preview tab for " + sourceEditor.getTabTitle() + " not found in pane, cleaning up maps.");
            cleanupPreviewResources(sourceEditor, existingPanel);
            // previewMap is cleaned implicitly by cleanupPreviewResources if editor != null
        }
    }

    // Prepare content
    String markdown;
    // CRITICAL: Ensure your Texteditor loads the file with UTF-8 encoding!
    // If this getText() doesn't have the correct characters, emojis won't show.
    try {
    	// When getting text from editor
    	markdown = new String(sourceEditor.getTextArea().getText().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
      //   System.out.println(markdown);
    } catch (Exception e) {
         showErrorDialog("Error getting text from editor", e);
         return;
    }

    File currentFile = sourceEditor.getCurrentFile();
    String html = renderMarkdown(markdown)
    	    .replace(":emoji_name:", "<img class='emoji' alt=':emoji_name:' src='https://github.githubassets.com/images/icons/emoji/unicode/1f604.png' height='20' width='20'>");
    final String styledHtml = getStyledHtml(html, currentFile); // Final for lambda use
   // final String finalBasePath = getBasePathForFile(currentFile); // Final for lambda use

    // Create Swing container for FX content
    JFXPanel fxPanel = new JFXPanel();
    Icon tabIcon = currentFile != null ? GetImage.getImage(currentFile) : null;
    String tabTitle = "Preview: " + sourceEditor.getTabTitle();

    // Initialize FX components on the FX thread
    Platform.runLater(() -> {
        try {
            WebView webView = new WebView();
            WebEngine engine = webView.getEngine();
         // After creating your WebView
            
            engine.setJavaScriptEnabled(true);
            
            // Add this to handle emoji rendering
            engine.documentProperty().addListener((obs, oldDoc, newDoc) -> {
                if (newDoc != null) {
                    newDoc.setDocumentURI("about:blank");
              //      newDoc.putProperty("charset", "UTF-8");
                }
            });
            
            boolean isDarkMode = FlatLaf.isLafDark();
            webView.getEngine().setUserStyleSheetLocation("data:text/css;charset=utf-8," +
                "::-webkit-scrollbar {" +
                "    width: 10px;" +
                "}" +
                "::-webkit-scrollbar-track {" +
                "    background: " + (isDarkMode ? "#2b2b2b" : "#f1f1f1") + ";" +
                "}" +
                "::-webkit-scrollbar-thumb {" +
                "    background: " + (isDarkMode ? "#555555" : "#888") + ";" +
                "}" +
                "::-webkit-scrollbar-thumb:hover {" +
                "    background: " + (isDarkMode ? "#666666" : "#555") + ";" +
                "}");

            // --- Java-to-JavaScript Bridge Setup ---
            JavaLinkHandler linkHandler = new JavaLinkHandler(url -> {
                handleExternalLink(url); // Your existing link handler
            });
            engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    JSObject window = (JSObject) engine.executeScript("window");
                    window.setMember("javaBridge", linkHandler);  // <--- ADD THIS
                    
                    // Add click interceptor script
                    engine.executeScript(  // <--- ADD FROM HERE
                        "document.addEventListener('click', function(e) {" +
                        "   var target = e.target.closest('a');" +
                        "   if (target) {" +
                        "       e.preventDefault();" +
                        "       window.javaBridge.handleLink(target.href);" +
                        "   }" +
                        "});"
                    );  // <--- TO HERE
                    
                //    engine.executeScript("parseEmojis();");
                }
            });
            // Store engine BEFORE setting up listeners or content
     //       engineMap.put(fxPanel, engine);

            // --- Link Handling Listener ---
         // Added location listener to block internal navigation
            engine.locationProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal == null || newVal.isEmpty() || newVal.startsWith("data:text/html")) return;
                Platform.runLater(() -> {
                    engine.load(null); // Cancel WebEngine navigation
                    handleExternalLink(newVal); // Handle URL externally
                });
            });
            //-- End of location listener ---

            engineMap.put(fxPanel, engine);
            engine.setJavaScriptEnabled(true); // Keep enabled for potential future use
            engine.loadContent(styledHtml);    // Load the initial HTML

            Scene scene = new Scene(webView);
            fxPanel.setScene(scene); // Set the scene on the JFXPanel

            // Add tab and listener on the Swing EDT *after* FX setup
            SwingUtilities.invokeLater(() -> {
                addTabWithPreview(fxPanel, tabTitle, tabIcon, sourceEditor);
                previewMap.put(sourceEditor, fxPanel); // Associate editor only after successful add
                attachDocumentListener(sourceEditor, fxPanel); // Start listening for changes
               //  System.out.println("Preview tab added for: " + sourceEditor.getTabTitle());
            });

        } catch (Exception e) {
            // System.err.println("Error initializing WebView/Scene: " + e.getMessage());
            // Cleanup if FX setup fails
            cleanupPreviewResources(sourceEditor, fxPanel);
            showErrorDialog("Failed to create preview component", e);
        }
    });
}


// --- Improved Link Handling ---
//Improved mailto link handling
private void handleExternalLink(String url) {
 executor.execute(() -> {
    // System.out.println("Handling URL: " + url);
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
                   //  System.err.println("Unsupported scheme: " + scheme);
             }
         }
     } catch (Exception e) {
         SwingUtilities.invokeLater(() -> 
             showErrorDialog("Error handling link", e)
         );
     }
 });
}

private void handleMailToLink(URI uri) {
 try {
     if (Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
         // Properly encode mailto parameters
         String mailTo = uri.toString()
             .replace(" ", "%20")
             .replace("+", "%2B");
         
         Desktop.getDesktop().mail(new URI(mailTo));
     } else {
         showErrorDialog("Mail links not supported", 
             new UnsupportedOperationException("Mailto not supported"));
     }
 } catch (Exception e) {
     showErrorDialog("Could not open email client", e);
 }
}

private void handleFileLink(URI uri) {
 try {
     File file = new File(uri);
     if (file.exists()) {
         if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
             Desktop.getDesktop().open(file);
         } else {
             Desktop.getDesktop().browse(uri);
         }
     } else {
         showErrorDialog("File not found", 
             new FileNotFoundException(file.getAbsolutePath()));
     }
 } catch (Exception e) {
     showErrorDialog("Error opening file", e);
 }
}


// Helper to get base path (no changes needed)
private String getBasePathForFile(File mdFile) {
    if (mdFile != null && mdFile.getParentFile() != null) {
        try {
            return mdFile.getParentFile().toURI().toString();
        } catch (Exception e) {
        	// System.err.println("Error creating base path URI: " + e.getMessage()); 
        	
        }
    }
    return null;
}

// Helper to cleanup resources (Modified slightly)
private void cleanupPreviewResources(Texteditor editor, JFXPanel panel) {
    if (panel == null) return; // Nothing to clean if panel is null

    //String editorTitle = (editor != null) ? editor.getTabTitle() : "Unknown";
  //   System.out.println("Cleaning up resources for editor: " + editorTitle + ", panel: " + panel.hashCode());

     // Remove editor association first
     if (editor != null) {
         previewMap.remove(editor);
       //   System.out.println("  - Removed editor from previewMap.");
     }

    // Remove panel -> engine mapping and cleanup engine
    WebEngine engine = engineMap.remove(panel);
    if (engine != null) {
       //  System.out.println("  - Found engine to cleanup.");
        Platform.runLater(() -> { // Cleanup on FX thread
            try {
               // System.out.println("  - Submitting engine.load(null) to FX thread.");
                engine.getLoadWorker().cancel();
                engine.load(null); // Release engine resources
            } catch (Exception e) { 
            	//System.err.println("  - Error during engine cleanup on FX thread: " + e.getMessage());
            	}
        });
    } else {
       //  System.out.println("  - No engine found in engineMap for this panel.");
    }

     // Cleanup Scene from panel on FX thread
     Platform.runLater(() -> {
         try {
             if (panel.getScene() != null) {
           //       System.out.println("  - Submitting panel.setScene(null) to FX thread.");
                 panel.setScene(null); // Remove scene reference
             }
         } catch (Exception e) { 
        	 //System.err.println("  - Error setting scene to null on FX thread: " + e.getMessage()); }
         }
     });
}

private void showErrorDialog(String message, Exception e) {
  //  System.err.println(message + ": " + e.getMessage()); // Log error too
    // e.printStackTrace(); // Uncomment for debugging stack trace
    SwingUtilities.invokeLater(() -> {
        JOptionPane.showMessageDialog(tabbedPane.getRootPane(), // Use root pane for better context
            message + ":\n" + e.getClass().getSimpleName() + ": " + e.getMessage(),
            "Markdown Preview Error", JOptionPane.ERROR_MESSAGE);
    });
}

// Helper to render markdown (no changes needed)
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
  /*  Pattern emojiPattern = Pattern.compile(
        "[\u00A9\u00AE\u203C\u2049\u2122\u2139\u2194-\u2199\u21A9\u21AA\u231A\u231B\u2328\u23CF" +
        "\u23E9-\u23F3\u23F8-\u23FA\u24C2\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE\u2600-\u2604\u260E" +
        "\u2611\u2614\u2615\u2618\u261D\u2620\u2622\u2623\u2626\u262A\u262E\u262F\u2638-\u263A\u2640" +
        "\u2642\u2648-\u2653\u265F\u2660\u2663\u2665\u2666\u2668\u267B\u267E\u267F\u2692-\u2697\u2699" +
        "\u269B\u269C\u26A0\u26A1\u26AA\u26AB\u26B0\u26B1\u26BD\u26BE\u26C4\u26C5\u26C8\u26CE\u26CF" +
        "\u26D1\u26D3\u26D4\u26E9\u26EA\u26F0-\u26F5\u26F7-\u26FA\u26FD\u2702\u2705\u2708-\u270D" +
        "\u270F\u2712\u2714\u2716\u271D\u2721\u2728\u2733\u2734\u2744\u2747\u274C\u274E\u2753-\u2755" +
        "\u2757\u2763\u2764\u2795-\u2797\u27A1\u27B0\u27BF\u2934\u2935\u2B05-\u2B07\u2B1B\u2B1C\u2B50" +
        "\u2B55\u3030\u303D\u3297\u3299\uD83C\uDC04\uD83C\uDD70\uD83C\uDD71\uD83C\uDD7E\uD83C\uDD7F" +
        "\uD83C\uDD8E\uD83C\uDD92-\uD83C\uDD9A\uD83C\uDDE6-\uD83C\uDDFF\uD83C\uDE01\uD83C\uDE02" +
        "\uD83C\uDE1A\uD83C\uDE2F\uD83C\uDE32-\uD83C\uDE3A\uD83C\uDE50\uD83C\uDE51\uD83C\uDF00-\uD83C\uDFFF" +
        "\uD83D\uDC00-\uD83D\uDDFF\uD83D\uDE00-\uD83D\uDE4F\uD83D\uDE80-\uD83D\uDEFF\uD83E\uDD00-\uD83E\uDDFF" +
        "\uD83E\uDE70-\uD83E\uDE74\uD83E\uDE78-\uD83E\uDE7A\uD83E\uDE80-\uD83E\uDE86\uD83E\uDE90-\uD83E\uDE95" +
        "\uD83E\uDEA0-\uD83E\uDEA5\uD83E\uDEB0-\uD83E\uDEB6]",
        Pattern.UNICODE_CHARACTER_CLASS
    );*/
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
    if (basePath == null || basePath.isEmpty()) {
       // System.err.println("Warning: Base path is empty. Relative images/links may fail.");
        basePath = "";
    }
    boolean isDarkMode = FlatLaf.isLafDark();
   

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
        isDarkMode ? "#ffffff" : "#333333",
        isDarkMode ? "#555555" : "#cccccc",
        isDarkMode ? "#3c3f41" : "#f2f2f2",
        isDarkMode ? "#4a4a4a" : "#e8e8e8",
        isDarkMode ? "#555555" : "#dddddd",
        isDarkMode ? "#4a9cff" : "#0366d6",
        isDarkMode ? "#444444" : "#eeeeee",
        isDarkMode ? "#58a6ff" : "#0366d6"
    );

    String mdStyles = """
    	    body {
    	        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Twemoji Mozilla";
    	        line-height: 1.5;
    	        padding: 16px;
    	        max-width: 800px;
    	        margin: 0 auto;
    	        background-color: var(--bg-color);
    	        color: var(--text-color);
    	    }
    	    h1, h2, h3, h4, h5, h6 {
    	        margin-top: 24px;
    	        margin-bottom: 16px;
    	        font-weight: 600;
    	    }
    	    h1 { font-size: 2em; border-bottom: 1px solid var(--hr-color); padding-bottom: 0.3em; }
    	    h2 { font-size: 1.5em; border-bottom: 1px solid var(--hr-color); padding-bottom: 0.3em; }
    	    code {
    	        font-family: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, monospace;
    	        background-color: var(--code-bg);
    	        padding: 0.2em 0.4em;
    	        margin: 0;
    	        font-size: 85%;
    	        border-radius: 3px;
    	    }
    	    pre {
    	        background-color: var(--code-bg);
    	        padding: 16px;
    	        overflow: auto;
    	        line-height: 1.45;
    	        border-radius: 3px;
    	    }
    	    pre code {
    	        background-color: transparent;
    	        padding: 0;
    	        margin: 0;
    	        font-size: 100%;
    	    }
    	    blockquote {
    	        padding: 0 1em;
    	        color: var(--text-color);
    	        border-left: 0.25em solid var(--blockquote-border);
    	        margin-left: 0;
    	    }
    	    table {
    	        border-collapse: collapse;
    	        width: 100%;
    	        margin-bottom: 16px;
    	    }
    	    table th {
    	        font-weight: 600;
    	        background-color: var(--table-header-bg);
    	    }
    	    table th, table td {
    	        padding: 6px 13px;
    	        border: 1px solid var(--table-border);
    	    }
    	    hr {
    	        height: 0.25em;
    	        padding: 0;
    	        margin: 24px 0;
    	        background-color: var(--hr-color);
    	        border: 0;
    	    }
    	    a {
    	        color: var(--link-color);
    	        text-decoration: none;
    	    }
    	    a:hover {
    	        text-decoration: underline;
    	    }
    	    img {
    	        max-width: 100%;
    	        box-sizing: content-box;
    	        background-color: var(--bg-color);
    	    }
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
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
            <base href="%s">
            <style>
                %s  /* Color variables */
                %s  /* Markdown styles */
                %s  /* Emoji font */
            </style>
          </head>
          <body>%s</body>
        </html>
        """, basePath, cssColors, mdStyles, fontCss, content);
}

// --- Tab UI Helpers (No changes needed) ---
 /*private void addTabWithPreview(JFXPanel fxPanel, String title, Icon icon, Texteditor sourceEditor) {
    SwingUtilities.invokeLater(() -> {
        if (tabbedPane.indexOfComponent(fxPanel) != -1) {
            tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(fxPanel)); 
            return;
        }
        // Find the index of the source editor tab
        int index = tabbedPane.indexOfComponent(sourceEditor);
        if (index == -1) {
            // If source tab not found (shouldn't happen), fall back to adding at end
            index = tabbedPane.getTabCount();
        } else {
            // Insert preview tab right after the source tab
            index += 1;
        }
       // int index = tabbedPane.getTabCount();
    //    tabbedPane.addTab("Preview", icon, fxPanel);
        tabbedPane.insertTab("Preview", icon, fxPanel, null, index);
        JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabHeader.setOpaque(false);
        JLabel titleLabel = new JLabel(title, icon, SwingConstants.LEFT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        JButton closeButton = createCloseButton(fxPanel, sourceEditor);
        tabHeader.add(titleLabel);
        tabHeader.add(closeButton);
        tabbedPane.setTabComponentAt(index, tabHeader);
        tabbedPane.setSelectedIndex(index);
        fxPanel.putClientProperty("tabLabel", titleLabel);
    });
}
 private void updateTabTitle(JFXPanel fxPanel, String newTitle) {
     SwingUtilities.invokeLater(() -> {
          int index = tabbedPane.indexOfComponent(fxPanel);
          if (index != -1) {
              Component tabComponent = tabbedPane.getTabComponentAt(index);
              if (tabComponent instanceof JPanel panel) {
                  for (Component comp : panel.getComponents()) {
                      if (comp instanceof JLabel label) { label.setText(newTitle); break; }
                  }
              }
              tabbedPane.setToolTipTextAt(index, newTitle);
          }
     });
 }
private JButton createCloseButton(JFXPanel fxPanelToClose, Texteditor associatedEditor) {
    JButton closeButton = new JButton("×");
    closeButton.setFont(new Font("Arial", Font.BOLD, 14));
    closeButton.setMargin(new Insets(0, 2, 0, 2));
    closeButton.setBorder(BorderFactory.createEmptyBorder());
    closeButton.setContentAreaFilled(false); closeButton.setFocusPainted(false);
    //closeButton.setToolTipText("Close Preview");
    closeButton.setForeground(UIManager.getColor("Label.foreground"));
    closeButton.addActionListener(e -> {
        int index = tabbedPane.indexOfComponent(fxPanelToClose);
        if (index != -1) {
            cleanupPreviewResources(associatedEditor, fxPanelToClose); // Use helper for cleanup
            SwingUtilities.invokeLater(() -> { // Remove tab on EDT
                int currentIndex = tabbedPane.indexOfComponent(fxPanelToClose);
                if (currentIndex != -1) { tabbedPane.remove(currentIndex); }
            });
        } else {
             System.out.println("Preview tab not found when trying to close.");
             cleanupPreviewResources(associatedEditor, fxPanelToClose); // Cleanup maps anyway
        }
    });
    closeButton.addMouseListener(new MouseAdapter() {
        @Override public void mouseEntered(MouseEvent e) { closeButton.setForeground(Color.RED); closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
        @Override public void mouseExited(MouseEvent e) { closeButton.setForeground(UIManager.getColor("Label.foreground")); closeButton.setCursor(Cursor.getDefaultCursor()); }
    });
    return closeButton;
}*/
private void addTabWithPreview(JFXPanel fxPanel, String title, Icon icon, Texteditor sourceEditor) {
    SwingUtilities.invokeLater(() -> {
        if (tabbedPane.indexOfComponent(fxPanel) != -1) {
            tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(fxPanel));
            return;
        }
        
        // Create tab components
        JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabHeader.setOpaque(false);
        JLabel titleLabel = new JLabel(title, icon, SwingConstants.LEFT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        JButton closeButton = createCloseButton(fxPanel, sourceEditor);
        tabHeader.add(titleLabel);
        tabHeader.add(closeButton);
        
        // Store components in the JFXPanel (which is a JComponent)
        fxPanel.putClientProperty("tabHeader", tabHeader);
        fxPanel.putClientProperty("titleLabel", titleLabel);
        fxPanel.putClientProperty("closeButton", closeButton);
        
        // Insert tab
        int index = tabbedPane.indexOfComponent(sourceEditor);
        if (index == -1) index = tabbedPane.getTabCount();
        else index += 1;
        
        tabbedPane.insertTab("Preview", icon, fxPanel, null, index);
        tabbedPane.setTabComponentAt(index, tabHeader);
        tabbedPane.setSelectedIndex(index);
    });
}
private void updateTabTitle(JFXPanel fxPanel, String newTitle) {
    SwingUtilities.invokeLater(() -> {
        int index = tabbedPane.indexOfComponent(fxPanel);
        if (index != -1) {
            // Get the stored label
            JLabel titleLabel = (JLabel)fxPanel.getClientProperty("titleLabel");
            if (titleLabel != null) {
                titleLabel.setText(newTitle);
            }
            tabbedPane.setToolTipTextAt(index, newTitle);
            
            // Ensure the tab component is still set
            JPanel tabHeader = (JPanel)fxPanel.getClientProperty("tabHeader");
            if (tabHeader != null && tabbedPane.getTabComponentAt(index) != tabHeader) {
                tabbedPane.setTabComponentAt(index, tabHeader);
            }
        }
    });
}
private JButton createCloseButton(JFXPanel fxPanelToClose, Texteditor associatedEditor) {
    JButton closeButton = new JButton("×");
    closeButton.setFont(new Font("Arial", Font.BOLD, 14));
    closeButton.setMargin(new Insets(0, 2, 0, 2));
    closeButton.setBorder(BorderFactory.createEmptyBorder());
    closeButton.setContentAreaFilled(false);
    closeButton.setFocusPainted(false);
    closeButton.setForeground(UIManager.getColor("Label.foreground"));
    
    closeButton.addActionListener(e -> {
        int index = tabbedPane.indexOfComponent(fxPanelToClose);
        if (index != -1) {
            cleanupPreviewResources(associatedEditor, fxPanelToClose);
            SwingUtilities.invokeLater(() -> {
                tabbedPane.remove(index);
            });
        }
    });
    
    closeButton.addMouseListener(new MouseAdapter() {
        @Override public void mouseEntered(MouseEvent e) {
            closeButton.setForeground(Color.RED);
            closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        @Override public void mouseExited(MouseEvent e) {
            closeButton.setForeground(UIManager.getColor("Label.foreground"));
            closeButton.setCursor(Cursor.getDefaultCursor());
        }
    });
    
    return closeButton;
}



// Dispose method to clean up everything
public void dispose() {
  //  System.out.println("Disposing MarkdownPreviewFX...");
    if (updateTimer != null && updateTimer.isRunning()) { updateTimer.stop(); }
    if (executor != null && !executor.isShutdown()) { executor.shutdownNow(); }

    java.util.List<Map.Entry<Texteditor, JFXPanel>> entries = new java.util.ArrayList<>(previewMap.entrySet());
    previewMap.clear(); // Clear map immediately

    CountDownLatch cleanupLatch = new CountDownLatch(entries.size());
    Platform.runLater(() -> {
        try {
            for (Map.Entry<Texteditor, JFXPanel> entry : entries) {
                JFXPanel panel = entry.getValue();
                WebEngine engine = engineMap.remove(panel); // Remove and get engine
                try {
                    if (engine != null) { engine.load(null); }
                    if (panel != null && panel.getScene() != null) { panel.setScene(null); }
                } catch (Exception e) { 
                	//System.err.println("Error during FX cleanup: " + e.getMessage()); }
                }
                finally { cleanupLatch.countDown(); }
            }
        } finally {
            // Ensure latch counts down even if loop fails
            while (cleanupLatch.getCount() > 0) cleanupLatch.countDown();
        }
    });

    try {
        if (!cleanupLatch.await(5, TimeUnit.SECONDS)) {
            // System.err.println("FX cleanup did not complete in time.");
        }
    } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

    engineMap.clear(); // Clear engine map

    SwingUtilities.invokeLater(() -> { // Remove tabs on EDT
        for (Map.Entry<Texteditor, JFXPanel> entry : entries) {
            JFXPanel panel = entry.getValue();
            if (panel != null && tabbedPane.indexOfComponent(panel) != -1) {
                tabbedPane.remove(panel);
            }
        }
       //  System.out.println("Preview tabs removed.");
    });
   //  System.out.println("MarkdownPreviewFX disposed.");
}
public class JavaLinkHandler {
    private final Consumer<String> linkHandler;

    public JavaLinkHandler(Consumer<String> linkHandler) {
        this.linkHandler = linkHandler;
    }

    public void handleLink(String url) {
        linkHandler.accept(url);
    }
}
}