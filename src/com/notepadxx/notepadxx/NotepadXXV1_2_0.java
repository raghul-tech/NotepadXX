package com.notepadxx.notepadxx;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.notepadxx.exit.CLOSE;
import com.notepadxx.exit.ExitFrame;
import com.notepadxx.flatlaf.theme.Themes;
import com.notepadxx.menu.Menubar;
import com.notepadxx.menu.PreferenceMenu;
import com.notepadxx.menu.ThemesMenuBar;
import com.notepadxx.open.LazyLoading;
import com.notepadxx.resources.icon.GetImage;
import com.notepadxx.rsyntaxtextarea.syntaxhightlighter.SyntaxHighlighter;
import com.notepadxx.toolbar.Toolbar;
import com.notepadxx.utils.LinuxUtils;
import com.notepadxx.utils.WindowsUtils;

import javafx.application.Platform; 

 
public class  NotepadXXV1_2_0 {
    private static final String APP_DIR = ConfigFiles.getAppDir();
    private static final String CONFIG_FILE_THEME = ConfigFiles.getConfigFileTheme();
    private static final String CONFIG_FILE_SIZE = ConfigFiles.getConfigFileSize();
    private static final String CONFIG_FILE_OPEN_FILES = ConfigFiles.getConfigFileOpenFiles();
    private static final String CONFIG_FILE_RECENT_CLOSED = ConfigFiles.getConfigFileRecentClosed();
    private static final String appName = "NotepadXX";
    private static JFrame frame;
    private static int newTabCount = 1;
    private static JTabbedPane tabbedPane;
   // private static NotepadXXV1_2_0 mainInstance = new NotepadXXV1_2_0();
    private static SplashScreen splash = null;
    private static List<String> openFiles = loadOpenFilesFromConfig( );
//private static  String theme = loadThemePreference();
    public static void main (String args[]) {
    	try {
    		 if (!Platform.isFxApplicationThread()) {
    	 Platform.startup(() -> {
             Platform.setImplicitExit(false);  
            
           });
    		 }
    	}catch(Exception e) {
    	//	System.setProperty("javafx.platform", "Desktop");
    	}
    	
        int maxFiles = 100;
        File appDir = new File(CONFIG_FILE_OPEN_FILES);
        if (openFiles.size() > maxFiles || !appDir.exists()) {
        	      MainMethod2(args);
        }else {
       	MainMethod1(args);
        }
       
    	//MainMethod1(args);
    }
    
	private static List<String> loadOpenFilesFromConfig() {
	    List<String> openFilesList = new ArrayList<>();
	    Properties savedFiles = new Properties();
	    try {
	        File openFiles = new File(CONFIG_FILE_OPEN_FILES);
	        if (openFiles.exists()) {
	            try (FileInputStream input = new FileInputStream(openFiles)) {
	                savedFiles.load(input);
	                // Load the files into the list, assuming savedFiles stores file paths with unique keys
	                for (String key : savedFiles.stringPropertyNames()) {
	                    openFilesList.add(savedFiles.getProperty(key));
	                }
	            }
	        }
	    } catch (IOException e) {}
	   
	    return openFilesList;
	   
	}

