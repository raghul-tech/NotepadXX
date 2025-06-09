package com.notepadxx.save;

import java.io.File;

import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.notepadxx.filewatcher.FileWatcher;
import com.notepadxx.notepadxx.Texteditor;

public class saveFile {

	  private Texteditor editor;
	  private File currentFile;
	  private RSyntaxTextArea textArea;
	  private RTextScrollPane scrollPane;
	  private String tabTitle;
	  private FileWatcher fileWatcher;
		
		public saveFile(Texteditor editor,File currentFile, RSyntaxTextArea textArea,RTextScrollPane scrollPane,String tabTitle,FileWatcher fileWatcher) {
			this.editor = editor;
			this.currentFile = currentFile;
			this.textArea = textArea;
			this.scrollPane = scrollPane;
			this.tabTitle = tabTitle;
			this.fileWatcher = fileWatcher;
		} 
	
	protected void saveFile1() { 
		 // new Thread(() -> {
		 SwingUtilities.invokeLater(() ->{
			 
			 if(editor == null) return;
	
			 if(fileWatcher != null)  fileWatcher.setSaving(true);  // Set the saving flag before saving

			 if(textArea.getText().isEmpty()) {
				//  JOptionPane.showMessageDialog(this, "Cannot save an empty file","Error",JOptionPane.ERROR_MESSAGE);
				 fileWatcher.setSaving(false);
				
				  return;
			  }
		 });

			  new Thread(() -> {
				  JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
			        int scrollPos = verticalScrollBar.getValue();

					if (currentFile == null || !currentFile.exists()) {
						saveAs	S  = new saveAs(editor,currentFile,textArea,scrollPane,tabTitle,fileWatcher);
							S.saveFileAs();
				    }else {
				    		write	W = new write(editor,textArea,scrollPane,tabTitle);
				    		      W.fileSizeToWrite(currentFile);
					    }
					  // Re-enable the save button after the file is saved
			        SwingUtilities.invokeLater(() ->{

			           //  fileWatcher.setSaving(false);
			             verticalScrollBar.setValue(scrollPos);
			            		 });
			    }).start();
		  }

	
}
 