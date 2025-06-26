package com.notepadxx.save;

import java.io.File;

import javax.swing.JOptionPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.notepadxx.filewatcher.FileWatcher;
import com.notepadxx.notepadxx.Texteditor;
import com.notepadxx.open.LazyLoading;

public class SAVE {

  private Texteditor editor;
  private File currentFile;
  private RSyntaxTextArea textArea;
  private RTextScrollPane scrollPane;
  private String tabTitle;
  private FileWatcher fileWatcher;
  
  
	public SAVE(Texteditor editor,File currentFile, RSyntaxTextArea textArea,RTextScrollPane scrollPane,String tabTitle, FileWatcher fileWatcher) {
		this.editor = editor;
		this.currentFile = currentFile;
		this.textArea = textArea;
		this.scrollPane = scrollPane;
		this.tabTitle = tabTitle; 
		this.fileWatcher = fileWatcher;
	}
	

	
	public  void saveFile() {
	    if (isFileLoading()) return;
	  saveFile  save = new saveFile(editor,currentFile,textArea,scrollPane,tabTitle,fileWatcher);
		save.saveFile1();
	}
	
	public void saveAs() {
	    if (isFileLoading()) return;
	saveAs S  = new saveAs(editor,currentFile,textArea,scrollPane,tabTitle,fileWatcher);
		S.saveFileAs();
	}
	
	public synchronized void saveAll() {
	    if (isFileLoading()) return;
		saveAllFile S = new saveAllFile();
		S.saveAll();
	}
	
	
	public void writeFile() {
	    if (isFileLoading()) return;
	write	W = new write(editor,textArea,scrollPane,tabTitle);
	      W.fileSizeToWrite(currentFile);
	}
	
	private boolean isFileLoading() {
        if (currentFile == null) return false;
        
        if (LazyLoading.getLoading()) {
            JOptionPane.showMessageDialog(editor, currentFile.getName() + " is still loading, please wait!",
                                          "Warning", JOptionPane.WARNING_MESSAGE);
            return true;
            
        }else if(write.getSaving()) {
        	 JOptionPane.showMessageDialog(editor, currentFile.getName() + " is still Saving, please wait!",
                     "Warning", JOptionPane.WARNING_MESSAGE);
return true;
        }
        return false;
    }

	
} 
