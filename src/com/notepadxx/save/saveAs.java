package com.notepadxx.save;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.notepadxx.filewatcher.FileWatcher;
import com.notepadxx.menu.LanguageMenuBar;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;
import com.notepadxx.open.RenameFile;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class saveAs {

	
	  private Texteditor editor;
	  private File currentFile; 
	  private RSyntaxTextArea textArea;
	  private RTextScrollPane scrollPane;
	  private String tabTitle; 
	  private FileWatcher fileWatcher;
	
	//  private static NotepadXXV1_2_0 notepad = new NotepadXXV1_2_0();
		
		public saveAs(Texteditor editor,File currentFile, RSyntaxTextArea textArea,RTextScrollPane scrollPane,String tabTitle,FileWatcher fileWatcher) {
			this.editor = editor;
			this.currentFile = currentFile;
			this.textArea = textArea;
			this.scrollPane = scrollPane;
			this.tabTitle = tabTitle;
			this.fileWatcher = fileWatcher;
		}
	
	//saveFileAs() method
			protected synchronized void saveFileAs() { 
				// SwingUtilities.invokeLater(() ->{
			   
				 if(fileWatcher != null) fileWatcher.setSaving(true); // Set the saving flag before saving

			    if(textArea.getText().isEmpty()) {
					//  JOptionPane.showMessageDialog(this, "Cannot save an empty file","Error",JOptionPane.ERROR_MESSAGE);
					 fileWatcher.setSaving(false);
					  return;
				  }
				 //});
			    if (SAVE.isJavaFXAvailable()) {
			    // Initialize JavaFX FileChooser for file saving
			    SwingUtilities.invokeLater(() -> {
			        new JFXPanel(); // Initializes JavaFX runtime

			        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
			        int scrollPos = verticalScrollBar.getValue();
			        String defaultFileName = tabTitle + ".txt";
			        Platform.runLater(() -> {
			            // Set up the JavaFX FileChooser
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

			            Texteditor previousEditor = NotepadXXV1_2_0.getPreviousEditor();
			         // Set the initial directory
			            if (currentFile != null && currentFile.getParentFile() != null) {
			                // Priority 1: Parent directory of the current file
			                fileChooser.setInitialDirectory(currentFile.getParentFile());
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

			            fileChooser.setInitialFileName(defaultFileName);
			            // Set FileChooser filters (for specific file types, optional) NOT IN USE
			         /*   FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
			            FileChooser.ExtensionFilter javaFilter = new FileChooser.ExtensionFilter("Java Files (*.java)", "*.java");
			            fileChooser.getExtensionFilters().addAll(txtFilter, javaFilter);*/

			            // Show the Save dialog and get the selected file
			         //  File selectedFile = fileChooser.showSaveDialog(null);
			            File selectedFile = fileChooser.showSaveDialog(dummyStage);
			            dummyStage.close();
			       
			            if (selectedFile != null) {
			                // Ensure the selected file has the proper extension
			                if (!selectedFile.getName().contains(".")) {
			                    selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
			                }

			                // Check if the file already exists
			                if (selectedFile.exists()) {
			                    int confirm = JOptionPane.showConfirmDialog(editor, selectedFile.getName()+" already exists. Overwrite?", "Confirm", JOptionPane.YES_NO_OPTION);
			                    if (confirm != JOptionPane.YES_OPTION) {
			                    // isModified = true;
			                    	editor.setModified(true);
			                      //  fileWatcher.setSaving(false);
			                        return;
			                    }
			                }

			                // Save the file
			                currentFile = selectedFile;
			                
			                if(editor.getisModified() && editor.getCurrentFile()==null) {
			                		write	W = new write(editor,textArea,scrollPane,tabTitle);
			                		      W.fileSizeToWrite(currentFile);
						      String ext = editor.getFileExtension(currentFile);
						      LanguageMenuBar.setLanguageForFile(currentFile.toString(), ext.toLowerCase());
						 
							      
			                }else {
			                NotepadXXV1_2_0.openNewTab(null);
			               
                            Texteditor editor1 = (Texteditor)  NotepadXXV1_2_0.getTabbedPane().getSelectedComponent();
                            if (editor1 != null) {
                                editor1.setTextContent(editor.getTextContent());
                                editor1.setOriginalContent("");
                              //  editor1.setModified(true);
                                editor1.setTabTitle(selectedFile.getName());
                                editor1.updateTitle(selectedFile.getAbsolutePath());
                            	write	W = new write(editor1,textArea,scrollPane,tabTitle);
                       	      W.fileSizeToWrite(currentFile);
  						      editor1.getFileWatcher().setSaving(false);
  						    String ext = editor1.getFileExtension(currentFile);
						      LanguageMenuBar.setLanguageForFile(currentFile.toString(), ext.toLowerCase());
                            }
                            }
			            }

			            // Reset the saving flag
			          //  fileWatcher.setSaving(false);

			            // Restore the scroll position after the file dialog
			            SwingUtilities.invokeLater(() -> verticalScrollBar.setValue(scrollPos));
			        });
			    });
			    }else {
			    	saveFileAsWithSwing();
			    }
			}
			//Helper method to save file using Swing JFileChooser
			public synchronized  void saveFileAsWithSwing() {
		
				 new Thread(() -> {
					 JFrame frame = new JFrame();
				        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				        frame.setIconImage(new ImageIcon(RenameFile.class.getResource("/icons/NotepadXXLogo.png")).getImage());
			     JFileChooser fileChooser = new JFileChooser();
			     fileChooser.setDialogTitle("Save As");
			     JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
			     int scrollPos = verticalScrollBar.getValue();
			     String defaultFileName = tabTitle + ".txt";

			     Texteditor previousEditor = NotepadXXV1_2_0.getPreviousEditor();
			     // Set the initial directory
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

			     fileChooser.setSelectedFile(new File(defaultFileName));

			     int userSelection = fileChooser.showSaveDialog(frame);
			     if (userSelection == JFileChooser.APPROVE_OPTION) {
			         File selectedFile = fileChooser.getSelectedFile();

			         // Ensure the selected file has the proper extension
			         if (!selectedFile.getName().contains(".")) {
			             selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
			         }

			         // Check if the file already exists
			         if (selectedFile.exists()) {
			             int confirm = JOptionPane.showConfirmDialog(
			                     editor,
			                     selectedFile.getName() + " already exists. Overwrite?",
			                     "Confirm",
			                     JOptionPane.YES_NO_OPTION
			             );
			             if (confirm != JOptionPane.YES_OPTION) {
			                 //isModified = true;
			                 editor.setModified(true);
			                // fileWatcher.setSaving(false);
			                 return;
			             }
			         }

			         // Save the file
		                currentFile = selectedFile;
		                
		                if(editor.getisModified() && editor.getCurrentFile()==null) {
		                	write	W = new write(editor,textArea,scrollPane,tabTitle);
              		      W.fileSizeToWrite(currentFile);
					      String ext = editor.getFileExtension(currentFile);
					      LanguageMenuBar.setLanguageForFile(currentFile.toString(), ext.toLowerCase());
					      
		                }else {
		                NotepadXXV1_2_0.openNewTab(null);
		               
                     Texteditor editor1 = (Texteditor)  NotepadXXV1_2_0.getTabbedPane().getSelectedComponent();
                     if (editor1 != null) {
                         editor1.setTextContent(editor.getTextContent());
                         editor1.setOriginalContent("");
                       //  editor1.setModified(true);
                         editor1.setTabTitle(selectedFile.getName());
                         editor1.updateTitle(selectedFile.getAbsolutePath());
                     	write	W = new write(editor1,textArea,scrollPane,tabTitle);
                     	      W.fileSizeToWrite(currentFile);
					      editor1.getFileWatcher().setSaving(false);
					      String ext = editor1.getFileExtension(currentFile);
					      LanguageMenuBar.setLanguageForFile(currentFile.toString(), ext.toLowerCase());
                     }
                     }
		            }

			     // Reset the saving flag and scroll position
			  //   fileWatcher.setSaving(false);
			     SwingUtilities.invokeLater(() -> verticalScrollBar.setValue(scrollPos));
			 //});
			     frame.dispose();
				 }).start();
			}
	
}
