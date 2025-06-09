package com.notepadxx.open;

import java.io.File;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.notepadxx.notepadxx.Texteditor;

import javafx.application.Platform;

public class OPEN {
	
	  private Texteditor editor;
	  private File currentFile;
	  private String tabTitle;
	  private boolean isModified = false;
	  private RSyntaxTextArea textArea;
	
		public OPEN(Texteditor editor,File currentFile,String tabTitle,boolean isModified, RSyntaxTextArea textArea) {
			this.editor = editor;
			this.currentFile = currentFile;
			this.tabTitle = tabTitle;
			this.isModified = isModified;
			this.textArea = textArea; 
		}
		
		
		public  void openFile() {
		openFile O = new openFile(editor,currentFile,tabTitle,isModified,textArea);
			O.openFile1();
		}
		
		public void loadFile(File file) {
			loadFile L = new loadFile(editor, tabTitle, textArea);
			L.fileSizeToOpen(file);
		} 
		
		public void RenameFile() {
		RenameFile  renameFile = new RenameFile(editor);
			renameFile.renameFileFX(currentFile);
		} 
		

		protected static boolean isJavaFXAvailable() { 
		    try {
		        // Check if JavaFX FileChooser class exists 
		     //   Class.forName("javafx.stage.FileChooser");
		    	  // Shut down JavaFX if already initialized (useful for resetting state)
		        if (Platform.isFxApplicationThread()) {
		            Platform.exit();
		        }
		        new javafx.embed.swing.JFXPanel(); // Forces JavaFX to initialize

		        return true; // JavaFX is available and fully functional
		    } catch ( RuntimeException |Error e) {
		        return false; // JavaFX is not available or failed to initialize
		    } catch (Throwable e) {
		        return false; // Catch other unexpected errors
		    }
		}



}
