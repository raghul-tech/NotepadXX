package com.notepadxx.notepadxx;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.notepadxx.exit.CLOSE;
import com.notepadxx.filewatcher.FileWatcher;
import com.notepadxx.flexmark.MarkdownPreviewTab;
import com.notepadxx.flexmark.MarkdownPreviewWindow;
import com.notepadxx.menu.CodeAnalysisMenuBar;
import com.notepadxx.menu.LanguageMenuBar;
import com.notepadxx.menu.WindowMenu;
import com.notepadxx.open.OPEN;
import com.notepadxx.resources.icon.GetImage;
import com.notepadxx.rsyntaxtextarea.syntaxhightlighter.SyntaxHighlighter;
import com.notepadxx.save.SAVE;
import com.notepadxx.syntaxchecker.SyntaxChecker;
import com.notepadxx.utils.LinuxUtils;
import com.notepadxx.utils.WindowsUtils;

import javafx.application.Platform;


@SuppressWarnings("serial")
public class Texteditor extends JPanel   {
  
	private RSyntaxTextArea textArea;
	private RTextScrollPane scrollPane;
	private SyntaxHighlighter syntaxHighlighter;
	private static final int MIN_FONT_SIZE = 10;   // Minimum font size
	private static final int MAX_FONT_SIZE = 34;  // Maximum font size
	private FileWatcher fileWatcher;
    //private Thread fileWatcherThread;
	  private transient WeakReference<Thread> fileWatcherThread;
    private UndoManager undoManager;
    private volatile  boolean isModified = false;
    private String originalContent = "";
   // private StringBuilder originalContent = new StringBuilder();
 //   private volatile long originalContentHash = 0; // Store hash instead of full text
    private File currentFile = null;
    private String tabTitle;
    private Font defaultFont = new Font("Monospaced", Font.PLAIN, 18);
   // private Font defaultFont = new Font("Apple Color", Font.PLAIN, 18);
 //   private Font defaultFont = new Font("Courier New", Font.PLAIN, 18);
   
  //  private final   CRC32 crc = new CRC32();
    //private final    XXHashFactory factory = XXHashFactory.fastestInstance();
    private DocumentListener documentListener; // Store reference to listener
    private MouseAdapter mouseListener;
    private String os = System.getProperty("os.name").toLowerCase();
//private NotepadXXV1_2_0 notepad = new NotepadXXV1_2_0();
private  MarkdownPreviewWindow markdown1 ;
private MarkdownPreviewTab markdown2;
private boolean isPaused = false;
//private Timer backgroundTimer;
private CheckCode checkcode ;


public Texteditor() {

	setLayout(new BorderLayout());
	
//	 SwingUtilities.invokeLater(() -> {
	 textArea = new RSyntaxTextArea();
	 
	// SwingUtilities.invokeLater(() -> {
	 textArea.setAutoIndentEnabled(true);
	 textArea.setAntiAliasingEnabled(true);
     textArea.setCodeFoldingEnabled(true);
     textArea.setUseSelectedTextColor(true);
     textArea.setFont(defaultFont);
     textArea.setPaintTabLines(false);
     textArea.setAnimateBracketMatching(true);
     textArea.setClearWhitespaceLinesEnabled(true);
//     textArea.setEOLMarkersVisible(false);
     textArea.setDragEnabled(true);
     if(NotepadXXV1_2_0.getFrame().isShowing()) {
    	  NotepadXXV1_2_0.updateLineWrap(this);
     }
  
	// });
     syntaxHighlighter = new SyntaxHighlighter(textArea);
		    scrollPane = new RTextScrollPane(textArea);
	        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scrollPane.setLineNumbersEnabled(true);

	
			 add(scrollPane, BorderLayout.CENTER);
	// });
//			  textArea.addMouseListener(new MouseAdapter() {
				  mouseListener = new MouseAdapter() {

				    @Override
				    public void mouseEntered(MouseEvent e) {
				        // Trigger drag-and-drop functionality when the mouse touches the text area
				        enableDragAndDrop();
				    }

				    @Override
				    public void mousePressed(MouseEvent e) {
				        // Trigger when the mouse is pressed inside the text area
				        enableDragAndDrop();
				    //  textArea.requestFocusInWindow();

				    }
				//});
				  };
				  textArea.addMouseListener(mouseListener);


			 //this is for tracking the text area
		//	textArea.getDocument().addDocumentListener(new DocumentListener() {
			  documentListener = new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
				//	checkModified();
					  CompletableFuture.runAsync(() -> checkModified());
					 
				}
				@Override
				public void removeUpdate(DocumentEvent e) {
					//checkModified();
					  CompletableFuture.runAsync(() -> checkModified());
				}
				@Override
				public void changedUpdate(DocumentEvent e) {
					//checkModified();
					  CompletableFuture.runAsync(() -> checkModified());
				}
			//});
			  };
			textArea.getDocument().addDocumentListener(documentListener);
			 undoManager = new UndoManager();
		//	 undoManager.setLimit(100); // Limit to 100 undo steps
			 
			 setUndoManager(undoManager);
			 
			 new Shortcut(this);		 
			 checkcode = new CheckCode(this);
			 checkcode.setEnabled(true);
			 //Background timer for auto-saving or other operations
		        //backgroundTimer = new Timer(2000, e -> performBackgroundTask());
		       // backgroundTimer.start();
}


public void openMarkdownPreviewWindow(Texteditor editor) {
	// MarkdownPreview.showMarkdownPreview(tabbedPane, editor);

	 if(markdown1 == null) {
		 markdown1 = new MarkdownPreviewWindow(editor);
	 }else {
		 markdown1.reopen();
	 }
	// markdown.openMarkdownPreviewTabFX(editor);
	 
	 
 }
public void openMarkdownPreviewTab(Texteditor editor) {
	// MarkdownPreview.showMarkdownPreview(tabbedPane, editor);

	 if(markdown2 == null) {
		 markdown2 = new MarkdownPreviewTab(NotepadXXV1_2_0.getTabbedPane());
	 }
	 markdown2.openMarkdownPreviewTabFX(editor);
	 
 }


public void pauseEditor(Texteditor editor) {
    if (!editor.isPaused) {
    	
        editor.isPaused = true;
     //   textArea.setEnabled(false); // Disable text input
      //  editor.textArea.getDocument().removeDocumentListener(editor.getDocumentListener()); // Remove listeners
      //  backgroundTimer.stop(); // Stop background tasks
    //    System.out.println("Paused: " + tabTitle);
      //  editor = null;
     
      editor.checkcode.setEnabled(false);
        //editor.setEnabled(false);
      //  editor = null;
    }
}

public void resumeEditor(Texteditor editor) {
    if (editor.isPaused) {
        editor.isPaused = false;
       // textArea.setEnabled(true); // Enable text input
     //   editor.textArea.getDocument().addDocumentListener(editor.getDocumentListener()); // Restore listeners
    //   backgroundTimer.start(); // Restart background tasks
   //     System.out.println("Resumed: " + tabTitle);
        editor.checkcode.setEnabled(true);
     //   editor.setEnabled(true);
       // editor = this;
        
        
        
    }
}

/*private void performBackgroundTask() {
    if (!isPaused) {
    	SwingUtilities.invokeLater(() ->{
    	
        checkcode.setEnabled(true);
    	});
        // Auto-save or background processing logic
        System.out.println("Performing background task in: " + tabTitle);
    }
}*/

//to get extension
public String getFileExtension(File file) {
	if(file== null) {
		return"";
	}
	
String fileName = file.getName();
if (fileName.toLowerCase().startsWith("dockerfile.")) {
    return "dockerfile"; // Return the full file name
}


int dotIndex = fileName.lastIndexOf('.');
if (dotIndex >= 0) {
   return fileName.substring(dotIndex + 1);
} else {
   return fileName; // No extension

}
}

/*
 * it is for drag and drop
 */

private void enableDragAndDrop() {
	  if (textArea == null) {
     //   System.err.println("Drag and drop not enabled: textArea is null.");
        return;
    }
	  
//SwingUtilities.invokeLater(() ->{		
if (os.contains("win")) {
	WindowsEnableDragAndDrop();
} else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	LinuxEnableDragAndDrop();
} else {
  JOptionPane.showMessageDialog(
      null,
      "Unsupported operating system.",
      "Error",
      JOptionPane.ERROR_MESSAGE
  ); 
}
//});
	
	//LinuxEnableDragAndDrop();
}


