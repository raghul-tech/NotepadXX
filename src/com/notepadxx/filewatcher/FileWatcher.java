/*package com.notepadxx.filewatcher;

import java.io.File;

import javax.swing.JOptionPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class FileWatcher implements Runnable {
	
	 private final File file;
	 private final RSyntaxTextArea textArea;
	 private WindowsFileWatcher WinFileWatch;
	 private LinuxFileWatcher LinuxFileWatch;
	 private String os = System.getProperty("os.name").toLowerCase();
	 
	  public FileWatcher(File file, RSyntaxTextArea textArea) {
	        this.file = file;
	        this.textArea = textArea;
	        
	        if (os.contains("win")) {
		    	 WinFileWatch = new WindowsFileWatcher(file,textArea);
		
		     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
		      LinuxFileWatch = new LinuxFileWatcher(file,textArea);
		}else {
            JOptionPane.showMessageDialog(
                    null,
                    "Unsupported operating system.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
	  }

	public void setSaving(boolean saving) {
		// TODO Auto-generated method stub
	     if (os.contains("win")) {
	    	 if (WinFileWatch != null) {
	         WinFileWatch.setSaving(saving);
	    	 }else {
	    		 WinFileWatch = new WindowsFileWatcher(file,textArea);
		         WinFileWatch.setSaving(saving); 
	    	 }
	     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	    	 if (LinuxFileWatch != null) {
	         LinuxFileWatch.setSaving(saving);
	    	 }else {
	          LinuxFileWatch = new LinuxFileWatcher(file,textArea);
	   	      LinuxFileWatch.setSaving(saving);
	    	 }
	    	 
	     } else {
	         JOptionPane.showMessageDialog(
	             null,
	             "Unsupported operating system.",
	             "Error",
	             JOptionPane.ERROR_MESSAGE
	         );
	     }
	}
	
	

	public void stopWatching() {
		// TODO Auto-generated method stub
	     if (os.contains("win")) {
	    	 if (WinFileWatch != null) {
	         WinFileWatch.stopWatching();
	    	 }else {
	        	 WinFileWatch = new WindowsFileWatcher(file,textArea);
		         WinFileWatch.stopWatching();
	    	 }
	     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	    	 if (LinuxFileWatch != null) {
	      LinuxFileWatch.stopWatching();
	    	 }else {
	          LinuxFileWatch = new LinuxFileWatcher(file,textArea);
	   	      LinuxFileWatch.stopWatching();
	    	 }
	     } else {
	         JOptionPane.showMessageDialog(
	             null,
	             "Unsupported operating system.",
	             "Error",
	             JOptionPane.ERROR_MESSAGE
	         );
	     }
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
	     if (os.contains("win")) {
	    	 if (WinFileWatch != null) {
	         WinFileWatch.run();
	    	 }else {
	    		WinFileWatch = new WindowsFileWatcher(file,textArea);
		         WinFileWatch.run();
	    	 }
	     } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	    	 if (LinuxFileWatch != null) {
	      LinuxFileWatch.run();
	    	 }else {
	          LinuxFileWatch = new LinuxFileWatcher(file,textArea);
	   	      LinuxFileWatch.run();
	    	 }
	    	 
	     } else {
	         JOptionPane.showMessageDialog(
	             null,
	             "Unsupported operating system.",
	             "Error",
	             JOptionPane.ERROR_MESSAGE
	         );
	     }
	} 

	
}*/


package com.notepadxx.filewatcher;

import java.io.File;

import javax.swing.JOptionPane;

//import javax.swing.JOptionPane;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class FileWatcher implements Runnable {
    
   // private final File file;
    //private final RSyntaxTextArea textArea;
    private final Object fileWatcher; // Generic Object to hold either Windows or Linux watcher

    public FileWatcher(File file, RSyntaxTextArea textArea) {
      //  this.file = file;
        //this.textArea = textArea;
        String os = System.getProperty("os.name").toLowerCase();
      //  fileWatcher = new LinuxFileWatcher(file, textArea);
        if (os.contains("win")) {
            fileWatcher = new WindowsFileWatcher(file, textArea);
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            fileWatcher = new LinuxFileWatcher(file, textArea);
        } else {
            fileWatcher = null;
            JOptionPane.showMessageDialog(
                null,
                "Unsupported operating system.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void setSaving(boolean saving) {
        if (fileWatcher instanceof WindowsFileWatcher) {
            ((WindowsFileWatcher) fileWatcher).setSaving(saving);
        } else if (fileWatcher instanceof LinuxFileWatcher) {
            ((LinuxFileWatcher) fileWatcher).setSaving(saving);
        }
        //System.err.println("FIle watcher set saving is "+ saving);
        
    }

    public void stopWatching() {
        if (fileWatcher instanceof WindowsFileWatcher) {
            ((WindowsFileWatcher) fileWatcher).stopWatching();
        } else if (fileWatcher instanceof LinuxFileWatcher) {
            ((LinuxFileWatcher) fileWatcher).stopWatching();
        }
    }

    @Override
    public void run() {
        if (fileWatcher instanceof WindowsFileWatcher) {
            ((WindowsFileWatcher) fileWatcher).run();
        } else if (fileWatcher instanceof LinuxFileWatcher) {
            ((LinuxFileWatcher) fileWatcher).run();
        }
    }
}