	private static void MainMethod1(String[] args) {
		 SwingUtilities.invokeLater(() -> {
			
	            
	        ensureAppDirectoryExists();
	        String theme = loadThemePreference();
	  	      // Themes.applyTheme(theme);
	  	       ThemesMenuBar.applyTheme(theme, ThemesMenuBar.themesMenuBar());

	            frame = new JFrame(appName);
	            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	          
	         // Load and set window size and position
	            Dimension windowSize = loadWindowSize();
	            if (windowSize != null) {
	                if (windowSize.width < 100 || windowSize.height < 100) {
	                    frame.setSize(1200, 900); // Default size if the loaded size is invalid
	                    frame.setLocationRelativeTo(null);

	                } else {
	                    frame.setSize(windowSize); 
	                }
	            } else {
	                frame.setSize(1200, 900);
	                frame.setLocationRelativeTo(null);

	            }

	            frame.setLayout(new BorderLayout());
	          //  frame.setIconImage(new ImageIcon(mainInstance.getClass().getResource("/icons/LogoX.png")).getImage());
	            ImageIcon icon = new ImageIcon(new NotepadXXV1_2_0().getClass().getResource("/icons/LogoX.png"));
	            Image scaledImage = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	            frame.setIconImage(scaledImage);
	            
	            frame.getContentPane().setBackground(new Color(0, 0, 0, 0)); // Fully transparent background
	            
	            // Create a JTabbedPane
	            tabbedPane = new JTabbedPane();
	           tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); // Set scroll layout

	           TabDragAndDropHandler.enableTabDragAndDrop(tabbedPane);

	            // Create a panel to hold both the menu and toolbar
	            JPanel topPanel = new JPanel(new BorderLayout());
	            JMenuBar menuBar = Menubar.createMenuBar();
	            topPanel.add(menuBar, BorderLayout.NORTH); // Add the menu bar to the top panel
	      
	            // Create the toolbar
	            JToolBar toolBar = Toolbar.createToolBar();
	            toolBar.setFloatable(false);
	            topPanel.add(toolBar, BorderLayout.SOUTH); // Add the toolbar to the bottom of the top panel

	            frame.add(topPanel, BorderLayout.NORTH);

	            // Create a panel for the button
	            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	            initializeNewTabButton(buttonPanel); // Initialize button and add to the panel

	            // Create a panel to hold the tabbed pane and button
	            JPanel mainPanel = new JPanel(new BorderLayout());
	            mainPanel.add(tabbedPane, BorderLayout.CENTER);
	            mainPanel.add(buttonPanel, BorderLayout.EAST); // Add button panel to the right of the tabbed pane
	            frame.add(mainPanel, BorderLayout.CENTER);

	            
	         //   loadOpenedFiles();
	          //  SwingUtilities.invokeLater(() -> {    
	          loadOpenedFiles(()->{
	        	//  SwingUtilities.invokeLater(() -> {
	               
	                if (args.length > 0) {
	                    String filePath = args[0];
	                    try {
	                        File fileToOpen = new File(filePath);
	                        if (fileToOpen.exists() && fileToOpen.isFile()) {
	                            openNewTab(fileToOpen);
	                        } else {
	                            JOptionPane.showMessageDialog(frame, "Invalid file: " + filePath);
	                        }
	                    } catch (Exception ex) {
	                        JOptionPane.showMessageDialog(frame, "Error opening file: " + ex.getMessage());
	                    }
	                }
	       
	                // Ensure default tab opens last
	                checkAndOpenDefaultTab();
	             //   SwingUtilities.invokeLater(() -> {
	               // updateLineWrapForAllTabs(PreferenceMenu.isWordWrapEnabled());
	               //});
	             //   SwingUtilities.invokeLater(() -> {
	                  //   Timer timer = new Timer(100, e -> {                   
                         frame.setResizable(true);
                         frame.setVisible(true);
                         startUpdateScheduler();
                    /*     SwingUtilities.invokeLater(() -> {
                        	    ChangeEvent event = new ChangeEvent(tabbedPane);
                        	    for (ChangeListener listener : tabbedPane.getChangeListeners()) {
                        	        listener.stateChanged(event);  // ✅ manually trigger it
                        	    }
                        	});*/
                          
                   //      });
         	         //   timer.setRepeats(false);
         	           // timer.start();
	      //   });
	          });
	          
	           
	            frame.addWindowListener(new java.awt.event.WindowAdapter() {
	                @Override
	                public void windowClosing(java.awt.event.WindowEvent e) {
	                	
	                	 saveOpenedFiles();
	                	 saveWindowSize(frame.getSize());  
	                    ExitFrame.handleWindowClosing(frame);
	                	//texteditor().exit();
	               
	                }
	            });
	            
	            // Add mouse listener to tabbedPane to open a new tab on click
	            tabbedPane.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    // Check if the click occurred on the tab area
	                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) { // Check for left mouse button
	                     openNewTab(null);
	                    }
	                }

	                
	            });

	  tabbedPane.addChangeListener(e -> {
	            	
	                int selectedIndex = tabbedPane.getSelectedIndex();
	                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                        Component component = tabbedPane.getComponentAt(i);
                        if (component instanceof Texteditor editor) {
                            if (i == selectedIndex ) {
                                editor.resumeEditor(editor);  // Resume only the selected tab
                                
                            } else {
                                editor.pauseEditor(editor);   // Pause inactive tabs
                            }
                        }
                    }
	                
	               // if (selectedIndex >= 0 && selectedIndex < tabbedPane.getTabCount()) {
	                 //   Texteditor selectedEditor = (Texteditor) tabbedPane.getComponentAt(selectedIndex);

	                Component selectedComponent = tabbedPane.getComponentAt(selectedIndex);
	                if(selectedComponent == null) checkAndOpenDefaultTab();
	                if (selectedComponent instanceof Texteditor selectedEditor) {
	                    if (selectedEditor != null) {
	                    	
	                        // Use existing UndoManager if available
	                     /*   UndoManager selectedManager = selectedEditor.getUndoManager();
	                        if (selectedManager == null) {
	                            selectedManager = new UndoManager();
	                            selectedEditor.setUndoManager(selectedManager);
	                        }*/
	                    	 if (selectedEditor.getUndoManager() == null) {
	                             selectedEditor.setUndoManager(new UndoManager());
	                         }
	                       
	                        // Update the tab and window titles
	                        selectedEditor.updateTabTitle();
	                        selectedEditor.updateTitle(selectedEditor.getCurrentFile() != null ?
	                            selectedEditor.getCurrentFile().getAbsolutePath() : selectedEditor.getTabTitle());
	             //      if(frame.isVisible() && selectedEditor.getCurrentFile() != null)     selectedEditor.fileSizeToOpen(selectedEditor.getCurrentFile());
	                       // new Themes(selectedEditor);
	                        Themes.init(selectedEditor);
	                        Themes.applyFont(Themes.getTheme()); 
	                      Menubar.MenuComponentChange();
	                      
	                      if(selectedEditor.getCurrentFile() != null) {
	                   //   String tabName = selectedEditor.getCurrentFile().toString();
	                     // Menubar.EntryExist(selectedEditor,tabName);
	                    	  Menubar.EntryExist(selectedEditor, selectedEditor.getCurrentFile().toString());
	                    	 
	                      }
	                    }
	                } 
	            
	            });
	  
	          //  startUpdateScheduler();
	       //    frame.setResizable(true);
	        //   frame.setVisible(true);
	           
	          });

	       
		 }
	
	private static void MainMethod2(String[] args) {
	    SwingUtilities.invokeLater(() -> {
	        // Step 1: Show Splash Screen
	        splash = new SplashScreen();
	        
	     //   if(openFiles.size()<10) splash.animateProgressBar();
 
	        // Step 2: Run background process for loading resources
	        SwingWorker<Void, Void> worker = new SwingWorker<>() {
	        	
	            @Override
	            protected Void doInBackground() {
	         //   	  long startTime = System.nanoTime(); 
	                try {
	                      // Ensure splash screen is visible for at least 2 seconds
	                    Thread.sleep(2000);

	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            //    splash.fadeOutAndDispose();
	             //   long splashTime = System.nanoTime(); // Splash screen end time
	               // System.out.println("Splash Screen Time: " + (splashTime - startTime) / 1_000_000 + " ms");

	                try {
	                //	 long resourceLoadStart = System.nanoTime();
	                    // Simulate loading tasks
	                    ensureAppDirectoryExists();
	                    String theme = loadThemePreference();
	                    ThemesMenuBar.applyTheme(theme, ThemesMenuBar.themesMenuBar());
	                    
	                    // Step 4: Create and show main application window
	                    frame = new JFrame(appName);
	                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                //    ThemesMenuBar.applyTheme(theme, ThemesMenuBar.themesMenuBar());
	                    
	                    // Load and set window size and position
	                    Dimension windowSize = loadWindowSize();
	                    if (windowSize != null && windowSize.width >= 100 && windowSize.height >= 100) {
	                        frame.setSize(windowSize);
	                    } else {
	                        frame.setSize(1200, 900);
	                        frame.setLocationRelativeTo(null);
	                    }

	                    frame.setLayout(new BorderLayout());
	                 //   frame.setIconImage(new ImageIcon(getClass().getResource("/icons/NotepadXXLogo.png")).getImage());
	                    ImageIcon icon = new ImageIcon(getClass().getResource("/icons/LogoX.png"));
	    	            Image scaledImage = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	    	            frame.setIconImage(scaledImage);
	                    
	                    
	                    // Create JTabbedPane
	                    tabbedPane = new JTabbedPane();
	                    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	                    TabDragAndDropHandler.enableTabDragAndDrop(tabbedPane);
	                    
	                    // Load opened files
	                  //  long fileLoadStart = System.nanoTime();
	                
	        
	      //         splash.waitForLoadingToComplete(() -> {   
	            	  loadOpenedFiles(() -> {
	            		   SwingUtilities.invokeLater(() -> {
	            		//	   ThemesMenuBar.applyTheme(theme, ThemesMenuBar.themesMenuBar());
	            			   splash.setprogressValue(100);
                       //    long fileLoadEnd = System.nanoTime();
                         //  System.out.println("File Load Time: " + (fileLoadEnd - fileLoadStart) / 1_000_000 + " ms");

                           if (args.length > 0) {
                               String filePath = args[0];
                               File fileToOpen = new File(filePath);
                               if (fileToOpen.exists() && fileToOpen.isFile()) {
                                   openNewTab(fileToOpen);
                               } else {
                                   JOptionPane.showMessageDialog(frame, "Invalid file: " + filePath);
                               }
                           }
                           checkAndOpenDefaultTab();
                          // SwingUtilities.invokeLater(() -> {
           	            //    updateLineWrapForAllTabs(PreferenceMenu.isWordWrapEnabled());
           	               //});
                          // splash.dispose(); // Dispose of splash screen only after UI updates are done
	                     
	                         Timer timer = new Timer(50, e -> {
	                        	 splash.setprogressValue(100);
	                        	 splash.dispose();
	                         frame.setResizable(true);
	                         frame.setVisible(true);
	                         startUpdateScheduler();
	                     /*    SwingUtilities.invokeLater(() -> {
	                        	    ChangeEvent event = new ChangeEvent(tabbedPane);
	                        	    for (ChangeListener listener : tabbedPane.getChangeListeners()) {
	                        	        listener.stateChanged(event);  // ✅ manually trigger it
	                        	    }
	                        	})*/
	                         
	                         });
	         	            timer.setRepeats(false);
	         	            timer.start();
	            		   });  
                        });
	             //  });
		            
	              
	                    // Create Menu and Toolbar
	                    JPanel topPanel = new JPanel(new BorderLayout());
	                    JMenuBar menuBar = Menubar.createMenuBar();
	                    topPanel.add(menuBar, BorderLayout.NORTH);
	                    JToolBar toolBar = Toolbar.createToolBar();
	                    toolBar.setFloatable(false);
	                    topPanel.add(toolBar, BorderLayout.SOUTH);
	                    frame.add(topPanel, BorderLayout.NORTH);
	                    SwingUtilities.updateComponentTreeUI(menuBar);
	                    // Create panel for tabbed pane and button
	                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	                    initializeNewTabButton(buttonPanel);
	                    JPanel mainPanel = new JPanel(new BorderLayout());
	                    mainPanel.add(tabbedPane, BorderLayout.CENTER);
	                    mainPanel.add(buttonPanel, BorderLayout.EAST);
	                    frame.add(mainPanel, BorderLayout.CENTER);

	                
	               //     splash.fadeOutAndDispose();
	                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
	                        @Override
	                        public void windowClosing(java.awt.event.WindowEvent e) {
	                            saveOpenedFiles();
	                            saveWindowSize(frame.getSize());
	                             ExitFrame.handleWindowClosing(frame);
	                        }
	                    });

	                    // Double-click to open new tab
	                    tabbedPane.addMouseListener(new MouseAdapter() {
	                        @Override
	                        public void mouseClicked(MouseEvent e) {
	                            if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
	                                openNewTab(null);
	                            }
	                        }
	                       
	                            
	                    });

	                    // Tab Change Listener
	                    tabbedPane.addChangeListener(e -> {
	                        int selectedIndex = tabbedPane.getSelectedIndex();
	                        
	                        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
	                            Component component = tabbedPane.getComponentAt(i);
	                            if (component instanceof Texteditor editor) {
	                                if (i == selectedIndex) {
	                                    editor.resumeEditor(editor);  // Resume only the selected tab
	                                } else {
	                                    editor.pauseEditor(editor);   // Pause inactive tabs
	                                }
	                            }
	                        }
	                        
	                        Component selectedComponent = tabbedPane.getComponentAt(selectedIndex);
	                        if(selectedComponent == null) checkAndOpenDefaultTab();
	                        if (selectedComponent instanceof Texteditor selectedEditor) {
	                            if (selectedEditor != null) {
	                              /*  UndoManager selectedManager = selectedEditor.getUndoManager();
	                                if (selectedManager == null) {
	                                    selectedManager = new UndoManager();
	                                    selectedEditor.setUndoManager(selectedManager);
	                                }*/
	                            	 if (selectedEditor.getUndoManager() == null) {
	                                     selectedEditor.setUndoManager(new UndoManager());
	                                 }
	                                selectedEditor.updateTabTitle();
	                                selectedEditor.updateTitle(selectedEditor.getCurrentFile() != null ?
	                                        selectedEditor.getCurrentFile().getAbsolutePath() : selectedEditor.getTabTitle());
	                            
	                              //  selectedEditor.fileSizeToOpen(selectedEditor.getCurrentFile());
	                                
	                             //   new Themes(selectedEditor);
	                                Themes.init(selectedEditor);
	                                Themes.applyFont(Themes.getTheme());
	                                Menubar.MenuComponentChange();
	                                
	                                if(selectedEditor.getCurrentFile() != null) {
	          	                   //   String tabName = selectedEditor.getCurrentFile().toString();
	          	                     // Menubar.EntryExist(selectedEditor,tabName);
	                                	Menubar.EntryExist(selectedEditor, selectedEditor.getCurrentFile().toString());
	          	                      }
	                                
	                            }
	                        }
	                     //   updateTabAppearances();
	                    });
	                 
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(frame, "Error loading application: " + ex.getMessage());
	                }
	            
  
	                return null; // No result needed
	            }

	            @Override
	            protected void done() {
	            	try {
	            		
						get();
					/*	SwingUtilities.invokeLater(() ->{
	                         splash.dispose(); // Dispose of splash screen only after UI updates are done
	                         startUpdateScheduler();
	                         frame.setResizable(true);
	                         frame.setVisible(true);
	                     });*/
	            		  
					} catch (Error | InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		         
	            }
	        };

	        worker.execute(); // Start background loading
	    });
	}    
	
	public static void openNewTab(File file) {
	    if (file != null) {
	        int existingTabIndex = getOpenTabIndex(file);
	        if (existingTabIndex != -1) {
	            tabbedPane.setSelectedIndex(existingTabIndex);
	            return;
	        }
	    }

	    Texteditor editorForTab = new Texteditor();
	    UndoManager undoManagerForTab = new UndoManager();
        editorForTab.setUndoManager(undoManagerForTab);
        // new Themes(editorForTab);
        Themes.init(editorForTab);
         Themes.applyFont(Themes.getTheme());
       //  SwingUtilities.updateComponentTreeUI(editorForTab);

        SwingUtilities.invokeLater(() -> {
        String fileExtension = (file != null ? editorForTab.getFileExtension(file) : null);
        SyntaxHighlighter syntaxHighlighter = new SyntaxHighlighter(editorForTab.getTextArea());
        if (fileExtension != null) {
            syntaxHighlighter.setFileExtension(fileExtension);
            syntaxHighlighter.setSaved(true);
        }
 
        editorForTab.setSyntaxHighlighter(syntaxHighlighter);
      //  checker.checkSyntax(fileExtension);
        });
        // Add the editor to the tabbedPane first to get a valid index
     //   tabbedPane.add(editorForTab); // Add editor before getting index
      //  int tabIndex = tabbedPane.indexOfComponent(editorForTab);

            if (file != null) {
            	// SwingUtilities.invokeLater(() -> {
                editorForTab.setCurrentFile(file); // Associate the file
                editorForTab.setTabTitle(file.getName()); // Set file name as title
                editorForTab.fileSizeToOpen(file);
                
              
            } else {
                editorForTab.setTabTitle("new " + (newTabCount++)+" ");
            }
           
	    String tabTitle = editorForTab.getTabTitle();
	    Icon tabIcon = GetImage.getImage(file);

	    // Add tab with icon
	    tabbedPane.addTab(tabTitle, tabIcon, editorForTab);
	    int tabIndex = tabbedPane.indexOfComponent(editorForTab);

	    // Create custom tab component
	    JPanel tabPanel = new JPanel(new BorderLayout());
	    tabPanel.setOpaque(false);
	    
	    JLabel titleLabel = new JLabel(tabTitle, tabIcon, SwingConstants.LEADING);
	    titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
	    
	    JButton closeButton = createCloseButton(editorForTab);
	    
	    tabPanel.add(titleLabel, BorderLayout.CENTER);
	    tabPanel.add(closeButton, BorderLayout.EAST);
	    
	    tabbedPane.setTabComponentAt(tabIndex, tabPanel);
	    tabbedPane.setSelectedIndex(tabIndex);
	    
	  
	}
	
	private static JButton createCloseButton(Texteditor editor) {
	    JButton closeButton = new JButton("×");
	    closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
	    closeButton.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
	    closeButton.setContentAreaFilled(false);
	    closeButton.addActionListener(e -> CLOSE.closeTab(editor));
	    
	    closeButton.addMouseListener(new MouseAdapter() {
	        public void mouseEntered(MouseEvent e) { 
	            closeButton.setForeground(Color.RED); 
	        }
	        public void mouseExited(MouseEvent e) { 
	            closeButton.setForeground(null); 
	        }
	    });
	    
	    return closeButton;
	}
		
	public static Texteditor  openOutOfMemoryErrorTab(File file) {

		/* if (file != null) {
		        int existingTabIndex = getOpenTabIndex(file);

		        if (existingTabIndex != -1) {
		            tabbedPane.setSelectedIndex(existingTabIndex); // Switch to existing tab
		            return null;
		            
		        }
		    }*/
       Texteditor editorForTab = new Texteditor();

       UndoManager undoManagerForTab = new UndoManager();
       editorForTab.setUndoManager(undoManagerForTab);
    //    new Themes(editorForTab);
       Themes.init(editorForTab);
        Themes.applyFont(Themes.getTheme());
    
       // Add the editor to the tabbedPane first to get a valid index
    //   tabbedPane.add(editorForTab); // Add editor before getting index
      // int tabIndex = tabbedPane.indexOfComponent(editorForTab);

           if (file != null) {
           	// SwingUtilities.invokeLater(() -> {
               editorForTab.setCurrentFile(file); // Associate the file
               editorForTab.setTabTitle(file.getName()); // Set file name as title
               LazyLoading load = new LazyLoading();
     		  load.loadFile(file, editorForTab.getTextArea(), editorForTab);
     		  editorForTab.pauseEditor(editorForTab);
           
     		  //	 });
           } else {
               editorForTab.setTabTitle("new " + (newTabCount++)+" ");
           }
           
           
           // Update window title
        editorForTab.updateTitle(file != null ? file.getAbsolutePath() : editorForTab.getTabTitle());//"Untitled");

        String tabTitle = editorForTab.getTabTitle();
	    Icon tabIcon = GetImage.getImage(file);

	    // Add tab with icon
	    tabbedPane.addTab(tabTitle, tabIcon, editorForTab);
	    int tabIndex = tabbedPane.indexOfComponent(editorForTab);
        
       JPanel tabPanel = new JPanel(new BorderLayout());
       tabPanel.setOpaque(false);
   
       JLabel titleLabel = new JLabel(editorForTab.getTabTitle());
       titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
    /*   JButton closeButton = new JButton("x");
      // SwingUtilities.invokeLater(() -> {
       closeButton.setFont(new Font("Arial", Font.BOLD, 12));
       closeButton.setMargin(new Insets(0, 5, 0, 5));
       closeButton.setBorder(null);
       closeButton.setFocusPainted(false);
    //   });

       closeButton.addActionListener(e -> editorForTab.closeTab());*/
       JButton closeButton = createCloseButton(editorForTab);
       
       tabPanel.add(titleLabel, BorderLayout.WEST);
       tabPanel.add(closeButton, BorderLayout.EAST);
       
       tabbedPane.setTabComponentAt(tabIndex, tabPanel);
       tabbedPane.setSelectedComponent(editorForTab);    
 
     //  tabbedPane.setTitleAt(tabIndex, editorForTab.getTabTitle());
		   //});
       //SwingUtilities.updateComponentTreeUI(closeButton);
      // frame.revalidate();
       //frame.repaint();
   return editorForTab;
	}
	
	
	
	/**
	 * Checks if a file is already open and returns its tab index.
	 */
	private static int getOpenTabIndex(File file) {
	    String filePath;
	    try {
	        filePath = file.getCanonicalPath(); // Get absolute file path
	    } catch (IOException e) {
	        return -1; // If path retrieval fails, assume file is not open
	    }

	    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
	      //  Texteditor editor = (Texteditor) tabbedPane.getComponentAt(i);
	        Component selectedComponent = tabbedPane.getComponentAt(i);
            if (selectedComponent instanceof Texteditor editor) {
	        File openFile = editor.getCurrentFile();
	        if (openFile != null) {
	            try {
	                if (openFile.getCanonicalPath().equals(filePath)) {
	                    return i; // File is already open, return its tab index
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
            }
	    }
	    return -1; // File is not open
	}

public static void moveRight() {
	  int selectedIndex = tabbedPane.getSelectedIndex();
      int tabCount = tabbedPane.getTabCount();
    
          if (selectedIndex < tabCount - 1) {
              tabbedPane.setSelectedIndex(selectedIndex + 1); // Move right
          } else {
              tabbedPane.setSelectedIndex(0); // Wrap around to first tab
          }
     
}

public static void moveLeft() {
	 int selectedIndex = tabbedPane.getSelectedIndex();
     int tabCount = tabbedPane.getTabCount();
     
	 if (selectedIndex > 0) {
         tabbedPane.setSelectedIndex(selectedIndex - 1); // Move left
     } else {
         tabbedPane.setSelectedIndex(tabCount - 1); // Wrap around to last tab
     }
}

public static void moveTabRight() {
    int currentIndex = tabbedPane.getSelectedIndex();
    int tabCount = tabbedPane.getTabCount();

    // If there is only one tab, no need to move
    if (tabCount < 2) return;

    int nextIndex = (currentIndex + 1) % tabCount; // Wrap around

    // Swap tabs
    SwingUtilities.invokeLater(() -> swapTabs(currentIndex, nextIndex));
}

public static void moveTabLeft() {
    int currentIndex = tabbedPane.getSelectedIndex();
    int tabCount = tabbedPane.getTabCount();

    // If there is only one tab, no need to move
    if (tabCount < 2) return;

    int prevIndex = (currentIndex - 1 + tabCount) % tabCount; // Wrap around

    // Swap tabs
    SwingUtilities.invokeLater(() -> swapTabs(currentIndex, prevIndex));
}



private static void swapTabs(int fromIndex, int toIndex) {
    // Save tab information
    Component tabComponent = tabbedPane.getComponentAt(fromIndex);
    String tabTitle = tabbedPane.getTitleAt(fromIndex);
    Icon tabIcon = tabbedPane.getIconAt(fromIndex);
    String tabTooltip = tabbedPane.getToolTipTextAt(fromIndex);
  //  Component tabHeaderComponent = tabbedPane.getTabComponentAt(fromIndex);
    // Remove the tab from the current position
    tabbedPane.remove(fromIndex);

    // Insert it at the new position
    tabbedPane.insertTab(tabTitle, tabIcon, tabComponent, tabTooltip, toIndex);

   // tabbedPane.setTabComponentAt(toIndex, tabHeaderComponent);
    // Set focus on the moved tab
    tabbedPane.setSelectedIndex(toIndex);
    
}

	

public synchronized static Texteditor texteditor() {
    int selectedIndex = tabbedPane.getSelectedIndex();
    if (selectedIndex >= 0) {
        // Check if the selected component is an instance of Texteditor
     //   if (tabbedPane.getComponentAt(selectedIndex) instanceof Texteditor) {
            return (Texteditor) tabbedPane.getComponentAt(selectedIndex);
       // }
    }
    return null; // Return null if no valid tab is selected
}

public synchronized static Texteditor getPreviousEditor() {
    int selectedIndex = tabbedPane.getSelectedIndex();

    // If there is a previous tab, check for a Texteditor instance
    if (selectedIndex > 0) {
   //     if (tabbedPane.getComponentAt(selectedIndex - 1) instanceof Texteditor) {
            return (Texteditor) tabbedPane.getComponentAt(selectedIndex - 1);
     //   }
    }
    return null; // No valid editor is available
}


     public static void checkAndOpenDefaultTab() {
        if (tabbedPane.getTabCount() == 0) {
            newTabCount = 1;
           // SwingUtilities.invokeLater(() -> openNewTab(null));
            openNewTab(null);
        }

    }

    protected synchronized static void initializeNewTabButton(JPanel buttonPanel) {

        JButton newTabButton = new JButton("+");
       // newTabButton.addActionListener(e -> SwingUtilities.invokeLater(() ->openNewTab(null)));
        newTabButton.addActionListener(e -> openNewTab(null));
        buttonPanel.add(newTabButton);
    }


    protected static void ensureAppDirectoryExists() {
        File appDir = new File(APP_DIR);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
    }
    
    protected static String loadThemePreference() {
        Properties prop = new Properties();
        File configFile = new File(CONFIG_FILE_THEME);
        if (configFile.exists()) {
            try (FileInputStream input = new FileInputStream(CONFIG_FILE_THEME)) {
                prop.load(input);
                return prop.getProperty("theme", getThemeBaseOnOS());
            } catch (IOException e) {
               // e.printStackTrace();
                return getThemeBaseOnOS();
            }
        } else {
            return getThemeBaseOnOS();
        }
    }
    
    private static String getThemeBaseOnOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "Light";
        }else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return "Dark";
        } else {
            return "Light"; // fallback for macOS or unknown OS
        }
    }
    
 

    public static JFrame getFrame() {
    	return frame;
    }
    
    public static Point getFrameLoc() {
    	return frame.getLocation();
    }

    public static JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
  
    
    public static Dimension getFrameSize() {
		return frame.getSize();
	}
    


	/**
     * this is to save the dimension and open the frame in the same place
     * @return
     */
    protected static Dimension loadWindowSize() {
        Properties properties = new Properties();
        // Check if the config file exists
        File configFile = new File(CONFIG_FILE_SIZE);
        if (!configFile.exists()) {
            // If the config file does not exist, return a default size
            //return new Dimension(1200, 900);
        	return null;
        }

        try (FileInputStream input = new FileInputStream(CONFIG_FILE_SIZE)) {
            properties.load(input);
            try {
            int width = Integer.parseInt(properties.getProperty("window_width", "1200"));
            int height = Integer.parseInt(properties.getProperty("window_height", "900"));
            int x = Integer.parseInt(properties.getProperty("window_x", "100")); // Default X position
            int y = Integer.parseInt(properties.getProperty("window_y", "100")); // Default Y position

            // Ensure the window's position is within screen bounds
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            x = Math.max(screenBounds.x, Math.min(x, screenBounds.x + screenBounds.width - width));
            y = Math.max(screenBounds.y, Math.min(y, screenBounds.y + screenBounds.height - height));


            frame.setLocation(x, y); // Set the window position
            return new Dimension(width, height);
            }catch(Exception e) {
            	return null;
            }
        } catch (IOException e) {
          //  e.printStackTrace();
            //return new Dimension(1200, 900); // Return default size on error
            return null;
        }
    }

    public static void saveWindowSize(Dimension size) {
        Properties properties = new Properties();

        // Load existing properties or create new one
        File configFile = new File(CONFIG_FILE_SIZE);
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
              //  e.printStackTrace();
            }
        }
        if (configFile.exists()) {
            try (FileInputStream input = new FileInputStream(CONFIG_FILE_SIZE)) {
                properties.load(input);
            } catch (IOException e) {
              //  e.printStackTrace();
            }
        }

        // Get the current window position
        Point location = frame.getLocation();

        // Save the window size to the properties
        properties.setProperty("window_width", String.valueOf(size.width));
        properties.setProperty("window_height", String.valueOf(size.height));
        properties.setProperty("window_x", String.valueOf(location.x));
        properties.setProperty("window_y", String.valueOf(location.y));

        // Store the updated properties
        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE_SIZE)) {
            properties.store(output, " Window Size and Position Configuration");
        } catch (IOException e) {
           // e.printStackTrace();
        }
    }


    /**
     * This is to save and load the previous files.
     *
     */

    public static void saveOpenedFiles() {
        SwingUtilities.invokeLater(() -> {
            resetOpenedFiles(); // Clear previous saved files
            Properties savedTabs = new Properties();

            int tabCount = tabbedPane.getTabCount();
            for (int i = 0; i < tabCount; i++) {
             //   Texteditor editor = (Texteditor) tabbedPane.getComponentAt(i);
            Component comp = tabbedPane.getComponentAt(i);

            if (comp instanceof Texteditor) {
                Texteditor editor = (Texteditor) comp;
                File file = editor.getCurrentFile();
                String content = editor.getTextArea().getText();

                if (file != null && file.exists()) { 
                    // Saved file path
                    savedTabs.setProperty("tab" + i, "file:" + file.getAbsolutePath());
                } else if (!content.trim().isEmpty()) { 
                    // Unsaved content (Base64-encoded)
                    String encodedContent = Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8));
                    savedTabs.setProperty("tab" + i, "content:" + encodedContent);
                }
            }
            }
            // Save everything to a single properties file
            saveToProperties(savedTabs);
        });
    }



    private static void resetOpenedFiles() {
        try {
            // Clear saved files configuration
            File savedFilesConfig = new File(CONFIG_FILE_OPEN_FILES);
            if (savedFilesConfig.exists()) {
                try (FileOutputStream out = new FileOutputStream(savedFilesConfig)) {
                    new Properties().store(out, "Cleared saved files");
                }
            }

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    // Helper method to save properties to files
    private static void saveToProperties(Properties savedFiles) {
        try {
            ensureAppDirectoryExists();

            // Save files with paths
            if (!savedFiles.isEmpty()) {
                try (FileOutputStream out = new FileOutputStream(CONFIG_FILE_OPEN_FILES)) {
                    savedFiles.store(out, "Files with Paths");
                }
            }

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
   
    private static void loadOpenedFiles(Runnable onComplete) {
        CompletableFuture.supplyAsync(() -> {
            Properties savedTabs = new Properties();
            TreeMap<Integer, String> tabEntries = new TreeMap<>();

            try (FileInputStream input = new FileInputStream(CONFIG_FILE_OPEN_FILES)) {
                savedTabs.load(input);
            } catch (IOException ignored) {}

            for (String key : savedTabs.stringPropertyNames()) {
                if (key.startsWith("tab")) {
                    try {
                        int index = Integer.parseInt(key.substring(3));
                        tabEntries.put(index, savedTabs.getProperty(key));
                    } catch (NumberFormatException ignored) {}
                }
            }

            return new ArrayList<>(tabEntries.entrySet()); // Convert to sorted list
        }).thenAccept(tabList -> {
            if (tabList.isEmpty()) {
                SwingUtilities.invokeLater(() -> {
                	updateSplashProgress(100);
                    if (onComplete != null) onComplete.run(); // Run immediately if no files
                });
                return;
            }

            // Efficient file loading with progress updates
            SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                private final int totalFiles = tabList.size();
                private final AtomicInteger counter = new AtomicInteger(0);
      
                @Override
                protected Void doInBackground() {
                	
                    for (Map.Entry<Integer, String> entry : tabList) {
                        if (isCancelled()) break; // Allow cancellation
            
         //     SwingUtilities.invokeLater(() -> openTabFromEntry(entry.getValue())); // Load file
              
                      openTabFromEntry(entry.getValue());  

                        // Batch update progress to reduce UI redraws
                        int progress = (counter.incrementAndGet() * 100) / totalFiles;
                        if (progress % 5 == 0) { // Update every 5% progress
                            publish(progress);
                        }
                  //  });
                    }

                    return null;
                }

                @Override
                protected void process(List<Integer> chunks) {
                    if (!chunks.isEmpty()) {
                        updateSplashProgress(chunks.get(chunks.size() - 1));
                    }
                }

                @Override
                protected void done() {
                    SwingUtilities.invokeLater(() -> {
                    	// updateLineWrapForAllTabs(PreferenceMenu.isWordWrapEnabled());
                        updateSplashProgress(100);
                        if (onComplete != null) {
                            onComplete.run();
                        }
                       
                    });
                }
            };
            worker.execute();
        });
    }
    

    private static void updateSplashProgress(int progress) {
      //  SwingUtilities.invokeLater(() -> {
            if (splash != null) {
                splash.setprogressValue(progress);
            	//splash.updateProgress(progress);
            }
        //});
            
    }

    // Helper method to open a tab
    private static void openTabFromEntry(String value) {
    //	 SwingUtilities.invokeLater(() -> {
        if (value.startsWith("file:")) {
            File file = new File(value.substring(5));
            if (file.exists()) {
                openNewTab(file);
            	//  SwingUtilities.invokeLater(() -> openNewTab(file));
            }
        } else if (value.startsWith("content:")) {
            String decodedContent = new String(Base64.getDecoder().decode(value.substring(8)), StandardCharsets.UTF_8);
         //   SwingUtilities.invokeLater(() -> {
            openNewTab(null);
            Texteditor editor = (Texteditor) tabbedPane.getSelectedComponent();
            if (editor != null) {
                editor.setTextContent(decodedContent);
                editor.setOriginalContent("");
                editor.setModified(true);
                editor.updateTitle(editor.getTabTitle());
            }
          //  });
        }
    	// });
    } 


   
 /*  private static void loadOpenedFiles1(Runnable onComplete) {
    	if(loadOpenFilesFromConfig().size() <= 0) {
    		if (splash != null) {
                splash.setprogressValue(100);
            }
    		  SwingUtilities.invokeLater(onComplete);
    		return ;
    	}
        CompletableFuture.supplyAsync(() -> {
            Properties savedTabs = new Properties();
            TreeMap<Integer, String> tabEntries = new TreeMap<>();

            try (FileInputStream input = new FileInputStream(CONFIG_FILE_OPEN_FILES)) {
                savedTabs.load(input);
            } catch (IOException ignored) {}

            // Parse properties
            for (String key : savedTabs.stringPropertyNames()) {
                if (key.startsWith("tab")) {
                    try {
                        int index = Integer.parseInt(key.substring(3));
                        tabEntries.put(index, savedTabs.getProperty(key));
                    } catch (NumberFormatException ignored) {
                        // Ignore invalid keys
                    }
                }
            }

            return tabEntries;
        }).thenAcceptAsync(tabEntries -> {
            if (tabEntries.isEmpty()) {
            	 if(splash != null) {
                     splash.setprogressValue(100);
                    }
                SwingUtilities.invokeLater(onComplete);
                return;
            }

            List<Integer> keys = new ArrayList<>(tabEntries.keySet());
            Collections.sort(keys);
            // Open tabs in order using a sequential CompletableFuture chain
            CompletableFuture<Void> sequence = CompletableFuture.completedFuture(null);
            for (int i : keys) {
                sequence = sequence.thenRunAsync(() -> {
                    SwingUtilities.invokeLater(() -> {
                        String value = tabEntries.get(i);
                        if (value.startsWith("file:")) {
                            File file = new File(value.substring(5));
                            if (file.exists()) {
                         openNewTab(file);              
                            }
                        } else if (value.startsWith("content:")) {
                            String decodedContent = new String(Base64.getDecoder().decode(value.substring(8)), StandardCharsets.UTF_8);
                            openNewTab(null);
                        
                            Texteditor editor = (Texteditor) tabbedPane.getSelectedComponent();
                            if (editor != null) {
                                editor.setTextContent(decodedContent);
                                editor.setOriginalContent("");
                                editor.setModified(true);
                                editor.updateTitle(editor.getTabTitle());
                            }
                        }
                    });
                });
            }

           if(splash != null) {
            splash.setprogressValue(100);
           }
            sequence.thenRunAsync(onComplete, SwingUtilities::invokeLater);
       
        }, SwingUtilities::invokeLater);
    }*/

    
    
    
/**
 * this is to save the recent close tab and open it
 * @param file
 */

// Save a recently closed tab 
    public  static void saveClosedTab(File closedFile, String tabContent) {
        CompletableFuture.runAsync(() -> {
            Properties recentClosedFiles = new Properties();
            File configFile = new File(CONFIG_FILE_RECENT_CLOSED);

            // Load existing properties
            try (FileInputStream fis = configFile.exists() ? new FileInputStream(configFile) : null) {
                if (fis != null) {
                    recentClosedFiles.load(fis);
                }
            } catch (IOException ignored) {}

            // Find the next available index
            int nextIndex = 0;
            while (recentClosedFiles.containsKey("tab" + nextIndex)) {
                nextIndex++;
            }

            // Store new tab entry
            String key = "tab" + nextIndex;
            if (closedFile != null && closedFile.exists()) {
                recentClosedFiles.setProperty(key, closedFile.getAbsolutePath());
            } else if (tabContent != null && !tabContent.isEmpty()) {
                recentClosedFiles.setProperty(key, "content:" + Base64.getEncoder().encodeToString(tabContent.getBytes(StandardCharsets.UTF_8)));
            }

            // Save properties
            saveClosedTabProperties(recentClosedFiles);
        });
    }

    private static void saveClosedTabProperties(Properties savedFiles) {
        CompletableFuture.runAsync(() -> {
            try {
                ensureAppDirectoryExists();
                try (FileOutputStream out = new FileOutputStream(CONFIG_FILE_RECENT_CLOSED)) {
                    savedFiles.store(out, "Recently Closed Tabs");
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(frame, "Failed to save recently closed tabs", "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        });
    }




//Reopen the last closed tab
    public static void openRecentClosedTab() {
        CompletableFuture.runAsync(() -> {
            Properties recentClosedFiles = new Properties();
            File file = new File(CONFIG_FILE_RECENT_CLOSED);

            try (FileInputStream in = file.exists() ? new FileInputStream(file) : null) {
                if (in != null) {
                    recentClosedFiles.load(in);

                    //  Store tabs in sorted order (Reverse Order)
                    TreeMap<Integer, String> sortedTabs = new TreeMap<>(Collections.reverseOrder());

                    for (String key : recentClosedFiles.stringPropertyNames()) {
                        if (key.startsWith("tab")) {
                            try {
                                int index = Integer.parseInt(key.substring(3)); // Extract tab index
                                sortedTabs.put(index, recentClosedFiles.getProperty(key));
                            } catch (NumberFormatException ignored) {}
                        }
                    }

                    //  Open only the most recent tab (One tab per call)
                    if (!sortedTabs.isEmpty()) {
                        Map.Entry<Integer, String> lastTabEntry = sortedTabs.pollFirstEntry(); // Get & remove highest tab
                        int lastIndex = lastTabEntry.getKey();
                        String value = lastTabEntry.getValue();

                        SwingUtilities.invokeLater(() -> {
                            if (value.startsWith("content:")) {
                                //  Handle unsaved content
                                String content = new String(Base64.getDecoder().decode(value.substring(8)), StandardCharsets.UTF_8);
                                openNewTab(null);
                                Texteditor editor = (Texteditor) tabbedPane.getSelectedComponent();
                                if (editor != null) {
                                    editor.setTextContent(content);
                                    editor.setOriginalContent("");
                                    editor.setModified(true);
                                    editor.updateTitle(editor.getTabTitle());
                                }
                            } else {
                                //  Handle file path
                                File recentFile = new File(value);
                                if (recentFile.exists()) {
                                    openNewTab(recentFile);
                                } else {
                                    openNewTab(null);
                                }
                            }
                        });

                        //  Remove the last closed tab entry after opening it
                        recentClosedFiles.remove("tab" + lastIndex);
                        saveClosedTabProperties(recentClosedFiles);
                    }
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(frame, "Failed to open recent closed tab", "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        });
    }



//Open all recently closed tabs
    public static void openAllRecentClosedTabs() {
        CompletableFuture.supplyAsync(() -> {
            Properties recentClosedFiles = new Properties();
            TreeMap<Integer, String> recentTabs = new TreeMap<>(Collections.reverseOrder()); // Reverse order

            File configFile = new File(CONFIG_FILE_RECENT_CLOSED);

            // Load existing properties
            try (FileInputStream in = configFile.exists() ? new FileInputStream(configFile) : null) {
                if (in != null) {
                    recentClosedFiles.load(in);

                    // Extract indices and sort properly
                    for (String key : recentClosedFiles.stringPropertyNames()) {
                        if (key.startsWith("tab")) {
                            try {
                                int index = Integer.parseInt(key.substring(3)); // Extract tab index
                                recentTabs.put(index, recentClosedFiles.getProperty(key));
                            } catch (NumberFormatException ignored) {
                                // Ignore invalid keys
                            }
                        }
                    }
                }
            } catch (IOException ignored) {}

            return recentTabs;
        }).thenAcceptAsync(recentTabs -> {
            if (recentTabs.isEmpty()) return;

            new SwingWorker<Void, Integer>() {
                final Set<Integer> openedTabs = new HashSet<>(); // Track all opened tabs

                @Override
                protected Void doInBackground() {
                    for (Map.Entry<Integer, String> entry : recentTabs.entrySet()) {
                        int tabIndex = entry.getKey();

                        try {
                            Thread.sleep(50); // Prevent UI lag
                        } catch (InterruptedException ignored) {}

                        openedTabs.add(tabIndex); // Collect tabs to be removed
                        publish(tabIndex);
                    }
                    return null;
                }

                @Override
                protected void process(List<Integer> openedTabsList) {
                    Properties updatedProperties = new Properties();
                    File configFile = new File(CONFIG_FILE_RECENT_CLOSED);

                    // Load existing properties once
                    try (FileInputStream in = configFile.exists() ? new FileInputStream(configFile) : null) {
                        if (in != null) {
                            updatedProperties.load(in);
                        }
                    } catch (IOException ignored) {}

                    for (int index : openedTabsList) {
                        String value = recentTabs.get(index);
                        if (value.startsWith("content:")) {
                            // Handle unsaved content
                            String content = new String(Base64.getDecoder().decode(value.substring(8)), StandardCharsets.UTF_8);
                            openNewTab(null);
                            Texteditor editor = (Texteditor) tabbedPane.getSelectedComponent();
                            if (editor != null) {
                                editor.setTextContent(content);
                                editor.setOriginalContent("");
                                editor.setModified(true);
                                editor.updateTitle(editor.getTabTitle());
                            }
                        } else {
                            // Handle file paths
                            File file = new File(value);
                            if (file.exists()) {
                                openNewTab(file);
                            } else {
                                openNewTab(null);
                            }
                        }
                    }
                }

                @Override
                protected void done() {
                    // Perform a single write operation after processing all tabs
                    Properties updatedProperties = new Properties();
                    File configFile = new File(CONFIG_FILE_RECENT_CLOSED);

                    // Load the latest properties
                    try (FileInputStream in = configFile.exists() ? new FileInputStream(configFile) : null) {
                        if (in != null) {
                            updatedProperties.load(in);
                        }
                    } catch (IOException ignored) {}

                    // Remove all processed tabs
                    for (int index : openedTabs) {
                        updatedProperties.remove("tab" + index);
                    }

                    // Save properties once
                    saveClosedTabProperties(updatedProperties);
                }
            }.execute();

        }, SwingUtilities::invokeLater);
    }





//Clear all recently closed tabs
	public static void clearAllRecentClosedTabs() {
	    try {
	        File recentClosedConfig = new File(CONFIG_FILE_RECENT_CLOSED);
	        if (recentClosedConfig.exists()) {
	            try (FileOutputStream out = new FileOutputStream(recentClosedConfig)) {
	                // Write an empty properties set to clear the file content
	                new Properties().store(out, "Cleared recently closed tabs");
	            }
	        }
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(frame, "Failed to clear recently closed tabs", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	private static void startUpdateScheduler() {
		String os = System.getProperty("os.name").toLowerCase();
	     if (os.contains("win")) {
	         WindowsUtils.startUpdateScheduler();
	     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	         LinuxUtils.startUpdateScheduler();
	     } else {
	         JOptionPane.showMessageDialog(
	             null,
	             "Unsupported operating system.",
	             "Error",
	             JOptionPane.ERROR_MESSAGE
	         );
	     }
   }

	


    public static void onCheckForUpdatesMenuSelected() {
    	 String os = System.getProperty("os.name").toLowerCase();
	     if (os.contains("win")) {
	         WindowsUtils.onCheckForUpdatesMenuSelected();
	     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	         LinuxUtils.onCheckForUpdatesMenuSelected();
	     } else {
	         JOptionPane.showMessageDialog(
	             null,
	             "Unsupported operating system.",
	             "Error",
	             JOptionPane.ERROR_MESSAGE
	         );
	     }
    }
    
 // Updates line wrap for all tabs without freezing UI
    public static void updateLineWrapForAllTabs(boolean shouldWrap) {
        // Snapshot of all tabs
        List<RSyntaxTextArea> textAreas = new ArrayList<>();

        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component c = tabbedPane.getComponentAt(i);
            if (c instanceof Texteditor) {
                RSyntaxTextArea area = ((Texteditor) c).getTextArea();
                textAreas.add(area);
            }
        }

        // Process visible tab immediately
        RSyntaxTextArea visibleArea = null;
        Component visibleTab = tabbedPane.getSelectedComponent();
        if (visibleTab instanceof Texteditor) {
            visibleArea = ((Texteditor) visibleTab).getTextArea();
            updateLineWrapNow(visibleArea, shouldWrap);
            textAreas.remove(visibleArea); // avoid duplicate processing
        }

        // Batch update remaining in small chunks
        batchUpdateLineWrap(textAreas, shouldWrap, 3);
    }

    private static void batchUpdateLineWrap(List<RSyntaxTextArea> areas, boolean shouldWrap, int batchSize) {
        final int[] index = {0};

        Runnable updater = new Runnable() {
            public void run() {
                int count = 0;
                while (index[0] < areas.size() && count < batchSize) {
                    RSyntaxTextArea area = areas.get(index[0]);
                    updateLineWrapNow(area, shouldWrap);
                    index[0]++;
                    count++;
                }

                if (index[0] < areas.size()) {
                    Timer timer = new Timer(10, e -> SwingUtilities.invokeLater(this));
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        };

        SwingUtilities.invokeLater(updater);
    }

    private static void updateLineWrapNow(RSyntaxTextArea area, boolean shouldWrap) {
        if (area.getLineWrap() != shouldWrap) {
            area.setLineWrap(shouldWrap);
            area.setWrapStyleWord(shouldWrap);
        }
    }
    // Updated single-tab update method
    protected static void updateLineWrap(Texteditor editor) {
        boolean shouldWrap = PreferenceMenu.isWordWrapEnabled();
        updateLineWrapNow(editor.getTextArea(), shouldWrap);
    }


}