private void WindowsEnableDragAndDrop() {
    if (textArea == null) {
        return;
    }
    
    // Simplified version using only TransferHandler
    textArea.setTransferHandler(new FileDropHandler());
    
    // Visual feedback can be handled through the TransferHandler's events
}

private class FileDropHandler extends TransferHandler {
	 private Color originalBackground = null;
    @Override
    public boolean canImport(TransferSupport support) {
        // Highlight the component when valid content is dragged over
        if (support.isDrop()) {
            JComponent component = (JComponent) support.getComponent();
            // Save original only once
            if (originalBackground == null) {
                originalBackground = component.getBackground();
            }
            component.setBackground(new Color(230, 240, 255));
        }
        return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }

    @Override
    public void exportDone(JComponent c, Transferable t, int action) {
        // Restore original background when operation completes
    //    c.setBackground(UIManager.getColor("TextArea.background"));
        c.setBackground(originalBackground);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            // Restore background if import is cancelled
            JComponent component = (JComponent) support.getComponent();
         //   component.setBackground(UIManager.getColor("TextArea.background"));
            if (originalBackground != null) {
                component.setBackground(originalBackground);
            }
            return false;
        }

        try {
            Transferable transferable = support.getTransferable();
            @SuppressWarnings("unchecked")
            List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
            
            if (!files.isEmpty()) {
                // Process files in background
                new FileProcessorWorker(files).execute();
            }
            return true;
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(textArea, 
                    "Error processing files: " + e.getMessage(),
                    "Drag and Drop Error",
                    JOptionPane.ERROR_MESSAGE);
            });
            return false;
        } finally {
            // Ensure background is restored
            JComponent component = (JComponent) support.getComponent();
           // component.setBackground(UIManager.getColor("TextArea.background"));
            if (originalBackground != null) {
                component.setBackground(originalBackground);
            }
        }
    }
}

private class FileProcessorWorker extends SwingWorker<Void, File> {
    private final List<File> files;
    
    public FileProcessorWorker(List<File> files) {
        this.files = files;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        for (File file : files) {
            if (isCancelled()) break;
            if (file.isFile()) {
                publish(file);
            }
        }
        return null;
    }
    
    @Override
    protected void process(List<File> chunks) {
        for (File file : chunks) {
            if (currentFile != null || isModified || files.size() > 1) {
                NotepadXXV1_2_0.openNewTab(file);
            } else {
                fileSizeToOpen(file);
            }
        }
    }
    
