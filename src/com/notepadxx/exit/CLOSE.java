package com.notepadxx.exit;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.notepadxx.notepadxx.NotepadXXV1_2_1;
import com.notepadxx.notepadxx.Texteditor;

public class CLOSE {

	//private static NotepadXXV1_2_1 notepad = new NotepadXXV1_2_1();
	
	public static void closeTab(Component selectedComp) {
	    SwingUtilities.invokeLater(() -> {

	   //     Component selectedComp = NotepadXXV1_2_1.getTabbedPane().getSelectedComponent();
 
	    	
	        // If the selected component is not a Texteditor, just close it
	        if (selectedComp == null || !(selectedComp instanceof Texteditor)) {
	            NotepadXXV1_2_1.getTabbedPane().remove(selectedComp);
	            NotepadXXV1_2_1.checkAndOpenDefaultTab();
	            return;
	        }

	        // Now safely cast to Texteditor
	        Texteditor editor = (Texteditor) selectedComp;

	        if (!editor.getisModified() || editor.getTextArea().getText().isEmpty()) {
	            updateForClosebtn(editor);
	        } else {
	            int confirm = JOptionPane.showConfirmDialog(
	                editor,
	                "Save file '" + editor.getTabTitle() + "' ?",
	                "Save",
	                JOptionPane.YES_NO_CANCEL_OPTION
	            );

	            if (confirm == JOptionPane.YES_OPTION) {
	                editor.saveFile();
	                while (editor.getisModified()) {
	                    try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // Wait 100ms before checking again
	                }
	                if (!editor.getisModified()) {
	                    updateForClosebtn(editor);
	                }
	            } else if (confirm == JOptionPane.NO_OPTION) {
	                updateForClosebtn(editor);
	            } else {
	                // Cancel – do nothing
	                return;
	            }
	        }

	        NotepadXXV1_2_1.checkAndOpenDefaultTab();
	    });
	}
	public static void closeAllTab() {
	    SwingWorker<Void, Texteditor> worker = new SwingWorker<>() {
	        private final List<Component> tabsToClose = new ArrayList<>();

	        @Override
	        protected Void doInBackground() {
	            int tabCount = NotepadXXV1_2_1.getTabbedPane().getTabCount();

	            for (int i = tabCount - 1; i >= 0; i--) {
	                Component comp = NotepadXXV1_2_1.getTabbedPane().getComponentAt(i);

	                if (comp instanceof Texteditor tab) {
	                    if (tab.getisModified() && !tab.getTextArea().getText().isEmpty()) {
	                        int confirm = JOptionPane.showConfirmDialog(
	                                NotepadXXV1_2_1.getFrame(),
	                                "Save file '" + tab.getTabTitle() + "'?",
	                                "Save",
	                                JOptionPane.YES_NO_CANCEL_OPTION
	                        );

	                        if (confirm == JOptionPane.YES_OPTION) {
	                            tab.saveFile();
	                            while (tab.getisModified()) {
	                                try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} // Wait 100ms before checking again
	                            }
	                            if (!tab.getisModified()) {
	                                tabsToClose.add(tab);
	                            }
	                        } else if (confirm == JOptionPane.NO_OPTION) {
	                            tabsToClose.add(tab);
	                        } else {
	                            return null; // Cancelled by user
	                        }
	                    } else {
	                        tabsToClose.add(tab);
	                    }
	                } else {
	                    // Any non-texteditor tab can just be closed
	                    tabsToClose.add(comp);
	                }
	            }

	            return null;
	        }

	        @Override
	        protected void done() {
	            SwingUtilities.invokeLater(() -> {
	                for (Component tab : tabsToClose) {
	                    if (tab instanceof Texteditor editor) {
	                        updateForClosebtn(editor);
	                    } else {
	                        NotepadXXV1_2_1.getTabbedPane().remove(tab);
	                    }
	                }

	                NotepadXXV1_2_1.checkAndOpenDefaultTab();
	            });
	        }
	    };

	    worker.execute();
	}

	public static void updateForClosebtn(Texteditor tab) {
	    if (tab == null) return;

	    File closedFile = tab.getCurrentFile();
	    String fileText = tab.getTextArea() != null ? tab.getTextArea().getText() : "";
	    NotepadXXV1_2_1.saveClosedTab(closedFile, fileText);
	  
	 /*   if(tab.currentFile != null) {
         removeEntry(tab.currentFile.toString());
	    }else {
	    	removeEntry(tab.tabTitle);
	    }*/
	    dispose(tab);
	    tab.cleanup();
	   try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	    
	 //   SwingUtilities.invokeLater(() -> {
	        // Remove the tab from the JTabbedPane
	        int index = NotepadXXV1_2_1.getTabbedPane().indexOfComponent(tab);
	        try {
	        if (index != -1) {
	            NotepadXXV1_2_1.getTabbedPane().remove(index);
	        }
	        }catch(Exception e ) {}
	        finally {
	        	 NotepadXXV1_2_1.checkAndOpenDefaultTab();
	        }
	   // notepad.getTabbedPane().remove(tab);

	        // Check if a default tab needs to be opened
	      //  NotepadXXV1_2_1.checkAndOpenDefaultTab();
	 //   });
	
	}

	private static void dispose(Texteditor tab) {
		if(tab!=null) {
	    tab.getTextArea().removeMouseListener(tab.getMouseListener());
	    tab.getTextArea().getDocument().removeDocumentListener(tab.getDocumentListener());
	    tab.getUndoManager().discardAllEdits(); // Clears undo history
	    tab.getcheckCode().removeCheckCodeDoc();
	    tab.getSyntaxHighlighter().removeSyntaxHightlighterDoc();
	    tab.setSyntaxHighlighter(null); // Dereference highlighter
	 //   backgroundTimer.stop(); // Stop any active timers (if uncommented)
		}
		}

	
}

