package com.notepadxx.notepadxx;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.MenuSelectionManager;

import com.notepadxx.exit.CLOSE;
import com.notepadxx.exit.ExitFrame;
import com.notepadxx.syntaxchecker.SyntaxChecker;


public class Shortcut {

    private SyntaxChecker checker;
    private Texteditor editor;
   
	public Shortcut(Texteditor editor) {
		
		this.editor = editor;
		checker = new SyntaxChecker(editor);
		 addShortcutKeys(); 
		 addTabShortcut();
		
	}
	
	

	// this is for adding shortcut
	@SuppressWarnings("serial")
	private void addShortcutKeys() {
		    	 InputMap inputMap = editor.getTextArea().getInputMap(JComponent.WHEN_FOCUSED);
		    	 ActionMap actionMap = editor.getTextArea().getActionMap();
		
		 

		    	// Ctrl + X to cut
		    	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "cut");
		    	    actionMap.put("cut", new AbstractAction() {
		    	        @Override
		    	        public void actionPerformed(ActionEvent e) {
		    	            editor.cutText();
		    	        }
		    	    });

		    	    // Ctrl + C to copy
		    	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "copy");
		    	    actionMap.put("copy", new AbstractAction() {
		    	        @Override
		    	        public void actionPerformed(ActionEvent e) {
		    	            editor.copyText();
		    	        }
		    	    });

		    	    // Ctrl + V to paste
		    	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "paste");
		    	    actionMap.put("paste", new AbstractAction() {
		    	        @Override
		    	        public void actionPerformed(ActionEvent e) {
		    	        	 editor.pasteText();
		    	        }
		    	    });

		    	  // ctrl + S this code for save
		    	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK),"save");
		    	actionMap.put("save",new AbstractAction() {
		    		@Override
		    		public void actionPerformed(ActionEvent e) {
		    			 editor.saveFile();
		    		 }
		    	 });
		    	//ctrl + O  open file
		    	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK),"openFile");
		    	actionMap.put("openFile",new AbstractAction() {
		    		@Override
		    		public void actionPerformed(ActionEvent e) {
		    			 editor.openFile();
		    		 }
		    	 });

		      //ctrl + n this code for new file
		    	 inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "newFile");
		         actionMap.put("newFile", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.newFile();  // Call your new file method
		             }
		         });
		         //ctrl + shift + s save all
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "saveAll");
		         actionMap.put("saveAll", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.saveAll();
		             }
		         });
		         //ctrl + Alt + s save As
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK), "saveAs");
		         actionMap.put("saveAs", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.saveFileAs();
		             }
		         });

		         // Ctrl + Z for Undo
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
		         actionMap.put("undo", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.undoText();
		                  }
		         });

		         // Ctrl + Y for Redo
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "redo");
		         actionMap.put("redo", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.redoText();
		             }
		         });

		         // Ctrl + F for Find (basic implementation)
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK), "find");
		         actionMap.put("find", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.findText();
		             }
		         });
		      // Ctrl + H
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK), "replace");
		         actionMap.put("replace", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.replaceText();
		             }
		         });

		         // Ctrl + P for Print
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK), "print");
		         actionMap.put("print", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.printText();

		             }
		         });
		         
		         // Ctrl + K for view md in tab 
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK), "md2");
		         actionMap.put("md2", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	  String ext = editor.getFileExtension(editor.getCurrentFile());
		            	  if (CheckCode.isItMD(ext)) {
		            	editor.openMarkdownPreviewTab(editor);
		                  }
		             }
		         });
		         
		         // Ctrl +shift+ K for view md in new frame 
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK), "md1");
		         actionMap.put("md1", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	  String ext = editor.getFileExtension(editor.getCurrentFile());
		                  if (CheckCode.isItMD(ext)) {
		            	editor.openMarkdownPreviewWindow(editor);
		                  }
		             }
		         });

		      // Ctrl + = for Increasing Font Size
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK), "increaseFont");
		         actionMap.put("increaseFont", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.setFontSize(+2);
		             }
		         });

		         // Ctrl + - for Decreasing Font Size
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK), "decreaseFont");
		         actionMap.put("decreaseFont", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.setFontSize(-2);
		             }
		         });

		         // Ctrl + 0 to Reset Font Size
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.CTRL_DOWN_MASK), "resetFontSize");
		         actionMap.put("resetFontSize", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.resetFontSize();
		             }
		         });

		         // Ctrl + W to close tab
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK), "closeTab");
		         actionMap.put("closeTab", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 CLOSE.closeTab(editor);
		             }
		         });

		         // Ctrl + M to change tool tip (advance or normal)
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK), "Advanced mode");
		         actionMap.put("Advanced mode", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            checker.toggleAdvancedMode();
		      
		             }
		         });

		      // Ctrl + shift + M to turn on or off the tooltip
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "tooltip on or off");
		         actionMap.put("tooltip on or off", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		          checker.toggleToolTip(); 

		             }
		         });

		         //ctrl + shift + W to close all tab
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "closeAllTab");
		         actionMap.put("closeAllTab", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 CLOSE.closeAllTab();
		             }
		         });

		      // Ctrl + Shift + E for opening File Explorer
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "Open File Explorer");
		         actionMap.put("Open File Explorer", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.Explorer();
		             }
		         });

		         // Ctrl + Shift + C for opening Command Prompt
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "Open Command Prompt");
		         actionMap.put("Open Command Prompt", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.Cmd();
		             }
		         });
		         // Ctrl + Shift + T  open recent closed tab
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "Open Recent Tab");
		         actionMap.put("Open Recent Tab", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		                 NotepadXXV1_2_0.openRecentClosedTab();
		             }
		         });

		         // Ctrl + Alt + A for opening Command Prompt as Admin
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK), "Open Admin Command Prompt");
		         actionMap.put("Open Admin Command Prompt", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		            	 editor.adminCmd();
		             }
		         });

		         // Ctrl + Alt + F for opening firefox
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK), "firefox Browser");
		         actionMap.put("firefox Browser", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		                 editor.FireFox();
		             }
		         });
		         
		      // Ctrl + Alt + E for opening edge
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK), "edge Browser");
		         actionMap.put("edge Browser", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		                 editor.edgeBrowser();
		             }
		         });

		      // Ctrl + ALT + C for opening chrome
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK), "chrome Browser");
		         actionMap.put("chrome Browser", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		                 editor.chromeBrowser();
		             }
		         });
		         
		         // Ctrl + Alt + T for opening Tor Browser
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK), "tor Browser");
		         actionMap.put("tor Browser", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		                 editor.TorBrowser();  // You can use the method openInTorBrowser() as defined earlier
		             }
		         });


		      // ALT + F4 for exit
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4,  InputEvent.ALT_DOWN_MASK), "exit");
		         actionMap.put("exit", new AbstractAction() {
		             @Override
		             public void actionPerformed(ActionEvent e) {
		                 ExitFrame.exit();
		             }
		         });

		        // Space button to remove highlight
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "removeHighlight");
		         actionMap.put("removeHighlight", new AbstractAction() {
		        	 @Override
		        	 public void actionPerformed(ActionEvent e) {
		        		 MenuSelectionManager.defaultManager().clearSelectedPath();
		        		 editor.removeHighlights(editor.getTextArea());
		        		 }
		        	 });
		         
		  

	}
	
	@SuppressWarnings("serial")
	private void addTabShortcut() {
		 InputMap inputMap = NotepadXXV1_2_0.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	        ActionMap actionMap = NotepadXXV1_2_0.getFrame().getRootPane().getActionMap();
	        // Ctrl + right arrow to move to the right side of the tab
	         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.CTRL_DOWN_MASK), "Tab Right");
	         actionMap.put("Tab Right", new AbstractAction() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                 NotepadXXV1_2_0.moveRight();
	             }
	         }); 
	         
	         // Ctrl + left arrow to move to the left side of the tab 
	         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.CTRL_DOWN_MASK), "Tab Left");
	         actionMap.put("Tab Left", new AbstractAction() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                 NotepadXXV1_2_0.moveLeft();
	             }
	         }); 
	         
	      // Ctrl + right arrow to move tab right
	         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "Move Tab Right");
	         actionMap.put("Move Tab Right", new AbstractAction() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                 NotepadXXV1_2_0.moveTabRight();
	             }
	         }); 
	         
	         // Ctrl + left arrow to move tab left
	         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "Move Tab Left");
	         actionMap.put("Move Tab Left", new AbstractAction() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                 NotepadXXV1_2_0.moveTabLeft();
	             }
	         }); 
	
	}

}