    @Override
    protected void done() {
        try {
            get(); // Check for any exceptions
        } catch (Exception e) {
            JOptionPane.showMessageDialog(textArea, 
                "Error processing files: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}


private void LinuxEnableDragAndDrop() {
	  //  textArea.setDragEnabled(true);
	  //  textArea.requestFocusInWindow(); // Ensure the text area has focus

	    textArea.setDropTarget(new DropTarget() {
	        @Override
	        public synchronized void drop(DropTargetDropEvent dtde) {
	        //    System.out.println("Drop event received");
	            dtde.acceptDrop(DnDConstants.ACTION_COPY);
	            Transferable transferable = dtde.getTransferable();
	            try {
	                if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
	                    @SuppressWarnings("unchecked")
	                    List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
	                    if (!files.isEmpty()) {
	                        handleDroppedFiles(files);
	                    }

	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Drag and Drop Failed\nMessage: " + ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	    });
	}



private void handleDroppedFiles(List<File> files) {
    SwingWorker<Void, File> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() {
            for (File file : files) {
                try {
                    if (file.isFile()) {
                        publish(file);  // Add the file to be processed in the UI thread
                    }
                } catch (Exception e) {
                 //   System.err.println("Failed to process file: " + file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void process(List<File> chunks) {
            for (File file : chunks) {
                if (currentFile != null || isModified || files.size() > 1) {
                    NotepadXXV1_2_0.openNewTab(file);
                } else {
                    fileSizeToOpen(file);
                }
            }
        }
    };
    worker.execute();
} 




// this is for undo/redo 	  to find change in text area
protected void setUndoManager(UndoManager undoManager) {
	
	if(currentFile != null &&currentFile.length() > 50*1024*1024) {
		return;
	}
	        this.undoManager = undoManager;
	        textArea.getDocument().addUndoableEditListener(e -> {
	        if(e.getEdit() != null) {
	            	 //SwingUtilities.invokeLater(() -> {
	                undoManager.addEdit(e.getEdit());  // Add the edit to the UndoManager
	             //   System.out.println("Edit added to UndoManager");
	                checkModified();
	              //  CompletableFuture.runAsync(() -> checkModified());
	            	// });
	        }
	        });
	    }


/*private void checkModified() {
    long currentHash = computeHybridHash(textArea.getText(), currentFile);

  /*  if (currentHash != originalContentHash) {  // Compare hashes instead of full text
        isModified = true;
    } else {
        isModified = false;
    }

    SwingUtilities.invokeLater(() -> {
        updateTitle(currentFile != null ? currentFile.getAbsolutePath() : getTabTitle());
        updateTabTitle();
        removeHighlights(textArea);
    });*/
    
/*    boolean modified = currentHash != originalContentHash;
	  if(modified != isModified) {
	    isModified = modified;
		  SwingUtilities.invokeLater(() -> {
		updateTitle(currentFile != null ? currentFile.getAbsolutePath() :getTabTitle());
		updateTabTitle();
		 removeHighlights(textArea);
		// MenuSelectionManager.defaultManager().clearSelectedPath();
		  });
	  }
}

// Hybrid hash function (switches based on file size)
private long computeHybridHash(String text, File file) {
	 if (file == null || file.length() <= 10 * 1024 * 1024) {  
	        return computeCRC32(text);  // Use CRC32 for small or unsaved files
	    }
	    return computeXXHash(text);  // Use xxHash for large files
}

private long computeCRC32(String text) {
	  crc.reset();
    crc.update(text.getBytes(StandardCharsets.UTF_8));
    return crc.getValue();
}

private long computeXXHash(String text) {
    
    byte[] data = text.getBytes(StandardCharsets.UTF_8);
    int seed = 0x9747b28c; // Arbitrary seed
    return factory.hash64().hash(ByteBuffer.wrap(data), seed);
}*/

private Timer modificationTimer;

private void checkModified() {
	
	if(currentFile != null && currentFile.length() > 30*(1024.0 * 1024.0) && isModified) {
	 return;
	}
	
    boolean modified = !textArea.getText().equals(originalContent);

    if (modified == isModified) {
        return; // No change, so don't update
    }

    isModified = modified;

    if (modificationTimer == null) {
        modificationTimer = new Timer(200, e -> {
            SwingUtilities.invokeLater(() -> {
                updateTitle(currentFile != null ? currentFile.getAbsolutePath() : getTabTitle());
                updateTabTitle();
                removeHighlights(textArea);
            });
        });
        modificationTimer.setRepeats(false);
    }

    modificationTimer.restart();
    
   // System.err.println("ISMODIFIED is Called " + tabTitle);
}


/**
 * this is save file method
 */

public  void saveFile() {

  SAVE save = new SAVE(this,currentFile,textArea,scrollPane,tabTitle,fileWatcher);
	save.saveFile();
}

public  void saveFileAs() {
	 SAVE save = new SAVE(this,currentFile,textArea,scrollPane,tabTitle,fileWatcher);	
	save.saveAs();
}

public void saveAll() {
	 SAVE save = new SAVE(this,currentFile,textArea,scrollPane,tabTitle,fileWatcher);
	save.saveAll();
}

public void writeFile(File file) {
	 SAVE save = new SAVE(this,file,textArea,scrollPane,tabTitle,fileWatcher);
	save.writeFile();
}



public void updateAfterSave(File savedFile,RSyntaxTextArea textArea) {
	
	 currentFile = savedFile;
	// originalContentHash = computeHybridHash(textArea.getText(),savedFile);
	 originalContent = textArea.getText();
	// originalContent.setLength(0);
	 //originalContent.append(textArea.getText());
	 isModified = false;
	// String fileExtension = getFileExtension(currentFile);
	/* syntaxHighlighter.setFileExtension(fileExtension);
	 syntaxHighlighter.setSaved(true);*/
	  ChangeLanguageMenu(currentFile.toString(),  LanguageMenuBar.getLanguageForFile(currentFile.toString()));
	 ChangeCodeAnalysisMenu(currentFile.toString(), CodeAnalysisMenuBar.getCodeAnalysisForFile(currentFile.toString()));
	 removeHighlights(textArea);
	 updateTitle(currentFile.getAbsolutePath());
	 updateTabTitle();
	 if(fileWatcher != null) {
		 Timer delayTimer = new Timer(500, e -> fileWatcher.setSaving(false));
		    delayTimer.setRepeats(false);
		    delayTimer.start();
	 }
  // getFileWatcher().setSaving(false);
//	watchFileForChanges();
	 //undoManager.discardAllEdits();
	}

	/**
	 * this is the open a file code
	 */

public  void openFile() {
	
	OPEN open = new OPEN(this,currentFile,tabTitle,isModified,textArea);
	open.openFile();
}
	
public  void fileSizeToOpen(File file) {
		  OPEN open = new OPEN(this,currentFile,tabTitle,isModified,textArea);
	open.loadFile(file);
}

public void newFile() {
	NotepadXXV1_2_0.openNewTab(null);
}
public void RenameFile() {
	OPEN open = new OPEN(this,currentFile,tabTitle,isModified,textArea);
	open.RenameFile();
}

public void updateAfterOpen(File file,RSyntaxTextArea textArea) {
    currentFile = file;
  //  originalContentHash = computeHybridHash(textArea.getText(),file);
    isModified = false;
    originalContent = textArea.getText();
   // originalContent.setLength(0);
    //originalContent.append(textArea.getText());
    String fileExtension = getFileExtension(currentFile);
 /*   syntaxHighlighter.setFileExtension(fileExtension);
    syntaxHighlighter.setSaved(true);
    setSyntaxHighlighter(syntaxHighlighter);*/
   ChangeLanguageMenu(currentFile.toString(),fileExtension);
   ChangeCodeAnalysisMenu(currentFile.toString(),fileExtension);
    updateTitle(currentFile.getAbsolutePath());
    updateTabTitle();
    watchFileForChanges();
    undoManager.discardAllEdits();
   
    if(currentFile !=null && currentFile.length()>30*(1024.0 * 1024.0)) {
        //  	 fileExtension = "txt";
          	 CodeAnalysisMenuBar.setLanguageForFile(currentFile.toString(),"txt");
           }
    if(fileWatcher != null) {
    	 Timer delayTimer = new Timer(500, e -> fileWatcher.setSaving(false));
    	    delayTimer.setRepeats(false);
    	    delayTimer.start();
   	 }
}

//this is for monitoring file changes
/*private synchronized void watchFileForChanges() {
	 if(fileWatcherThread != null && fileWatcherThread.isAlive()) {
		 fileWatcher.stopWatching();
		 fileWatcherThread.interrupt();
		  try {
	            fileWatcherThread.join(); // Wait for the thread to finish
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt(); // Restore interrupted status
	        }
	 }
	 fileWatcher = new FileWatcher(currentFile,textArea);
	 fileWatcherThread = new Thread (fileWatcher);
	 fileWatcherThread.setDaemon(true);
	 fileWatcherThread.start();
	 isModified = false;
	 updateTitle(currentFile.getAbsolutePath()); // Update title with the file path
	 updateTabTitle();
}*/

private  synchronized void watchFileForChanges() {
    stopFileWatcher();  // Stop existing watcher before starting a new one
   
    if (currentFile == null || !currentFile.exists()) {
        return;
    }
    
    fileWatcher = new FileWatcher(currentFile, textArea);
    Thread watcherThread = new Thread(fileWatcher);
    watcherThread.setDaemon(true);
    fileWatcherThread = new WeakReference<>(watcherThread);
    watcherThread.start();

    isModified = false;
    updateTitle(currentFile.getAbsolutePath());
    updateTabTitle();
}

private synchronized void stopFileWatcher() {
    if (fileWatcher != null) {
        fileWatcher.stopWatching(); // Gracefully stop the watcher
    }

    Thread watcherThread = (fileWatcherThread != null) ? fileWatcherThread.get() : null;

    if (watcherThread != null && watcherThread.isAlive()) {
        watcherThread.interrupt();
        try {
            watcherThread.join(1000); // Wait for the thread to stop
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Clear WeakReference to help with garbage collection
    if (fileWatcherThread != null) {
        fileWatcherThread.clear();
    }
}



private void ChangeLanguageMenu(String tabname, String ext) {
	LanguageMenuBar.setLanguageForFile(tabname, ext.toLowerCase());
	 LanguageMenuBar.updateLanguageMenuForActiveTab();
	 setlanguage(ext);
}

public void setlanguage(String ext) {
	 syntaxHighlighter.setFileExtension(ext);
  	    syntaxHighlighter.setSaved(true);
  	
	  //  setSyntaxHighlighter(syntaxHighlighter);
}

public void resetLanguage() {
	 String fileExtension = "txt";
	 String tabName = tabTitle;
	 if(currentFile != null ) {
	  fileExtension = getFileExtension(currentFile);
	  tabName = currentFile.toString();
	 }
	  
	 ChangeLanguageMenu(tabName,fileExtension);
	  /*  syntaxHighlighter.setFileExtension(fileExtension);
	    syntaxHighlighter.setSaved(true);*/	    
}




public void setCheckCode() {
	SwingUtilities.invokeLater(() -> { 
	SyntaxChecker.updateUI(this);
	//new CheckCode(this);
	
	// ChangeLanguageMenu(tabname,ext);
	});
	}
public void resetCheckCode() {
	 String fileExtension = "txt";
	 String Name = tabTitle;
	 if(currentFile != null ) {
	  fileExtension = getFileExtension(currentFile);
	  Name = currentFile.toString();
	 }
	 
  ChangeCodeAnalysisMenu(Name,fileExtension);	
 // new CheckCode(this);
}

private void ChangeCodeAnalysisMenu(String tabname ,String ext) {
	CodeAnalysisMenuBar.setLanguageForFile(tabname, ext.toLowerCase());
	CodeAnalysisMenuBar.updateCodeAnalysisActiveTab();
}


/*private void removeEntry(String tab) {
	LanguageMenuBar.removeLanguageFromFile(tab);
	CodeAnalysisMenuBar.removeCodeAnalysisFromFile(tab);
}*/

/*private void ClearMenuComponentFiles() {
	// TODO Auto-generated method stub
	LanguageMenuBar.clearConfigLanguageFile();
	CodeAnalysisMenuBar.clearConfigCodeAnalysisFile();
}*/


/**
 * this is to find if javaFX available in the user system
 * this ensure that the app work in all platform
 * @return 
 */

public  boolean isJavaFXAvailable() {
    try {
        // Check if JavaFX FileChooser class exists
        //Class.forName("javafx.stage.FileChooser");
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




/**
 * shortcut keys method
 */

//to open file explorer
public void Explorer() {

     if (os.contains("win")) {
         WindowsUtils.WindowsExplorer(currentFile);
     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
         LinuxUtils.LinuxExplorer(currentFile);
     } else {
         JOptionPane.showMessageDialog(
             null,
             "Unsupported operating system.",
             "Error",
             JOptionPane.ERROR_MESSAGE
         );
     }
}

//to open cmd
public void Cmd() {

     if (os.contains("win")) {
         WindowsUtils.WindowsCmd(currentFile);
     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
         LinuxUtils.LinuxTerminal(currentFile);
     } else {
         JOptionPane.showMessageDialog(
             null,
             "Unsupported operating system.",
             "Error",
             JOptionPane.ERROR_MESSAGE
         );
     }
}
//this is to open root cmd
public void adminCmd() {

    if (os.contains("win")) {
        WindowsUtils.WindowsAdminCmd(currentFile);
    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
        LinuxUtils.LinuxRootTerminal(currentFile);
    } else {
        JOptionPane.showMessageDialog(
            null,
            "Unsupported operating system.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}


//cut
public void cutText() {
    if (textArea != null) {
        String selectedText = textArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            // Instead of calling textArea.cut(), handle the cut operation directly
            StringSelection stringSelection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            // Remove the selected text from the text area
            try {
                int start = textArea.getSelectionStart();
                int end = textArea.getSelectionEnd();
                textArea.getDocument().remove(start, end - start);
            } catch (BadLocationException e) {
               // e.printStackTrace(); // Handle potential issues
            	 JOptionPane.showMessageDialog(null, "An Error occur "+e.getMessage(),"Cut Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Optional: Notify user if no text is selected
         //   System.out.println("No text selected to cut.");
        }
    } else {
       // System.out.println("textArea is not initialized");
    }
}



//copy
public void copyText() {
	 if (textArea.getSelectedText() != null) {
	        // Store the selected text to the clipboard
	        String selectedText = textArea.getSelectedText();
	        StringSelection selection = new StringSelection(selectedText);
	        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        clipboard.setContents(selection, selection);
	    } else {
	        // Optional: You can add a message if nothing is selected
	        // JOptionPane.showMessageDialog(this, "No text selected to copy.");
	    }

}
//paste
public void pasteText() {
    try {
        // Check if the textArea is not null
        if (textArea != null) {
            // Get the current caret position
           // int caretPosition = textArea.getCaretPosition();
            // Get the clipboard content
            String clipboardContent = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
                    .getData(DataFlavor.stringFlavor);

            // Check if clipboard content is not null or empty
            if (clipboardContent != null && !clipboardContent.isEmpty()) {
            	  int start = textArea.getSelectionStart();
                  int end = textArea.getSelectionEnd();
                // Insert the clipboard content at the current caret position
                Document doc = textArea.getDocument();
              //  doc.insertString(caretPosition, clipboardContent, null);
                if (start != end) {
                    // Replace the selected text with clipboard content
                    doc.remove(start, end - start);
                    doc.insertString(start, clipboardContent, null);
                } else {
                    // Insert at the caret position if no text is selected
                    int caretPosition = textArea.getCaretPosition();
                    doc.insertString(caretPosition, clipboardContent, null);
                }

            // Move the caret to the end of the pasted content
                textArea.setCaretPosition(start + clipboardContent.length());
        }
    }
    }catch (Exception e) {
     //   e.printStackTrace(); // Handle any exceptions
    	 //JOptionPane.showMessageDialog(null, "Error while pasting text","Paste Error",JOptionPane.ERROR_MESSAGE);
    }
}



//undo
/*public synchronized void undoText() {
	 if (undoManager.canUndo()) {
		// System.out.println("Undoing...");
         undoManager.undo();
        checkModified();
     }else {
        // System.out.println("No actions to undo");
     }
}
//redo
public synchronized void redoText() {
	if (undoManager.canRedo()) {
        undoManager.redo();
        checkModified();
    }else {
       // System.out.println("No actions to Redo");
    }
}*/

public synchronized void undoText() {
	 int caretPosition = textArea.getCaretPosition();
	 StringBuilder Text = new StringBuilder(textArea.getText());
    if (undoManager.canUndo()) {
        undoManager.undo();
        String newText = getTextArea().getText();
        //int caretPosition = textArea.getCaretPosition();
        if (newText != null && !newText.isEmpty()) {
            checkModified();
        } else {
            if(undoManager.canUndo()){
            //	int caretPosition = textArea.getCaretPosition();
        	   undoManager.undo();
        	 //  textArea.setCaretPosition(caretPosition);
        	 //  System.out.println("Undo Done Twice skipped: Text area would become empty.");
        	  // textArea.setCaretPosition(0);
        	   textArea.setCaretPosition(Math.min(caretPosition, Text.length()));
        
            }else {
            	// int caretPosition = textArea.getCaretPosition();
            	   undoManager.redo();
            	//   textArea.setCaretPosition(caretPosition);
            	// System.out.println("Redo Done in else Undo ");
            	   textArea.setCaretPosition(Math.min(caretPosition, Text.length()));
            	  
            }
        	   checkModified();
        	   

        }
    } else {
        // No actions to undo
       // System.out.println("No actions to undo.");
    }
    //Text.setLength(0);
}

public synchronized void redoText() {
	 int caretPosition = textArea.getCaretPosition();
	 StringBuilder Text = new StringBuilder(textArea.getText());
    if (undoManager.canRedo()) {
        undoManager.redo();
        String newText = textArea.getText();
       // int caretPosition = textArea.getCaretPosition();
        if (newText != null && !newText.isEmpty()) {
            checkModified();
        } else {
             if(undoManager.canRedo()){
             //	 int caretPosition = textArea.getCaretPosition();
             	   undoManager.redo();
             	 //  textArea.setCaretPosition(caretPosition);
             	// System.out.println("Redo Done in else Undo ");
             	  textArea.setCaretPosition(Math.min(caretPosition, Text.length()));
        
             }else {
             	//int caretPosition = textArea.getCaretPosition();
         	   undoManager.undo();
         	  // textArea.setCaretPosition(caretPosition);
         	 //  System.out.println("Undo Done Twice skipped: Text area would become empty.");
         	  textArea.setCaretPosition(Math.min(caretPosition, Text.length()));
         
             }
         	   checkModified();
        }
    } else {
        // No actions to redo
       // System.out.println("No actions to redo.");
    }
   // Text.setLength(0);
}


public synchronized void findText() {
    String searchText = JOptionPane.showInputDialog("Find: ");
    if (searchText != null && !searchText.isEmpty()) {
        // Run the find operation on a background thread
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                removeHighlights(textArea); // Remove previous highlights
                highlightText(textArea, searchText); // Highlight all occurrences
                return null;
            }

            @Override
            protected void done() {
                try {
                    get(); // Check for exceptions
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error while Finding: " + e.getMessage(), "Find Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
}


// Method to highlight all occurrences of the search term
private void highlightText(JTextComponent textComp, String pattern) {
	    // Run the highlighting logic in a background thread
	    new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() {
	            try {
	                Highlighter highlighter = textComp.getHighlighter();
	                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
	                String text = textComp.getText();
	                int pos = 0;
	                boolean found = false;

	                // Loop to find and highlight all matches
	                while ((pos = text.toLowerCase().indexOf(pattern.toLowerCase(), pos)) >= 0) {
	                    final int start = pos;
	                    final int end = pos + pattern.length();

	                    // Use invokeLater to highlight on the EDT
	                    SwingUtilities.invokeLater(() -> {
	                        try {
	                            highlighter.addHighlight(start, end, painter);
	                        } catch (BadLocationException e) {
	                            e.printStackTrace();
	                        }
	                    });

	                    pos += pattern.length(); // Move to the next position
	                    found = true;
	                }

	                // Show a message if no occurrences are found
	                if (!found) {
	                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Text not found"));
	                }

	            } catch (Exception e) {
	                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null,
	                        "Error while Finding: " + e.getMessage(),
	                        "Find Error", JOptionPane.ERROR_MESSAGE));
	            }
	            return null;
	        }
	    }.execute();
	}

	// Method to remove all highlights
	protected void removeHighlights(JTextComponent textComp) {
	    SwingUtilities.invokeLater(() -> {
	    	if(textComp == null) return;
	        try {
	            Highlighter highlighter = textComp.getHighlighter();
	            Highlighter.Highlight[] highlights = highlighter.getHighlights();

	            for (Highlighter.Highlight highlight : highlights) {
	                if (highlight.getPainter() instanceof DefaultHighlighter.DefaultHighlightPainter) {
	                    highlighter.removeHighlight(highlight);
	                }
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null,
	                    "Error while removing highlights: " + e.getMessage(),
	                    "Highlight Removal Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	}

//replace text
public synchronized void replaceText() {
    // Prompt the user to input the text to find
    String searchText = JOptionPane.showInputDialog("Find and Replace:");
    if (searchText == null || searchText.isEmpty()) {
        return; // Exit if no text is provided
    }

    // Highlight all occurrences of the search text
    removeHighlights(textArea);
    highlightText(textArea, searchText);

    // Prompt the user to input the replacement text on the EDT
    SwingUtilities.invokeLater(() -> {
        String replaceText = JOptionPane.showInputDialog("Replace with:");
        if (replaceText == null) {
            return; // Exit if the user cancels the replacement prompt
        }

        // Perform the replacement on a background thread to avoid freezing
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    // Get the text from the text area
                    String text = textArea.getText();
                    StringBuilder updatedText = new StringBuilder(text.length());

                    int lastPos = 0;
                    int pos;
                    boolean found = false;

                    // Perform a case-insensitive search and replacement
                    while ((pos = text.toLowerCase().indexOf(searchText.toLowerCase(), lastPos)) >= 0) {
                        found = true;
                        updatedText.append(text, lastPos, pos); // Append text before the match
                        updatedText.append(replaceText); // Append the replacement text
                        lastPos = pos + searchText.length(); // Move past the current match
                    }

                    // Append the remaining text after the last match
                    updatedText.append(text.substring(lastPos));

                    if (!found) {
                        SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(null,
                                    "Text not found.",
                                    "Replace Error",
                                    JOptionPane.WARNING_MESSAGE)
                        );
                        return null;
                    }

                    // Update the text area on the EDT with the replaced text
                    SwingUtilities.invokeLater(() -> {
                        textArea.setText(updatedText.toString());
                        JOptionPane.showMessageDialog(null,
                                "All occurrences of \"" + searchText + "\" replaced with \"" + replaceText + "\".",
                                "Replace Completed",
                                JOptionPane.INFORMATION_MESSAGE);
                    });

                } catch (Exception e) {
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null,
                                "Error while replacing: " + e.getMessage(),
                                "Replace Text Error",
                                JOptionPane.ERROR_MESSAGE)
                    );
                }
                return null;
            }
        }.execute();
    });
}


// print text code 
public void printText() {
	  new Thread(() -> {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(new Printable() {
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            Font originalFont = textArea.getFont();
            Font printFont = new Font(originalFont.getName(), originalFont.getStyle(), 12);
            g2d.setFont(printFont);

            RSyntaxTextArea rTextArea = (RSyntaxTextArea) textArea;
            FontMetrics fm = g2d.getFontMetrics();
            int lineHeight = fm.getHeight();
            int pageWidth = (int) pageFormat.getImageableWidth();
            int maxLinesPerPage = (int) (pageFormat.getImageableHeight() / lineHeight);

            List<WrappedTokenLine> wrappedLines = new ArrayList<>();

            try {
                for (int i = 0; i < rTextArea.getLineCount(); i++) {
                    int startOffset = rTextArea.getLineStartOffset(i);
                    int endOffset = rTextArea.getLineEndOffset(i);
                    String line = rTextArea.getText(startOffset, endOffset - startOffset);

                    List<WrappedTokenLine> wrappedSubLines = wrapText(line, fm, pageWidth, rTextArea, i);
                    wrappedLines.addAll(wrappedSubLines);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error reading text for printing: " + e.getMessage(),
                        "Print Error", JOptionPane.ERROR_MESSAGE);
                return Printable.NO_SUCH_PAGE;
            }

            int totalPages = (int) Math.ceil((double) wrappedLines.size() / maxLinesPerPage);
            if (pageIndex >= totalPages) return Printable.NO_SUCH_PAGE;

            int y = 20;
            int startLine = pageIndex * maxLinesPerPage;
            int endLine = Math.min((pageIndex + 1) * maxLinesPerPage, wrappedLines.size());

            for (int i = startLine; i < endLine; i++) {
                WrappedTokenLine tokenLine = wrappedLines.get(i);
                int x = 10;
                for (TokenSegment segment : tokenLine.segments) {
                    g2d.setPaint(segment.color);
                    g2d.drawString(segment.text, x, y);
                    x += fm.stringWidth(segment.text);
                }
                y += lineHeight;
            }

            return Printable.PAGE_EXISTS;
        }
    });

    if (job.printDialog()) {
        try {
            job.print();
        } catch (PrinterException e) {
        	SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, 
                    "Error while Printing: " + e.getMessage(),
                    "Print Error", JOptionPane.ERROR_MESSAGE));
        }
    }
	  }).start();
}

private static class TokenSegment {
    String text;
    Color color;

    TokenSegment(String text, Color color) {
        this.text = text;
        this.color = color;
    }
}

private static class WrappedTokenLine {
    List<TokenSegment> segments = new ArrayList<>();
}

private List<WrappedTokenLine> wrapText(String text, FontMetrics fm, int pageWidth, RSyntaxTextArea textArea, int lineIndex) {
    List<WrappedTokenLine> wrappedLines = new ArrayList<>();
    WrappedTokenLine currentLine = new WrappedTokenLine();
    int currentWidth = 0;

    try {
        Token token = textArea.getTokenListForLine(lineIndex);
        while (token != null && token.getType() != Token.NULL) {
            String tokenText = token.getLexeme();
            if (tokenText == null) tokenText = "";

            Color tokenColor = getTokenColor(textArea, token);
            if (tokenColor == null) tokenColor = Color.BLACK; // Default to black if no syntax color

            String[] words = tokenText.split("(?=\\n)|(?<=\\n)|(?=\\s)|(?<=\\s)");
            for (String word : words) {
                int wordWidth = fm.stringWidth(word);

                if (word.equals("\n") || currentWidth + wordWidth > pageWidth) {
                    wrappedLines.add(currentLine);
                    currentLine = new WrappedTokenLine();
                    currentWidth = 0;
                }

                if (!word.equals("\n")) {
                    currentLine.segments.add(new TokenSegment(word, tokenColor));
                    currentWidth += wordWidth;
                }
            }

            token = token.getNextToken();
        }

        if (!currentLine.segments.isEmpty()) {
            wrappedLines.add(currentLine);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return wrappedLines;
}

private Color getTokenColor(RSyntaxTextArea textArea, Token token) {
    SyntaxScheme scheme = textArea.getSyntaxScheme();
    if (token.getType() < 0 || token.getType() >= scheme.getStyles().length) {
        return Color.BLACK;
    }
    return (scheme.getStyle(token.getType()) != null)
           ? scheme.getStyle(token.getType()).foreground
           : Color.BLACK;
}





/*public void printText() {
    try {
        // Store original foreground color
        Color originalColor = textArea.getForeground();
        Font originalFont = textArea.getFont();
        textArea.setFont(new Font(originalFont.getName(), originalFont.getStyle(), 12));
        // Temporarily set text color to black
        textArea.setForeground(Color.BLACK);

        // Print the text area
        boolean printSuccess = textArea.print(null, null, true, null, null, true);

        // Restore original foreground color
        textArea.setForeground(originalColor);
        textArea.setFont(originalFont);
        if (!printSuccess) {
            JOptionPane.showMessageDialog(null, "Printing canceled");
        }
    } catch (PrinterException e) {
        JOptionPane.showMessageDialog(null, "Error while Printing.... " + e.getMessage(),
                "Print Error", JOptionPane.ERROR_MESSAGE);
    }
}
public void printText() {
    try {
        // Create a temporary copy of the text area
        JTextArea tempTextArea = new JTextArea();
        // Copy content and settings from the original
        tempTextArea.setText(textArea.getText());
        tempTextArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getStyle(), 12)); // Set font size for printing
        tempTextArea.setForeground(Color.BLACK); // Force text color to black for printing
        tempTextArea.setLineWrap(textArea.getLineWrap());
        tempTextArea.setWrapStyleWord(textArea.getWrapStyleWord());
        tempTextArea.setTabSize(textArea.getTabSize());
        // (Copy other relevant properties if needed)

        // Print the temporary text area (no UI changes)
        boolean printSuccess = tempTextArea.print(null, null, true, null, null, true);

        if (!printSuccess) {
            JOptionPane.showMessageDialog(null, "Printing canceled");
        }
    } catch (PrinterException e) {
        JOptionPane.showMessageDialog(null, "Error while Printing: " + e.getMessage(),
                "Print Error", JOptionPane.ERROR_MESSAGE);
    }
}
*/

// font size
public void chooseFont() {
    // Show a font chooser dialog
	 String[] availableFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	    JComboBox<String> fontList = new JComboBox<>(availableFonts);
	    fontList.setSelectedItem(textArea.getFont().getFontName());

	    JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(textArea.getFont().getSize(), 8, 72, 1));

	    JPanel panel = new JPanel(new BorderLayout());
	    panel.add(new JLabel("Select Font:"), BorderLayout.NORTH);
	    panel.add(fontList, BorderLayout.CENTER);
	    panel.add(sizeSpinner, BorderLayout.SOUTH);

	    int result = JOptionPane.showConfirmDialog(this, panel, "Choose Font", JOptionPane.OK_CANCEL_OPTION);

	    if (result == JOptionPane.OK_OPTION) {
	        String selectedFont = (String) fontList.getSelectedItem();
	        int selectedSize = (int) sizeSpinner.getValue();
	        Font newFont = new Font(selectedFont, Font.PLAIN, selectedSize);

	        textArea.setFont(newFont);
	     //   lineNumbers.updateFont(newFont);

	      //  System.out.println("Font changed to: " + newFont.getFontName() + " Size: " + selectedSize);
	    }
}
public void setFontSize(int increment) {
    int currentSize = textArea.getFont().getSize();
    int newSize = currentSize + increment;

    // Check if the new size is within the defined limits
    if (newSize >= MIN_FONT_SIZE && newSize <= MAX_FONT_SIZE) {
        Font newFont = new Font(textArea.getFont().getName(), textArea.getFont().getStyle(), newSize);
        textArea.setFont(newFont);
      //  lineNumbers.updateFont(newFont);
     //   System.out.println("Font size changed to: " + newSize);
    }

}
public void resetFontSize() {
    textArea.setFont(defaultFont);
} 



// this is for  title to update
public synchronized void updateTitle(String title) {
	 SwingUtilities.invokeLater(() -> {
	//this.currentFile = new File(title);
		  JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		  if(parentFrame != null) {
			  String modifiedFlag = isModified ? " *": "";
			  String fullTitle = title + modifiedFlag + " - NotepadXX";
			  parentFrame.setTitle(fullTitle);
		  }
	 });
}




		//Report for bug
   public void reportBug() {
			 
	     if (os.contains("win")) {
	         WindowsUtils.WindowsReportBug();
	     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	         LinuxUtils.LinuxReportBug();
	     } else {
	         JOptionPane.showMessageDialog(
	             null,
	             "Unsupported operating system.",
	             "Error",
	             JOptionPane.ERROR_MESSAGE
	         );
	     }
		    }


		 public void Donate() {
		     if (os.contains("win")) {
		         WindowsUtils.WindowsDonate();
		     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
		         LinuxUtils.LinuxDonate();
		     } else {
		         JOptionPane.showMessageDialog(
		             null,
		             "Unsupported operating system.",
		             "Error",
		             JOptionPane.ERROR_MESSAGE
		         );
		     }
		    }


		 public void Discord() {
		     if (os.contains("win")) {
		         WindowsUtils.WindowsDiscord();
		     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
		         LinuxUtils.LinuxDiscord();
		     } else {
		         JOptionPane.showMessageDialog(
		             null,
		             "Unsupported operating system.",
		             "Error",
		             JOptionPane.ERROR_MESSAGE
		         );
		     }
		    }



			/*
			 * this is to open any file in browser in edge
			 */
			 public void edgeBrowser() {
				 if (os.contains("win")) {
			        WindowsUtils.WindowsEdgeBrowser(currentFile);
				 }
			    }

			 /*
				 * this is to open any file in browser in chrome
				 */
			 public void chromeBrowser() {
			     if (os.contains("win")) {
			         WindowsUtils.WindowsChromeBrowser(currentFile);
			     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
			         LinuxUtils.LinuxGoogleChrome(currentFile);
			     } else {
			         JOptionPane.showMessageDialog(
			             null,
			             "Unsupported operating system.",
			             "Error",
			             JOptionPane.ERROR_MESSAGE
			         );
			     }
			    }

			 public void FireFox() {
			     if (os.contains("win")) {
			         WindowsUtils.WindowsFirefoxBrowser(currentFile);
			     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
			         LinuxUtils.LinuxFireFox(currentFile);
			     } else {
			         JOptionPane.showMessageDialog(
			             null,
			             "Unsupported operating system.",
			             "Error",
			             JOptionPane.ERROR_MESSAGE
			         );
			     }
			 }
			 
		public void TorBrowser() {
			if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
			LinuxUtils.LinuxTorBrowser(currentFile);
			}
		}




/*
 * this is for generate the tab title
 *   and updating the tab title
 */
		   private synchronized String generateTabTitle(String baseTitle) {
		       // Add * if modified, otherwise just the base title
		       String modifiedFlag = isModified ? " *" : " ";
		       return baseTitle + modifiedFlag;
		   }

		   public synchronized void updateTabTitle() {
			    int tabIndex = NotepadXXV1_2_0.getTabbedPane().indexOfComponent(this);
			    if (tabIndex < 0) return;

			    // Determine tab title and icon
			    tabTitle = (currentFile != null) ? currentFile.getName() : getTabTitle();
			    String title = generateTabTitle(tabTitle);
			    
			    Icon tabIcon = GetImage.getImage(currentFile);
			    // Get or create tab panel
			    JPanel tabPanel = (JPanel) NotepadXXV1_2_0.getTabbedPane().getTabComponentAt(tabIndex);
			    if (tabPanel == null) {
			        tabPanel = createTabPanel();
			        NotepadXXV1_2_0.getTabbedPane().setTabComponentAt(tabIndex, tabPanel);
			    }

			    // Update title components
			    JLabel titleLabel = null;
			    final JButton[] closeButtonHolder = new JButton[1]; // Holder for effectively final access
			    
			    // Find existing components
			    for (Component comp : tabPanel.getComponents()) {
			        if (comp instanceof JLabel) {
			            titleLabel = (JLabel) comp;
			        } else if (comp instanceof JButton) {
			            closeButtonHolder[0] = (JButton) comp;
			        }
			    }

			    // Create title label if missing
			    if (titleLabel == null) {
			        titleLabel = new JLabel();
			        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			        tabPanel.add(titleLabel, BorderLayout.CENTER);
			    }

			    // Update title label
			    titleLabel.setText(title);
			    titleLabel.setIcon(tabIcon);
			 //   titleLabel.setForeground(isModified ? Color.RED : UIManager.getColor("Label.foreground"));

			    JButton closeButton = new JButton("x");
			       // SwingUtilities.invokeLater(() -> {
			        closeButton.setFont(new Font("Arial", Font.BOLD, 12));
			        closeButton.setMargin(new Insets(0, 5, 0, 5));
			        closeButton.setBorder(null);
			        closeButton.setFocusPainted(false);
			    
			    // Create close button if missing
			    if (closeButtonHolder[0] == null) {
			        closeButtonHolder[0] = new JButton("×");
			        closeButtonHolder[0].setFont(new Font("Arial", Font.PLAIN, 12));
			        closeButtonHolder[0].setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
			        closeButtonHolder[0].setContentAreaFilled(false);
			        closeButtonHolder[0].setFocusPainted(false);
			        tabPanel.add(closeButtonHolder[0], BorderLayout.EAST);
			        
			        // Hover effects using holder array
			        closeButtonHolder[0].addMouseListener(new MouseAdapter() {
			            public void mouseEntered(MouseEvent e) { 
			                closeButtonHolder[0].setForeground(Color.RED); 
			            }
			            public void mouseExited(MouseEvent e) { 
			                closeButtonHolder[0].setForeground(null); 
			            }
			        });
			    }

			    // Update close button listener
			    for (ActionListener listener : closeButtonHolder[0].getActionListeners()) {
			        closeButtonHolder[0].removeActionListener(listener);
			    }
			    closeButtonHolder[0].addActionListener(e -> CLOSE.closeTab(this));

			    // Update tab properties
			    NotepadXXV1_2_0.getTabbedPane().setTitleAt(tabIndex, title);
			    NotepadXXV1_2_0.getTabbedPane().setIconAt(tabIndex, tabIcon);
			//    notepad.getTabbedPane().setToolTipTextAt(tabIndex, tooltip);

			    WindowMenu.updateWindowMenu();
			    SwingUtilities.updateComponentTreeUI(tabPanel);
			}

			private JPanel createTabPanel() {
			    JPanel panel = new JPanel(new BorderLayout());
			    panel.setOpaque(false);
			    panel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
			    return panel;
			}

	/*	public synchronized void updateTabTitle() {
			   //SwingUtilities.invokeLater(() -> {
			    int tabIndex = notepad.getTabbedPane().indexOfComponent(this);

			    // Check if tabIndex is valid
			    if (tabIndex < 0) {
			        // Log an error or handle the case where the tab does not exist
			     //   System.err.println("Tab index not found: " + tabIndex);
			        return; // Early exit
			    }

			    // Define the title, setting it uniquely per tab
		        tabTitle = (currentFile != null) ? currentFile.getName() : getTabTitle();
		        String title = generateTabTitle(tabTitle);

			    // Get the existing tab component for this index
			    JPanel tabPanel = (JPanel)notepad.getTabbedPane().getTabComponentAt(tabIndex);

			    if (tabPanel == null) {
			        // Create a new tabPanel if none exists
			        tabPanel = new JPanel(new BorderLayout());
			        tabPanel.setOpaque(false);
			       notepad.getTabbedPane().setTabComponentAt(tabIndex, tabPanel); // Set the new tab panel
			    }

			    // Create or update the title label
			    JLabel titleLabel;
			    if (tabPanel.getComponentCount() > 0&& tabPanel.getComponent(0) instanceof JLabel) {
			        titleLabel = (JLabel) tabPanel.getComponent(0); // Retrieve existing label
			      //  titleLabel.setText(title); // Update title text
			    } else {
			        titleLabel = new JLabel(); // Create new label if none exists
			        tabPanel.add(titleLabel, BorderLayout.WEST); // Add to tab panel
			    }

			    titleLabel.setText(title);
			  //  titleLabel.setForeground(isModified ? Color.RED : getDefaultTitleColor());


			    // Create or update the close button
			    JButton closeButton;
			    if (tabPanel.getComponentCount() > 1 && tabPanel.getComponent(1) instanceof JButton) {
			        closeButton = (JButton) tabPanel.getComponent(1); // Retrieve existing close button
			       // closeButton.setMargin(new Insets(0, 5, 0, 5));
			    } else {

			        closeButton = new JButton("x"); // Create new close button
			        closeButton.setFont(new Font("Arial", Font.BOLD, 12));
			        closeButton.setMargin(new Insets(0, 5, 0, 5));
			        closeButton.setBorder(null);
			        closeButton.setFocusPainted(false);

			        tabPanel.add(closeButton, BorderLayout.EAST); // Add close button to panel
			    }
			    // Remove all previous listeners to prevent multiple executions
			    for (ActionListener listener : closeButton.getActionListeners()) {
			        closeButton.removeActionListener(listener);
			    }

			    // Action listener for the close button
			    closeButton.addActionListener(e -> closeTab());
			   notepad.getTabbedPane().setTabComponentAt(tabIndex, tabPanel);
			   notepad.getTabbedPane().setTitleAt(tabIndex, tabTitle);
			   //});
			   
			   WindowMenu.updateWindowMenu();
			
			    SwingUtilities.updateComponentTreeUI(closeButton);
			  
		        // Optionally, you can force the JScrollPane to update after adding the new tab:
		       // notepad.getFrame().repaint();
			}*/

			
		/*	public  void closeTab() {
			    Component selectedComp = NotepadXXV1_2_0.getTabbedPane().getSelectedComponent();

			    if (selectedComp == null) return;

			    if (selectedComp instanceof Texteditor) {
			        ((Texteditor) selectedComp).closeTab1();
			    } else {
			        NotepadXXV1_2_0.getTabbedPane().remove(selectedComp);
			        NotepadXXV1_2_0.checkAndOpenDefaultTab();
			    }
			}

		
			 public void closeTab1() {
			 	 SwingUtilities.invokeLater(() -> {
			 		 
			 		  Component selectedComp = NotepadXXV1_2_0.getTabbedPane().getSelectedComponent();

			 	        // If the selected component is not a Texteditor, just close it
			 	        if (!(selectedComp instanceof Texteditor)) {
			 	            NotepadXXV1_2_0.getTabbedPane().remove(selectedComp);
			 	            NotepadXXV1_2_0.checkAndOpenDefaultTab();
			 	            return;
			 	        }

			 		 
			 		 if (!isModified || textArea.getText().isEmpty()) {
			    	  updateForClosebtn(this);
			    	  
			 		      //  closeButton();
			 		    } else {
			 		        // Ask the user if they want to save unsaved changes
			 		        //int confirm = JOptionPane.showConfirmDialog(this, "You have unsaved changes. \nDo you want to save them?", "Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION);

			 		    int confirm = JOptionPane.showConfirmDialog(this,"Save file '"+tabTitle+"' ?","Save",JOptionPane.YES_NO_CANCEL_OPTION);
			 		    	
			 		        if (confirm == JOptionPane.YES_OPTION) {
			 		            // If user chooses to save the file
			 		        	saveFile();
			 		        	if(!isModified) {
			 		        		 updateForClosebtn(this);
			 		        	}
			 		        } else if (confirm == JOptionPane.NO_OPTION) {
			 		        	 updateForClosebtn(this);
			 		        } else {
			 		            // If user chooses cancel, do nothing (stay in the application)
			 		            return;
			 		        }
			 		    }
			   //   notepad.saveClosedTab(this);
			    NotepadXXV1_2_0.checkAndOpenDefaultTab();
			      //notepad.saveOpenedFiles();
			    
			 	 });
			 		}
			 
			 public void closeAllTab() {
				    SwingWorker<Void, Texteditor> worker = new SwingWorker<>() {
				        private final List<Texteditor> tabsToClose = new ArrayList<>();

				        @Override
				        protected Void doInBackground() {
				            int tabCount = NotepadXXV1_2_0.getTabbedPane().getTabCount();

				            for (int i = tabCount - 1; i >= 0; i--) {
				                Texteditor tab = (Texteditor) NotepadXXV1_2_0.getTabbedPane().getComponentAt(i);

				                if (tab.isModified && !tab.textArea.getText().isEmpty()) {
				                    int confirm = JOptionPane.showConfirmDialog(
				                            NotepadXXV1_2_0.getFrame(),
				                            "Save file '" + tab.tabTitle + "'?",
				                            "Save",
				                            JOptionPane.YES_NO_CANCEL_OPTION
				                    );

				                    if (confirm == JOptionPane.YES_OPTION) {
				                        tab.saveFile();
				                        if (!tab.isModified) {
				                            tabsToClose.add(tab);
				                        }
				                    } else if (confirm == JOptionPane.NO_OPTION) {
				                        tabsToClose.add(tab);
				                    } else {
				                        return null; // Stop process if canceled
				                    }
				                } else {
				                    tabsToClose.add(tab);
				                }
				            }
				            return null;
				        }

				        @Override
				        protected void done() {
				            SwingUtilities.invokeLater(() -> {
				                for (Texteditor tab : tabsToClose) {
				                    updateForClosebtn(tab);
				                }
				                NotepadXXV1_2_0.checkAndOpenDefaultTab();
				            });
				        }
				    };

				    worker.execute();
				}
			
				public void updateForClosebtn(Texteditor tab) {
				    if (tab == null) return;

				    File closedFile = tab.currentFile;
				    String fileText = tab.textArea != null ? tab.textArea.getText() : "";
				    NotepadXXV1_2_0.saveClosedTab(closedFile, fileText);
				  
				
				    dispose(tab);
				   try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				    
				 //   SwingUtilities.invokeLater(() -> {
				        // Remove the tab from the JTabbedPane
				        int index = NotepadXXV1_2_0.getTabbedPane().indexOfComponent(tab);
				        try {
				        if (index != -1) {
				            NotepadXXV1_2_0.getTabbedPane().remove(index);
				        }
				        }catch(Exception e ) {}
				        finally {
				        	 NotepadXXV1_2_0.checkAndOpenDefaultTab();
				        }
				   // notepad.getTabbedPane().remove(tab);

				        // Check if a default tab needs to be opened
				      //  NotepadXXV1_2_0.checkAndOpenDefaultTab();
				 //   });
				
				}

				private void dispose(Texteditor tab) {
					if(tab!=null) {
				    tab.textArea.removeMouseListener(getMouseListener());
				    tab.textArea.getDocument().removeDocumentListener(getDocumentListener());
				    tab.undoManager.discardAllEdits(); // Clears undo history
				    tab.checkcode.removeCheckCodeDoc();
				    tab.syntaxHighlighter.removeSyntaxHightlighterDoc();
				    tab.syntaxHighlighter = null; // Dereference highlighter
				 //   backgroundTimer.stop(); // Stop any active timers (if uncommented)
					}
					}*/
			
		/*	 private void exit1() {
				 
				    SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
				        @Override
				        protected Boolean doInBackground() {
				            // Check if there are unsaved changes in any tabs
				            boolean hasUnsavedChanges = false;
				            for (int i = 0; i < notepad.getTabbedPane().getTabCount(); i++) {
				          
				            	Component comp = notepad.getTabbedPane().getComponentAt(i);

				                if (comp instanceof Texteditor) {
				                    Texteditor editor = (Texteditor) comp;
				                    if (editor.isModified) {
				                        hasUnsavedChanges = true;
				                    }
				                } else {
				                    // Remove the tab if it is not a Texteditor
				                    notepad.getTabbedPane().removeTabAt(i);
				                }
				            }
				            return hasUnsavedChanges;
				        }

				        @Override
				        protected void done() {
				            try {
				                boolean hasUnsavedChanges = get();

				                if (hasUnsavedChanges) {
				                    int option = JOptionPane.showConfirmDialog(
				                            NotepadXXV1_2_0.getFrame(),
				                            "Save changes before closing?",
				                            "Unsaved Changes",
				                            JOptionPane.YES_NO_CANCEL_OPTION,
				                            JOptionPane.WARNING_MESSAGE
				                    );

				                    if (option == JOptionPane.YES_OPTION) {
				                        SwingWorker<Boolean, Void> saveWorker = new SwingWorker<>() {
				                            @Override
				                            protected Boolean doInBackground() {
				                                return saveAllBeforeExit(); // Save all files
				                            }

				                            @Override
				                            protected void done() {
				                                try {
				                                    if (get()) {
				                                        updateForExit();
				                                    } else {
				                                        JOptionPane.showMessageDialog(
				                                                NotepadXXV1_2_0.getFrame(),
				                                                "Some files were not saved.",
				                                                "Warning",
				                                                JOptionPane.WARNING_MESSAGE
				                                        );
				                                    }
				                                } catch (Exception e) {
				                                    e.printStackTrace();
				                                }
				                            }
				                        };
				                        saveWorker.execute();
				                    } else if (option == JOptionPane.NO_OPTION) {
				                        updateForExit();
				                    }
				                } else {
				                    updateForExit();
				                }
				            } catch (Exception e) {
				                e.printStackTrace();
				            }
				        }
				    };

				     worker.execute();
				}

				private void updateForExit() {
					if(markdown1 != null) {
	                	markdown1.dispose();
	                	}
					if(markdown2 != null) {
				    	markdown2.dispose();
				    	}
				    NotepadXXV1_2_0.clearAllRecentClosedTabs();
				 //   ClearMenuComponentFiles();
				    NotepadXXV1_2_0.getFrame().dispose();
				    System.exit(0);
				}

				public void exit() {
				    NotepadXXV1_2_0.saveOpenedFiles();
				    NotepadXXV1_2_0.saveWindowSize(NotepadXXV1_2_0.getFrame().getSize());
				    exit1();
				}
*/
			
			/**
			 * Comprehensive resource cleanup method for TextEditor
			 * Combines all cleanup operations into one synchronized method
			 */
			public synchronized void cleanup() {
			    // Stop file watcher thread first
			    stopFileWatcher();
			    
			    // Clean up text area and its listeners
			    if (textArea != null) {
			        // Remove key listeners
			        for (KeyListener listener : textArea.getKeyListeners()) {
			            textArea.removeKeyListener(listener);
			        }
			        
			        // Remove mouse listeners
			        for (MouseListener listener : textArea.getMouseListeners()) {
			            textArea.removeMouseListener(listener);
			        }
			        
			        // Remove caret listeners
			        for (CaretListener listener : textArea.getCaretListeners()) {
			            textArea.removeCaretListener(listener);
			        }
			        
			     // Remove document listeners - safe implementation
			        if (textArea.getDocument() != null) {
			            Document doc = textArea.getDocument();
			            if (doc instanceof AbstractDocument) {
			                // Preferred method for RSyntaxTextArea's document
			                EventListener[] listeners = ((AbstractDocument)doc).getListeners(DocumentListener.class);
			                for (EventListener listener : listeners) {
			                    doc.removeDocumentListener((DocumentListener)listener);
			                }
			            } else {
			                // Fallback for other Document implementations
			                // Maintain our own list of added document listeners
			                if (documentListener != null) {  // Assuming you store your listener reference
			                    doc.removeDocumentListener(documentListener);
			                }
			                // Alternative: Track listeners in a collection if multiple are added
			              /*  for (DocumentListener listener : addedDocumentListeners) {
			                    doc.removeDocumentListener(listener);
			                }*/
			            }
			        }
			    
			    // Clean up syntax highlighter
			    if (syntaxHighlighter != null) {
			        syntaxHighlighter.removeSyntaxHightlighterDoc();
			        syntaxHighlighter = null;
			    }
			    
			    // Clean up undo manager
			    if (undoManager != null) {
			        undoManager.discardAllEdits();
			        undoManager = null;
			    }
			    
			    // Clean up code checker
			    if (checkcode != null) {
			        checkcode.removeCheckCodeDoc();
			        checkcode = null;
			    }
			    
			    // Clean up markdown previews
			    if (markdown1 != null) {
			        markdown1.dispose();
			        markdown1 = null;
			    }
			    if (markdown2 != null) {
			        markdown2.dispose();
			        markdown2 = null;
			    }
			    
			    // Clear other references
			    currentFile = null;
			    tabTitle = null;
			    originalContent = null;
			    
			    // Clear file watcher references
			    fileWatcher = null;
			    if (fileWatcherThread != null) {
			        fileWatcherThread.clear();
			        fileWatcherThread = null;
			    }
			}
			}

			
		   public synchronized void setCurrentFile(File file) {
		        this.currentFile = file;
		        updateTabTitle();
		        //updateTitle(currentFile.getAbsolutePath());

		    }

		    public File getCurrentFile() {
		        return currentFile;
		    }

		    public synchronized UndoManager getUndoManager() {
		        return undoManager;
		    }

		    public synchronized boolean getisModified() {
		        return isModified;

		    }

		    public synchronized void setModified(boolean modified) {
		        this.isModified = modified;
		        updateTabTitle();
		       // updateTitle(currentFile !=null ?currentFile.getAbsolutePath() : "Untitled");

		    }

		    public synchronized RSyntaxTextArea getTextArea() {
		        return textArea;
		    }
		   public synchronized String  getTextContent() {
		    	return textArea.getText();
		    }

		   public void setTextContent(String content) {
			  this.textArea.setText(content);
		   }

		    public SyntaxHighlighter getSyntaxHighlighter() {
		    	if(syntaxHighlighter == null) {
		    		syntaxHighlighter = new SyntaxHighlighter(textArea);
		    	}
				return syntaxHighlighter;

		    }

			public void setSyntaxHighlighter(SyntaxHighlighter syntaxHighlighter2) {
				this.syntaxHighlighter = syntaxHighlighter2;
			/*	if (syntaxHighlighter != null) {
			        syntaxHighlighter.highlightSyntax(); // Apply highlighting to current content
			    }*/
			}

			 public void setTabTitle(String tabTitle) {
			        this.tabTitle = tabTitle;
			        updateTabTitle();
			        updateTitle(tabTitle);
			    }

			 public String getTabTitle() {
			        return tabTitle;
			    }

			 public void setOriginalContent(String tempContent) {
				
				// originalContentHash = computeHybridHash(textArea.getText(),null);
				 this.originalContent = tempContent;
				// this.originalContent.setLength(0);
				 //this.originalContent.append(tempContent);
			 }

			public RTextScrollPane getScrollPane() {
				return scrollPane;
			}		
			
	public FileWatcher getFileWatcher() {
		 if (fileWatcher == null) {
		        fileWatcher = new FileWatcher(currentFile, textArea);
		    }
		return fileWatcher;
	}
	
	 private boolean fileLoaded = false; // Flag to track file loading

	 public boolean isFileLoaded() {
	        return fileLoaded;
	    }

	    public void setFileLoaded(boolean loaded) {
	        this.fileLoaded = loaded;
	    }
	
	    
	    public  MarkdownPreviewWindow getMDwindow() {
	    	return markdown1;
	    }
	    
	    public  MarkdownPreviewTab getMDTab() {
	    	return markdown2;
	    }
	    
	    public CheckCode getcheckCode() {
	    	return checkcode;
	    }


		public MouseAdapter getMouseListener() {
			return mouseListener;
		}
		
		public DocumentListener getDocumentListener() {
			return documentListener;
		}
		
	}



 


