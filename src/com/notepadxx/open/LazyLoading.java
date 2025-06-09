package com.notepadxx.open;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.formdev.flatlaf.FlatLaf;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;


public class LazyLoading { 
  //  private static final String CONFIG_FILE = ConfigFiles.getConfigLargeFile();
    private JFrame loadFrame;
    private  JProgressBar progressBar;
    private RSyntaxTextArea textArea;
    private Texteditor editor;
    private static boolean isLoading;
   
  
    public void loadFile(File newFile, RSyntaxTextArea textArea, Texteditor editor) {
        this.textArea = textArea;
        this.editor = editor;

        new SwingWorker<Void, Integer>() {
            private static final int BUFFER_SIZE = 64 * 1024; // 64KB buffer
            private FileChannel fileChannel;
            private BufferedReader reader;
            private long totalSize;
            private long bytesRead = 0;
            private final int INITIAL_LINES = 500; // Load more lines initially
            private volatile boolean isActive = true; // Control flag

            @Override
            protected Void doInBackground() throws Exception {
                try {
                    fileChannel = FileChannel.open(newFile.toPath(), StandardOpenOption.READ);
                    reader = new BufferedReader(Channels.newReader(fileChannel, StandardCharsets.UTF_8), BUFFER_SIZE);
                    totalSize = fileChannel.size();
//textArea.setText(null);
                    
                   // SwingUtilities.invokeLater(() -> updateProgress(0, newFile));
                    loadInitialLines();
                    addScrollListener();
   
                    
                    // Keep worker alive for background loading
                    while (isActive && bytesRead < totalSize) {
                        Thread.sleep(50); // Reduce CPU usage
                    }

                } catch (IOException | InterruptedException e) {
                    showErrorMessage(newFile, e);
                } finally {
                    closeResources();
                }
                return null;
            }

            private void loadInitialLines() throws IOException {
                StringBuilder initialContent = new StringBuilder();
                char[] buffer = new char[BUFFER_SIZE];
                int charsRead;
                int linesRead = 0;

                while (isActive && (charsRead = reader.read(buffer)) != -1 && linesRead < INITIAL_LINES) {
                    initialContent.append(buffer, 0, charsRead);
                    bytesRead += charsRead;
                    linesRead += countNewLines(buffer, charsRead);
                    if (linesRead >= INITIAL_LINES) break;
                }

                final String content = initialContent.toString();
                SwingUtilities.invokeLater(() -> {
                	try {
                    textArea.setText(content);
                    textArea.setCaretPosition(0);
                	}catch(OutOfMemoryError e) {
                		 handleMemoryError(newFile); // Handle memory error here
                	}
                });

                // Free up memory
                initialContent.setLength(0); // Clear StringBuilder
                initialContent = null; // Allow GC to reclaim memory
                publishProgress();
            }

            private void addScrollListener() {
                SwingUtilities.invokeLater(() -> {
                    Container parent = textArea.getParent();
                    if (parent instanceof JViewport) {
                        Container grandParent = parent.getParent();
                        if (grandParent instanceof JScrollPane) {
                            JScrollBar verticalScrollBar = ((JScrollPane) grandParent).getVerticalScrollBar();
                            verticalScrollBar.addAdjustmentListener(e -> {
                                if (!e.getValueIsAdjusting() && isActive) {
                                    try {
                                        loadMoreLines();
                                    } catch (IOException i) {
                                        showErrorMessage(newFile, i);
                                    }
                                }
                            });
                        }
                    }
                });
            }

            private void loadMoreLines() throws IOException {
                if (!isActive) return; // Skip if worker is done

                StringBuilder additionalContent = new StringBuilder();
                char[] buffer = new char[BUFFER_SIZE];
                int charsRead;
                int linesRead = 0;
                final int BATCH_LINES = 200;

                while (isActive && (charsRead = reader.read(buffer)) != -1 && linesRead < BATCH_LINES) {
                    additionalContent.append(buffer, 0, charsRead);
                    bytesRead += charsRead;
                    linesRead += countNewLines(buffer, charsRead);
                    if (linesRead >= BATCH_LINES) break;
                }

                if (additionalContent.length() > 0) {
                    final String moreContent = additionalContent.toString();
                    SwingUtilities.invokeLater(() ->{
                    	try {
                    	textArea.append(moreContent);
                    	}catch(OutOfMemoryError e) {
                    		 handleMemoryError(newFile); // Handle memory error here
                    	}
                    	});

                    // Free up memory
                    additionalContent.setLength(0); // Clear StringBuilder
                    additionalContent = null; // Allow GC to reclaim memory
                    publishProgress();
                }
            }

            private int countNewLines(char[] buffer, int length) {
                int count = 0;
                for (int i = 0; i < length; i++) {
                    if (buffer[i] == '\n') count++;
                }
                return count;
            }

            private void publishProgress() {
                int progress = (int) ((bytesRead * 100) / totalSize);
                publish(progress);
            }

            private void showErrorMessage(File file, Exception e) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                    null, "Error loading file: " + file.getName() + " " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE));
            }

            private void closeResources() {
                isActive = false; // Stop all loading operations
                try {
                    if (reader != null) reader.close();
                    if (fileChannel != null) fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void process(List<Integer> chunks) {
                if (!chunks.isEmpty()) {
                    int progress = chunks.get(chunks.size() - 1);
                   // SwingUtilities.invokeLater(() -> updateProgress(progress, newFile));
                    updateProgress(progress, newFile);
                }
            }

            @Override
            protected void done() {
                closeResources();
                SwingUtilities.invokeLater(() -> {
                    updateProgress(100, newFile);
                });
            }
        }.execute();
    }

   /* private boolean frameShown = false;
            private void updateProgress(int progress, File file) {
            	if(frameShown)return;
            	frameShown = true;
                // Ensure progressBar is always initialized
                if (progressBar == null) {
                    progressBar = new JProgressBar(0, 100);
                    progressBar.setStringPainted(true);
                    progressBar.setForeground(new Color(0, 180, 255)); // Bright cyan color
                  //  progressBar.setBackground(new Color(50, 50, 50));
                  //  progressBar.setForeground(new Color(70, 130, 180));
                   progressBar.setValue(0);
                }

                // Ensure loadFrame is initialized
                if (loadFrame == null) {
                    Point mainFrameLocation =NotepadXXV1_2_0.getFrameLoc();
                    Dimension mainFrameSize =NotepadXXV1_2_0.getFrameSize();

                    loadFrame = new JFrame("Opening File...");
                    loadFrame.setSize(350, 80);
                    loadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Prevent manual close
                    loadFrame.setLayout(new BorderLayout());
                    loadFrame.setIconImage(new ImageIcon(getClass().getResource("/icons/NotepadXXLogo.png")).getImage());

                    JLabel loadingLabel = new JLabel("Preparing to open: " + file.getName(), SwingConstants.CENTER);
                    loadingLabel.setFont(new Font("Arial", Font.PLAIN, 13));
                    loadFrame.add(loadingLabel, BorderLayout.NORTH);
                    loadFrame.add(progressBar, BorderLayout.CENTER);

                    int x = mainFrameLocation.x + (mainFrameSize.width - loadFrame.getWidth()) / 2;
                    int y = mainFrameLocation.y + (mainFrameSize.height - loadFrame.getHeight()) / 2;
                    loadFrame.setLocation(x, y);
                    loadFrame.setResizable(false);
                    loadFrame.setVisible(true);
                }

                // Update progress bar safely
                SwingUtilities.invokeLater(() -> {
                    if (progressBar != null) {
                        progressBar.setValue(progress);
                        progressBar.setString("Loading " + file.getName() + "... " + progress + "%");
                     //   saveIsLoading( file, true);
                        isLoading = true;
                        
                    }
                });

                // Close frame when done
                if (progress >= 100) {
                //  setLoading(false);
                    //saveIsLoading( file, false);
                    SwingUtilities.invokeLater(() -> {
                        if (loadFrame != null) {
                              editor.updateAfterOpen(file, textArea);
                            //enableUI();
                            //setLoading(false);
                           // saveIsLoading( file, false);
                              isLoading = false;
                            // editor.setModified(false);
                            loadFrame.dispose();
                            loadFrame = null; // Reset for future use
                        }
                        progressBar = null; // Reset progress bar
                      //  saveIsLoading( file, false);
                        isLoading = false;
                        frameShown = false;
                        
                    });
                }
            }*/   

    private void updateProgress(int progress, File file) {
       
    	 boolean isdarkmode = FlatLaf.isLafDark();
        // Initialize progress bar with FlatLaf styling
        if (progressBar == null) {
            progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true);
            
            // Let FlatLaf handle the styling
            progressBar.putClientProperty("JProgressBar.largeHeight", true);
            progressBar.putClientProperty("JProgressBar.smoothProgress", true);
        }

        // Initialize the loading frame
        if (loadFrame == null) {
            Point mainFrameLocation = NotepadXXV1_2_0.getFrameLoc();
            Dimension mainFrameSize = NotepadXXV1_2_0.getFrameSize();
            
            loadFrame = new JFrame("Opening File...");
            loadFrame.setSize(400, 120);
            loadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            loadFrame.setIconImage(new ImageIcon(getClass().getResource("/icons/NotepadXXLogo.png")).getImage());
          
            Color bgColor = isdarkmode ? new Color(43, 43, 43) : Color.WHITE;
	        Color fgColor = isdarkmode ? Color.WHITE : Color.BLACK;
	        Color borderColor = isdarkmode ? new Color(90, 90, 90) : new Color(220, 220, 220);

            // Apply FlatLaf styling to the frame
            JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            contentPanel.setBackground(bgColor);
            
            JLabel loadingLabel = new JLabel("Opening: " + file.getName(), SwingConstants.CENTER);
            loadingLabel.putClientProperty("FlatLaf.style", "font: 13 $semibold.font");
            loadingLabel.setForeground(fgColor);
            
            // Create icon panel with FlatLaf styling
            JPanel iconPanel = new JPanel(new BorderLayout(10, 0));
            iconPanel.setOpaque(false);
            
            // Use FlatLaf's icon or fallback
            Icon openIcon = UIManager.getIcon("Tree.openIcon");
            if (openIcon == null) {
                openIcon = createFlatOpenIcon();
            }
            iconPanel.add(new JLabel(openIcon), BorderLayout.WEST); 
            iconPanel.add(loadingLabel, BorderLayout.CENTER);
            
            contentPanel.add(iconPanel, BorderLayout.NORTH);
            contentPanel.add(progressBar, BorderLayout.CENTER);
            
            loadFrame.add(contentPanel);
            
            // Center the frame
            int x = mainFrameLocation.x + (mainFrameSize.width - loadFrame.getWidth()) / 2;
            int y = mainFrameLocation.y + (mainFrameSize.height - loadFrame.getHeight()) / 2;
            loadFrame.setLocation(x, y);
            
            loadFrame.getRootPane().setBorder(BorderFactory.createLineBorder(borderColor));
            loadFrame.setResizable(false);
            loadFrame.setVisible(true);
        }

        // Update progress bar - this must run on EDT
        SwingUtilities.invokeLater(() -> {
            if (progressBar != null && loadFrame != null) {
                progressBar.setValue(progress);
                progressBar.setString(progress + "%");
                isLoading = true;
                // Change appearance when complete
                if (progress >= 100) {
                	 progressBar.setForeground(new Color(0, 180, 0));
                    progressBar.putClientProperty("JProgressBar.finished", true);
                    
                    // Close after delay
                    Timer timer = new Timer(100, e -> {
                        editor.updateAfterOpen(file, textArea);
                        isLoading = false;
                        if (loadFrame != null) {
                            loadFrame.dispose();
                            loadFrame = null;
                        }
                        
                        progressBar = null;
                      //  frameShown = false;
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });
    }

    // Creates a FlatLaf-style open icon
    private Icon createFlatOpenIcon() {
        BufferedImage icon = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = icon.createGraphics();
        
        // Get colors from FlatLaf
        Color foreground = UIManager.getColor("ProgressBar.foreground");
        if (foreground == null) foreground = new Color(0, 90, 158);
        
        // Draw folder icon
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(foreground);
        g2d.fillRoundRect(2, 4, 12, 10, 2, 2); // Folder body
        g2d.fillRect(3, 2, 10, 3); // Folder tab
        
        g2d.dispose();
        return new ImageIcon(icon);
    }
    
            
            public static boolean getLoading(){
            	return  isLoading;
            }

            private boolean memoryErrorShown = false; // Prevent duplicate dialogs

            private void handleMemoryError(File file) {
                if (memoryErrorShown) return; // Exit if dialog is already shown
                memoryErrorShown = true; // Set flag to true

             //   SwingUtilities.invokeLater(() -> {
                    int choice = JOptionPane.showOptionDialog(editor,
                           "* "+ file.getName()+" * is too large to load completely.\nIt is recommanded to close tab",
                            "Memory Error",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null, new String[]{"Close Tab", "Load File"}, "Close Tab");

                    if (choice == JOptionPane.YES_OPTION) {
                    	System.gc();
                    	  SwingUtilities.invokeLater(() -> {
                    	loadFrame.dispose();
                    	loadFrame = null;
                    	progressBar = null;
                    	NotepadXXV1_2_0.checkAndOpenDefaultTab();
                     NotepadXXV1_2_0.getTabbedPane().remove(editor);
                    	NotepadXXV1_2_0.checkAndOpenDefaultTab();
                    	  });
                    	// editor.closeTab();
                      //   System.gc();
                    }else {
                    	System.gc();
                    	
                    }

                    memoryErrorShown = false; // Reset flag after dialog is handled
               // });
            }
    
}