package com.notepadxx.save;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.formdev.flatlaf.FlatLaf;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;

public class write {

	private JFrame saveFrame;
	private  JProgressBar progressBar;
	private  Texteditor editor;
	private  RSyntaxTextArea textArea;
	private  RTextScrollPane scrollPane;
	private  String tabTitle;
	private static boolean isSaving;
	 
	// Define thresholds for small, medium, and large files
   private static final long SMALL_FILE_THRESHOLD = 10_000_000; // 10 MB
    private static final long MEDIUM_FILE_THRESHOLD = 30_000_000; // 30 MB

    // Buffer sizes for medium and large files
    private static final int MEDIUM_FILE_BUFFER_SIZE = 8192; // 8 KB
    private static final int LARGE_FILE_BUFFER_SIZE = 16384; // 16 KB
   
		public write(Texteditor editor, RSyntaxTextArea textArea,RTextScrollPane scrollPane,String tabTitle) {
			this.editor = editor;
			this.textArea = textArea;
			this.scrollPane = scrollPane;
			this.tabTitle = tabTitle;
		}
	
	
		protected  void fileSizeToWrite(File writeFile) {
	        int contentLength = textArea.getText().length();
	        long fileSizeInBytes = writeFile.exists() ? writeFile.length() : -1;

	        if (fileSizeInBytes > -1) {
	            if (fileSizeInBytes <= SMALL_FILE_THRESHOLD) {
	                writeFile(writeFile); // Existing small file
	            } else if (fileSizeInBytes <= MEDIUM_FILE_THRESHOLD) {
	                writeBigFile(writeFile, MEDIUM_FILE_BUFFER_SIZE); // Existing medium file
	            } else {
	                writeBigFile(writeFile, LARGE_FILE_BUFFER_SIZE); // Existing large file
	            }
	        } else {
	            if (contentLength <= SMALL_FILE_THRESHOLD) {
	                writeFile(writeFile); // New small file
	            } else if (contentLength <= MEDIUM_FILE_THRESHOLD) {
	                writeBigFile(writeFile, MEDIUM_FILE_BUFFER_SIZE); // New medium file
	            } else {
	                writeBigFile(writeFile, LARGE_FILE_BUFFER_SIZE); // New large file
	            }
	        }

	    }
	  private  void writeFile(File writeFile) {
		    SwingWorker<Void, Void> fileSaver = new SwingWorker<>() {
		        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		        int scrollPos = verticalScrollBar.getValue();

		        @Override
		        protected Void doInBackground() throws Exception {
		            verticalScrollBar.setValueIsAdjusting(true);
		            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile), StandardCharsets.UTF_8))) {
		                out.write(textArea.getText());
		                out.flush();
		                //SwingUtilities.invokeLater(() -> editor.updateAfterSave(writeFile, textArea));
		            } catch (IOException e) {
		                SwingUtilities.invokeLater(() -> {
		                    JOptionPane.showMessageDialog(null, "Error saving file: " + writeFile.getName() + "\n " + e.getMessage(),
		                        "Error", JOptionPane.ERROR_MESSAGE);
		                });
		            }
		            return null;
		        }

		        @Override
		        protected void done() {
		            SwingUtilities.invokeLater(() -> {
		                verticalScrollBar.setValue(scrollPos);
		                verticalScrollBar.setValueIsAdjusting(false);
		                editor.updateAfterSave(writeFile, textArea);
		            });
		        }
		    };
		    fileSaver.execute(); // Execute as SwingWorker
		}


	private   void writeBigFile(File writeFile,int BUFFER_SIZE) {
	    SwingWorker<Void, Integer> fileSaver = new SwingWorker<>() {
	        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
	        int scrollPos = verticalScrollBar.getValue();
	       // final int BUFFER_SIZE = 16384; // Larger buffer size for better performance

	        @Override
	        protected Void doInBackground() {
	            verticalScrollBar.setValueIsAdjusting(true); // Prevent UI updates on scroll during save
	            	try (BufferedWriter out = new BufferedWriter(
	                    new OutputStreamWriter(new FileOutputStream(writeFile), StandardCharsets.UTF_8), BUFFER_SIZE);
	                 BufferedReader reader = new BufferedReader(new StringReader(textArea.getText()), BUFFER_SIZE)) {
	            
	                char[] buffer = new char[BUFFER_SIZE];
	                int charsRead;
	                int totalChars = 0;
	                int contentLength = textArea.getText().length();

	                
	                if(BUFFER_SIZE != 8192) {
		                // Ensure the progress bar is displayed and initialized to 0
		                SwingUtilities.invokeLater(() -> updateProgress(0,writeFile));
		                }
	                // Write content in larger chunks for better performance
	                while ((charsRead = reader.read(buffer)) != -1) {
	                    out.write(buffer, 0, charsRead);
	                    totalChars += charsRead;

	                    // Periodically flush data to disk to reduce memory usage
	                    out.flush();

	                    // Periodically report progress to keep UI responsive
	                    if (contentLength > 100000 && totalChars % (5 * BUFFER_SIZE) == 0) {
	                    	int progress = (int) ((totalChars / (double) contentLength) * 100);
	                        publish(progress);
	                    }
	                }
	                
	            /*   if(BUFFER_SIZE != 8192) {
	                // Ensure the progress bar is displayed and initialized to 0
	                SwingUtilities.invokeLater(() -> updateProgress(0,writeFile));
	                }*/
	                
	                // Write content in chunks for better performance
	          /*      while ((charsRead = reader.read(buffer)) != -1) {
	                    out.write(buffer, 0, charsRead);
	                    totalChars += charsRead;

	                    // Periodically flush data to disk
	                    if (totalChars % (5 * BUFFER_SIZE) == 0) {
	                        out.flush();
	                    }

	                    // Update progress at smaller intervals for smoother updates
	                    if (contentLength > 10000) {
	                        int progress = (int) ((totalChars / (double) contentLength) * 100);
	                        publish(progress);
	                    }
	                }*/
	                publish(100);
	                out.flush(); // Ensure all data is written at the end
	            //    SwingUtilities.invokeLater(() -> editor.updateAfterSave(writeFile,textArea));
	            } catch (IOException e) {
	                SwingUtilities.invokeLater(() ->{
	                    JOptionPane.showMessageDialog(null, "Error saving file: " +
	                            (writeFile != null ? writeFile.getName() :tabTitle) +"\n "+ e.getMessage(),
	                            "Error", JOptionPane.ERROR_MESSAGE);
	                });
	            }
	            finally {
	                // Ensure the scroll bar is reset
	                SwingUtilities.invokeLater(() -> {
	                    verticalScrollBar.setValue(scrollPos);
	                    verticalScrollBar.setValueIsAdjusting(false);
	                });
	            }
	            
	            return null;
	        }

	        @Override
	        protected void process(List<Integer> chunks) {
	            // Update progress if needed (e.g., display in a progress bar)
	        	if(BUFFER_SIZE != 8192&& !chunks.isEmpty()) {
	         
	                int progress = chunks.get(chunks.size() - 1);
	           //     SwingUtilities.invokeLater(() -> updateProgress(progress,writeFile)); // Ensure thread safety
	                updateProgress(progress,writeFile);
	            }
	        	
	        }

	        @Override
	        protected void done() {
	            // Update UI after file is saved
	            SwingUtilities.invokeLater(() -> {
	            //	 editor.updateAfterSave(writeFile,textArea);
	              //  verticalScrollBar.setValue(scrollPos);
	               // verticalScrollBar.setValueIsAdjusting(false);
	            	if(BUFFER_SIZE != 8192) {
	                updateProgress(100,writeFile); // Ensure progress reaches 100% on completion
	            	}
	            	 editor.updateAfterSave(writeFile,textArea);
	          
	              /*  if (saveFrame != null) {
	                    saveFrame.dispose();
	                    saveFrame = null;  // Avoid memory leak
	                }*/
	            });
	        }
	    };
	    fileSaver.execute(); // Start the file-saving task in the background
	}

	
	
	private volatile Timer completionTimer = null;

	private synchronized void updateProgress(int progress, File file) {
	 //   if (frameshown && progress < 100) return;
	    boolean isdarkmode = FlatLaf.isLafDark();
	    // Initialize progress bar with FlatLaf styling
	    if (progressBar == null) {
	        progressBar = new JProgressBar(0, 100);
	        progressBar.setStringPainted(true);
	        progressBar.putClientProperty("JProgressBar.largeHeight", true);
	        progressBar.putClientProperty("JProgressBar.smoothProgress", true);
	    }

	    // Initialize the progress frame only once
	    if (saveFrame == null && !(progress >= 100)) {
	   //     frameshown = true;
	        Point mainFrameLocation = NotepadXXV1_2_0.getFrameLoc();
	        Dimension mainFrameSize = NotepadXXV1_2_0.getFrameSize();
	        
	        saveFrame = new JFrame("Saving...");
	        saveFrame.setSize(400, 120);
	        saveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        saveFrame.setLayout(new BorderLayout(10, 10));
	        
	        try {
	            ImageIcon icon = new ImageIcon(getClass().getResource("/icons/NotepadXXLogo.png"));
	            if (icon != null) {
	                saveFrame.setIconImage(icon.getImage());
	            }
	        } catch (Exception e) {
	          //  System.err.println("Could not load frame icon: " + e.getMessage());
	        }
	        
	        Color bgColor = isdarkmode ? new Color(43, 43, 43) : Color.WHITE;
	        Color fgColor = isdarkmode ? Color.WHITE : Color.BLACK;
	        Color borderColor = isdarkmode ? new Color(90, 90, 90) : new Color(220, 220, 220);

	        
	        
	        // Modern panel with padding
	        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
	        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	        //contentPanel.setBackground(Color.WHITE);
	        contentPanel.setBackground(bgColor);
	        
	        JLabel loadingLabel = new JLabel("Saving: " + file.getName(), SwingConstants.CENTER);
	        loadingLabel.putClientProperty("FlatLaf.style", "font: 13 $semibold.font");
	        loadingLabel.setForeground(fgColor);
	        
	        // Add some visual hierarchy
	        JPanel topPanel = new JPanel(new BorderLayout());
	       // topPanel.setBackground(Color.WHITE);
	        topPanel.setBackground(bgColor);
	        topPanel.add(loadingLabel, BorderLayout.CENTER);
	        
	        // Add save icon
	        JLabel iconLabel = new JLabel(createSaveIcon());
	        topPanel.add(iconLabel, BorderLayout.WEST);
	        
	        contentPanel.add(topPanel, BorderLayout.NORTH);
	        contentPanel.add(progressBar, BorderLayout.CENTER);
	        
	        saveFrame.add(contentPanel);
	        
	        // Calculate the center position
	        int x = mainFrameLocation.x + (mainFrameSize.width - saveFrame.getWidth()) / 2;
	        int y = mainFrameLocation.y + (mainFrameSize.height - saveFrame.getHeight()) / 2;
	        saveFrame.setLocation(x, y);
	        
	        // Modern window styling
	      //  saveFrame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
	        saveFrame.getRootPane().setBorder(BorderFactory.createLineBorder(borderColor));
	        saveFrame.setResizable(false);
	        saveFrame.setVisible(true);
	    }

	    // Update progress bar safely
	    SwingUtilities.invokeLater(() -> {
	        if (progressBar != null) {
	            progressBar.setValue(progress);
	            progressBar.setString(progress + "%");
	            isSaving = true;
	            if (progress >= 100) {
	                progressBar.setForeground(new Color(0, 180, 0)); // Green when complete
	            }
	        }
	    });
	    
	    // Close the frame when saving is complete
	    if (progress >= 100) {
	   //     frameshown = false;
	        SwingUtilities.invokeLater(() -> {
	            // Cancel any pending completion timer
	            if (completionTimer != null) {
	                completionTimer.stop();
	                completionTimer = null;
	            }
	            
	            // Schedule new completion timer
	            completionTimer = new Timer(100, e -> {
	                //synchronized (this) {
	            //	 editor.updateAfterSave(file,textArea);
	            	isSaving = false;
	                    if (saveFrame != null) {
	                        saveFrame.dispose();
	                        saveFrame = null;
	                    }
	                    progressBar = null;
	                    completionTimer = null;
	              //  }
	            	 
	            });
	            completionTimer.setRepeats(false);
	            completionTimer.start();
	        });
	    }
	}
	   public static boolean getSaving(){
       	return  isSaving;
       }


	private ImageIcon createSaveIcon() {
	    BufferedImage icon = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = icon.createGraphics();

	    // Smooth rendering
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    // Draw floppy disk shape
	    g2d.setColor(new Color(0, 150, 255)); // Modern blue
	    g2d.fillRoundRect(4, 2, 16, 18, 4, 4); // Disk body
	    g2d.setColor(new Color(200, 230, 255)); // Lighter blue
	    g2d.fillRect(8, 4, 8, 4); // Label area
	    g2d.setColor(new Color(0, 150, 255));
	    g2d.fillRect(14, 8, 2, 10); // Metal slider

	    g2d.dispose();
	    return new ImageIcon(icon);
	}
	
}
