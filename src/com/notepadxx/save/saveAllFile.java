package com.notepadxx.save;

import java.awt.Component;
import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.notepadxx.notepadxx.NotepadXXV1_2_1;
import com.notepadxx.notepadxx.Texteditor;
import com.notepadxx.open.RenameFile;
import com.notepadxx.utils.JavaFXUtils;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class saveAllFile { 
	
	protected  void saveAll() {
		
//	    int tabCount = notepad.tabbedPane.getTabCount();
	    new Thread(() ->{
	    	int tabCount = NotepadXXV1_2_1 .getTabbedPane().getTabCount();
	   // 	int tabCount = SwingUtilities.invokeLater(() -> notepad.tabbedPane.getTabCount());
	    // Check if JavaFX is available
	    boolean isJavaFXAvailable = JavaFXUtils.isJavaFXAvailable();

	    // Initialize JavaFX runtime if available
	    if (isJavaFXAvailable) {
	       // SwingUtilities.invokeLater(() -> new JFXPanel());
	        //SwingUtilities.invokeLater(JFXPanel::new);
	    	new JFXPanel();
	    }  

	    for (int i = 0; i < tabCount; i++) { 
	        Component component = NotepadXXV1_2_1 .getTabbedPane().getComponentAt(i);
	        if (component instanceof Texteditor editor) {
	            File file = editor.getCurrentFile();
	          
	            if(editor.getFileWatcher() != null) editor.getFileWatcher().setSaving(true);  // Set the saving flag before saving

	            // Skip saving if the text content is empty
	            if (editor.getTextContent().isEmpty()) {
	            	editor.getFileWatcher().setSaving(false);
	                continue;
	            }

	            // If no file is associated or file doesn't exist, prompt the user to save
	            if (file == null || !file.exists()) {
	                String defaultFileName = editor.getTabTitle() + ".txt";
	                File selectedFile = null;

	                if (isJavaFXAvailable) {
	                    // Use JavaFX FileChooser
	                    selectedFile = showJavaFXFileChooser(editor, defaultFileName);
	                } else {
	                    // Use Swing JFileChooser as a fallback
	                    selectedFile = showSwingFileChooser(editor, defaultFileName);
	               }

	                // If the user selected a file, save it
	                if (selectedFile != null) {
	                  // editor.setCurrentFile(selectedFile);			 
					  		write W = new write(editor,editor.getTextArea(),editor.getScrollPane(),editor.getTabTitle());
					  	      W.fileSizeToWrite(selectedFile);
	                }
	            } else {
	            	write W = new write(editor,editor.getTextArea(),editor.getScrollPane(),editor.getTabTitle());
			  	      W.fileSizeToWrite(file);
	            }
	           // editor.getFileWatcher().setSaving(false);
	        }
	    }
	    }).start();
	}

	// Use JavaFX FileChooser if available
	private synchronized  File showJavaFXFileChooser(Texteditor editor, String defaultFileName) {
	    CountDownLatch latch = new CountDownLatch(1);
	    final File[] selectedFile = {null};
	    Platform.runLater(() -> {
	    	 try {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Save As");
	        Stage dummyStage = new Stage();
            dummyStage.getIcons().add(new Image(getClass().getResource("/icons/LogoX.png").toExternalForm()));

            dummyStage.initStyle(StageStyle.UTILITY);
            dummyStage.setWidth(1);
            dummyStage.setHeight(1);
            dummyStage.setX(-1000);
            dummyStage.setY(-1000);
            dummyStage.show(); // You must show it, even if off-screen
	        
	        fileChooser.setInitialFileName(defaultFileName);

	        Texteditor previousEditor = NotepadXXV1_2_1.getPreviousEditor();
	         // Set the initial directory
	            if (editor.getCurrentFile() != null &&editor.getCurrentFile().getParentFile() != null) {
	                // Priority 1: Parent directory of the current file
	                fileChooser.setInitialDirectory(editor.getCurrentFile().getParentFile());
	            } else if(previousEditor!=null && previousEditor.getCurrentFile()!=null){ 
                    fileChooser.setInitialDirectory(previousEditor.getCurrentFile().getParentFile());
               }else {
	                // Get the user's "Documents" folder
	                File documentsFolder = new File(System.getProperty("user.home"), "Documents");

	                // Priority 2: User's "Documents" folder
	                if (documentsFolder.exists() && documentsFolder.isDirectory()) {
	                    fileChooser.setInitialDirectory(documentsFolder);
	                } else {
	                    // Priority 3: User's home directory as the last resort
	                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	                }
	            }

	     //   selectedFile[0] = fileChooser.showSaveDialog(null);
	       selectedFile[0] = fileChooser.showSaveDialog(dummyStage);
	       dummyStage.close();
	        
	    	 } catch (Exception e) {
	             e.printStackTrace();
	         } finally {
	             latch.countDown();
	         }
	    });

	    try {
	        latch.await();
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }

	    // Ensure the file has a ".txt" extension if no extension is provided
	    if (selectedFile[0] != null && !selectedFile[0].getName().contains(".")) {
	        return new File(selectedFile[0].getAbsolutePath() + ".txt");
	    }

	    return selectedFile[0];
	}

	// Use Swing JFileChooser as a fallback
	private  synchronized File showSwingFileChooser(Texteditor editor, String defaultFileName) {

		 JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setIconImage(new ImageIcon(RenameFile.class.getResource("/icons/NotepadXXLogo.png")).getImage());

	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Save As");
	    fileChooser.setSelectedFile(new File(defaultFileName));

	    Texteditor previousEditor = NotepadXXV1_2_1.getPreviousEditor();
	     // Set the initial directory
	     if (editor.getCurrentFile() != null && editor.getCurrentFile().getParentFile() != null) {
	         fileChooser.setCurrentDirectory(editor.getCurrentFile().getParentFile());
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

	    int userSelection = fileChooser.showSaveDialog(frame);
	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File file = fileChooser.getSelectedFile();

	        // Ensure the file has a ".txt" extension if no extension is provided
	        if (!file.getName().contains(".")) {
	            file = new File(file.getAbsolutePath() + ".txt");
	        }

	        // Confirm overwrite if file exists
	        if (file.exists()) {
	            int confirm = JOptionPane.showConfirmDialog(
	                    null,
	                    file.getName() + " already exists. Overwrite?",
	                    "Confirm",
	                    JOptionPane.YES_NO_OPTION
	            );
	            if (confirm != JOptionPane.YES_OPTION) {
	            	frame.dispose();
	                return null;
	            }
	        }
	        frame.dispose();
	        return file;
	    }
	    frame.dispose();
	    return null;
	}


	
}
