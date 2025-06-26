package com.notepadxx.open;

import java.io.File;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.notepadxx.notepadxx.Texteditor;

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



}
