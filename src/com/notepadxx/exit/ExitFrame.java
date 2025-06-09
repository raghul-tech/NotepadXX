package com.notepadxx.exit;

import java.awt.Component;
import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.notepadxx.menu.CodeAnalysisMenuBar;
import com.notepadxx.menu.LanguageMenuBar;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;


public class ExitFrame {

	
	public static  void handleWindowClosing(JFrame frame) {
	    SwingUtilities.invokeLater(() -> {
	        if (frame == null) return;

	        // First remove any non-Texteditor tabs
	        for (int i = NotepadXXV1_2_0.getTabbedPane().getTabCount() - 1; i >= 0; i--) {
	            Component comp = NotepadXXV1_2_0.getTabbedPane().getComponentAt(i);
	            if (!(comp instanceof Texteditor)) {
	                NotepadXXV1_2_0.getTabbedPane().removeTabAt(i);
	            }
	        }
	        
	        // Check if there are unsaved changes
	        boolean hasUnsavedChanges = false;

	        for (int i = 0; i < NotepadXXV1_2_0.getTabbedPane().getTabCount(); i++) {
	            Texteditor editor = (Texteditor) NotepadXXV1_2_0.getTabbedPane().getComponentAt(i);
	            if (editor.getisModified()) {
	                hasUnsavedChanges = true;
	                break;
	            }
	        /*	Component comp = NotepadXXV1_2_0.getTabbedPane1().getComponentAt(i);

	            if (comp instanceof Texteditor) {
	                Texteditor editor = (Texteditor) comp;
	                if (editor.getisModified()) {
	                    hasUnsavedChanges = true;
	                }
	            } else {
	                // Remove the tab if it is not a Texteditor
	                NotepadXXV1_2_0.getTabbedPane1().removeTabAt(i);
	            }*/
	        }

	        // If there are unsaved changes, ask the user
	        if (hasUnsavedChanges) {
	       
	        	int option = JOptionPane.showConfirmDialog(NotepadXXV1_2_0.getFrame(),
	        		    "Save changes before closing?",
	        		    "Unsaved Changes",
	        		    JOptionPane.YES_NO_CANCEL_OPTION,
	        		    JOptionPane.WARNING_MESSAGE);


	            if (option == JOptionPane.YES_OPTION) {
	                new SwingWorker<Boolean, Void>() {
	                    @Override
	                    protected Boolean doInBackground() {
	                        return saveAllBeforeExit();
	                    }

	                    @Override
	                    protected void done() {
	                        try {
	                            if (get()) {
	                            	 updateForWindowClosing(frame);
	                            } else {
	                                JOptionPane.showMessageDialog(frame,
	                                        "Some files were not saved.",
	                                        "Warning",
	                                        JOptionPane.WARNING_MESSAGE);
	                            }
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }.execute();
	            } else if (option == JOptionPane.NO_OPTION) {
	            	 updateForWindowClosing(frame);
	            }
	        } else {
	        	 updateForWindowClosing(frame);
	        }
	    });
	}

	private static void updateForWindowClosing(JFrame frame) {
		/* NotepadXXV1_2_0.saveOpenedFiles();
		 NotepadXXV1_2_0.saveWindowSize(frame.getSize());*/
		/*if(editor.getMDwindow() != null) {
	    	editor.getMDwindow().dispose();
	    	}
		if(editor.getMDTab() != null) {
	    	editor.getMDTab().dispose();
	    	}*/
		 NotepadXXV1_2_0.clearAllRecentClosedTabs();
	     ClearMenuComponentFiles();
	     frame.dispose();
	      System.exit(0); 
	    
	}

	private static boolean saveAllBeforeExit() {
	    // Ensure JavaFX is initialized
	  //  new JFXPanel(); // Only needed once in your application
		
	    int tabCount = NotepadXXV1_2_0.getTabbedPane().getTabCount();
	    boolean allSaved = true; // Track if all files are saved successfully

	    for (int i = 0; i < tabCount; i++) {
	        Component component = NotepadXXV1_2_0.getTabbedPane().getComponentAt(i);
	        if (component instanceof Texteditor) {
	            Texteditor editor = (Texteditor) component;
	            if (!editor.getisModified()) {
	                continue;
	            }
	            File file = editor.getCurrentFile();

	            // If no file is associated, prompt the user to save with a default name
	            if (file == null || !file.exists()) {
	                file = promptUserToSaveFile(editor);
	            	//file = showSwingFileChooser(editor);
	                if (file == null) {
	                    allSaved = false; // User canceled save for this tab
	                    continue; // Skip saving this tab if the user cancels
	                }
	                editor.setCurrentFile(file); // Set the selected file as currentFile
	            }
	            
	           
	            // Save the file
	            try {
	            	editor.writeFile(file);
	            // 	editor.saveFile();
	            	   while (editor.getisModified()) {
	            	        Thread.sleep(100); // Wait 100ms before checking again
	            	    }
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null,
	                        "Error saving file: " + (file != null ? file.getName() : editor.getTabTitle()),
	                        "Save Error", JOptionPane.ERROR_MESSAGE);
	                allSaved = false; // Mark as unsuccessful if any save fails
	                break;
	            }
	           
	            if(editor.getMDwindow() != null) {
	    	    	editor.getMDwindow().dispose();
	    	    	}
	    		if(editor.getMDTab() != null) {
	    	    	editor.getMDTab().dispose();
	    	    	}
	        }
	  
	    }

	  /*  if (allSaved) {
	        JOptionPane.showMessageDialog(null, "All files have been saved successfully.", "Save All", JOptionPane.INFORMATION_MESSAGE);
	    }*/

	    return allSaved;
		  
	}

	private static File promptUserToSaveFile(Texteditor editor) {
	    if (editor.isJavaFXAvailable()) {
	        return showJavaFXFileChooser(editor);
	    } else {
	        return showSwingFileChooser(editor);
	    }
	}

	private static File showJavaFXFileChooser(Texteditor editor) {
	    CountDownLatch latch = new CountDownLatch(1); // To wait for JavaFX thread to complete
	    final File[] selectedFile = {null}; // Array to hold the selected file
	   new JFXPanel();
	    Platform.runLater(() -> {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Save As");
	   	 String defaultFileName = (editor.getTabTitle() != null && !editor.getTabTitle().isEmpty())
                 ? editor.getTabTitle() + ".txt"
                 : "Untitled.txt";
	    //    fileChooser.setInitialFileName(editor.getTabTitle() + ".txt");
	        fileChooser.setInitialFileName(defaultFileName);

	        Texteditor previousEditor = NotepadXXV1_2_0.getPreviousEditor();
	        // Set the initial directory
	           if (editor.getCurrentFile() != null && editor.getCurrentFile().getParentFile() != null) {
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


	        // Show the Save dialog
	        selectedFile[0] = fileChooser.showSaveDialog(null);
	        latch.countDown(); // Notify that the dialog is closed
	    });

	    // Wait for the JavaFX FileChooser to complete
	    try {
	        latch.await();
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }

	    if (selectedFile[0] != null) {
	        // Ensure the file has a proper extension
	        if (!selectedFile[0].getName().contains(".")) {
	            return new File(selectedFile[0].getAbsolutePath() + ".txt");
	        }

	        // Check if file already exists and confirm overwrite
	        if (selectedFile[0].exists()) {
	            int confirm = JOptionPane.showConfirmDialog(null,selectedFile[0].getName()+
	                    "File already exists. Overwrite?", "Confirm",
	                    JOptionPane.YES_NO_OPTION);
	            if (confirm != JOptionPane.YES_OPTION) {
	                return null; // User chose not to overwrite
	            }
	        }
	    }

	    return selectedFile[0];
	}

	//Use Swing JFileChooser as a fallback
	private static File showSwingFileChooser(Texteditor editor) {

	 JFileChooser fileChooser = new JFileChooser();
	 fileChooser.setDialogTitle("Save As");
	//Set the default file name
	 String defaultFileName = (editor.getTabTitle() != null && !editor.getTabTitle().isEmpty())
	                          ? editor.getTabTitle() + ".txt"
	                          : "Untitled.txt";
	 fileChooser.setSelectedFile(new File(defaultFileName));

	 Texteditor previousEditor = NotepadXXV1_2_0.getPreviousEditor();
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

	 int userSelection = fileChooser.showSaveDialog(null);
	 if (userSelection == JFileChooser.APPROVE_OPTION) {
	     File file = fileChooser.getSelectedFile();

	     // Ensure the file has a proper extension
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
	             return null;
	         }
	     }
	     return file;

	 }

	 return null;

	}
	
		public static  void exit() {
		    NotepadXXV1_2_0.saveOpenedFiles();
		    NotepadXXV1_2_0.saveWindowSize(NotepadXXV1_2_0.getFrameSize());
		    handleWindowClosing(NotepadXXV1_2_0.getFrame());
		}
		
		private static void ClearMenuComponentFiles() {
			// TODO Auto-generated method stub
			LanguageMenuBar.clearConfigLanguageFile();
			CodeAnalysisMenuBar.clearConfigCodeAnalysisFile();
		}

	
}
