package com.notepadxx.menu;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import com.notepadxx.resources.icon.GetImage;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AboutMenu {
    
    private static final Map<String, List<String>> LANGUAGE_MAP = createLanguageMap();
    private static final Map<String, List<String>> SUPPORTED_EXTENSIONS = createSupportedExtensions();
    private static final Map<String,String> CODE_ANALYSIS_EXTENSION = CodeAnalysisSupportedLanguage();
    private static JFXPanel fxPanel;
    
    public static void showAboutDialog() {
        // Create the JavaFX panel
    	

      //  JFXPanel fxPanel = new JFXPanel();
        if (fxPanel == null) {
            fxPanel = new JFXPanel();
          //  Platform.runLater(() -> createFXContent(fxPanel));
        }
        
        // Create the about frame with FlatLaf styling
        JFrame aboutFrame = new JFrame("About NotepadXX");
        aboutFrame.setSize(900, 800);
        aboutFrame.setLocationRelativeTo(null);
        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        try {
        	// LookAndFeel previousLookAndFeel = UIManager.getLookAndFeel();
        	 aboutFrame.setIconImage(new ImageIcon(AboutMenu.class.getResource("/icons/NotepadXXLogo.png")).getImage());
            //UIManager.setLookAndFeel(new FlatMacLightLaf());
            //SwingUtilities.updateComponentTreeUI(aboutFrame);
            //UIManager.setLookAndFeel(previousLookAndFeel); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        aboutFrame.getContentPane().add(fxPanel);
 
        // JavaFX content
        Platform.runLater(() -> createFXContent(fxPanel, aboutFrame));
        
        aboutFrame.setVisible(true);
    }
    
    private static void createFXContent(JFXPanel fxPanel, JFrame frame) {
        // Main container with modern styling
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f5f5f7;");
        
        // Header with logo and title (modern design)
        StackPane header = createModernHeader();
        root.setTop(header);
        
        // Tabbed content with glass effect
        TabPane tabPane = new TabPane();
        tabPane.getStyleClass().add("floating");
        
        // Create modern tabs
        tabPane.getTabs().addAll(
            createWhyChooseTab(),
            createShortcutsTab(),
            createFeaturesTab(),
            createLanguagesTab(),
            createLegalTab()
        );
        
        root.setCenter(tabPane);
        
        // Footer with modern close button
        HBox footer = createModernFooter(frame);
        root.setBottom(footer);
        
        // Set the scene with macOS-inspired styling
        Scene scene = new Scene(root);
        fxPanel.setScene(scene);
    }
    
    private static StackPane createModernHeader() {
        StackPane header = new StackPane();
        header.setPadding(new Insets(20));
        
        // Background with subtle gradient
        Rectangle bg = new Rectangle(300, 100);
        bg.setArcWidth(20);
        bg.setArcHeight(20);
        bg.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#e0e0e0")),
            new Stop(1, Color.web("#f0f0f0"))
        ));
        bg.setEffect(new DropShadow(10, 0, 2, Color.gray(0, 0.2)));
        
        HBox content = new HBox(20);
        content.setAlignment(Pos.CENTER);
        
        try {
            // Load logo image with professional styling
            Image logoImage = new Image(AboutMenu.class.getResourceAsStream("/icons/LogoX.png"));
            ImageView logoView = new ImageView(logoImage);
            
            // Professional image sizing
            logoView.setFitHeight(64);  // Standard size for application logos
            logoView.setPreserveRatio(true);
            logoView.setSmooth(true);   // Enable high-quality scaling
            
            // Subtle drop shadow for depth
            logoView.setEffect(new DropShadow(5, Color.gray(0, 0.3)));
            
            content.getChildren().add(logoView);
        } catch (Exception e) {
           // System.err.println("Could not load logo: " + e.getMessage());
            
            // Professional fallback - simple text with styling
            Text logoText = new Text("XX");
            logoText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 28));
            logoText.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#007AFF")),
                new Stop(1, Color.web("#34C759"))
            ));
            content.getChildren().add(logoText);
        }
        
        VBox textContent = new VBox(5);
        textContent.setAlignment(Pos.CENTER_LEFT);
        
        Label title = new Label("NotepadXX");
        title.setFont(Font.font("SF Pro Display", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#2c2c2e"));
        
        Label version = new Label("Version 1.2.1");
        version.setFont(Font.font("SF Pro Text", 14));
        version.setTextFill(Color.web("#636366"));
        
        textContent.getChildren().addAll(title, version);
        content.getChildren().add(textContent);
        
        header.getChildren().addAll(bg, content);
        return header;
    }
    
    private static Tab createWhyChooseTab() {
        Tab tab = new Tab("Why Choose NotepadXX?");
        tab.setClosable(false);
        
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        
        // Main description
        Label description = new Label(
            "NotepadXX is not just another text editor; it's a powerful tool designed to enhance your productivity " +
            "and provide a seamless editing experience. Whether you're a coder, writer, or just someone who needs " +
            "a lightweight text editor, NotepadXX offers:");
        description.setWrapText(true);
        description.setStyle("-fx-font-size: 14px;");
        
        // Features list
        VBox featuresList = new VBox(10);
        featuresList.getChildren().add(createFeatureItem("Fast and Responsive", 
            "A minimalistic design ensures quick startup times and efficient file handling"));
        featuresList.getChildren().add(createFeatureItem("Multitab Editing", 
            "Manage multiple files effortlessly with our intuitive tabbed interface"));
        featuresList.getChildren().add(createFeatureItem("Cross-Platform Compatibility", 
            "Works on any system that supports Java"));
        featuresList.getChildren().add(createFeatureItem("Hybrid UI", 
            "Combining the best of Java Swing and JavaFX for a modern interface"));
        featuresList.getChildren().add(createFeatureItem("Real-Time File Monitoring", 
            "Detects external changes to your files and alerts you"));
        featuresList.getChildren().add(createFeatureItem("Powerful Tools", 
            "Command Prompt access, Web Browser integration, and File Browser capabilities"));
        featuresList.getChildren().add(createFeatureItem("Syntax Highlighting", 
            "Supports " + LANGUAGE_MAP.size() + "+ programming languages"));
        featuresList.getChildren().add(createFeatureItem("Customizable Themes", 
            "Light and dark modes with full FlatLaf support"));
        featuresList.getChildren().add(createFeatureItem("No Bloatware", 
            "Lightweight and free from unnecessary features"));
        
        // Why Stand Out section
        Label standOut = new Label(
            "NotepadXX isn't just another text editor; it's a comprehensive, feature-packed tool that combines " +
            "the power of advanced coding features with essential utilities like browser access, file management, " +
            "and command-line support. Perfect for developers, writers, and anyone who needs a versatile editor.");
        standOut.setWrapText(true);
        standOut.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
        
        // Call to action
        HBox ctaBox = new HBox(10);
        ctaBox.setAlignment(Pos.CENTER);
        
      /*  Button downloadBtn = createModernButton("Download Now", "#007AFF");
        downloadBtn.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new URI("https://yourwebsite.com/download"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        ctaBox.getChildren().add(downloadBtn);*/
        
        content.getChildren().addAll(description, featuresList, standOut, ctaBox);
        
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        tab.setContent(scrollPane);
        
        return tab;
    }
    
    private static Tab createShortcutsTab() {
        Tab tab = new Tab("Shortcuts");
        tab.setClosable(false);

        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(30);
        grid.setPadding(new Insets(20));

        int row = 0;

        // First row: File, Edit, Search
        addShortcutSection(grid, "File Operations", 0, row, new String[][] {
            {"Ctrl+N", "Create new tab"},
            {"Ctrl+O", "Open file"},
            {"Ctrl+S", "Save file"},
            {"Ctrl+Alt+S", "Save as file"},
            {"Ctrl+Shift+S", "Save all files"},
            {"Ctrl+W", "Close tab"},
            {"Ctrl+Shift+W", "Close all tabs"},
            {"Ctrl+Shift+T", "Restore recent closed file"},
            {"Alt+F4", "Exit"}
        });

        addShortcutSection(grid, "Edit Operations", 1, row, new String[][] {
            {"Ctrl+Z", "Undo"},
            {"Ctrl+Y", "Redo"},
            {"Ctrl+X", "Cut"},
            {"Ctrl+C", "Copy"},
            {"Ctrl+V", "Paste"},
            {"Ctrl+P", "Print"}
        });

        addShortcutSection(grid, "Search Operations", 2, row, new String[][] {
            {"Ctrl+F", "Find"},
            {"Ctrl+H", "Replace"}
        });

        row++;

        // Second row: View, Tools, Others
        addShortcutSection(grid, "View Operations", 0, row, new String[][] {
            {"Ctrl+Shift+E", "File Explorer"},
            {"Ctrl+Shift+C", "Command Prompt"},
            {"Ctrl+Alt+A", "Admin Command Prompt"},
            {"Ctrl+Alt+E", "Microsoft Edge"},
            {"Ctrl+Alt+C", "Google Chrome"},
            {"Ctrl+Alt+F", "Firefox"}
        });

        addShortcutSection(grid, "Tools Operations", 1, row, new String[][] {
            {"Ctrl+=", "Zoom In"},
            {"Ctrl+-", "Zoom Out"},
            {"Ctrl+0", "Reset Size"}
        });

        addShortcutSection(grid, "Other Operations", 2, row, new String[][] {
            {"Ctrl+K", "Open MD preview in tab"},
            {"Ctrl+Shift+K", "Open MD preview in new window"},
            {"Ctrl+M", "Toggle tooltip for code errors"},
            {"Ctrl+Shift+M", "Hide code error tooltips"},
            {"Ctrl+→ / Ctrl+←", "Switch to next/previous tab"},
            {"Ctrl+Shift+→ / Ctrl+Shift+←", "Move tab to right/left"}
        });

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        tab.setContent(scrollPane);

        return tab;
    }

    private static Tab createFeaturesTab() {
        Tab tab = new Tab("Features");
        tab.setClosable(false);

        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        
        // Add each feature card
        content.getChildren().add(createModernFeatureCard(
        	    "File Operations",
        	    "Manage your text files effortlessly with integrated file handling options.\n" +
        	    """
        	    
        	    • Create new files (Ctrl+N)
        	    • Open existing files (Ctrl+O)
        	    • Save current work (Ctrl+S)
        	    • Save As (Ctrl+Alt+S) or Save All (Ctrl+Shift+S)
        	    • Save a Copy or Rename the current file
        	    • Close current tab (Ctrl+W) or close all tabs (Ctrl+Shift+W)
        	    • Restore previously closed tab (Ctrl+Shift+T)
        	    • View all recently closed tabs via MenuBar
        	    • Clear recent closed files list
        	    • Exit the application (Alt+F4)

        	    🔧 How to Use:
        	    - Use the MenuBar or keyboard shortcuts for quick actions
        	    - ToolBar icons provide one-click access to common operations
        	    """,
        	    createFileOperationsIcon()
        	));

        
        content.getChildren().add(createModernFeatureCard(
        	    "Basic Editing Functions",
        	    "Perform everyday text operations with ease and efficiency.\n" +
        	    """
        	    
        	    • Cut, copy, and paste text (Ctrl+X, Ctrl+C, Ctrl+V)
        	    • Undo and redo actions (Ctrl+Z, Ctrl+Y)
        	    • Print your document (Ctrl+P)
        	    • Select all text, delete specific lines, or clear the entire content

        	    🔧 How to Use:
        	    - Use keyboard shortcuts or the context menu (right-click in the editor)
        	    - All actions follow standard OS behaviors for familiarity
        	    - ToolBar buttons allow one-click access to all editing functions
        	    """,
        	    createEditingIcon()
        	));

        content.getChildren().add(createModernFeatureCard(
        	    "Command Line Integration",
        	    "Seamlessly integrate NotepadXX with your terminal or command prompt for faster access and workflow.\n" +
        	    """
        	    
        	    • Launch the app from terminal using: notepadxx <filename>
        	    • Automatically opens the specified file in a new tab or window
        	    • Supports both relative and absolute file paths
        	    • Open Command Prompt or Terminal directly from the editor (Ctrl+Shift+C)
        	    • Open as Administrator (Windows) or Root Terminal (Linux) using (Ctrl+Shift+A)
        	    • Accessible from both Menu Bar and Tool Bar

        	    🔧 How to Use:
        	    - Use Ctrl+Shift+C to launch regular terminal
        	    - Use Ctrl+Shift+A to open with admin/root privileges
        	    - Configure system environment variables or aliases for global access
        	    - Great for developers who prefer terminal-based workflows
        	    """,
        	    createCommandLineIcon()
        	));


        content.getChildren().add(createModernFeatureCard(
        	    "Web Browser Support",
        	    "Launch your current file or just open a browser directly — perfect for web development workflows.\n" +
        	    """
        	    
        	    • Supports 3 major browsers for each OS:
        	      - 🪟 Windows: Microsoft Edge, Google Chrome, Mozilla Firefox
        	      - 🐧 Linux: Google Chrome, Mozilla Firefox, Tor Browser

        	    • If a file is open:
        	      - The file is opened in the selected browser
        	      - Ideal for previewing HTML, JS, CSS, or other web-related files

        	    • If no file is open:
        	      - Launches the selected browser to its home/start page

        	    • Access Options:
        	      - Toolbar icons (Edge, Chrome, Firefox/Tor)
        	      - Menu Bar under 'View'
        	      - Keyboard Shortcuts:
        	        - Windows:
        	          - Ctrl+Alt+E – Open with Edge
        	          - Ctrl+Alt+C – Open with Chrome
        	          - Ctrl+Alt+F – Open with Firefox
        	        - Linux:
        	          - Ctrl+Alt+C – Chrome
        	          - Ctrl+Alt+F – Firefox
        	          - Ctrl+Alt+T – Tor Browser

        	    🔧 How to Use:
        	    - Click on the desired browser icon in the toolbar or use the shortcut keys
        	    - Open a file to preview it directly; if not, browser opens normally
        	    - Great for testing HTML/CSS/JS in multiple environments
        	    """,
        	    createWebBrowserIcon()
        	));


        content.getChildren().add(createModernFeatureCard(
        	    "Syntax Highlighting",
        	    "Improve code readability and productivity with real-time, language-aware syntax coloring.\n" +
        	    """
        	    
        	    • Powered by RSyntaxTextArea – fast and lightweight
        	    • Automatically detects language from file extension
        	    • Supports a wide range of programming languages:
        	      - Java, Python, HTML, CSS, JavaScript, C/C++, PHP, XML, and more
        	    • Easily switch highlighters manually via 'Language' menu
        	    • 'Language' menu shows the current active syntax and allows you to override it

        	    🔧 How to Use:
        	    - Open a source code file (e.g., .java, .html, .py) to see automatic syntax highlighting
        	    - Use the Menu Bar → Language to manually choose a different syntax mode
        	    - Great for trying out different formats or working with unknown extensions
        	    """,
        	    createSyntaxHighlightingIcon()
        	));


        content.getChildren().add(createModernFeatureCard(
        	    "Real-Time Code Analysis",
        	    "Catch coding mistakes instantly while typing with smart syntax and semantic checks.\n" +
        	    """
        	    
        	    • Powered by ANTLR lexer and parser for multiple languages
        	    • Highlights syntax and logic errors in real-time
        	    • Tooltip suggestions show hints and corrections
        	    • Two modes: Normal and Advanced (switch with Ctrl+M)
        	    • Disable analysis on the fly using Ctrl+Shift+M
        	    • Automatically activates when you begin editing code
        	    • ‘Code Analysis’ Menu:
        	      - Shows all supported languages
        	      - Allows switching between analyzers

        	    🔧 How to Use:
        	    - Just start typing in a supported source file
        	    - Hover over underlined code to view analysis tooltip
        	    - Press Ctrl+M to toggle between Normal and Advanced suggestions
        	    - Press Ctrl+Shift+M to turn off/on analysis
        	    - Use Preferences MenuBar to toggle between them
        	    - Change the analyzer from the MenuBar → Code Analysis
        	    """,
        	    createCodeAnalysisIcon()
        	));

        content.getChildren().add(createModernFeatureCard(
        	    "Markdown Preview",
        	    "Write and preview Markdown documents with instant, synchronized updates.\n" +
        	    """
        	    
        	    • Real-time Markdown rendering using Flexmark + JavaFX WebView
        	    • Supports all standard elements: headings, lists, images, code blocks, tables, etc.
        	    • Ctrl+K → Opens preview in a tab
        	    • Ctrl+Shift+K → Opens preview in a new window
        	    • Syncs live with content as you type
        	    • Automatically detects `.md` files when opened

        	    🔧 How to Use:
        	    - Open a Markdown (.md) file in the editor
        	    - Press Ctrl+K or Ctrl+Shift+K to launch the preview
        	    - Edits reflect instantly in the preview window/tab
        	    """
        	    , createMarkdownIcon()
        	));

        content.getChildren().add(createModernFeatureCard(
        	    "Multitab File Handling",
        	    "Efficiently manage and edit multiple files using a smart tabbed interface.\n" +
        	    """
        	    
        	    • Open and work with unlimited files simultaneously
        	    • Each tab shows file name, extension icon (e.g., Java, Python)
        	    • Tabs can be rearranged via drag-and-drop or keyboard
        	    • Ctrl + → / ← to focus between tabs
        	    • Ctrl + Shift + → / ← to move the tab position in the tab bar
        	    • Auto-save file paths and restore them on next launch
        	    • Tabs remember scroll, caret, and unsaved status
        	    • Full file path shown on hover

        	    🔧 How to Use:
        	    - Open files via File → Open or drag & drop
        	    - On app restart, previously open files will be restored in the same order
        	    """
        	    , createMultitabIcon()
        	));

        
        // Themes section (your existing themes code)
        VBox themesBox = new VBox(10);
        themesBox.setPadding(new Insets(15));
        themesBox.setStyle("-fx-background-color: white; -fx-background-radius: 12;");
        
        Label themesTitle = new Label("Themes");
        themesTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        FlowPane themesFlow = new FlowPane();
        themesFlow.setHgap(15);
        themesFlow.setVgap(15);
        themesFlow.setPadding(new Insets(10));
        
        themesFlow.getChildren().addAll(
            createThemeCard("Light Theme", "Bright and clean", Color.LIGHTGRAY),
            createThemeCard("Dark Theme", "Easy on the eyes", Color.DARKGRAY),
            createThemeCard("Classic", "Traditional look", Color.web("#f0f0f0")),
            createThemeCard("Darcula", "Rich dark colors", Color.web("#2b2b2b")),
            createThemeCard("macOS Light", "Apple-inspired", Color.web("#f5f5f7")),
            createThemeCard("macOS Dark", "Dark Apple style", Color.web("#1e1e1e"))
        );
        
        themesBox.getChildren().addAll(themesTitle, themesFlow);
        content.getChildren().add(themesBox);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        tab.setContent(scrollPane);

        return tab;
    }
      
    
    private static ImageView createSyntaxHighlightingIcon() {
    	 // Create a simple code analysis icon
        StackPane codeIcon = new StackPane();
        
        Rectangle bg = new Rectangle(24, 24);
        bg.setFill(Color.TRANSPARENT);
        
        Text codeText = new Text("{}");
        codeText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        codeText.setFill(Color.web("#6A5ACD")); // SlateBlue (cooler and modern for code)
        
        codeIcon.getChildren().addAll(bg, codeText);
        
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        WritableImage image = codeIcon.snapshot(params, null);
        return new ImageView(image);
	}

	private static ImageView createMultitabIcon() {
    	 Image image = new Image(AboutMenu.class.getResourceAsStream("multitab.png"));
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(24);   // optional: scale image to 24x24
	        imageView.setFitHeight(24);
	        return imageView;
	}

	private static ImageView createWebBrowserIcon() {
    	 Image image = new Image(AboutMenu.class.getResourceAsStream("internet.png"));
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(24);   // optional: scale image to 24x24
	        imageView.setFitHeight(24);
	        return imageView;
	}

	private static ImageView createCommandLineIcon() {
    	 Image image = new Image(AboutMenu.class.getResourceAsStream("terminal.png"));
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(24);   // optional: scale image to 24x24
	        imageView.setFitHeight(24);
	        return imageView;
	}

	private static ImageView createEditingIcon() {
		 Image image = new Image(AboutMenu.class.getResourceAsStream("edit.png"));
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(24);   // optional: scale image to 24x24
	        imageView.setFitHeight(24);
	        return imageView;
	}

	private static ImageView createFileOperationsIcon() {
		 Image image = new Image(AboutMenu.class.getResourceAsStream("file (3).png"));
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(24);   // optional: scale image to 24x24
	        imageView.setFitHeight(24);
	        return imageView;
	}

	 private static ImageView createMarkdownIcon() {
		 Image image = new Image(AboutMenu.class.getResourceAsStream("MD.png"));
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(24);   // optional: scale image to 24x24
	        imageView.setFitHeight(24);
	        return imageView;
	    }
	    
	    private static ImageView createCodeAnalysisIcon() {
	        // Create a simple code analysis icon
	        StackPane codeIcon = new StackPane();
	        
	        Rectangle bg = new Rectangle(24, 24);
	        bg.setFill(Color.TRANSPARENT);
	        
	        Text codeText = new Text("{}");
	        codeText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
	        codeText.setFill(Color.web("#34C759"));
	        
	        codeIcon.getChildren().addAll(bg, codeText);
	        
	        SnapshotParameters params = new SnapshotParameters();
	        params.setFill(Color.TRANSPARENT);
	        WritableImage image = codeIcon.snapshot(params, null);
	        return new ImageView(image);
	    }
	    
	    private static Tab createLanguagesTab() {
	        Tab tab = new Tab("Languages");
	        tab.setClosable(false);

	        VBox content = new VBox(20);
	        content.setPadding(new Insets(20));

	        // === Syntax Highlighting Section ===
	        VBox langSupportBox = new VBox(10);
	        langSupportBox.setPadding(new Insets(15));
	        langSupportBox.setStyle("-fx-background-color: white; -fx-background-radius: 12;");

	        Label langTitle = new Label("Supported Languages (Syntax Highlighting) (" + LANGUAGE_MAP.values().stream().mapToInt(List::size).sum() + ")");
	        langTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	        TabPane langTabPane = new TabPane();
	        langTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

	        LANGUAGE_MAP.forEach((letter, langs) -> {
	            Tab letterTab = new Tab(letter);

	            FlowPane langFlow = new FlowPane();
	            langFlow.setHgap(10);
	            langFlow.setVgap(10);
	            langFlow.setPadding(new Insets(10));

	            langs.forEach(lang -> {
	                List<String> extensions = SUPPORTED_EXTENSIONS.getOrDefault(lang, Collections.singletonList("txt"));
	                String primaryExtension = extensions.get(0);
	                VBox langCard = createLanguageCard(lang, primaryExtension);
	                langFlow.getChildren().add(langCard);
	            });

	            ScrollPane scroll = new ScrollPane(langFlow);
	            scroll.setFitToWidth(true);
	            letterTab.setContent(scroll);
	            langTabPane.getTabs().add(letterTab);
	        });

	        langSupportBox.getChildren().addAll(langTitle, langTabPane);
	        content.getChildren().add(langSupportBox);

	        // === Code Analysis Supported Languages Section ===
	        VBox analysisLangBox = new VBox(10);
	        analysisLangBox.setPadding(new Insets(15));
	        analysisLangBox.setStyle("-fx-background-color: white; -fx-background-radius: 12;");

	        Label analysisLangTitle = new Label("Supported Languages (Code Analysis) (" + CODE_ANALYSIS_EXTENSION.size() + ")");
	        analysisLangTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	        // Sort the map by language name (key)
	        List<Map.Entry<String, String>> sortedAnalysisLangs = CODE_ANALYSIS_EXTENSION.entrySet()
	                .stream()
	                .sorted(Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER))
	                .collect(Collectors.toList());

	        FlowPane analysisLangFlow = new FlowPane();
	        analysisLangFlow.setHgap(12);
	        analysisLangFlow.setVgap(12);
	        analysisLangFlow.setPadding(new Insets(10));
	        analysisLangFlow.setPrefWrapLength(500); // Adjust wrap length as needed

	        for (Map.Entry<String, String> entry : sortedAnalysisLangs) {
	            String langName = entry.getKey();
	            String ext = entry.getValue();
	            VBox langCard = createLanguageCard(langName, ext);
	            analysisLangFlow.getChildren().add(langCard);
	        }

	        ScrollPane analysisScroll = new ScrollPane(analysisLangFlow);
	        analysisScroll.setFitToWidth(true);
	        analysisScroll.setPrefViewportHeight(200);

	        analysisLangBox.getChildren().addAll(analysisLangTitle, analysisScroll);
	        content.getChildren().add(analysisLangBox);

	        ScrollPane scrollPane = new ScrollPane(content);
	        scrollPane.setFitToWidth(true);
	        tab.setContent(scrollPane);

	        return tab;
	    }

	    
    
    private static Tab createLegalTab() {
        Tab tab = new Tab("Legal");
        tab.setClosable(false);
        
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        
        // License Section
        VBox licenseBox = new VBox(10);
        licenseBox.setPadding(new Insets(15));
        licenseBox.setStyle("-fx-background-color: white; -fx-background-radius: 12;");
        
        Label licenseTitle = new Label("License");
        licenseTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        Label licenseText = new Label("This project is licensed under the GNU General Public License (GPL v3)");
        licenseText.setStyle("-fx-font-size: 14px;");
        
        Hyperlink licenseLink = new Hyperlink("View Full License Text");
        licenseLink.setOnAction(e -> openUrl("https://github.com/raghul-tech/NotepadXX/blob/master/LICENSE"));
        
        licenseBox.getChildren().addAll(licenseTitle, licenseText, licenseLink);
        
        // Privacy Section
        VBox privacyBox = new VBox(10);
        privacyBox.setPadding(new Insets(15));
        privacyBox.setStyle("-fx-background-color: white; -fx-background-radius: 12;");
        
        Label privacyTitle = new Label("Privacy");
        privacyTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        HBox privacyContent = new HBox(10);
        privacyContent.setAlignment(Pos.CENTER_LEFT);
        
        // Use system privacy icon
        ImageView privacyIcon = getSystemIconView("privacy");
        
        Hyperlink privacyLink = new Hyperlink("Privacy Policy");
        privacyLink.setOnAction(e -> openUrl("https://github.com/raghul-tech/NotepadXX/blob/master/privacy-policy.md"));
        
        Label privacyText = new Label("Read our privacy policy to understand how we handle your data");
        privacyText.setStyle("-fx-font-size: 14px;");
        
        privacyContent.getChildren().addAll(privacyIcon, privacyLink);
        privacyBox.getChildren().addAll(privacyTitle, privacyContent, privacyText);
        
        // Changelog Section
        VBox changelogBox = new VBox(10);
        changelogBox.setPadding(new Insets(15));
        changelogBox.setStyle("-fx-background-color: white; -fx-background-radius: 12;");
        
        Label changelogTitle = new Label("Changelog");
        changelogTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        HBox changelogContent = new HBox(10);
        changelogContent.setAlignment(Pos.CENTER_LEFT);
        
        // Use system document icon
        ImageView changelogIcon = getSystemIconView("document");
        
        Hyperlink changelogLink = new Hyperlink("View Change Log");
        changelogLink.setOnAction(e -> openUrl("https://github.com/raghul-tech/NotepadXX/blob/master/CHANGELOG.md"));
        
        changelogContent.getChildren().addAll(changelogIcon, changelogLink);
        changelogBox.getChildren().addAll(changelogTitle, changelogContent);
        
        // Contributing Section
        VBox contributeBox = new VBox(10);
        contributeBox.setPadding(new Insets(15));
        contributeBox.setStyle("-fx-background-color: white; -fx-background-radius: 12;");
        
        Label contributeTitle = new Label("Contributing");
        contributeTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        // Create the main text with hyperlinks
        TextFlow textFlow = new TextFlow();
        textFlow.setLineSpacing(5);
        
        Text text1 = new Text("If you like my work, please consider:\n• ");
        Text text2 = new Text("Star this project on GitHub\n• ");
        Text text3 = new Text("Leave me a review ");
        Text text4 = new Text("here\n");
        Text text5 = new Text("Buy Me a Coffee\n\n");
        Text text6 = new Text("If you're interested in contributing, please ");
      //  Text text7 = new Text("contact me");
        Text text8 = new Text(" or submit an issue.");
        
        // Style the regular text
        String defaultStyle = "-fx-font-size: 14px;";
        text1.setStyle(defaultStyle);
        text2.setStyle(defaultStyle);
        text4.setStyle(defaultStyle);
        text5.setStyle(defaultStyle);
        text6.setStyle(defaultStyle);
        text8.setStyle(defaultStyle);
        
        // Create hyperlinks
        Hyperlink reviewLink = new Hyperlink("here");
        reviewLink.setOnAction(e -> openUrl("https://apps.microsoft.com/store/detail/9PL8NMXDXD40?cid=DevShareMCLPCS"));
        
        Hyperlink contactLink = new Hyperlink("contact me");
        contactLink.setOnAction(e -> openUrl("mailto:raghultech.app@gmail.com"));
        
        // Add "Buy Me a Coffee" badge
        try {
            Image coffeeImage = new Image("https://img.shields.io/badge/Support-Donate-yellow.png");
            ImageView coffeeView = new ImageView(coffeeImage);
            coffeeView.setFitHeight(20);
            coffeeView.setFitWidth(100);
            coffeeView.setOnMouseClicked(e -> openUrl("https://buymeacoffee.com/raghultech"));
            coffeeView.setCursor(Cursor.HAND);
            
            HBox coffeeBox = new HBox(5);
            coffeeBox.setAlignment(Pos.CENTER_LEFT);
            coffeeBox.getChildren().addAll(new Text("• "), coffeeView);
            
            textFlow.getChildren().addAll(
                text1, text2, text3, reviewLink, text4, coffeeBox, 
                new Text("\n"), text6, contactLink, text8
            );
        } catch (Exception e) {
            // Fallback if image not found
            Hyperlink coffeeLink = new Hyperlink("Buy Me a Coffee");
            coffeeLink.setOnAction(ev -> openUrl("https://buymeacoffee.com/raghultech"));
            
            textFlow.getChildren().addAll(
                text1, text2, text3, reviewLink, text4, coffeeLink, 
                new Text("\n\n"), text6, contactLink, text8
            );
        }

        // Bug report section
        HBox bugBox = new HBox(10);
        bugBox.setAlignment(Pos.CENTER_LEFT);
        
        Hyperlink bugLink = new Hyperlink("Report a Bug");
        bugLink.setOnAction(e -> openUrl("https://github.com/raghul-tech/NotepadXX/issues/new?template=bug_report.md"));
        
        bugBox.getChildren().addAll(new Text("Found an issue? "), bugLink);

        contributeBox.getChildren().addAll(contributeTitle, textFlow, bugBox);
        
        content.getChildren().addAll(licenseBox, privacyBox, changelogBox, contributeBox);
        
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        tab.setContent(scrollPane);
        
        return tab;
    }

    private static void openUrl(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Helper methods for creating UI components
    private static HBox createModernFooter(JFrame frame) {
        HBox footer = new HBox();
        footer.setPadding(new Insets(15));
        footer.setAlignment(Pos.CENTER_RIGHT);
        footer.setStyle("-fx-background-color: #e5e5ea; -fx-background-radius: 0 0 12 12;");
        
        Button closeButton = createModernButton("Close", "#FF3B30");
        closeButton.setDefaultButton(true);
        closeButton.setOnAction(e -> frame.dispose());
        
        footer.getChildren().add(closeButton);
        return footer;
    }
    
    private static Button createModernButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; " +
                       "-fx-padding: 8 16; -fx-background-radius: 18;");
        button.setEffect(new DropShadow(5, Color.gray(0, 0.3)));
        button.setOnMouseEntered(e -> button.setOpacity(0.9));
        button.setOnMouseExited(e -> button.setOpacity(1.0));
        return button;
    }
    
    private static ImageView createBulletIcon() {
        // Create a simple circle bullet point
        Circle bullet = new Circle(5, Color.web("#007AFF"));
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        WritableImage image = bullet.snapshot(params, null);
        return new ImageView(image);
    }
    
   
    
    private static HBox createFeatureItem(String title, String description) {
        HBox item = new HBox(10);
        item.setAlignment(Pos.CENTER_LEFT);
        
        ImageView bullet = createBulletIcon();
        bullet.setFitWidth(10);
        bullet.setFitHeight(10);
        
        VBox textBox = new VBox(2);
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        Label descLabel = new Label(description);
        descLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #636366;");
        descLabel.setWrapText(true);
        
        textBox.getChildren().addAll(titleLabel, descLabel);
        item.getChildren().addAll(bullet, textBox);
        
        return item;
    }
     
    private static void addShortcutSection(GridPane grid, String title, int col, int row, String[][] shortcuts) {
        VBox section = new VBox(8);
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #007AFF;");
        section.getChildren().add(titleLabel);
        
        for (String[] shortcut : shortcuts) {
            HBox item = new HBox(15);
            
            Label keyLabel = new Label(shortcut[0]);
            keyLabel.setStyle("-fx-font-family: 'Menlo'; -fx-font-weight: bold; -fx-font-size: 13px; " +
                            "-fx-background-color: #e5e5ea; -fx-padding: 2 8; -fx-background-radius: 4;");
            
            Label descLabel = new Label(shortcut[1]);
            descLabel.setStyle("-fx-font-size: 13px;");
            
            item.getChildren().addAll(keyLabel, descLabel);
            section.getChildren().add(item);
        }
        
        grid.add(section, col, row);
    }
    
    private static VBox createModernFeatureCard(String title, String content, ImageView icon) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12;");
        
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);
        
        icon.setFitWidth(24);
        icon.setFitHeight(24);
        header.getChildren().add(icon);
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        header.getChildren().add(titleLabel);
        
        TextArea contentArea = new TextArea(content);
        contentArea.setEditable(false);
        contentArea.setWrapText(true);
        contentArea.setStyle("-fx-font-size: 14px; -fx-background-color: transparent; -fx-border-color: transparent;");
        
        card.getChildren().addAll(header, contentArea);
        return card;
    }
    
    private static VBox createThemeCard(String name, String desc, Color color) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #f5f5f7; -fx-background-radius: 8;");
        card.setAlignment(Pos.CENTER);
        
        // Create theme preview
        Rectangle preview = new Rectangle(48, 48);
        preview.setFill(color);
        preview.setArcWidth(12);
        preview.setArcHeight(12);
        preview.setStroke(Color.LIGHTGRAY);
        preview.setStrokeWidth(1);
        
        card.getChildren().add(preview);
        
        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-weight: bold;");
        
        Label descLabel = new Label(desc);
        descLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #636366;");
        
        card.getChildren().addAll(nameLabel, descLabel);
        return card;
    }
    
    private static VBox createLanguageCard(String language, String extension) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(10));
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: #f5f5f7; -fx-background-radius: 8;");

        // Try to get language icon
        ImageView iconView;
        try {
            // Get the Swing Icon from your GetImage class
            Icon swingIcon = GetImage.getImage(extension.toLowerCase());
            
            // Convert Swing Icon to JavaFX Image
            java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(
                swingIcon.getIconWidth(),
                swingIcon.getIconHeight(),
                java.awt.image.BufferedImage.TYPE_INT_ARGB);
            swingIcon.paintIcon(null, bi.getGraphics(), 0, 0);
            Image fxImage = SwingFXUtils.toFXImage(bi, null);
            
            iconView = new ImageView(fxImage);
        } catch (Exception e) {
            // Fallback - create a simple icon with the extension text
            StackPane fallbackIcon = new StackPane();
            Rectangle bg = new Rectangle(32, 32);
            bg.setFill(Color.WHITE);
            bg.setStroke(Color.LIGHTGRAY);
            bg.setArcWidth(8);
            bg.setArcHeight(8);
            
            Text extText = new Text(extension.length() > 4 ? 
                                  extension.substring(0, 4) : extension);
            extText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            extText.setFill(Color.web("#007AFF"));
            
            fallbackIcon.getChildren().addAll(bg, extText);
            
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage image = fallbackIcon.snapshot(params, null);
            iconView = new ImageView(image);
        }

        // Configure the image view
     //   iconView.setFitWidth(32);
       // iconView.setFitHeight(32);
        iconView.setPreserveRatio(true);
        iconView.setSmooth(true);

        // Add the icon and labels to the card
        card.getChildren().add(iconView);
        
        Label langLabel = new Label(language);
        langLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        
        Label extLabel = new Label("." + extension);
        extLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #636366;");
        
        card.getChildren().addAll(langLabel, extLabel);
        return card;
    }
    private static ImageView getSystemIconView(String type) {
        if (type.equals("privacy")) {
            return new ImageView(getSystemIcon("privacy"));
        } else {
            return new ImageView(getSystemIcon("document"));
        }
    }
    
    private static Image getSystemIcon(String type) {
        // Use Swing's FileSystemView to get system icons
        FileSystemView fsv = FileSystemView.getFileSystemView();
        Icon swingIcon;
        
        if (type.equals("privacy")) {
            swingIcon = UIManager.getIcon("OptionPane.informationIcon");
        } else {
            swingIcon = fsv.getSystemIcon(new File(System.getProperty("user.home")));
        }
        
        // Convert Swing Icon to JavaFX Image
        java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(
            swingIcon.getIconWidth(),
            swingIcon.getIconHeight(),
            java.awt.image.BufferedImage.TYPE_INT_ARGB);
        
        swingIcon.paintIcon(null, bi.getGraphics(), 0, 0);
        
        return SwingFXUtils.toFXImage(bi, null);
    }
    
    // Data initialization methods
    private static Map<String, List<String>> createLanguageMap() {
        Map<String, List<String>> map = new LinkedHashMap<>();
        map.put("A", Arrays.asList("ActionScript", "Assembly x86", "Assembly 6502"));
        map.put("B", Arrays.asList("BBCode", "Batch File", "Bash"));
        map.put("C", Arrays.asList("C", "C++", "C#", "CSS", "Clojure", "CSV"));
        map.put("D", Arrays.asList("D", "Dart", "Delphi", "Dockerfile", "DTD"));
        map.put("F", Collections.singletonList("Fortran"));
        map.put("G", Arrays.asList("Groovy", "Go"));
        map.put("H", Arrays.asList("HTML", "Handlebars", "Hosts", "Htaccess"));
        map.put("I", Arrays.asList("IML", "INI", "ipynb"));
        map.put("J", Arrays.asList("Java", "JavaScript", "JSP", "JSON"));
        map.put("K", Collections.singletonList("Kotlin"));
        map.put("L", Arrays.asList("Lua", "Less", "LISP", "LaTeX"));
        map.put("M", Arrays.asList("Markdown", "Makefile", "MXML"));
        map.put("N", Collections.singletonList("NSIS"));
        map.put("P", Arrays.asList("Python", "PHP", "Perl", "Proto", "Properties file"));
        map.put("R", Arrays.asList("Ruby", "Rust","React"));
        map.put("S", Arrays.asList("SQL", "Scala", "SAS"));
        map.put("T", Arrays.asList("TCL", "TypeScript"));
        map.put("V", Collections.singletonList("Visual Basic"));
        map.put("X", Collections.singletonList("XML"));
        map.put("Y", Collections.singletonList("YAML"));
        return map;
    }
    
    private static Map<String, List<String>> createSupportedExtensions() {
    	Map<String, List<String>> languageExtensionsMap = new HashMap<>();

    	languageExtensionsMap.put("HTML", Arrays.asList("html","htm"));
    	languageExtensionsMap.put("CSS", Arrays.asList("css"));
    	languageExtensionsMap.put("JavaScript", Arrays.asList("js","mjs","cjs"));
    	languageExtensionsMap.put("Java", Arrays.asList("java","jsp"));
    	languageExtensionsMap.put("Python", Arrays.asList("py","pyw","pyd"));
    	languageExtensionsMap.put("ipynb", Arrays.asList("ipynb"));
    	languageExtensionsMap.put("C", Arrays.asList("c"));
    	languageExtensionsMap.put("C++", Arrays.asList("cpp","hpp","h","cxx","cc"));
    	languageExtensionsMap.put("C#", Arrays.asList("cs"));
    	languageExtensionsMap.put("XML", Arrays.asList("xml"));
    	languageExtensionsMap.put("IML", Arrays.asList("iml"));
    	languageExtensionsMap.put("YAML", Arrays.asList("yml", "yaml"));
    	languageExtensionsMap.put("JSON", Arrays.asList("json"));
    	languageExtensionsMap.put("Markdown", Arrays.asList("md", "markdown","mdown","mkd","mkdn","mdtext"));
    	languageExtensionsMap.put("PHP", Arrays.asList("php","phps"));
    	languageExtensionsMap.put("SQL", Arrays.asList("sql"));
    	languageExtensionsMap.put("Shell", Arrays.asList("sh","bash","zsh","csh"));
    	languageExtensionsMap.put("Ruby", Arrays.asList("rb"));
    	languageExtensionsMap.put("React", Arrays.asList("jsx","tsx"));
    	languageExtensionsMap.put("Groovy", Arrays.asList("groovy"));
    	languageExtensionsMap.put("Scala", Arrays.asList("scala"));
    	languageExtensionsMap.put("LaTeX", Arrays.asList("tex","sty","dtx","bib"));
    	languageExtensionsMap.put("ActionScript", Arrays.asList("as"));
    	languageExtensionsMap.put("BBCode", Arrays.asList("bbcode"));
    	languageExtensionsMap.put("Assembly x86", Arrays.asList("asm", "s", "a51","a","lst"));
    	languageExtensionsMap.put("Assembly 6502", Arrays.asList("a65", "inc"));
    	languageExtensionsMap.put("Batch File", Arrays.asList("bat","cmd"));
    	languageExtensionsMap.put("CSV", Arrays.asList("csv"));
    	languageExtensionsMap.put("D", Arrays.asList("d"));
    	languageExtensionsMap.put("Dart", Arrays.asList("dart"));
    	languageExtensionsMap.put("Delphi", Arrays.asList("pas", "dpr", "dfr", "dpm"));
    	languageExtensionsMap.put("Dockerfile", Arrays.asList("dockerfile"));
    	languageExtensionsMap.put("DTD", Arrays.asList("dtd"));
    	languageExtensionsMap.put("Clojure", Arrays.asList("clj", "cljs", "cljc", "clojure"));
    	languageExtensionsMap.put("Fortran", Arrays.asList("f", "for", "f90", "f95", "f77", "f03", "f08", "f18", "mod", "ftn", "fpp"));
    	languageExtensionsMap.put("Go", Arrays.asList("go"));
    	languageExtensionsMap.put("Handlebars", Arrays.asList("hbs", "handlebars"));
    	languageExtensionsMap.put("Hosts", Arrays.asList("hosts"));
    	languageExtensionsMap.put("Htaccess", Arrays.asList("htaccess"));
    	languageExtensionsMap.put("INI", Arrays.asList("ini","cfg","conf"));
    	languageExtensionsMap.put("JSP", Arrays.asList("jsp"));
    	languageExtensionsMap.put("Kotlin", Arrays.asList("kt", "kts"));
    	languageExtensionsMap.put("Less", Arrays.asList("less"));
    	languageExtensionsMap.put("LISP", Arrays.asList("lisp", "lsp"));
    	languageExtensionsMap.put("Lua", Arrays.asList("lua"));
    	languageExtensionsMap.put("Makefile", Arrays.asList("makefile", "Makefile"));
    	languageExtensionsMap.put("MXML", Arrays.asList("mxml"));
    	languageExtensionsMap.put("NSIS", Arrays.asList("nsi"));
    	languageExtensionsMap.put("Perl", Arrays.asList("pl", "pm", "t", "cgi","perl","pxl"));
    	languageExtensionsMap.put("Properties file", Arrays.asList("properties"));
    	languageExtensionsMap.put("Proto", Arrays.asList("proto"));
    	languageExtensionsMap.put("Rust", Arrays.asList("rs"));
    	languageExtensionsMap.put("SAS", Arrays.asList("sas", "sas7bdat", "sas7bvew", "sas7bcat"));
    	languageExtensionsMap.put("TCL", Arrays.asList("tcl"));
    	languageExtensionsMap.put("TypeScript", Arrays.asList("ts"));
    	languageExtensionsMap.put("Visual Basic", Arrays.asList("vb", "vbs", "bas", "frm", "cls"));
    	languageExtensionsMap.put("Batch File", Arrays.asList("bat", "cmd"));
    	languageExtensionsMap.put("Bash", Arrays.asList("sh","bash","zsh","csh"));
    	languageExtensionsMap.put("Plain Text", Arrays.asList("txt"));

    	return languageExtensionsMap;
    }
    
    private static Map<String,String> CodeAnalysisSupportedLanguage(){
    	 Map<String,String> SUPPORTED_EXTENSIONS = new HashMap<>();
         SUPPORTED_EXTENSIONS.put("Java","java"); 
         SUPPORTED_EXTENSIONS.put("Python","py");
         SUPPORTED_EXTENSIONS.put("ipynb","ipynb");
         SUPPORTED_EXTENSIONS.put("CSS","css");
         SUPPORTED_EXTENSIONS.put("HTML","html");
         SUPPORTED_EXTENSIONS.put("JavaScript","js");
         SUPPORTED_EXTENSIONS.put("C","c");
         SUPPORTED_EXTENSIONS.put("C++","cpp");
         SUPPORTED_EXTENSIONS.put("CSV","csv");
         SUPPORTED_EXTENSIONS.put("C#","cs");
         SUPPORTED_EXTENSIONS.put("JSON","json");
         SUPPORTED_EXTENSIONS.put("SQL","sql");
         SUPPORTED_EXTENSIONS.put("GO","go");
         SUPPORTED_EXTENSIONS.put("XML","xml");
         SUPPORTED_EXTENSIONS.put("IML","iml");
         SUPPORTED_EXTENSIONS.put("PHP","php");
         SUPPORTED_EXTENSIONS.put("Rust","rs");
         SUPPORTED_EXTENSIONS.put("Dart","dart");
         SUPPORTED_EXTENSIONS.put("Kotlin","kt");
         return SUPPORTED_EXTENSIONS;
    }
    
}