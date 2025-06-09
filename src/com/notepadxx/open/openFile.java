package com.notepadxx.open;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class openFile {

	 private Texteditor editor;
	  private File currentFile; 
	  private String tabTitle;
	  private boolean isModified = false;
	  private RSyntaxTextArea textArea;
	
	
		public openFile(Texteditor editor,File currentFile,String tabTitle,boolean isModified, RSyntaxTextArea textArea) {
			this.editor = editor;
			this.currentFile = currentFile;
			this.tabTitle = tabTitle; 
			this.isModified = isModified;
			this.textArea = textArea; 
		}
		
		
		 
	protected synchronized void openFile1() {
	    // Try to initialize JavaFX and use its FileChooser if available
	    if (OPEN.isJavaFXAvailable()) {
	        // JavaFX FileChooser block
	        try {
	            SwingUtilities.invokeLater(() -> {
	                new JFXPanel(); // Initializes JavaFX runtime if not already initialized
	                Platform.runLater(() -> {
	       
	                    FileChooser fileChooser = new FileChooser();
	                    fileChooser.setTitle("Open");
	                    
	                    Stage dummyStage = new Stage();
	                    dummyStage.getIcons().add(new Image(getClass().getResource("/icons/LogoX.png").toExternalForm()));

	                    dummyStage.initStyle(StageStyle.UTILITY);
	                    dummyStage.setWidth(1);
	                    dummyStage.setHeight(1);
	                    dummyStage.setX(-1000);
	                    dummyStage.setY(-1000);
	                    dummyStage.show(); // You must show it, even if off-screen
	                  
	                    Texteditor previousEditor = NotepadXXV1_2_0.getPreviousEditor();
	                    // Set the initial directory
	                    if (currentFile != null && currentFile.getParentFile() != null) {
	                        fileChooser.setInitialDirectory(currentFile.getParentFile());
	                    }else if(previousEditor!=null && previousEditor.getCurrentFile()!=null){ 
	                    	fileChooser.setInitialDirectory(previousEditor.getCurrentFile().getParentFile());
	                    }else {
	                        File documentsFolder = new File(System.getProperty("user.home"), "Documents");
	                        if (documentsFolder.exists() && documentsFolder.isDirectory()) {
	                            fileChooser.setInitialDirectory(documentsFolder);
	                        } else {
	                            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	                        }
	                    }
	                  
	                    // Open JavaFX file dialog
	                  //  File selectedFile = fileChooser.showOpenDialog(null);
	                    File selectedFile = fileChooser.showOpenDialog(dummyStage);
	                    dummyStage.close(); // Dispose dummy stage after use
	                    
	                    
	                    if (selectedFile != null) {
	                        SwingUtilities.invokeLater(() -> {
	                            if (currentFile != null || isModified) {
	                               NotepadXXV1_2_0.openNewTab(selectedFile);
	                            } else {
	                       		loadFile	 L = new loadFile(editor, tabTitle, textArea);
	                       			L.fileSizeToOpen(selectedFile);
	                            }
	                        });
	                    }
	                    
	                });
	            });
	        } catch (Exception e) {
	            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
	                    null, "Error While Opening file: " + e.getMessage(),
	                    "Open Error", JOptionPane.ERROR_MESSAGE
	            ));
	        }
	    } else {
	        // Fallback to Swing JFileChooser if JavaFX is not available
	        openFileWithSwing();
	    }
	}

	// Helper method to open file using Swing JFileChooser
	private synchronized void openFileWithSwing() {
	    try {
	       // SwingUtilities.invokeLater(() -> {
	    	new Thread(()->{
	         //   JFileChooser fileChooser = new JFileChooser();
	           // fileChooser.setDialogTitle("Open File");
	    		 JFrame frame = new JFrame();
	    	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	        frame.setIconImage(new ImageIcon(RenameFile.class.getResource("/icons/NotepadXXLogo.png")).getImage());

	    	        JFileChooser fileChooser = new JFileChooser();
	    	        fileChooser.setDialogTitle("Open File");
	    	      
	            // Set the initial directory
	            Texteditor previousEditor = NotepadXXV1_2_0.getPreviousEditor();
	            if (currentFile != null && currentFile.getParentFile() != null) {
	                fileChooser.setCurrentDirectory(currentFile.getParentFile());
	            }else if(previousEditor!=null && previousEditor.getCurrentFile()!=null){ 
                	fileChooser.setCurrentDirectory(previousEditor.getCurrentFile().getParentFile());
                } else {
	                File documentsFolder = new File(System.getProperty("user.home"), "Documents");
	                if (documentsFolder.exists() && documentsFolder.isDirectory()) {
	                    fileChooser.setCurrentDirectory(documentsFolder);
	                } else {
	                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	                }
	            }
	            // Open Swing file dialog
	            int result = fileChooser.showOpenDialog(frame);
	            if (result == JFileChooser.APPROVE_OPTION) {
	                File selectedFile = fileChooser.getSelectedFile();
	                if (selectedFile != null) {
	                    if (currentFile != null || isModified) {
	                       NotepadXXV1_2_0.openNewTab(selectedFile);
	                    } else {
	                    	loadFile	 L = new loadFile(editor, tabTitle, textArea);
                   			L.fileSizeToOpen(selectedFile);
	                    }
	                }
	            }
	          frame.dispose();
	       // });
	    }).start();

	    } catch (Exception e) {
	        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
	                null, "Error While Opening file: " + e.getMessage(),
	                "Open Error", JOptionPane.ERROR_MESSAGE
	        ));
	       }
	}

	
}
